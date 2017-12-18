package code;

import java.awt.Point;
import java.util.ArrayList;
import java.util.HashSet;

import graphics.View;

public class Game implements Runnable {
	
	View _v;
	Board _b;
	int _length, _width,_rows,_cols;
	
	public Game(String title,int length, int width, int rows, int cols){
		this._length = length;
		this._width = width;
		_rows = rows;
		_cols = cols;		
		_b = new Board(this,rows,cols);
		_v = new View(_b,title,length,width,rows,cols);
	}

	@Override
	public void run() {
		updateView();
	}
	
	public View getView(){
		return _v;
	}
	
	public void updateView(){
		_v.update();
	}
}

