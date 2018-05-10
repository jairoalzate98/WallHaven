package views;

import java.awt.GridLayout;
import java.awt.Image;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import models.Img;

public class MainWindow extends JFrame{

	private static final long serialVersionUID = 1L;

	public MainWindow(ArrayList<Img> imgList) {
		setTitle("Img");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setExtendedState(MAXIMIZED_BOTH);
		initImgs(imgList);
	}

	private void initImgs(ArrayList<Img> imgList) {
		setLayout(new GridLayout(imgList.size()/3, 3));
		for (Img img : imgList) {
			try {
				JLabel label = new JLabel();
				ImageIcon imageIcon = new ImageIcon(new ImageIcon(new URL(img.getPath())).getImage().getScaledInstance(150, 150, Image.SCALE_DEFAULT));
				label.setIcon(imageIcon);
				add(label);
			} catch (MalformedURLException e) {
				System.out.println(e.getMessage());
			}
		}
	}
}