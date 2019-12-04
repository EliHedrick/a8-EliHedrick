package a8;


import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import javax.swing.BoxLayout;
import javax.swing.JButton;

public class Main {
	public static void main(String[] args) {
		LifeModel model = new LifeModel();
		LifeView view = new LifeView();
		LifeController controller = new LifeController(model, view);
		
		JFrame main_frame = new JFrame();
		main_frame.setTitle("Game Of Life");
		main_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		main_frame.setContentPane(view);

		main_frame.pack();
		main_frame.setVisible(true);
	}
}
