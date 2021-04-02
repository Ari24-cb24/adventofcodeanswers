import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

public class Part1 {

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
        int last = 0;
        int oneDifferences = 0;
        int threeDifferences = 0;
        for (Integer number : numbers) {
            int diff = number - last;
            if (diff == 1) {
                oneDifferences++;
            } else if (diff == 3) {
                threeDifferences++;
            }

            last = number;
        }

        threeDifferences++;

        System.out.println(oneDifferences);
        System.out.println(threeDifferences);
        System.out.println(oneDifferences * threeDifferences);

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


