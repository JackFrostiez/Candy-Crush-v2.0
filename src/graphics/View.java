package graphics;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import code.Board;
import system.ActionHandler;

public class View {
	
	private JFrame _frame;
	private int _length, _width, _rows, _cols;
	private String _title, _score = "111", _highscore;
	public ArrayList<ArrayList<JButton>> _jButtons;
	private JPanel _jp, _scoreboard;
	private Board _b;
	
	public View(Board b, String title, int length, int width, int rows, int cols){
		_b = b;
		_length = length;
		_width = width;
		_title = title;
		_rows = rows;
		_cols = cols;
		startUp();
	}
	
	private void startUp(){
		_frame = new JFrame(_title);
		_frame.getContentPane().setLayout(new BoxLayout(_frame.getContentPane(), BoxLayout.X_AXIS));
		_jp = new JPanel();
		_jp.setLayout(new GridLayout(_rows,_cols));
		_frame.add(_jp);
		_scoreboard = new JPanel();
		JTextArea jta = new JTextArea();
		JTextArea jta2 = new JTextArea();
		jta.setText("\n\n\n    CURRENT HIGHSCORE:  " + "\n             " + _score +
				"\n      CURRENT SCORE:     " + "\n             " + _score);
		jta.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
		jta.setEditable(false);
		//jta2.setText("");
		jta2.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
		jta2.setEditable(false);
		_scoreboard.add(jta);
		_scoreboard.add(jta2);
		_scoreboard.setLayout(new BoxLayout(_scoreboard,BoxLayout.Y_AXIS));

		_frame.add(_scoreboard);
		generateButtons();
		update();
		_frame.setVisible(true);
		_frame.setLocationRelativeTo(null);
		_frame.setDefaultCloseOperation(_frame.EXIT_ON_CLOSE);
		_frame.pack();
		_frame.repaint();

	}
	
	private void generateButtons(){
		_jButtons = new ArrayList<ArrayList<JButton>>();
		//stores all new jbuttons into rows
		for(int i = 0; i < _rows; i++){	 	 
			_jButtons.add(new ArrayList<JButton>());
			for(int j = 0; j < _cols; j++){ 
				JButton jb = new JButton();
				_jButtons.get(i).add(jb);
				_jp.add(jb);
				jb.addActionListener(new ActionHandler(jb,_b,i,j));
				
			}
		}
	}
	
	public void update(){
		ArrayList<ArrayList<String>> list = _b.getImageList();
		for(int i = 0; i < _rows; i++){	 	 
			for(int j = 0; j < _cols; j++){ 
				_jButtons.get(i).get(j).setBackground(Color.white);
				_jButtons.get(i).get(j).setIcon(new ImageIcon(list.get(i).get(j)));
			}
		}
	}
}
