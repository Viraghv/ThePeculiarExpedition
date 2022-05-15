package Inventory;

import Csapat.*;

/**
 * Az Inventoryt megvalosito osztaly.<br>
 * Az invenoryrol tudjuk a slotok maximalis szamat, illetve hogy ebbol hany sloton van mar valami.
 * Az inventory tovabba tartalmazza minden lehetseges targy adatait, ami benne lehet.<br>
 * Az inventorybol lehet be- es kitenni targyakat.
 */
public class Inventory {
    private int maxSlot;
    private int slotDb;
    private Bozotvago bozotvago;
    private Csokolade csokolade;
    private Faklya faklya;
    private Gyumolcs gyumolcs;
    private Hus hus;
    private Kabitoszer kabitoszer;
    private Kincs kincs;
    private Kotel kotel;
    private Uveggolyo uveggolyo;
    private Whiskey whiskey;


    /**
     * Az inventory alapertelmezett maximalis slotszama 8, es kezdetben ures.
     */
    public Inventory() {
        this.maxSlot = 8;
        this.slotDb = 0;
        this.bozotvago = new Bozotvago();
        this.csokolade = new Csokolade();
        this.faklya = new Faklya();
        this.gyumolcs = new Gyumolcs();
        this.hus = new Hus();
        this.kabitoszer = new Kabitoszer();
        this.kincs = new Kincs();
        this.kotel = new Kotel();
        this.uveggolyo = new Uveggolyo();
        this.whiskey = new Whiskey();
    }

    /**
     * Mivel a csapat tagjai befolyasolhatjak a maximalis slotszamot, ezert ennek erteket ahhoz igazitva adja vissza.
     * @param csapat a csapat, amelynek tagjai hatassal lehetnek a max slotok szamara
     * @return a maximalis slotszam tenyleges erteke
     */
    public int filteredGetMaxSlot(Csapat csapat) {
        int maxSlot = this.maxSlot;
        for(int i=0; i<csapat.getCsapattarsak().length; i++){
            if(csapat.getCsapattarsak()[i] instanceof Szamar){
                maxSlot = ((Szamar) csapat.getCsapattarsak()[i]).inventorytBovit(maxSlot);
            }
        }
        return maxSlot;
    }

    public int getSlotDb() {
        return slotDb;
    }

    public Bozotvago getBozotvago() {
        return bozotvago;
    }

    public Csokolade getCsokolade() {
        return csokolade;
    }

    public Faklya getFaklya() {
        return faklya;
    }

    public Gyumolcs getGyumolcs() {
        return gyumolcs;
    }

    public Hus getHus() {
        return hus;
    }

    public Kabitoszer getKabitoszer() {
        return kabitoszer;
    }

    public Kincs getKincs() {
        return kincs;
    }

    public Kotel getKotel() {
        return kotel;
    }

    public Uveggolyo getUveggolyo() {
        return uveggolyo;
    }

    public Whiskey getWhiskey() {
        return whiskey;
    }

    public void setMaxSlot(int maxSlot) {
        this.maxSlot = maxSlot;
    }

    public void setSlotDb(int slotDb) {
        this.slotDb = slotDb;
    }

    public void setBozotvago(Bozotvago bozotvago) {
        this.bozotvago = bozotvago;
    }

    public void setCsokolade(Csokolade csokolade) {
        this.csokolade = csokolade;
    }

    public void setFaklya(Faklya faklya) {
        this.faklya = faklya;
    }

    public void setGyumolcs(Gyumolcs gyumolcs) {
        this.gyumolcs = gyumolcs;
    }

    public void setHus(Hus hus) {
        this.hus = hus;
    }

    public void setKabitoszer(Kabitoszer kabitoszer) {
        this.kabitoszer = kabitoszer;
    }

    public void setKincs(Kincs kincs) {
        this.kincs = kincs;
    }

    public void setKotel(Kotel kotel) {
        this.kotel = kotel;
    }

    public void setUveggolyo(Uveggolyo uveggolyo) {
        this.uveggolyo = uveggolyo;
    }

    public void setWhiskey(Whiskey whiskey) {
        this.whiskey = whiskey;
    }

    /**
     * Az adott targybol egy belekerul az inventoryba.<br>
     * Ezutan a foglalt slotok szamat az inventory uj tartalmahoz igazitja.
     * @param item a targy, amibol egyet az inventoryba szeretnenk rakni. A megadott targy az inventory egyik adattagja
     *             kell hogy legyen
     */
    public void belerak(Item item){
        item.novelDb();

        if(item instanceof Kincs){
            slotDb++;
        } else if (item.getDb() % 7 == 1){
            slotDb++;
        }

    }

    /**
     * Az adott targybol egyet kivesz az inventorybol, ha van benne.<br>
     * Ezutan a foglalt slotok szamat az inventory uj tartalmahoz igazitja.
     * @param item a targy, amibol egyet ki szeretnenk venni az inventorybol.
     */
    public void kivesz(Item item){
        if(item.db > 0){
            item.csokkentDb();
            if(item instanceof Kincs){
                slotDb--;
            } else if(item.getDb() % 7 == 0 && slotDb > 0){
                slotDb--;
            }
        }
    }

    /**
     * Kilistazza az inventory tartalmat.
     * @param csapat a csapat, amelynek tagjai hatassal lehetnek a max slotok szamara
     * @return az inventory tartalmanak listaja Stringben
     */
    public String toString(Csapat csapat) {
        StringBuilder result = new StringBuilder();
        result.append("---------------").append(System.lineSeparator());
        result.append("   Inventory   ").append(System.lineSeparator());
        result.append("---------------").append(System.lineSeparator());
        result.append(bozotvago.toString(csapat));
        result.append(csokolade.toString(csapat));
        result.append(faklya.toString(csapat));
        result.append(gyumolcs.toString(csapat));
        result.append(hus.toString(csapat));
        result.append(kabitoszer.toString(csapat));
        result.append(kincs.toString(csapat));
        result.append(kotel.toString(csapat));
        result.append(uveggolyo.toString(csapat));
        result.append(whiskey.toString(csapat));

        for(int i=slotDb; i<this.filteredGetMaxSlot(csapat); i++){
            result.append("[üres]").append(System.lineSeparator());
        }

        if(slotDb > this.filteredGetMaxSlot(csapat)){
            result.append(slotDb - this.filteredGetMaxSlot(csapat)).append(" slottal átlépted az inventory kapacitását!")
                  .append(System.lineSeparator());
        }

        return result.toString();
    }
}
