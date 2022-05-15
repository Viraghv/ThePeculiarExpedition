package Csapat;

/**
 * A Katona csapattarsat megvalosito osztaly.<br>
 * A Katona elonyet kezeli.
 */
public class Katona extends Csapattars {
    /**
     * 20%-al noveli a Whiskey plusz energiajat.
     * @param pluszEnergia a Whiskey plusz energiajanak eredeti erteke
     * @return a megnovelt plusz energia
     */
    public double whiskeytNovel(double pluszEnergia){
        return pluszEnergia * 1.2;
    }

    public String nev (){
        return "Katona";
    }


}
