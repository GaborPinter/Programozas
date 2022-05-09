
import Coordinates;
import Direction;
import Basicentity;
import MovingEntity;
import Powerup;

public class PickUpPowerupMovingStrategy extends AbstractMovingStrategy{

	public PickUpPowerupMovingStrategy(MovingEntity entity) {
		super(entity);
	}

	@Override
	public Direction calculateNewDirection() {
		Basicentity closestPresentPowerup = getClosestPresentPowerup();
		if(closestPresentPowerup==null) {
			return entity.getDirection();
		}
		Coordinates closestPowerupCoordinates = closestPresentPowerup.getCoordinates();
		return entity.getLevel().getshortestpath(entity.getDirection(), entity.getCoordinates(),closestPowerupCoordinates);
	}
	
	private Basicentity getClosestPresentPowerup() {
		int shortestDistance=Integer.MAX_VALUE;
		Powerup closestPowerup=null;
		for (Powerup powerup : entity.getLevel().getPowerups()) {
			if(powerup.isPresentonlevel()) {
			int distanceFromPowerup = powerup.getCoordinates().distancefrom(entity.getCoordinates());
			if(distanceFromPowerup<shortestDistance) {
				shortestDistance=distanceFromPowerup;
				closestPowerup=powerup;
			}
			}
		}
		return closestPowerup;
	}

}
