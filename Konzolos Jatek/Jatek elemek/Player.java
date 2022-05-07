import Direction;
import Level;
import CatchEnemyMovingStrategy;
import EscapeMovingStrategy;
import PickUpPowerupMovingStrategy;

public class Player extends MovingEntity {
	
	private int iterationnumber;

	public Player(String mark, Direction direction,
			Level level) {
		super(mark,level.getrandomcoordinates(), direction, level);
		currentMovingStrategy=new EscapeMovingStrategy(this);
	}

	@Override
	public boolean update() {
		if (level.isAnyStrengthPowerupActive()) {
			currentMovingStrategy=new CatchEnemyMovingStrategy(this);
		} else {
			if (isAnyPowerupOnLevel()) {
				currentMovingStrategy=new PickUpPowerupMovingStrategy(this);
			} else {
				if (iterationnumber++ % 100 == 0) {
					setEscapecoordinates(level.getfarthestcorner(coordinates));
				}
				currentMovingStrategy=new EscapeMovingStrategy(this);
			}
		}
		direction=currentMovingStrategy.calculateNewDirection();
		return super.update();
	}



	private boolean isAnyPowerupOnLevel() {
		for (Powerup powerup : level.getPowerups()) {
			if(powerup.isPresentonlevel()) {
				return true;
			}
		}
		return false;
	}
}
