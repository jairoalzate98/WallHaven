package controllers;

import java.awt.event.ActionEvent;		
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import models.Manager;
import persistence.FileManager;
import views.MainWindow;

public class Controller implements ActionListener{

	private Manager manager;
	private FileManager fileManager;
	private MainWindow mainWindow;
	
	public Controller() {
		fileManager = new FileManager();
		manager = new Manager();
		mainWindow = new MainWindow(this);
		mainWindow.setVisible(true);
	}

	private void initImages(String search) {
		try {
			fileManager.getInfo(search);
		} catch (IOException | SAXException | ParserConfigurationException e1) {
			System.out.println(e1.getMessage());
		}
		try {
			ArrayList<String> imgs = fileManager.readFile();
			for (String string : imgs) {
				manager.addImg(Manager.createImg(string));
			}		
		} catch (IOException | SAXException | ParserConfigurationException e) {
			System.out.println("hola" +e.getMessage());
		}
		mainWindow.initImgs(manager.getImgList());
		mainWindow.revalidate();
		mainWindow.repaint();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		switch (Events.valueOf(e.getActionCommand())) {
		case SEARCH:
			initImages(mainWindow.getSearch());
			break;
		}
	}
}