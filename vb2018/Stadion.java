package vb2018;

public class Stadion {

	private String varosNev;
	private String stadionNev;
	private String stadionAlternativNev;
	private int ferohely;

	public Stadion(String sor) {
		String[] DB = sor.split(";");
		varosNev = DB[0];
		stadionNev = DB[1];
		stadionAlternativNev = DB[2];
		ferohely = Integer.parseInt(DB[3]);

	}

	@Override
	public String toString() {
		return "Stadion [varosNev=" + varosNev + ", stadionNev=" + stadionNev + ", stadionAlternativNev="
				+ stadionAlternativNev + ", ferohely=" + ferohely + "]";
	}

	public String getVarosNev() {
		return varosNev;
	}

	public String getStadionNev() {
		return stadionNev;
	}

	public String getStadionAlternativNev() {
		return stadionAlternativNev;
	}

	public int getFerohely() {
		return ferohely;
	}

	public void setVarosNev(String varosNev) {
		this.varosNev = varosNev;
	}

	public void setStadionNev(String stadionNev) {
		this.stadionNev = stadionNev;
	}

	public void setStadionAlternativNev(String stadionAlternativNev) {
		this.stadionAlternativNev = stadionAlternativNev;
	}

	public void setFerohely(int ferohely) {
		this.ferohely = ferohely;
	}

}
