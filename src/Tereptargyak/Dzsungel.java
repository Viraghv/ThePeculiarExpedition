package Tereptargyak;

import Inventory.Inventory;

import java.util.Scanner;

/**
 * A Dzsungel mezot megvalosito osztaly.<br>
 * Egy dzsungelt le lehet vagni.
 */
public class Dzsungel extends Mezo{

    /**
     * A Dzsungel egy jarhato mezo.
     */
    public Dzsungel() {
        super();
        this.jarhato = true;
    }

    /**
     * Dzsungelen tartozkodva a jatekos donthet, hogy levagja a dzsungelt, ha van nala bozotvago.<br>
     * Ha levagja, az inventorybol kikerul egy bozotvago.
     * @param inventory a felfedezo inventoryja, aki a dzsungelen tartozkodik
     * @return igaz, ha a dzsungelt sikeresen levagtak
     */
    public boolean levagjak(Inventory inventory){
        boolean levagtak = false;

        if(inventory.getBozotvago().getDb() > 0){
            Scanner be = new Scanner(System.in);
            String valasz = null;

            while (!("i".equals(valasz) || "n".equals(valasz))) {
                System.out.print("Szeretnél bozótvágót használni? (i/n) ");
                valasz = be.nextLine();

                if (!("i".equals(valasz) || "n".equals(valasz))){
                    System.out.println("Hibás bemenet!");
                }
            }

            if ("i".equals(valasz)) {
                inventory.kivesz(inventory.getBozotvago());
                levagtak = true;
            }
        }

        return levagtak;
    }

    @Override
    public String toString() {
        return !lava ? "Y" : "L";
    }

    @Override
    public void tajekoztat() {
        System.out.println(!lava ? "Az aktuális mező egy dzsungel. (" + (this.isVizes() ? "nedves" : "száraz") + ")" :
                            "Az aktuális mező lávával borított.");
    }
}
