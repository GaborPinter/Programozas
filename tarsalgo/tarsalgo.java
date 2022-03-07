package tarsalgo;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.Scanner;

public class tarsalgo {

	public static void main(String[] args) throws IOException {
		// 1.feladat
		List<String> sorok = Files.readAllLines(Path.of("ajto.txt"), StandardCharsets.UTF_8);
		ArrayList<Információk> adatok = new ArrayList<Információk>();
		for (int i = 0; i < sorok.size(); i++) {
			adatok.add(new Információk(sorok, i));
		}

		// ellenőrzés
		/*
		 * for (Információk adat : adatok) { System.out.println(adat); }
		 */

		// 2.feladat
		for (int i = 0; i < adatok.size(); i++) {
			if (adatok.get(i).irany.equals("be")) {
				System.out.println("2. feladat\nAz első belépő: " + adatok.get(i).azonosito);
				break;
			}
		}
		for (int i = adatok.size() - 1; i > 0; i--) {
			if (adatok.get(i).irany.equals("ki")) {
				System.out.println("Az utolsó kilépő: " + adatok.get(i).azonosito);
				break;
			}
		}

		// 3.feladat
		ArrayList<Integer> azonositok = new ArrayList<Integer>();
		for (Információk adat : adatok) {
			if (!azonositok.contains(adat.azonosito)) {
				azonositok.add(adat.azonosito);
			}
		}
		azonositok.sort(Comparator.naturalOrder());
		String text = "athaladas.txt";
		try {
			File fájl = new File(text);
			fájl.delete();
			fájl.createNewFile();
		} catch (IOException e) {
		}
		try {
			File fájl = new File(text);
			PrintWriter PW = new PrintWriter(fájl);
			for (int i = 0; i < azonositok.size(); i++) {
				int db = 0;
				for (int j = 0; j < adatok.size(); j++) {
					if (azonositok.get(i) == adatok.get(j).azonosito) {
						db++;
					}
				}
				PW.println(azonositok.get(i) + " " + db);
			}
			PW.close();
		} catch (IOException e) {
		}

		// 4.feladat
		System.out.print("\n4. feladat\nA végén a társalgóban voltak: ");
		for (int i = 0; i < azonositok.size(); i++) {
			int b = 0;
			for (int j = 0; j < adatok.size(); j++) {
				if (azonositok.get(i) == adatok.get(j).azonosito && adatok.get(j).irany.equals("be")) {
					b++;
				}
				if (azonositok.get(i) == adatok.get(j).azonosito && adatok.get(j).irany.equals("ki")) {
					b--;
				}
			}
			if (b > 0) {
				System.out.print(azonositok.get(i) + " ");
			}
		}

		// 5.feladat
		HashMap<LocalTime, Integer> EppenBent = new HashMap<LocalTime, Integer>();
		int db = 0;
		int legtobb = 0;
		for (int i = 0; i < adatok.size(); i++) {
			LocalTime idopont = LocalTime.of(adatok.get(i).ora, adatok.get(i).perc);
			if (adatok.get(i).irany.equals("be")) {
				db++;
			} else {
				db--;
			}
			EppenBent.put(idopont, db);
		}
		LocalTime ido = null;
		for (Integer EppBent : EppenBent.values()) {
			if (EppBent > legtobb) {
				legtobb = EppBent;
			}
		}
		for (Entry<LocalTime, Integer> E : EppenBent.entrySet()) {
			if (E.getValue() == legtobb) {
				ido = E.getKey();
			}
		}
		System.out.println("\n\n5. feladat\nPéldául " + ido + "-kor voltak a legtöbben a társalgóban.");

		// 6.feladat
		System.out.print("\n6. feladat\nAdja meg a személy azonosítóját! ");
		Scanner input = new Scanner(System.in);
		int megadottAzonosito = input.nextInt();

		// 7.feladat
		ArrayList<String> idok = new ArrayList<String>();
		System.out.println("\n7. feladat");
		for (int i = 0; i < adatok.size(); i++) {
			if (megadottAzonosito == adatok.get(i).azonosito) {
				idok.add(adatok.get(i).ora + ":" + adatok.get(i).perc);
			}
		}
		for (int i = 0; i < idok.size() - 1; i = i + 2) {
			System.out.println(idok.get(i) + "-" + idok.get(i + 1));
		}
		if (idok.size() % 2 != 0) {
			System.out.println(idok.get(idok.size() - 1) + "-");
		}

		// 8.feladat
		String bentvolt = "";
		for (int i = adatok.size() - 1; i > 0; i--) {
			if (megadottAzonosito == adatok.get(i).azonosito) {
				bentvolt = adatok.get(i).irany;
				break;
			}
		}
		int osszesido = 0;
		for (int i = 0; i < idok.size() - 1; i = i + 2) {
			int ora = LocalTime
					.of(Integer.valueOf(idok.get(i + 1).substring(0, 2)),
							Integer.valueOf(idok.get(i + 1).substring(3, 5)))
					.getHour()
					- LocalTime.of(Integer.valueOf(idok.get(i).substring(0, 2)),
							Integer.valueOf(idok.get(i).substring(3, 5))).getHour();
			int perc = LocalTime
					.of(Integer.valueOf(idok.get(i + 1).substring(0, 2)),
							Integer.valueOf(idok.get(i + 1).substring(3, 5)))
					.getMinute()
					- LocalTime.of(Integer.valueOf(idok.get(i).substring(0, 2)),
							Integer.valueOf(idok.get(i).substring(3, 5))).getMinute();
			osszesido = osszesido + ora * 60 + perc;
		}
		LocalTime vég = LocalTime.of(15, 00);
		if (idok.size() % 2 != 0) {
			int ora = vég.getHour() - LocalTime.of(Integer.valueOf(idok.get(idok.size() - 1).substring(0, 2)),
					Integer.valueOf(idok.get(idok.size() - 1).substring(3, 5))).getHour();
			int perc = vég.getMinute() - LocalTime.of(Integer.valueOf(idok.get(idok.size() - 1).substring(0, 2)),
					Integer.valueOf(idok.get(idok.size() - 1).substring(3, 5))).getMinute();
			osszesido = osszesido + ora * 60 + perc;
		}
		System.out.println("\n8. feladat\nA(z) " + megadottAzonosito + ". személy összesen " + osszesido
				+ " percet volt bent, a megfigyelés végén a társalgóban "
				+ (bentvolt.equals("be") ? "volt." : "nem volt."));
	}

	public static class Információk {
		private int ora;
		private int perc;
		private int azonosito;
		private String irany;

		public Információk(List<String> sorok, int i) {
			String[] DB = sorok.get(i).split(" ");
			this.ora = Integer.parseInt(DB[0]);
			this.perc = Integer.parseInt(DB[1]);
			this.azonosito = Integer.parseInt(DB[2]);
			this.irany = DB[3];
		}

		@Override
		public String toString() {
			return "Információk [ora=" + ora + ", perc=" + perc + ", azonosito=" + azonosito + ", irany=" + irany + "]";
		}
	}
}
