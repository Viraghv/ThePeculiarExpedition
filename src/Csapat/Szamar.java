package Csapat;
/**
 * A Szamar csapattarsat megvalosíto osztaly.<br>
 * A Szamar elonyet kezeli.
 */
public class Szamar extends Csapattars {

    /**
     * 2-vel noveli az Inventory meretet.
     * @param maxSlot az Inventory max slot-szamanak eredeti erteke
     * @return a megnovelt meret
     */
    public int inventorytBovit (int maxSlot){
        return maxSlot + 2;
    }

    public String nev (){
        return "Szamár";
    }

}
