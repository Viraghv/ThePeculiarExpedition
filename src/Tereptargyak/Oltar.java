package Tereptargyak;

import FoElemek.Felfedezo;
import FoElemek.Palya;

import java.util.Random;
import java.util.Scanner;

/**
 * Az Oltar mezot megvalosito osztaly.<br>
 * Egy oltarrol tudjuk, hogy van e rajta kincs.<br>
 * Egy oltart fel lehet fedezni, es atkot szorhat a felfedezore.
 */
public class Oltar extends Mezo{
    private boolean kincsVanBenne;

    /**
     * Az oltar jarhato, es kezdetben van rajta kincs.
     */
    public Oltar() {
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
     * Ha az oltaron meg van kincs, es a felfedezonek van eleg viszonya (min. 2), akkor a jatekos donthet ugy, hogy
     * ellopja a kincset az oltarrol.<br>
     * Ebben az esetben 2-vel csokken a felfedezo viszonya, egy kincs bekerul az inventoryba, es az oltaron mar nem lesz
     * tobbe kincs. 80% esellyel atok kovetkezik be.
     * @param felfedezo az oltart felfedezo felfedezo :D
     * @param palya a palya, amin az oltar van
     */
    public void felfedezik (Felfedezo felfedezo, Palya palya){
        if(this.kincsVanBenne) {
            Scanner be = new Scanner(System.in);
            String valasz = null;

            if (felfedezo.getViszony() >= 2) {
                while (!("i".equals(valasz) || "n".equals(valasz))) {
                    System.out.print("Ellopod a kincset az oltárról (-2 viszony)? (i/n) ");
                    valasz = be.nextLine();

                    if (!("i".equals(valasz) || "n".equals(valasz))){
                        System.out.println("Hibás bemenet!");
                    }
                }

                if ("i".equals(valasz)) {
                    felfedezo.getInventory().belerak(felfedezo.getInventory().getKincs());
                    this.kincsVanBenne = false;
                    felfedezo.csokkentViszony(2);

                    Random rand = new Random();
                    int random = rand.nextInt(100);

                    if (random < 80) {
                        System.out.print("Átok: ");
                        this.atok(palya, felfedezo);
                    }
                }

            } else {
                System.out.println("Nincs elég viszonyod, hogy odamenj az oltárhoz!");
            }
        } else {
            System.out.println("Az oltáron már nincs kincs.");
        }
    }

    /**
     * 35% esellyel vulkankitores tortenik, azaz a legkozelebbi hegy vulkanna alakul
     * (Hegy.legkozelebbiHegyVulkannaAlakul metodust hasznalva). <br>
     * Kulonben gejzirkitores tortenik, azaz egy kornyezo teruletek gejzir tor ki
     * (Gejzir.kornyezoMezoGejzirreAlakul metodust hasznalva).
     * @param palya a palya, amin az atok tortenik
     * @param felfedezo a felfedezo, aki az atkot kivaltotta
     */
    public void atok (Palya palya, Felfedezo felfedezo){
        Random rand = new Random();
        int random = rand.nextInt(100);

        if(random < 35){
            System.out.println("Vulkánkitörés!");
            Hegy.legkozelebbiHegyVulkannaAlakul(palya, felfedezo);

        } else {
            System.out.println("Gejzírkitörés!");
            Gejzir.kornyezoMezoGejzirreAlakul(palya.getTerkep(), felfedezo);
        }
    }

    @Override
    public String toString() {
        return !lava ? "+" : "L";
    }

    @Override
    public void tajekoztat() {
        System.out.println(!lava ? "Az aktuális mező egy oltár. (" + (this.isVizes() ? "nedves" : "száraz") + ")" :
                                    "Az aktuális mező lávával borított.");
    }
}
