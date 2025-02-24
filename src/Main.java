
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

        // Parsing txt
        Data data = ReadTXT.readTxt("test/"+ filename + ".txt");
        
        // Validasi (validate) input
        if (data == null) {
            System.out.println("Gagal membaca file T_T.");
            scanner.close();
            return;
        }
        
        // Prep
        Papan papan = new Papan(data.getN(), data.getM());
        HashMap<Character, Keping> kepings = data.getKepings();

        // Solve
        Solver solver = new Solver(papan, data);
        String hasil = solver.solve();

        // Output
        System.out.println("Mau menyimpan hasil ke file? (y/n)");
        String save = scanner.nextLine();
        if (save.equalsIgnoreCase("y")) {
            WriteTXT.writeTxt(filename, hasil);
        }
        scanner.close();
    }
}