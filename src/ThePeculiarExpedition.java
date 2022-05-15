import FoElemek.Felfedezo;
import FoElemek.Kuldetes;
import FoElemek.Palya;
import FoElemek.Rivalis;

/**
 * A futtathato main metodust megvalosito osztaly.
 */
public class ThePeculiarExpedition {

    /**
     * A jatek 5 kuldetesre van osztva, az ezek soran es kozott torteno dolgokat megvalosito fuggvenyek vannak sorban meghivva benne.<br>
     * Ha valahol game over kovetkezik be, ezt jelzi a jatekosnak, majd a program futasa leall.
     * Ha nem kovetkezik be game over, a program 5 kuldetes lefolyasa utan leall.
     * @param args nincs funkcioja
     */
    public static void main(String[] args) {
        Felfedezo felfedezo = new Felfedezo();
        Palya palya = new Palya();
        Rivalis[] rivalisok = new Rivalis[3];
        rivalisok[0] = new Rivalis("Columbus");
        rivalisok[1] = new Rivalis("Magellan ");
        rivalisok [2] = new Rivalis("Marco Polo");


        for(int kuldetes=1; kuldetes<=5; kuldetes++) {
            if(kuldetes != 1){
                felfedezo.kuldkozHiresLesz(palya);
                felfedezo.kuldkozKincsrolDont(palya);
                Rivalis.hirnevNo(rivalisok, palya);
            }
            Rivalis.rangsorKiiratas(rivalisok, felfedezo);
            System.out.println();
            palya = new Palya();
            palya.ujTerkepLetrehoz(kuldetes);
            felfedezo.kuldkozVasarol();
            System.out.println();
            System.out.println("-----------------------");
            System.out.println("      " + kuldetes + ". küldetés");
            System.out.println("-----------------------");
            felfedezo.posBeallit(palya.getTerkep());
            palya.tavatMeghiv();
            if(Kuldetes.kuldetestVezerel(palya, felfedezo)){
                System.out.println("A Felfedező elhagyta a csapatot!" + System.lineSeparator());
                System.out.println("----------------------------------");
                System.out.println("            GAME OVER             ");
                System.out.println("----------------------------------");
                return;
            }
        }
        felfedezo.kuldkozHiresLesz(palya);
        felfedezo.kuldkozKincsrolDont(palya);
        Rivalis.hirnevNo(rivalisok, palya);
        Rivalis.rangsorKiiratas(rivalisok, felfedezo);
    }
}
