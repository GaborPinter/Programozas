import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

public class Snooker {

	public static void main(String[] args) throws IOException {
		// 2.feladat
		List<String> sorok = Files.readAllLines(Path.of("snooker.txt"), StandardCharsets.UTF_8);
		ArrayList<Infók> adatok = new ArrayList<Infók>();
		for (int i = 1; i < sorok.size(); i++) {
			String[] DB = sorok.get(i).split(";");
			adatok.add(new Infók(Integer.parseInt(DB[0]), DB[1], DB[2], Integer.parseInt(DB[3])));
		}

		// ellenőrzés
		/*
		 * for (Infók adat : adatok) { System.out.println(adat); }
		 */

		// 3.feladat
		System.out.println("3. feladat: A világranglistán " + adatok.size() + " versenyző szerepel");

		// 4.feladat
		int osszeg = 0;
		for (Infók adat : adatok) {
			osszeg = osszeg + adat.nyeremeny;
		}
		double kereset = (double) osszeg / adatok.size();
		System.out.println(String.format("4. feladat: A versenyzők átlagosan %.2f fontot kerestek", kereset));

		// 5.feladat
		int max = 0;
		String ertek = "";
		for (Infók adat : adatok) {
			if (adat.nyeremeny > max && adat.orszag.equals("Kína")) {
				max = adat.nyeremeny;
				ertek = String.valueOf(adat.nyeremeny * 380);
			}
		}
		String joertek = "";
		for (int i = 0; i < ertek.length(); i++) {
			if (i % 3 == 2) {
				joertek = joertek + ertek.charAt(i) + " ";
			} else {
				joertek = joertek + ertek.charAt(i);
			}
		}
		for (Infók adat : adatok) {
			if (max == adat.nyeremeny) {
				System.out.println(
						"5. feladat: A legjobban kereső kínai versenyző:\n\tHelyezés: " + adat.helyezes + "\n\tNév: "
								+ adat.nev + "\n\tOrszág: " + adat.orszag + "\n\tNyeremény összege: " + joertek + "Ft"); // ha
																															// szükséges
																															// hogy
																															// 3számonként
																															// egy
																															// szóköz
																															// szerepeljen
			} // különben elég csak az adat.nyeremény*380at beírni
		}

		// 6.feladat
		boolean van = false;
		for (Infók adat : adatok) {
			if (adat.orszag.equals("Norvégia")) {
				van = true;
			}
		}
		System.out
				.println("6. feladat: A versenyzők között " + (van == true ? "van " : "nincs ") + "norvég versenyző.");

		// 7.feladat
		System.out.println("7. feladat: Statisztika");
		HashMap<String, Integer> stat = new HashMap<String, Integer>();
		for (Infók adat : adatok) {
			stat.put(adat.orszag, stat.getOrDefault(adat.orszag, 0) + 1);
		}
		for (Entry<String, Integer> s : stat.entrySet()) {
			if (s.getValue() > 4) {
				System.out.println("\t" + s.getKey() + " - " + s.getValue() + " fő");
			}
		}
	}

	public static class Infók {
		private int helyezes;
		private String nev;
		private String orszag;
		private int nyeremeny;

		public Infók(int helyezes, String nev, String orszag, int nyeremeny) {
			this.helyezes = helyezes;
			this.nev = nev;
			this.orszag = orszag;
			this.nyeremeny = nyeremeny;
		}

		@Override
		public String toString() {
			return "Infók [helyezes=" + helyezes + ", nev=" + nev + ", orszag=" + orszag + ", nyeremeny=" + nyeremeny
					+ "]";
		}
	}
}
