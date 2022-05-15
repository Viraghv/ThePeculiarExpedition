package Inventory;

/**
 * A Csokolade etelt megvalosito osztaly.
 */
public class Csokolade extends Etel{

    /**
     * A Csokolade 20 energiat tolt vissza.<br>
     * Az inventoryban ilyen formaban jon letre a Csokolade.
     */
    public Csokolade() {
        super();
        this.pluszEnergia = 20;
    }

    /**
     * A Csokolade alapertelmezett ara 40 arany.<br>
     * A boltokban ilyen formaban jon letre a Csokolade.
     * @param db ahany darab van a csokoladebol
     */
    public Csokolade(int db){
        this.db = db;
        this.ar = 40;
    }


    public String neve(){
        return "Csokoládé";
    }
}
