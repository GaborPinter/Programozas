
import java.util.Random;

import hu.ak_akademia.basicgame.entity.Enemy;
import hu.ak_akademia.basicgame.entity.Player;
import hu.ak_akademia.basicgame.entity.Powerup;
import hu.ak_akademia.basicgame.entity.SlowdownPowerup;
import hu.ak_akademia.basicgame.entity.StrengthPowerup;

public class Main {

	static final int GAME_LOOP_NUMBER = 1_000; // final miatt nem tudjuk megvaltoztatni az erteket magaban a scopeban
	static final int HEIGHT = 40;
	static final int WIDTH = 40;
	static final int ENEMIES = 3;
	static final int POWERUPS = 4;
	static final Random RANDOM = new Random(1L);

	public static void main(String[] args) throws InterruptedException {

		Level level = new Level(RANDOM, HEIGHT, WIDTH);


		Player player = new Player("O",
				Direction.RIGHT, level);
		level.setPlayer(player);

		Enemy[] enemies=new Enemy[ENEMIES];
		for(int i=0;i<ENEMIES;i++) {
			enemies[i] = new Enemy("-", level
					.getrandomcoordinatesatleastacertaindistancefromgivenpoint(player.getCoordinates(), 10),
					Direction.LEFT, level);
		}
		level.setEnemies(enemies );
		
		Powerup[] powerups=new Powerup[POWERUPS];
		for(int i=0;i<POWERUPS/2;i++) {
			powerups[i] = new StrengthPowerup(level);
		}
		for(int i=POWERUPS/2;i<POWERUPS;i++) {
			powerups[i] = new SlowdownPowerup(level);
		}
		level.setPowerups(powerups);

		
		Gameresult gameresult = Gameresult.TIE;

		gameLoop:
		for (int iterationnumber = 1; iterationnumber <= GAME_LOOP_NUMBER; iterationnumber++) {
			
			player.update();
			for (Enemy enemy :enemies) {
				enemy.update();
			}
			
			for (Powerup powerup : powerups) {
				if (powerup.update()) {
					player.setEscapecoordinates(level.getfarthestcorner(player.getCoordinates()));
				}
			}
			
			for (Powerup powerup : powerups) {
			if (powerup.isPresentonlevel() && player.getCoordinates().issameas(powerup.getCoordinates())) {
				powerup.activate();
				powerup.hideOnLevel();
				powerup.resetPresenceCounter();
				for (Enemy enemy : enemies) {
					enemy.setEscapecoordinates(level.getfarthestcorner(enemy.getCoordinates()));
				}
				}
			}

			level.draw();
			addsomedelay(500L, iterationnumber);
			
			for(Enemy enemy:enemies) {
			if (player.getCoordinates().issameas(enemy.getCoordinates())) {
				if (level.isAnyStrengthPowerupActive()) {
					gameresult = Gameresult.WIN;
				} else {
					gameresult = Gameresult.LOSE;
				}
				break gameLoop;
			}
			}
		}
		switch (gameresult) {
		case WIN:
			System.out.println("Gratulalok, gyoztel");
			break;
		case LOSE:
			System.out.println("Sajnalom, vesztettel");
			break;
		case TIE:
			System.out.println("A jatek dontetlen");
			break;
		}
	}

	static void addsomedelay(long timeout, int iterationnumber) throws InterruptedException {
		System.out.println("----------" + iterationnumber + "----------");
		Thread.sleep(timeout);
	}
}
