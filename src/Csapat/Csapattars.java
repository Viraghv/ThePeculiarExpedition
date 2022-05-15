package Csapat;

import FoElemek.Felfedezo;
import java.util.Random;

/**
 * A csapattarsakat altalanosan megvalosito, abstract osztaly.<br>
 * Egy csapattars allapotat kezeli.
 */
public abstract class Csapattars {
    protected boolean alkoholista;
    protected boolean kabszerFuggo;
    protected boolean serult;


    /**
     * A csapattarsat kezdetben nem fuggore, es egeszsegesre allítja be.
     */
    public Csapattars() {
        this.alkoholista = false;
        this.kabszerFuggo = false;
        this.serult = false;
    }


    public boolean isSerult() {
        return serult;
    }

    public void setSerult(boolean serult) {
        this.serult = serult;
    }

    /**
     * Ha van meg hely a csapatban, es a felfedezonek van 150 aranya, akkor azt abbol levonja, es a csapattag a csapat
     * resze lesz.
     * @param felfedezo a felfedezo, aki fizet a csapattagert
     */
    public void belep(Felfedezo felfedezo){
        boolean tele = true;
        boolean elegArany = true;

        for(int i=0; i<3; i++){
            if(felfedezo.getCsapat().getCsapattarsak()[i] == null){
                tele = false;
                if(felfedezo.getArany() >= 150){
                    felfedezo.getCsapat().getCsapattarsak()[i] = this;
                    felfedezo.setArany(felfedezo.getArany()-150);
                } else {
                    elegArany = false;
                }
                break;
            }
        }

        if(tele){
            System.out.println("Már nincs több szabad hely a csapatodban!");
        }
        if(!elegArany){
            System.out.println("Nincs elég aranyad!");
        }
    }

    /**
     * Kiveszi a csapattarsat a csapatbol.
     * @param csapat a csapat, amit elhagy a csapattag
     */
    public void elhagy(Csapat csapat){
        for(int i=0; i<3; i++){
            if(csapat.getCsapattarsak()[i] == this){
                csapat.getCsapattarsak()[i] = null;
                System.out.println("Egy " + this.nev() + " elhagyta a csapatot.");
                break;
            }
        }

        for(int i=0; i<3; i++){
            for(int j=0; j<2; j++) {
                if (csapat.getCsapattarsak()[j] == null && csapat.getCsapattarsak()[j + 1] != null) {
                    csapat.getCsapattarsak()[j] = csapat.getCsapattarsak()[j + 1];
                    csapat.getCsapattarsak()[j + 1] = null;
                }
            }
        }
    }


    /**
     * Ha a csapattag fuggo, és 30 lepes ota nem kapott a kivalto szerbol, akkor azt 10% esellyel kilepteti a csapatbol.<br>
     * Ha a felfedezo kilepett, jelzi a jatek veget.
     * @param felfedezo aki fuggoseg miatt kilephet
     * @return igaz, ha a felfedezo elhagyta a csapatot
     */
    public boolean elvonasbanSzenved(Felfedezo felfedezo){
        boolean gameOver = false;

        if((this.isAlkoholista() && felfedezo.getWhiskeyUtoljara() >= 30) ||
           (this.isKabszerFuggo() && felfedezo.getKabszerUtoljara() >= 30)){
            Random rand = new Random();
            int random = rand.nextInt(100);

            if(random < 10){
                if(this instanceof Felfedezo){
                    gameOver = true;
                } else {
                    this.elhagy(felfedezo.getCsapat());
                }
            }
        }

        return gameOver;
    }

    /**
     * A csapattag serultseget igazra allitja, és errol kiirassal is tajekoztat.
     */
    public void leserul(){
        this.serult = true;

        if(this instanceof Felfedezo){
            System.out.println("A Felfedező lesérült.");
        } else {
            System.out.println("Egy " + this.nev() +" lesérült.");
        }
    }

    public boolean isAlkoholista() {
        return alkoholista;
    }

    public boolean isKabszerFuggo() {
        return kabszerFuggo;
    }

    public void setAlkoholista(boolean alkoholista) {
        this.alkoholista = alkoholista;
    }

    public void setKabszerFuggo(boolean kabszerFuggo) {
        this.kabszerFuggo = kabszerFuggo;
    }

    /**
     * Megadja a konkret csapattars elnevezeset.
     * @return a csapattars neve Stringben
     */
    public abstract String nev ();

    /**
     * Stringben megadja a csapattars elnevezeset es allapotat.
     * @return a csapattars neve es allapota Stringben
     */

    public String toString() {
        return this.nev() + (alkoholista ? "  (Alkoholista)" : "") + (kabszerFuggo ? "  (Kábítószerfüggő)" : "") +
                (serult ? "  (Sérült)" : "");
    }

}

