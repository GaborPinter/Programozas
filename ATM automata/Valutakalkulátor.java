import java.util.Arrays;
import java.util.Scanner;


/**
 * Írjunk egy olyan programot ami bekér a felhasználótól egy pozitív egész
 * számot majd kiszámolja hogy ez a pénzösszeg milyen címletekbe adható ki úgy
 * hogy a legkevesebb címletet használjuk fel.
 */

public class Valutakalkulátor {

	public static void main(String[] args) {

		Scanner scanner = new Scanner(System.in); 
		int penzösszeg; 
		do {
			System.out.print("Kérem adjon meg egy pénzösszeget: ");
			penzösszeg = scanner.nextInt();
			System.out.println("A felhasználó által megadott pénzösszeg: " + penzösszeg);
			if (penzösszeg < 0) {
				System.out.println("Negatív pénzösszeg nem adható meg!");
			}
			if (penzösszeg % 5 != 0) {
				System.out.println("A megadott pénzössszeg nem fizethető ki mert nem osztható 5-tel");
			}
		} while (penzösszeg < 0 || penzösszeg % 5 != 0);

		int[] valutak = { 20_000, 10_000, 5_000, 2_000, 1_000, 500, 200, 100, 50, 20, 10, 5 };
		int maradtösszeg = penzösszeg;
		int[] darabok = new int[valutak.length];

		for (int i = 0; i < valutak.length; i++) {
			darabok[i] = maradtösszeg / valutak[i];
			maradtösszeg = maradtösszeg % valutak[i];
			if (maradtösszeg >= 0) {
				System.out.println(darabok[i] + " darab " + valutak[i] + "Ft-osra van szükség");
			}
		}
		if (maradtösszeg == 0) {
			System.out.println(Arrays.toString(darabok));
		}
		
		scanner.close();

	}
}

