package Tereptargyak;

import FoElemek.Bolt;

/**
 * A Falu mezot megvalosito osztaly.<br>
 * A falvakban van egy bolt.
 */
public class Falu extends Mezo{

    private Bolt bolt;

    /**
     * A falu jarhato, es a bennuk levo bolt egy falu tipusu bolt.
     */
    public Falu() {
        super();
        this.jarhato = true;
        this.bolt = new Bolt("falu");
    }

    public Bolt getBolt() {
        return bolt;
    }

    public void setBolt(Bolt bolt) {
        this.bolt = bolt;
    }

    @Override
    public String toString() {
        return !lava ? "#" : "L";
    }

    @Override
    public void tajekoztat() {
        System.out.println(!lava ? "Az aktuális mező egy falu. (" + (this.isVizes() ? "nedves" : "száraz") + ")" :
                            "Az aktuális mező lávával borított.");
    }
}
