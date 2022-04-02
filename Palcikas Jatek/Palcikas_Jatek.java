import java.util.Random;
import java.util.Scanner;

public class Palcikak_jatek {

	public static void main(String[] args) {

		/*
		 * Pálcikás játék
		 
		 Két játékos játék, egy ember mérkőzik meg a géppel. 
		 Szabályok: 
		 - Kiindulásként van 13 pálcikából álló halmaz az asztalon 
		 - Minden lépésben az aktuális játékos elvehet 1,2 vagy 3 pálcikát 
		 – Nem vehetsz el több pálcikát mint amennyi van még az asztalon 
		 - Nem passzolhat egyik játékos sem 
		 - Az emberi játékos kezd 
		 - Az veszít akinek az utolsó pálcikát kell felvennie az asztalról
		 */

		int palcikak = 13;

		Random rand = new Random();
		Scanner scan = new Scanner(System.in);
		boolean jatekosJon = true;
		int megadottSzam;
		int elvett;

		while (palcikak > 1) {
			if (jatekosJon) {
				do {
					System.out.print("Adjon meg egy szamot ami 1, 2 vagy 3: ");
					megadottSzam = scan.nextInt();
				} while (megadottSzam < 1 || megadottSzam > 3 || megadottSzam >= palcikak);
				palcikak = palcikak - megadottSzam;
				System.out.println("A maradt palcikak: " + palcikak);
				jatekosJon = false;
			} else {
				do {
					elvett = rand.nextInt(4);
				} while (elvett < 1 || elvett >= palcikak);
				palcikak = palcikak - elvett;
				System.out.println("A gep a " + elvett + " szamot valasztotta\nA maradt palcikak: " + palcikak);
				jatekosJon = true;
			}
		}
		if (palcikak == 1) {
			if (jatekosJon) {
				System.out.println("Vesztettel");
			} else {
				System.out.println("Nyertel");
			}
		}
	}
}
