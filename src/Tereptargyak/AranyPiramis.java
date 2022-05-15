package Tereptargyak;

/**
 * Az Arany Piramis mezot megvalito osztaly.<br>
 * Egy arany piramisrol tudjuk, hogy mar megtalaltak-e.
 */
public class AranyPiramis extends Mezo{
    private boolean megtalalt;

    /**
     * Az Arany Piramis jarhato, es kezdetben nincs megtalalva.
     */
    public AranyPiramis() {
        super();
        this.jarhato = true;
        this.megtalalt = false;
    }

    public boolean isMegtalalt() {
        return megtalalt;
    }

    @Override
    public String toString() {
        return !lava ? "$" : "L";
    }

    @Override
    public void tajekoztat() {
        System.out.println(!lava ? "Az aktuális mező az Arany piramis! (" + (this.isVizes() ? "nedves" : "száraz") + ")" :
                                   "Az aktuális mező lávával borított.");
    }

    /**
     * A "megtalalt" tulajdonsagat igazra allitja.
     */
    public void megtalaljak(){
        megtalalt = true;
    }
}
