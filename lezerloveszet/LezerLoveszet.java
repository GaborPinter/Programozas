import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

public class LezerLoveszet {

	public static void main(String[] args) throws IOException {
		// 4.feladat
		List<String> sorok = Files.readAllLines(Path.of("lovesek.txt"), StandardCharsets.UTF_8);
		ArrayList<JatekosLovese> adatok = new ArrayList<JatekosLovese>();
		for (int i = 1; i < sorok.size(); i++) {
			adatok.add(new JatekosLovese(sorok.get(i), i));
		}

		// ellenőrzés
		/*
		 * for (JatekosLovese adat : adatok) { System.out.println(adat); }
		 */

		// 5.feladat
		System.out.println("5. feladat: Lövések száma: " + adatok.size() + " db");

		// 7.feladat
		String[] C = sorok.get(0).split(";");
		double celtablaX = Double.parseDouble(C[0].replace(",", "."));
		double celtablaY = Double.parseDouble(C[1].replace(",", "."));
		double legkozelebb = Integer.MAX_VALUE;
		for (JatekosLovese adat : adatok) {
			double ertek = adat.Tavolsag(celtablaX, celtablaY, adat.x, adat.y);
			if (ertek < legkozelebb) {
				legkozelebb = ertek;
			}
		}
		for (JatekosLovese adat : adatok) {
			if (adat.Tavolsag(celtablaX, celtablaY, adat.x, adat.y) == legkozelebb) {
				String x = String.valueOf(adat.x).replace(".", ";");
				String y = String.valueOf(adat.y).replace(".", ";");
				System.out.println("7. feladat: Legpontosabb lövés:\n\t" + adat.sorszam + ".; " + adat.nev + " x=" + x
						+ "; y=" + y + "; távolság: " + legkozelebb);
			}
		}

		// 9.feladat
		int db = 0;
		for (JatekosLovese adat : adatok) {
			if (adat.Pontszam(celtablaX, celtablaY, adat.x, adat.y) == 0) {
				db++;
			}
		}
		System.out.println("9. feladat: Nulla pontos lövések száma: " + db + " db");

		// 10.feladat
		ArrayList<String> nevek = new ArrayList<String>();
		for (JatekosLovese adat : adatok) {
			if (!nevek.contains(adat.nev)) {
				nevek.add(adat.nev);
			}
		}
		System.out.println("10. feladat: Játékosok száma: " + nevek.size());

		// 11.feladat
		System.out.println("11. feladat: Lövések száma");
		for (int i = 0; i < nevek.size(); i++) {
			int n = 0;
			for (JatekosLovese adat : adatok) {
				if (nevek.get(i).equals(adat.nev)) {
					n++;
				}
			}
			System.out.println("\t" + nevek.get(i) + " - " + n + " db");
		}

		// 12.feladat
		System.out.println("12. feladat: Átlagpontszámok");
		HashMap<String, Double> pontszamok = new HashMap<String, Double>();
		for (int i = 0; i < nevek.size(); i++) {
			double osszes = 0;
			int k = 0;
			for (JatekosLovese adat : adatok) {
				if (nevek.get(i).equals(adat.nev)) {
					osszes = osszes + adat.Pontszam(celtablaX, celtablaY, adat.x, adat.y);
					k++;
				}
			}
			pontszamok.put(nevek.get(i), osszes / k);
			if (i == 2) {
				System.out.println(
						"\t" + nevek.get(i) + " - " + String.valueOf(osszes / k).replace(".", ",").substring(0, 6));
			} else {
				System.out.println("\t" + nevek.get(i) + " - " + (String.valueOf(osszes / k).replace(".", ",")));
			}
		}

		// 13.feladat
		double max = 0;
		for (Entry<String, Double> p : pontszamok.entrySet()) {
			if (p.getValue() > max) {
				max = p.getValue();
			}
		}
		for (Entry<String, Double> p : pontszamok.entrySet()) {
			if (max == p.getValue()) {
				System.out.println("13. feladat: A játék nyertese: " + p.getKey());
			}
		}
	}

	// 2.feladat
	public static class JatekosLovese {
		private int sorszam;
		private String nev;
		private double x;
		private double y;

		// 3.feladat
		public JatekosLovese(String sor, int i) {
			String[] DB = sor.split(";");
			this.sorszam = i;
			this.nev = DB[0];
			this.x = Double.parseDouble(DB[1].replace(",", "."));
			this.y = Double.parseDouble(DB[2].replace(",", "."));
		}

		@Override
		public String toString() {
			return "JatekosLovese [sorszam=" + sorszam + ", nev=" + nev + ", x=" + x + ", y=" + y + "]";
		}

		// 6.feladat
		public double Tavolsag(double CéltáblaX, double CéltáblaY, double LövésX, double LövésY) {
			double dx = CéltáblaX - LövésX;
			double dy = CéltáblaY - LövésY;
			return Math.sqrt(dx * dx + dy * dy);
		}

		// 8.feladat
		public double Pontszam(double CX, double CY, double LX, double LY) {
			double pontszam = 10 - Tavolsag(CX, CY, LX, LY);
			double kerekitettpontszam = Math.round(pontszam * 100) / 100D;
			if (pontszam < 0) {
				return 0D;
			} else {
				return kerekitettpontszam;
			}
		}
	}
}
