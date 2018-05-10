package views;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controllers.Controller;
import controllers.Events;

public class JPanelHeader extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField jtfInfo;

	public JPanelHeader(Controller controller) {
		setLayout(new FlowLayout());
		setBackground(Color.BLACK);
		jtfInfo = new JTextField(30);
		jtfInfo.setPreferredSize(new Dimension(700, 40));
		jtfInfo.setFont(new Font("Ubuntu", Font.BOLD, 17));
		add(jtfInfo);
		JButton jbtnSearch = new JButton("Search");
		jbtnSearch.setActionCommand(Events.SEARCH.toString());
		jbtnSearch.setBackground(Color.BLACK);
		jbtnSearch.setForeground(Color.WHITE);
		jbtnSearch.setBorder(null);
		jbtnSearch.setPreferredSize(new Dimension(150, 40));
		jbtnSearch.addActionListener(controller);
		add(jbtnSearch);
	}
	
	public String getInfo(){
		return jtfInfo.getText();
	}
}