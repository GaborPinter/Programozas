package kektura;

public class Szakaszok {

	private String kiindulópont;
	private String végpont;
	private double hossz;
	private int emelkedésÖsszegek;
	private int lejtésÖsszegek;
	private char pecsételőhely;

	public Szakaszok(String kiindulópont, String végpont, double hossz, int emelkedésÖsszegek, int lejtésÖsszegek,
			char pecsételőhely) {
		this.kiindulópont = kiindulópont;
		this.végpont = végpont;
		this.hossz = hossz;
		this.emelkedésÖsszegek = emelkedésÖsszegek;
		this.lejtésÖsszegek = lejtésÖsszegek;
		this.pecsételőhely = pecsételőhely;
	}

	@Override
	public String toString() {
		return "Szakaszok [kiindulópont=" + kiindulópont + ", végpont=" + végpont + ", hossz=" + hossz
				+ ", emelkedésÖsszegek=" + emelkedésÖsszegek + ", lejtésÖsszegek=" + lejtésÖsszegek + ", pecsételőhely="
				+ pecsételőhely + "]";
	}

	public String getKiindulópont() {
		return kiindulópont;
	}

	public String getVégpont() {
		return végpont;
	}

	public double getHossz() {
		return hossz;
	}

	public int getEmelkedésÖsszegek() {
		return emelkedésÖsszegek;
	}

	public int getLejtésÖsszegek() {
		return lejtésÖsszegek;
	}

	public char getPecsételőhely() {
		return pecsételőhely;
	}

}
