package FoElemek;

import Tereptargyak.*;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Egy jatekbeli palyat megvalosito osztaly.<br>
 * Egy palyanak van terkepe, mely a palyat felepito mezokbol all, illetve szamon vannak tartva a mar felfedezett teruletek.<br>
 * Az osztaly kezeli a terkep beolvasasat es letrehozasat es a palyan levo mezoket.
 */
public class Palya {
    private Mezo[][] terkep = new Mezo[16][16];
    private String [][] felfedezett = new String[16][16];

    public Palya() {
        for(int i=0; i<16; i++) {
            Arrays.fill(felfedezett[i], "X");
        }
        for(int i=0; i<16; i++){
            for( int j=0; j<16; j++){
                terkep[i][j] = new SimaFold();
            }
        }
    }

    public Mezo[][] getTerkep() {
        return terkep;
    }

    public String[][] getFelfedezett() {
        return felfedezett;
    }

    public void setTerkep(Mezo[][] terkep) {
        this.terkep = terkep;
    }

    public void setFelfedezett(String[][] felfedezett) {
        this.felfedezett = felfedezett;
    }

    /**
     * A megadott fajlbol beolvassa a terkepet, es annak megfeleloen letrehozza a terkep mezoit.<br>
     * Ha a fajl nem letezik, vagy nem megfelelo a tartalma, akkor ezt hibauzenettel jelzi.
     * @param fajl a fajl, amibol beolvasunk
     * @return igaz, ha a terkep beolvasasa sikeresen megtortent
     */
    public boolean ujTerkepBeolvas(String fajl){
        String bemenet;
        boolean siker = true;
        try (Scanner scanner = new Scanner(new File(fajl))) {
            for (int i = 0; i < 16; i++) {
                for (int j = 0; j < 16; j++) {
                    if(scanner.hasNext()){
                        bemenet = scanner.next();
                    } else {
                        siker = false;
                        System.out.println("Hibás fájl!");
                        break;
                    }

                    switch (bemenet) {
                        case "1": terkep[i][j] = new SimaFold(); break;
                        case "2": terkep[i][j] = new Tenger(); break;
                        case "3": terkep[i][j] = new Hajo(); break;
                        case "4": terkep[i][j] = new To(); break;
                        case "5": terkep[i][j] = new Hegy(); break;
                        case "6": terkep[i][j] = new Barlang(); break;
                        case "7": terkep[i][j] = new Fu(); break;
                        case "8": terkep[i][j] = new Falu(); break;
                        case "9": terkep[i][j] = new Oltar(); break;
                        case "10": terkep[i][j] = new Dzsungel(); break;
                        case "11": terkep[i][j] = new AranyPiramis(); break;
                        default: System.out.println("Hibás fájl!"); siker = false;
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Hiba történt: " + e.getMessage());
            siker = false;
        }
        return siker;
    }

    /**
     * Meghivja az "ujTerkepBeolvas" metodust a kivant terkepet tartalmazo fajlra.<br>
     * A terkep lehet a jatekos altal megadott fajlbol beolvasva. Ha a jatekos nem kivan sajat terkepet megadni,
     * akkor a kuldetes alapertelmezett terkepet fogja beolvasni.<br>
     * A palyanak kezdetben a teljes terulete felfedezetlen.
     * @param kuldetes az aktualis kuldetes sorszama
     */
    public void ujTerkepLetrehoz(int kuldetes){
        Scanner be = new Scanner(System.in);
        String valasz;
        String utvonal;
        boolean siker = true;

        do {
            System.out.print("Szeretnél saját térképet beolvasni? (i/n) ");
            valasz = be.nextLine();

            if ("i".equals(valasz)) {
                System.out.print("Add meg a fájl elérési útvonalát: ");
                utvonal = be.nextLine();
                siker = ujTerkepBeolvas(utvonal);
            } else if (!"n".equals(valasz)){
                System.out.println("Hibás bemenet!");
                siker = false;
            }
        } while (!"n".equals(valasz) && !siker);

        if("n".equals(valasz)){
            String terkep;
            switch (kuldetes){
                case 1: terkep = "terkepek/terkep1.txt"; break;
                case 2: terkep = "terkepek/terkep2.txt"; break;
                case 3: terkep = "terkepek/terkep3.txt"; break;
                case 4: terkep = "terkepek/terkep4.txt"; break;
                default: terkep = "terkepek/terkep5.txt"; break;
            }

            ujTerkepBeolvas(terkep);
        }

        for(int i=0; i<16; i++) {
            Arrays.fill(felfedezett[i], "X");
        }
    }

    /**
     * A terkep osszes To mezojenek meghivja az "aztat" metodusat.
     */
    public void tavatMeghiv(){
        for(int i=0; i<16; i++) {
            for (int j = 0; j < 16; j++) {
                if (this.getTerkep()[i][j] instanceof To) {
                    ((To) this.getTerkep()[i][j]).aztat(this.getTerkep(), i, j);
                }
            }
        }
    }

    /**
     * A felfedezo poziciojat, es latokorenek megfelelo kornyezetet felfedezettre allitja.
     * @param felfedezo a palyan tartozkodo felfedezo
     */
    public void felfedezettBeallit(Felfedezo felfedezo) {
        boolean found = false;
        for(int i=0; i<16; i++) {
            for(int j=0; j<16; j++){
                if(i == felfedezo.getPos_x() && j == felfedezo.getPos_y()){
                    found = true;
                    felfedezett[i][j] = "O";
                    for(int a = -felfedezo.filteredGetLatokor(); a<=felfedezo.filteredGetLatokor(); a++){
                        for(int b = -felfedezo.filteredGetLatokor(); b<=felfedezo.filteredGetLatokor(); b++){
                            if(i+a>=0 && j+b>=0 && i+a < 16 && j+b < 16) {
                                felfedezett[i + a][j + b] = "O";
                            }
                        }
                    }

                }
            }
            if (found){
                break;
            }
        }
    }

    /**
     * Megadja, hogy a terkepen hany olyan mezo szerepel, amelyen lehetseges a kincsszerzes, azaz hany mezo tartalmaz kincset.
     * @return a palyan levo kincsek szama osszesen
     */
    public int kincsesMezokSzama(){
        int db=0;

        for(int i=0; i<16; i++){
            for(int j=0; j<16; j++){
                if(this.terkep[i][j] instanceof Barlang || this.terkep[i][j] instanceof Oltar){
                    db++;
                }
            }
        }

        return db;
    }

    /**
     * Megjelenithetoen visszadja a terkepet es azon a felfedezo poziciojat.<br>
     * Csak a felfedezett teruletek lathatok, a tobbi "X".
     * @param felfedezo a palyan tartozkodo felfedezo
     * @return a terkep Stringben
     */
    public String printTerkep(Felfedezo felfedezo) {
        StringBuilder result = new StringBuilder();

        for(int i=0; i<16; i++){
            for(int j=0; j<16; j++){
                if( i == felfedezo.getPos_x() && j == felfedezo.getPos_y()){
                    result.append("P ");
                } else if(felfedezett[i][j].equals("X")){
                    result.append("■ ");
                } else {
                    result.append(terkep[i][j].toString()).append(" ");
                }
            }
            result.append(System.lineSeparator());
        }

        return result.toString();
    }
}
