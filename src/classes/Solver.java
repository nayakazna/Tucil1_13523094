package src.classes;

import java.util.HashMap;

public class Solver {
    private Papan papan;
    private final HashMap<Character, Keping> kepings;
    private int nIterasi;
    private long waktu;
    
    public Solver(Papan papan, HashMap<Character, Keping> kepings) {
        this.papan = papan;
        this.kepings = kepings;
        this.nIterasi = 0;
        this.waktu = 0;
    }

    public void solve() {
        long start = System.currentTimeMillis();
        solve(0, 0, nIterasi);
        long end = System.currentTimeMillis();
        this.waktu = end - start;

        if (papan.isPenuh()) {
            System.out.println("Papan berhasil terisi!");
            System.out.println("Iterasi: " + nIterasi);
            System.out.println("Waktu: " + waktu + " ms");
        } else {
            System.out.println("Papan tidak dapat terisi.");
        }
    }

    private int solve(int x, int y, int nIterasi) {
        nIterasi++;
        if (papan.isPenuh()) {
            papan.printPapan();
        } else {
            for (Character key : kepings.keySet()) {
                Keping keping = kepings.get(key);
                for (int i = 0; i < 4; i++) {
                    for (int j = 0; j < 2; j++) {
                        if (papan.isPlacementValid(keping, x, y)) {
                            papan.tambahKeping(keping, x, y);
                            int nextX = x;
                            int nextY = y;
                            while (papan.petak[nextX][nextY] != '-') {
                                nextY++;
                                if (nextY == papan.m) {
                                    nextY = 0;
                                    nextX++;
                                }
                                if (nextX == papan.n) {
                                    nextX = 0;
                                }
                            }
                            solve(nextX, nextY, nIterasi);
                            papan.hapusKeping(keping, x, y);
                        }
                        keping.cermin();
                    }
                    keping.putar();
                }
            }
        }
        return nIterasi;
    }
}
