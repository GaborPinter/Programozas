package tanciskola;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map.Entry;
import java.util.Scanner;

public class tanciskola {

	public static void main(String[] args) throws IOException {
		// 1.feladat
		List<String> sorok = Files.readAllLines(Path.of("tancrend.txt"), StandardCharsets.UTF_8);
		ArrayList<TancEsTancos> adatok = new ArrayList<TancEsTancos>();
		for (int i = 0; i < sorok.size(); i = i + 3) {
			adatok.add(new TancEsTancos(sorok, i));
		}

		// ellenorzes
		/*
		 * for (TancEsTancos adat : adatok) { System.out.println(adat); }
		 */

		// 2.feladat
		System.out.println("2. feladat: \nElsőként bemutatott tánc: " + adatok.get(0).tanc
				+ "\nUtolsóként bemutatott tánc: " + adatok.get(adatok.size() - 1).getTanc());

		// 3.feladat
		int db = 0;
		for (TancEsTancos adat : adatok) {
			if (adat.getTanc().equals("samba")) {
				db++;
			}
		}
		System.out.println("3. feladat: A sambát " + db + " pár mutatta be.");

		// 4.feladat
		System.out.println("4. feladat: Táncok amelyekben Vilma szerepelt:");
		for (TancEsTancos adat : adatok) {
			if (adat.getLany().equals("Vilma")) {
				System.out.println(adat.getTanc());
			}
		}

		// 5.feladat
		System.out.print("5. feladat: Adja meg egy tánc nevét: ");
		Scanner input = new Scanner(System.in);
		String tanc = input.nextLine();
		boolean vanTanc = false;
		for (TancEsTancos adat : adatok) {
			if (adat.getTanc().equals(tanc) && adat.getLany().equals("Vilma")) {
				System.out.println("A " + tanc + " bemutatóján Vilma párja " + adat.getFiu() + " volt.");
				vanTanc = true;
			}
		}
		if (vanTanc == false) {
			System.out.println("Vilma nem táncolt " + tanc + "-t.");
		}

		// 6.feladat
		HashSet<String> lanyok = new HashSet<String>();
		for (TancEsTancos adat : adatok) {
			lanyok.add(adat.getLany());
		}
		HashSet<String> fiuk = new HashSet<String>();
		for (TancEsTancos adat : adatok) {
			fiuk.add(adat.getFiu());
		}
		String text = "szereplok.txt";
		try {
			File fájl = new File(text);
			fájl.delete();
			fájl.createNewFile();
		} catch (IOException e) {
		}
		try {
			File fájl = new File(text);
			PrintWriter PW = new PrintWriter(fájl);
			PW.println("Lányok: " + lanyok.toString().substring(1, lanyok.toString().length() - 1));
			PW.println("Fiúk: " + fiuk.toString().substring(1, fiuk.toString().length() - 1));
			PW.close();
		} catch (IOException e) {
		}

		// 7.feladat
		System.out.println("7. feladat:");
		HashMap<String, Integer> fiukTanc = new HashMap<String, Integer>();
		for (TancEsTancos adat : adatok) {
			fiukTanc.put(adat.getFiu(), fiukTanc.getOrDefault(adat.getFiu(), 0) + 1);
		}
		int fiuMax = 0;
		for (Integer fiuTanc : fiukTanc.values()) {
			if (fiuTanc > fiuMax) {
				fiuMax = fiuTanc;
			}
		}
		System.out.println("A legtöbbet táncolt fiú/fiúk: ");
		for (Entry<String, Integer> f : fiukTanc.entrySet()) {
			if (f.getValue() == fiuMax) {
				System.out.println("\t" + f.getKey());
			}
		}

		HashMap<String, Integer> lanyokTanc = new HashMap<String, Integer>();
		for (TancEsTancos adat : adatok) {
			lanyokTanc.put(adat.getLany(), lanyokTanc.getOrDefault(adat.getLany(), 0) + 1);
		}
		int lanyMax = 0;
		for (Integer lanyTanc : lanyokTanc.values()) {
			if (lanyTanc > lanyMax) {
				lanyMax = lanyTanc;
			}
		}
		System.out.println("A legtöbbet táncolt lány/lányok: ");
		for (Entry<String, Integer> l : lanyokTanc.entrySet()) {
			if (l.getValue() == lanyMax) {
				System.out.println("\t" + l.getKey());
			}
		}
	}

	public static class TancEsTancos {
		private String tanc;
		private String lany;
		private String fiu;

		public TancEsTancos(List<String> sorok, int i) {
			this.tanc = sorok.get(i);
			this.lany = sorok.get(i + 1);
			this.fiu = sorok.get(i + 2);
		}

		@Override
		public String toString() {
			return "TancEsTancos [tanc=" + tanc + ", lany=" + lany + ", fiu=" + fiu + "]";
		}

		public String getTanc() {
			return tanc;
		}

		public String getLany() {
			return lany;
		}

		public String getFiu() {
			return fiu;
		}

	}
}
