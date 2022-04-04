
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
 * Írjunk egy olyan programot ami a felhasználótól bekér egy email címet és megvizsgálja, hogy a megadott 
 * email cím megfelelő-e vagy sem, az email címek szabályai alapján.
 */

public class EmailAddressSolution {

	public static void main(String[] args) {
		try (Scanner scanner = new Scanner(System.in)) {
			System.out.println("Kérem adjon meg egy e-mail címet!");
			String Email = scanner.nextLine();
			String regex = "^[\\w+-]+(?:\\.[\\w+-]+)*@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)+$";
			Pattern pattern = Pattern.compile(regex);
			Matcher matcher = pattern.matcher(Email);
			
			int counter = 0;
			while (matcher.find()) {
				counter++;
			}
			if (counter > 0) {
				System.out.println("A megadott e-mail cím helyes.");
			} else {
				System.out.println("A megadott e-mail cím helytelen.");
			}
		}
	}

}
