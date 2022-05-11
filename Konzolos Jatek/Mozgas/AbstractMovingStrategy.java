package hu.ak_akademia.basicgame.move;

import hu.ak_akademia.basicgame.entity.MovingEntity;

public abstract class AbstractMovingStrategy implements MovingStrategy{
	
	protected final MovingEntity entity;

	public AbstractMovingStrategy(MovingEntity entity) {
		this.entity = entity;
	}
	
	
}
