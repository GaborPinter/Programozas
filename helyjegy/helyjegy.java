package helyjegy;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class helyjegy {

	public static void main(String[] args) throws IOException {
		// 1.feladat
		List<String> sorok = Files.readAllLines(Path.of("eladott.txt"), StandardCharsets.UTF_8);
		ArrayList<Utas> adatok = new ArrayList<Utas>();
		for (int i = 1; i < sorok.size(); i++) {
			String[] DB = sorok.get(i).split(" ");
			adatok.add(new Utas(Integer.parseInt(DB[0]), Integer.parseInt(DB[1]), Integer.parseInt(DB[2])));
		}

		// ellenőrzés
		/*
		 * for (Utas adat : adatok) { System.out.println(adat); }
		 */

		// 2.feladat
		System.out.println("2. feladat:\nÜlés száma: " + adatok.get(adatok.size() - 1).ülésSzám + ".\nUtazott táv: "
				+ (adatok.get(adatok.size() - 1).leszállás - adatok.get(adatok.size() - 1).felszállás) + " km");

		// 3.feladat
		String[] elso = sorok.get(0).split(" ");
		int utHossza = Integer.parseInt(elso[1]);
		int eladottJegyekSzama = Integer.parseInt(elso[0]);
		int ar = Integer.parseInt(elso[2]);
		System.out.print("3. feladat:\nUtasok sorszáma akik végig utaztak: ");
		for (int i = 0; i < adatok.size(); i++) {
			if (adatok.get(i).leszállás - adatok.get(i).felszállás == utHossza) {
				System.out.print((i + 1) + ". ");
			}
		}

		// 4.feladat
		int bevetel = 0;
		for (Utas adat : adatok) {
			bevetel = bevetel + fizetnivalo(adat.felszállás, adat.leszállás);
		}
		System.out.println("\n4.feladat:\nÖsszes bevétel: " + bevetel + " Ft");

		// 5.feladat
		ArrayList<Integer> megallok = new ArrayList<Integer>();
		for (Utas adat : adatok) {
			if (!megallok.contains(adat.felszállás)) {
				megallok.add(adat.felszállás);
			}
			if (!megallok.contains(adat.leszállás)) {
				megallok.add(adat.leszállás);
			}
		}
		megallok.sort(Comparator.naturalOrder());
		int fel = 0;
		int le = 0;
		for (Utas adat : adatok) {
			if (adat.felszállás == megallok.get(megallok.size() - 2)) {
				fel++;
			}
			if (adat.leszállás == megallok.get(megallok.size() - 2)) {
				le++;
			}
		}
		System.out.println(
				"5.feladat:\nAz utolsó előtti megállónál felszálltak: " + fel + "-en,leszálltak: " + le + "-en");

		// 6.feladat
		System.out.println("6.feladat:\n" + (megallok.size() - 2) + " helyen állt meg a busz");

		// 7.feladat
		String text = "kihol.txt";
		try {
			File fájl = new File(text);
			fájl.delete();
			fájl.createNewFile();
		} catch (IOException e) {
		}
		System.out.print("7.feladat:\nAdjon meg egy távolságot: ");
		Scanner input = new Scanner(System.in);
		int megadottTáv = 0;
		do {
			megadottTáv = input.nextInt();
		} while (megadottTáv < -1 || megadottTáv > utHossza);
		ArrayList<Integer> ulesSzamok = new ArrayList<Integer>();
		for (Utas adat : adatok) {
			if (!ulesSzamok.contains(adat.ülésSzám)) {
				ulesSzamok.add(adat.ülésSzám);
			}
		}
		try {
			File fájl = new File(text);
			PrintWriter PW = new PrintWriter(fájl);
			for (int j = 0; j < eladottJegyekSzama; j++) {
				int utas = 0;
				for (int i = 0; i < adatok.size(); i++) {
					if (adatok.get(i).felszállás <= megadottTáv && adatok.get(i).leszállás > megadottTáv
							&& adatok.get(i).ülésSzám == j + 1) {
						utas = i + 1;
					}
				}
				PW.println((j + 1) + ". ülés: " + (utas == 0 ? "üres" : utas + ". utas"));
			}
			PW.close();
		} catch (IOException e) {
		}

	}

	public static class Utas {
		private int ülésSzám;
		private int felszállás;
		private int leszállás;

		public Utas(int ülésSzám, int felszállás, int leszállás) {
			this.ülésSzám = ülésSzám;
			this.felszállás = felszállás;
			this.leszállás = leszállás;
		}

		@Override
		public String toString() {
			return "Utas [ülésSzám=" + ülésSzám + ", felszállás=" + felszállás + ", leszállás=" + leszállás + "]";
		}
	}

	public static int fizetnivalo(int kezdet, int veg) {
		int ertek = veg - kezdet;
		int hanyszor = 0;
		hanyszor = ertek / 10;
		if (ertek % 10 != 0) {
			hanyszor = hanyszor + 1;
		}
		hanyszor = hanyszor * 71;

		if (String.valueOf(hanyszor).endsWith("1") || String.valueOf(hanyszor).endsWith("2")) {
			hanyszor = hanyszor - String.valueOf(hanyszor).charAt(String.valueOf(hanyszor).length() - 1);
		}
		if (String.valueOf(hanyszor).endsWith("3") || String.valueOf(hanyszor).endsWith("4")
				|| String.valueOf(hanyszor).endsWith("6") || String.valueOf(hanyszor).endsWith("7")) {
			hanyszor = hanyszor - String.valueOf(hanyszor).charAt(String.valueOf(hanyszor).length() - 1) + 5;
		}
		if (String.valueOf(hanyszor).endsWith("8") || String.valueOf(hanyszor).endsWith("9")) {
			hanyszor = hanyszor - String.valueOf(hanyszor).charAt(String.valueOf(hanyszor).length() - 1) + 10;
		}
		return hanyszor;
	}
}
