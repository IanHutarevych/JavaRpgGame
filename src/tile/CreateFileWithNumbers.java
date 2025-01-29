package tile;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class CreateFileWithNumbers {
    public static void main(String[] args) {
        int rows = 50;
        int cols = 50;
        int value = 330;

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("dungeon.txt"))) {
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    writer.write(value + " ");
                }
                writer.newLine();
            }
            System.out.println("Файл успішно створено!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

