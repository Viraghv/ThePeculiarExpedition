package FoElemek;

import Csapat.*;
import Inventory.*;

import java.util.Random;
import java.util.Scanner;

/**
 * A kuldeteskozi es a falubeli Boltot valositja meg.
 * Targyakat es csapattarsat lehet venni tole.
 * A Boltrol tudjuk hogy falubeli-e (kulonben kuldeteskozi), es hogy mi van meg a raktaraban.
 */
public class Bolt {
    private boolean falu;
    private Kotel kotel;
    private Bozotvago bozotvago;
    private Faklya faklya;
    private Uveggolyo uveggolyo;
    private Hus hus;
    private Whiskey whiskey;
    private Csokolade csokolade;
    private Gyumolcs gyumolcs;
    private Kabitoszer kabitoszer;

    /**
     * Letrehoz egy adott tipusu boltot, es a szukseges arukkal feltolti a raktarat.
     * @param tipus "kuldkoz" ha a bolt kuldeteskozi, "falu" ha a bolt falubeli
     */
    public Bolt(String tipus) {
        if("falu".equals(tipus)){
            this.falu = true;
            kotel = new Kotel(5);
            bozotvago = new Bozotvago(0);
            faklya = new Faklya(5);
            uveggolyo = new Uveggolyo(0);
            hus = new Hus(5);
            whiskey = new Whiskey(0);
            csokolade = new Csokolade(0);
            gyumolcs = new Gyumolcs(5);
            kabitoszer = new Kabitoszer(5);
        } else if ("kuldkoz".equals(tipus)){
            this.falu = false;
            kotel = new Kotel(5);
            bozotvago = new Bozotvago(5);
            faklya = new Faklya(5);
            uveggolyo = new Uveggolyo(5);
            hus = new Hus(5);
            whiskey = new Whiskey(5);
            csokolade = new Csokolade(5);
            gyumolcs = new Gyumolcs(0);
            kabitoszer = new Kabitoszer(0);
        } else {
            System.out.println("Hibás paraméter!");
        }
    }

    public boolean isFalu() {
        return falu;
    }

    public Kotel getKotel() {
        return kotel;
    }

    public Bozotvago getBozotvago() {
        return bozotvago;
    }

    public Faklya getFaklya() {
        return faklya;
    }

    public Uveggolyo getUveggolyo() {
        return uveggolyo;
    }

    public Hus getHus() {
        return hus;
    }

    public Whiskey getWhiskey() {
        return whiskey;
    }

    public Csokolade getCsokolade() {
        return csokolade;
    }

    public Gyumolcs getGyumolcs() {
        return gyumolcs;
    }

    public Kabitoszer getKabitoszer() {
        return kabitoszer;
    }

    /**
     * Megjeleniti a bolt kinalatat a jatekosnak, megkerdezi hogy mit szeretne venni, es ha a felfedezonek van eleg
     * aranya, a megfelelo input utan levonja az arat a felfedezo vagyonabol es a targyat az inventoryba helyezi.
     * @param felfedezo a felfedezo, aki vasarol
     */
    public void vasarlas(Felfedezo felfedezo){
        boolean hiba = false;
        boolean vege = false;
        Scanner be = new Scanner(System.in);
        String valasz;

        do{
            hiba = false;
            vege = false;

            System.out.println("----------");
            System.out.println("   Bolt   ");
            System.out.println("----------");
            System.out.println();
            System.out.println(this.printBolt(felfedezo.getCsapat()));
            System.out.println(felfedezo.getInventory().toString(felfedezo.getCsapat()));
            System.out.println("Arany: " + felfedezo.getArany());
            System.out.print("Mit szeretnél venni? (pl. 'hús' vagy 'semmit') ");
            valasz = be.nextLine();
            valasz = valasz.toLowerCase();

            switch (valasz) {
                case "csokoládé":
                    if(elad(csokolade, felfedezo)) {
                        csokolade.csokkentDb();
                        felfedezo.getInventory().belerak(felfedezo.getInventory().getCsokolade());
                    } break;
                case "gyümölcs":
                    if(elad(gyumolcs, felfedezo)) {
                        gyumolcs.csokkentDb();
                        felfedezo.getInventory().belerak(felfedezo.getInventory().getGyumolcs());
                    } break;
                case "hús":
                    if(elad(hus, felfedezo)) {
                        hus.csokkentDb();
                        felfedezo.getInventory().belerak(felfedezo.getInventory().getHus());
                    } break;
                case "kábítószer":
                    if(elad(kabitoszer, felfedezo)) {
                        kabitoszer.csokkentDb();
                        felfedezo.getInventory().belerak(felfedezo.getInventory().getKabitoszer());
                    } break;
                case "whiskey":
                    if(elad(whiskey, felfedezo)) {
                        whiskey.csokkentDb();
                        felfedezo.getInventory().belerak(felfedezo.getInventory().getWhiskey());
                    }  break;
                case "kötél":
                    if(elad(kotel, felfedezo)) {
                        kotel.csokkentDb();
                        felfedezo.getInventory().belerak(felfedezo.getInventory().getKotel());
                    }  break;
                case "bozótvágó":
                    if(elad(bozotvago, felfedezo)) {
                        bozotvago.csokkentDb();
                        felfedezo.getInventory().belerak(felfedezo.getInventory().getBozotvago());
                    } break;
                case "fáklya":
                    if(elad(faklya, felfedezo)) {
                        faklya.csokkentDb();
                        felfedezo.getInventory().belerak(felfedezo.getInventory().getFaklya());
                    } break;
                case "üveggolyó":
                    if(elad(uveggolyo, felfedezo)) {
                        uveggolyo.csokkentDb();
                        felfedezo.getInventory().belerak(felfedezo.getInventory().getUveggolyo());
                    } break;
                case "semmit":
                    vege = true; break;
                default:
                    System.out.println("Hibás bemenet!");
                    hiba = true;
                    break;
            }
        } while (hiba || !vege);
    }

    /**
     * Egy arucikket elad a bolt a felfedezonek, ha annak van hozza eleg aranya.
     * @param mit az eladando arucikk
     * @param felfedezo a felfedezo, aki megprobalja megvenni az arucikket
     * @return igaz, ha az eladas sikeresen megtortent
     */
    public boolean elad (Item mit, Felfedezo felfedezo){
        boolean sikeres = false;

        if (mit.getDb() > 0 && felfedezo.getArany() >= mit.filteredGetAr(felfedezo.getCsapat())){
            felfedezo.setArany(felfedezo.getArany() - mit.filteredGetAr(felfedezo.getCsapat()));
            sikeres = true;
        } else if (mit.getDb() == 0){
            System.out.println("Ez az árucikk elfogyott, vagy itt nem kapható!");
        } else if (felfedezo.getArany() < mit.filteredGetAr(felfedezo.getCsapat())){
            System.out.println("Nincs elég aranyad!");
        }
        return sikeres;
    }


    /**
     * A bolt tipusanak megfeleloen felajanl a jatekosnak egy csapattagot.<br>
     * Falubeli bolt eseten 20% esellyel: Felderito, Saman, vagy Bolcs<br>
     * Kuldeteskozi bolt eseten mindig: Kereskedo, Katona, vagy Szamar<br>
     * Ha a jatekos fel akarja venni a csapattagot, akkor ha van eleg hely a csapatban, es a felfedezonek eleg aranya,
     * belepteti az uj csapattagot.
     * @param felfedezo a felfedezo, akinek felajanlja az uj csapattagot
     */
    public void csapattagotAjanl(Felfedezo felfedezo){
        Csapattars ujcsapattars = null;
        Random rand = new Random();
        Scanner be = new Scanner(System.in);
        String valasz;
        int random = rand.nextInt(3);

        if(falu){
            if (felfedezo.getViszony() >= 2 && rand.nextInt(100) < 20){
                if(random == 0){
                    ujcsapattars = new Felderito();
                } else if (random == 1){
                    ujcsapattars = new Saman();
                } else {
                    ujcsapattars = new Bolcs();
                }
            }
        } else {
            if(random == 0){
                ujcsapattars = new Kereskedo();
            } else if (random == 1){
                ujcsapattars = new Katona();
            } else {
                ujcsapattars = new Szamar();
            }
        }
        if (ujcsapattars != null) {
            System.out.println(felfedezo.getCsapat().toString());
            System.out.println("Új felvehető csapattag: " + ujcsapattars.toString() + " (150 arany)");

            do {
                System.out.print("Fel szeretnéd venni? (i/n) ");
                valasz = be.nextLine();
                if ("i".equals(valasz)) {
                    ujcsapattars.belep(felfedezo);
                } else if (!"n".equals(valasz)) {
                    System.out.println("Hibás bemenet!");
                }
            } while (!"i".equals(valasz) && !"n".equals(valasz));
        }
    }

    /**
     * Kilistazza a bolt elerheto kinalatat, az aruk mennyiseget, es arat.
     * @param csapat a csapat, ami befolyasolhatja az arakat
     * @return a bolt kinalata Stringben
     */
    public String printBolt(Csapat csapat) {
        StringBuilder result = new StringBuilder();

        result.append("Kötél x").append(kotel.getDb())
              .append("      ").append(kotel.filteredGetAr(csapat)).append(" arany").append(System.lineSeparator());
        result.append("Fáklya x").append(faklya.getDb())
                .append("     ").append(faklya.filteredGetAr(csapat)).append(" arany").append(System.lineSeparator());
        result.append("Hús x").append(hus.getDb())
                .append("        ").append(hus.filteredGetAr(csapat)).append(" arany").append(System.lineSeparator());

        if(falu){
            result.append("Gyümölcs x").append(gyumolcs.getDb())
                    .append("   ").append(gyumolcs.filteredGetAr(csapat)).append(" arany").append(System.lineSeparator());
            result.append("Kábítószer x").append(kabitoszer.getDb())
                    .append(" ").append(kabitoszer.filteredGetAr(csapat)).append(" arany").append(System.lineSeparator());
        } else {
            result.append("Bozótvágó x").append(bozotvago.getDb())
                    .append("  ").append(bozotvago.filteredGetAr(csapat)).append(" arany").append(System.lineSeparator());
            result.append("Üveggolyó x").append(uveggolyo.getDb())
                    .append("  ").append(uveggolyo.filteredGetAr(csapat)).append(" arany").append(System.lineSeparator());
            result.append("Whiskey x").append(whiskey.getDb())
                    .append("    ").append(whiskey.filteredGetAr(csapat)).append(" arany").append(System.lineSeparator());
            result.append("Csokoládé x").append(csokolade.getDb())
                    .append("  ").append(csokolade.filteredGetAr(csapat)).append(" arany").append(System.lineSeparator());
        }

        return result.toString();
    }
}
