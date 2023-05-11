package minesweeper;
import java.util.Scanner;

class minebombGrid {
	private char[][] bombGrid;
	private int size;
	private int freq;
	
	minebombGrid(int size,int freq){
		bombGrid = new char[size][size];
		this.size = size;
		this.freq = 5;
		if (!(freq == 0)) {this.freq = freq;}
	}
	
	public void printbombGrid() {
		for (int i = 0; i < bombGrid.length; i++) {
			for (int x = 0; x < bombGrid[0].length; x++) {
				System.out.print(bombGrid[i][x] + " ");
				System.out.print("|" + " ");
			}
			System.out.println();
	}}//EOM

	private void setBomb(int x, int y) {
		char bomb = '*';
		bombGrid[x][y] = bomb;
	}
	
	private void setNum(int x, int y, char num) {
		bombGrid[x][y] = num;
	}
	
	public char getChar(int x, int y) {
		return bombGrid[x][y];
	}
	
	private int getRand() {
		return (int)((Math.random())*(size));
	}//EOM
	
	private void setBombs(){
		int x = 0;
		int calibAmt = (int) (size*size / freq);
		System.out.println("There are " + calibAmt + " Bombs");
		while (x  < calibAmt) {
			int r1 = getRand();
			int r2 = getRand();
			if(!(isBomb(r1,r2))) {
				setBomb(r1,r2);
				x++;
			}
		}
	}

	public boolean isBomb(int x, int y) {
		char bomb = '*';
		if (bombGrid[x][y] != bomb) {
			return false;
		}
		return true;
	}
	
	private void setNums() {
		
		for (int i = 0; i < bombGrid.length; i++) {
			
			for(int x = 0; x < bombGrid[0].length; x++) {
				int bombCt = 0;
				if(!(isBomb(i,x))) {
					for(int z = 1; z > -2; z--) {
						for(int y = 1; y > -2; y--) {
							if(!((i+z < 0 || i+z >= bombGrid[0].length) || (y+x < 0 || y+x >= bombGrid.length) )) {
								if(isBomb(i+z,y+x)) {
									bombCt++;
								}
							}
						}
					}
					setNum(i,x,(char)(bombCt+48));
					}
				
					
		}	
	}}
	
	public void initGame() {setBombs(); setNums();}
}//end of minebombGrid class

class playerBoard extends minebombGrid {
	int size;
	private char[][] plrboard;
	
	playerBoard(int size, int freq){
		super(size,freq);
		plrboard = new char[size][size];
	}

	public void printPlayerBoard() {
		for (int i = 0; i < plrboard.length; i++) {
			for (int x = 0; x < plrboard[0].length; x++) {
				System.out.print(plrboard[i][x] + " ");
				System.out.print("|" + " ");
			}
			System.out.println();
	}}//EOM
	
	
}







class mainClass {
	
	public static void main(String[] args) {
		playerBoard gameSurface = new playerBoard(6,0);
		gameSurface.initGame();
		gameSurface.printbombGrid();
		System.out.println();
		gameSurface.printPlayerBoard();
		
}}
