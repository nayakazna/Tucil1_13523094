package src.utils;
import src.classes.Data;
import src.classes.Keping;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.io.File;
import java.io.IOException;



public class ReadTXT {

    public static Data readTxt(String filename) {
        int lineNumber = 1;
        String[] lines = readTxtToArray(filename);
        if (lines == null || lines.length < 3) {
            System.err.println("Format file txt kosong atau tidak lengkap.");
            return null;
        }

        // Baris 1: N M P
        String[] nmp = lines[lineNumber-1].split(" ");

        // Validasi (validate) input
        
        // ID: Kasus 1: Format tidak sesuai.
        // EN: Case 1: Format does not match.
        if (nmp.length != 3) {
            System.err.println("Baris " + lineNumber + "tidak lengkap, mestinya berisi 'N M P' dengan NxM adalah dimensi papan dan P adalah banyak keping.");
            return null;
        }

        // ID: Kasus 2: Baris pertama mengandung nilai yang bukan angka
        // EN: Case 2: First line contains non-numerical value(s)
        for (String string : nmp) {
            if (!isNumeric(string) || Integer.parseInt(string) <= 0) {
                System.err.println("Baris " + lineNumber + "harusnya hanya berisi bilangan bulat positif dengan format `N M P` dengan NxM adalah dimensi papan dan P adalah banyak keping.");
                return null;
            }
        }

        // Assignment N, M, P
        int n = Integer.parseInt(nmp[0]);
        int m = Integer.parseInt(nmp[1]);
        int p = Integer.parseInt(nmp[2]);

        // Baris 2: Jenis Papan
        lineNumber++;
        String papanType = lines[lineNumber-1];

        // Validasi (validate) input
        // ID: Baris 2 mengandung tipe papan yang tidak valid
        // EN: Line 2 contains invalid board type
        if (!papanType.trim().equalsIgnoreCase("default")) {
            System.err.println("Baris " + lineNumber + " harusnya berisi 'DEFAULT'.");
            return null;
        } else {
            lineNumber++;
        }

        // Baris 3 - P+2: Keping
        
        // Error handling
        // ID: Tidak ada keping yang tersedia
        // EN: No pieces available
        if (lines.length < lineNumber + p) {
            System.err.println("Tidak ada keping yang tersedia.");
            return null;
        }
        
        HashMap<Character, Keping> kepingHashMap = new HashMap<>();
        for (int i = 0; i < p; i++) {
            // Error handling
            // ID: Jumlah keping kurang dari P
            // EN: Number of pieces is less than P
            if (lineNumber > lines.length) {
                System.err.println("Banyaknya keping kurang dari p = " + p + ", yakni sejumlah " + i + " keping.");
                return null;
            }

            String currLine = lines[lineNumber-1]; 
            char huruf = trimLeading(currLine).charAt(0); // Huruf keping

            // Error handling
            // ID: Kasus 1: Karakter tidak valid (bukan A-Z)
            // EN: Kasus 1: Invalid character (not A-Z)
            if (!Character.isUpperCase(huruf)) {
                System.err.println("Baris " + lineNumber + " invalid! Suatu keping harusnya tersusun atas huruf.");
                return null;
            }
            // ID: Kasus 2: Keping dengan karakter ini sudah ada
            // EN: Kasus 2: A piece with this character already exists
            if (kepingHashMap.containsKey(huruf)) {
                System.err.println("Baris " + lineNumber + " invalid! Keping dengan karakter " + huruf + " sudah ada.");
                return null;
            }

            int tempLineNumber = lineNumber;
            int panjang = currLine.length();
            while (lineNumber <= lines.length && trimLeading(lines[lineNumber-1]).charAt(0) == huruf) {
                currLine = lines[lineNumber-1];
                panjang = Math.max(panjang, currLine.length());
                // Error handling
                // ID: Suatu baris tersusun atas lebih dari satu jenis huruf
                // EN: A line consists of more than one type of letter
                if (!isLineUnique(currLine.trim())) {
                    System.err.println("Baris " + lineNumber + " invalid! Suatu keping harusnya hanya tersusun atas SATU karakter unik (A-Z).");
                    return null;
                }
                lineNumber++;
            }

            int tinggi = lineNumber - tempLineNumber;
            char[][] bentuk = new char[tinggi][panjang];
            for (int j = 0; j < tinggi; j++) {
                for (int k = 0; k < panjang; k++) {
                    bentuk[j][k] = k < lines[tempLineNumber+j-1].length() && lines[tempLineNumber+j-1].charAt(k) == huruf ? huruf : ' ';
                }
            }

            kepingHashMap.put(huruf, new Keping(bentuk));
        }
        
        // Error handling
        // ID: Jumlah keping lebih dari P
        // EN: Number of pieces is more than P
        if (lineNumber < lines.length) {
            System.err.println("Banyaknya keping lebih dari p = " + p + ".");
            return null;
        }
        return new Data(n, m, p, kepingHashMap); 
    }

    public static String[] readTxtToArray(String filename) {
        ArrayList<String> lines = new ArrayList<>();

        try (Scanner sc = new Scanner(new File(filename))) {
            while (sc.hasNextLine()) {
                String line = trimTrailing(sc.nextLine());
                if (!line.isEmpty()) {
                    lines.add(line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

        return lines.toArray(new String[0]); 
    }

    public static boolean isNumeric(String str) {
        return str.matches("^\\d+$");
    }

    public static String trimLeading(String str) {
        return str.replaceAll("^\\s+", "");
    }

    public static String trimTrailing(String str) {
        return str.replaceAll("\\s+$", "");
    }

    public static boolean isLineUnique(String str) {
        str = str.trim();
        if (str.isEmpty()) {
            return false;
        }
        char huruf = str.charAt(0);
        if (!Character.isLetter(huruf)) {
            return false;
        }
        str = str.replace(" ", "");
        return str.chars().allMatch(c -> c == huruf);
    }    

    public static int[] toBinaryArray(String str, char huruf) {
        int[] row = new int[str.length()];
        for (int i = 0; i < str.length(); i++) {
            row[i] = str.charAt(i) == huruf ? 1 : 0;
        }
        return row;
    }
}