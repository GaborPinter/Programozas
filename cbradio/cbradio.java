package cbradio;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class cbradio {

	public static void main(String[] args) throws IOException {
		// 2.feladat
		List<String> sorok = Files.readAllLines(Path.of("cb.txt"), StandardCharsets.UTF_8);
		ArrayList<Ertekek> adatok = new ArrayList<Ertekek>();
		for (int i = 1; i < sorok.size(); i++) {
			adatok.add(new Ertekek(sorok.get(i)));
		}

		// ellenőrzés
		/*
		 * for (Ertekek adat : adatok) { System.out.println(adat); }
		 */

		// 3.feladat
		System.out.println("3. feladat: Bejegyzések száma: " + adatok.size() + " db");

		// 4.feladat
		for (Ertekek adat : adatok) {
			if (adat.adasSzam == 4) {
				System.out.println("4. feladat: Volt négy adást indító sofőr.");
				break;
			}
		}

		// 5.feladat
		System.out.print("5. feladat: Kérek egy nevet: ");
		Scanner input = new Scanner(System.in);
		String megadottSofor = input.nextLine();
		int db = 0;
		for (Ertekek adat : adatok) {
			if (megadottSofor.equals(adat.becenev)) {
				db = db + adat.adasSzam;
			}
		}
		if (db > 0) {
			System.out.println("\t" + megadottSofor + " " + db + "x használta a CB-rádiót.");
		} else {
			System.out.println("\tNincs ilyen nevű sofőr!");
		}

		// 6.feladat-ellenőrzés
		/*
		 * int szam=AtszamolPercre(8, 5); System.out.println(szam);
		 */

		// 7.feladat
		String text = "cb2.txt";
		try {
			File fájl = new File(text);
			fájl.delete();
			fájl.createNewFile();
		} catch (IOException e) {
		}
		try {
			File fájl = new File(text);
			PrintWriter PW = new PrintWriter(fájl);
			PW.println("Kezdes;Nev;AdasDb");
			for (Ertekek adat : adatok) {
				PW.println(AtszamolPercre(adat.ora, adat.perc) + ";" + adat.becenev + ";" + adat.adasSzam);
			}
			PW.close();
		} catch (IOException e) {
		}

		// 8.feladat
		ArrayList<String> soforok = new ArrayList<String>();
		for (Ertekek adat : adatok) {
			if (!soforok.contains(adat.becenev)) {
				soforok.add(adat.becenev);
			}
		}
		System.out.println("8. feladat: Sofőrök száma: " + soforok.size() + " fő");

		// 9.feladat
		int legtobbAdas = 0;
		String legtobbetAdoNeve = "";
		for (int i = 0; i < soforok.size(); i++) {
			int adas = 0;
			String nev = "";
			for (Ertekek adat : adatok) {
				if (soforok.get(i).equals(adat.becenev)) {
					adas = adas + adat.adasSzam;
					nev = adat.becenev;
				}
			}
			if (legtobbAdas < adas) {
				legtobbAdas = adas;
				legtobbetAdoNeve = nev;
			}
		}
		System.out.println("9. feladat: Legtöbb adást indító sofőr\n\tNév: " + legtobbetAdoNeve + "\n\tAdások száma: "
				+ legtobbAdas + " alkalom");

	}

	public static class Ertekek {

		private int ora;
		private int perc;
		private int adasSzam;
		private String becenev;

		public Ertekek(String sor) {
			String[] DB = sor.split(";");
			ora = Integer.parseInt(DB[0]);
			perc = Integer.parseInt(DB[1]);
			adasSzam = Integer.parseInt(DB[2]);
			becenev = DB[3];
		}

		@Override
		public String toString() {
			return "Ertekek [ora=" + ora + ", perc=" + perc + ", adasSzam=" + adasSzam + ", becenev=" + becenev + "]";
		}
	}

	// 6.feladat
	public static int AtszamolPercre(int ora, int perc) {
		int osszeg = ora * 60 + perc;
		return osszeg;
	}
}
