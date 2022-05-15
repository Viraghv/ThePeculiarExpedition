package Inventory;

import Csapat.*;

/**
 * A Kincs targyat megvalosito osztaly.<br>
 * Egy kincsnek van eladasi ara, illetve hogy mennyi hirnevert lehet eladomanyozni. Utobbi erteke mindig 200.
 */
public class Kincs extends Item{

    private final int eladasiAr;
    private final static int pluszHirnev = 200;

    /**
     * Egy Kincs alapertelmezett eladasi ara 200 arany.
     */
    public Kincs() {
        this.eladasiAr = 400;
    }

    /**
     * Mivel a csapat tagjai befolyasolhatjak a kincsek eladasi arat, ezert az ar erteket ahhoz igazitva adja vissza.
     * @param csapat a csapat, melynek tagjai hatassal lehetnek az arra
     * @return az eladasi ar tenyleges erteke
     */
    public int filteredGetEladasiAr(Csapat csapat) {
        int eladasiAr = this.eladasiAr;
        for(int i=0; i<csapat.getCsapattarsak().length; i++){
            if(csapat.getCsapattarsak()[i] instanceof Kereskedo){
                eladasiAr = ((Kereskedo) csapat.getCsapattarsak()[i]).kincsAratNovel(eladasiAr);
            }
        }
        return eladasiAr;
    }

    public static int getPluszHirnev() {
        return pluszHirnev;
    }

    public String neve(){
        return "Kincs";
    }

    /**
     * Mivel a tobbi targytol elteroen kincsbol egy inventory-sloton csak egy lehet, igy a megjelenitesben is minden
     * kincs kulon sorban fog megjelenni.
     * @param csapat a csapat, melynek tagjai hatassal lehetnek az etelek plusz energiajara (itt konkretan nincs
     *               felhasznalva, de a helyes Override miatt szukseges)
     * @return a kincs neve es darabszama egyesevel, Stringben
     */
    @Override
    public String toString(Csapat csapat) {
        StringBuilder result = new StringBuilder();
        for(int i=0; i<db; i++){
            result.append("Kincs x1").append(System.lineSeparator());
        }

        return result.toString();
    }
}
