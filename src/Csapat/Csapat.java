package Csapat;

import FoElemek.Felfedezo;
import java.util.Random;

/**
 * Megvalositja a max. 3 csapattarsbol allo csapatot.
 * Egy csapat kulonbozo okokbol torteno letszamvaltozasait kezeli.
 */
public class Csapat {
    private Csapattars[] csapattarsak;

    /**
     * A csapat merete 3 lesz, igy max. 3 csapattars fer bele.
     */
    public Csapat() {
        this.csapattarsak = new Csapattars[3];
    }

    public Csapattars[] getCsapattarsak() {
        return csapattarsak;
    }

    public void setCsapattarsak(Csapattars[] csapattarsak) {
        this.csapattarsak = csapattarsak;
    }


    /**
     * Ha a Felfedezo energiaja 0, akkor minden csapattarsat 8% esellyel kivesz a csapatbol<br>
     * Ha a csapat mar ures, akkor a felfedezo is elhagyhatja 8% esellyel a csapatot.<br>
     * Utobbi esetben megallapitja, hogy veget ert a jatek es vesztettunk.
     * @param felfedezo a felfedezo akinek az energiajat vizsgaljuk
     * @return igaz, ha a felfedezo elhagyta a csapatot
     */
    public boolean energiaMiattCsokken (Felfedezo felfedezo) {
        boolean gameOver = false;
        Random rand = new Random();
        int random;

        if(felfedezo.getEnergia() == 0){
            if(this.ures()){
                random = rand.nextInt(100);
                if(random < 8){
                    gameOver = true;
                }
            } else {
                for (Csapattars csapattars : csapattarsak) {
                    random = rand.nextInt(100);
                    if (csapattars != null && random < 8) {
                        csapattars.elhagy(this);
                    }
                }
            }
        }

        return gameOver;
    }

    /**
     * Megallapitja, hogy ures-e a csapat.
     * @return igaz, ha a csapatban nincs csapattars
     */
    public boolean ures (){
        boolean empty = true;

        for (Csapattars csapattars : csapattarsak) {
            if (csapattars != null) {
                empty = false;
                break;
            }
        }

        return empty;
    }


    /**
     * Megszamolja, hany csapattars van a csapatban.
     * @return csapattarsak szama
     */
    public int csapatMeret(){
        int db = 0;
        for(int i=0; i<this.getCsapattarsak().length; i++){
            if(this.getCsapattarsak()[i] != null){
                db++;
            }
        }
        return db;
    }

    /**
     * Ha az utolso 2 fogyasztott elelmiszer Whiskey volt, minden ujabb Whiskeynel 15% esellyel valakit a
     * csapatbol, vagy a felfedezot, alkoholistava tesz.
     * @param felfedezo a felfedezo, aki az "etkezest" iranyitja es aki alkoholistava valhat
     */
    public void alkoholistaLehetValaki(Felfedezo felfedezo){
        if(felfedezo.utolsoHaromWhiskey()){
            Random rand = new Random();
            int randomEsely = rand.nextInt(100);

            if(randomEsely < 15){
                int randomKi = rand.nextInt(csapatMeret()+1);
                if(randomKi == csapatMeret()){
                    felfedezo.setAlkoholista(true);
                    System.out.println("A Felfedező alkoholista lett.");
                } else {
                    this.getCsapattarsak()[randomKi].setAlkoholista(true);
                    System.out.println("Egy " + this.getCsapattarsak()[randomKi].nev() + " alkoholista lett.");
                }
            }
        }
    }

    /**
     * Ha az utolso 2 fogyasztott elelmiszer Kabitoszer volt, minden ujabb Kabitoszernel 15% esellyel valakit a
     * csapatbol, vagy a felfedezot, kabitoszerfuggove tesz.
     * @param felfedezo a felfedezo, aki az "etkezest" iranyitja es aki kabitoszerfuggove valhat
     */
    public void kabszerfuggoLehetValaki(Felfedezo felfedezo){
        if(felfedezo.utolsoHaromKabszer()){
            Random rand = new Random();
            int randomEsely = rand.nextInt(100);

            if(randomEsely < 15){
                int randomKi = rand.nextInt(csapatMeret()+1);
                if(randomKi == csapatMeret()){
                    felfedezo.setKabszerFuggo(true);
                    System.out.println("A Felfedező kábítószerfüggő lett.");
                } else {
                    this.getCsapattarsak()[randomKi].setKabszerFuggo(true);
                    System.out.println("Egy " + this.getCsapattarsak()[randomKi].nev() + " kábítószerfüggő lett.");
                }
            }
        }
    }


    /**
     * Miutan a fuggo csapattagok az elvonas miatt elhagyjak a csapatot, ha a felfedezo kilepett,
     * jelzi a jatek veget.
     * @param felfedezo a felfedezo aki elhagyhatja a csapatot
     * @return igaz, ha a felfedezo elhagyta a csapatot
     */
    public boolean fuggosegMiattCsokken(Felfedezo felfedezo){
        boolean gameOver = false;

        if(felfedezo.elvonasbanSzenved(felfedezo)){
               gameOver = true;
        }

        for(int i=0; i<this.getCsapattarsak().length; i++){
            if(this.getCsapattarsak()[i] != null) {
                this.getCsapattarsak()[i].elvonasbanSzenved(felfedezo);
            }
        }

        return gameOver;
    }

    /**
     * Ha a felfedezo vagy egy csapattars serult, akkor 5% esellyel kiveszi a csapatbol
     * @param felfedezo a felfedezo, aki serult lehet
     * @return igaz, ha felfedezo elhagyta a csapatot
     */
    public boolean serultsegMiattCsokken(Felfedezo felfedezo){
        boolean gameOver = false;
        Random rand = new Random();
        int random = rand.nextInt(100);

        if(felfedezo.isSerult() && random < 5){
            gameOver = true;
        }

        for(int i=0; i<this.getCsapattarsak().length; i++){
            if(this.getCsapattarsak()[i] != null) {
                random = rand.nextInt(100);
                if(this.getCsapattarsak()[i].isSerult() && random < 5) {
                    this.getCsapattarsak()[i].elhagy(this);
                }
            }
        }

        return gameOver;
    }

    /**
     * Minden csapattarsnak hamisra allitja a serultseget, meggyogyitja oket.
     */
    public void kuldkozMindenkiMeggyogyul(){
        for(int i=0; i<csapatMeret(); i++){
            this.csapattarsak[i].setSerult(false);
        }

    }

    /**
     * Kilistazza a csapattagokat.
     * @return a csapattagok listaja Stringben
     */
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();

        result.append("--------------").append(System.lineSeparator());
        result.append("    Csapat    ").append(System.lineSeparator());
        result.append("--------------").append(System.lineSeparator());

        for(int i=0; i<3; i++){
            if(csapattarsak[i] == null){
                result.append("[üres]").append(System.lineSeparator());
            } else {
                result.append(csapattarsak[i].toString()).append(System.lineSeparator());
            }
        }

        return result.toString();
    }
}
