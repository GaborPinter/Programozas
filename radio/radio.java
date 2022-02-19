package radio;

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

public class radio {

	public static void main(String[] args) throws IOException {
		// 1.feladat
		List<String> sorok = Files.readAllLines(Path.of("veetel.txt"), StandardCharsets.UTF_8);
		ArrayList<Feljegyzések> adatok = new ArrayList<Feljegyzések>();
		for (int i = 0; i < sorok.size(); i = i + 2) {
			adatok.add(new Feljegyzések(sorok, i));
		}

		// ellenőrzés
		/*
		 * for (Feljegyzések adat : adatok) { System.out.println(adat); }
		 */

		// 2.feladat
		for (int i = 0; i < adatok.size(); i++) {
			if (i == 0) {
				System.out.println("2. feladat:\nAz első üzenet rögzítője: " + adatok.get(i).rádióamatőr);
			}
			if (i == adatok.size() - 1) {
				System.out.println("Az utolsó üzenet rögzítője: " + adatok.get(i).rádióamatőr);
			}
		}

		// 3.feladat
		System.out.println("\n3. feladat:");
		for (Feljegyzések adat : adatok) {
			if (adat.üzenet.contains("farkas")) {
				System.out.println(adat.nap + ". nap " + adat.rádióamatőr + ". rádióamatőr");
			}
		}

		// 4.feladat
		System.out.println("\n4. feladat:");
		HashMap<Integer, Integer> adások = new HashMap<Integer, Integer>();
		for (Feljegyzések adat : adatok) {
			adások.put(adat.nap, adások.getOrDefault(adat.nap, 0) + 1);
		}
		for (Entry<Integer, Integer> adás : adások.entrySet()) {
			System.out.println(adás.getKey() + ". nap: " + adás.getValue() + " rádióamatőr");
		}

		// 5.feladat
		String text = "adaas.txt";
		try {
			File fájl = new File(text);
			fájl.delete();
			fájl.createNewFile();
		} catch (IOException e) {
		}
		ArrayList<Integer> napok = new ArrayList<Integer>();
		for (Feljegyzések adat : adatok) {
			if (!napok.contains(adat.nap)) {
				napok.add(adat.nap);
			}
		}
		napok.sort(Comparator.naturalOrder());
		char[] jouzenet = new char[90];
		try {
			File fájl = new File(text);
			PrintWriter PW = new PrintWriter(fájl);
			for (int i = 0; i < napok.size(); i++) {
				for (Feljegyzések adat : adatok) {
					if (napok.get(i) == adat.nap) {
						PW.println(adat.nap + " " + adat.rádióamatőr);
						PW.println(adat.üzenet);
						for (int j = 0; j < adat.üzenet.length(); j++) {
							if (adat.üzenet.charAt(j) != '#') {
								jouzenet[j] = adat.üzenet.charAt(j);
							}
						}
					}
				}
				PW.print("\nA(z) " + (i + 1) + ". helyreállított üzenet: ");
				for (char c : jouzenet) {
					PW.print(c);
				}
				PW.println("\n");
			}
			PW.close();
		} catch (IOException e) {
		}

		// 7.feladat
		Scanner input = new Scanner(System.in);
		System.out.print("\n7. feladat:\nAdja meg a nap sorszámát! ");
		int megadottNap = input.nextInt();
		System.out.print("Adja meg a rádióamatőr sorszámát! ");
		int megadottRádióAmatőr = input.nextInt();
		int egyed = 0;
		int perjel = 0;
		int szokoz = 0;
		for (int i = 0; i < adatok.size(); i++) {
			if (adatok.get(i).nap == megadottNap && adatok.get(i).rádióamatőr == megadottRádióAmatőr) {
				for (int j = 0; j < adatok.get(i).üzenet.length(); j++) {
					if (adatok.get(i).üzenet.charAt(j) == '/') {
						perjel = j;
					}
					if (adatok.get(i).üzenet.charAt(j) == ' ') {
						szokoz = j;
						break;
					}
				}
				if (!adatok.get(i).üzenet.contains("/") || szame(adatok.get(i).üzenet.toCharArray())) {
					System.out.println("Nincs ilyen feljegyzés");
				}
				if (adatok.get(i).üzenet.substring(0, szokoz).contains("#")) {
					System.out.println("Nincs információ");
				}
				egyed = Integer.parseInt(adatok.get(i).üzenet.substring(0, perjel))
						+ Integer.valueOf(adatok.get(i).üzenet.substring(perjel + 1, szokoz));
			}
		}
		System.out.println("A megfigyelt egyedek száma: " + egyed);
	}

	public static class Feljegyzések {
		private int nap;
		private int rádióamatőr;
		private String üzenet;

		public Feljegyzések(List<String> sorok, int i) {
			String[] DB = sorok.get(i).split(" ");
			this.nap = Integer.parseInt(DB[0]);
			this.rádióamatőr = Integer.parseInt(DB[1]);
			this.üzenet = sorok.get(i + 1);
		}

		@Override
		public String toString() {
			return "Feljegyzések [nap=" + nap + ", rádióamatőr=" + rádióamatőr + ", üzenet=" + üzenet + "]";
		}
	}

	// 6.feladat
	public static boolean szame(char[] szo) {
		boolean valasz = true;
		for (int i = 1; i < szo.length; i++) {
			if (szo[i] < '0' || szo[i] > '9') {
				valasz = false;
			}
		}
		return valasz;
	}
}
