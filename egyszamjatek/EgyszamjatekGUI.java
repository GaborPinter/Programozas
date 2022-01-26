import java.awt.Color;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class EgyszamjatekGUI {

	public static void main(String[] args) throws IOException {

		HashMap<String, int[]> adatok = new HashMap<String, int[]>();
		for (String sor : Files.readAllLines(Path.of("egyszamjatek2.txt"))) {
			String[] DB = sor.split(" ");
			int[] tippek = new int[DB.length - 1];
			for (int i = 1; i < DB.length; ++i) {
				tippek[i - 1] = Integer.parseInt(DB[i]);
			}
			adatok.put(DB[0], tippek);
		}

		JFrame graf = new JFrame("Egyszamjatek GUI");

		JTextField nevInputField = newInputField("Játékos neve:", 20, 40, 250, graf);
		JTextField tippInputField = newInputField("Játékos tippjei:", 20, 80, 400, graf);
		JButton gomb = newButton("Játékos hozzáadása", 250, 220, 250, graf);
		JLabel tippDbLabel = newLabel("0 db", 540, 80, 50, graf);

		tippInputField.addCaretListener(e -> {
			String tipptext = tippInputField.getText();

			tippDbLabel.setText(szavakSzáma(tipptext) + " db");
		});

		gomb.addActionListener(e -> {
			String ujNev = nevInputField.getText();
			if (adatok.containsKey(ujNev)) {
				JOptionPane.showMessageDialog(graf, "Van már ilyen nevű játékos!");
			} else {
				String tippBemenet = tippInputField.getText();
				int bemenetiTippSzam = szavakSzáma(tippBemenet);
				int elvartTippSzam = adatok.values().iterator().next().length;

				if (elvartTippSzam != bemenetiTippSzam) {
					JOptionPane.showMessageDialog(graf, "A tippek száma nem megfelelő");
				} else {
					try {
						Files.writeString(Path.of("egyszamjatek2.txt"),
								nevInputField.getText() + " " + tippBemenet + "\n", StandardOpenOption.APPEND);
					} catch (IOException e1) {
					}

					tippInputField.setText(null);
					nevInputField.setText(null);
					JOptionPane.showMessageDialog(graf, "Az állomány bővítése sikeres volt!");
				}
			}
		});

		graf.setBounds(0, 0, 800, 300);
		graf.setLayout(null);
		graf.setLocationRelativeTo(null);
		graf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		graf.setVisible(true);

	}

	public static int szavakSzáma(String szo) {
		return szo.length() == 0 ? 0 : szo.split(" ").length;
	}

	public static JTextField newInputField(String labelText, int x, int y, int textFieldWidth, JFrame graf) {
		newLabel(labelText, x, y, 100, graf);
		JTextField textField = new JTextField();
		textField.setBounds(x + 100, y, textFieldWidth, 30);
		graf.add(textField);
		return textField;
	}

	public static JLabel newLabel(String labelText, int x, int y, int width, JFrame graf) {
		JLabel label = new JLabel(labelText);
		label.setBounds(x, y, width, 30);
		graf.add(label);
		return label;
	}

	public static JButton newButton(String text, int x, int y, int width, JFrame graf) {
		JButton button = new JButton(text);
		button.setBounds(x, y, width, 30);
		button.setBackground(Color.LIGHT_GRAY);
		graf.add(button);
		return button;
	}
}
