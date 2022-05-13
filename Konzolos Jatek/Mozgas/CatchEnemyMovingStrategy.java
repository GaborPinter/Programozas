package hu.ak_akademia.basicgame.move;

import hu.ak_akademia.basicgame.Coordinates;
import hu.ak_akademia.basicgame.Direction;
import hu.ak_akademia.basicgame.entity.Enemy;
import hu.ak_akademia.basicgame.entity.MovingEntity;

public class CatchEnemyMovingStrategy extends AbstractMovingStrategy{

	public CatchEnemyMovingStrategy(MovingEntity entity) {
		super(entity);
	}

	@Override
	public Direction calculateNewDirection() {
		Enemy closestEnemy = getClosestEnemy();
		if(closestEnemy==null) {
			return entity.getDirection();
		}
		Coordinates closestEnemyCoordinates = closestEnemy.getCoordinates();
		return entity.getLevel().getshortestpath(entity.getDirection(), entity.getCoordinates(),
				closestEnemyCoordinates);
	}
	
	private Enemy getClosestEnemy() {
		int shortestDistance=Integer.MAX_VALUE;
		Enemy closestEnemy=null;
		for (Enemy enemy : entity.getLevel().getEnemies()) {
			int distanceFromEnemy = enemy.getCoordinates().distancefrom(entity.getCoordinates());
			if(distanceFromEnemy<shortestDistance) {
				shortestDistance=distanceFromEnemy;
				closestEnemy=enemy;
			}
			
		}
		return closestEnemy;
	}

}
