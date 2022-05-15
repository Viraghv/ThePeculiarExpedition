package FoElemek;

import Csapat.*;
import Inventory.*;
import Tereptargyak.*;

import java.util.Scanner;

/**
 * A Felfedezot, azaz magat a jatekost megvalosito osztaly.<br>
 * Egy felfedezonek van pozicioja, latokore, energiaja, aranya, viszonya, hirneve, inventoryja, es csapata,
 * tovabba szamon van tartva a mozgasanak aktualis koltsege, az utolso 3 etel, amit fogyasztott, illetve hogy mikor fogyaszott
 * utoljara whiskeyt es kabitoszert.<br>
 * Egy felfedezo tud a palyan mozogni, a palya mezoivel interakcioba lepni, elelmiszert fogyasztani, pihenni, hazamenni,
 * valamint megkezdeni az uj kuldetest.
 */

public class Felfedezo extends Csapattars{
    private int latokor;
    private int pos_x;
    private int pos_y;
    private double energia;
    private int arany;
    private int viszony;
    private double mozgasKoltseg;
    private int hirnev;
    private Inventory inventory;
    private Csapat csapat;
    private Etel[] utolsoHaromEtel;
    private int whiskeyUtoljara;
    private int kabszerUtoljara;


    /**
     * Azokkal az alapertelmezett tulajdonsagokkal jon letre, amivel a jatekot elkezdi.
     */
    public Felfedezo() {
        super();
        latokor = 1;
        pos_x = 0;
        pos_y = 0;
        energia = 100;
        arany = 250;
        viszony = 3;
        mozgasKoltseg = 1;
        hirnev = 0;
        inventory = new Inventory();
        csapat = new Csapat();
        utolsoHaromEtel = new Etel[3];
        whiskeyUtoljara = 0;
        kabszerUtoljara = 0;
    }


    public int getViszony() {
        return viszony;
    }

    public int getArany() {
        return arany;
    }

    public int getLatokor() {
        return latokor;
    }

    /**
     * Mivel a csapat tagjai befolyasolhatjak a latokort, ezert a latokor erteket ahhoz igazitva adja vissza.
     * @return a latokor tenyleges erteke
     */
    public int filteredGetLatokor() {
        int latokor = this.latokor;
        for(int i=0; i<csapat.getCsapattarsak().length; i++){
            if(csapat.getCsapattarsak()[i] instanceof Felderito){
                latokor = ((Felderito) csapat.getCsapattarsak()[i]).latokortNovel(latokor);
            }
        }
        return latokor;
    }

    public int getPos_x() {
        return pos_x;
    }

    public int getPos_y() {
        return pos_y;
    }

    public double getEnergia() {
        return energia;
    }

    public double getMozgasKoltseg() {
        return mozgasKoltseg;
    }

    public int getHirnev() {
        return hirnev;
    }

    public Etel[] getUtolsoHaromEtel() {
        return utolsoHaromEtel;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public Csapat getCsapat() {
        return csapat;
    }

    public boolean isAlkoholista() {
        return alkoholista;
    }

    public boolean isKabszerFuggo() {
        return kabszerFuggo;
    }

    public int getWhiskeyUtoljara() {
        return whiskeyUtoljara;
    }

    public int getKabszerUtoljara() {
        return kabszerUtoljara;
    }

    public void setPos_x(int pos_x) {
        this.pos_x = pos_x;
    }

    public void setPos_y(int pos_y) {
        this.pos_y = pos_y;
    }

    public void setViszony(int viszony) {
        this.viszony = viszony;
    }

    public void setMozgasKoltseg(double mozgasKoltseg) {
        this.mozgasKoltseg = mozgasKoltseg;
    }

    public void setHirnev(int hirnev) {
        this.hirnev = hirnev;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }

    public void setCsapat(Csapat csapat) {
        this.csapat = csapat;
    }

    public void setLatokor(int latokor) {
        this.latokor = latokor;
    }

    public void setArany(int arany) {
        if(arany >= 0){
            this.arany = arany;
        } else {
            this.arany = 0;
        }

    }

    public void setEnergia(double energia) {
        if(energia > 100){
            this.energia = 100;
        } else if (energia < 0){
            this.energia = 0;
        } else {
            this.energia = energia;
        }
        this.energia = (int) (this.energia*100) / 100.0;
    }

    public void setUtolsoHaromEtel(Etel[] utolsoHaromEtel) {
        this.utolsoHaromEtel = utolsoHaromEtel;
    }

    public void setAlkoholista(boolean alkoholista) {
        this.alkoholista = alkoholista;
    }

    public void setKabszerFuggo(boolean kabszerFuggo) {
        this.kabszerFuggo = kabszerFuggo;
    }

    public void setWhiskeyUtoljara(int whiskeyUtoljara) {
        this.whiskeyUtoljara = whiskeyUtoljara;
    }

    public void setKabszerUtoljara(int kabszerUtoljara) {
        this.kabszerUtoljara = kabszerUtoljara;
    }

    /**
     * Az energiat csokkenti a mozgaskoltseg mertekevel, de legfeljebb csak 0-ig.<br>
     * 2 tizedesjegyre kerekit.
     */
    public void farad (){
        if(energia-mozgasKoltseg<0){
            energia = 0;
        } else {
            energia -= mozgasKoltseg;
        }
        energia = Math.round (energia*100.0) / 100.0;
    }

    /**
     * A viszony erteket az adott mennyiseggel csokkenti, ha meg van eleg, kulonben hibat ir ki.
     * @param mennyivel amennyivel csokkenteni akarjuk a viszonyt
     */
    public void csokkentViszony (int mennyivel){
        if(mennyivel <= getViszony()) {
            viszony -= mennyivel;
        } else {
            System.out.println("Hiba!");
        }
    }


    /**
     * Megkeresi az adott terkepen a hajo poziciojat, es a poziciojat arra allitja.
     * @param terkep a terkep, amire a felfedezot el akarjuk helyezni
     */
    public void posBeallit(Mezo[][] terkep) {
        for(int i = 0; i<16; i++){
            for(int j=0; j<16; j++){
                if(terkep[i][j] instanceof Hajo){
                    pos_x = i;
                    pos_y = j;
                }
            }
        }
    }

    /**
     * Beallitja az aktualis helyes mozgaskoltseget az inventory telitettsege, a csapattarsak szama, illetve a felfedezo
     * elhelyezkedese alapjan (dzsungelben kozlekedes).<br>
     * 2 tizedesjegyre kerekit.
     * @param palya a palya, amin a felfedezo tartozkodik
     */
    public void mozgasKoltsegBeallit(Palya palya) {
        if(inventory.getSlotDb() > inventory.filteredGetMaxSlot(csapat)){
            mozgasKoltseg = 1 * Math.pow(1.2, inventory.getSlotDb()-inventory.filteredGetMaxSlot(csapat));
        } else {
            mozgasKoltseg = 1;
        }

        for(int i=0; i<csapat.getCsapattarsak().length; i++){
            if(csapat.getCsapattarsak()[i] != null){
                mozgasKoltseg *= 1.15;
            }
        }

        if(palya.getTerkep()[pos_x][pos_y] instanceof Dzsungel){
            mozgasKoltseg *= 2;
        }

        mozgasKoltseg = Math.round(mozgasKoltseg * 100.0) / 100.0;
    }

    /**
     * Megkerdezi a jatekostol, hogy melyik iranyba szeretne lepni, es ez alapjan megvaltoztatja a poziciojat, ha ez lehetseges,
     * kulonben hibauzenetet ir ki.
     * @param palya a palya, amin a felfedezo lepked
     */
    public void lep (Palya palya){
        Scanner be = new Scanner(System.in);
        String irany = null;
        boolean sikeres = false;
        boolean hiba = false;

        do {
            int temp_pos_x = pos_x;
            int temp_pos_y = pos_y;

            System.out.print("Melyik irányba szeretnél lépni? (w/a/s/d) ");
            irany = be.nextLine();
            hiba = false;
            switch (irany) {
                case "w":
                    temp_pos_x--; break;
                case "a":
                    temp_pos_y--; break;
                case "s":
                    temp_pos_x++; break;
                case "d":
                    temp_pos_y++; break;
                default:
                    System.out.println("Hibás bemenet!");
                    hiba = true;
                    break;
            }

            if(!hiba) {
                if (temp_pos_x < 0 || temp_pos_x >= 16 || temp_pos_y < 0 || temp_pos_y >= 16) {
                    System.out.println("Nem mehetsz le a térképről!");
                } else if (!palya.getTerkep()[temp_pos_x][temp_pos_y].isJarhato()) {
                    System.out.println("A terület nem járható!");
                } else {
                    pos_x = temp_pos_x;
                    pos_y = temp_pos_y;
                    farad();
                    sikeres = true;
                }
            }
        } while (!sikeres);
        whiskeyUtoljara++;
        kabszerUtoljara++;
    }

    /**
     * Elfogyaszt egy elelmiszert, es annak megfeleloen no az energiaja, illetve kiveszi az inventorybol es megjegyzi,
     * mint utoljara fogyasztott etelt.<br>
     * Ha az elelmiszer whiskey vagy kabitoszer volt, nullazza azok utoljara fogyasztasanak idejet. Ezek fogyasztasa
     * utan lehetseges, hogy alkoholizmus vagy kabitoszerfuggoseg alakul ki
     * @param etel az elfogyasztando etel
     */
    public void eszik(Etel etel){
        if(etel.getDb() > 0){
            setEnergia(energia + etel.filteredGetPluszEnergia(csapat));
            inventory.kivesz(etel);
            megjegyziAzUtolsoHaromEtelt(etel);

            if(etel instanceof Whiskey){
                whiskeyUtoljara = 0;
            } else if(etel instanceof Kabitoszer){
                kabszerUtoljara = 0;
            }
            csapat.alkoholistaLehetValaki(this);
            csapat.kabszerfuggoLehetValaki(this);
        }
    }

    /**
     * Ha a jatekos szeretne enni, akkor az inputnak megfeleloen elfogyasztja az adott elelmiszert.<br>
     * A metodus fel van keszulve a hibas inputokra, illetve arra, ha a megadott etel nincs az inventoryban.
     */
    public void eteltKezel (){
        Scanner be = new Scanner(System.in);
        String szeretnelEnni = null;
        String mitSzeretnelEnni = null;
        if(this.getEnergia() != 100.0){
            while(!("i".equals(szeretnelEnni) || "n".equals(szeretnelEnni)) ) {
                System.out.print("Szeretnél valamit enni? (i/n) ");
                szeretnelEnni = be.nextLine();
                if(!("i".equals(szeretnelEnni) || "n".equals(szeretnelEnni))){
                    System.out.println("Hibás bemenet!");
                }
            }
            if("i".equals(szeretnelEnni)) {
                boolean hiba;
                double voltEnergia = this.getEnergia();
                do {
                    hiba = false;
                    System.out.print("Mit szeretnél enni? (pl. 'csokoládé') ");
                    mitSzeretnelEnni = be.nextLine();
                    mitSzeretnelEnni = mitSzeretnelEnni.toLowerCase();
                    switch (mitSzeretnelEnni) {
                        case "csokoládé":
                            this.eszik(this.getInventory().getCsokolade()); break;
                        case "gyümölcs":
                            this.eszik(this.getInventory().getGyumolcs()); break;
                        case "hús":
                            this.eszik(this.getInventory().getHus()); break;
                        case "kábítószer":
                            this.eszik(this.getInventory().getKabitoszer()); break;
                        case "whiskey":
                            this.eszik(this.getInventory().getWhiskey()); break;
                        default:
                            System.out.println("Hibás bemenet!"); break;
                    }
                    if (voltEnergia == this.getEnergia() && voltEnergia != 100.0) {
                        hiba = true;
                        String biztos = null;
                        while (!("i".equals(biztos) || "n".equals(biztos))) {
                            System.out.print("Nincs ilyen étel az inventoryban! Akarsz mást választani? (i/n) ");
                            biztos = be.nextLine();
                            if (!("i".equals(biztos) || "n".equals(biztos))) {
                                System.out.println("Hibás bemenet!");
                            }
                        }
                        if ("n".equals(biztos)) { break; }
                    }
                } while (hiba);
                System.out.println("Energia: " + this.getEnergia());
            }
        }
    }

    /**
     * Megjegyzi a legutobb fogyasztott etelt, a legregebben eltaroltat pedig elfelejti (3 evesig emlekszik egyre).
     * @param ujEtel az aktualisan elfogyasztott etel
     */
    public void megjegyziAzUtolsoHaromEtelt(Etel ujEtel){
        this.utolsoHaromEtel[0] = this.utolsoHaromEtel[1];
        this.utolsoHaromEtel[1] = this.utolsoHaromEtel[2];
        this.utolsoHaromEtel[2] = ujEtel;
    }

    /**
     * Megvizsgalja, hogy az utolso harom fogyasztott etel whiskey volt-e.
     * @return igaz, ha az utolso harom fogyasztott etel whiskey volt
     */
    public boolean utolsoHaromWhiskey(){
        return this.utolsoHaromEtel[0] instanceof Whiskey &&
                this.utolsoHaromEtel[1] instanceof Whiskey &&
                this.utolsoHaromEtel[2] instanceof Whiskey;
    }

    /**
     * Megvizsgalja, hogy az utolso harom fogyasztott etel kabitoszer volt-e.
     * @return igaz, ha az utolso harom fogyasztott etel kabitoszer volt
     */
    public boolean utolsoHaromKabszer(){
        return this.utolsoHaromEtel[0] instanceof Kabitoszer &&
                this.utolsoHaromEtel[1] instanceof Kabitoszer &&
                this.utolsoHaromEtel[2] instanceof Kabitoszer;
    }

    /**
     * Ha a jatekos haza szeretne, akkor hazamegy.<br>
     * Az energia visszatoltodik 100-ra, a viszony 3-ra vagy tobbre a csapattarsaktol fuggoen.<br>
     * A felfedezo es csapattarsai meggyogyulnak.
     * @return igaz, ha sikeresen hazament a felfedezo
     */
    public boolean hazamegy(){
        Scanner be = new Scanner(System.in);
        String valasz;
        boolean hibas;
        boolean result = false;

        do {
            hibas=false;

            System.out.print("Haza szeretnél menni? (i/n) ");
            valasz = be.nextLine();

            if ("i".equals(valasz)) {
                energia = 100.0;
                viszony = 3;
                this.kuldkozMeggyogyul();
                this.getCsapat().kuldkozMindenkiMeggyogyul();
                for(int i=0; i<csapat.getCsapattarsak().length; i++) {
                    if (csapat.getCsapattarsak()[i] instanceof Bolcs) {
                        this.viszony = ((Bolcs) csapat.getCsapattarsak()[i]).viszonytNovel(this.viszony);
                    }
                }
                result = true;
            } else if (!"n".equals(valasz)) {
                System.out.println("Hibás bemenet!");
                hibas = true;
            }
        } while (hibas);

        return result;
    }

    /**
     * Ha megtalalja az arany piramist, vagy hajon tartozkodik, akkor hazamehet.
     * @param palya az aktualis palya amin tartozkodik
     * @return igaz, ha hazament
     */
    public boolean ujKortKezd (Palya palya){
        boolean result = false;

        if(palya.getTerkep()[pos_x][pos_y] instanceof AranyPiramis && !palya.getTerkep()[pos_x][pos_y].isLava()){
            ((AranyPiramis) palya.getTerkep()[pos_x][pos_y]).megtalaljak();
            if(this.hazamegy()){
                result = true;
            }
        } else if (palya.getTerkep()[pos_x][pos_y] instanceof Hajo){
            if(this.hazamegy()){
                result = true;
            }
        }

        return result;
    }

    /**
     * Ha a jatekos szeretne, akkor pihen, azaz az energiajat visszaallitja 100-ra.
     */
    public void pihen (){
        Scanner be = new Scanner(System.in);
        String pihenes = null;


        while(!("i".equals(pihenes) || "n".equals(pihenes))) {
            System.out.print("Szeretnél pihenni? (i/n) ");
            pihenes = be.nextLine();
            if(!("i".equals(pihenes) || "n".equals(pihenes))){
                System.out.println("Hibás bemenet!");
            }
        }
        if("i".equals(pihenes)){
            energia = 100.0;
            System.out.println("Energia: " + energia);
        }

    }

    /**
     * Ha hajora lepett, akkor pihenhet, es meghivja a Hajo "raktaroz" metodusat.
     * @param palya az aktualis palya amin tartozkodik
     */
    public void hajoraLep(Palya palya){
        if(palya.getTerkep()[pos_x][pos_y] instanceof Hajo) {
            pihen();
            ((Hajo) palya.getTerkep()[pos_x][pos_y]).raktaroz(inventory);
        }
    }

    /**
     * Ha barlangra lep, akkor meghivja a Barlang "felfedezik" metodusat.
     * @param palya az aktualis palya amin tartozkodik
     * @return igaz, ha a barlangban jaras hatasara a felfedezo elhagyta a csapatot
     */
    public boolean barlangraLep (Palya palya){
        boolean gameOver = false;
        if(palya.getTerkep()[pos_x][pos_y] instanceof Barlang && !palya.getTerkep()[pos_x][pos_y].isLava()){
            gameOver = ((Barlang) palya.getTerkep()[pos_x][pos_y]).felfedezik(this);
        }
        return gameOver;
    }

    /**
     * Ha oltarra lep, akkor meghivja az Oltar "felfedezik" metodusat.
     * @param palya az aktualis palya amin tartozkodik
     */
    public void oltarraLep (Palya palya){
        if(palya.getTerkep()[pos_x][pos_y] instanceof Oltar && !palya.getTerkep()[pos_x][pos_y].isLava()){
            ((Oltar) palya.getTerkep()[pos_x][pos_y]).felfedezik(this, palya);
        }
    }

    /**
     * Ha dzsungelre lep, akkor meghivja a Dzsungel "levagjak" metodusat, es ha ez sikeresen megtortenik, akkor
     * levagja a dzsungelt.
     * @param palya az aktualis palya amin tartozkodik
     */
    public void dzsungelreLep (Palya palya){
        if(palya.getTerkep()[pos_x][pos_y] instanceof Dzsungel && !palya.getTerkep()[pos_x][pos_y].isLava()) {
            if(((Dzsungel) palya.getTerkep()[pos_x][pos_y]).levagjak(inventory)) {
                palya.getTerkep()[pos_x][pos_y] = new Fu();
            }
        }
    }

    /**
     * Ha falura lep, meghivja a falu Boltjanak "vasarlas" es "csapattagotAjanl" metodusat.
     * @param palya az aktualis palya amin tartozkodik
     */
    public void faluraLep(Palya palya){
        if(palya.getTerkep()[pos_x][pos_y] instanceof Falu && !palya.getTerkep()[pos_x][pos_y].isLava()){
            ((Falu) palya.getTerkep()[pos_x][pos_y]).getBolt().vasarlas(this);
            ((Falu) palya.getTerkep()[pos_x][pos_y]).getBolt().csapattagotAjanl(this);
        }
    }

    /**
     * Az aktualis kuldeteskozi Boltbol vasarolhat, illetve a Bolt csapattagot ajanl neki.
     */
    public void kuldkozVasarol (){
        Bolt bolt = new Bolt("kuldkoz");
        bolt.vasarlas(this);
        System.out.println();
        bolt.csapattagotAjanl(this);
    }

    /**
     * A jatekos valasztasa szerint minden hajoban es inventoryban levo kincset elad aranyert, vagy eladomanyoz hirnevert.
     * A kincseket eltavolitja az inventorybol (a hajo nem jegyzi oket meg).<br>
     * @param palya a palya, ahonnan elozoleg hazajott
     */
    public void kuldkozKincsrolDont (Palya palya){
        for(int x = 0; x < 16; x++){
            for (int y =0; y < 16; y++){
                if (palya.getTerkep()[x][y] instanceof Hajo){
                    int kincsdb = ((Hajo) palya.getTerkep()[x][y]).getTaroltKincsek() + inventory.getKincs().getDb();
                    if(kincsdb > 0) {
                        Scanner be = new Scanner(System.in);
                        String valasz = null;
                        boolean hiba = false;

                        System.out.println(aranyHirnevToString());
                        System.out.println();
                        System.out.println(kincsdb + " kincset találtál.");
                        System.out.println("Mit szeretnél a kincsekkel csinálni?");
                        System.out.println("Eladni ('A')                   +" + inventory.getKincs().filteredGetEladasiAr(csapat) + " arany");
                        System.out.println("Múzeumnak adományozni ('H')    +" + Kincs.getPluszHirnev() + " hírnév");
                        System.out.println();

                        for (int i = 1; i <= kincsdb; i++) {
                            do {
                                hiba = false;
                                System.out.print(i + ". kincs: ");
                                valasz = be.nextLine();
                                if ("A".equals(valasz)) {
                                    arany += inventory.getKincs().filteredGetEladasiAr(csapat);
                                } else if ("H".equals(valasz)) {
                                    hirnev += Kincs.getPluszHirnev();
                                } else {
                                    System.out.println("Hibás bemenet!");
                                    hiba = true;
                                }
                            } while (hiba);
                        }
                        ((Hajo) palya.getTerkep()[x][y]).setTaroltKincsek(0);

                        int inventoryKincsDb = inventory.getKincs().getDb();
                        for (int i = 0; i < inventoryKincsDb; i++) {
                            inventory.kivesz(inventory.getKincs());
                        }
                    } else {
                        System.out.println("Nem szereztél kincseket!");
                    }
                    System.out.println(aranyHirnevToString());
                }
            }
        }
    }

    /**
     * A hirneve 1000-el no, ha az elozo kuldetes soran megtalalta az arany piramist.
     * @param palya a palya, ahonnan elozoleg hazajott
     */
    public void kuldkozHiresLesz (Palya palya){
        System.out.println("-------------------------------------------------");
        for(int i=0; i<16; i++){
            for (int j=0; j<16; j++){
                if(palya.getTerkep()[i][j] instanceof AranyPiramis){
                    if(((AranyPiramis) palya.getTerkep()[i][j]).isMegtalalt()){
                        System.out.println(System.lineSeparator() + "Ebben a körben megtaláltad az Arany piramist! (+1000 hírnév)");
                        hirnev += 1000;
                    } else {
                        System.out.println(System.lineSeparator() + "Ebben a körben sajnos nem találtad meg az Arany piramist.");
                    }
                }
            }
        }
    }

    /**
     * Hamisra allitja a serultseget, azaz meggyogyul.
     */
    public void kuldkozMeggyogyul(){
        this.setSerult(false);
    }

    /**
     * Kiirathatoan visszaadja csak az aranyat es a hirnevet.
     * @return a felfedezo aranya es hirneve Stringben
     */
    public String aranyHirnevToString(){
        return System.lineSeparator() + "Arany: " + getArany() + System.lineSeparator() +
               "Hírnév: " + getHirnev();
    }

    @Override
    public String nev() {
        return "Felfedező";
    }

    /**
     * Kiirathatoan visszaadja az energiat, aranyat, viszonyt, alkoholizmust, kabitoszerfuggoseget, es serultseget.
     * @return a felfedezo energiaja, aranya, viszonya, alkoholizmusa, kabitoszerfuggosege, es serultsege Stringben
     */
    public String toString() {
        return "Energia: " + getEnergia() + System.lineSeparator() + "Arany: " + getArany() + System.lineSeparator() +
                "Viszony: " + getViszony()  +
                (alkoholista ? (System.lineSeparator()+"Alkoholista") : "") +
                (kabszerFuggo ? (System.lineSeparator()+"Kábítószerfüggő") : "") +
                (serult ? (System.lineSeparator()+"Sérült") : "");
    }

    /**
     * Kiirathatoan visszaadja csak a hirnevet, a rangsorban valo megjeleniteshez.
     * @return a felfedezo hirneve Stringben
     */
    public String rangsorbanToString(){
        return "TE: " + this.getHirnev() + " hírnév";
    }
}
