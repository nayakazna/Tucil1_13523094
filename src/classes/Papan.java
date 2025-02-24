package src.classes;

import java.util.HashMap;

public class Papan {
    public int n;
    public int m;
    public int terisi;
    public char[][] petak;
    private static String RESET = "\u001B[0m";
    private static String[] WARNA = {
        // jujur aku gatau ini masing-masing warnanya apa
        // Ambil dari random palette generator
        "\u001B[30m",
        "\u001B[31m",
        "\u001B[32m",
        "\u001B[33m",
        "\u001B[34m",
        "\u001B[35m",
        "\u001B[36m",
        "\u001B[38;2;255;33;111m",
        "\u001B[38;2;77;62;199m",
        "\u001B[38;2;31;4;108m",
        "\u001B[38;2;93;189;204m",
        "\u001B[38;2;114;225;38m",
        "\u001B[38;2;227;111;223m",
        "\u001B[38;2;105;41;179m",
        "\u001B[38;2;138;55;24m",
        "\u001B[38;2;253;202;196m",
        "\u001B[38;2;103;230;193m",
        "\u001B[38;2;249;239;147m",
        "\u001B[38;2;240;41;93m",
        "\u001B[38;2;229;98;41m",
        "\u001B[38;2;35;82;41m",
        "\u001B[38;2;26;12;24m",
        "\u001B[38;2;118;227;124m",
        "\u001B[38;2;238;153;170m",
        "\u001B[38;2;44;224;115m",
        "\u001B[38;2;182;126;74m",
    };
    private static HashMap<Character, String> warnaMap;

    // Getters
    public int getN() {
        return this.n;
    }
    public int getM() {
        return m;
    }
    public char[][] getPetak() {
        return petak;
    }

    // Konstruktor (Constructor)
    public Papan(int n, int m) {
        this.n = n;
        this.m = m;
        this.terisi = 0;
        this.petak = new char[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                petak[i][j] = '-';
            }
        }

        warnaMap = new HashMap<Character, String>();
        char huruf = 'A';
        for (String warna : WARNA) {
            warnaMap.put(huruf, warna);
            huruf++;
        }
        warnaMap.put('-', "\u001B[0m");
    }

    public void printPapan() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                System.out.print(warnaMap.get((char) (petak[i][j])));
                System.out.print(petak[i][j]);
                System.out.print(RESET);
                System.out.print(" ");
            }
            System.out.println();
        }
    }

    public boolean isPenuh() {
        return this.terisi == n * m;
    }

    public boolean isPlacementValid(Keping keping, int x, int y) {
        char[][] bentuk = keping.getBentuk();

        if (x < 0 || y < 0 || x + keping.getTinggi() > n || y + keping.getPanjang() > m) {
            return false;
        }

        for (int i = 0; i < keping.getTinggi(); i++) {
            for (int j = 0; j < keping.getPanjang(); j++) {
                if (bentuk[i][j] != '-' && petak[x + i][y + j] != '-') {
                    return false;
                }
            }
        }
        return true;
    }

    public void tambahKeping(Keping keping, int x, int y) {
        char[][] bentuk = keping.getBentuk();
        for (int i = 0; i < keping.getTinggi(); i++) {
            for (int j = 0; j < keping.getPanjang(); j++) {
                if (keping.getBentuk()[i][j] != '-') {
                    petak[x + i][y + j] = bentuk[i][j];
                    terisi++;
                }
            }
        }
    }

    public void hapusKeping(Keping keping, int x, int y) {
        char[][] bentuk = keping.getBentuk();
        for (int i = 0; i < keping.getTinggi(); i++) {
            for (int j = 0; j < keping.getPanjang(); j++) {
                if (bentuk[i][j] != '-') {
                    petak[x + i][y + j] = '-';
                    terisi--;
                }
            }
        }
    }
}
