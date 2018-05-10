package controllers;

import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import models.Manager;
import persistence.FileManager;
import views.MainWindow;

public class Controller {

	private Manager manager;
	private FileManager fileManager;
	private MainWindow mainWindow;
	
	public Controller() {
		fileManager = new FileManager();
		manager = new Manager();
		try {
			ArrayList<String> imgs = fileManager.readFile();
			for (String string : imgs) {
				manager.addImg(Manager.createImg(string));
			}
		} catch (IOException | SAXException | ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		mainWindow = new MainWindow(manager.getImgList());
		mainWindow.setVisible(true);
	}
}