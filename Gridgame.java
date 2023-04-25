// My simple Java program Final project, 5x5 multiplayer grid game 
package gridGame;
import java.util.ArrayList;
import java.util.Scanner;

class GameGrid {
	private int[][] Board = new int[5][5];
	
	//returns sum of grid by iteration
	public int gridSum() {
		int sum = 0;
		for (int i = 0; i < Board.length; i++) {
			for (int x = 0; x < Board[0].length; x++) {
				sum += Board[i][x];
			}
		}
		return sum;
	}

	//use of Math.random & basic iteration
	public void generateRandomPoints() {
		for (int i = 0; i < Board.length; i++) {
			for (int x = 0; x < Board[0].length; x++) {
				Board[i][x] = (int)(Math.random() * 20) + 1;
			}
		}
	}
	//checks if number is > 10, then adds space for beautification 
	public void printGrid() {
		for (int i = 0; i < Board.length; i++) {
			for (int x = 0; x < Board[0].length; x++) {
				System.out.print(Board[i][x] + " ");
				if (Board[i][x] < 10) {
					System.out.print(" ");
				}
				System.out.print("|" + " ");
			}
			System.out.println();
	}
}
	//use in main for deleting points after walked on
	public void beenWalked(int xpos, int ypos) {
		Board[ypos][xpos] = 0;
	}
	
	public int getPoint(int xpos, int ypos) {
		return Board[ypos][xpos];
	}
	//iterates through ArrayList which contains player objects and gives a unique name(number) to each player
	//essentially updates Player position data with visual grid as '88' '99' etc.
	public void atPos(ArrayList<Player> player) {
		int name = 88;
		for (int i = 0; i < player.size(); i ++) {
			Board[player.get(i).showyPos()][player.get(i).showxPos()] = name;
			name += 11;
			
		}
	}
	
}
class Player {
	private String name;
	private int score;
	private int xPos;
	private int yPos;
	Player(String name, int xPos, int yPos){
		this.name = name;
		this.score = 0;
		//and operators: sets random number if x/y constructors greater than 5
		this.xPos = xPos < 5 ? xPos : randNum();
		this.yPos = yPos < 5 ? yPos : randNum();
		
	}
	
	private int randNum() {
		return (int)(Math.random()*5);
	}
	
	//accessors
	public String showName() {return name;}
	public int showScore() {return score;}
	public int showxPos() {return xPos;}
	public int showyPos() {return yPos;}
	//mutator
	public void movexPos(int edit) {xPos = edit;}
	public void moveyPos(int edit) {yPos = edit;}
	public void editScore(int edit) {score = edit;}
		
	
	
}

class mainClass {
	//editing score by taking current score and getting the point in the new position of player
	public static void updateScore(Player p, GameGrid g) {
		
		p.editScore(p.showScore() + g.getPoint(p.showxPos(), p.showyPos()));
		
	}
	
	//first checks if move is possible (not OOB) then moves position in Player class
	public static void movePlayer(Player player, GameGrid grid, String direction) {
			if(direction.equals("down") && player.showyPos() +1 < 5){grid.beenWalked(player.showxPos(), player.showyPos()); player.moveyPos(player.showyPos()+1); updateScore(player,grid);}
			else if(direction.equals("up") && player.showyPos() -1 >= 0){grid.beenWalked(player.showxPos(), player.showyPos());player.moveyPos(player.showyPos()-1); updateScore(player,grid);}
			else if(direction.equals("left") && player.showxPos() -1 >= 0){grid.beenWalked(player.showxPos(), player.showyPos());player.movexPos(player.showxPos()-1); updateScore(player,grid);}
			else if(direction.equals("right") && player.showxPos() +1 < 5){grid.beenWalked(player.showxPos(), player.showyPos());player.movexPos(player.showxPos()+1); updateScore(player,grid);}
			else {
				System.out.println("Invalid Direction");
			}
	}
	
	public static void main(String[] args) {
		ArrayList<Player> players = new ArrayList<Player>();
		
		GameGrid grid1 = new GameGrid();
		//10,10 since if > 5, it chooses random number in bounds which is preferable for gameplay
		Player p1 = new Player("Player1",10,10); players.add(p1);
		Player p2 = new Player("Player2",10,10); players.add(p2);
		System.out.println("Player 1 is '88' and Player 2 is '99'. Move them around the board to eat all the points and win!");
		grid1.generateRandomPoints();
		//updates visual position
		grid1.atPos(players);
		
		//while greater than the 2 players (88+99) keep looping (stops loop when everything but players are left as sum == the players
		while (grid1.gridSum() > 190) {
			grid1.printGrid();
			System.out.println (p1.showName() + " | Points: " + p1.showScore());
			System.out.println (p2.showName() + " | Points: " + p2.showScore());
			
			Scanner getDirection = new Scanner(System.in);
			System.out.println("P1: What Direction Next?: ");
			
			String direction = getDirection.nextLine();
			movePlayer(p1,grid1,direction);
			grid1.atPos(players);
			grid1.printGrid();
			System.out.println (p1.showName() + " | Points: " + p1.showScore());
			System.out.println (p2.showName() + " | Points: " + p2.showScore());
			
			Scanner getDirection2 = new Scanner(System.in);
			System.out.println("P2: What Direction Next?: ");
			
			String direction2 = getDirection2.nextLine();
			movePlayer(p2,grid1,direction2);
			grid1.atPos(players);
		}
		if (p1.showScore() > p2.showScore()) {
			System.out.println("Congrats " + p1.showName() + ", You Win!");
		}
		else {
			System.out.println("Congrats " + p2.showName() + ", You Win!");
		}
		
	}
}
