package Csapat;

/**
 * A Kereskedo csapattarsat megvalosito osztaly.<br>
 * A Kereskedo elonyet kezeli.
 */
public class Kereskedo extends Csapattars{

    public String nev (){
        return "Kereskedő";
    }

    /**
     * 5 arannyal csokkenti egy arucikk arat.
     * @param ar az ar eredeti erteke
     * @return az ar csokkentett erteke
     */
    public int itemAratCsokkent(int ar) {
        return ar - 5;
    }


    /**
     * 50 arannyal noveli a kincs eladasi arat.
     * @param ar a kincs eredeti ara
     * @return a kincs megnovelt ara
     */
    public int kincsAratNovel (int ar){
        return ar + 50;
    }

}
