package GUI;

import javax.swing.*;  // For Swing components like JFrame, JButton, etc.
import java.awt.*;     // For layouts and other graphical utilities
import java.awt.event.*;  // For event handling (button clicks, etc.)


public class SampleGUI implements ActionListener {
	
	int count = 0;
	JLabel label;
	JFrame frame;
	JPanel panel;
	JButton button;
	

	 public SampleGUI() {
		
		//for creating the frame only
		frame = new JFrame(); //name of the window
		
		button = new JButton("Click Me!");
		button.addActionListener(this);
		
		label = new JLabel ("Number of Clicks: 0");
		
		//panel is within the frame(windows)
		panel = new JPanel();
		panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30)); //TBLR
		panel.setLayout(new GridLayout(0, 1)); //rows and columns
		panel.add(button);
		panel.add(label);
		
		frame.add(panel, BorderLayout.CENTER);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //close operation
		frame.setTitle("M First GUI");
		frame.pack(); //to match a certain size like free flowing
		frame.setVisible(true);
		
	}

	 public static void main(String[] args) {
		 new SampleGUI();
	 }

	public void actionPerformed(ActionEvent e) {
		count++;
		label.setText("Number of Clicks: " + count);
		
	}
}
