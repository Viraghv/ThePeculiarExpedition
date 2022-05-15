package Inventory;

import Csapat.*;

/**
 * Az eteleket, azaz "eheto targyakat" altalanosan megvalosito abstract osztaly.
 * Az etelek kulonbozo fajtairol tudjuk, hogy mennyi plusz energiat toltenek vissza elfogyasztas hatasara.
 */
public abstract class Etel extends Item {
    protected double pluszEnergia;

    /**
     * Mivel a csapat tagjai befolyasolhatjak bizonyos etelek (Whiskey, Kabitoszer) plusz energiajat, ezert a
     * plusz energia erteket ahhoz igazitva adja vissza.<br>
     * 2 tizedesjegyre kerekit.
     * @param csapat a csapat, melynek tagjai hatassal lehetnek a plusz energiara
     * @return a plusz energia tenyleges erteke
     */
    public double filteredGetPluszEnergia(Csapat csapat) {
        double pluszEnergia = this.pluszEnergia;
        if(this instanceof Whiskey){
            for(int i=0; i<csapat.getCsapattarsak().length; i++){
                if(csapat.getCsapattarsak()[i] instanceof Katona){
                    pluszEnergia = ((Katona) csapat.getCsapattarsak()[i]).whiskeytNovel(pluszEnergia);
                }
            }
        } else if(this instanceof Kabitoszer){
            for(int i=0; i<csapat.getCsapattarsak().length; i++){
                if(csapat.getCsapattarsak()[i] instanceof Saman){
                    pluszEnergia = ((Saman) csapat.getCsapattarsak()[i]).kabszertNovel(pluszEnergia);
                }
            }
        }
        return  Math.round(pluszEnergia * 100.0) / 100.0;
    }

}
