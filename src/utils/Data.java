package src.utils;
import src.classes.Keping;
import java.util.HashMap;

public class Data {
    // Atribut (Attributes)
    private final int n;
    private final int m;
    private final int p;
    private final HashMap<Character, Keping> Kepings;

    // Metode (Methods)
    /// Getters
    public int getN() {
        return n;
    }
    public int getM() {
        return m;
    }
    public int getP() {
        return p;
    }
    public HashMap<Character, Keping> getKepings() {
        return Kepings;
    }



    /// Konstruktor (Constructor)
    public Data(int n, int m, int p, HashMap<Character, Keping> Kepings) {
        this.n = n;
        this.m = m;
        this.p = p;
        this.Kepings = Kepings;
    }

    /// Metode lain (Other methods)
    // printData
    public void printData() {
        System.out.println("n: " + n);
        System.out.println("m: " + m);
        System.out.println("p: " + p);
        System.out.println("Keping:");
        for (Character key : Kepings.keySet()) {
            System.out.println("Keping " + key + ":");
            Kepings.get(key).printKeping();
        }
    }


}
