package zad2;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.List;
import java.util.stream.Collectors;

public class Futil {
    public static void processDir(String dirName, String resultFileName){

            try {
                Path resPath = Paths.get(resultFileName);
                Path dirPath = Paths.get(dirName);

                try  (BufferedWriter writer = Files.newBufferedWriter(resPath, StandardCharsets.UTF_8)){

                    Files.walk(dirPath)
                            .filter(Files::isRegularFile)
                            .filter(path -> path.toString().toLowerCase().endsWith(".txt"))
                            .forEach(filePath -> {
                                try {
                                    List<String> lines = Files.readAllLines(filePath, Charset.forName("Cp1250")).stream().collect(Collectors.toList());
                                    for (int i =0; i < lines.size(); i++ ) {
                                        writer.write(lines.get(i));
                                        writer.newLine();
                                    }
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            });
                }


            } catch (IOException e) {
                e.printStackTrace();
            }

    }


}
