package FoElemek;

import Inventory.Kincs;

import java.util.Random;

/**
 * A rivalis felfedezoket, es azok rangsorat megvalosito osztaly.<br>
 * A rivalisoknak van hirnevuk es nevuk. A hirnev a jatek soran veletlenszeruen no.<br>
 * Kiirathato a rivalisok rangsora.
 */
public class Rivalis {
    private String nev;
    private int hirnev;

    /**
     * Az uj rivalis nevet meg lehet adni. Mindig 0 hirnevvel kezd.
     * @param nev az uj rivalis neve
     */
    public Rivalis(String nev) {
        this.nev = nev;
        this.hirnev = 0;
    }

    public String getNev() {
        return nev;
    }

    public void setNev(String nev) {
        this.nev = nev;
    }

    public int getHirnev() {
        return hirnev;
    }

    public void setHirnev(int hirnev) {
        this.hirnev = hirnev;
    }

    /**
     * A rivalisok hirnevet veletlenszeruen megnoveli.<br>
     * Egy rivalis 70% esellyel talalja meg a kuldetes soran az arany piramist.<br>
     * Ezen tul, a megtalalt kincsek szama az aktualis palyan megtalalhato kincsek szamahoz van igazitva.
     * @param rivalisok a rivalisokbol allo tomb
     * @param palya az aktualis palya, amit a rivalisok felfedeztek
     */
    public static void hirnevNo (Rivalis[] rivalisok, Palya palya){
        for(int i=0; i<rivalisok.length; i++) {
            Random rand = new Random();
            int random1 = rand.nextInt(100);
            int random2 = rand.nextInt(Math.max((palya.kincsesMezokSzama()-4),2));

            if (random1 < 70) {
                rivalisok[i].hirnev += 1000;
            }

            for(int j=0; j<= random2; j++){
                rivalisok[i].hirnev += Kincs.getPluszHirnev();
            }
        }
    }

    /**
     * Kiirathato formaban visszaadja a rivalis nevet es hirnevet.
     * @return a rivalis neve es hirneve Stringben
     */
    @Override
    public String toString() {
        return nev + ": " + hirnev + " hírnév";
    }

    /**
     * A rivalisokat hirnevuk szerint sorba rendezi a tombben, csokkeno sorrendben.
     * @param rivalisok a rivalisokbol allo tomb
     */
    public static void rivalisRendezes (Rivalis[] rivalisok){
        for(int i = 0; i<rivalisok.length; i++){
            for(int j = 0; j<rivalisok.length-1; j++) {
                if(rivalisok[j].hirnev < rivalisok[j+1].hirnev){
                    Rivalis temp;
                    temp = rivalisok[j];
                    rivalisok[j] = rivalisok[j+1];
                    rivalisok[j+1] = temp;
                }
            }
        }
    }

    /**
     * Kiirathatoan visszaadja a ranglistat.<br>
     * Eloszor megallipitja a felfedezo helyet a ranglistan, majd annak megfeleloen szurja be a rivalisok koze.
     * @param rivalisok a rivalisok tombben
     * @param felfedezo a felfedezo, akit a jatekos iranyit
     */
    public static void rangsorKiiratas (Rivalis[] rivalisok, Felfedezo felfedezo){
        int felfedezoHelyezett;
        Rivalis.rivalisRendezes(rivalisok);

        if(felfedezo.getHirnev() < rivalisok[2].hirnev){
            felfedezoHelyezett = 4;
        } else if (felfedezo.getHirnev() < rivalisok[1].hirnev){
            felfedezoHelyezett = 3;
        } else if (felfedezo.getHirnev() < rivalisok[0].hirnev){
            felfedezoHelyezett = 2;
        } else {
            felfedezoHelyezett = 1;
        }

        System.out.println("------------");
        System.out.println(" Ranglista");
        System.out.println("------------" + System.lineSeparator());

        int sorszam = 1;
        for(int i=0; i<rivalisok.length; i++){
            if(sorszam == felfedezoHelyezett){
                System.out.println((sorszam) + ". " + felfedezo.rangsorbanToString());
                sorszam++;
            }
            System.out.println((sorszam) + ". " +rivalisok[i].toString());
            sorszam++;
        }
        if(felfedezoHelyezett == 4){
            System.out.println((sorszam) + ". " + felfedezo.rangsorbanToString());
        }
    }
}
