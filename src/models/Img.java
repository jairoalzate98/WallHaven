package models;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class Img implements Runnable{

	private String path;
	private boolean convert;
	private boolean download;
	private boolean finish;
	private Thread thread;
	private String pathAbsolute;

	public Img(String path) {
		this.path = path;
		thread = new Thread(this);
		thread.start();
	}

	public String getPath() {
		return path;
	}

	@Override
	public void run() {
		while (!convert) {
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			if (!download) {
				try {
					download();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (!finish) {
				try {
					convert();
					convert = true;
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public boolean isConvert() {
		return convert;
	}

	public boolean isDownload() {
		return download;
	}

	private void convert() throws IOException {
		Image im = new ImageIcon("img/" + path.substring(41, path.length())).getImage();
		BufferedImage br = new BufferedImage(im.getWidth(null), im.getHeight(null), BufferedImage.TYPE_INT_RGB);
		Graphics bg = br.getGraphics();
		bg.drawImage(im, 0, 0, null);
		bg.dispose();
		//	    BufferedImage br = resize(bi, 400, 400);
		for (int i = 0; i < br.getWidth(); i++) {
			for (int j = 0; j < br.getHeight(); j++) {
				Color color = new Color(br.getRGB(i, j));
				int med = (color.getBlue() + color.getGreen() + color.getRed())/3;
				br.setRGB(i, j, new Color(med, med, med).getRGB());
			}
		}
		pathAbsolute = "img/BlackAndWhite/" + path.substring(41, path.length());
		ImageIO.write(br, "jpg", new File("img/BlackAndWhite/" + path.substring(41, path.length())));
	}

	private void download() throws IOException, MalformedURLException {
//		Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("172.16.0.73", 8080));
		URL url = new URL(path); 
//		URLConnection connection = url.openConnection(proxy);
//		connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Macintosh; U; Intel Mac OS X 10.4; en-US; rv:1.9.2.2) Gecko/20100316 Firefox/3.6.2");
		InputStream in = url.openStream();
		pathAbsolute = "img/" + path.substring(41, path.length());
		Files.copy(in, Paths.get("img/" + path.substring(41, path.length())));
		download = true;
	}

	public String getPathAbsolute() {
		return pathAbsolute;
	}
}