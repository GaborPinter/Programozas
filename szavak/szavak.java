package szavak;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class szavak {

	public static void main(String[] args) throws IOException {
		// 1.feladat
		System.out.println("1. feladat:");
		Scanner input = new Scanner(System.in);
		System.out.print("Adjon meg egy szot amely csak az angol abc kisbetuit tartalmazza: ");
		String megadottSzó = input.nextLine();
		char[] maganhangzok = { 'a', 'e', 'i', 'o', 'u' };
		boolean van = false;
		for (int i = 0; i < megadottSzó.length(); i++) {
			for (int j = 0; j < maganhangzok.length; j++) {
				if (megadottSzó.charAt(i) == maganhangzok[j]) {
					van = true;
				}
			}
		}
		if (van == true) {
			System.out.println("Van benne magángangzó.");
		} else {
			System.out.println("Nincs benne magánhagzó.");
		}

		// 2.feladat
		List<String> sorok = Files.readAllLines(Path.of("szoveg.txt"), StandardCharsets.UTF_8);
		int max = 0;
		for (int i = 0; i < sorok.size(); i++) {
			if (sorok.get(i).length() > max) {
				max = sorok.get(i).length();
			}
		}
		for (String sor : sorok) {
			if (sor.length() == max) {
				System.out.println(
						"2. feladat:\nA leghosszabb szó: " + sor + ", ami " + (sor.length() + 1) + " karakterből áll.");
				break;
			}
		}

		// 3.feladat
		int db = 0;
		System.out.println("3. feladat:");
		for (int j = 0; j < sorok.size(); j++) {
			int magandb = 0;
			int maganertek = 0;
			int mshertek = 0;
			for (int k = 0; k < sorok.get(j).length(); k++) {
				for (int i = 0; i < maganhangzok.length; i++) {
					if (sorok.get(j).charAt(k) == maganhangzok[i]) {
						magandb++;
					}
				}
			}
			mshertek = sorok.get(j).length() - magandb;
			maganertek = sorok.get(j).length() - mshertek;
			if (maganertek > mshertek) {
				System.out.print(sorok.get(j) + " ");
				db++;
			}
		}
		double szazalek = (double) db * 100 / sorok.size();
		System.out.println("\n" + db + "/" + sorok.size() + " : " + String.format("%.2f", szazalek) + "%");

		// 4.feladat
		ArrayList<String> otkarakteres = new ArrayList<String>();
		for (String sor : sorok) {
			if (sor.length() == 5) {
				otkarakteres.add(sor);
			}
		}
		System.out.print("4.feladat:\nAdjon meg egy harom karakteres szoreszletet: ");
		String megadottSzoreszlet = input.nextLine();
		for (String szo : otkarakteres) {
			if (szo.substring(1, 4).equals(megadottSzoreszlet)) {
				System.out.print(szo + " ");
			}
		}

		// 5.feladat
		String text = "letra.txt";
		try {
			File fájl = new File(text);
			fájl.delete();
			fájl.createNewFile();
		} catch (IOException e) {
		}
		try {
			File fájl = new File(text);
			PrintWriter PW = new PrintWriter(fájl);
			ArrayList<String> harombetusek = new ArrayList<String>();
			ArrayList<String> megfelelo = new ArrayList<String>();
			for (String szo : otkarakteres) {
				String kozepsoHarom = szo.substring(1, 4);
				if (!harombetusek.contains(kozepsoHarom)) {
					harombetusek.add(kozepsoHarom);
					for (String otkarakter : otkarakteres) {
						if (otkarakter.regionMatches(1, kozepsoHarom, 0, 3)) {
							megfelelo.add(otkarakter);
						}
					}
					if (megfelelo.size() > 1) {
						for (String m : megfelelo) {
							PW.println(m);
						}
						PW.println();
					}
					megfelelo.clear();
				}
			}
			PW.close();
		} catch (IOException e) {
		}
	}
}
