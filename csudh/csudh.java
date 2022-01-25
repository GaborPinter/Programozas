package csudh;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class csudh {

	public static void main(String[] args) throws IOException {
		// 2.feladat
		List<String> sorok = Files.readAllLines(Path.of("csudh.txt"), StandardCharsets.UTF_8);
		ArrayList<tulajdonság> hozzáférések = new ArrayList<tulajdonság>();
		for (int i = 1; i < sorok.size(); i++) {
			hozzáférések.add(new tulajdonság(sorok.get(i)));
		}

		System.out.println("3. feladat: Domainek száma: " + hozzáférések.size());

		System.out.println("5. feladat: Az első domain felépítése:");
		String elsodomain = hozzáférések.get(0).domain;
		for (int i = 0; i < 5; i++) {
			System.out.println("	" + (i + 1) + ". szint: " + Domain(i, elsodomain));
		}

		// 6.feladat
		String text = "table.html";
		try {
			File fájl = new File(text);
			fájl.delete();
			fájl.createNewFile();
		} catch (IOException e) {
		}

		String fejléc = "<table>\n" + "<tr>\n" + "<th style='text-align: left'>Ssz</th>\n"
				+ "<th style='text-align: left'>Host domain neve</th>\n"
				+ "<th style='text-align: left'>Host IP címe</th>\n" + "<th style='text-align: left'>1. szint</th>\n"
				+ "<th style='text-align: left'>2. szint</th>\n" + "<th style='text-align: left'>3. szint</th>\n"
				+ "<th style='text-align: left'>4. szint</th>\n" + "<th style='text-align: left'>5. szint</th>\n"
				+ "</tr>";

		try {
			File fájl = new File(text);
			PrintWriter PW = new PrintWriter(fájl);

			PW.println(fejléc);
			for (int i = 0; i < hozzáférések.size(); i++) {
				tulajdonság hozzáférés = hozzáférések.get(i);

				PW.println("<tr>");
				PW.println("<th style='text-align: left'>" + (i + 1) + ".</th>");
				PW.println("<td>" + hozzáférés.domain + "</td>");
				PW.println("<td>" + hozzáférés.IP + "</td>");

				for (int j = 0; j < 5; j++) {
					PW.println("<td>" + Domain(j, hozzáférés.domain) + "</td>");
				}
				PW.println("</tr>");
			}
			PW.println("</table>");
			PW.close();
		} catch (IOException e) {
		}
	}

	public static class tulajdonság {
		String domain;
		String IP;

		public tulajdonság(String sor) {
			String[] db = sor.split(";");
			domain = db[0];
			IP = db[1];
		}

		@Override
		public String toString() {
			return domain + ";" + IP;
		}

	}

	// 4.feladat
	public static String Domain(int szint, String domain) {
		String[] split = domain.split("\\.");
		int index = split.length - 1;

		return index < szint ? "nincs" : split[index - szint];

	}
}
