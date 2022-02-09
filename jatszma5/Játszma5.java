import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Játszma5 {

	public static void main(String[] args) throws IOException {
		// 2.feladat
		List<String> sorok = Files.readAllLines(Path.of("labdamenetek.txt"), StandardCharsets.UTF_8);

		// 3.feladat
		System.out.println("3. feladat: Labdamenetek száma: " + sorok.size());

		// 4.feladat
		int a = 0;
		for (String sor : sorok) {
			if (sor.equals("A")) {
				a++;
			}
		}
		System.out.println("4. feladat: Az adogató játokos " + (double) a * 100 / sorok.size()
				+ "%-ban nyerte meg a labdameneteket. ");

		// 5.feladat
		int leghosszabb = 0;
		int max = 1;
		for (int i = 0; i < sorok.size() - 1; i++) {
			if (sorok.get(i).equals("A") && sorok.get(i + 1).equals("A")) {
				max++;
			}
			if (max > leghosszabb) {
				leghosszabb = max;
			}
			if (sorok.get(i).equals("A") && sorok.get(i + 1).equals("F")) {
				max = 1;
			}
		}
		System.out.println("5. feladat: Leghosszabb sorozat: " + leghosszabb);

		// 7.feladat
		Játék PróbaJáték = new Játék("FAFAA", "Mahut", "Isner");
		String hozzaadott = "";
		for (String sor : sorok) {
			if (sor.equals("A")) {
				hozzaadott = PróbaJáték.allas + Játék.Hozzáad(sor);
				break;
			}
		}

		int A = 0;
		for (int i = 0; i < hozzaadott.length(); i++) {
			A = A + Játék.NyertLabdamenetekSzámaA(hozzaadott.charAt(i));
		}
		int F = 0;
		for (int i = 0; i < hozzaadott.length(); i++) {
			F = F + Játék.NyertLabdamenetekSzámaF(hozzaadott.charAt(i));
		}
		boolean vege = false;
		if (A >= 4 || F >= 4 && A - F >= 2 || F - A >= 2) {
			vege = true;
		}
		System.out.println("7. feladat: A próba játék\n\tÁllás: " + hozzaadott + "\n\tBefejeződött a játék: "
				+ (vege == true ? "igen" : "nem"));

		// 8.feladat
		ArrayList<String> meccsek = new ArrayList<String>();
		int ado = 0;
		int fogado = 0;
		String allas = "";
		for (int i = 0; i < sorok.size(); i++) {
			allas = allas + sorok.get(i);
			if (sorok.get(i).equals("A")) {
				ado++;
			}
			if (sorok.get(i).equals("F")) {
				fogado++;
			}
			if ((ado >= 4 || fogado >= 4) && (ado - fogado >= 2 || fogado - ado >= 2)) {
				meccsek.add(allas);
				allas = "";
				ado = 0;
				fogado = 0;
			}
		}

		// 9.feladat
		int M = 0;
		int I = 0;
		for (int i = 0; i < meccsek.size(); i++) {
			int abetu = 0;
			int fbetu = 0;
			for (int j = 0; j < meccsek.get(i).length(); j++) {
				if (meccsek.get(i).charAt(j) == 'A') {
					abetu++;
				} else {
					fbetu++;
				}
			}
			if (((i == 0 || i % 2 == 0) && abetu > fbetu) || (i % 2 == 1 && fbetu > abetu)) {
				I++;
			} else {
				M++;
			}
		}
		System.out.println("9. feladat: Az 5. játszma végeredménye:\n\tMahut: " + M + "\n\tIsner: " + I);

	}

	// 6.feladat
	public static class Játék {
		private String allas;
		private String adogato;
		private String fogado;

		public Játék(String allas, String adogato, String fogado) {
			this.allas = allas;
			this.adogato = adogato;
			this.fogado = fogado;
		}

		public static String Hozzáad(String allas) {
			String eredmeny = "";
			eredmeny = eredmeny + allas;
			return eredmeny;
		}

		public static int NyertLabdamenetekSzámaA(char parameter) {
			int a = 0;
			if (parameter == 'A') {
				a++;
			}
			return a;
		}

		public static int NyertLabdamenetekSzámaF(char parameter) {
			int f = 0;
			if (parameter == 'F') {
				f++;
			}
			return f;
		}

		public static boolean JátékVége() { // nem megfelelő működésű a NyertLabdamenetek metódus átírása
			int nyertAdogató = 0; // miatt, ezért nincs is használva a programban
			int nyertFogadó = 0;
			int különbség = 0;
			nyertAdogató = NyertLabdamenetekSzámaA('A');
			nyertFogadó = NyertLabdamenetekSzámaF('F');
			különbség = Math.abs(nyertAdogató - nyertFogadó);
			return (nyertAdogató >= 4 || nyertFogadó >= 4) && (különbség >= 2);
		}
	}
}