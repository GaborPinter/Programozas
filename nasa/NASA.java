import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class NASA {

	public static void main(String[] args) throws IOException {
		// 4.feladat
		List<String> sorok = Files.readAllLines(Path.of("NASAlog.txt"), StandardCharsets.UTF_8);
		ArrayList<Keres> adatok = new ArrayList<Keres>();
		for (int i = 0; i < sorok.size(); i++) {
			String[] DB = sorok.get(i).split("\\*");
			int szokoz = 0;
			for (int j = 0; j < DB[3].length(); j++) {
				if (DB[3].charAt(j) == ' ') {
					szokoz = j;
				}
			}
			adatok.add(new Keres(DB[0], DB[1], DB[2], DB[3].substring(0, szokoz),
					DB[3].substring(szokoz + 1, DB[3].length())));
		}

		// ellenőrzés
		/*
		 * for (Keres adat : adatok) { System.out.println(adat); }
		 */

		// 5.feladat
		System.out.println("5. feladat: Kérések száma: " + adatok.size());

		// 6.feladat
		int osszes = 0;
		for (Keres adat : adatok) {
			osszes = osszes + adat.ByteMeret(adat.valasz);
		}
		System.out.println("6. feladat: Válaszok összes mérete: " + osszes + " byte");

		// 8.feladat
		double rendelkezik = 0;
		for (Keres adat : adatok) {
			if (adat.Domain(adat.domainNev)) {
				rendelkezik++;
			}
		}
		System.out.println(
				"8. feladat: Domain-es kérések: " + String.format("%.2f", rendelkezik * 100 / adatok.size()) + "%");

		// 9.feladat
		int k = 0, n = 0, h = 0;
		for (Keres adat : adatok) {
			if (adat.allapotkod.equals("200")) {
				k++;
			}
			if (adat.allapotkod.equals("404")) {
				n++;
			}
			if (adat.allapotkod.equals("304")) {
				h++;
			}
		}
		System.out.println("9. feladat: Statisztika:\n\t200: " + k + " db\n\t404: " + n + " db\n\t304: " + h + " db");
	}

	// 2.feladat
	public static class Keres {
		private String domainNev;
		private String datum;
		private String fajl;
		private String allapotkod;
		private String valasz;

		// 3.feladat
		public Keres(String domainNev, String datum, String fajl, String allapotkod, String valasz) {
			this.domainNev = domainNev;
			this.datum = datum;
			this.fajl = fajl;
			this.allapotkod = allapotkod;
			this.valasz = valasz;
		}

		@Override
		public String toString() {
			return "Keres [domainNev=" + domainNev + ", datum=" + datum + ", fajl=" + fajl + ", allapotkod="
					+ allapotkod + ", valasz=" + valasz + "]";
		}

		public int ByteMeret(String v) {
			int vmeret = 0;
			if (v.equals("-")) {
				vmeret = vmeret + 0;
			} else {
				vmeret = vmeret + Integer.valueOf(v);
			}
			return vmeret;
		}

		// 7.feladat
		public boolean Domain(String ü) {
			if (ü.charAt(ü.length() - 1) < 65) {
				return false;
			} else {
				return true;
			}
		}
	}
}
