package Csapat;

/**
 * A Felderíto csapattarsat megvalosito osztaly.<br>
 * A Felderíto elonyet kezeli.
 */
public class Felderito extends Csapattars {

    /**
     * 1-el noveli a latokort.
     * @param latokor latokor eredeti erteke
     * @return a megnovelt latokor erteke
     */
    public int latokortNovel (int latokor){
        return latokor + 1;
    }

    public String nev (){
        return "Felderítő";
    }

}
