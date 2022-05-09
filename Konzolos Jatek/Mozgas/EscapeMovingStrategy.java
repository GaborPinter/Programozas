
import Direction;
import Level;
import MovingEntity;

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
