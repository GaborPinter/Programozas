package cegesauto;

import java.time.LocalTime;

public class autóTulajdonság {

	private int nap;
	private LocalTime óraperc;
	private String rendszám;
	private int személyAzonosító;
	private int km;
	private int kiBeHajtás;

	public autóTulajdonság(int nap, LocalTime óraperc, String rendszám, int személyAzonosító, int km, int kiBeHajtás) {
		this.nap = nap;
		this.óraperc = óraperc;
		this.rendszám = rendszám;
		this.személyAzonosító = személyAzonosító;
		this.km = km;
		this.kiBeHajtás = kiBeHajtás;
	}

	@Override
	public String toString() {
		return nap + " " + óraperc + " " + rendszám + " " + személyAzonosító + " " + km + " " + kiBeHajtás;
	}

	public int getNap() {
		return nap;
	}

	public LocalTime getÓraperc() {
		return óraperc;
	}

	public String getRendszám() {
		return rendszám;
	}

	public int getSzemélyAzonosító() {
		return személyAzonosító;
	}

	public int getKm() {
		return km;
	}

	public int getKiBeHajtás() {
		return kiBeHajtás;
	}

	public void setNap(int nap) {
		this.nap = nap;
	}

	public void setÓraperc(LocalTime óraperc) {
		this.óraperc = óraperc;
	}

	public void setRendszám(String rendszám) {
		this.rendszám = rendszám;
	}

	public void setSzemélyAzonosító(int személyAzonosító) {
		this.személyAzonosító = személyAzonosító;
	}

	public void setKm(int km) {
		this.km = km;
	}

	public void setKiBeHajtás(int kiBeHajtás) {
		this.kiBeHajtás = kiBeHajtás;
	}

}
