
import MovingEntity;

public abstract class AbstractMovingStrategy implements MovingStrategy{
	
	protected final MovingEntity entity;

	public AbstractMovingStrategy(MovingEntity entity) {
		this.entity = entity;
	}
	
	
}
