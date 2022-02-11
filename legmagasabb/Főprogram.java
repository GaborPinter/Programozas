package legmagasabb;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class Főprogram {

	public static void main(String[] args) {

		// 1000elemű tömb létrehozása
		Épület[] épületek = new Épület[1000];

		// fájl beolvasása
		try {
			System.setProperty("file.encoding", "UTF-8");
			BufferedReader BR = new BufferedReader(new FileReader("legmagasabb.txt"));
			String sor = "";
			BR.readLine();
			int counter = 0;
			while (sor != null) {
				sor = BR.readLine();
				if ((sor != null) && (counter < 1000)) {
					String[] darab = sor.split(";");
					épületek[counter] = new Épület(darab[0], darab[1], darab[2], Integer.parseInt(darab[3]),
							Integer.parseInt(darab[4]), Integer.parseInt(darab[5]));
				}
				counter++;
			}
			BR.close();
		} catch (IOException e) {
			// nem iratunk ki semmit mert feltételezhetjük hogy nem dob kivételt
		}

		// Beolvasás ellenőrzése
		for (int i = 0; i < 1000; i++) {
			if (épületek[i] != null) {
				System.out.println(épületek[i].toString());
			}
		}

		// 3.Feladat
		int db = 0;
		while (épületek[db] != null) {
			db++;
		}
		System.out.println("3. feladat: Épületek száma: " + db + " db");

		// 4.Feladat
		int EmeletÖsszeg = 0, i = 0;
		while (épületek[i] != null) {
			EmeletÖsszeg = EmeletÖsszeg + épületek[i].getEmelet();
			i++;
		}
		System.out.println("4. feladat: Emeletek összege: " + EmeletÖsszeg);

		// 5.Feladat
		int max = épületek[0].getMagasság(), k = 0, j = 0, index = 0;
		while (épületek[k] != null) {
			if (max < épületek[k].getMagasság()) {
				max = épületek[k].getMagasság();
			}
			k++;
		}
		while (épületek[j] != null) {
			if (épületek[j].getMagasság() == max) {
				index = j;
			}
			j++;
		}
		System.out.println("5. feladat: A legmagasabb épület adatai: " + "\n\tNév:" + épületek[index].getNév()
				+ "\n\tVáros:" + épületek[index].getVáros() + "\n\tOrszág:" + épületek[index].getOrszág()
				+ "\n\tMagasság:" + épületek[index].getMagasság() + "\n\tEmeletek száma:" + épületek[index].getEmelet()
				+ "\n\tÉpítés éve:" + épületek[index].getÉpült());

		// 6.Feladat
		int z = 0;
		while (épületek[z] != null) {
			if (épületek[z].getOrszág().equals("Olaszország")) {
				System.out.println("6.feladat: Van olasz épület az adatok között!");
				break;
			}
			z++;
		}

		// 7.Feladat
		int p = 0, szam = 0;
		while (épületek[p] != null) {
			if (épületek[p].getMagasság() * 3.280839895 > 666) {
				szam++;
			}
			p++;
		}
		System.out.println("7.feladat: 666 lábnál magasabb épületek száma: " + szam);

		// 8.Feladat
		System.out.println("8. feladat: Ország statisztika:");
		List<String> országNevek = new ArrayList<String>();
		boolean vanOrszág = false;
		// országnevek legyûjtése
		int t = 0;
		while (épületek[t] != null) {
			for (String országok : országNevek) {
				if (épületek[t].getOrszág().equals(országok)) {
					vanOrszág = true;
				}
			}
			if (vanOrszág == false) {
				országNevek.add(épületek[t].getOrszág());
			}
			vanOrszág = false;
			t++;

		}
		// statisztika
		int épületekSzámaOrszágonként = 0;
		for (String országok : országNevek) {
			for (int x = 0; x < db; x++) {
				if (épületek[x].getOrszág().equals(országok)) {
					épületekSzámaOrszágonként++;
				}
			}
			System.out.println("        " + országok + " - " + épületekSzámaOrszágonként + " db");
			épületekSzámaOrszágonként = 0;
		}

		// 9.Feladat
		System.out.println("9. feladat: nemet.txt");
		// Német városok legyûjtése
		Set<String> nemetVarosok = new LinkedHashSet<>();
		int q = 0;
		while (épületek[q] != null) {
			if (épületek[q].getOrszág().startsWith("N")) {
				nemetVarosok.add(épületek[q].getVáros());
			}
			q++;
		}
		// Fájlba írás
		String fájlNév = "nemet.txt";
		// törlés és új fájl létrehozása
		try {
			File fájl = new File(fájlNév);
			fájl.delete();
			fájl.createNewFile();
		} catch (IOException e) {
		}
		// Adat beírása a fájlba
		try {
			File fájl = new File(fájlNév);
			PrintWriter pw = new PrintWriter(fájl);
			for (String városok : nemetVarosok) {
				pw.println(városok);
			}
			pw.close();
		} catch (IOException e) {

		}

	}
}
