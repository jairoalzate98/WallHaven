package controllers;

import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JOptionPane;
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
		String search = JOptionPane.showInputDialog(mainWindow, "Ingrese la palabra a buscar");
		fileManager = new FileManager();
		try {
			fileManager.getInfo(search);
		} catch (IOException | SAXException | ParserConfigurationException e1) {
			System.out.println(e1.getMessage());
		}
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