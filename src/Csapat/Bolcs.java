package Csapat;

/**
 * A Bolcs csapattarsat megvalosito osztaly.<br>
 * A Bolcs elonyet kezeli.
 */

public class Bolcs extends Csapattars{

    /**
     * 3-al noveli a viszonyt.
     * @param viszony a viszony eredeti erteke
     * @return a viszony megnovelt erteke
     */
    public int viszonytNovel(int viszony){
        return viszony + 3;
    }

    public String nev (){
        return "Bölcs";
    }

}
