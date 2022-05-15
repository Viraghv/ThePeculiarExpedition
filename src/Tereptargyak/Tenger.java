package Tereptargyak;

/**
 * A Tenger mezot megvalosito osztaly.
 */
public class Tenger  extends Mezo{

    /**
     * A tenger nem jarhato.
     */
    public Tenger() {
        super();
        this.jarhato = false;
    }

    @Override
    public String toString() {
        return "~";
    }

    @Override
    public void tajekoztat() {}
}
