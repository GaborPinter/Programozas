package helsinki1952;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class Helsinki1952 {

	public static void main(String[] args) {

		// lista létrehozás és adatbeolvasás
		ArrayList<Olimpia> adatok = new ArrayList<Olimpia>();
		try {
			System.setProperty("file.encoding", "UTF-8");
			BufferedReader BR = new BufferedReader(new FileReader("helsinki.txt"));
			String sor = "";
			while ((sor = BR.readLine()) != null) {
				String[] DB = sor.split(" ");
				adatok.add(new Olimpia(DB[0], DB[1], DB[2], DB[3]));
			}
		} catch (IOException e) {
			// feltételezzük hogy nem dob kivételt
		}

		// beolvasás ellenőrzése
		for (Olimpia adat : adatok) {
			System.out.println(adat.toString());
		}

		// 3.feladat
		System.out.println("3. feladat:");
		System.out.println("Pontszerző helyezések száma: " + adatok.size());

		// 4.feladat
		System.out.println("4. feladat:");
		int a = 0, b = 0, c = 0;
		for (Olimpia adat : adatok) {
			if (adat.getHelyezés() == 1) {
				a++;
			} else if (adat.getHelyezés() == 2) {
				b++;
			} else if (adat.getHelyezés() == 3) {
				c++;
			}
		}
		System.out.println("Arany: " + a + "\nEzüst: " + b + "\nBronz: " + c + "\nÖsszesen: " + (a + b + c));

		// 5.feladat
		System.out.println("5. feladat:");
		int db = olimpiaiPontSzámítás(adatok);
		System.out.println("Olimpiai pontok száma: " + db);

		// 6.feladat
		System.out.println("6. feladat:");
		int torna = 0;
		int uszas = 0;
		for (Olimpia adat : adatok) {
			if (adat.getHelyezés() <= 3 && adat.getSportágNeve().equals("torna")) {
				torna++;
			}
			if (adat.getHelyezés() <= 3 && adat.getSportágNeve().equals("uszas")) {
				uszas++;
			}
		}
		if (torna > uszas) {
			System.out.println("Torna sportágban szereztek több érmet");
		} else if (torna < uszas) {
			System.out.println("Úszás sportágban szereztek több érmet");
		} else {
			System.out.println("Egyenlő volt az érmek száma");
		}

		// 7.feladat
		for (Olimpia adat : adatok) {
			if (adat.getSportágNeve().equals("kajakkenu")) {
				adat.setSportágNeve("kajak-kenu");
			}

		}
		// Fájlba írás
		String fájlnév = "helsinki2.txt";
		try {
			File fájl = new File(fájlnév);
			fájl.delete();
			fájl.createNewFile();
		} catch (IOException e) {

		}
		// Adat beírás a fájlba
		try {
			File fájl = new File(fájlnév);
			PrintWriter PW = new PrintWriter(fájl);
			for (Olimpia adat : adatok) {
				PW.println(adat.toString());
			}
			PW.close();
		} catch (IOException e) {

		}

		// 8.feladat
		System.out.println("8. feladat");
		int max = 0;
		for (int j = 0; j < adatok.size(); j++) {
			if (adatok.get(j).getSportolókSzáma() > max) {
				max = adatok.get(j).getSportolókSzáma();
			}
		}
		for (Olimpia adat : adatok) {
			if (max == adat.getSportolókSzáma()) {
				System.out.println("Helyezés: " + adat.getHelyezés() + "\nSportág: " + adat.getSportágNeve()
						+ "\nVersenyszám: " + adat.getVersenyszám() + "\nSportolók száma: " + adat.getSportolókSzáma());
			}
		}

	}

	public static int olimpiaiPontSzámítás(ArrayList<Olimpia> adatok) {
		int db = 0;
		for (Olimpia adat : adatok) {
			switch (adat.getHelyezés()) {
			case 1 -> db = db + 7;
			case 2 -> db = db + 5;
			case 3 -> db = db + 4;
			case 4 -> db = db + 3;
			case 5 -> db = db + 2;
			case 6 -> db = db + 1;
			}
		}
		return db;
	}

}
