package hu.ak_akademia.basicgame.entity;

import hu.ak_akademia.basicgame.Level;

public class SlowdownPowerup extends Powerup{

	public SlowdownPowerup( Level level) {
		super("@", level);
	}

	@Override
	public void activate() {
		super.activate();
		for (Enemy enemy : level.getEnemies()) {
			enemy.setSlowed(true);
		}
	}

	@Override
	public void deactivate() {
		super.deactivate();
		for (Enemy enemy : level.getEnemies()) {
			enemy.setSlowed(false);
		}
	}
	
	

}
