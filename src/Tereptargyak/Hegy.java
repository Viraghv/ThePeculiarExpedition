package Tereptargyak;

import FoElemek.Felfedezo;
import FoElemek.Palya;

/**
 * A Hegy mezot megvalosito osztaly.<br>
 * Egy hegy vulkanna tud alakulni.
 */
public class Hegy extends Mezo{

    /**
     * A hegy nem jarhato.
     */
    public Hegy() {
        super();
        this.jarhato = false;
    }

    /**
     * Megadja a felfedezohoz legkozelebbi hegyet.<br>
     * A tavolsagot "legvonalban", Pitagorasz-tetel segitsegevel szamitja ki.
     * @param palya a palya, amin a hegyet keressuk
     * @param felfedezo a felfedezo, akihez a legkozelebbi hegyet keressuk
     * @return a felfedezohoz legkozelebbi hegy
     */
    public static Hegy legkozelebbiHegy (Palya palya, Felfedezo felfedezo){
        double minTavolsag = Double.MAX_VALUE;
        double tavolsag;
        double a, b;
        Hegy legkozelebbi = new Hegy();

        for(int i=0; i<16; i++){
            for(int j=0; j<16; j++){
                if(palya.getTerkep()[i][j] instanceof Hegy){
                    a =  Math.abs(i-felfedezo.getPos_x());
                    b = Math.abs(j-felfedezo.getPos_y());
                    tavolsag = Math.sqrt(a*a + b*b);
                    if(tavolsag < minTavolsag){
                        minTavolsag = tavolsag;
                        legkozelebbi = (Hegy) palya.getTerkep()[i][j];
                    }
                }
            }
        }
        return legkozelebbi;
    }

    /**
     * A felfedezokoz legkozelebb levo hegyet vulkanna alakitja.
     * @param palya a palya, amin a hegyet keressuk
     * @param felfedezo a felfedezo, akihez a legkozelebbi hegyet keressuk
     */
    public static void legkozelebbiHegyVulkannaAlakul (Palya palya, Felfedezo felfedezo){
        Hegy hegy = legkozelebbiHegy(palya, felfedezo);

        for(int i=0; i<16; i++){
            for(int j=0; j<16; j++){
                if(palya.getTerkep()[i][j] == hegy){
                    palya.getTerkep()[i][j] = new Vulkan();
                }
            }
        }
    }

    @Override
    public String toString() {
        return "A";
    }

    @Override
    public void tajekoztat() {}
}
