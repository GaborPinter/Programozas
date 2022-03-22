package vb2018;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class vb2018 {

	public static void main(String[] args) throws IOException {

		// 2.feladat
		List<String> sorok = Files.readAllLines(Path.of("vb2018.txt"), StandardCharsets.UTF_8);
		ArrayList<Stadion> adatok = new ArrayList<Stadion>();
		for (int i = 1; i < sorok.size(); i++) {
			adatok.add(new Stadion(sorok.get(i)));
		}

		// beolvasott adatok ellenőrzése
		/*
		 * for (Stadion adat : adatok) { System.out.println(adat); }
		 */

		// 3.feladat
		System.out.println("3. feladat: Stadionok száma: " + adatok.size());

		// 4.feladat
		int min = adatok.get(0).getFerohely();
		for (Stadion adat : adatok) {
			if (min > adat.getFerohely()) {
				min = adat.getFerohely();
			}
		}
		for (Stadion adat : adatok) {
			if (min == adat.getFerohely()) {
				System.out.println("4. feladat: A legkevesebb férőhely:\n\tVáros: " + adat.getVarosNev()
						+ "\n\tStadion neve: " + adat.getStadionNev() + "\n\tFérőhely: " + adat.getFerohely());
			}
		}

		// 5.feladat
		double osszes = 0;
		for (Stadion adat : adatok) {
			osszes = osszes + adat.getFerohely();
		}
		System.out
				.println("5. feladat: Átlagos férőhelyszám: " + String.valueOf(osszes / adatok.size()).substring(0, 7));

		// 6.feladat
		int alternativNev = 0;
		for (Stadion adat : adatok) {
			if (!adat.getStadionAlternativNev().equals("n.a.")) {
				alternativNev++;
			}
		}
		System.out.println("6. feladat: Két néven is ismert stadionok száma: " + alternativNev);

		// 7.feladat
		String megadottNev = null;
		Scanner input = null;
		try {
			input = new Scanner(System.in);
			do {
				System.out.println("7. feladat: Kérem a város nevét: ");
				megadottNev = input.nextLine();
			} while (megadottNev.length() < 3);
		} finally {
			if (input != null) {
				input.close();
			}
		}

		// 8.feladat
		boolean vanVaros = false;
		for (Stadion adat : adatok) {
			if (adat.getVarosNev().equalsIgnoreCase(megadottNev)) {
				vanVaros = true;
				break;
			}
		}
		System.out.println("8. feladat: A megadott város" + (vanVaros == true ? " " : " nem ") + "VB helszín.");

		// 9.feladat
		List<String> kulonbozoVaros = new ArrayList<String>();
		for (Stadion adat : adatok) {
			if (!kulonbozoVaros.contains(adat.getVarosNev())) {
				kulonbozoVaros.add(adat.getVarosNev());
			}
		}
		System.out
				.println(String.format("9. feladat: %s különböző városban voltak mérkőzések.", kulonbozoVaros.size()));

	}
}
