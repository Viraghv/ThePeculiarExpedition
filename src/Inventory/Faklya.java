package Inventory;

/**
 * A Faklya targyat megvalosito osztaly.
 */
public class Faklya extends Item{

    public Faklya() {
        super();
    }

    /**
     * A faklya alapertelmezett ara 50 arany.
     * @param db ahany darab van a faklyabol
     */
    public Faklya (int db){
        this.db = db;
        this.ar = 50;
    }



    public String neve(){
        return "Fáklya";
    }
}
