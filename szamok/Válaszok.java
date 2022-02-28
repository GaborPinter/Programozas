package szamok;

import java.util.List;

public class Válaszok {

	private String kérdés;
	private int megoldás;
	private int pontszám;
	private String témakör;

	public Válaszok(List<String> sor, int i) {
		String[] DB = sor.get(i + 1).split(" ");
		this.kérdés = sor.get(i);
		this.megoldás = Integer.parseInt(DB[0]);
		this.pontszám = Integer.parseInt(DB[1]);
		this.témakör = DB[2];
	}

	@Override
	public String toString() {
		return "Válaszok [kérdés=" + kérdés + ", megoldás=" + megoldás + ", pontszám=" + pontszám + ", témakör="
				+ témakör + "]";
	}

	public String getKérdés() {
		return kérdés;
	}

	public int getMegoldás() {
		return megoldás;
	}

	public int getPontszám() {
		return pontszám;
	}

	public String getTémakör() {
		return témakör;
	}

}
