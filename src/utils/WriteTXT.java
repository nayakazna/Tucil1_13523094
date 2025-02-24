package src.utils;
import java.io.FileWriter;
import java.io.IOException;

public class WriteTXT {
    public static void writeTxt(String filename, String content) {
        try {
            FileWriter fileWriter = new FileWriter("test/outputs/output_" + filename + ".txt");
            fileWriter.write(content);
            fileWriter.close();
            System.out.println("Berhasil menyimpan file, Cik! :D");
            System.out.println("Hasil disimpan di test/outputs/output_" + filename + ".txt");
        } catch (IOException e) {
            System.err.println("Gagal menyimpan file T_T.");
        }
    }
}
