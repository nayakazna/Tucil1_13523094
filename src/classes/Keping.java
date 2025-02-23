package src.classes;

public class Keping {
    // Atribut (Attributes)
    private int panjang;
    private int tinggi;
    private int[][] bentuk;
    private int nTitik;

    // Metode (Methods)
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
    public int[][] getBentuk() {
        return bentuk;
    }
    public int getnTitik() {
        return nTitik;
    }

    /// Konstruktor (Constructor)
    public Keping(int[][] bentuk) {
        this.setBentuk(bentuk);
        this.setTinggi(this.getBentuk().length);
        this.setPanjang(this.getBentuk()[0].length);
        this.setnTitik(this.countGrid());
    }
    
    /// Transformasi (Transformation)
    //// ID: Rotasi 90 derajat searah jarum jam
    //// EN: 90 degrees clockwise rotation
    public void putar() {
        int[][] diputar = new int[this.getPanjang()][this.getTinggi()];
        
        for (int i = 0; i < panjang; i++) {
            for (int j = 0; j < tinggi; j++) {
                diputar[i][j] = bentuk[tinggi-1-j][i];
            }
        }
        
        int temp = tinggi;
        this.setTinggi(panjang);
        this.setPanjang(temp);
        this.setBentuk(diputar);
    }

    /// Pencerminan (Reflection)
    //// ID: Pencerminan terhadap sumbu-x
    //// EN: Reflection with respect to the x-axis 
    public void cermin() {
        int[][] tecermin = new int[this.getTinggi()][this.getPanjang()];
        
        for (int i = 0; i < this.getTinggi(); i++) {
            for (int j = 0; j < this.getPanjang(); j++) {
                tecermin[i][j] = this.bentuk[i][this.getPanjang() - 1 - j];
            }
        }
        
        this.setBentuk(tecermin);
    }

    /// Fungsi Pembantu (Helper Function(s))
    public int countGrid() {
        int count = 0;

        for (int i = 0; i < this.getTinggi(); i++) {
            for (int j = 0; j < this.getPanjang(); j++) {
                if (this.getBentuk()[i][j] == 1) {
                    count++;
                }
            }
        }
        return count;
    }

    // printKeping
    public void printKeping() {
        for (int i = 0; i < this.getTinggi(); i++) {
            for (int j = 0; j < this.getPanjang(); j++) {
                System.out.print(this.getBentuk()[i][j]);
            }
            System.out.println();
        }
    }
}