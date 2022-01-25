package cimek;

public class Tagok {

	private String elso;
	private String masodik;
	private String harmadik;
	private String negyedik;
	private String otodik;
	private String hatodik;
	private String hetedik;
	private String nyolcadik;
	private int sorszam;

	public Tagok(String elso, String masodik, String harmadik, String negyedik, String otodik, String hatodik,
			String hetedik, String nyolcadik, int sorszam) {
		this.elso = elso;
		this.masodik = masodik;
		this.harmadik = harmadik;
		this.negyedik = negyedik;
		this.otodik = otodik;
		this.hatodik = hatodik;
		this.hetedik = hetedik;
		this.nyolcadik = nyolcadik;
		this.sorszam = sorszam;
	}

	public String getElso() {
		return elso;
	}

	public String getMasodik() {
		return masodik;
	}

	public String getHarmadik() {
		return harmadik;
	}

	public String getNegyedik() {
		return negyedik;
	}

	public String getOtodik() {
		return otodik;
	}

	public String getHatodik() {
		return hatodik;
	}

	public String getHetedik() {
		return hetedik;
	}

	public String getNyolcadik() {
		return nyolcadik;
	}

	public void setElso(String elso) {
		this.elso = elso;
	}

	public void setMasodik(String masodik) {
		this.masodik = masodik;
	}

	public void setHarmadik(String harmadik) {
		this.harmadik = harmadik;
	}

	public void setNegyedik(String negyedik) {
		this.negyedik = negyedik;
	}

	public void setOtodik(String otodik) {
		this.otodik = otodik;
	}

	public void setHatodik(String hatodik) {
		this.hatodik = hatodik;
	}

	public void setHetedik(String hetedik) {
		this.hetedik = hetedik;
	}

	public void setNyolcadik(String nyolcadik) {
		this.nyolcadik = nyolcadik;
	}

	public int getSorszam() {
		return sorszam;
	}

	public void setSorszam(int sorszam) {
		this.sorszam = sorszam;
	}

	@Override
	public String toString() {
		return sorszam + ". " + elso + ":" + masodik + ":" + harmadik + ":" + negyedik + ":" + otodik + ":" + hatodik
				+ ":" + hetedik + ":" + nyolcadik;
	}

}
