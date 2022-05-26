
import basicgame.Coordinates;
import basicgame.Direction;
import basicgame.Level;
import move.MovingStrategy;

public abstract class MovingEntity extends Basicentity {
	protected Coordinates escapecoordinates;
	protected Direction direction;
	protected MovingStrategy currentMovingStrategy;

	public MovingEntity(String mark, Coordinates coordinates, Direction direction,
			Level level) { // konstruktor
		super(mark, coordinates, level);
		this.escapecoordinates = level.getfarthestcorner(coordinates);
		this.direction = direction;
	}

	public Coordinates getEscapecoordinates() {
		return escapecoordinates;
	}

	public void setEscapecoordinates(Coordinates escapecoordinates) {
		this.escapecoordinates = escapecoordinates;
	}

	public Direction getDirection() {
		return direction;
	}

	public void setDirection(Direction direction) {
		this.direction = direction;
	}

	public boolean update() {
		Coordinates newcoordinates = new Coordinates(getCoordinates());
		switch (direction) {
		case UP:
			if (getLevel().isEmpty(new Coordinates(getCoordinates().getRow() - 1, getCoordinates().getColumn()))) {
				newcoordinates.setRow(getCoordinates().getRow() - 1);
			}
			break;
		case DOWN:
			if (getLevel().isEmpty(new Coordinates(getCoordinates().getRow() + 1, getCoordinates().getColumn()))) {
				newcoordinates.setRow(getCoordinates().getRow() + 1);
			}
			break;
		case LEFT:
			if (getLevel().isEmpty(new Coordinates(getCoordinates().getRow(), getCoordinates().getColumn() - 1))) {
				newcoordinates.setColumn(getCoordinates().getColumn() - 1);
			}
			break;
		case RIGHT:
			if (getLevel().isEmpty(new Coordinates(getCoordinates().getRow(), getCoordinates().getColumn() + 1))) {
				newcoordinates.setColumn(getCoordinates().getColumn() + 1);
			}
			break;
		}
		setCoordinates(newcoordinates);
		return false;
	}

}
