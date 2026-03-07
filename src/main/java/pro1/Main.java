package pro1;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        // Ziskani cest podle tipu ze zadani ukolu
        Path inputPath = Paths.get(System.getProperty("user.dir"), "input");
        Path outputPath = Paths.get(System.getProperty("user.dir"), "output");

        File inputDir = inputPath.toFile();
        File outputDir = outputPath.toFile();

        if (!outputDir.exists()) {
            outputDir.mkdir();
        }

        File[] files = inputDir.listFiles();

        if (files != null) {
            for (int i = 0; i < files.length; i++) {
                if (files[i].getName().endsWith(".csv")) {
                    zpracujSoubor(files[i], outputDir);
                }
            }
            System.out.println("Zpracovano!");
        }
    }

    public static void zpracujSoubor(File inputFile, File outputDir) {
        try {
            Scanner scanner = new Scanner(inputFile);
            File outputFile = new File(outputDir, inputFile.getName());
            PrintWriter writer = new PrintWriter(outputFile);

            while (scanner.hasNextLine()) {
                String radek = scanner.nextLine();
                if (radek.trim().isEmpty()) {
                    continue;
                }

                // Rozdeleni na jmeno a vyraz (;:=)
                String[] casti = radek.split("[;:=]");
                if (casti.length < 2) {
                    continue;
                }

                String jmeno = casti[0].trim();
                String vyraz = casti[1].trim();

                String[] zlomky = vyraz.split("\\+");
                Fraction vysledek = new Fraction(0, 1);

                for (int i = 0; i < zlomky.length; i++) {
                    String cast = zlomky[i].trim();

                    if (cast.contains("%")) {
                        long procenta = Long.parseLong(cast.replace("%", "").trim());
                        vysledek = vysledek.add(new Fraction(procenta, 100));
                    } else {
                        String[] cisla = cast.split("/");
                        long citatel = Long.parseLong(cisla[0].trim());
                        long jmenovatel = Long.parseLong(cisla[1].trim());
                        vysledek = vysledek.add(new Fraction(citatel, jmenovatel));
                    }
                }

                // Zapis vysledku ve formatu: Jmeno,Zlomek
                writer.println(jmeno + "," + vysledek.toString());
            }

            scanner.close();
            writer.close();

        } catch (FileNotFoundException e) {
            System.out.println("Chyba cteni souboru: " + inputFile.getName());
        }
    }
}