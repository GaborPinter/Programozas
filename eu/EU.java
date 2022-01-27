
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class EU {

	public static void main(String[] args) {

		ArrayList<Országok> adatok = new ArrayList<>();
		try {
			System.setProperty("file.encoding", "UTF_8");
			BufferedReader BR = new BufferedReader(new FileReader("EUcsatlakozas.txt"));
			String sor = "";
			while ((sor = BR.readLine()) != null) {
				String[] érték = sor.split(";");
				adatok.add(new Országok(érték[0], érték[1]));
			}
		} catch (IOException e) {

		}

		/*
		 * for (Országok adat : adatok) { System.out.println(adat); }
		 */

		// 3.feladat
		System.out.println("3. feladat: EU tagállamainak száma: " + adatok.size() + " db");

		// 4.feladat
		int i = 0;
		for (Országok adat : adatok) {
			if (adat.getDátum().startsWith("2007")) {
				i++;
			}
		}
		System.out.println("4. feladat: 2007-ben " + i + " ország csatlakozott.");

		// 5.feladat
		for (Országok adat : adatok) {
			if (adat.getOrszág().equals("Magyarország")) {
				System.out.println("5. feladat: Magyarország csatlakozásának dátuma: " + adat.getDátum());
			}
		}

		// 6.feladat
		boolean érték = false;
		for (Országok adat : adatok) {
			if (adat.getDátum().substring(5, 7).equals("05")) {
				érték = true;
			}
		}
		System.out.println("6. feladat: Májusban" + (érték ? " volt" : " nem volt") + " csatlakozás!");

		// 7.feladat
		int maxÉv = 0;
		int maxHó = 0;
		int maxNap = 0;
		for (Országok adat : adatok) {
			if (Integer.decode(adat.getDátum().substring(0, 4)) > maxÉv) {
				maxÉv = Integer.decode(adat.getDátum().substring(0, 4));
			}
			if (Integer.decode(adat.getDátum().substring(0, 4)) == maxÉv
					&& Integer.decode(adat.getDátum().substring(5, 7)) > maxHó) {
				maxHó = Integer.decode(adat.getDátum().substring(5, 7));
			}
			if (Integer.decode(adat.getDátum().substring(0, 4)) == maxÉv
					&& Integer.decode(adat.getDátum().substring(5, 7)) == maxHó
					&& Integer.decode(adat.getDátum().substring(8)) > maxNap) {
				maxNap = Integer.decode(adat.getDátum().substring(8, adat.getDátum().length()));
			}
		}
		for (Országok adat : adatok) {
			if (Integer.decode(adat.getDátum().substring(0, 4)) == maxÉv
					&& Integer.decode(adat.getDátum().substring(5, 7)) == maxHó
					&& Integer.decode(adat.getDátum().substring(8, adat.getDátum().length())) == maxNap) {
				System.out.println("7. feladat: Legutoljára csatlakozott ország: " + adat.getOrszág());
			}
		}
		
		// 8.feladat
		System.out.println("8. feladat: Statisztika");
		ArrayList<String> évSzámok = new ArrayList<String>();
		boolean vanÉvszám = false;
		for (int t = 0; t < adatok.size(); t++) {
			for (String évSzám : évSzámok) {
				if (adatok.get(t).getDátum().substring(0, 4).equals(évSzám)) {
					vanÉvszám = true;
				}
			}
			if (vanÉvszám == false) {
				évSzámok.add(adatok.get(t).getDátum().substring(0, 4));
			}
			vanÉvszám = false;
		}
		for (int j = 0; j < évSzámok.size(); j++) {
			int db = 0;
			for (Országok adat : adatok) {
				if (évSzámok.get(j).equals(adat.getDátum().substring(0, 4))) {
					db = db + 1;
				}
			}
			System.out.println("\t" + évSzámok.get(j) + " - " + db + " ország");
		}
	}
}
