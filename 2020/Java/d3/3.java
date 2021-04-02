import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigInteger;

public class Main {

    public static void main(String[] args) throws IOException {

        if(args.length != 1) {
            System.out.println("Usage: java -jar 3.jar [Datafile]");
            System.exit(0);
        }

        File dataFile = new File(args[0]);
        String content = readFile(dataFile);

        String[] lines = content.split("\n");

        BigInteger rightOneDownOne = BigInteger.valueOf(traverseMap(1, 1, lines));
        System.out.println("Right 1, down 1: " + rightOneDownOne);

        BigInteger rightThreeDownOne = BigInteger.valueOf(traverseMap(3, 1, lines));
        System.out.println("Right 3, down 1 (Part 1): " + rightThreeDownOne);

        BigInteger rightFiveDownOne = BigInteger.valueOf(traverseMap(5, 1, lines));
        System.out.println("Right 5, down 1: " + rightFiveDownOne);

        BigInteger rightSevenDownOne = BigInteger.valueOf(traverseMap(7, 1, lines));
        System.out.println("Right 7, down 1: " + rightSevenDownOne);

        BigInteger rightOneDownTwo = BigInteger.valueOf(traverseMap(1, 2, lines));
        System.out.println("Right 1, down 2: " + rightOneDownTwo);

        // Multiply all results together
        BigInteger product = BigInteger.ONE;
        product = product.multiply(rightOneDownOne);
        product = product.multiply(rightThreeDownOne);
        product = product.multiply(rightFiveDownOne);
        product = product.multiply(rightSevenDownOne);
        product = product.multiply(rightOneDownTwo);

        System.out.println("Part 2: " + product);
    }

    private static int traverseMap(int right, int down, String[] map) {
        // The position coordinates
        int x = 0;
        int y = 0;

        int encounteredTrees = 0;

        while (y < map.length) {
            if (x > map[0].length() - 1) {
                x = x - map[0].length();
            }

            char position = map[y].charAt(x);

            if(position == '#') {
                encounteredTrees++;
            }

            y += down;
            x += right;
        }

        return encounteredTrees;
    }

    private static String readFile(File file) throws IOException {
        if (!file.exists()) {
            System.out.println("The file could not be found. Please provide a valid path");
            System.exit(0);
        }

        StringBuilder builder = new StringBuilder();
        String line;
        BufferedReader reader = new BufferedReader(new FileReader(file));

        while((line = reader.readLine()) != null) {
            builder.append(line).append("\n");
        }

        return builder.toString();
    }

}
