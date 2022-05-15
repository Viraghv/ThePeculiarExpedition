package Csapat;

/**
 * A Saman csapattarsat megvalosito osztaly.<br>
 * A Saman elonyet kezeli.
 */
public class Saman extends Csapattars {

    /**
     * 20%-al noveli a Kabitaszer plusz energiajat.
     * @param pluszEnergia a Kabitoszer plusz energiajanak eredeti erteke
     * @return a megnovelt plusz energia
     */
    public double kabszertNovel(double pluszEnergia){
        return pluszEnergia * 1.2;
    }

    public String nev (){
        return "Sámán";
    }

}
