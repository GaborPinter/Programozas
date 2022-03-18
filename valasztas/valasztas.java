package valasztas;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.Scanner;

public class valasztas {

	public static void main(String[] args) throws IOException {
		
		// 1.feladat
		List<String> sorok = Files.readAllLines(Path.of("szavazatok.txt"), StandardCharsets.UTF_8);
		ArrayList<Jelöltek> adatok = new ArrayList<>();
		for (int i = 0; i < sorok.size(); i++) {
			String[] DB = sorok.get(i).split(" ");
			adatok.add(new Jelöltek(Integer.parseInt(DB[0]), Integer.parseInt(DB[1]), DB[2], DB[3], DB[4]));
		}
		
		// ellenőrzés
		for (Jelöltek adat : adatok) {
			System.out.println(adat);
		}
		
		
		// 2.feladat
		System.out.println("2.feladat:\nA helyhatósági választáson " + adatok.size() + " képviselőjelölt indult.");

		// 3.feladat
		System.out.println("3.feladat:");
		Scanner input = new Scanner(System.in);
		System.out.print("Adjon meg egy vezeték nevet: ");
		String megadottVezetékNév = input.nextLine();
		System.out.print("Adjon meg egy kereszt nevet: ");
		String megadottKeresztNév = input.nextLine();
		boolean van = false;
		for (Jelöltek adat : adatok) {
			if (adat.vezetékNév.equals(megadottVezetékNév) && adat.keresztNév.equals(megadottKeresztNév)) {
				System.out.println(
						megadottVezetékNév + " " + megadottKeresztNév + " " + adat.szavazatok + " szavazatot kapott");
				van = true;
			}
		}
		if (van == false) {
			System.out.println("Ilyen nevű képviselőjelölt nem szerepel a nyilvántartásban!");
		}

		// 4.feladat
		int osszes = 12345;
		int leadott = 0;
		for (Jelöltek adat : adatok) {
			leadott = leadott + adat.szavazatok;
		}
		double szazalek = (double) leadott * 100 / osszes;
		System.out.println(
				String.format("4.feladat:\nA választáson " + leadott + " állampolgár, a jogosultak %.2f", szazalek)
						+ "%-a vett részt.");

		// 5.feladat
		HashMap<String, Integer> pártok = new HashMap<String, Integer>();
		for (Jelöltek adat : adatok) {
			String nev = "";
			if (adat.párt.equals("GYEP")) {
				nev = "Gyümölcsevők Pártja";
			}
			if (adat.párt.equals("HEP")) {
				nev = "Húsevők Pártja";
			}
			if (adat.párt.equals("TISZ")) {
				nev = "Tejivók Szövetsége";
			}
			if (adat.párt.equals("ZEP")) {
				nev = "Zöldségevők Pártja";
			}
			if (adat.párt.equals("-")) {
				nev = "Független jelöltek";
			}
			pártok.put(nev, pártok.getOrDefault(nev, 0) + adat.szavazatok);

		}
		System.out.println("5.feladat:");
		double arany = 0;
		for (Entry<String, Integer> párt : pártok.entrySet()) {
			arany = (double) párt.getValue() * 100 / leadott;
			System.out.println(String.format("%s= %.2f", párt.getKey(), arany) + "%");
		}

		// 6.feladat
		int max = 0;
		for (Jelöltek adat : adatok) {
			if (max < adat.szavazatok) {
				max = adat.szavazatok;
			}
		}
		System.out.println("6.feladat:");
		for (Jelöltek adat : adatok) {
			if (max == adat.szavazatok) {
				System.out.println(adat.vezetékNév + " " + adat.keresztNév + " "
						+ (adat.párt.equals("-") ? "független" : adat.párt));
			}
		}

		// 7.feladat
		String text = "kepviselok.txt";
		try {
			File fájl = new File(text);
			fájl.delete();
			fájl.createNewFile();
		} catch (IOException e) {
		}
		ArrayList<Integer> kerületek = new ArrayList<Integer>();
		kerületek.sort(Comparator.naturalOrder());
		for (Jelöltek adat : adatok) {
			if (!kerületek.contains(adat.választókerület)) {
				kerületek.add(adat.választókerület);
			}
		}
		try {
			File fájl = new File(text);
			PrintWriter PW = new PrintWriter(fájl);
			for (int i = 0; i < kerületek.size(); i++) {
				int legtobb = 0;
				for (Jelöltek adat : adatok) {
					if (adat.választókerület == kerületek.get(i)) {
						if (adat.szavazatok > legtobb) {
							legtobb = adat.szavazatok;
						}
					}
				}
				for (Jelöltek adat : adatok) {
					if (adat.szavazatok == legtobb && adat.választókerület == kerületek.get(i)) {
						PW.println((i + 1) + ". választókerület " + adat.vezetékNév + " " + adat.keresztNév + " "
								+ (adat.párt.equals("-") ? "független" : adat.párt));
					}
				}
			}
			PW.close();
		} catch (IOException e) {
		}

	}

	public static class Jelöltek {
		private int választókerület;
		private int szavazatok;
		private String vezetékNév;
		private String keresztNév;
		private String párt;

		public Jelöltek(int választókerület, int szavazatok, String vezetékNév, String keresztNév, String párt) {
			this.választókerület = választókerület;
			this.szavazatok = szavazatok;
			this.vezetékNév = vezetékNév;
			this.keresztNév = keresztNév;
			this.párt = párt;
		}

		@Override
		public String toString() {
			return "Jelöltek [választókerület=" + választókerület + ", szavazatok=" + szavazatok + ", vezetékNév="
					+ vezetékNév + ", keresztNév=" + keresztNév + ", párt=" + párt + "]";
		}
	}
}
