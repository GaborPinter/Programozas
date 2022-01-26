import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Scanner;

public class Egyszamjatek {

	public static void main(String[] args) throws IOException {

		// 2.feladat
		HashMap<String, int[]> adatok = new HashMap<String, int[]>();
		for (String sor : Files.readAllLines(Path.of("egyszamjatek1.txt"))) {
			String[] DB = sor.split(" ");
			int[] tippek = new int[DB.length - 1];
			for (int i = 1; i < DB.length; ++i) {
				tippek[i - 1] = Integer.parseInt(DB[i]);
			}
			adatok.put(DB[0], tippek);
		}

		/*
		 * for (Tippek adat : adatok) { System.out.println(adat); }
		 */

		// 3.feladat
		System.out.println(String.format("3. feladat: Játékosok száma: %s fő", adatok.size()));

		// 4.feladat
		System.out.print("4. feladat: Kérem a forduló sorszámát: ");
		Scanner input = new Scanner(System.in);
		int sorszám = input.nextInt() - 1;

		// 5.feladat
		int osszes = 0;
		for (int[] tippek : adatok.values()) {
			osszes = osszes + tippek[sorszám];
		}
		System.out.println("5. feladat: A megadott forduló " + "tippjeinek átlaga: "
				+ String.valueOf((double) osszes / adatok.size()).substring(0, 4));

		System.out.println(String.format("5. feladat: A megadott forduló tippjeinek átlaga: %.2f ",
				(double) osszes / adatok.size()));

	}
}
