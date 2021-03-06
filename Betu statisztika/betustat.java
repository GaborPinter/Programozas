import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

/*
 * Készítsen statisztikát amely összeszámolja hogy a kiválasztott mesében előforduló karakterekből hány darab található!
 */

public class betuStatisztika {
	public static void main(String[] args) {
		String mese = """
								Babszem Jankó
								
Volt egyszer egy szabó és annak egy fia. Ez a gyerek olyan pöttömre sikerült, hogy amikor fölserdült, akkor sem lett nagyobb egy babszemnél. Apró volt a teste, de nagy a mersze. Egy szép napon odaállt az apja elé, kihúzta magát, és kijelentette:
- Édesapám, világot akarok látni!
- Jól van - mondta a szabó -, segítsen a jó szerencse!
Fogott egy tűt, gyertyát gyújtott, az olvadt viaszból kis gömböt gyúrt, rászúrta markolatnak a tű tetejére, és átadta a parányi fegyvert a fiának.
- Nesze, most már kardod is van az útra - mondta.
- Köszönöm, apám - felelte a fiú -, megyek is hamarosan, csak előbb hadd költsük el együtt a búcsúebédet.
Azzal kiszökdécselt a konyhába, megnézni, mit főz az édesanyja. Éppen fortyogott is valami a tűzhelyen egy jókora fazékban.
- Mit eszünk ma, édesanyám? - kotnyeleskedett Jankó.
Az öreg szabóné nem szerette, ha sütés-főzés közben zavarják.
- Nézd meg magad, ha kíváncsi vagy rá - vetette oda.
Babszem Jankó szerette a jó falatot, hogyne lett volna kíváncsi. Felugrott a tűzhelyre, félrebillentette kicsit a fedőt, és belekukkantott a fazékba. Hanem abból egyszeriben felcsapott a gőz, elkapta a kis embert, és uzsgyi! - kivitte a kéményen.
A legényke egy ideig ott lovagolt a bodor felhőcskén, aztán szerencsésen leereszkedett a földre, körülnézett, s azt mondta:
- No kint vagyok hát a nagyvilágban!
Elindult foglalkozást keresni magának. És mert otthon jól kitanulta a mesterségét , beszegődött egy szabóhoz segédnek. A mester meg volt elégedve a munkájával, Babszem Jankó is a helyével; csak egy kifogása volt: sehogyan sem találta elég jónak az ételt, amit az: asztalra adtak.
Végül is kifogyott a béketűrésből, kiment a konyhába, és azt mondta:
- Hallja-e, mesterné asszony! Ha nem főz nekünk jobbat, holnap reggel odébbállok, és kiírom krétával a kapujukra:
Krumplihoz itt hús sose jár:
Isten veled, krumplikirály!
 
Az asszonyt elfutotta a méreg, fölkapott egy rongyot, hozzá akarta vágni a kis emberhez, de az elbújt egy gyűszű alá, és onnét öltögette a nyelvét.
- Megállj csak, majd ellátom én a bajodat! - pörölt a mesterné, de mire a gyűszűt fölemelte, Babszem Jankó már régen a rongy ráncában lapult; mikor pedig a gazdasszonya a rongyot is kirázta, ügyes szökkenéssel beugrott egy repedésbe az asztalon.
- Itt vagyok, ragyogok! - csúfolódott hol az asztalban, hol a fiókban, hol az egyik szék támláján.
A mesterné végül mégis elcsípte, és kidobta a házból.
A szabócska nagyot repült, még nagyobbat huppant, aztán talpon termett. lerázta magáról a port, és megint nekivágott a világnak. Ment, mendegélt, beért egy nagy erdőbe. Erősen alkonyodott már. Babszem Jankó körülkémlelt, hol lelhetne jó kényelmes szállást éjszakára. Látott is valami olyasmit, mint egy kisebb faderék gondolta: ez éppen megfelelő. Nekikészült, hogy leheveredjék a tövébe de a fa megmozdult, mert hát nem fa volt, hanem egy embernek a lába. Abban a pillanatban nyakon ragadták, magasba lódították, s hiába kapálódzott kezével-lábával, jó erősen fogta valaki a nyakát. Egy pillanatra el is szédült a nagy repülésben. Mire eszméletre tért, ott találta magát egy embernek a tenyerén s egy sereg borostás arcot látott. Mind őt lesték, s úgy szuszogtak hozzá, hogy szegény kis Babszem Jankónak valóságos orkán süvöltött a füle mellett.
- Nézd már ezt a Góliátot! - harsogta az egyik ember.
- Többet ér ez minden tolvajkulcsnál! - mondta a másik. - Nincs az a szűk kulcslyuk, amelyiken be ne férne!
- Hallod-e, fickó! - szólt a harmadik. - Jössz-e velünk a király kincstárába? Bebújsz a kulcslyukon, és kihajigálod nekünk a sok pénzt. Rendben van?
Babszem Jankó töprengett egy kicsit, aztán ráállt a dologra. Most hát látta: rablók közé keveredett, s ha szépszerével nem megy velük, magukkal viszik erőszakkal. Beült hát az egyik rablónak a zsebébe, és mentek a kincseskamrához.
Csakhogy őrizték ám azt erősen! Két szuronyos katona is állt az ajtajában. A rablók tanakodtak egy ideig, aztán legtanácsosabbnak azt tartották, ha megkerülik a házat, s elrejtőznek hátul a kincseskamra ablaka alatt, a bokrok közt, Babszem Jankó meg próbáljon meg valahogyan behatolni, hátha nem veszik észre az őrök a pöttömnyi legénykét.
- Ez így jó lesz - helyeselte Babszem Jankó a tervet -, csak aztán mind fölszedjétek ám, amit kihajigálok!
Azzal nagy peckesen odalépkedett a kincseskamra vasalt ajtajához, s elkezdte szemügyre venni, nincs-e valahol hasadék az alján, hátha megtakaríthatja a fáradságot, s nem kell fölmásznia a kulcslyukig. Csakhamar föl is fedezett egy rést, éppen elég széles volt ahhoz, hogy becsússzék rajta. Hanem úgy látszik, elbízta magát a parányiságában, és nem ügyelt eléggé; mert az egyik silbak mégiscsak megpillantotta, amint befelé iparkodott.
- Nézd csak, milyen rusnya pók mászik ott - mondta a társának. - Agyontaposom.
- Hadd éljen szegény, nem vétett az neked - szólt a másik.
Így aztán Babszem Jankó végül is szerencsésen bejutott a kincstárba. Kinyitotta az ablakot, pisszentett a rablóknak, azok előbújtak a bokrok közül, ő meg kezdte kihajigálni nekik az aranytallérokat.
Amint így javában munkálkodik, egyszer csak hallja, hogy léptek közelednek, a kulcs csikordul a zárban: jön a király megszemlélni a kincseit. Nosza, gyorsan elbújt egy rakás arany mögé.
A király rögtön észrevette, hogy a tallérjaiból jócskán hiányzik. Sehogyan sem foghatta meg, mi ennek az oka, ki lehet a tolvaj, hiszen a kulcsot magánál őrizte, lakatnak, zárnak semmi hibája nem volt, és az őrség is a helyén állt. Egy ideig töprengett, de mivel semmiféle magyarázatot nem talált a dologra, végül is visszatért a lakosztályába, hanem azért elmenőben odaszólt az őröknek:
- Halljátok-e, jobban vigyázzatok, valaki rájár a pénzre!
A katonák megijedtek; és erősen fülelni kezdtek. Egyszer csak hallják ám, hogy odabent csendül-pendül, csörren-zörren az arany; mert amikor a király léptei eltávolodtak, és csend lett, Babszem megint nekilátott a munkának. Az őrök szaladtak hamar a kulcsért, jól bezsírozták, hogy ne csikorogjon, óvatosan kikattintották a zárat, aztán hopp! - beugrottak a kamrába, hogy lefüleljék a tolvajt. Babszem Jankó azonban fürgébb volt náluk: végigszaladt a fal mentén, bebújt a sarokba, és magára húzott egy aranytallért, még a füle hegye se látszott ki mögüle.
- Hol vagyok? Itt vagyok! - kiáltotta csúfondárosan.
Mire az őrök odarohantak, már a másik sarokból kiabálta egy másik tallér mögül:
- Itt vagyok! Hol vagyok?
Szegény katonák egy ideig ide-oda szaladgáltak a kincseskamrában, hanem aztán megunták, hogy így bolonddá tartják őket, bele is fáradtak a haszontalan hajszába; legyen, ami lesz! - gondolták, ráfordították a kulcsot a kamrára, leültek jobbról-balról a pántos ajtó mellé, és elaludtak a kimerültségtől.
Babszem Jankó pedig mind egy szálig kihajigálta a rengeteg aranypénzt. A legutolsót jól megpörgette, röptében fölpattant rá, úgy suhant ki az ablakon.
Dicsérték, magasztalták is a rablók:
- Szó, ami szó: igazi hős vagy! Akarsz-e a vezérünk lenni?
Babszem Jankónak azonban nem fűlt a foga a rablóvezérséghez. "Hogyisne - gondolta magában -, hogy a végén akasztófára jussak!" De a rablóknak csak annyit mondott nagy udvariasan:
- Köszönöm a megtiszteltetést; nem fogadhatom el, mert előbb világot szeretnék látni.
- Rendben van - mondták a rablók -, de akkor legalább osztozzunk meg a zsákmányon!
Babszem Jankó azonban abból sem kért egyebet, mint egy lyukas krajcárt. Fölcsatolta derekára a kardját, elbúcsúzott a rablóktól, és nekiindult megint a világnak. Itt is, ott is munkába állt, de mindenütt hamarosan kitelt az ideje mert sehogyan sem bírta az állandóságot.
Sok bolyongás után végül is beállt szolgának egy fogadóshoz. A szolgálólányok azonban nem szívelhették, mert a pöttöm emberke minden titkos dolgukat kileste.
- Amióta ez a mihaszna itt van, még egy üveg bort sem csenhetünk el a pincéből - mondogatták egymás közt, és legszívesebben eltüntették volna a föld színéről.
De hát hogyan tüntessék el? Megjött annak is az alkalma.
Egyszer az egyik lányt leküldték a kertbe füvet kaszálni. Babszem Jankó ott ugrált a közelében, egyik fűszálról a másikra szökdécselt. "Megállj gondolta a lány -, most elbánok veled!" - azzal meglendítette a kaszát, és egyetlen suhintással levágta Babszem Jankó alatt a füvet. Az még csak meg se nyikkanhatott: a lány gyorsan összemarkolta a friss szénát, kendőbe kötötte, aztán Babszem Jankóstul odavetette a tehenek elé.
Volt azok közt egy nagy fekete jószág, az egyszeriben lenyelte Jankót, úgyhogy a sok fű közt semmi bája nem lett.
Sűrű sötétség volt a tehén bendőjében, még csak egy árva gyertyaszál sem égett benne. A kis legény nagyon rosszul érezte magát ebben a fekete éjszakában. Alig várta, hogy alkalom kerülközzék a jeladásra.
Kínálkozott is hamarosan, mert már elérkezett a fejés ideje. Mikor a feketére került a sor, Babszem Jankó nagyot kiáltott:
Hoppla-hó! Hoppla-hó!
Tele van a csobolyó!
 
De hiába, elveszett a hangja a tehénbőgésben.
Fejés után lement a fogadós az istállóba, megállt a fekete előtt, azt mondta:
- Ezt a jószágot holnap le kel! vágni.
Babszem Jankó megijedt, teli torokból kiabálni kezdett:
- Engedjetek ki! Itt ülök benne!
- Hol? - kérdezte a gazda, mert hallotta a hangot, de el sem tudta képzelni, honnét jön.
- Hát a feketében! - kiáltotta Jankó.
- Ugyan ne bolondozzál velem! - mondta mérgesen a fogadós, mert azt hitte, hogy a csaposlegény tréfálkozik, és elment.
Másnap reggel levágták a tehenet, apróra földarabolták, de Babszem Jankóban szerencsére nem esett kár, mindössze csak annyi történt vele, hogy belekeveredett a kolbászhúsba. Megsózták, megborsozták, megpaprikázták, hogy csak úgy prüszkölt tőle, aztán jött a böllér, és egy csomó szalonnakocka meg egy marék daráit hús társaságában beletöltötte Babszem Jankó a kolbászhéjba.
Hát ez meglehetősen szűk kvártély volt! Ráadásul füstre akasztották a kéménybe; ott aztán aszalódhatott volna ítéletnapjáig.
Szerencsére úgy tél dereka táján vendég érkezett a fogadóba, és mindenáron kolbászt akart enni reggelire.
- Hozz egy szálat a füstről! - mondta a fogadósné a konyhalánynak, az meg éppen azt akasztotta le, amelyikben Babszem Jankó telelt.
Lekerült hát a füstről, de most jutott ám csak veszedelembe! A fogadósné nekiállt a szeletelésnek, szisszent az éles kés, hullott az egyik szelet a másik után, Babszem Jankó meg csak kapdosta a fejét jobbra is, balra is, nehogy véletlenül az ő nyakát is elvágják. Mikor aztán az egyik szelettel végre kifordult a konyhadeszkára, nem sokat teketóriázott, kapta magát, és megugrott.
Ebben a házban annyi baj érte már, egy pillanatig sem akart tovább itt maradni.
Nyakába vette a lábát, s uccu neki! - meg sem állt, míg ki nem ért a mezőre.
- Csakhogy végre szabad eget látok! - fohászkodott föl, s éppen egy nagyot akart szippantani a hűs levegőből, mikor egy kódorgó róka merő szórakozottságból egy szöcskével együtt őt is bekapta.
A szabócska összeszedte minden erejét, megvetette a lábát róka koma garatjában, jól megkapszkodott, nehogy lecsusszanjon, s elkezdett kiabálni:
- Mi dolog ez, róka testvér! Én vagyok az, Babszem Jankó, én akadtam a torkodon, engedj ki!
- Igaz - mondta a róka -, minek is nyelnék le ilyen semmiséget? Ha megígéred, hogy nekem adod az apád tyúkjait, kijöhetsz.
Babszem Jankó mindent megígért. Erre a róka nemcsak hogy kiengedte, hanem még haza is vitte.
Otthon nagyon megörültek Babszem Jankónak, és cserébe odaadták érte az egész baromfiudvart. De mert az öreg szabónak mégiscsak fájt egy kicsit a szíve a szép fehér meg kendermagos tyúkjaiért, Babszem Jankó elébe állt, s azt mondta:
- Sose búsulj, édesapám, hoztam ám neked valamit a tyúkjaid helyett!
Azzal nagy büszkén odaadta neki a lyukas krajcárt, amit a vándorútján szerzett.

								""";

		Map<Character, Integer> stat = new HashMap<>();
		char[] karakterTomb = mese.toCharArray();
		for (char karakter : karakterTomb) {
			if (stat.containsKey(karakter)) {
				Integer ertek = stat.get(karakter);
				ertek++;
				stat.put(karakter, ertek);
			} else {
				stat.put(karakter, 1);
			}
		}

		Set<Entry<Character, Integer>> entryHalmaz = stat.entrySet();
		for (Entry<Character, Integer> entry : entryHalmaz) {
			System.out.println(
					"A(z) '" + entry.getKey() + "' karakterbol " + entry.getValue() + " db van ebben a meseben.");
		}

	}
}
