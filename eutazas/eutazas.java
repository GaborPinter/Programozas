package eutazas;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class eutazas {

	public static void main(String[] args) {

		// 1.feladat
		ArrayList<Bérlet> adatok = new ArrayList<Bérlet>();
		try {
			System.setProperty("file.encoding", "UTF-8");
			BufferedReader BR = new BufferedReader(new FileReader("utasadat.txt"));
			String sor = "";
			while ((sor = BR.readLine()) != null) {
				String[] DB = sor.split(" ");
				adatok.add(new Bérlet(Integer.parseInt(DB[0]), DB[1], Integer.parseInt(DB[2]), DB[3],
						Integer.parseInt(DB[4])));
			}
		} catch (IOException e) {
		}

		/*
		 * for (Bérlet adat : adatok) { System.out.println(adat); }
		 */

		// 2.feladat
		System.out.println(String.format("2. feladat\nA buszra %s utas akart felszállni.", adatok.size()));

		// 3.feladat
		int ervenytelen = 0;
		for (Bérlet adat : adatok) {
			if (adat.getÉrvényesség() == 0) {
				ervenytelen++;
			}
			if (Integer.parseInt(adat.getDátumIdő().substring(0, 8)) > adat.getÉrvényesség()
					&& adat.getÉrvényesség() > 10) {
				ervenytelen++;
			}
		}
		System.out.println(String.format("3. feladat\nA buszra %s utas nem szállhatott fel.", ervenytelen));

		// 4.feladat
		int max = 0;
		int index = 0;
		List<Integer> megallok = new ArrayList<Integer>();
		for (Bérlet adat : adatok) {
			if (!megallok.contains(adat.getMegálló())) {
				megallok.add(adat.getMegálló());
			}
		}
		for (int i = 0; i < megallok.size(); i++) {
			int akt = 0;
			for (Bérlet adat : adatok) {
				if (megallok.get(i) == adat.getMegálló()) {
					akt++;
				}
				if (akt > max) {
					max = akt;
					index = megallok.get(i);
				}
			}
		}
		System.out.println(
				String.format("4. feladat\nA legtöbb utas (%s fő) a %s. megállóban próbált felszállni. ", max, index));

		// 5.feladat
		int ingyenes = 0;
		int kedvezmenyes = 0;
		for (Bérlet adat : adatok) {
			if (Integer.parseInt(adat.getDátumIdő().substring(0, 8)) - adat.getÉrvényesség() <= 0) {
				if (adat.getTípus().equals("NYP") || adat.getTípus().equals("RVS") || adat.getTípus().equals("GYK")) {
					ingyenes++;
				}
				if (adat.getTípus().equals("NYB") || adat.getTípus().equals("TAB")) {
					kedvezmenyes++;
				}
			}
		}
		System.out.println(
				String.format("5. feladat\nIngyenesen utazók száma:" + " %s fő\nA kedvezményesen utazók száma: %s fő",
						ingyenes, kedvezmenyes));

		// 6.feladat
		// függvény ellenőrzése
		// int nap=napokszama(2020, 04, 03, 2021, 04, 03);
		// System.out.println(nap);

		// 7.feladat
		String text = "figyelmeztetes.txt";
		try {
			File fájl = new File(text);
			fájl.delete();
			fájl.createNewFile();
		} catch (IOException e) {
		}
		try {
			File fájl = new File(text);
			PrintWriter PW = new PrintWriter(fájl);
			for (Bérlet adat : adatok) {
				if ((adat.getÉrvényesség() - Integer.parseInt(adat.getDátumIdő().substring(0, 8))) < 4
						&& adat.getÉrvényesség() > 10) {
					PW.println(adat.getKártyaAzonosító() + " " + (String.valueOf(adat.getÉrvényesség()).substring(0, 4))
							+ "-" + (String.valueOf(adat.getÉrvényesség()).substring(4, 6)) + "-"
							+ (String.valueOf(adat.getÉrvényesség()).substring(6, 8)));
				}
			}
			PW.close();
		} catch (IOException e) {
		}

	}

	// 6.feladat
	public static int napokszama(int e1, int h1, int n1, int e2, int h2, int n2) {
		h1 = (h1 + 9) % 12;
		e1 = e1 - h1 / 10;
		int d1 = 365 * e1 + e1 / 4 - e1 / 100 + e1 / 400 + (h1 * 306 + 5) / 10 + n1 - 1;
		h2 = (h2 + 9) % 12;
		e2 = e2 - h2 / 10;
		int d2 = 365 * e2 + e2 / 4 - e2 / 100 + e2 / 400 + (h2 * 306 + 5) / 10 + n2 - 1;
		int napokszama = d2 - d1;
		return napokszama;
	}

}
