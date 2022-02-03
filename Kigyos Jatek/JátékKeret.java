import javax.swing.JFrame;

public class JátékKeret extends JFrame{

	JátékKeret(){
			
		this.add(new JátékPanel());
		this.setTitle("Kígyó");
		this.setResizable(false);
		this.pack();
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		
	}
}