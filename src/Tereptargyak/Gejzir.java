package Tereptargyak;

import FoElemek.Felfedezo;
import FoElemek.Palya;

/**
 * A Gejzir mezot megvalosito osztaly.<br>
 * Egy gejzirrol tudjuk, hogy meg aktiv-e, es hogy eppen maga korul milyen hatotavban aztatja a kornyezo mezoket.<br>
 * Egy mezo gejzirre tud alakulni, es a gejzir vizet terjeszt.
 */
public class Gejzir extends Mezo{

    private int aktualisVizHatotav;
    private boolean aktiv;

    /**
     * A gejzir jarhato, mindig vizes, letrejottekor aktiv es kezdetben 0 hatotavu.
     */
    public Gejzir() {
        super();
        this.jarhato = true;
        this.vizes = true;
        this.aktualisVizHatotav = 0;
        this.aktiv = true;
    }

    /**
     * A felfedezo kozvetlen kornyezeteben egy Sima Fold, Fu, vagy Dzsungel mezo Gejzirre alakul.
     * @param terkep az aktualis terkep, amin a felfedezo tartozkodik
     * @param felfedezo a felfedezo, aki kivaltotta a gejzirkitorest
     */
    public static void kornyezoMezoGejzirreAlakul(Mezo[][] terkep, Felfedezo felfedezo){
        boolean kitort = false;

        for(int a = -1; a<=1; a++){
            for(int b = -1; b<=1; b++){
                if(felfedezo.getPos_x()+a>=0 && felfedezo.getPos_y()+b>=0 && felfedezo.getPos_x()+a < 16 && felfedezo.getPos_y()+b < 16) {
                    if(terkep[felfedezo.getPos_x() + a][felfedezo.getPos_y() + b] instanceof SimaFold ||
                       terkep[felfedezo.getPos_x() + a][felfedezo.getPos_y() + b] instanceof Fu ||
                       terkep[felfedezo.getPos_x() + a][felfedezo.getPos_y() + b] instanceof Dzsungel) {
                            terkep[felfedezo.getPos_x() + a][felfedezo.getPos_y() + b] = new Gejzir();
                            kitort = true;
                            break;
                    }
                }
            }
            if(kitort){
                break;
            }
        }
    }

    /**
     * Az aktiv gejzir az aktualis hatotavjanak megfelelo korben vizesse teszi a kornyezo nem To, Tenger, vagy Hegy mezoket.<br>
     * Ezutan megnoveli a gejzir hatotavjat. Ha ezutan a hatotav nagyobb mint 4, akkor a gejzir nem lesz tovabb aktiv,
     * azaz hatokore nem terjed tovabb.
     * @param palya a palya, amin a gejzir van
     * @param i a gejzir "i" pozicioja a palyan (x)
     * @param j a gejzir "j" pozicioja a palyan (y)
     */
    public void vizetTerjeszt(Palya palya, int i, int j){
        if(aktiv) {
            for (int a = -aktualisVizHatotav; a <= aktualisVizHatotav; a++) {
                for (int b = -aktualisVizHatotav; b <= aktualisVizHatotav; b++) {
                    if (i + a >= 0 && j + b >= 0 && i + a < 16 && j + b < 16) {
                        if(!(palya.getTerkep()[i + a][j + b] instanceof To) &&
                           !(palya.getTerkep()[i + a][j + b] instanceof Tenger) &&
                           !(palya.getTerkep()[i + a][j + b] instanceof Hegy))
                        palya.getTerkep()[i + a][j + b].setVizes(true);
                    }
                }
            }
            aktualisVizHatotav++;
            if (aktualisVizHatotav > 4) {
                aktiv = false;
            }
        }
    }

    /**
     * A palyan levo osszes aktiv gejzir aztatja a korulotte levo teruleteket.
     * @param palya a palya, amin a gejzireket keressuk
     */
    public static void aktivGejzirekVizetTerjesztenek (Palya palya){
        for(int i = 0; i<16; i++){
            for(int j=0; j<16; j++){
                if(palya.getTerkep()[i][j] instanceof Gejzir){
                    ((Gejzir) palya.getTerkep()[i][j]).vizetTerjeszt(palya, i, j);
                }
            }
        }
    }

    @Override
    public void tajekoztat() {
        System.out.println(!lava ? "Az aktuális mező egy gejzír. (" + (this.isVizes() ? "nedves" : "száraz") + ")" :
                                    "Az aktuális mező lávával borított.");
    }

    @Override
    public String toString() {
        return !lava ? "G" : "L"; }
}
