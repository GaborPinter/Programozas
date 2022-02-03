import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Random;

public class JátékPanel extends JPanel implements ActionListener{

	static final int szélesség = 1300;
	static final int magasság = 750;
	static final int karakterMéret = 50;
	static final int képernyő = (szélesség*magasság)/(karakterMéret*karakterMéret);
	static final int késleltetés = 175;
	final int x[] = new int[képernyő];
	final int y[] = new int[képernyő];
	int testrészek = 6;
	int megevettAlmák;
	int almaX;
	int almaY;
	char irány = 'D';
	boolean futás = false;
	Timer időzítő;
	Random rand;
	
	JátékPanel(){
		rand = new Random();
		this.setPreferredSize(new Dimension(szélesség,magasság));
		this.setBackground(Color.gray);
		this.setFocusable(true);
		this.addKeyListener(new MyKeyAdapter());
		startGame();
	}
	public void startGame() {
		newApple();
		futás = true;
		időzítő = new Timer(késleltetés,this);
		időzítő.start();
	}
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		draw(g);
	}
	public void draw(Graphics g) {
		
		if(futás) {
			/*
			for(int i=0;i<SCREEN_HEIGHT/UNIT_SIZE;i++) {
				g.drawLine(i*UNIT_SIZE, 0, i*UNIT_SIZE, SCREEN_HEIGHT);
				g.drawLine(0, i*UNIT_SIZE, SCREEN_WIDTH, i*UNIT_SIZE);
			}
			*/
			g.setColor(Color.black);
			g.fillOval(almaX, almaY, karakterMéret, karakterMéret);
		
			for(int i = 0; i< testrészek;i++) {
				if(i == 0) {
					g.setColor(Color.YELLOW);
					g.fillRect(x[i], y[i], karakterMéret, karakterMéret);
				}
				else {
					g.setColor(new Color(45,180,0));
					//g.setColor(new Color(random.nextInt(255),random.nextInt(255),random.nextInt(255)));
					g.fillRect(x[i], y[i], karakterMéret, karakterMéret);
				}			
			}
			g.setColor(Color.black);
			g.setFont( new Font("Ink Free",Font.BOLD, 40));
			FontMetrics metrics = getFontMetrics(g.getFont());
			g.drawString("Score: "+megevettAlmák, (szélesség - metrics.stringWidth("Score: "+megevettAlmák))/2, g.getFont().getSize());
		}
		else {
			gameOver(g);
		}
		
	}
	public void newApple(){
		almaX = rand.nextInt((int)(szélesség/karakterMéret))*karakterMéret;
		almaY = rand.nextInt((int)(magasság/karakterMéret))*karakterMéret;
	}
	public void move(){
		for(int i = testrészek;i>0;i--) {
			x[i] = x[i-1];
			y[i] = y[i-1];
		}
		
		switch(irány) {
		case 'U':
			y[0] = y[0] - karakterMéret;
			break;
		case 'D':
			y[0] = y[0] + karakterMéret;
			break;
		case 'L':
			x[0] = x[0] - karakterMéret;
			break;
		case 'R':
			x[0] = x[0] + karakterMéret;
			break;
		}
		
	}
	public void checkApple() {
		if((x[0] == almaX) && (y[0] == almaY)) {
			testrészek++;
			megevettAlmák++;
			newApple();
		}
	}
	public void checkCollisions() {
		//checks if head collides with body
		for(int i = testrészek;i>0;i--) {
			if((x[0] == x[i])&& (y[0] == y[i])) {
				futás = false;
			}
		}
		//check if head touches left border
		if(x[0] < 0) {
			futás = false;
		}
		//check if head touches right border
		if(x[0] > szélesség) {
			futás = false;
		}
		//check if head touches top border
		if(y[0] < 0) {
			futás = false;
		}
		//check if head touches bottom border
		if(y[0] > magasság) {
			futás = false;
		}
		
		if(!futás) {
			időzítő.stop();
		}
	}
	public void gameOver(Graphics g) {
		//Score
		g.setColor(Color.black);
		g.setFont( new Font("Ink Free",Font.BOLD, 40));
		FontMetrics metrics1 = getFontMetrics(g.getFont());
		g.drawString("Score: "+megevettAlmák, (szélesség - metrics1.stringWidth("Score: "+megevettAlmák))/2, g.getFont().getSize());
		//Game Over text
		g.setColor(Color.black);
		g.setFont( new Font("Ink Free",Font.BOLD, 75));
		FontMetrics metrics2 = getFontMetrics(g.getFont());
		g.drawString("Game Over", (szélesség - metrics2.stringWidth("Game Over"))/2, magasság/2);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(futás) {
			move();
			checkApple();
			checkCollisions();
		}
		repaint();
	}
	
	public class MyKeyAdapter extends KeyAdapter{
		@Override
		public void keyPressed(KeyEvent e) {
			switch(e.getKeyCode()) {
			case KeyEvent.VK_LEFT:
				if(irány != 'R') {
					irány = 'L';
				}
				break;
			case KeyEvent.VK_RIGHT:
				if(irány != 'L') {
					irány = 'R';
				}
				break;
			case KeyEvent.VK_UP:
				if(irány != 'D') {
					irány = 'U';
				}
				break;
			case KeyEvent.VK_DOWN:
				if(irány != 'U') {
					irány = 'D';
				}
				break;
			}
		}
	}
}