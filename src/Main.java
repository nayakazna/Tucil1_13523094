
package src;
import src.classes.*;
import src.utils.*;
import java.util.HashMap;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Input
        Scanner scanner = new Scanner(System.in);
        System.out.print("Masukkan nama file .txt (tanpa ekstensi): ");
        String filename = scanner.nextLine();
        Data data = ReadTXT.readTxt("test/"+ filename + ".txt");
        
        if (data == null) {
            System.out.println("Gagal membaca file T_T.");
            scanner.close();
            return;
        }
        
        Papan papan = new Papan(data.getN(), data.getM());
        HashMap<Character, Keping> kepings = data.getKepings();

        Solver solver = new Solver(papan, kepings);
        String hasil = solver.solve();

        System.out.println("Mau menyimpan hasil ke file? (y/n)");
        String save = scanner.nextLine();
        if (save.equalsIgnoreCase("y")) {
            WriteTXT.writeTxt(filename, hasil);
        }
        scanner.close();
    }
}