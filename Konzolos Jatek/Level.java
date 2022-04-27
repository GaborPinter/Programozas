
import java.util.Random;

import hu.ak_akademia.basicgame.entity.Enemy;
import hu.ak_akademia.basicgame.entity.Player;
import hu.ak_akademia.basicgame.entity.Powerup;
import hu.ak_akademia.basicgame.entity.StrengthPowerup;

public class Level {
	private final int height;
	private final int width;
	private final String[][] level;
	private final Random RANDOM;
	private Player player;
	private Enemy[] enemies;
	private Powerup[] powerups;

	public Level(Random random, int height, int width) {
		
		this.RANDOM = random;
		this.height = height;
		this.width = width;
		this.level = new String[height][width];
		int lastrowindex = height - 1;
		int lastcolumnindex = width - 1;
		do {
			initLevelWithSurroundingWalls(height, width, lastrowindex, lastcolumnindex);
			addrandomwalls();
		} while (!ispassable());
	}

	private void initLevelWithSurroundingWalls(int height, int width, int lastrowindex, int lastcolumnindex) {
		for (int row = 0; row < height; row++) {
			for (int column = 0; column < width; column++) {
				if (row == 0 || row == lastrowindex || column == 0 || column == lastcolumnindex) {
					level[row][column] = "X";
				} else {
					level[row][column] = " ";
				}
			}
		}
	}	
	
	public void setPlayer(Player player) {
		this.player = player;
	}
	
	public Player getPlayer() {
		return player;
	}

	public Enemy[] getEnemies() {
		return enemies;
	}

	public void setEnemies(Enemy[] enemies) {
		this.enemies = enemies;
	}

	public Powerup[] getPowerups() {
		return powerups;
	}

	public void setPowerups(Powerup[] powerups) {
		this.powerups = powerups;
	}

	public void addrandomwalls() {
		addrandomwalls(15, 15);
	}

	public void addrandomwalls(int numberofhorizontalwalls, int numberofverticalwalls) {
		for (int i = 0; i < numberofhorizontalwalls; i++) {
			addhorizontalwall();
		}
		for (int i = 0; i < numberofverticalwalls; i++) {
			addverticalwall();
		}
	}

	private void addhorizontalwall() {
		int wallwidth = RANDOM.nextInt(width - 3);
		int wallrow = RANDOM.nextInt(height - 2) + 1;
		int wallcolumn = RANDOM.nextInt(width - 2 - wallwidth);
		for (int i = 0; i < wallwidth; i++) {
			level[wallrow][wallcolumn + i] = "X";
		}
	}

	private void addverticalwall() {
		int wallheight = RANDOM.nextInt(height - 3);
		int wallrow = RANDOM.nextInt(height - 2 - wallheight);
		int wallcolumn = RANDOM.nextInt(width - 2) + 1;
		for (int i = 0; i < wallheight; i++) {
			level[wallrow + i][wallcolumn] = "X";
		}
	}

	public boolean ispassable() {
		return ispassable(false);
	}

	public boolean ispassable(boolean draw) {
		String[][] levelcopy = copy(level);
		outer: for (int row = 0; row < height; row++) {
			for (int column = 0; column < width; column++) {
				if (" ".equals(levelcopy[row][column])) {
					levelcopy[row][column] = "*";
					break outer;
				}
			}
		}
		while (spreadasterisks(levelcopy)) {
			if (draw) {
				for (int row = 0; row < height; row++) {
					for (int column = 0; column < width; column++) {
						System.out.print(level[row][column]);
					}

					System.out.println();
				}
			}
		}

		for (int row = 0; row < height; row++) {
			for (int column = 0; column < width; column++) {
				if (" ".equals(levelcopy[row][column])) {
					return false;
				}
			}
		}
		return true;
	}

	private boolean spreadasterisks(String[][] levelcopy) {
		boolean changed = false;
		for (int row = 0; row < height; row++) {
			for (int column = 0; column < width; column++) {
				if ("*".equals(levelcopy[row][column])) {
					if (" ".equals(levelcopy[row - 1][column])) {
						levelcopy[row - 1][column] = "*";
						changed = true;
					}
					if (" ".equals(levelcopy[row + 1][column])) {
						levelcopy[row + 1][column] = "*";
						changed = true;
					}
					if (" ".equals(levelcopy[row][column - 1])) {
						levelcopy[row][column - 1] = "*";
						changed = true;
					}
					if (" ".equals(levelcopy[row][column + 1])) {
						levelcopy[row][column + 1] = "*";
						changed = true;
					}
				}
			}
		}
		return changed;
	}

	private String[][] copy(String[][] level) {
		String[][] copy = new String[height][width];
		for (int row = 0; row < height; row++) {
			for (int column = 0; column < width; column++) {
				copy[row][column] = level[row][column];
			}
		}
		return copy;
	}

	public boolean isEmpty(Coordinates coordinates) {
		return " ".equals(level[coordinates.getRow()][coordinates.getColumn()]);
	}

	public Coordinates getfarthestcorner(Coordinates from) {
		String[][] levelcopy = copy(level);
		levelcopy[from.getRow()][from.getColumn()] = "*";

		int farthestrow = 0;
		int farthestcolumn = 0;
		while (spreadasteriskswithcheck(levelcopy)) {
			outer: for (int row = 0; row < height; row++) {
				for (int column = 0; column < width; column++) {
					if ("*".equals(levelcopy[row][column])) {
						farthestrow = row;
						farthestcolumn = column;
						break outer;
					}
				}
			}
		}
		return new Coordinates(farthestrow, farthestcolumn);
	}

	private boolean spreadasteriskswithcheck(String[][] levelcopy) {
		boolean[][] mask = new boolean[height][width];
		for (int row = 0; row < height; row++) {
			for (int column = 0; column < width; column++) {
				if ("*".equals(levelcopy[row][column])) {
					mask[row][column] = true;
				}
			}
		}
		boolean changed = false;
		for (int row = 0; row < height; row++) {
			for (int column = 0; column < width; column++) {
				if ("*".equals(levelcopy[row][column]) && mask[row][column]) {
					if (" ".equals(levelcopy[row - 1][column])) {
						levelcopy[row - 1][column] = "*";
						changed = true;
					}
					if (" ".equals(levelcopy[row + 1][column])) {
						levelcopy[row + 1][column] = "*";
						changed = true;
					}
					if (" ".equals(levelcopy[row][column - 1])) {
						levelcopy[row][column - 1] = "*";
						changed = true;
					}
					if (" ".equals(levelcopy[row][column + 1])) {
						levelcopy[row][column + 1] = "*";
						changed = true;
					}

				}
			}
		}
		return changed;
	}

	public Direction getshortestpath(Direction defaultdirection, Coordinates from, Coordinates to) {
		String[][] levelcopy = copy(level);
		levelcopy[to.getRow()][to.getColumn()] = "*";
		while (spreadasteriskswithcheck(levelcopy)) {

			if ("*".equals(levelcopy[from.getRow() - 1][from.getColumn()])) {
				return Direction.UP;
			}
			if ("*".equals(levelcopy[from.getRow() + 1][from.getColumn()])) {
				return Direction.DOWN;
			}
			if ("*".equals(levelcopy[from.getRow()][from.getColumn() - 1])) {
				return Direction.LEFT;
			}
			if ("*".equals(levelcopy[from.getRow()][from.getColumn() + 1])) {
				return Direction.RIGHT;
			}

		}
		return defaultdirection;
	}

	public String getcell(Coordinates coordinates) {
		return level[coordinates.getRow()][coordinates.getColumn()];
	}

	public Coordinates getrandomcoordinates() {
		Coordinates randomcoordinates;
		do {
			randomcoordinates = new Coordinates(RANDOM.nextInt(height), RANDOM.nextInt(width));
		} while (!isEmpty(randomcoordinates));
		return randomcoordinates;
	}

	public Coordinates getrandomcoordinatesatleastacertaindistancefromgivenpoint(Coordinates coordinates,
			int distance) {
		int counter = 0;
		Coordinates randomcoordinates;
		do {
			randomcoordinates = getrandomcoordinates();
		} while (counter++ < 1000 && randomcoordinates.distancefrom(coordinates) < distance);
		return randomcoordinates;
	}
	
	public boolean isAnyStrengthPowerupActive() {
		for (Powerup powerup : powerups) {
			if(powerup instanceof StrengthPowerup && powerup.isActive()) {
				return true;
			}
		}
		return false;
	}
	
	public void draw() {
		String[][] drawbuffer=copy(level);		
		drawbuffer[player.getCoordinates().getRow()][player.getCoordinates().getColumn()]=player.getMark();
		for(Enemy enemy:enemies) {
			drawbuffer[enemy.getCoordinates().getRow()][enemy.getCoordinates().getColumn()]=enemy.getMark();
		}
		for(Powerup powerup:powerups) {
			if(powerup.isPresentonlevel()) {
			drawbuffer[powerup.getCoordinates().getRow()][powerup.getCoordinates().getColumn()]=powerup.getMark();
			}
		}
		for (int row = 0; row < height; row++) {
			for (int column = 0; column < width; column++) {
				System.out.print(drawbuffer[row][column]);
			}
			System.out.println();
		}
			
		if (isAnyStrengthPowerupActive()) {
			System.out.println("Legalább egy power-up aktiv.");
		}
	}
}
