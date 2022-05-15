package FoElemek;

import Tereptargyak.Gejzir;
import Tereptargyak.Mezo;
import Tereptargyak.Vulkan;

/**
 * Az egy kuldetes soran bekovetkezo, vagy bekovetkezheto dolgokat egyesito metodust valositja meg.
 */
public class Kuldetes {

    /**
     * Mindent megvalosit, ami egy kuldetes soran tortenhet a megfelelo metodusokat meghivva egymas utan.<br>
     * A while ciklus tartalma egy lepest reprezental, mely addig ismetlodik, amig a felfedezo uj kort nem kezd, vagy
     * el nem hagyja a csapatot.
     * @param palya a palya, amin a kuldetes zajlik
     * @param felfedezo a felfedezo, aki a kuldetesen van
     * @return igaz, ha a kuldetes kozben a felfedezo elhagyja a csapatot es game over kovetkezik be
     */
    public static boolean kuldetestVezerel(Palya palya, Felfedezo felfedezo){
        boolean gameOver = false;
        while (true) {
            palya.felfedezettBeallit(felfedezo);
            System.out.println(System.lineSeparator() + felfedezo.toString() + System.lineSeparator());
            System.out.println(palya.printTerkep(felfedezo));

            if(felfedezo.getCsapat().energiaMiattCsokken(felfedezo) || felfedezo.getCsapat().fuggosegMiattCsokken(felfedezo) ||
                    felfedezo.getCsapat().serultsegMiattCsokken(felfedezo)){
                gameOver = true;
                break;
            }

            System.out.println(felfedezo.getCsapat().toString());
            System.out.println(felfedezo.getInventory().toString(felfedezo.getCsapat()));
            palya.getTerkep()[felfedezo.getPos_x()][felfedezo.getPos_y()].tajekoztat();

            felfedezo.hajoraLep(palya);
            if(felfedezo.barlangraLep(palya)){
                gameOver = true;
                break;
            }
            felfedezo.oltarraLep(palya);
            Vulkan.aktivVulkanokLavatTerjesztenek(palya);
            Mezo.leegetiAMezoketALava(palya.getTerkep());
            Mezo.kialszikALava(palya.getTerkep());
            Gejzir.aktivGejzirekVizetTerjesztenek(palya);
            felfedezo.dzsungelreLep(palya);
            felfedezo.faluraLep(palya);

            felfedezo.eteltKezel();

            if(felfedezo.ujKortKezd(palya)){
                break;
            }

            felfedezo.mozgasKoltsegBeallit(palya);
            //System.out.println(felfedezo.getMozgasKoltseg());
            felfedezo.lep(palya);
        }
        return gameOver;
    }
}
