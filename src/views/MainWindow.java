package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Image;
import java.io.File;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import controllers.Controller;
import models.Img;

public class MainWindow extends JFrame{

	private static final long serialVersionUID = 1L;
	private JPanel jpImgs;
	private JPanelHeader jPanelHeader;

	public MainWindow(Controller controller) {
		setTitle("Img");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setExtendedState(MAXIMIZED_BOTH);
		setIconImage(new ImageIcon(getClass().getResource("/img/icon.png")).getImage());
		jPanelHeader = new JPanelHeader(controller);
		add(jPanelHeader, BorderLayout.NORTH);
		jpImgs = new JPanel();
		jpImgs.setBorder(null);
		jpImgs.setBackground(Color.BLACK);
		JScrollPane js = new JScrollPane(jpImgs);
		js.setBorder(null);
		add(js, BorderLayout.CENTER);
	}

	public void initImgs(ArrayList<Img> imgList) {
		jpImgs.removeAll();
		jpImgs.setLayout(new GridLayout(imgList.size() / 3, 3));
		File url = new File("img/BlackAndWhite");
		File[] files = url.listFiles();		
		for (File file : files) {
			if (file.getName().substring(file.getName().length() - 3, file.getName().length()).equals("jpg")) {
				JLabel jlimg = new JLabel();
				ImageIcon imageIcon = new ImageIcon(new ImageIcon(file.getAbsolutePath()).getImage().getScaledInstance(350, 350, Image.SCALE_DEFAULT));
				jlimg.setIcon(imageIcon);
				jpImgs.add(jlimg);
				revalidate();
				repaint();
			}
		}
	}
	
	public String getSearch(){
		return jPanelHeader.getInfo();
	}
}