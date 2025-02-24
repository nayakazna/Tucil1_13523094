package src.classes;

import java.util.*;

public class Solver {
    private Papan papan;
    private final HashMap<Character, Keping> kepings;
    private final List<Character> kepingOrder;
    private final HashMap<Character, List<Keping>> transformationsMap;
    private int nIterasi;
    private long waktu;
    
    public Solver(Papan papan, Data data) {
        this.papan = papan;
        this.kepings = data.getKepings();
        this.kepingOrder = data.getKepingOrder();
        this.transformationsMap = precomputeTransformations();
        this.nIterasi = 0;
        this.waktu = 0;
    }

    private HashMap<Character, List<Keping>> precomputeTransformations() {
        HashMap<Character, List<Keping>> map = new HashMap<>();
        for (Character key : kepings.keySet()) {
            Keping original = kepings.get(key);
            Set<String> done = new HashSet<>();
            List<Keping> precompute = new ArrayList<>();

            for (int rot = 0; rot < 4; rot++) {
                Keping kepingDiputar = original.copyKeping();
                for (int r = 0; r < rot; r++) kepingDiputar.putar();
                
                for (int flip = 0; flip < 2; flip++) {
                    Keping kepingDicermin = kepingDiputar.copyKeping();
                    if (flip == 1) kepingDicermin.cermin();

                    String keyRep = Arrays.deepToString(kepingDicermin.getBentuk());
                    if (!done.contains(keyRep)) {
                        done.add(keyRep);
                        precompute.add(kepingDicermin);
                    }
                }
            }
            map.put(key, precompute);
        }
        return map;
    }

    public boolean solve(int idx) {
        if (idx >= kepings.size()) {
            return papan.isPenuh();
        }
        Character currKey = kepingOrder.get(idx);
        for (Keping keping : transformationsMap.get(currKey)) {
            for (int row = 0; row < papan.getN(); row++) {
                for (int col = 0; col < papan.getM(); col++) {
                    nIterasi++;
                    if (papan.isPlacementValid(keping, row, col)) {
                        papan.tambahKeping(keping, row, col);
                        if (solve(idx + 1)) return true;
                        papan.hapusKeping(keping, row, col);
                    }
                }
            }
        }
        return false;
    }

    public String solve() {
        long start = System.currentTimeMillis();
        boolean solved = solve(0);
        long end = System.currentTimeMillis();
        this.waktu = end - start;
        String hasil = "";

        if (solved && papan.isPenuh()) {
            papan.printPapan();
            System.out.println("Berhasil menyelesaikan puzzle!");
            hasil += "Berhasil menyelesaikan puzzle!\n";
            
            System.out.println("Hasil:");
            hasil += "Hasil:\n";
            
            for (int i = 0; i < papan.getN(); i++) {
                for (int j = 0; j < papan.getM(); j++) {
                    hasil += papan.getPetak()[i][j];
                    hasil += " ";
                }
                hasil += "\n";
            }

        } else {
            System.out.println("Gagal menyelesaikan puzzle :(");
        }

        System.out.println("Jumlah iterasi: " + this.nIterasi);
        hasil += "Jumlah iterasi: " + this.nIterasi + "\n";

        System.out.println("Waktu yang dibutuhkan: " + this.waktu + " ms");
        hasil += "Waktu yang dibutuhkan: " + this.waktu + " ms\n";
        return hasil;
    }
}
