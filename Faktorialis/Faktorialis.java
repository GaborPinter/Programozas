import java.util.InputMismatchException;
import java.util.Scanner;

public class Faktoriális {

	public static void main(String[] args) {

		/*
		 * Egy n természetes szám faktoriálisát n! jelöli és az 1*2*3*...*n szorzatot
		 * értjük alatta. Készítsen programot n! kiszámítására.
		 */

		System.out.print("Adjon meg egy számot amelynek a faktoriális értékére kiváncsi: ");
		int megadottSzam = 0;
		try (Scanner input = new Scanner(System.in)) {
			megadottSzam = input.nextInt();
		} catch (InputMismatchException e) {
			System.out.println("Kérem egész számot adjon meg!");
			return;
		}
		if (megadottSzam > 0) {
			for (int i = 1; i <= megadottSzam; i++) {
				if (i == megadottSzam) {
					System.out.println(i);
				} else {
					System.out.print(i + "*");
				}
			}
		}
		long osszeg = Faktor(megadottSzam);
		if (megadottSzam != 0) {
			System.out.println("Az ertek: " + osszeg);
		} else {
			System.out.println("Az ertek: " + osszeg);
		}
	}

	public static long Faktor(int f) {
		long ertek = 1;
		if (f < 0) {
			for (int i = f; i < 0; i++) {
				ertek = ertek * i;
			}
			return ertek;
		}
		if (f == 0 || f == 1) {
			return 1;
		} else {
			for (int i = f; i > 0; i--) {
				ertek = ertek * i;
			}
			return ertek;
		}
	}

}
