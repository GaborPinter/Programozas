package tesztverseny;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class tesztverseny {

	public static void main(String[] args) {

		
		// lista létrehozása
		ArrayList<Válasz> adatok = new ArrayList<>();
		// adatok beolvasása
		System.out.println("1. feladat: Az adatok beolvasása\n");
		try {
			System.setProperty("file.encoding", "UTF-8");
			BufferedReader BR = new BufferedReader(new FileReader("valaszok.txt"));
			String sor = "";
			while ((sor = BR.readLine()) != null) {
				String[] DB = sor.split(" ");
				if (sor.length() == 14) {
					adatok.add(new Válasz(DB[0]));
				} else {
					adatok.add(new Válasz(DB[0], DB[1]));
				}
			}
		} catch (IOException e) {

		}
		
		/* char[] megoldasok = Files.lines(Paths.get("./valaszok.txt"),StandardCharsets.UTF_8).findFirst().get().toCharArray();
	     List<Válasz> adatok = Files.lines(Paths.get("./valaszok.txt"),StandardCharsets.UTF_8)
	                              .skip(1)
	                              .map(k -> k.split(" "))
	                              .map(k->new Válasz(k[0],k[1].toCharArray()))
	                              .collect(Collectors.toList());
		*/
		/*
		 * for (Válasz adat : adatok) { System.out.println(adat.toString()); }
		 */

		// 2.feladat
		int résztvevők = 0;
		for (Válasz adat : adatok) {
			if (adat.getAzonosító() != null) {
				résztvevők++;
			}
		}
		System.out.println("2. feladat: A vetélkedőn " + résztvevők + " versenyző indult.\n");

		// 3.feladat
		char[] tippek = null;
		Scanner scanner = new Scanner(System.in);
		String azonosító = scanner.nextLine();
		for (Válasz adat : adatok) {
			if (azonosító.equals(adat.getAzonosító())) {
				tippek = adat.getTippek().toCharArray();
				System.out.println("3. feladat: A versenyző azonosítója = " + azonosító + "\n" + adat.getTippek()
						+ "	(a versenyző válasza)\n ");
			}
		}

		// 4.feladat
		char[] megoldás = null;
		for (Válasz adat : adatok) {
			if (adat.getAzonosító() == null) {
				megoldás = adat.getTippek().toCharArray();
			}
		}
		String jelölés = "";
		for (int i = 0; i < megoldás.length; i++) {
			if (megoldás[i] == tippek[i]) {
				jelölés = jelölés + "+";
			} else {
				jelölés = jelölés + " ";
			}
		}
		System.out.println("4. feladat:\n" + String.valueOf(megoldás) + "	(a helyes megoldás)\n" + jelölés
				+ "	(a versenyző helyes válaszai)\n ");

		// 5.feladat
		int feladatSorszám = scanner.nextInt() - 1;
		int fő = 0;
		for (Válasz adat : adatok) {
			if (megoldás[feladatSorszám] == adat.getTippek().charAt(feladatSorszám) && adat.getAzonosító() != null) {
				fő++;
			}
		}
		String százalék = String.valueOf((double) fő * 100 / (adatok.size() - 1)).substring(0, 5);
		System.out.println("5. feladat: A feladat sorszáma = " + (feladatSorszám + 1) + "\nA feladatra " + fő
				+ "  fő, a versenyzők " + százalék + "%-a adott helyes választ.\n");
		scanner.close();

		// 6.feladat

		System.out.println("6. feladat: A versenyzők pontszámának meghatározása\n ");
		for (Válasz adat : adatok) {
			int db = 0;
			for (int i = 0; i < megoldás.length; i++) {
				if (megoldás[i] == adat.getTippek().charAt(i) && adat.getAzonosító() != null) {
					if (i < 5) {
						db = db + 3;
					} else if (i > 4 && i < 10) {
						db = db + 4;
					} else if (i > 9 && i < 13) {
						db = db + 5;
					} else {
						db = db + 6;
					}
				}
			}
			adat.setPoints(db);
		}

		// fájl létrehozás
		String fájlnév = "pontok.txt";
		try {
			File fájl = new File(fájlnév);
			fájl.delete();
			fájl.createNewFile();
		} catch (IOException e) {
		}
		try {
			File fájl = new File(fájlnév);
			PrintWriter PW = new PrintWriter(fájl);
			for (Válasz adat : adatok) {
				if (adat.getAzonosító() != null) {
					PW.println(adat.kiír());
				}
			}
			PW.close();
		} catch (IOException e) {
		}

		// 7.feladat
		System.out.println("7. feladat: A verseny legjobbjai:");
		adatok.sort(Comparator.comparingInt(Válasz::getDb).reversed());
		for (int i = 1, j = 0; i < 4; i++, j++) {
			System.out.println(i + ".díj (" + adatok.get(j).getDb() + " pont) : " + adatok.get(j).getAzonosító());
			if (adatok.get(j).getDb() == adatok.get(j + 1).getDb()) {
				System.out
						.println(i + ".díj (" + adatok.get(j).getDb() + " pont) : " + adatok.get(j + 1).getAzonosító());
				j++;
			}
		}

	}
}
