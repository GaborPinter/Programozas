package otszaz;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;

public class otszaz {

	public static void main(String[] args) throws IOException {
		// 1.feladat
		List<String> sorok = Files.readAllLines(Path.of("penztar.txt"), StandardCharsets.UTF_8);
		ArrayList<List<String>> adatok = new ArrayList<List<String>>();
		ArrayList<String> ujLista = new ArrayList<String>();
		for (int i = 0; i < sorok.size(); i++) {
			if (sorok.get(i).equals("F")) {
				adatok.add(new ArrayList<>(ujLista));
				ujLista.clear();
			} else {
				ujLista.add(sorok.get(i));
			}
		}

		// ellenőrzés
		/*
		 * for (List<String> adat : adatok) { System.out.println(adat); }
		 */

		// 2.feladat
		System.out.println("2. feladat\nA fizetések száma: " + adatok.size());

		// 3.feladat
		System.out.println("\n3. feladat\nAz első vásárló " + adatok.get(0).size() + " darab árucikket vásárolt.");

		// 4.feladat
		Scanner input = new Scanner(System.in);
		System.out.print("\n4. feladat\nAdja meg egy vásárlás sorszámát! ");
		int megadottSorszám = input.nextInt() - 1;
		input.nextLine();
		System.out.print("Adja meg egy árucikk nevét! ");
		String megadottÁrucikkNév = input.nextLine();
		System.out.print("Adja meg a vásárolt darabszámot! ");
		int megadottDarabszám = input.nextInt();

		// 5.feladat
		System.out.println("\n5.feladat");
		for (int i = 0; i < adatok.size(); i++) {
			if (adatok.get(i).contains(megadottÁrucikkNév)) {
				System.out.println("Az első vásárlás sorszáma: " + (i + 1));
				break;
			}
		}
		for (int i = adatok.size() - 1; i > 0; i--) {
			if (adatok.get(i).contains(megadottÁrucikkNév)) {
				System.out.println("Az utolsó vásárlás sorszáma: " + (i + 1));
				break;
			}
		}
		int db = 0;
		for (int i = 0; i < adatok.size(); i++) {
			if (adatok.get(i).contains(megadottÁrucikkNév)) {
				db++;
			}
		}
		System.out.println(db + " vásárlás során vettek belőle.");

		// 6.feladat
		int osszeg = 0;
		for (int i = 0; i < megadottDarabszám; i++) {
			if (i == 0) {
				osszeg = osszeg + 500;
			} else if (i == 1) {
				osszeg = osszeg + 450;
			} else {
				osszeg = osszeg + 400;
			}
		}
		System.out.println("\n6. feladat\n" + megadottDarabszám + " darab vételekor fizetendő: " + osszeg);

		// 7.feladat
		/*
		 * HashMap<String, Integer>aruEsSzama=new HashMap<String, Integer>(); for(int
		 * i=0;i<adatok.size();i++) { if(i==megadottSorszám) { for(int
		 * j=0;j<adatok.get(i).size();j++) {
		 * aruEsSzama.put(adatok.get(i).get(j),aruEsSzama.getOrDefault(adatok.get(i).get
		 * (j),0)+1 ); } } }
		 */
		System.out.println("\n7. feladat");
		ArrayList<String> targyak = new ArrayList<String>();
		for (int i = 0; i < adatok.size(); i++) {
			if (i == megadottSorszám) {
				for (int j = 0; j < adatok.get(i).size(); j++) {
					if (!targyak.contains(adatok.get(i).get(j))) {
						targyak.add(adatok.get(i).get(j));
					}
				}
			}
		}
		for (int i = 0; i < targyak.size(); i++) {
			int ertek = 0;
			for (int j = 0; j < adatok.size(); j++) {
				if (j == megadottSorszám) {
					for (int k = 0; k < adatok.get(j).size(); k++) {
						if (targyak.get(i).equals(adatok.get(j).get(k))) {
							ertek++;
						}
					}
				}
			}
			System.out.println(ertek + " " + targyak.get(i));
		}

		// 8.feladat
		String text = "osszeg.txt";
		try {
			File fájl = new File(text);
			fájl.delete();
			fájl.createNewFile();
		} catch (IOException e) {
		}
		try {
			File fájl = new File(text);
			PrintWriter PW = new PrintWriter(fájl);
			for (int i = 0; i < adatok.size(); i++) {
				int ossz = 0;
				Map<String, Integer> statisztika = stat(adatok.get(i));
				for (Entry<String, Integer> entries : statisztika.entrySet()) {
					ossz = ossz + penz(entries.getValue());
				}
				PW.println((i + 1) + ": " + ossz);
			}
			PW.close();
		} catch (IOException e) {
		}

	}

	public static int penz(int db) {
		int osszeg = 0;
		for (int i = 0; i < db; i++) {
			if (i == 0) {
				osszeg = osszeg + 500;
			} else if (i == 1) {
				osszeg = osszeg + 450;
			} else {
				osszeg = osszeg + 400;
			}
		}
		return osszeg;
	}

	// adatok terkepbe pakolasa
	public static Map<String, Integer> stat(List<String> sorok) {
		HashMap<String, Integer> terkep = new HashMap<String, Integer>();
		for (String sor : sorok) {
			if (!terkep.containsKey(sor)) {
				terkep.put(sor, Collections.frequency(sorok, sor));
			}
		}
		return terkep;
	}
}
