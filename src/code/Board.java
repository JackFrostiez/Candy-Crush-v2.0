package code;

import java.awt.Point; 
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Random;

import graphics.View;

public class Board {
	
	private boolean _selected;
	private Random _rand;
	private int _rows, _cols;
	private Game _g;
	private ArrayList<String> _colors;
	private ArrayList<ArrayList<String>> _board;
	Point _p1,_p2;
	
	public Board(Game g,int rows, int cols){
		_g = g;
		_rows = rows;
		_cols = cols;
		_selected = false;
		generateImageList();
	}
	
	public void generateImageList(){
		_colors = new ArrayList<String>();
		_colors.add("resource/green.png");
		_colors.add("resource/red.png");
		_colors.add("resource/blue.png");
		_colors.add("resource/yellow.png");
		_colors.add("resource/purple.png");

		_board =  new ArrayList<ArrayList<String>>();
		
		_rand = new Random(); 
		//sets up rows and cols of images 
		//to be placed on jbuttons

		for(int i = 0; i < _rows; i++){	 	 
			_board.add(new ArrayList<String>());		 
			for(int j = 0; j < _cols; j++){ 			
				int num = _rand.nextInt(5); //random num between 1 to 4 inclusive colors
				System.out.println(num);
				_board.get(i).add(_colors.get(num));
			}
		}
	} 
	
	public ArrayList<ArrayList<String>> getImageList(){
		return getList();
	}
	
	public void setSelected(boolean value,Point coords){
		_selected = value;
		System.out.println("(" + (int) coords.getX() + "," + (int) coords.getY() + ")");
		//if already selected, determine the result of 1st and 2nd selection.
		if(!_selected){ 
			_p1 = coords;
			if(adjacent(_p1,_p2)){
				System.out.println("Swapped!");
				swap();
			}
			else{
				System.out.println("Cannot be swapped if far off, or diagonal");
				_g.updateView();
			}
		}
		//If not already selected, then take the 1st selection.
		else{
			_p2 = coords;
		}
	}
	
	public void swap(){ //swap the selected tiles
		int x,y,x2,y2;
		x = (int) _p1.getX();
		y = (int) _p1.getY();
		x2 = (int) _p2.getX();
		y2 = (int) _p2.getY();
		detectMatch(x,y,x2,y2);
	}
	
	public void detectMatch(int x, int y, int x2, int y2){
		HashSet<Point> matches = horizontalMatch(x,y,x2,y2);
		matches.addAll(verticalMatch(x,y,x2,y2));
		
		Iterator<Point> points = matches.iterator();
		
		if(matches.size() >= 3){
			while(points.hasNext()){
				Point p = points.next();
				if(p.y == 0){ // if the tile is at top, then give it a random tile
					_board.get(p.x).set(p.y,_colors.get(new Random().nextInt(5)));
					System.out.println("check");
				}
				else{
					while(p.y > 0){ // Otherwise, shift the colors above this tile down to it
						_board.get(p.x).set(p.y,_board.get(p.x).get(p.y-1));
						p.y -= 1;
					}
					System.out.println("check 2");
					_board.get(p.x).set(p.y,_colors.get(new Random().nextInt(5)));
				}
			}
		}
	}
	
	public HashSet<Point> horizontalMatch(int x, int y, int x2, int y2){
		HashSet<Point> matches = new HashSet<Point>();
		for(int a = 0; a < _rows; a++){ //Checks every three tiles same as 1st selection tile
			for(int b = 0; b < _cols-3; b++){
				if(_board.get(x).get(y) == _board.get(a).get(b)){
					if(_board.get(x).get(y) == _board.get(a+1).get(b)){
						if(_board.get(x).get(y) == _board.get(a+2).get(b)){
							matches.add(new Point(a,b));
							matches.add(new Point(a+1,b));
							matches.add(new Point(a+2,b));
						}
					}
				}
			}
		}
		for(int a = 0; a < _rows-3; a++){ //Checks every three tiles same as 2nd selection tile
			for(int b = 0; b < _cols; b++){
				if(_board.get(x2).get(y2) == _board.get(a).get(b)){
					if(_board.get(x2).get(y2) == _board.get(a+1).get(b)){
						if(_board.get(x2).get(y2) == _board.get(a+2).get(b)){
							matches.add(new Point(a,b));
							matches.add(new Point(a+1,b));
							matches.add(new Point(a+2,b));
						}
					}
				}
			}
		}
		return matches;
	}
	
	public HashSet<Point> verticalMatch(int x, int y, int x2, int y2){
		HashSet<Point> matches = new HashSet<Point>();
		ArrayList<String> mList1 = new ArrayList<String>();
		for(int a = 0; a < _rows-2; a++){ //Checks every three tiles same as 1st selection tile
			for(int b = 0; b < _cols-2; b++){
				if(_board.get(x).get(y) == _board.get(a).get(b)){
					if(_board.get(x).get(y) == _board.get(a).get(b+1)){
						if(_board.get(x).get(y) == _board.get(a).get(b+2)){
							matches.add(new Point(a,b));
							matches.add(new Point(a,b+1));
							matches.add(new Point(a,b+2));
						}
					}
				}
			}
		}
		for(int a = 0; a < _rows-2; a++){ //Checks every three tiles same as 2nd selection tile
			for(int b = 0; b < _cols-2; b++){
				if(_board.get(x2).get(y2) == _board.get(a).get(b)){
					if(_board.get(x2).get(y2) == _board.get(a).get(b+1)){
						if(_board.get(x2).get(y2) == _board.get(a).get(b+2)){
							matches.add(new Point(a,b));
							matches.add(new Point(a,b+1));
							matches.add(new Point(a,b+2));
						}
					}
				}
			}
		}
		return matches;
	}
	
	private boolean adjacent(Point p, Point q) {
		return Math.abs(p.x-q.x) + Math.abs(p.y-q.y) == 1;
	}
	
	public String get(Point p) {
		return _board.get(p.x).get(p.y);
	}

	private String set(Point p, String s) {
		return _board.get(p.x).set(p.y, s);
	}
	
	public ArrayList<ArrayList<String>> getList(){
		return _board;
	}
}
