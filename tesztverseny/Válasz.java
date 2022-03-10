package tesztverseny;

public class Válasz {

	private String azonosító;
	private String tippek;
	private int db;

	public Válasz(String tippek) {
		this.tippek = tippek;
	}

	public Válasz(String azonosító, String tippek) {
		this.azonosító = azonosító;
		this.tippek = tippek;
		this.db = db;
	}

	public String getAzonosító() {
		return azonosító;
	}

	public String getTippek() {
		return tippek;
	}

	public void setAzonosító(String azonosító) {
		this.azonosító = azonosító;
	}

	public void setTippek(String tippek) {
		this.tippek = tippek;
	}

	@Override
	public String toString() {
		return azonosító + " " + tippek;
	}

	public String kiír() {
		return azonosító + " " + db;
	}

	public int getDb() {
		return db;
	}

	public void setPoints(int pontok) {
		db = pontok;
	}

}
