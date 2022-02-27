package sorozatok;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class sorozatok {

	public static void main(String[] args) throws IOException {
		// 1.feladat
		List<String> sorok = Files.readAllLines(Path.of("lista.txt"), StandardCharsets.UTF_8);
		ArrayList<Tulajdonságok> adatok = new ArrayList<Tulajdonságok>();
		for (int i = 0; i < sorok.size(); i = i + 5) {
			adatok.add(new Tulajdonságok(sorok, i));
		}

		// ellenőrzés
		/*
		 * for (Tulajdonságok adat : adatok) { System.out.println(adat); }
		 */

		// 2.feladat
		int db = 0;
		for (Tulajdonságok adat : adatok) {
			if (!adat.dátum.equals("NI")) {
				db++;
			}
		}
		System.out.println("2. feladat\nA listában " + db + " db vetítési dátummal rendelkező epizód van.");

		// 3.feladat
		int l = 0;
		int ido = 0;
		for (Tulajdonságok adat : adatok) {
			if (adat.látta == true) {
				l++;
				ido = ido + adat.hossz;
			}
		}
		String szazalek = String.valueOf((double) l * 100 / adatok.size()).substring(0, 5);
		System.out.println("\n3. feladat\nA listában lévő epizódok " + szazalek + "%-át látta.");

		// 4.feladat
		int nap = ido / 1440;
		int ora = ido % 1440 / 60;
		int perc = ido % 1440 % 60;
		System.out.println(
				"\n4. feladat\nSorozatnézéssel " + nap + " napot " + ora + " órát és " + perc + " percet töltött.");

		// 5.feladat
		System.out.print("\n5. feladat\nAdjon meg egy dátumot! Dátum= ");
		Scanner input = new Scanner(System.in);
		String megadottDátum = input.nextLine();
		LocalDate megadottDátumformátum = LocalDate.of(Integer.valueOf(megadottDátum.substring(0, 4)),
				Integer.valueOf(megadottDátum.substring(5, 7)), Integer.valueOf(megadottDátum.substring(8, 10)) + 1);
		for (Tulajdonságok adat : adatok) {
			if (!adat.dátum.equals("NI") && adat.látta == false
					&& LocalDate.of(Integer.valueOf(adat.dátum.substring(0, 4)),
							Integer.valueOf(adat.dátum.substring(5, 7)), Integer.valueOf(adat.dátum.substring(8, 10)))
							.isBefore(megadottDátumformátum)) {
				System.out.println(adat.évadÉsEpizód + "\t" + adat.cím);
			}
		}

		// 7.feladat
		ArrayList<String> adottNapCimek = new ArrayList<String>();
		System.out.print("\n7. feladat\nAdja meg a hét egy napját (például cs)! Nap= ");
		String megadottNap = input.nextLine();
		for (Tulajdonságok adat : adatok) {
			if (!adat.dátum.equals("NI") && Hetnapja(Integer.valueOf(adat.dátum.substring(0, 4)),
					Integer.valueOf(adat.dátum.substring(5, 7)), Integer.valueOf(adat.dátum.substring(8, 10)))
							.equals(megadottNap)) {
				if (!adottNapCimek.contains(adat.cím)) {
					adottNapCimek.add(adat.cím);
				}
			}
		}
		if (!adottNapCimek.isEmpty()) {
			for (String adottNapCím : adottNapCimek) {
				System.out.println(adottNapCím);
			}
		} else {
			System.out.println("Az adott napon nem kerül adásba sorozat.");
		}

		// 8.feladat
		ArrayList<String> cimek = new ArrayList<String>();
		for (Tulajdonságok adat : adatok) {
			if (!cimek.contains(adat.cím)) {
				cimek.add(adat.cím);
			}
		}
		String text = "summa.txt";
		try {
			File fájl = new File(text);
			fájl.delete();
			fájl.createNewFile();
		} catch (IOException e) {
		}
		try {
			File fájl = new File(text);
			PrintWriter PW = new PrintWriter(fájl);
			for (int j = 0; j < cimek.size(); j++) {
				int darab = 0;
				int osszeg = 0;
				String cim = null;
				for (int i = 0; i < adatok.size(); i++) {
					if (cimek.get(j).equals(adatok.get(i).cím)) {
						darab++;
						osszeg = osszeg + adatok.get(i).hossz;
						cim = adatok.get(i).cím;
					}
				}
				PW.println(cim + " " + osszeg + " " + darab);
			}
			PW.close();
		} catch (IOException e) {
		}
	}

	public static class Tulajdonságok {
		private String dátum;
		private String cím;
		private String évadÉsEpizód;
		private int hossz;
		private boolean látta;

		public Tulajdonságok(List<String> lista, int i) {
			this.dátum = lista.get(i);
			this.cím = lista.get(i + 1);
			this.évadÉsEpizód = lista.get(i + 2);
			this.hossz = Integer.valueOf(lista.get(i + 3));
			this.látta = lista.get(i + 4).equals("1") ? true : false;
		}

		@Override
		public String toString() {
			return "Tulajdonságok [dátum=" + dátum + ", cím=" + cím + ", évadÉsEpizód=" + évadÉsEpizód + ", hossz="
					+ hossz + ", látta=" + látta + "]";
		}

	}

	// 6.feladat
	public static String Hetnapja(int ev, int ho, int nap) {
		String[] napok = { "v", "h", "k", "sze", "cs", "p", "szo" };
		int[] honapok = { 0, 3, 2, 5, 0, 3, 5, 1, 4, 6, 2, 4 };
		String hetnapja = null;
		if (ho < 3) {
			ev = ev - 1;
		}
		hetnapja = napok[(ev + ev / 4 - ev / 100 + ev / 400 + honapok[ho - 1] + nap) % 7];
		return hetnapja;
	}
}
