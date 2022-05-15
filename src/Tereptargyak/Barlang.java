package Tereptargyak;

import java.util.Random;
import java.util.Scanner;

import FoElemek.Felfedezo;

/**
 * A Barlang mezot megvalosito osztaly.<br>
 * Egy barlangrol tudjuk, hogy van e benne kincs. Egy barlangot fel lehet fedezni es tud katasztrofat okozni.
 */
public class Barlang extends Mezo{

    private boolean kincsVanBenne;

    /**
     * A barlang jarhato, es kezdetben van benne kincs.
     */
    public Barlang() {
        super();
        this.jarhato = true;
        this.kincsVanBenne = true;
    }

    public boolean isKincsVanBenne() {
        return kincsVanBenne;
    }

    public void setKincsVanBenne(boolean kincsVanBenne) {
        this.kincsVanBenne = kincsVanBenne;
    }

    /**
     * A barlangot egy felfedezo fel tudja fedezni.<br>
     * Ha a barlangban meg van kincs, akkor a jatekos valaszthat, hogy szeretne e faklyat hasznalni. Ha hasznal, az
     * inventoryjaba kerul a kincs, es a barlangban mar nem lesu tobbe kincs.<br>
     * Ha nem hasznal faklyat, vagy nincs nala, akkor 65% esellyel katasztrofa kovetkezik be. A kinccsel ebben az esetben
     * is ugyanaz tortenik.<br>
     * Ha a barlangban ralepeskor mar nincs kincs, errol tajekoztatja a jatekost, es nem tortenik semmi.
     * @param felfedezo a barlangot felfedezo felfedezo :D
     * @return igaz, ha a barlang felfedezesenek hatasara a felfedezo elhagyta a csapatot, azaz game over kovetkezett be
     */
    public boolean felfedezik (Felfedezo felfedezo){
        boolean gameOver = false;
        if(this.kincsVanBenne) {
            Scanner be = new Scanner(System.in);
            String valasz = null;
            boolean hasznaltFaklyat = false;

            if (felfedezo.getInventory().getFaklya().getDb() > 0) {
                while (!("i".equals(valasz) || "n".equals(valasz))) {
                    System.out.print("Szeretnél fáklyát használni? (i/n) ");
                    valasz = be.nextLine();

                    if (!("i".equals(valasz) || "n".equals(valasz))){
                        System.out.println("Hibás bemenet!");
                    }
                }

                if ("i".equals(valasz)) {
                    felfedezo.getInventory().kivesz(felfedezo.getInventory().getFaklya());
                    hasznaltFaklyat = true;
                }

            }
            if (!hasznaltFaklyat) {
                Random rand = new Random();
                int random = rand.nextInt(100);

                if (random < 65) {
                    System.out.println("Katasztrófa!");
                    gameOver = katasztrofa(felfedezo);
                }
            }
            felfedezo.getInventory().belerak(felfedezo.getInventory().getKincs());
            if(!gameOver){
                System.out.println("Kincset találtál!");
            }
            this.kincsVanBenne = false;
        } else {
            System.out.println("A barlangban már nincs kincs.");
        }

        return gameOver;
    }

    /**
     * Lehetseges kimenetelek:<br>
     * 20% esellyel leserul random a felfedezo vagy egy csapattars.<br>
     * 10% esellyel random vagy a felfedezo vagy az egyik csapattars elhagyja a csapatot.<br>
     * 70% esellyel a felfedezo 45 energiat veszit.<br>
     * A kimenetelek egymast kizaroak, egy katasztrofa soran csak az egyik kovetkezhet be.
     * @param felfedezo a katasztrofat kivalto felfedezo
     * @return igaz, ha a katasztrofa hatasara a felfedezo elhagyta a csapatot, azaz game over kovetkezett be
     */
    public boolean katasztrofa (Felfedezo felfedezo){
        boolean gameOver = false;
        Random rand = new Random();
        int random = rand.nextInt(100);

        if(random < 20){
            random = rand.nextInt(felfedezo.getCsapat().csapatMeret() + 1);
            if(random == felfedezo.getCsapat().csapatMeret()){
                felfedezo.leserul();
            } else {
                felfedezo.getCsapat().getCsapattarsak()[random].leserul();
            }
        } else if (random < 30){
            random = rand.nextInt(felfedezo.getCsapat().csapatMeret() + 1);
            if(random == felfedezo.getCsapat().csapatMeret()){
                gameOver = true;
            } else {
                felfedezo.getCsapat().getCsapattarsak()[random].elhagy(felfedezo.getCsapat());
            }
        } else {
            felfedezo.setEnergia(felfedezo.getEnergia() - 45);
            System.out.println("-45 energia!");
        }

        return gameOver;
    }

    @Override
    public String toString() {
        return !lava ? "C" : "L";
    }

    @Override
    public void tajekoztat() {
        System.out.println(!lava ?"Az aktuális mező egy barlang. (" + (this.isVizes() ? "nedves" : "száraz") + ")" :
                                  "Az aktuális mező lávával borított.");
    }
}
