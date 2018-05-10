package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import models.Img;

public class MainWindow extends JFrame{

	private static final long serialVersionUID = 1L;
	private JPanel jpImgs;

	public MainWindow(ArrayList<Img> imgList) {
		setTitle("Img");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setExtendedState(MAXIMIZED_BOTH);
		jpImgs = new JPanel();
		jpImgs.setBackground(Color.WHITE);
		JScrollPane js = new JScrollPane(jpImgs);
		add(js, BorderLayout.CENTER);
		initImgs(imgList);
	}

	private void initImgs(ArrayList<Img> imgList) {
		jpImgs.setLayout(new GridLayout(imgList.size()/3, 3, 10, 10));
		for (Img img : imgList) {
			try {
				BufferedImage br = ImageIO.read(new URL(img.getPath())); 
				for (int i = 0; i < br.getWidth(); i++) {
					for (int j = 0; j < br.getHeight(); j++) {
						Color color = new Color(br.getRGB(i, j));
						int med = (color.getBlue() + color.getGreen() + color.getRed())/3;
						br.setRGB(i, j, new Color(med, med, med).getRGB());
					}
				}
				JLabel label = new JLabel();
				ImageIcon imageIcon = new ImageIcon(br);
				ImageIcon imageIconFinal = new ImageIcon(imageIcon.getImage().getScaledInstance(250, 250, Image.SCALE_DEFAULT));
				label.setIcon(imageIconFinal);
				jpImgs.add(label);
			} catch (IOException e) {
				System.out.println(e.getMessage());
			}
		}
	}
}