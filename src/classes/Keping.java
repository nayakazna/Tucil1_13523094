package src.classes;

public class Keping {
    // Atribut
    private int panjang;
    private int tinggi;
    private int[][] bentuk;

    // Metode
    /// Setters
    public void setPanjang(int panjang) {
        this.panjang = panjang;
    }
    public void setTinggi(int tinggi) {
        this.tinggi = tinggi;
    }
    public void setBentuk(int[][] bentuk) {
        this.bentuk = bentuk;
    }

    /// Getters
    public int getPanjang() {
        return panjang;
    }
    public int getTinggi() {
        return tinggi;
    }
    public int[][] getBentuk() {
        return bentuk;
    }

    /// Konstruktor
    public Keping(int panjang, int tinggi, int[][] bentuk) {
        this.setPanjang(panjang);
        this.setTinggi(tinggi);
        this.setBentuk(bentuk);
    }

    /// Transformasi
    //// Rotasi 90 derajat searah jarum jam
    public void putar() {
        int[][] bentuk_diputar = new int[this.getPanjang()][this.getTinggi()];

        for (int i = 0; i < panjang; i++) {
            for (int j = 0; j < tinggi; j++) {
                bentuk_diputar[i][j] = bentuk[tinggi-1-j][i];
            }
        }

        int temp = tinggi;
        this.setTinggi(panjang);
        this.setPanjang(temp);
        this.setBentuk(bentuk_diputar);
    }

    //// Pencerminan
    public void cermin() {
        int[][] bentuk_dicermin = new int[this.getTinggi()][this.getPanjang()];

        for (int i = 0; i < tinggi; i++) {
            for (int j = 0; j < panjang; j++) {
                bentuk_dicermin[i][j] = bentuk[i][panjang-1-j];
            }
        }

        this.setBentuk(bentuk_dicermin);
    }
}
