package cegesauto;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Scanner;

public class cegesauto {

	public static void main(String[] args) {
		// 1.feladat
		autóTulajdonság[] adatok = new autóTulajdonság[500];
		try {
			System.setProperty("file.encoding", "UTF-8");
			BufferedReader BR = new BufferedReader(new FileReader("autok.txt"));
			String sor = "";
			int i = 0;
			while ((sor = BR.readLine()) != null) {
				String[] DB = sor.split(" ");
				adatok[i] = new autóTulajdonság(Integer.parseInt(DB[0]), LocalTime.parse(DB[1]), DB[2],
						Integer.parseInt(DB[3]), Integer.parseInt(DB[4]), Integer.parseInt(DB[5]));
				i++;
			}
		} catch (IOException e) {
		}

		/*
		 * for (autóTulajdonság adat : adatok) { System.out.println(adat); }
		 */

		// 2.feladat
		int maxNap = 0;
		for (autóTulajdonság adat : adatok) {
			if (adat != null && adat.getNap() > maxNap) {
				maxNap = adat.getNap();
			}
		}
		int maxÓra = 0;
		int maxPerc = 0;
		for (autóTulajdonság adat : adatok) {
			if (adat != null && adat.getNap() == maxNap && adat.getKiBeHajtás() == 0
					&& adat.getÓraperc().getHour() > maxÓra) {
				maxÓra = adat.getÓraperc().getHour();
			}
			if (adat != null && adat.getNap() == maxNap && adat.getKiBeHajtás() == 0
					&& adat.getÓraperc().getMinute() > maxPerc) {
				maxPerc = adat.getÓraperc().getMinute();
			}
		}
		for (autóTulajdonság adat : adatok) {
			if (adat != null && adat.getNap() == maxNap && adat.getKiBeHajtás() == 0
					&& adat.getÓraperc().getHour() == maxÓra && adat.getÓraperc().getMinute() == maxPerc) {
				System.out.println("2. feladat \n" + maxNap + ". nap rendszám: " + adat.getRendszám());
			}
		}

		// 3.feladat
		Scanner input = new Scanner(System.in);
		int megAdottNap = input.nextInt();
		System.out.println("3. feladat\nNap: " + megAdottNap + "\nForgalom a(z) " + megAdottNap + ". napon:");
		for (autóTulajdonság adat : adatok) {
			if (adat != null && adat.getNap() == megAdottNap) {
				System.out.println(adat.getÓraperc() + " " + adat.getRendszám() + " " + adat.getSzemélyAzonosító() + " "
						+ (adat.getKiBeHajtás() == 0 ? "ki" : "be"));
			}
		}

		// 4.feladat
		ArrayList<String> rsz = new ArrayList<String>();
		for (autóTulajdonság adat : adatok) {
			if (adat != null && adat.getKiBeHajtás() == 0) {
				rsz.add(adat.getRendszám());
			}
			if (adat != null && adat.getKiBeHajtás() == 1) {
				rsz.remove(adat.getRendszám());
			}
		}
		System.out.println("4. feladat\nA hónap végén " + rsz.size() + " autót nem hoztak vissza.");

		// 5.feladat
		ArrayList<String> autok = new ArrayList<String>();
		boolean bennevan = false;
		for (autóTulajdonság adat : adatok) {
			if (adat != null && autok.contains(adat.getRendszám())) {
				bennevan = true;
			}
			if (adat != null && bennevan == false) {
				autok.add(adat.getRendszám());
			}
			bennevan = false;
		}
		System.out.println("5. feladat");
		for (String auto : autok) {
			int km = 0;
			for (int i = adatok.length - 1; i > -1; i--) {
				if (adatok[i] != null && adatok[i].getRendszám().equals(auto)) {
					int maxKm = adatok[i].getKm();
					km = 0;
					for (int j = 0; j < adatok.length; j++) {
						if (adatok[j] != null && adatok[j].getRendszám().equals(auto)) {
							km = maxKm - adatok[j].getKm();
							break;
						}
					}
					break;
				}
			}
			System.out.println(auto + " " + km + " km");
		}

		// 6.feladat
		int leghosszabb = 0;
		int azonosító = 0;
		for (String auto : autok) {
			int km = 0;
			for (int i = 0; i < adatok.length; i++) {
				if (adatok[i] != null && adatok[i].getRendszám().equals(auto)) {
					km = adatok[i].getKiBeHajtás() == 0 ? adatok[i].getKm() : adatok[i].getKm() - km;
					if (adatok[i] != null && adatok[i].getKiBeHajtás() == 1 && km > leghosszabb) {
						leghosszabb = km;
						azonosító = adatok[i].getSzemélyAzonosító();
					}
				}
			}
		}
		System.out.println("6. feladat \nLeghosszabb út: " + leghosszabb + " km, személy: " + azonosító);

		// 7.feladat
		Scanner rendszám = new Scanner(System.in);
		String kapottrsz = rendszám.nextLine();
		String text = kapottrsz + "_menetlevel.txt";
		System.out.println("7. feladat \nRendszám: " + kapottrsz + " \nMenetlevél kész.");
		try {
			File fájl = new File(text);
			fájl.delete();
			fájl.createNewFile();
		} catch (IOException e) {
		}
		try {
			File fájl = new File(text);
			PrintWriter PW = new PrintWriter(fájl);
			for (autóTulajdonság adat : adatok) {
				if (adat != null && adat.getRendszám().equals(kapottrsz) && adat.getKiBeHajtás() == 0) {
					PW.print(adat.getSzemélyAzonosító() + "\t" + adat.getNap() + ". " + adat.getÓraperc() + "\t"
							+ adat.getKm() + " km\t");
				}
				if (adat != null && adat.getRendszám().equals(kapottrsz) && adat.getKiBeHajtás() == 1) {
					PW.print(adat.getNap() + ". " + adat.getÓraperc() + "\t" + adat.getKm() + " km\n");
				}
			}
			PW.close();
		} catch (IOException e) {
		}

	}
}
