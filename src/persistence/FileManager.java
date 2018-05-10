package persistence;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

public class FileManager {
	
	public static final String URL_PAGE_INIT = "https://wallpaperscraft.com/search/?query=";
	public static final String URL_PRINCIPAL = "https://wallpaperscraft.com";
	
	public void getInfo(String search) throws IOException, SAXException, ParserConfigurationException{
//		Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("172.16.0.73", 8080));
		URL url = new URL(URL_PAGE_INIT + search + "/"); 
//		URLConnection connection = url.openConnection(proxy);
//		connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Macintosh; U; Intel Mac OS X 10.4; en-US; rv:1.9.2.2) Gecko/20100316 Firefox/3.6.2");
		BufferedInputStream in = new BufferedInputStream(url.openStream());
		int ch;
		PrintWriter pr = new PrintWriter("info.txt");
		while ((ch = in.read()) != -1) {
			pr.append((char) ch);
		}
		pr.close();
	}
	
	public void generateInfo(String search) throws IOException, SAXException, ParserConfigurationException{
//		Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("172.16.0.73", 8080));
		URL url = new URL(URL_PRINCIPAL + search); 
//		URLConnection connection = url.openConnection(proxy);
//		connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Macintosh; U; Intel Mac OS X 10.4; en-US; rv:1.9.2.2) Gecko/20100316 Firefox/3.6.2");
		BufferedInputStream in = new BufferedInputStream(url.openStream());
		int ch;
		PrintWriter pr = new PrintWriter("img.txt");
		while ((ch = in.read()) != -1) {
			pr.append((char) ch);
		}
		pr.close();
	}
	
	public ArrayList<String> readFile() throws IOException, SAXException, ParserConfigurationException{
		ArrayList<String> imgs = new ArrayList<>();
		BufferedReader br = new BufferedReader(new FileReader(new File("info.txt")));
		String data = "";
		while((data = br.readLine()) != null){
			Pattern p = Pattern.compile("href=\"/wallpaper/(.*?)\"", Pattern.CASE_INSENSITIVE);
			Matcher m = p.matcher(data);
			if (m.find()) {
				String url = data.substring(m.start() + 6, m.end() - 1);
				imgs.add(readImgs(url, true));
			}
		}
		br.close();
		return imgs;
	}
	
	public String readImgs(String path, boolean f) throws IOException, SAXException, ParserConfigurationException{
		generateInfo(path);
		BufferedReader br = new BufferedReader(new FileReader(new File("img.txt")));
		if (f) {
			String data = "";
			while((data = br.readLine()) != null){
				Pattern p = Pattern.compile("href=\"/download/(.*?)\"", Pattern.CASE_INSENSITIVE);
				Matcher m = p.matcher(data);
				if (m.find()) {
					String pa = data.substring(m.start() + 6, m.end() - 1);
					br.close();
					return readImgs(pa, false);
				}
			}
		} else {
			String data = "";
			while((data = br.readLine()) != null){
				Pattern p = Pattern.compile("href=\"https://images.wallpaperscraft.com/image/(.*?)\"", Pattern.CASE_INSENSITIVE);
				Matcher m = p.matcher(data);
				if (m.find()) {
					br.close();
					return data.substring(m.start() + 6, m.end() - 1);
				}
			}
		}
		br.close();
		return "";
	}
}