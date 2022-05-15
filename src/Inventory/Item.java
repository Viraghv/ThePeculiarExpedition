package Inventory;

import Csapat.*;

/**
 * A targyakat altalanosan megvalosito abstract osztaly.<br>
 * A targynak van darabszama, azaz hogy az aktualis kontextusban hany darab van belole (pl. inventoryban, boltban),
 * illetve alapertelmezett ara.<br>
 */
public abstract class Item {
    protected int db;
    protected int ar;

    public Item() {
        this.db = 0;
    }

    public int getDb() {
        return db;
    }

    /**
     * Mivel a csapat tagjai befolyasolhatjak a arakat, ezert az ar erteket ahhoz igazitva adja vissza.
     * @param csapat a csapat, melynek tagjai hatassal lehetnek az arakra
     * @return a targy aranak tenyleges erteke
     */
    public int filteredGetAr(Csapat csapat) {
        int ar = this.ar;
        for (int i = 0; i<csapat.getCsapattarsak().length; i++){
            if(csapat.getCsapattarsak()[i] instanceof Kereskedo){
                ar = ((Kereskedo) csapat.getCsapattarsak()[i]).itemAratCsokkent(ar);
            }
        }
        return ar;
    }

    public void setDb(int db) {
        this.db = db;
    }

    public void setAr(int ar) {
        this.ar = ar;
    }

    /**
     * A targy darabszamat eggyel noveli.
     */
    public void novelDb(){
        this.db++;
    }

    /**
     * A targy darabszamat eggyel csokkenti.
     */
    public void csokkentDb() {this.db--;}

    /**
     * A gyermekosztalyban felulirva megadja a specifikus targy elnevezeset.
     * @return a specifikus targy elnevezese Stringben
     */
    public abstract String neve();

    /**
     * Kiirathato modon visszaadja a targy nevet es hogy hany darab van belole. Ha a darabszam meghaladja a 7-et, akkor
     * a maradekot uj sorban megegyszer kirja (egeszen addig amig a teljes darabszam szerepel).
     * @param csapat a csapat, melynek tagjai hatassal lehetnek az etelek plusz energiajara
     * @return a targy neve es darabszama hetesevel, Stringben
     */

    public String toString(Csapat csapat) {
        if(db == 0){
            return "";
        } else {
            int megVan = db;
            StringBuilder kiiras = new StringBuilder();

            for(int i=1; i<= (db%7 == 0 ? db/7 : db/7+1) ; i++){
                if (megVan<7){
                    kiiras.append(this.neve()).append(" x").append(megVan);
                } else {
                    kiiras.append(this.neve()).append(" x").append("7");
                    megVan -= 7;
                }
                if(this instanceof Etel){
                    kiiras.append(" (+").append(((Etel) this).filteredGetPluszEnergia(csapat)).append(")");
                }
                kiiras.append(System.lineSeparator());
            }

            return kiiras.toString();
        }
    }
}
