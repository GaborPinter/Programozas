package helsinki1952;

public class Olimpia {

	private int pontszám;
	private int helyezés;
	private int sportolókSzáma;
	private String sportágNeve;
	private String versenyszám;

	public Olimpia(String helyezés, String sportolókSzáma, String sportágNeve, String versenyszám) {
		this.helyezés = Integer.parseInt(helyezés);
		this.sportolókSzáma = Integer.parseInt(sportolókSzáma);
		this.sportágNeve = sportágNeve;
		this.versenyszám = versenyszám;
		this.pontszám = getPontszám(this.helyezés);
	}

	private int getPontszám(int helyezés) {
		switch (helyezés) {
		case 1:
			return 7;
		case 2:
			return 5;
		case 3:
			return 4;
		case 4:
			return 3;
		case 5:
			return 2;
		case 6:
			return 1;
		default:
			return 1;
		}
	}

	@Override
	public String toString() {
		return helyezés + " " + sportolókSzáma + " " + pontszám + " " + sportágNeve + " " + versenyszám;
	}

	public int getHelyezés() {
		return helyezés;
	}

	public int getSportolókSzáma() {
		return sportolókSzáma;
	}

	public String getSportágNeve() {
		return sportágNeve;
	}

	public String getVersenyszám() {
		return versenyszám;
	}

	public void setHelyezés(int helyezés) {
		this.helyezés = helyezés;
	}

	public void setSportolókSzáma(int sportolókSzáma) {
		this.sportolókSzáma = sportolókSzáma;
	}

	public void setSportágNeve(String sportágNeve) {
		this.sportágNeve = sportágNeve;
	}

	public void setVersenyszám(String versenyszám) {
		this.versenyszám = versenyszám;
	}

	public int getPontszám() {
		return pontszám;
	}

	public void setPontszám(int pontszám) {
		this.pontszám = pontszám;
	}

}
