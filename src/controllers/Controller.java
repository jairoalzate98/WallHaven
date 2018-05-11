package controllers;

import java.awt.event.ActionEvent;		
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.Timer;
import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import models.Manager;
import persistence.FileManager;
import views.MainWindow;

public class Controller implements ActionListener{

	private Manager manager;
	private FileManager fileManager;
	private MainWindow mainWindow;
	private Timer timer;
	
	public Controller() {
		fileManager = new FileManager();
		manager = new Manager();
		mainWindow = new MainWindow(this);
		mainWindow.setVisible(true);
		timer = new Timer(1000, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				refreshView();
			}
		});
	}

	private void initImages(String search) {
		try {
			fileManager.getInfo(search);
		} catch (IOException | SAXException | ParserConfigurationException e1) {
			System.out.println(e1.getMessage());
		}
		try {
			ArrayList<String> imgs = fileManager.readFile();
			manager.clearArray();
			for (String string : imgs) {
				manager.addImg(Manager.createImg(string));
			}		
		} catch (IOException | SAXException | ParserConfigurationException  e) {
			System.out.println(e.getMessage());
		}
	}

	private void refreshView() {
		mainWindow.initImgs(manager.getImgList());
		mainWindow.revalidate();
		mainWindow.repaint();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		switch (Events.valueOf(e.getActionCommand())) {
		case SEARCH:
			timer.start();
			initImages(mainWindow.getSearch());
			break;
		}
	}
}