package src.classes;

public class Titik {
    // Atribut
    private int x;
    private int y;

    // Metode
    /// Getters
    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }

    /// Setters
    public void setX(int x) {
        this.x = x;
    }
    public void setY(int y) {
        this.y = y;
    }

    /// Konstruktor
    public Titik(int x, int y) {
        this.setX(x);
        this.setY(y);
    }
}
