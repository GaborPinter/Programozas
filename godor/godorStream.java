package godor;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class godorStream {

	public static void main(String[] args) throws IOException {
		int[] array = Files.lines(Path.of("melyseg.txt")).mapToInt(a -> Integer.parseInt(a)).toArray();
		
		System.out.println("1.feladat\nA fajl adatainak szama: "+array.length);
		
		System.out.print("Adjon meg egy tavolsagerteket! ");
		int nextInt;
		try(Scanner scan=new Scanner(System.in)) {
			nextInt = scan.nextInt()-1;
		}
		System.out.println("Ezen a helyen a felszin "+array[nextInt]+" meter melyen van.");
		
		long count = Arrays.stream(array).filter(a -> ( a==0?true:false)).count();
		System.out.printf("3.feladat\nAz erintetlen terulet aranya: %.2f%%",(double)count*100/array.length);
		
		int[] elejeVege = IntStream.range(1, array.length).
				filter(a -> array[a]!=0 && array[a-1]==0 || array[a]==0 && array[a-1]!=0).toArray();
		int[][] godrok = IntStream.iterate(0, a -> a<elejeVege.length, a-> a+2).
		mapToObj(a -> Arrays.copyOfRange(array,elejeVege[a],elejeVege[a+1])).toArray(a -> new int[a][]);
		String fajlba = Arrays.stream(godrok).map(a -> Arrays.stream(a).
				mapToObj(b -> Integer.toString(b)).collect(Collectors.joining(" "))).collect(Collectors.joining("\n"));
		Files.writeString(Path.of("godrok.txt"), fajlba);
		
		System.out.println("\n5.feladat\nA godrok szama: "+godrok.length);
		
		if(array[nextInt]==0) {
			System.out.println("6. feladat\nAz adott helyen nincs godor.");
		}else {
			int index = IntStream.iterate(0, a -> a<elejeVege.length,a -> a+2)
					.filter(a -> nextInt>=elejeVege[a] && nextInt<=elejeVege[a+1]).findFirst().getAsInt();		
			
			int kezdet=elejeVege[index]+1;
			int veg=elejeVege[index+1];
			
			System.out.println("6. feladat\na,\nA  godor kezdete: "+kezdet+ " meter, a godor vege: "+veg+" meter.");
			
			int[] jelenlegi=godrok[index];
			
			int legmelyebb = Arrays.stream(jelenlegi).max().getAsInt();
			int legmelyebbIndex = Arrays.stream(jelenlegi).filter(a -> a==legmelyebb).findFirst().getAsInt();
			
			boolean balrolLegmelyebbig = IntStream.range(0, legmelyebbIndex-1).allMatch(a -> jelenlegi[a]<=jelenlegi[a+1]);
			boolean legmelyebbtolJobbig = IntStream.range(legmelyebbIndex, jelenlegi.length-1)
					.allMatch(a -> jelenlegi[a]>=jelenlegi[a+1]);
			
			System.out.println("b,\n"+(balrolLegmelyebbig && legmelyebbtolJobbig?"Folyamatosan melyul":"Nem melyul folyamatosan."));
			
			System.out.println("c,\nA legnagyobb melysege a "+legmelyebb+" meter.");
			
			int T = Arrays.stream(jelenlegi).sum();
			System.out.println("d,\nA terfogata "+T*10+" m^3");
			
			int kiont = jelenlegi.length;
			System.out.println("e,\nA vizmennyiseg "+(T-kiont)*10+" m^3");
		}
	}
	
}
