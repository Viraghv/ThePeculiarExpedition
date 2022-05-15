package Inventory;

/**
 * A Whiskey etelt megvalosito osztaly.
 */
public class Whiskey extends Etel{

    /**
     * A Whiskey 20 energiat tolt vissza.<br>
     * Az inventoryban ilyen formaban jon letre a Whiskey.
     */
    public Whiskey() {
        super();
        this.pluszEnergia = 20;
    }

    /**
     * A Whiskey alapertelmezett ara 35 arany.<br>
     * A boltokban ilyen formaban jon letre a Whiskey.
     * @param db ahany darab van a whiskeybol
     */
    public Whiskey (int db){
        this.db = db;
        this.ar = 35;
    }

    public String neve(){
        return "Whiskey";
    }
}
