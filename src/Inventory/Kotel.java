package Inventory;

/**
 * A Kotel targyat megvalosito osztaly.
 */
public class Kotel extends Item{

    public Kotel() {
        super();
    }

    /**
     * A kotel alapertelmezett ara 20 arany.
     * @param db ahany darab van a kotelbol
     */
    public Kotel(int db){
        this.db = db;
        this.ar = 20;
    }

    public String neve(){
        return "Kötél";
    }
}
