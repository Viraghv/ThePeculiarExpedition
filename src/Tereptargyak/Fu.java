package Tereptargyak;

/**
 * A Fu mezot megvalosito osztaly.
 */
public class Fu extends Mezo{

    /**
     * A fu mezo jarhato.
     */
    public Fu() {
        super();
        this.jarhato = true;
    }

    @Override
    public String toString() {
        return !lava ? "w" : "L";
    }

    @Override
    public void tajekoztat() {
        System.out.println(!lava ?"Az aktuális mező egy füves terület. (" + (this.isVizes() ? "nedves" : "száraz") + ")" :
                                    "Az aktuális mező lávával borított.");
    }
}
