package a8;

import java.awt.event.ActionEvent;

public class LifeController {

	LifeModel model;
	LifeView view;

	public LifeController(LifeModel model, LifeView view) {
		this.model = model;
		this.view = view;

		view.addListener(this);

		model.addObserver(this);
	}
	
	public void getEvent(ActionEvent e) {
		String cmd = e.getActionCommand();
		
		// Respond accordingly
		if (cmd.equals("reset")) {
			model.resetGame();
		} else if (cmd.equals("next")) {
			model.next();
		} else if (cmd.equals("random")) {
			model.randomFill();
		} else if (cmd.equals("update settings")) {
			model.updateSettings(view.getLength(), view.getlowBirth(), view.getHighBirth(),
					 view.getLowSurvival(), view.getHighSurvival());
		} else if (cmd.equals("torus")) {
			model.toggleTorus();
		}
	}
	
	public void updateBoard(JSpotBoard board) {
		view.setBoard(board);
	}
}
