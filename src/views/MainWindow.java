package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
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
	private JPanelBar jPanelBar;

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
		jPanelBar = new JPanelBar();
		jPanelBar.setPreferredSize(new Dimension(getWidth(), 60));
		add(jPanelBar, BorderLayout.SOUTH);
	}

	public void initImgs(ArrayList<Img> imgList) {
		jpImgs.removeAll();
		int rows;
		if (imgList.size() < 3) {
			rows = 1;
		}else if (imgList.size() % 3 != 0) {
			rows = (imgList.size() / 3) + 1;
		}else{
			rows = imgList.size() / 3;
		}
		jpImgs.setLayout(new GridLayout(rows, 3, 20, 20));
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
	
	public void setValues(int[] values){
		jPanelBar.setDownload(values[0]);
		jPanelBar.setConvert(values[1]);
		jPanelBar.setTotal(values[2]);
	}
}