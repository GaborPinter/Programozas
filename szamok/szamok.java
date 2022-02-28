package szamok;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class szamok {

	public static void main(String[] args) throws IOException {
		// 1.feladat
		List<String> sorok = Files.readAllLines(Path.of("felszam.txt"), StandardCharsets.UTF_8);
		ArrayList<Válaszok> adatok = new ArrayList<Válaszok>();
		for (int i = 0; i < sorok.size(); i = i + 2) {
			adatok.add(new Válaszok(sorok, i));
		}

		// ellenőrzés
		for (Válaszok adat : adatok) {
			System.out.println(adat);
		}

		// 2.feladat
		System.out.println("2. feladat: Feladatok száma: " + adatok.size());

		// 3.feladat
		int m = 0;
		int egy = 0;
		int ketto = 0;
		int harom = 0;
		for (Válaszok adat : adatok) {
			if (adat.getTémakör().equals("matematika")) {
				m++;
				if (adat.getPontszám() == 1) {
					egy++;
				}
				if (adat.getPontszám() == 2) {
					ketto++;
				}
				if (adat.getPontszám() == 3) {
					harom++;
				}
			}
		}
		System.out.println("3. feladat: Az adatfajlban " + m + " matematika feladat van, 1 pontot er " + egy
				+ " feladat, 2 pontot er " + ketto + " feladat, 3 pontot er " + harom + " feladat.");

		// 4.feladat
		int min = adatok.get(0).getMegoldás();
		int max = 0;
		for (Válaszok adat : adatok) {
			if (adat.getMegoldás() > max) {
				max = adat.getMegoldás();
			}
			if (adat.getMegoldás() < min) {
				min = adat.getMegoldás();
			}
		}
		System.out
				.println("4. feladat: A fájlban található válaszok számértéke " + min + "-től " + max + "-ig terjed.");

		// 5.feladat
		HashSet<String> témakörök = new HashSet<String>();
		for (Válaszok adat : adatok) {
			témakörök.add(adat.getTémakör());
		}
		System.out.println("5. feladat: A témakörök az adatfájlban: " + témakörök);

		// 6.feladat
		System.out.print("6. feladat: Milyen temakorbol szeretne kerdest kapni? ");
		Scanner input = new Scanner(System.in);
		String témakörNév = input.nextLine();
		ArrayList<String> kérdésekTémakörSzerint = new ArrayList<String>();
		for (Válaszok adat : adatok) {
			if (adat.getTémakör().equals(témakörNév)) {
				kérdésekTémakörSzerint.add(adat.getKérdés());
			}
		}
		Random rand = new Random();
		int szam = rand.nextInt(kérdésekTémakörSzerint.size());
		System.out.print(kérdésekTémakörSzerint.get(szam) + " ");
		int válasz = input.nextInt();
		for (Válaszok adat : adatok) {
			if (kérdésekTémakörSzerint.get(szam).equals(adat.getKérdés())) {
				if (válasz == adat.getMegoldás()) {
					System.out.println("A válasz " + adat.getPontszám() + " pontot ér.");
				} else {
					System.out.println("A valasz 0 pontot er.\nA helyes valasz: " + adat.getMegoldás());
				}
			}
		}

		// 7.feladat
		String text = "tesztfel.txt";
		try {
			File fájl = new File(text);
			fájl.delete();
			fájl.createNewFile();
		} catch (IOException e) {
		}
		int ossz = 0;
		ArrayList<Integer> szamok = new ArrayList<Integer>();
		for (int i = 0; i < 10; i++) {
			int sorszam = rand.nextInt(adatok.size());
			if (!szamok.contains(sorszam)) {
				szamok.add(sorszam);
			} else {
				i--;
			}
		}
		try {
			File fájl = new File(text);
			PrintWriter PW = new PrintWriter(fájl);
			for (int i = 0; i < szamok.size(); i++) {
				PW.println(adatok.get(szamok.get(i)).getPontszám() + " " + adatok.get(szamok.get(i)).getMegoldás() + " "
						+ adatok.get(szamok.get(i)).getKérdés());
				ossz = ossz + adatok.get(szamok.get(i)).getPontszám();
			}
			PW.println("A feladatsorra osszesen " + ossz + " pont adhato.");
			PW.close();
		} catch (IOException e) {
		}

	}
}
