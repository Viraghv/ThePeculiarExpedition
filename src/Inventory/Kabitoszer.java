package Inventory;

/**
 * A Kabitoszer etelt megvalosito osztaly.
 */
public class Kabitoszer extends Etel{

    /**
     * A Kabitoszer 20 energiat tolt vissza.<br>
     * Az inventoryban ilyen formaban jon letre a Kabitoszer.
     */
    public Kabitoszer() {
        super();
        this.pluszEnergia = 20;
    }

    /**
     * A Kabitoszer alapertelmezett ara 35 arany.<br>
     * A boltokban ilyen formaban jon letre a Kabitoszer.
     * @param db ahany darab van a kabitoszerbol
     */
    public Kabitoszer (int db){
        this.db = db;
        this.ar = 35;
    }

    public String neve(){
        return "Kábítószer";
    }
}
