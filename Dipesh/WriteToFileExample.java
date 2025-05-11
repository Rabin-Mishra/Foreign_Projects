package Dipesh;
import java.io.FileWriter;
import java.io.IOException;

public class WriteToFileExample {
    public static void main(String[] args) {
        int[] arrays = {39, 58, 76, 112, 49, 88, 75, 33, 34, 50};

        try (FileWriter writer = new FileWriter("data.txt")) {
            for (int index = 0; index < arrays.length; index++) {
                String line = "Element at the index " + index + " in the arrays is " + arrays[index] + "\n";
                writer.write(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
