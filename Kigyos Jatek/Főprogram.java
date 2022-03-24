
public class Főprogram {
	
	/**
	 * Egy program amelyben egy több blokkból álló kígyónak a mozgását tudjuk befolyásolni. Amennyiben a kígyó feje hozzáér a 
	 * pálya falához vagy a testéhez a játék véget ér és a képernyőn megjelenik a Game Over felirat. A kígyó feje piros, teste zöld és a pályán
	 * megtalálhatóak kék pontok amelyek szilvák szerepét valósítják meg. Minden egyes megevett szilva után a kígyónk teste nőni
	 * fog egyel. A kígyó mozgását a nyíl gombokkal tudjuk befolyásolni, illetve a grafikus felületünk tetején még a megevett
	 * szilvák száma is nyomon követhető.
	 * 
	 */

	public static void main(String[] args) {
		
		 new JátékKeret();
	}
}
