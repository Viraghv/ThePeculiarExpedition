package Tereptargyak;

/**
 * A Sima Foldterulet mezot megvalosito osztaly.
 */
public class SimaFold extends Mezo{

    /**
     * A sima foldterulet jarhato.
     */
    public SimaFold() {
        super();
        this.jarhato = true;
    }

    @Override
    public String toString() {
        return !lava ? "-" : "L";
    }

    @Override
    public void tajekoztat() {
        System.out.println(!lava ? "Az aktuális mező egy sima földterület. (" + (this.isVizes() ? "nedves" : "száraz") + ")":
                                    "Az aktuális mező lávával borított.");
    }
}
