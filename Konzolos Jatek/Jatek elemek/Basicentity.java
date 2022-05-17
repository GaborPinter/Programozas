

import basicgame.Coordinates;
import basicgame.Level;

public abstract class Basicentity implements Entity {
	protected String mark;
	protected Coordinates coordinates;
	protected Level level;

	public Basicentity(String mark, Coordinates coordinates, Level level) { 
		this.mark = mark;
		this.coordinates = coordinates;
		this.level = level;
	}

	public String getMark() { 
		return mark;
	}

	public void setMark(String mark) {
		this.mark = mark;
	}

	public Coordinates getCoordinates() {
		return coordinates;
	}

	public void setCoordinates(Coordinates coordinates) {
		this.coordinates = coordinates;
	}

	public Level getLevel() {
		return level;
	}

	public boolean update() {
		return false;
	}
	
}
