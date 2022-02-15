package metjelentes;

import java.time.LocalTime;

public class Tulajdonsag {

	private String szoveg;
	private LocalTime ido;
	private String szeliranyEsErosseg;
	private int homerseklet;

	public Tulajdonsag(String szoveg, LocalTime ido, String szeliranyEsErosseg, int homerseklet) {
		this.szoveg = szoveg;
		this.ido = ido;
		this.szeliranyEsErosseg = szeliranyEsErosseg;
		this.homerseklet = homerseklet;
	}

	@Override
	public String toString() {
		return "Tulajdonsag [szoveg=" + szoveg + ", ido=" + ido + ", szeliranyEsErosseg=" + szeliranyEsErosseg
				+ ", homerseklet=" + homerseklet + "]";
	}

	public String getSzoveg() {
		return szoveg;
	}

	public LocalTime getIdo() {
		return ido;
	}

	public String getSzeliranyEsErosseg() {
		return szeliranyEsErosseg;
	}

	public int getHomerseklet() {
		return homerseklet;
	}

	public void setSzoveg(String szoveg) {
		this.szoveg = szoveg;
	}

	public void setIdo(LocalTime ido) {
		this.ido = ido;
	}

	public void setSzeliranyEsErosseg(String szeliranyEsErosseg) {
		this.szeliranyEsErosseg = szeliranyEsErosseg;
	}

	public void setHomerseklet(int homerseklet) {
		this.homerseklet = homerseklet;
	}

}
