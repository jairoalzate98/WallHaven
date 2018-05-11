package views;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JPanel;

public class JPanelBar extends JPanel{

	private static final long serialVersionUID = 1L;
	private int download;
	private int convert;
	private int total;

	public JPanelBar() {
	}

	public int getDownload() {
		return download;
	}

	public void setDownload(int download) {
		this.download = download;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public int getConvert() {
		return convert;
	}

	public void setConvert(int convert) {
		this.convert = convert;
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		g.drawRect((getWidth() / 2) - 200, getHeight() - 50, 400, 40);
		try{
			g.fillRect((getWidth() / 2) - 200, getHeight() - 50, (download*400)/total, 40);
			g.setColor(Color.BLACK);
			g.setFont(new Font("Ubunutu", Font.BOLD, 17));
			g.drawString("Descangadas y convitidas -> " + download + "de " + total, (getWidth() / 2) - 150, getHeight() - 25);
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}