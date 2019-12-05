package a8;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class LifeView extends JPanel implements ActionListener, SpotListener {

	private JSpotBoard _board;
	private JTextField _sizeF;
	private JTextField _lowB;
	private JTextField _highB;
	private JTextField _lowS;
	private JTextField _highS;
	private JTextField _torusF;
	private JTextField _temp;
	boolean removed;
	private List<LifeController> listeners;
	
	public LifeView() {

		/* Create SpotBoard and message label. */
		_board = new JSpotBoard(10, 10);
		listeners = new ArrayList<LifeController>();
		
		for (Spot s : _board) {
			s.setBackground(new Color(0.8f, 0.8f, 0.8f));
			s.setSpotColor(Color.BLACK);;
		}

		/* Set layout and place SpotBoard at center. */

		setLayout(new BorderLayout());
		_temp = new JTextField("Set settings to begin the game of life!");
		_temp.setEditable(false);
		add(_temp, BorderLayout.CENTER);

		/* Create subpanel for message area and reset button. */

		JPanel button_panel = new JPanel();
		button_panel.setLayout(new BorderLayout());

		/* Reset button. Add ourselves as the action listener. */

		JButton reset_button = new JButton("Restart");
		JButton step_button = new JButton("Next Generation");
		JButton random_button = new JButton("Randomly Fill Board");
		step_button.addActionListener(this);
		random_button.addActionListener(this);
		reset_button.addActionListener(this);
		step_button.setActionCommand("next");
		reset_button.setActionCommand("reset");
		random_button.setActionCommand("random");
		button_panel.add(reset_button, BorderLayout.EAST);
		button_panel.add(step_button, BorderLayout.CENTER);
		button_panel.add(random_button, BorderLayout.WEST);

		/* Add subpanel in south area of layout. */

		add(button_panel, BorderLayout.SOUTH);
		
		JPanel settings = new JPanel();
		settings.setLayout(new BoxLayout(settings, BoxLayout.Y_AXIS));
		JPanel size_panel = new JPanel();
		size_panel.setLayout(new BorderLayout());
		size_panel.setAlignmentX(Component.CENTER_ALIGNMENT);
		settings.add(size_panel);
		JPanel low_birth = new JPanel();
		low_birth.setLayout(new BorderLayout());
		low_birth.setAlignmentX(Component.CENTER_ALIGNMENT);
		settings.add(low_birth);
		JPanel high_birth = new JPanel();
		high_birth.setLayout(new BorderLayout());
		high_birth.setAlignmentX(Component.CENTER_ALIGNMENT);
		settings.add(high_birth);
		JPanel low_survival = new JPanel();
		low_survival.setLayout(new BorderLayout());
		low_survival.setAlignmentX(Component.CENTER_ALIGNMENT);
		settings.add(low_survival);
		JPanel high_survival = new JPanel();
		high_survival.setLayout(new BorderLayout());
		high_survival.setAlignmentX(Component.CENTER_ALIGNMENT);
		settings.add(high_survival);
		
		
		JLabel sizeL = new JLabel("Size:");
		size_panel.add(sizeL, BorderLayout.WEST);
		_sizeF = new JTextField("10");
		size_panel.add(_sizeF, BorderLayout.CENTER);
		JLabel lowBL = new JLabel("Low Birth Threshold:");
		low_birth.add(lowBL, BorderLayout.WEST);
		_lowB = new JTextField("3");
		low_birth.add(_lowB, BorderLayout.CENTER);
		JLabel highBL = new JLabel("High Birth Threshold:");
		high_birth.add(highBL, BorderLayout.WEST);
		_highB = new JTextField("3");
		high_birth.add(_highB, BorderLayout.CENTER);
		JLabel lowSL = new JLabel("Low Survival Threshold:");
		low_survival.add(lowSL, BorderLayout.WEST);
		_lowS = new JTextField("2");
		low_survival.add(_lowS, BorderLayout.CENTER);
		JLabel highSL = new JLabel("High Survival Threshold:");
		high_survival.add(highSL, BorderLayout.WEST);
		_highS = new JTextField("3");
		high_survival.add(_highS, BorderLayout.CENTER);
		JButton last = new JButton("Set Settings");
		last.setAlignmentX(Component.CENTER_ALIGNMENT);
		last.addActionListener(this);
		last.setActionCommand("update settings");
		settings.add(last);
		JPanel torus_panel = new JPanel();
		torus_panel.setLayout(new BorderLayout());
		torus_panel.setAlignmentX(Component.CENTER_ALIGNMENT);
		JButton torus = new JButton("Torus:");
		torus.setAlignmentX(Component.CENTER_ALIGNMENT);
		torus.addActionListener(this);
		torus.setActionCommand("torus");
		torus_panel.add(torus, BorderLayout.WEST);
		_torusF = new JTextField("false");
		_torusF.setEditable(false);
		torus_panel.add(_torusF, BorderLayout.CENTER);
		settings.add(torus_panel);
		add(settings, BorderLayout.WEST);
		_board.addSpotListener(this);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("update settings") && 
				(Integer.parseInt(_sizeF.getText()) < 10 || Integer.parseInt(_sizeF.getText()) > 500)) {
			_temp.setText("Illegal Size!");
			add(_temp, BorderLayout.CENTER);
			return;
		}
		if(e.getActionCommand().equals("update settings") && 
				(Integer.parseInt(_lowB.getText()) < 0 || Integer.parseInt(_highB.getText()) > 8
				|| Integer.parseInt(_lowB.getText()) > Integer.parseInt(_highB.getText()))) {
			_temp.setText("Illegal Birth Threshold!");
			add(_temp, BorderLayout.CENTER);
			return;
		}
		if(e.getActionCommand().equals("update settings") && 
				(Integer.parseInt(_lowS.getText()) < 0 || Integer.parseInt(_highS.getText()) > 8
				|| Integer.parseInt(_lowS.getText()) > Integer.parseInt(_highS.getText()))) {
			_temp.setText("Illegal Survival Threshold!");
			add(_temp, BorderLayout.CENTER);
			return;
		}
		if(e.getActionCommand().equals("torus"))
			toggleTorus();
		for(LifeController l : listeners)
			l.getEvent(e);
	}

	@Override
	public void spotClicked(Spot spot) {
		// TODO Auto-generated method stub
		spot.toggleSpot();
	}

	@Override
	public void spotEntered(Spot spot) {
		// TODO Auto-generated method stub
		spot.highlightSpot();
	}

	@Override
	public void spotExited(Spot spot) {
		// TODO Auto-generated method stub
		spot.unhighlightSpot();
	}

	public void addListener(LifeController l) {
		listeners.add(l);
	}
	
	public int getLength() {
		return Integer.parseInt(_sizeF.getText());
	}
	
	public int getlowBirth() {
		return Integer.parseInt(_lowB.getText());
	}
	
	public int getHighBirth() {
		return Integer.parseInt(_highB.getText());
	}
	
	public int getLowSurvival() {
		return Integer.parseInt(_lowS.getText());
	}
	
	public int getHighSurvival() {
		return Integer.parseInt(_highS.getText());
	}
	
	public void setBoard(JSpotBoard board) {
		remove(_temp);
		remove(_board);
		_board = board;
		add(_board, BorderLayout.CENTER);
		_board.removeSpotListener(this);
		_board.addSpotListener(this);
		revalidate();
		repaint();
	}
	
	public void toggleTorus() {
		if(_torusF.getText().contentEquals("false"))
			_torusF.setText("true");
		else
			_torusF.setText("false");
	}
}
