package Inventory;

/**
 * A Uveggolyo targyat megvalosito osztaly.
 */
public class Uveggolyo extends Item{

    public Uveggolyo(){
        super();
    }

    /**
     * Az Uveggolyo alapertelmezett ara 20 arany.
     * @param db ahany darab van az Uveggolyobol
     */
    public Uveggolyo(int db){
        this.db = db;
        this.ar = 20;
    }

    public String neve(){
        return "Üveggolyó";
    }
}
