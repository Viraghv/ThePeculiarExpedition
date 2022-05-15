package Tereptargyak;

/**
 * A palya Mezoit altalanosan megvalosito abstract osztaly.<br>
 * Egy mezorol tudjuk hogy vizes es jarhato e, illetve hogy lava fedi-e, ezesetben pedig azt is, hogy hany lepes ota fedi lava.<br>
 * A mezoket a lava le tudja egetni, es egy ido utan kialszik a lava a mezokon.
 */
public abstract class Mezo {
    protected boolean vizes;
    protected boolean jarhato;
    protected boolean lava;
    protected int miotaLava;

    /**
     * Egy mezo alapertelmezetten szaraz, es nem boritja lava.
     */
    public Mezo() {
        this.vizes = false;
        this.lava = false;
        this.miotaLava = 0;
    }

    public boolean isVizes() {
        return vizes;
    }

    public void setVizes(boolean vizes) {
        this.vizes = vizes;
    }

    public boolean isJarhato() {
        return jarhato;
    }

    public void setJarhato(boolean jarhato) {
        this.jarhato = jarhato;
    }

    public boolean isLava() {
        return lava;
    }

    public void setLava(boolean lava) {
        this.lava = lava;
    }

    /**
     * A Dzsungel es Fu mezoket sima terulette (SimaFold) alakitja, ha azok lavaval boritotta valnak.<br>
     * Az uj terulet megorzi elodjenek tulajdonsagait (vizesseg, lavaval boritottsag).
     * @param terkep a mezokbol allo terkep
     */
    public static void leegetiAMezoketALava(Mezo[][] terkep){
        for(int i=0; i<16; i++){
            for(int j=0; j<16; j++){
                if(terkep[i][j].lava){
                    if(terkep[i][j] instanceof Dzsungel || terkep[i][j] instanceof Fu){
                        SimaFold ujFold = new SimaFold();
                        ujFold.setVizes(terkep[i][j].isVizes());
                        ujFold.setLava(terkep[i][j].isLava());
                        terkep[i][j] = ujFold;
                    }
                }
            }
        }
    }

    /**
     * Azokon a mezokon, melyek lavaval boritottak, eggyel noveli az idotartamot, hogy miota van lava a teruleten.<br>
     * Azokon a mezokon, amik mar tobb mint 4 lepes ota lavaval boriottak, kialszik a lava, azaz "lava" tulajdonsaguk
     * hamisra allitodik.
     * @param terkep a mezokbol allo terkep
     */
    public static void kialszikALava(Mezo[][] terkep){
        for(int i =0; i<16; i++){
            for (int j =0; j<16; j++){
                if(terkep[i][j].lava){
                    terkep[i][j].miotaLava++;
                }
                if(terkep[i][j].miotaLava > 4){
                    terkep[i][j].lava = false;
                    terkep[i][j].miotaLava = 0;
                }
            }
        }
    }

    /**
     * Kiirja az aktualis mezo tipusat, illetve hogy vizes vagy szaraz.<br>
     * Ha a mezo lavaval boritott, akkor nem tudjuk milyen mezo, csak hogy lava.
     */
    public abstract void tajekoztat();

    /**
     * Terkepen megjelenithetoen visszaadja az aktualis mezot jelzo karaktert.
     * @return a mezot jelzo karakter
     */
    @Override
    public abstract String toString();
}
