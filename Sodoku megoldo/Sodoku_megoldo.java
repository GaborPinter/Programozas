public class Sodoku_megoldo {

	private static final int MERET = 9;

	public static void main(String[] args) {
		int[][] tabla = {

				{ 7, 0, 2, 0, 5, 0, 6, 0, 0 }, { 0, 0, 0, 0, 0, 3, 0, 0, 0 }, { 1, 0, 0, 0, 0, 9, 5, 0, 0 },
				{ 8, 0, 0, 0, 0, 0, 0, 9, 0 }, { 0, 4, 3, 0, 0, 0, 7, 5, 0 }, { 0, 9, 0, 0, 0, 0, 0, 0, 8 },
				{ 0, 0, 9, 7, 0, 0, 0, 0, 5 }, { 0, 0, 0, 2, 0, 0, 0, 0, 0 }, { 0, 0, 7, 0, 4, 0, 2, 0, 3 } };

		kinyomtatas(tabla);

		if (megoldva(tabla)) {
			System.out.println("\nSikeresen megoldva\n");
		} else {
			System.out.println("\nNem lehet megoldani\n");
		}

		kinyomtatas(tabla);

	}

	private static void kinyomtatas(int[][] tabla) {
		for (int sor = 0; sor < MERET; sor++) {
			if (sor % 3 == 0 && sor != 0) {
				System.out.println("-----------");
			}
			for (int oszlop = 0; oszlop < MERET; oszlop++) {
				if (oszlop % 3 == 0 && oszlop != 0) {
					System.out.print("|");
				}
				System.out.print(tabla[sor][oszlop]);
			}
			System.out.println();
		}
	}

	private static boolean szamASorban(int[][] tabla, int szam, int sor) {
		for (int i = 0; i < MERET; i++) {
			if (tabla[sor][i] == szam) {
				return true;
			}
		}
		return false;
	}

	private static boolean szamAzOszlopban(int[][] tabla, int szam, int oszlop) {
		for (int i = 0; i < MERET; i++) {
			if (tabla[i][oszlop] == szam) {
				return true;
			}
		}
		return false;
	}

	private static boolean szamADobozban(int[][] tabla, int szam, int sor, int oszlop) {
		int adottDobozSora = sor - sor % 3;
		int adottDobozOszlopa = oszlop - oszlop % 3;
		for (int i = adottDobozSora; i < adottDobozSora + 3; i++) {
			for (int j = adottDobozOszlopa; j < adottDobozOszlopa + 3; j++) {
				if (tabla[i][j] == szam) {
					return true;
				}
			}
		}
		return false;
	}

	private static boolean megfeleloElhelyezes(int[][] tabla, int szam, int sor, int oszlop) {
		return !szamASorban(tabla, szam, sor) && !szamAzOszlopban(tabla, szam, oszlop)
				&& !szamADobozban(tabla, szam, sor, oszlop);
	}

	private static boolean megoldva(int[][] tabla) {
		for (int sor = 0; sor < MERET; sor++) {
			for (int oszlop = 0; oszlop < MERET; oszlop++) {
				if (tabla[sor][oszlop] == 0) {
					for (int probaSzam = 1; probaSzam <= MERET; probaSzam++) {
						if (megfeleloElhelyezes(tabla, probaSzam, sor, oszlop)) {
							tabla[sor][oszlop] = probaSzam;

							if (megoldva(tabla)) {
								return true;
							} else {
								tabla[sor][oszlop] = 0;
							}
						}
					}
					return false;
				}
			}
		}
		return true;
	}
}
