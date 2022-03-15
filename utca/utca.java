package utca;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class utca {

	public static void main(String[] args) throws IOException {

		// 1.feladat
		List<String> sorok = Files.readAllLines(Path.of("kerites.txt"), StandardCharsets.UTF_8);
		ArrayList<KertTulajdonságok> adatok = new ArrayList<KertTulajdonságok>();

		int paratlanHazszam = 1;
		int parosHazszam = 2;

		for (String sor : sorok) {
			String[] DB = sor.split(" ");
			boolean paros = Integer.parseInt(DB[0]) == 0;

			if (paros) {
				adatok.add(new KertTulajdonságok(Integer.parseInt(DB[0]), Integer.parseInt(DB[1]), DB[2].charAt(0),
						parosHazszam));
				parosHazszam = parosHazszam + 2;
			} else {
				adatok.add(new KertTulajdonságok(Integer.parseInt(DB[0]), Integer.parseInt(DB[1]), DB[2].charAt(0),
						paratlanHazszam));
				paratlanHazszam = paratlanHazszam + 2;
			}
		}

		// ellenőrzés
		/*
		 * for (KertTulajdonságok adat : adatok) { System.out.println(adat); }
		 */

		// 2.feladat
		System.out.println("2. feladat\nAz eladott telkek száma: " + adatok.size());

		// 3.feladat
		int oldal = -1000;
		int hazSzam = 0;
		for (int i = 0; i < adatok.size(); i++) {
			if (i == adatok.size() - 1) {
				oldal = adatok.get(i).parosVagyParatlan;
			}
		}
		for (KertTulajdonságok adat : adatok) {
			if (adat.parosVagyParatlan == 0) {
				hazSzam = hazSzam + 2;
			}
		}
		System.out.println("3. feladat\nA " + (oldal == 0 ? "páros" : "páratlan")
				+ " oldalon adták el az utolsó telket.\nAz utolsó telek házszáma: " + hazSzam);

		// 4.feladat
		for (int i = 0; i < adatok.size() - 1; i++) {
			KertTulajdonságok haz = adatok.get(i);
			KertTulajdonságok utanaHaz = adatok.get(i + 1);

			if (haz.parosVagyParatlan == 1 && haz.keritesSzin != ':' && haz.keritesSzin != '#'
					&& utanaHaz.parosVagyParatlan == 1 && utanaHaz.keritesSzin == haz.keritesSzin) {
				System.out.println("4. feladat\nA szomszédossal egyezik a kerítés színe: " + haz.hazSzam);
				break;
			}
		}

		// 5.feladat
		System.out.print("5. feladat\nAdjon meg egy házszámot! ");
		Scanner input = new Scanner(System.in);
		int megadottHazszam = input.nextInt();
		char elotteLevoKeritesSzin = 0;
		char utanaLevoKeritesSzin = 0;
		char jelenlegiKeritesSzin = 0;

		for (KertTulajdonságok adat : adatok) {
			if (adat.hazSzam == megadottHazszam) {
				System.out.println("A kerítés színe / állapota: " + adat.keritesSzin);
				jelenlegiKeritesSzin = adat.keritesSzin;
			}
			if (megadottHazszam - 1 == adat.hazSzam) {
				elotteLevoKeritesSzin = adat.keritesSzin;
			}
			if (megadottHazszam + 1 == adat.hazSzam) {
				utanaLevoKeritesSzin = adat.keritesSzin;
			}
		}
		char[] betuk = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S',
				'T', 'U', 'V', 'W', 'X', 'Y', 'Z' };
		int randomszam = -100;
		do {
			Random rand = new Random();
			randomszam = rand.nextInt(betuk.length);
		} while (betuk[randomszam] == elotteLevoKeritesSzin && betuk[randomszam] == utanaLevoKeritesSzin
				&& betuk[randomszam] == jelenlegiKeritesSzin);
		System.out.println("Egy lehetséges festési szín: " + betuk[randomszam]);

		// 6.feladat
		String text = "utcakep.txt";
		try {
			File fájl = new File(text);
			fájl.delete();
			fájl.createNewFile();
		} catch (IOException e) {
		}
		try {
			File fájl = new File(text);
			PrintWriter PW = new PrintWriter(fájl);
			for (KertTulajdonságok adat : adatok) {
				if (adat.parosVagyParatlan == 1) {
					for (int i = 0; i < adat.telekSzelesseg; i++) {
						PW.print(adat.keritesSzin);
					}
				}
			}
			PW.println();
			for (KertTulajdonságok adat : adatok) {
				if (adat.parosVagyParatlan == 1) {
					for (int i = 0; i < adat.telekSzelesseg; i++) {
						if (i == 0) {
							PW.print(adat.hazSzam);
							if (adat.hazSzam > 9) {
								i++;
							}
							if (adat.hazSzam > 99) {
								i++;
							}
						} else {
							PW.print(" ");
						}
					}
				}
			}
			PW.close();
		} catch (IOException e) {
		}
	}

	public static class KertTulajdonságok {
		private int parosVagyParatlan;
		private int telekSzelesseg;
		private char keritesSzin;
		private int hazSzam;

		public KertTulajdonságok(int parosVagyParatlan, int telekSzelesseg, char keritesSzin, int hazSzam) {
			this.parosVagyParatlan = parosVagyParatlan;
			this.telekSzelesseg = telekSzelesseg;
			this.keritesSzin = keritesSzin;
			this.hazSzam = hazSzam;
		}

		@Override
		public String toString() {
			return "KertTulajdonságok [parosVagyParatlan=" + parosVagyParatlan + ", telekSzelesseg=" + telekSzelesseg
					+ ", keritesSzin=" + keritesSzin + "]";
		}
	}
}
