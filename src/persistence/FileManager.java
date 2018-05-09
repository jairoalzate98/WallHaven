package persistence;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URL;
import java.net.URLConnection;

import javax.swing.JOptionPane;
import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

public class FileManager {
	
	public static final String URL_PAGE_INIT = "https://unsplash.com/search/photos/";

	public void getInfo(String search) throws IOException, SAXException, ParserConfigurationException{
		Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("172.16.0.73", 8080));
		URL url = new URL(URL_PAGE_INIT + search); 
		URLConnection connection = url.openConnection(proxy);
		connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Macintosh; U; Intel Mac OS X 10.4; en-US; rv:1.9.2.2) Gecko/20100316 Firefox/3.6.2");
		BufferedInputStream in = new BufferedInputStream(connection.getInputStream());
		int ch;
		PrintWriter pr = new PrintWriter("info.txt");
		while ((ch = in.read()) != -1) {
			pr.append((char) ch);
		}
		pr.close();
	}
	
	public static void main(String[] args) {
		FileManager fileManager = new FileManager();
		try {
			String info = JOptionPane.showInputDialog(null, "ingrese la palabra");
			fileManager.getInfo(info);
		} catch (IOException | SAXException | ParserConfigurationException e) {
			System.out.println(e.getMessage());
		}
	}
}
