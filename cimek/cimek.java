package cimek;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class cimek {

	public static void main(String[] args) throws IOException {

		// 1.feladat
		ArrayList<Tagok> adatok = new ArrayList<Tagok>();
		int sorszam = 0;
		try {
			System.setProperty("file.encoding", "UTF-8");
			BufferedReader BR = new BufferedReader(new FileReader("ip.txt"));
			String sor = "";
			while ((sor = BR.readLine()) != null) {
				sorszam = sorszam + 1;
				String[] DB = sor.split(":");
				adatok.add(new Tagok(DB[0], DB[1], DB[2], DB[3], DB[4], DB[5], DB[6], DB[7], sorszam));
			}
		} catch (IOException e) {
		}

		// ellenőrzés
		/*
		 * for (Tagok adat : adatok) { System.out.println(adat); }
		 */

		// 2.feladat
		System.out.println(String.format("2. feladat:\nAz állományban %s darab adatsor van. ", adatok.size()));

		// 3.feladat

		List<String> sorok = Files.readAllLines(Path.of("ip.txt"), StandardCharsets.UTF_8);
		sorok.sort(Comparator.naturalOrder());
		System.out.println("\n3. feladat:\nA legalacsonyabb tárolt IP-cím:\n" + sorok.get(0));

		// 4.feladat
		int dok = 0;
		int glob = 0;
		int hely = 0;
		for (Tagok adat : adatok) {
			if (adat.getElso().equals("2001") && adat.getMasodik().equals("0db8")) {
				dok++;
			}
			if (adat.getElso().equals("2001") && adat.getMasodik().startsWith("0e")) {
				glob++;
			}
			if (adat.getElso().startsWith("fc") || adat.getElso().startsWith("fd")) {
				hely++;
			}
		}
		System.out.println(String.format("\n4. feladat:\nDokumentációs cím: %s darab \nGlobális egyedi cím: %s darab \n"
				+ "Helyi egyedi cím: %s darab ", dok, glob, hely));

		// 5.feladat
		String text = "sok.txt";
		try {
			File fájl = new File(text);
			fájl.delete();
			fájl.createNewFile();
		} catch (IOException e) {
		}
		try {
			File fájl = new File(text);
			PrintWriter PW = new PrintWriter(fájl);
			for (int i = 0; i < sorok.size(); i++) {
				int db = 0;
				for (int j = 0; j < sorok.get(i).length(); j++) {
					if (sorok.get(i).charAt(j) == '0') {
						db++;
					}
				}
				if (db > 17) {
					PW.println((i + 1) + " " + sorok.get(i));
				}
			}
			PW.close();
		} catch (IOException e) {
		}

		// 6.feladat
		Scanner input = new Scanner(System.in);
		System.out.print("\n6. feladat:\nKérek egy sorszámot: ");
		int megadottSzam = input.nextInt() - 1;
		System.out.println(sorok.get(megadottSzam));
		String egyszerusitett = sorok.get(megadottSzam).replace(":0", ":").replace(":0", ":").replace(":0", ":");
		System.out.println(egyszerusitett);

		// 7.feladat
		System.out.println("\n7. feladat:");
		String TovabbEgyszerusitett = egyszerusitett.replace(":0", ":").replace("::", ":");
		if (TovabbEgyszerusitett.equals(egyszerusitett)) {
			System.out.println("Nem rövidíthető tovább.");
		} else {
			System.out.println(TovabbEgyszerusitett);
		}

	}
}
