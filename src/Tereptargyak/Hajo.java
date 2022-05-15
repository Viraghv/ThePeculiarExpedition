package Tereptargyak;

import Inventory.Inventory;

import java.util.Scanner;

/**
 * A Hajo mezot megvalosito osztaly.
 * Egy hajorol tudjuk, hogy hany darab kincset taroltunk el rajta.<br>
 * Egy hajon el lehet raktarozni kincseket.
 */
public class Hajo extends Mezo{
    private int taroltKincsek;

    /**
     * A hajo jarhato, es kezdetben nem tarolunk rajta egy kincset sem.
     */
    public Hajo() {
        super();
        this.jarhato = true;
        this.taroltKincsek = 0;
    }

    public int getTaroltKincsek() {
        return taroltKincsek;
    }

    public void setTaroltKincsek(int taroltKincsek) {
        this.taroltKincsek = taroltKincsek;
    }

    /**
     * Ha a jatekos szeretne, elraktarozhatja az inventoryban levo osszes kincset a hajoban.<br>
     * Ha igy dont, akkor a hajoban tarolt kincsek szama annyival no, ahany kincs van az inventoryban, es az inventorybol
     * kivesszuk az osszes kincset.
     * @param inventory a felfedezo inventoryja, aki eltarolja a hajon a kincseit
     */
    public void raktaroz(Inventory inventory){
        Scanner be = new Scanner(System.in);
        String valasz = null;

        if(inventory.getKincs().getDb() > 0) {
            while (!("i".equals(valasz) || "n".equals(valasz))){
                System.out.print("El szeretnéd raktározni a kincseidet? (i/n) ");
                valasz = be.nextLine();

                if(!("i".equals(valasz) || "n".equals(valasz))){
                    System.out.println("Hibás bemenet!");
                }
            }

            if("i".equals(valasz)) {
                taroltKincsek += inventory.getKincs().getDb();

                int kincsDb = inventory.getKincs().getDb();
                for(int i=0; i<kincsDb; i++){
                    inventory.kivesz(inventory.getKincs());
                }
            }
        }
    }

    @Override
    public String toString() {
        return "¤";
    }

    @Override
    public void tajekoztat() {
        System.out.println("Az aktuális mező a hajó.");
    }
}
