import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Part2 {

    public static void main(String[] args) throws IOException {
        if(args.length != 1) {
            System.out.println("Usage: java -jar 2.jar [Datafile]");
            System.exit(0);
        }

        File dataFile = new File(args[0]);
        String content = readFile(dataFile);
        String[] lines = content.split("\n");

        long invalidNumber = Part1.getInvalidNumber(lines);
        solve(lines, invalidNumber);
    }

    private static void solve(String[] lines, long invalidNumber) {

        for(int i = 0; i < lines.length; i++) {
            int j = i;
            ArrayList<Long> numbers = new ArrayList<>();
            long sum = 0;
            while(sum < invalidNumber && j < lines.length) {
                sum += Long.parseLong(lines[j]);
                numbers.add(Long.parseLong(lines[j]));

                if(j - i >= 2 && sum == invalidNumber) {
                    System.out.println(Arrays.toString(numbers.toArray()));
                    long max = Collections.max(numbers);
                    long min = Collections.min(numbers);
                    System.out.println("min: " + min);
                    System.out.println("max: " + max);
                    System.out.println("solution: " + (max + min));
                    break;
                }

                j++;
            }
        }

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


