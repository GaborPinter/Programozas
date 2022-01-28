package eutazas;

public class Bérlet {

	private int megálló;
	private String dátumIdő;
	private int kártyaAzonosító;
	private String típus;
	private int érvényesség;

	public Bérlet(int megálló, String dátumIdő, int kártyaAzonosító, String típus, int érvényesség) {
		this.megálló = megálló;
		this.dátumIdő = dátumIdő;
		this.kártyaAzonosító = kártyaAzonosító;
		this.típus = típus;
		this.érvényesség = érvényesség;
	}

	@Override
	public String toString() {
		return "Bérlet [megálló=" + megálló + ", dátumIdő=" + dátumIdő + ", kártyaAzonosító=" + kártyaAzonosító
				+ ", típus=" + típus + ", érvényesség=" + érvényesség + "]";
	}

	public int getMegálló() {
		return megálló;
	}

	public String getDátumIdő() {
		return dátumIdő;
	}

	public int getKártyaAzonosító() {
		return kártyaAzonosító;
	}

	public String getTípus() {
		return típus;
	}

	public int getÉrvényesség() {
		return érvényesség;
	}

}
