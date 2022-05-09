package hu.ak_akademia.basicgame.move;

import hu.ak_akademia.basicgame.Direction;
import hu.ak_akademia.basicgame.Level;
import hu.ak_akademia.basicgame.entity.MovingEntity;

public class EscapeMovingStrategy extends AbstractMovingStrategy{

	public EscapeMovingStrategy(MovingEntity entity) {
		super(entity);
	}

	@Override
	public Direction calculateNewDirection() {
		return entity.getLevel().getshortestpath(entity.getDirection(), entity.getCoordinates(),
				entity.getEscapecoordinates());
	}

}
