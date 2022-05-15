package Tereptargyak;

import FoElemek.Palya;

/**
 * A Vulkan mezot megvalosito osztaly.<br>
 * Egy vulkanrol tudjuk, hogy aktiv e, es hogy eppen mekkora hatotavban boritja lavaval a kornyezo teruleteket.<br>
 * Egy vulkan tud lavat terjeszteni maga korul.
 */
public class Vulkan extends Mezo{
    private int aktualisLavaHatotav;
    private boolean aktiv;

    /**
     * A vulkan nem jarhato es letrjoveskor aktiv.
     */
    public Vulkan() {
        super();
        this.jarhato = false;
        this.aktiv = true;
        this.aktualisLavaHatotav = 0;
    }

    /**
     * Az aktiv vulkan az aktualis hatotavjanak megfelelo korben lavava boritotta teszi a kornyezo nem To, Tenger, vagy Hegy mezoket.<br>
     * Ezutan megnoveli a vulkan hatotavjat. Ha ezutan a hatotav nagyobb mint 3, akkor a vulkan nem lesz tovabb aktiv,
     * azaz hatokore nem terjed tovabb.
     * @param palya a palya, amin a vulkan van
     * @param i a vulkan "i" pozicioja a palyan (x)
     * @param j a vulkan "j" pozicioja a palyan (y)
     */
    public void lavatTerjeszt(Palya palya, int i, int j){
        if(aktiv) {
            for (int a = -aktualisLavaHatotav; a <= aktualisLavaHatotav; a++) {
                for (int b = -aktualisLavaHatotav; b <= aktualisLavaHatotav; b++) {
                    if (i + a >= 0 && j + b >= 0 && i + a < 16 && j + b < 16) {
                        if(!(palya.getTerkep()[i + a][j + b] instanceof To) &&
                           !(palya.getTerkep()[i + a][j + b] instanceof Tenger) &&
                           !(palya.getTerkep()[i + a][j + b] instanceof Hegy))
                        palya.getTerkep()[i + a][j + b].setLava(true);
                    }
                }
            }
            aktualisLavaHatotav++;
            if (aktualisLavaHatotav > 3) {
                aktiv = false;
            }
        }
    }

    /**
     * A palyan levo osszes aktiv vulkan lavaval boritja a korulotte levo teruleteket.
     * @param palya a palya, amin a vulkannokat keressuk
     */
    public static void aktivVulkanokLavatTerjesztenek (Palya palya){
        for(int i = 0; i<16; i++){
            for(int j=0; j<16; j++){
                if(palya.getTerkep()[i][j] instanceof Vulkan){
                    ((Vulkan) palya.getTerkep()[i][j]).lavatTerjeszt(palya, i, j);
                }
            }
        }
    }

    @Override
    public void tajekoztat() {}

    @Override
    public String toString() { return "V"; }
}
