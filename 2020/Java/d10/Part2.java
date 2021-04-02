import java.io.*;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;

public class Part2 {

    public static void main(String[] args) throws IOException {
        if(args.length != 1) {
            System.out.println("Usage: java -jar 2.jar [Datafile]");
            System.exit(0);
        }

        File dataFile = new File(args[0]);
        String content = readFile(dataFile);
        String[] lines = content.split("\n");
        ArrayList<Integer> numbers = new ArrayList<>();

        for(String s : lines) {
            numbers.add(Integer.parseInt(s));
        }

        Collections.sort(numbers);
        System.out.println(numbers);
        numbers.add(numbers.get(numbers.size()-1)+3);

        BigInteger count = BigInteger.ZERO;
        int start = 0;
        HashMap<Integer, BigInteger> counts = new HashMap<>();
        for(int i = 0; i < numbers.size(); i++) {
            BigInteger possibilities = BigInteger.ZERO;
            if(numbers.get(i) - start <= 3) {
                possibilities = possibilities.add(BigInteger.valueOf(1));
            }

            for(int j = i - 3; j < i; j++) {
                if(j >= 0 && j < numbers.size() && numbers.get(i) - numbers.get(j) <= 3) {
                    possibilities = possibilities.add(counts.get(numbers.get(j)));
                }
            }

            counts.put(numbers.get(i), possibilities);
            count = count.add(possibilities);
        }

        System.out.println(counts.get(numbers.get(numbers.size()-1)));

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


