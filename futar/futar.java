package futar;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.Scanner;

public class futar {

	public static void main(String[] args) throws IOException {
		// 1.feladat
		List<String> sorok = Files.readAllLines(Path.of("tavok.txt"), StandardCharsets.UTF_8);
		ArrayList<Távok> adatok = new ArrayList<Távok>();
		for (int i = 0; i < sorok.size(); i++) {
			String[] DB = sorok.get(i).split(" ");
			adatok.add(new Távok(Integer.parseInt(DB[0]), Integer.parseInt(DB[1]), Integer.parseInt(DB[2])));
		}

		// ellenőrzés
		/*
		 * for (Távok adat : adatok) { System.out.println(adat); }
		 */

		// 2.feladat
		int napmin = adatok.get(0).nap;
		int napmax = 0;
		for (Távok adat : adatok) {
			if (adat.nap < napmin) {
				napmin = adat.nap;
			}
			if (adat.nap > napmax) {
				napmax = adat.nap;
			}
		}
		for (Távok adat : adatok) {
			if (adat.nap == napmin && adat.fuvarszam == 1) {
				System.out.println("2. feladat: A hét legelső útja kilóméterben: " + adat.km + " km");
			}
		}

		// 3.feladat
		int maxfuvarszam = 0;
		for (Távok adat : adatok) {
			if (adat.nap == napmax) {
				if (adat.fuvarszam > maxfuvarszam) {
					maxfuvarszam = adat.fuvarszam;
				}
			}
		}
		for (Távok adat : adatok) {
			if (adat.nap == napmax && maxfuvarszam == adat.fuvarszam) {
				System.out.println("3. feladat: A hét utolsó útja kilóméterben: " + adat.km + " km");
			}
		}

		// 4.feladat
		int[] hetnapja = { 1, 2, 3, 4, 5, 6, 7 };
		for (int i = 0; i < hetnapja.length; i++) {
			for (Távok adat : adatok) {
				if (hetnapja[i] == adat.nap) {
					hetnapja[i] = 0;
				}
			}
		}
		System.out.print("4. feladat: Napok amelyeken nem dolgozott a futár: ");
		for (int i : hetnapja) {
			if (i != 0) {
				System.out.print(i + ". ");
			}
		}

		// 5.feladat
		HashMap<Integer, Integer> fuvarok = new HashMap<Integer, Integer>();
		for (Távok adat : adatok) {
			fuvarok.put(adat.nap, fuvarok.getOrDefault(adat.nap, 0) + 1);
		}
		int max = 0;
		for (Entry<Integer, Integer> fuvar : fuvarok.entrySet()) {
			if (max < fuvar.getValue()) {
				max = fuvar.getValue();
			}
		}
		for (Entry<Integer, Integer> fuvar : fuvarok.entrySet()) {
			if (max == fuvar.getValue()) {
				System.out.println("\n5. feladat: A nap amelyiken a legtöbb fuvar volt: " + fuvar.getKey() + ". nap");
			}
		}

		// 6.feladat
		System.out.println("6. feladat: ");
		int[] napok = { 1, 2, 3, 4, 5, 6, 7 };
		for (int i = 0; i < napok.length; i++) {
			int szam = 0;
			for (Távok adat : adatok) {
				if (adat.nap == napok[i]) {
					szam = szam + adat.km;
				}
			}
			System.out.println(napok[i] + ". nap: " + szam + " km");
		}

		// 7.feladat
		System.out.println("7. feladat:");
		Scanner input = new Scanner(System.in);
		int megadottTav = 0;
		do {
			System.out.print("Adjon meg egy távolságot 1 és 30 között: ");
			megadottTav = input.nextInt();
			if (megadottTav >= 1 && megadottTav <= 30) {
				System.out.println(penz(megadottTav) + " Ft a díjazás");
			}
		} while (megadottTav < 1 || megadottTav > 30);

		// 8.feladat
		String text = "dijazas.txt";
		try {
			File fájl = new File(text);
			fájl.delete();
			fájl.createNewFile();
		} catch (IOException e) {
		}
		ArrayList<Integer> fuvarszamok = new ArrayList<Integer>();
		for (Távok adat : adatok) {
			if (!fuvarszamok.contains(adat.fuvarszam)) {
				fuvarszamok.add(adat.fuvarszam);
			}
		}
		fuvarszamok.sort(Comparator.naturalOrder());
		try {
			File fáj = new File(text);
			PrintWriter PW = new PrintWriter(fáj);
			for (int i = 0; i < napok.length; i++) {
				for (int j = 0; j < fuvarszamok.size(); j++) {
					for (Távok adat : adatok) {
						if (adat.nap == napok[i] && adat.fuvarszam == fuvarszamok.get(j)) {
							PW.println(adat.nap + ". nap " + adat.fuvarszam + ". út: " + penz(adat.km) + " Ft");
						}
					}
				}
			}
			PW.close();
		} catch (IOException e) {
		}

		// 9.feladat
		int osszeg = 0;
		for (Távok adat : adatok) {
			osszeg = osszeg + penz(adat.km);
		}
		System.out.println("9. feladat: " + osszeg + " Ft jár a heti munkáért cserébe");

	}

	public static class Távok {
		private int nap;
		private int fuvarszam;
		private int km;

		public Távok(int nap, int fuvarszam, int km) {
			this.nap = nap;
			this.fuvarszam = fuvarszam;
			this.km = km;
		}

		@Override
		public String toString() {
			return "Távok [nap=" + nap + ", fuvarszam=" + fuvarszam + ", km=" + km + "]";
		}
	}

	public static int penz(int ertek) {
		int szam = 0;
		if (ertek >= 1 && ertek <= 2) {
			szam = 500;
		} else if (ertek >= 3 && ertek <= 5) {
			szam = 700;
		} else if (ertek >= 6 && ertek <= 10) {
			szam = 900;
		} else if (ertek >= 11 && ertek <= 20) {
			szam = 1400;
		} else if (ertek >= 21 && ertek <= 30) {
			szam = 2000;
		}
		return szam;
	}
}
