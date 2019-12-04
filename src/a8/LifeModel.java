package a8;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

public class LifeModel {

	private List<LifeController> observers;
	private JSpotBoard _board;
	private JSpotBoard _next_gen;
	private int _size;
	private int _low_birth;
	private int _high_birth;
	private int _low_survival;
	private int _high_survival;
	private boolean _torus;
	
	public LifeModel() {
		observers = new ArrayList<LifeController>();
		_size = 10;
		_next_gen = new JSpotBoard(_size, _size);
		_board = new JSpotBoard(_size,_size);
		_low_birth = 3;
		_high_birth = 3;
		_low_survival = 2;
		_high_survival = 3;
		_torus = false;
		for (Spot s : _board) {
			s.setBackground(new Color(0.8f, 0.8f, 0.8f));
			s.setSpotColor(Color.BLACK);;
		}
		for (Spot s : _next_gen) {
			s.setBackground(new Color(0.8f, 0.8f, 0.8f));
			s.setSpotColor(Color.BLACK);;
		}
	}
	
	public void addObserver(LifeController o) {
		observers.add(o);
	}
	
	public void updateObservers() {
		for(LifeController o : observers)
			o.updateBoard(_board);
	}
	
	public void resetGame() {
		for (Spot s : _board) {
			s.clearSpot();
		}
		for (Spot s : _next_gen) {
			s.clearSpot();
		}
		updateObservers();
	}
	
	public void next() {
		for(int i = 0; i < _size; i++)
			for(int j = 0; j < _size; j++) {
				int surrounding = 0;
				if(i > 0 && j > 0 && !_board.getSpotAt(i - 1,  j - 1).isEmpty())
					surrounding++;
				if(i > 0 && !_board.getSpotAt(i - 1,  j).isEmpty())
					surrounding++;
				if(i > 0 && j < _size - 1 && !_board.getSpotAt(i - 1,  j + 1).isEmpty())
					surrounding++;
				if(j > 0 && !_board.getSpotAt(i,  j - 1).isEmpty())
					surrounding++;
				if(j < _size - 1 && !_board.getSpotAt(i,  j + 1).isEmpty())
					surrounding++;
				if(i < _size - 1 && !_board.getSpotAt(i + 1,  j).isEmpty())
					surrounding++;
				if(i < _size - 1 && j > 0 && !_board.getSpotAt(i + 1,  j - 1).isEmpty())
					surrounding++;
				if(i < _size - 1 && j < _size - 1 && !_board.getSpotAt(i + 1,  j + 1).isEmpty())
					surrounding++;
				if(_torus) {
					if(i == 0 && j == 0 && !_board.getSpotAt(_size-1, _size-1).isEmpty())
						surrounding++;
					if(i == 0 && j == 0 && !_board.getSpotAt(0, _size-1).isEmpty())
						surrounding++;
					if(i == 0 && j == 0 && !_board.getSpotAt(_size-1, 0).isEmpty())
						surrounding++;
					if(i == 0 && j == 0 && !_board.getSpotAt(_size-1, 1).isEmpty())
						surrounding++;
					if(i == 0 && j == 0 && !_board.getSpotAt(1, _size - 1).isEmpty())
						surrounding++;
					if(i == 0 && (j == _size - 1) && !_board.getSpotAt(_size-1, 0).isEmpty())
						surrounding++;
					if(i == 0 && (j == _size - 1) && !_board.getSpotAt(_size-1, _size-1).isEmpty())
						surrounding++;
					if(i == 0 && (j == _size - 1) && !_board.getSpotAt(0,0).isEmpty())
						surrounding++;
					if(i == 0 && (j == _size - 1) && !_board.getSpotAt(1,0).isEmpty())
						surrounding++;
					if(i == 0 && (j == _size - 1) && !_board.getSpotAt(_size - 1, _size - 2).isEmpty())
						surrounding++;
					if((i == _size - 1) && j == 0 && !_board.getSpotAt(0, _size-1).isEmpty())
						surrounding++;
					if((i == _size - 1) && j == 0 && !_board.getSpotAt(_size - 1, _size - 1).isEmpty())
						surrounding++;
					if((i == _size - 1) && j == 0 && !_board.getSpotAt(0,0).isEmpty())
						surrounding++;
					if((i == _size - 1) && j == 0 && !_board.getSpotAt(0,1).isEmpty())
						surrounding++;
					if((i == _size - 1) && j == 0 && !_board.getSpotAt(_size - 2, _size - 1).isEmpty())
						surrounding++;
					if((i == _size - 1) && (j == _size - 1) && !_board.getSpotAt(0, 0).isEmpty())
						surrounding++;
					if((i == _size - 1) && (j == _size - 1) && !_board.getSpotAt(0, _size - 1).isEmpty())
						surrounding++;
					if((i == _size - 1) && (j == _size - 1) && !_board.getSpotAt(_size - 1, 0).isEmpty())
						surrounding++;
					if((i == _size - 1) && (j == _size - 1) && !_board.getSpotAt(_size-2, 0).isEmpty())
						surrounding++;
					if((i == _size - 1) && (j == _size - 1) && !_board.getSpotAt(0, _size -2).isEmpty())
						surrounding++;
					if(i == 0 && j!=0 && (j!= _size - 1) && !_board.getSpotAt(_size-1, j).isEmpty())
						surrounding++;
					if(i == 0 && j!=0 && (j!= _size - 1) && !_board.getSpotAt(_size-1, j-1).isEmpty())
						surrounding++;
					if(i == 0 && j!=0 && (j!= _size - 1) && !_board.getSpotAt(_size-1, j+1).isEmpty())
						surrounding++;
					if(j == 0 && i!= 0 && (i!= _size - 1) && !_board.getSpotAt(i, _size-1).isEmpty())
						surrounding++;
					if(j == 0 && i!= 0 && (i!= _size - 1) && !_board.getSpotAt(i-1, _size-1).isEmpty())
						surrounding++;
					if(j == 0 && i!= 0 && (i!= _size - 1) && !_board.getSpotAt(i+1, _size-1).isEmpty())
						surrounding++;
					if((i == _size - 1) && j!=0 && (j!= _size - 1) && !_board.getSpotAt(0, j).isEmpty())
						surrounding++;
					if((i == _size - 1) && j!=0 && (j!= _size - 1) && !_board.getSpotAt(0, j-1).isEmpty())
						surrounding++;
					if((i == _size - 1) && j!=0 && (j!= _size - 1) && !_board.getSpotAt(0, j+1).isEmpty())
						surrounding++;
					if((j == _size - 1) && i!= 0 && (i!= _size - 1) && !_board.getSpotAt(i, 0).isEmpty())
						surrounding++;
					if((j == _size - 1) && i!= 0 && (i!= _size - 1) && !_board.getSpotAt(i-1, 0).isEmpty())
						surrounding++;
					if((j == _size - 1) && i!= 0 && (i!= _size - 1) && !_board.getSpotAt(i+1, 0).isEmpty())
						surrounding++;
				}
				if(_board.getSpotAt(i,  j).isEmpty()) {
					if(surrounding >= _low_birth && surrounding <= _high_birth)
						_next_gen.getSpotAt(i, j).setSpot();
				}
				else {
					if(surrounding >= _low_survival && surrounding <= _high_survival)
						_next_gen.getSpotAt(i, j).setSpot();
					else
						_next_gen.getSpotAt(i, j).clearSpot();
				}
			}
		for(int i = 0; i < _size; i ++)
			for(int j = 0; j < _size; j++) {
				if(_next_gen.getSpotAt(i,j).isEmpty())
					_board.getSpotAt(i, j).clearSpot();
				else
					_board.getSpotAt(i, j).setSpot();
			}
		for(Spot s : _next_gen)
			s.clearSpot();
		updateObservers();
	}
	
	public void randomFill() {
		resetGame();
		for(Spot s: _board)
			if(Math.random() <= .25)
				s.toggleSpot();
		updateObservers();
	}
	
	public void updateSettings(int size, int lowB, int highB, int lowS, int highS) {
		_size = size;
		_next_gen = new JSpotBoard(_size, _size);
		_board = new JSpotBoard(_size,_size);
		_low_birth = lowB;
		_high_birth = highB;
		_low_survival = lowS;
		_high_survival = highS;
		for (Spot s : _board) {
			s.setBackground(new Color(0.8f, 0.8f, 0.8f));
			s.setSpotColor(Color.BLACK);
		}
		for (Spot s : _next_gen) {
			s.setBackground(new Color(0.8f, 0.8f, 0.8f));
			s.setSpotColor(Color.BLACK);
		}
		updateObservers();
	}
	
	public void toggleTorus() {
		_torus = !_torus;
	}
}
