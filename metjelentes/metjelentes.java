package metjelentes;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map.Entry;
import java.util.Scanner;

public class metjelentes {

	public static void main(String[] args) {
		// 1.feladat
		ArrayList<Tulajdonsag> adatok = new ArrayList<Tulajdonsag>();
		try {
			System.setProperty("file.encoding", "UTF-8");
			BufferedReader BR = new BufferedReader(new FileReader("tavirathu13.txt"));
			String sor = "";
			while ((sor = BR.readLine()) != null) {
				String[] DB = sor.split(" ");
				adatok.add(new Tulajdonsag(DB[0],
						LocalTime.of(Integer.valueOf(DB[1].substring(0, 2)), Integer.valueOf(DB[1].substring(2, 4))),
						DB[2], Integer.parseInt(DB[3])));
			}
		} catch (IOException e) {
		}

		// Ellenőrzés
		/*
		 * for (Tulajdonsag adat : adatok) { System.out.println(adat); }
		 */

		// 2.feladat
		Scanner input = new Scanner(System.in);
		System.out.print("2. feladat\nAdja meg egy település kódját! Település: ");
		String megadottVarosKod = input.nextLine();
		LocalTime utolso = LocalTime.of(0, 0);
		for (Tulajdonsag adat : adatok) {
			if (megadottVarosKod.equals(adat.getSzoveg())) {
				if (adat.getIdo().isAfter(utolso)) {
					utolso = adat.getIdo();
				}
			}
		}
		System.out.println("Az utolsó mérési adat a megadott településről " + utolso + "-kor érkezett.");

		// 3.feladat
		System.out.println("3. feladat");
		int maxHomerseklet = 0;
		int minHomerseklet = adatok.get(0).getHomerseklet();
		for (Tulajdonsag adat : adatok) {
			if (adat.getHomerseklet() > maxHomerseklet) {
				maxHomerseklet = adat.getHomerseklet();
			}
			if (adat.getHomerseklet() < minHomerseklet) {
				minHomerseklet = adat.getHomerseklet();
			}
		}
		for (Tulajdonsag adat : adatok) {
			if (adat.getHomerseklet() == minHomerseklet) {
				System.out.println("A legalacsonyabb hőmérséklet: " + adat.getSzoveg() + " " + adat.getIdo() + " "
						+ adat.getHomerseklet() + " fok.");
				break;
			}
		}
		for (Tulajdonsag adat : adatok) {
			if (adat.getHomerseklet() == maxHomerseklet) {
				System.out.println("A legmagasabb hőmérséklet: " + adat.getSzoveg() + " " + adat.getIdo() + " "
						+ adat.getHomerseklet() + " fok.");
				break;
			}
		}

		// 4.feladat
		System.out.println("4. feladat");
		boolean vanAdat = false;
		for (Tulajdonsag adat : adatok) {
			if (adat.getSzeliranyEsErosseg().equals("00000")) {
				System.out.println(adat.getSzoveg() + " " + adat.getIdo());
				vanAdat = true;
			}
		}
		if (vanAdat == false) {
			System.out.println("Nem volt szélcsend a mérések idején.");
		}

		// 5.feladat
		System.out.println("5. feladat");
		HashMap<String, ArrayList<Tulajdonsag>> telepulesAdatok = new HashMap<String, ArrayList<Tulajdonsag>>();
		for (Tulajdonsag adat : adatok) {
			String varosNev = adat.getSzoveg(); // ez a kulcsunk
			ArrayList<Tulajdonsag> telepulesAdatai = telepulesAdatok.get(varosNev); // ez az ertek
			if (telepulesAdatai == null) {
				telepulesAdatai = new ArrayList<>();
				telepulesAdatok.put(varosNev, telepulesAdatai);
			}
			telepulesAdatai.add(adat);
		}
		for (Entry<String, ArrayList<Tulajdonsag>> telepulesAdat : telepulesAdatok.entrySet()) {
			String telepules = telepulesAdat.getKey(); // kulcs
			ArrayList<Tulajdonsag> telepuleshezTartozoAdatok = telepulesAdat.getValue(); // ertek
			HashSet<Integer> egyediAdattalRendelkezoOrak = new HashSet<Integer>();
			int min = adatok.get(0).getHomerseklet();
			int max = 0;
			double osszeg = 0;
			int szam = 0;

			for (Tulajdonsag adat : telepuleshezTartozoAdatok) {
				if (adat.getHomerseklet() < min) {
					min = adat.getHomerseklet();
				}
				if (adat.getHomerseklet() > max) {
					max = adat.getHomerseklet();
				}

				osszeg = osszeg + adat.getHomerseklet();
				szam++;
				egyediAdattalRendelkezoOrak.add(adat.getIdo().getHour());
			}

			if (egyediAdattalRendelkezoOrak.size() == 24) { // azert 24 mert akkor minden orara volt adatunk
				double kozep = ((double) osszeg) / szam;
				int kerekitettKozep = (int) Math.ceil(kozep);

				System.out.println(telepules + " Középhőmérséklet: " + kerekitettKozep + "; Hőmérséklet-ingadozás: "
						+ (max - min));
			} else {
				System.out.println(telepules + " NA; Hőmérséklet-ingadozás: " + (max - min));
			}
		}

		// 6.feladat
		ArrayList<String> Telepules = new ArrayList<String>();
		for (Tulajdonsag adat : adatok) {
			if (!Telepules.contains(adat.getSzoveg())) {
				Telepules.add(adat.getSzoveg());
			}
		}
		for (int i = 0; i < Telepules.size(); i++) {
			String text = Telepules.get(i) + ".txt";
			try {
				File fájl = new File(text);
				fájl.delete();
				fájl.createNewFile();
			} catch (IOException e) {
			}
			try {
				File fájl = new File(text);
				PrintWriter PW = new PrintWriter(fájl);
				PW.println(Telepules.get(i));
				for (Tulajdonsag adat : adatok) {
					if (adat.getSzoveg().equals(Telepules.get(i))) {
						PW.print(adat.getIdo() + " ");
						for (int j = 0; j < Integer.valueOf(adat.getSzeliranyEsErosseg().substring(3, 5)); j++) {
							PW.print("#");
						}
						PW.print("\n");
					}
				}
				PW.close();
			} catch (IOException e) {
			}
		}
		System.out.println("6. feladat\nA fájlok elkészültek.");

	}
}
