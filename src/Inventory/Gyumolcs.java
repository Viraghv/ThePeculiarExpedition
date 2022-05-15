package Inventory;

/**
 * A Gyumolcs etelt megvalosito osztaly.
 */
public class Gyumolcs extends Etel{

    /**
     * A Gyumolcs 15 energiat tolt vissza.<br>
     * Az inventoryban ilyen formaban jon letre a Gyumolcs.
     */
    public Gyumolcs() {
        super();
        this.pluszEnergia = 15;
    }

    /**
     * A Gyumolcs alapertelmezett ara 30 arany.<br>
     * A boltokban ilyen formaban jon letre a Gyumolcs.
     * @param db ahany darab van a gyumolcsbol
     */
    public Gyumolcs (int db){
        this.db = db;
        this.ar = 30;
    }

    public String neve(){
        return "Gyümölcs";
    }
}
