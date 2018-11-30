
package ohtu.intjoukkosovellus;

public class IntJoukko {

    public final static int KAPASITEETTI = 5,
                            OLETUSKASVATUS = 5;
    private int kasvatuskoko;
    private int[] joukko;
    private int alkioidenLkm; 

    public IntJoukko() {
        alustaLuokka(KAPASITEETTI, OLETUSKASVATUS);
    }

    public IntJoukko(int kapasiteetti) {
        alustaLuokka(kapasiteetti, OLETUSKASVATUS);

    }
    
    public IntJoukko(int kapasiteetti, int kasvatuskoko) {
        alustaLuokka(kapasiteetti, kasvatuskoko);

    }
    
    private void alustaLuokka(int kapasiteetti, int kasvatuskoko) {
        if (kapasiteetti < 0 || kasvatuskoko < 0) {
            throw new IllegalArgumentException("Annettu kasvatuskoko tai kapasiteetti on liian pieni.");
        }
        joukko = new int[kapasiteetti];
        for (int i = 0; i < joukko.length; i++) {
            joukko[i] = 0;
        }  
        alkioidenLkm = 0;
        this.kasvatuskoko = kasvatuskoko;
    }

    public boolean lisaa(int luku) {       
        if (!kuuluu(luku)) {
            joukko[alkioidenLkm] = luku;
            alkioidenLkm++;
            if (alkioidenLkm == joukko.length) {
                lisaaTilaaJoukkoon();
            }
            return true;
        }
        return false;
    }
    
    private void lisaaTilaaJoukkoon() {
        int[] vanhaJoukko = new int[joukko.length];
        kopioiJoukko(joukko, vanhaJoukko);
        joukko = new int[alkioidenLkm + kasvatuskoko];
        kopioiJoukko(vanhaJoukko, joukko);
    }
    
    private void kopioiJoukko(int[] vanha, int[] uusi) {
        for (int i = 0; i < vanha.length; i++) {
            uusi[i] = vanha[i];
        }
    }

    public boolean kuuluu(int luku) {
        for (int i = 0; i < alkioidenLkm; i++) {
            if (luku == joukko[i]) {
                return true;
            }
        }
        return false;
    }

    public boolean poista(int luku) {
        int indeksi = -1;
        for (int i = 0; i < alkioidenLkm; i++) {
            if (luku == joukko[i]) {
                indeksi = i;
                poistaAlkio(indeksi);
                return true;
            }
        }
        return false;
    }
    
    private void poistaAlkio(int indeksi) {
        for (int i = indeksi; i < alkioidenLkm; i++) {
            int siirrettava = joukko[i];
            joukko[i] = joukko[i + 1];
            joukko[i + 1] = siirrettava;
        }
        alkioidenLkm--;
    }

    public int mahtavuus() {
        return alkioidenLkm;
    }

    @Override
    public String toString() {
        switch (alkioidenLkm) {
            case 0:
                return "{}";
            default:
                String tuotos = "{";
                for (int i = 0; i < alkioidenLkm - 1; i++) {
                    tuotos += joukko[i] + ", ";
                }
                tuotos += joukko[alkioidenLkm - 1] + "}";
                return tuotos;
        }
    }

    public int[] toIntArray() {
        int[] taulu = new int[alkioidenLkm];
        for (int i = 0; i < taulu.length; i++) {
            taulu[i] = joukko[i];
        }
        return taulu;
    }
   

    public static IntJoukko yhdiste(IntJoukko a, IntJoukko b) {
        IntJoukko yhdiste = new IntJoukko();
        int[] joukkoA = a.toIntArray();
        int[] joukkoB = b.toIntArray();
        for (int i = 0; i < joukkoA.length; i++) {
            yhdiste.lisaa(joukkoA[i]);
        }
        for (int i = 0; i < joukkoB.length; i++) {
            yhdiste.lisaa(joukkoB[i]);
        }
        return yhdiste;
    }

    public static IntJoukko leikkaus(IntJoukko a, IntJoukko b) {
        IntJoukko leikkaus = new IntJoukko();
        int[] joukkoA = a.toIntArray();
        for (int i = 0; i < joukkoA.length; i++) {
            if (b.kuuluu(joukkoA[i])) {
                leikkaus.lisaa(joukkoA[i]);
            }
        }
        return leikkaus;

    }
    
    public static IntJoukko erotus (IntJoukko a, IntJoukko b) {
        IntJoukko erotus = new IntJoukko();
        erotus.kopioiJoukko(a.toIntArray(), erotus.joukko);
        int[] joukkoB = b.toIntArray();
        for (int i = 0; i < joukkoB.length; i++) {
            erotus.poista(i);
        }
        return erotus;
    }
        
}