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
	
	public String getSearch(){
		return jPanelHeader.getInfo();
	}
}