
public class Országok {

	private String ország;
	private String dátum;

	public Országok(String ország, String dátum) {
		this.ország = ország;
		this.dátum = dátum;
	}

	public String getOrszág() {
		return ország;
	}

	public String getDátum() {
		return dátum;
	}

	public void setOrszág(String ország) {
		this.ország = ország;
	}

	public void setDátum(String dátum) {
		this.dátum = dátum;
	}

	@Override
	public String toString() {
		return ország + " " + dátum;
	}

}
