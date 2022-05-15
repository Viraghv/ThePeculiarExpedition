package Inventory;

/**
 * A Hus etelt megvalosito osztaly.
 */
public class Hus extends Etel{

    /**
     * A Hus 25 energiat tolt vissza.<br>
     * Az inventoryban ilyen formaban jon letre a Hus.
     */
    public Hus() {
        super();
        this.pluszEnergia = 25;
    }

    /**
     * A Hus alapertelmezett ara 50 arany.<br>
     * A boltokban ilyen formaban jon letre a Hus.
     * @param db ahany darab van a husbol
     */
    public Hus (int db){
        this.db = db;
        this.ar = 50;
    }

    public String neve(){
        return "Hús";
    }
}
