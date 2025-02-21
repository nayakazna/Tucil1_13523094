package src.classes;

public class Keping {
    // Atribut (Attributes)
    private int panjang;
    private int tinggi;
    private Titik[] bentuk;
    private int nTitik;

    // Metode (Methods)
    /// Setters
    public void setPanjang(int panjang) {
        this.panjang = panjang;
    }
    public void setTinggi(int tinggi) {
        this.tinggi = tinggi;
    }
    public void setBentuk(Titik[] bentuk) {
        this.bentuk = bentuk;
    }
    public void setnTitik(int nTitik) {
        this.nTitik = nTitik;
    }

    /// Getters
    public int getPanjang() {
        return panjang;
    }
    public int getTinggi() {
        return tinggi;
    }
    public Titik[] getBentuk() {
        return bentuk;
    }
    public int getnTitik() {
        return nTitik;
    }

    /// Konstruktor (Constructor)
    public Keping(int panjang, int tinggi, Titik[] bentuk) {
        this.setPanjang(panjang);
        this.setTinggi(tinggi);
        this.setBentuk(bentuk);
        this.setnTitik(bentuk.length);
    }

    /// Transformasi (Transformation)
    //// ID: Rotasi 90 derajat searah jarum jam
    //// EN: 90 degrees clockwise rotation
    public void putar() {
        for (Titik titik : bentuk) {
            // ID: Memutar tiap titik
            // EN: Rotate each point
            titik.setX(this.getTinggi() - 1 - titik.getY());
            titik.setY(titik.getX());

            // ID: Tukar panjang dan tingginya
            // EN: Swap the length and width
            int temp = tinggi;
            this.setTinggi(panjang);
            this.setPanjang(temp);
        }
        
    }

    /// Pencerminan (Reflection)
    //// ID: Pencerminan terhadap sumbu-x
    //// EN: Reflection with respect to the x-axis 
    public void cermin() {
        for (Titik titik : bentuk) {
            titik.setX(this.panjang - 1 - titik.getX());
        }
    }
}
