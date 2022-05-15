package Inventory;

/**
 * A Bozotvago targyat megvalosito osztaly.
 */
public class Bozotvago extends Item{

    public Bozotvago() {
        super();
    }

    /**
     * A bozotvago alapertelmezett ara 50 arany.
     * @param db ahany darab van a bozotvagobol
     */
    public Bozotvago (int db){
        this.db = db;
        this.ar = 50;
    }

    public String neve(){
        return "Bozótvágó";
    }
}
