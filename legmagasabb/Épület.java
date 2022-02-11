package legmagasabb;

public class Épület {

	// adattagok
	private String név;
	private String város;
	private String ország;
	private int magasság;
	private int emelet;
	private int épült;

	// konstruktor
	public Épület(String név, String város, String ország, int magasság, int emelet, int épült) {
		this.név = név;
		this.város = város;
		this.ország = ország;
		this.magasság = magasság;
		this.emelet = emelet;
		this.épült = épült;
	}

	// getter metódusok
	public String getNév() {
		return név;
	}

	public String getVáros() {
		return város;
	}

	public String getOrszág() {
		return ország;
	}

	public int getMagasság() {
		return magasság;
	}

	public int getEmelet() {
		return emelet;
	}

	public int getÉpült() {
		return épült;
	}

	// setter metódusok
	public void setNév(String név) {
		this.név = név;
	}

	public void setVáros(String város) {
		this.város = város;
	}

	public void setOrszág(String ország) {
		this.ország = ország;
	}

	public void setMagasság(int magasság) {
		this.magasság = magasság;
	}

	public void setEmelet(int emelet) {
		this.emelet = emelet;
	}

	public void setÉpült(int épült) {
		this.épült = épült;
	}

	// toString felüldefiniálása
	@Override
	public String toString() {
		return "Épület [név=" + név + ", város=" + város + ", ország=" + ország + ", magasság=" + magasság + ", emelet="
				+ emelet + ", épült=" + épült + "]";
	}

}
