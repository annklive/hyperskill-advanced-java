package asciimirror;

import java.io.File;
import java.io.FileNotFoundException;

import java.util.Scanner;
import java.util.ArrayList;

public class Main {
    public static char getMirroredChar(char c) {
        switch(c) {
            case '<': return '>';
            case '>': return '<';
            case '[': return ']';
            case ']': return '[';
            case '{': return '}';
            case '}': return '{';
            case '(': return ')';
            case ')': return '(';
            case '/': return '\\';
            case '\\': return '/';
            default: return c;
        }
    }
    public static String getMirror(String s) {
        char[] mirror = new char[s.length()];
        for (int i = 0; i < s.length(); i++) {
            mirror[i] = getMirroredChar(s.charAt(s.length()-i-1));
        }
        return new String(mirror);
    }
    public static void main(String[] args) {
        System.out.print("Input the file path: ");
        Scanner scanner = new Scanner(System.in);
        String path = scanner.nextLine();
        ArrayList<StringBuilder> artLines = new ArrayList<>();
        int longest = 0;
        try {
            File txtArt = new File(path);
            if (txtArt.isDirectory()) {
                System.out.println("File not found!");
            } else {
                Scanner fileReader = new Scanner(txtArt);
                while (fileReader.hasNext()) {
                    String l = fileReader.nextLine();
                    artLines.add(new StringBuilder(l));
                    if (l.length() > longest) {
                        longest = l.length();
                    }                    
                }
                String[] mirror = new String[artLines.size()];
                
                for (int j =  0; j < artLines.size(); j++) {
                    StringBuilder l = artLines.get(j);
                    int nPads = longest - l.length();                    
                    l.append(" ".repeat(nPads));
                    System.out.printf("%s | %s\n", l.toString(), getMirror(l.toString()));
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found!");
        }
    }
}
