package hianyzasok;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.Scanner;

public class hianyzasok {

	public static void main(String[] args) throws IOException {
		// 1.feladat
		List<String> sorok = Files.readAllLines(Path.of("naplo.txt"), StandardCharsets.UTF_8);
		ArrayList<Jegyzet> adatok = new ArrayList<Jegyzet>();
		int[] HónapNap = new int[2];
		for (int i = 0; i < sorok.size(); i++) {
			if (sorok.get(i).startsWith("#")) {
				String[] DB = sorok.get(i).split(" ");
				HónapNap[0] = Integer.parseInt(DB[1]);
				HónapNap[1] = Integer.parseInt(DB[2]);
			} else {
				String[] DB = sorok.get(i).split(" ");
				adatok.add(new Jegyzet(HónapNap[0], HónapNap[1], DB[0], DB[1], DB[2]));
			}
		}

		// ellenőrzés
		/*
		 * for (Jegyzet adat : adatok) { System.out.println(adat); }
		 */

		// 2.feladat
		System.out.println("2. feladat\nA naplóban " + adatok.size() + " bejegyzés van.");

		// 3.feladat
		int ig = 0;
		int igl = 0;
		for (Jegyzet adat : adatok) {
			for (int i = 0; i < adat.orak.length(); i++) {
				if (adat.orak.charAt(i) == 'X') {
					ig++;
				}
				if (adat.orak.charAt(i) == 'I') {
					igl++;
				}
			}
		}
		System.out.println("3. feladat\nAz igazolt hiányzások száma " + ig + ", az igazolatlanoké " + igl + " óra.");

		// 5.feladat
		System.out.print("5. feladat\nA hónap sorszáma=");
		Scanner input = new Scanner(System.in);
		int megadottHónap = input.nextInt();
		System.out.print("A nap sorszáma=");
		int megadottNap = input.nextInt();
		System.out.println("Azon a napon " + hetnapja(megadottHónap, megadottNap) + " volt.");

		// 6.feladat
		System.out.print("6. feladat\nA nap neve=");
		String megadottNapNév = input.next();
		System.out.print("Az óra sorszáma=");
		int megadottÓraSorszám = input.nextInt() - 1;
		int o = 0;
		for (Jegyzet adat : adatok) {
			if (megadottNapNév.equals(hetnapja(adat.hónap, adat.nap)) && adat.orak.charAt(megadottÓraSorszám) == 'X'
					|| adat.orak.charAt(megadottÓraSorszám) == 'I') {
				o++;
			}
		}
		System.out.println("Ekkor összesen " + o + " óra hiányzás történt.");

		// 7.feladat
		HashMap<String, Integer> tanulok = new HashMap<String, Integer>();
		for (int i = 0; i < adatok.size(); i++) {
			int hianyzas = 0;
			String nev = adatok.get(i).vezetékNév + " " + adatok.get(i).keresztNév;
			for (int j = 0; j < adatok.get(i).orak.length(); j++) {
				if (adatok.get(i).orak.charAt(j) == 'X' || adatok.get(i).orak.charAt(j) == 'I') {
					hianyzas++;
				}
			}
			tanulok.put(nev, tanulok.getOrDefault(nev, 0) + hianyzas);
		}
		int max = 0;
		for (Integer tanulo : tanulok.values()) {
			if (max < tanulo) {
				max = tanulo;
			}
		}
		System.out.print("7. feladat\nA legtöbbet hiányzó tanulók: ");
		for (Entry<String, Integer> t : tanulok.entrySet()) {
			if (max == t.getValue()) {
				System.out.print(t.getKey() + " ");
			}
		}
	}

	public static class Jegyzet {
		private int hónap;
		private int nap;
		private String vezetékNév;
		private String keresztNév;
		private String orak;

		public Jegyzet(int hónap, int nap, String vezetékNév, String keresztNév, String orak) {
			this.hónap = hónap;
			this.nap = nap;
			this.vezetékNév = vezetékNév;
			this.keresztNév = keresztNév;
			this.orak = orak;
		}

		@Override
		public String toString() {
			return "Jegyzet [hónap=" + hónap + ", nap=" + nap + ", vezetékNév=" + vezetékNév + ", keresztNév="
					+ keresztNév + ", orak=" + orak + "]";
		}
	}

	// 4.feladat
	public static String hetnapja(int honap, int nap) {
		String[] napnev = { "vasarnap", "hetfo", "kedd", "szerda", "csutortok", "pentek", "szombat" };
		int[] napszam = { 0, 31, 59, 90, 120, 151, 181, 212, 243, 273, 304, 335 };
		int napsorszam = (napszam[honap - 1] + nap) % 7;
		String hetnapja = napnev[napsorszam];
		return hetnapja;
	}
}
