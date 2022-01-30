package fejvagyiras;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class fejvagyiras {

	public static void main(String[] args) throws IOException {
		List<String> sorok = Files.readAllLines(Path.of("kiserlet.txt"), StandardCharsets.UTF_8);
		// 1.feladat
		char[] elemek = { 'I', 'F' };
		Random rand = new Random();
		int randomSzam = rand.nextInt(elemek.length);
		System.out.println("1. feadat:\nA pénzfeldobás eredménye: " + elemek[randomSzam]);

		// 2.feladat
		System.out.print("2. feladat\nTippeljen! (F/I)= ");
		Scanner input = new Scanner(System.in);
		String tipp = input.nextLine();
		String ertek = String.valueOf(elemek[randomSzam]);
		if (tipp.equals(ertek)) {
			System.out.println("A tipp " + tipp + ", a dobás eredménye " + ertek + " volt.\nÖn eltalálta!");
		} else {
			System.out.println("A tipp " + tipp + ", a dobás eredménye " + ertek + " volt.\nÖn nem találta el!");
		}

		// 3.feladat
		System.out.println("3. feladat\nA kísérlet " + sorok.size() + " dobásból állt.");

		// 4.feladat
		int f = 0;
		for (String sor : sorok) {
			if (sor.equals("F")) {
				f++;
			}
		}
		double szazalek = (double) f * 100 / sorok.size();
		System.out.println(
				String.format("4. feladat\nA kísérlet során a fej relatív gyakorisága %.2f", szazalek) + "% volt.");

		// 5.feladat
		int a = 0;
		for (int i = 0; i < sorok.size() - 3; i++) {
			if (!sorok.get(i).equals("F") && sorok.get(i + 1).equals("F") && sorok.get(i + 2).equals("F")
					&& !sorok.get(i + 3).equals("F")) {
				a++;
			}
		}
		System.out.println("5. feladat\nA kísérlet során " + a + " alkalommal dobtak pontosan két fejet egymás után.");

		// 6.feladat
		int leghosszabb = 0;
		int szam = 1;
		int index = 0;
		for (int i = 0; i < sorok.size() - 1; i++) {
			if (sorok.get(i).equals("F") && sorok.get(i + 1).equals("F")) {
				szam++;
			}
			if (szam > leghosszabb) {
				leghosszabb = szam;
			}
			if (sorok.get(i).equals("F") && !sorok.get(i + 1).equals("F")) {
				szam = 1;
			}
			if (szam == leghosszabb) {
				int j = i;
				while (sorok.get(j).equals("F") && sorok.get(j - 1).equals("F")) {
					j--;
				}
				index = j;
			}
		}
		System.out.println("6. feladat\nA leghosszabb tisztafej sorozat " + leghosszabb + " tagból áll, kezdete a(z) "
				+ (index + 1) + ". dobás.");

		// 7.feladat
		String text = "dobasok.txt";
		try {
			File fájl = new File(text);
			fájl.delete();
			fájl.createNewFile();
		} catch (IOException e) {
		}
		ArrayList<String> negyes = new ArrayList<String>();
		for (int i = 0; i < 1000; i++) {
			String generalt = "";
			for (int j = 0; j < 4; j++) {
				int random = rand.nextInt(elemek.length);
				generalt = generalt + String.valueOf((elemek[random]));
			}
			negyes.add(generalt);
		}
		try {
			File fájl = new File(text);
			PrintWriter PW = new PrintWriter(fájl);
			int F = 0;
			int I = 0;
			for (int i = 0; i < negyes.size(); i++) {
				if (negyes.get(i).matches("FFFF")) {
					F++;
				}
				if (negyes.get(i).matches("FFFI")) {
					I++;
				}
			}
			PW.println("FFFF: " + F + ", FFFI: " + I);
			for (int i = 0; i < negyes.size(); i++) {
				PW.print(negyes.get(i) + " ");
			}
			PW.close();
		} catch (IOException e) {
		}
	}
}
