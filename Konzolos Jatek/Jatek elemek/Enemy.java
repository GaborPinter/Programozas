

import basicgame.Coordinates;
import basicgame.Direction;
import basicgame.Level;
import basicgame.move.CatchPlayerMovingStrategy;
import basicgame.move.EscapeMovingStrategy;

public class Enemy extends MovingEntity{
	
	private int iterationnumber;
	private boolean slowed;

	public Enemy(String mark, Coordinates coordinates, Direction direction,
			Level level) {
		super(mark, coordinates, direction, level);
		currentMovingStrategy=new CatchPlayerMovingStrategy(this);
	}

	public void setSlowed(boolean slowed) {
		this.slowed = slowed;
	}

	
	public boolean isSlowed() {
		return slowed;
	}

	@Override
	public boolean update() {
		if(iterationnumber++%(slowed?4:2)==0) {
		if (level.isAnyStrengthPowerupActive()) {
			if (iterationnumber % 100 == 0) {
			escapecoordinates=level.getfarthestcorner(coordinates);
			}
			currentMovingStrategy=new EscapeMovingStrategy(this);
		} else {
			currentMovingStrategy=new CatchPlayerMovingStrategy(this);
		}
		direction=currentMovingStrategy.calculateNewDirection();
		return super.update();
	}
		
		return false;
	}
	
	
	
}
