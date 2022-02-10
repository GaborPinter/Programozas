package kektura;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class kektura {

	public static void main(String[] args) throws IOException {
		// 2.feladat
		List<String> sorok = Files.readAllLines(Path.of("kektura.txt"), StandardCharsets.UTF_8);
		ArrayList<Szakaszok> adatok = new ArrayList<Szakaszok>();
		for (int i = 1; i < sorok.size(); i++) {
			String[] DB = sorok.get(i).split(";");
			adatok.add(new Szakaszok(DB[0], DB[1], Double.parseDouble(DB[2].replace(",", ".")), Integer.parseInt(DB[3]),
					Integer.parseInt(DB[4]), DB[5].charAt(0)));
		}

		// ellenőrzés
		/*
		 * for (Szakaszok adat : adatok) { System.out.println(adat); }
		 */

		// 3.feladat
		System.out.println("3. feladat: Szakaszok száma: " + adatok.size() + " db");

		// 4.feladat
		double hossz = 0;
		for (int i = 0; i < adatok.size(); i++) {
			hossz = hossz + adatok.get(i).getHossz();
		}
		System.out.println(String.format("4. feladat: A túra teljes hossza: %.3f km", hossz));

		// 5.feladat
		double min = adatok.get(0).getHossz();
		for (Szakaszok adat : adatok) {
			if (adat.getHossz() < min) {
				min = adat.getHossz();
			}
		}
		System.out.println("5. feladat: A legrövidebb szakasz adatai:");
		for (Szakaszok adat : adatok) {
			if (adat.getHossz() == min) {
				System.out.println("\tKezdete: " + adat.getKiindulópont() + "\n\tVége: " + adat.getVégpont()
						+ "\n\tTávolság: " + String.valueOf(adat.getHossz()).replace(".", ",") + " km");
			}
		}

		// 7.feladat
		boolean van = false;
		System.out.println("7. feladat: Hiányos állomásnevek:");
		for (Szakaszok adat : adatok) {
			if (HianyosNev(adat.getVégpont()) && adat.getPecsételőhely() == 'i') {
				System.out.println("\t" + adat.getVégpont());
				van = true;
			}
		}
		if (van == false) {
			System.out.println("Nincs hiányos állomásnév!");
		}

		// 8.feladat
		int tengerszintFelttiMagasság = Integer.valueOf(sorok.get(0));
		int max = 0;
		String végpontNév = null;
		for (int i = 0; i < adatok.size(); i++) {
			if (max < tengerszintFelttiMagasság + adatok.get(i).getEmelkedésÖsszegek()
					- adatok.get(i).getLejtésÖsszegek()) {
				max = tengerszintFelttiMagasság + adatok.get(i).getEmelkedésÖsszegek()
						- adatok.get(i).getLejtésÖsszegek();
				végpontNév = adatok.get(i).getVégpont();
			}
		}
		System.out.println("8. feladat: A túra legmagasabban fekvő végpontja:\n\tA végpont neve: " + végpontNév
				+ "\n\tA végpont tengerszint feletti magassága: " + max + " m");

		// 9.feladat
		String text = "kektura2.txt";
		try {
			File fájl = new File(text);
			fájl.delete();
			fájl.createNewFile();
		} catch (IOException e) {
		}
		try {
			File fájl = new File(text);
			PrintWriter PW = new PrintWriter(fájl);
			PW.println(tengerszintFelttiMagasság);
			for (Szakaszok adat : adatok) {
				if (!adat.getVégpont().contains("pecsetelohely") && adat.getPecsételőhely() == 'i') {
					PW.println(adat.getKiindulópont() + " " + adat.getVégpont() + " pecsetelohely " + adat.getHossz()
							+ " " + adat.getEmelkedésÖsszegek() + " " + adat.getLejtésÖsszegek() + " "
							+ adat.getPecsételőhely());
				} else {
					PW.println(adat.getKiindulópont() + " " + adat.getVégpont() + " " + adat.getHossz() + " "
							+ adat.getEmelkedésÖsszegek() + " " + adat.getLejtésÖsszegek() + " "
							+ adat.getPecsételőhely());
				}
			}
			PW.close();
		} catch (IOException e) {
		}

	}

	// 6.feladat
	public static boolean HianyosNev(String vegpont) {
		if (!vegpont.contains("pecsetelohely")) {
			return true;
		}
		return false;
	}
}
