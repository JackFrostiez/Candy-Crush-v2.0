package system;

import java.awt.Color;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import code.Board;

public class ActionHandler implements ActionListener{
	
	private static boolean _selected;
	private Board _b;
	private JButton _jb;
	private Point _p;
	
	public ActionHandler(JButton jb,Board g, int i, int j){
		_b = g;
		_jb = jb;
		_p = new Point(i,j);
		
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		if(_selected ){
			_selected = false;
			_b.setSelected(_selected,_p);
		}
		else{
			_selected = true;
			_b.setSelected(_selected,_p);
			_jb.setBackground(Color.RED);
		}
		
	}
	
}
