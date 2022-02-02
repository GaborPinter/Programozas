package godor;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Scanner;

public class godor {

	public static void main(String[] args) throws IOException {
		// 1.feladat
		List<String> sorok = Files.readAllLines(Path.of("melyseg.txt"), StandardCharsets.UTF_8);
		System.out.println("1. feladat\nA fájl adatainak száma: " + sorok.size());

		// 2.feladat
		System.out.print("\n2. feladat\nAdjon meg egy távolságértéket! ");
		Scanner input = new Scanner(System.in);
		int tavolsagErtek = input.nextInt() - 1;
		for (int i = 0; i < sorok.size(); i++) {
			if (i == tavolsagErtek) {
				System.out.println("Ezen a helyen a felszín " + sorok.get(i) + " méter mélyen van.");
			}
		}

		// 3.feladat
		int db = 0;
		for (int i = 0; i < sorok.size(); i++) {
			if (Integer.valueOf(sorok.get(i)) == 0) {
				db++;
			}
		}
		double arany = (double) db * 100 / sorok.size();
		System.out.println(String.format("\n3. feladat\nAz érintetlen terület aránya %.2f", arany) + "%.");

		// 4.feladat
		String text = "godrok.txt";
		try {
			File fájl = new File(text);
			fájl.delete();
			fájl.createNewFile();
		} catch (IOException e) {
		}
		try {
			File fájl = new File(text);
			PrintWriter PW = new PrintWriter(fájl);
			for (int i = 0; i < sorok.size() - 1; i++) {
				if (Integer.valueOf(sorok.get(i)) != 0) {
					PW.print(sorok.get(i) + " ");
				}
				if (Integer.valueOf(sorok.get(i)) > 0 && Integer.valueOf(sorok.get(i + 1)) == 0) {
					PW.print("\n");
				}
			}
			PW.close();
		} catch (IOException e) {
		}

		// 5.feladat
		int g = 0;
		for (int i = 0; i < sorok.size() - 1; i++) {
			if (Integer.valueOf(sorok.get(i)) == 0 && Integer.valueOf(sorok.get(i + 1)) != 0) {
				g++;
			}
		}
		System.out.println("\n5. feladat\nA gödrök száma: " + g);

		// 6.feladat
		int kezdet = 0;
		int vég = 0;
		for (int i = 0; i < sorok.size(); i++) {
			if (i == tavolsagErtek) {
				if (Integer.valueOf(sorok.get(i)) == 0) {
					System.out.println("Az adott helyen nincs gödör.");
				} else {
					for (int j = tavolsagErtek; j > 0 - 1; j--) {
						if (Integer.valueOf(sorok.get(j)) > 0 && Integer.valueOf(sorok.get(j - 1)) == 0) {
							kezdet = i - 1;
							System.out.print("\n6. feladat\na,\nA gödör kezdete: " + kezdet + " méter, ");
						}
					}
					while (Integer.valueOf(sorok.get(i)) != 0) {
						i++;
						vég = i;
					}
					System.out.println("a gödör vége: " + vég + " méter.\nb,");
					boolean melyul = true; 
					for (int k = kezdet; k < vég; k++) {
						if (Integer.valueOf(sorok.get(k)) < Integer.valueOf(sorok.get(k + 1))
								|| Integer.valueOf(sorok.get(k)) > Integer.valueOf(sorok.get(k + 1))) {
							melyul = true;
						} else {
							melyul = false;
						}
					}
					System.out.println(melyul == true ? "Folyamatosan mélyül.\nc," : "Nem mélyül folyamatosan.\nc,");
					int legmélyebb = 0;
					for (int h = kezdet - 1; h < vég; h++) {
						if (Integer.valueOf(sorok.get(h)) > legmélyebb) {
							legmélyebb = Integer.valueOf(sorok.get(h));
						}
					}
					System.out.println("A legnagyobb mélysége " + legmélyebb + " méter.\nd,");
					int t = 0;
					for (int e = kezdet - 1; e < vég; e++) {
						t = t + Integer.valueOf(sorok.get(e));
					}
					System.out.println("A térfogata " + (t * 10) + " m^3.\ne,");
					int felso = 0;
					for (int z = kezdet - 1; z < vég; z++) {
						if (Integer.valueOf(sorok.get(z)) != 0) {
							felso--;
						}
					}
					System.out.println("A vízmennyiség " + ((t + felso) * 10) + " m^3.");
				}
			}
		}

	}
}
