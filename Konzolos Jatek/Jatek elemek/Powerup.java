package hu.ak_akademia.basicgame.entity;

import hu.ak_akademia.basicgame.Coordinates;
import hu.ak_akademia.basicgame.Level;

public abstract class Powerup extends Basicentity {
	private boolean presentonlevel=true;
	private int presencecounter;
	private boolean active;
	private int activecounter;

	public Powerup(String mark, Level level) {
		super(mark,level.getrandomcoordinates(), level);
	}

	public int incrementpresencecounter() {
		return ++presencecounter;
	}

	public void resetPresenceCounter() {
		presencecounter = 0;
	}

	public int incrementactivecounter() {
		return ++activecounter;
	}

	public void resetActiveCounter() {
		activecounter = 0;
	}

	public void showOnLevel() {
		presentonlevel = true;
	}

	public void hideOnLevel() {
		presentonlevel = false;
	}

	public boolean isPresentonlevel() {
		return presentonlevel;
	}

	public void activate() {
		active = true;
	}

	public void deactivate() {
		active = false;
	}

	public boolean isActive() {
		return active;
	}

	public boolean update() {
		if (active) {
			incrementactivecounter();
		} else {
			incrementpresencecounter();
		}
		if (presencecounter >= 60) {
			if (presentonlevel) {
				setCoordinates(level.getrandomcoordinates());
			}
			hideOnLevel();
			resetPresenceCounter();
			;
		}
		if (activecounter >= 60) {
			deactivate();
			resetActiveCounter();
			setCoordinates(level.getrandomcoordinates());
			return true;

		}
		return false;

	}

}
