package Tereptargyak;

/**
 * A To mezot megvalosito osztaly.<br>
 * A tavak aztatjak maguk korul a teruletet.
 */
public class To extends Mezo{

    /**
     * A to nem jarhato.
     */
    public To() {
        super();
        this.jarhato = false;
    }

    /**
     * A to vizesse teszi a kornyezeteben levo Arany Piramis, Barlang, Fu, Falu, Oltar, es Sima Fold teruleteket.
     * @param palya a palya, amin a to van
     * @param i a to "i" pozicioja a palyan (x)
     * @param j a to "j" pozicioja a palyan (y)
     */
    public void aztat(Mezo[][] palya, int i, int j){
        for(int a = -1; a<=1; a++){
            for(int b = -1; b<=1; b++){
                if(i+a>=0 && j+b>=0 && i+a < 16 && j+b < 16) {
                    if(palya[i + a][j + b] instanceof AranyPiramis ||
                       palya[i + a][j + b] instanceof Barlang ||
                       palya[i + a][j + b] instanceof Fu ||
                       palya[i + a][j + b] instanceof Falu ||
                       palya[i + a][j + b] instanceof Oltar ||
                       palya[i + a][j + b] instanceof SimaFold){

                            palya[i + a][j + b].vizes = true;
                    }
                }
            }
        }
    }

    @Override
    public void tajekoztat() {}

    @Override
    public String toString() {
        return "~";
    }
}
