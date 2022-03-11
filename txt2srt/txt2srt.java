package txt2srt;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class txt2srt {

	public static void main(String[] args) throws IOException {
		// 4.feladat
		List<String> sorok = Files.readAllLines(Path.of("feliratok.txt"), StandardCharsets.UTF_8);
		ArrayList<IdozitettFelirat> adatok = new ArrayList<IdozitettFelirat>();
		for (int i = 0; i < sorok.size(); i = i + 2) {
			adatok.add(new IdozitettFelirat(sorok.get(i), sorok.get(i + 1)));
		}

		// ellenőrzés
		/*
		 * for (IdozitettFelirat adat : adatok) { System.out.println(adat); }
		 */

		// 5.feladat
		System.out.println("5. feladat - Feliratok száma: " + adatok.size());

		// 7.feladat
		int max = 0;
		for (IdozitettFelirat adat : adatok) {
			int szam = 0;
			szam = adat.SzavakSzama(adat.Felirat);
			if (szam > max) {
				max = szam;
			}
		}
		for (IdozitettFelirat adat : adatok) {
			if (adat.SzavakSzama(adat.Felirat) == max) {
				System.out.println("7. feladat - Legtöbb szóból álló felirat:\n" + adat.Felirat);
			}
		}

		// 9.feladat
		String text = "felirat.srt";
		try {
			File fájl = new File(text);
			fájl.delete();
			fájl.createNewFile();
		} catch (IOException e) {
		}
		try {
			File fájl = new File(text);
			PrintWriter PW = new PrintWriter(fájl);
			for (int i = 0; i < adatok.size(); i++) {
				PW.println((i + 1) + "\n" + adatok.get(i).SrtIdozites(adatok.get(i).Időzítés) + "\n"
						+ adatok.get(i).Felirat + "\n");
			}
			PW.close();
		} catch (IOException e) {
		}
	}

	// 2.feladat
	public static class IdozitettFelirat {
		private String Időzítés;
		private String Felirat;

		// 3.feladat
		public IdozitettFelirat(String I, String F) {
			this.Időzítés = I;
			this.Felirat = F;
		}

		@Override
		public String toString() {
			return "IdozitettFelirat [Időzítés=" + Időzítés + ", Felirat=" + Felirat + "]";
		}

		// 6.feladat
		public int SzavakSzama(String felirat) {
			int db = 1;
			for (int i = 0; i < felirat.length(); i++) {
				if (felirat.charAt(i) == ' ') {
					db++;
				}
			}
			return db;
		}

		// 8.feladat
		public String SrtIdozites(String ido) {
			int percKezdet = Integer.parseInt(ido.substring(0, 2));
			int masodpercKezdet = Integer.parseInt(ido.substring(3, 5));
			int percVeg = Integer.parseInt(ido.substring(8, 10));
			int masodpercVeg = Integer.parseInt(ido.substring(11, 13));
			int oraKezdet = 0;
			if (percKezdet >= 60) {
				oraKezdet++;
				percKezdet = percKezdet - 60;
			}
			int oraVeg = 0;
			if (percVeg >= 60) {
				oraVeg++;
				percVeg = percVeg - 60;
			}
			String kesz = LocalTime.of(oraKezdet, percKezdet, masodpercKezdet) + " --> "
					+ LocalTime.of(oraVeg, percVeg, masodpercVeg);
			return kesz;
		}
	}
}
