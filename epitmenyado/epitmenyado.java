package epitmenyado;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

public class epitmenyado {

	public static void main(String[] args) throws IOException {
		List<String> sorok=Files.readAllLines(Path.of("utca.txt"),StandardCharsets.UTF_8);
		List<Tulajdonsagok> adatok=new ArrayList<>();
		for(int i=1;i<sorok.size();i++) {
			String[] db = sorok.get(i).split(" ");
			adatok.add(new Tulajdonsagok(Integer.parseInt(db[0]), db[1], db[2], db[3], Integer.parseInt(db[4])));
		}
		
//		for (Tulajdonsagok adat : adatok) {
//			System.out.println(adat);
//		}
		
		System.out.println("2. feladat. A mintában "+adatok.size()+" telek szerepel.");
		
		Scanner input=new Scanner(System.in);
		System.out.print("3. feladat. Egy tulajdonos adószáma: ");
		int megadottSzam=input.nextInt();
		boolean van=false;
		for (Tulajdonsagok adat : adatok) {
			if(adat.adoszam==megadottSzam) {
				System.out.println(adat.utcaNev+" utca "+adat.hazSzam );
				van=true;
			}
		}
		if(van==false) {
			System.out.println("Nem szerepel az adatállományban.");
		}
		
		int osszAT=0,osszBT=0,osszCT=0,osszAA=0,osszBA=0,osszCA=0;
		for (Tulajdonsagok adat : adatok) {
			if(adat.adosav.equals("A")) {
				osszAT++;
				osszAA=osszAA+adat.ado(adat.adosav, adat.alapterulet);
			}
			if(adat.adosav.equals("B")) {
				osszBT++;
				osszBA=osszBA+adat.ado(adat.adosav, adat.alapterulet);
			}
			if(adat.adosav.equals("C")) {
				osszCT++;
				osszCA=osszCA+adat.ado(adat.adosav, adat.alapterulet);
			}
		}
		
		System.out.println("5. feladat\nA sávba "+osszAT+" telek esik, az adó "+osszAA+" Ft."
				+ "\nB sávba "+osszBT+" telek esik, az adó "+osszBA+" Ft.\nC sávba "+osszCT+" telek esik, az adó "+osszCA+" Ft.");
		
		System.out.println("6. feladat. A több sávba sorolt utcák:");
		Set<String> utcak=new TreeSet<>();
		for (Tulajdonsagok adat : adatok) {
			utcak.add(adat.utcaNev);
		}
		for (String utca : utcak) {
			Set<String> betuk=new HashSet<>();
			for (Tulajdonsagok adat : adatok) {
				if(utca.equals(adat.utcaNev)) {
					betuk.add(adat.adosav);
				}
			}
			if(betuk.size()>1) {
				System.out.println(utca);
			}
			betuk.clear();
		}
		
		String fajlNev="fizetendo.txt";
		try {
			File fajl=new File(fajlNev);
			fajl.delete();
			fajl.createNewFile();
		}catch (Exception e) {
		}
		try {
			File fajl=new File(fajlNev);
			PrintWriter pw=new PrintWriter(fajl);
			for (Tulajdonsagok adat : adatok) {
				pw.println(adat.adoszam+" "+adat.ado(adat.adosav, adat.alapterulet));
			}
			pw.close();		
		}catch (Exception e) {
		}
		
		
	}
	
	public static class Tulajdonsagok{
		private int adoszam;
		private String utcaNev;
		private String hazSzam;
		private String adosav;
		private int alapterulet;
		
		public Tulajdonsagok(int adoszam, String utcaNev, String hazSzam, String adosav, int alapterulet) {
			this.adoszam = adoszam;
			this.utcaNev = utcaNev;
			this.hazSzam = hazSzam;
			this.adosav = adosav;
			this.alapterulet = alapterulet;
		}

		@Override
		public String toString() {
			return "Tulajdonsagok [adoszam=" + adoszam + ", utcaNev=" + utcaNev + ", hazSzam=" + hazSzam + ", adosav="
					+ adosav + ", alapterulet=" + alapterulet + "]";
		}
		
		public int ado(String adosav,int alapterulet) {
			int fizet=0;
			switch (adosav) {
			case "A" ->  fizet=800*alapterulet;
			case "B" ->  fizet=600*alapterulet;
			case "C" ->  fizet=100*alapterulet;
			}
			return fizet<10_000?0:fizet;
		}
		
		
	}
}
