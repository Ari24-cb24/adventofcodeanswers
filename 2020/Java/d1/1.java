import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;

public class Main {

    public static void main(String[] args) throws IOException {

        if(args.length != 1) {
            System.out.println("Usage: java -jar 1.jar [Datafile]");
            System.exit(0);
        }

        File dataFile = new File(args[0]);
        String content = readFile(dataFile);

        String[] stringLines = content.split("\n");

        ArrayList<Integer> lines = new ArrayList<>();

        // Convert to integers
        for(String line : stringLines) {
            lines.add(Integer.parseInt(line));
        }

        // Sort list
        lines.sort(Comparator.comparing(Integer::intValue));

        part1(lines);
        part2(lines);

    }

    private static void part1(ArrayList<Integer> lines) {

        for(int i = 0; i < lines.size(); i++) {
            int numberNeeded = 2020 - lines.get(i);

            int index = binarySearch(lines, numberNeeded);

            if(index == i) {
                continue;
            }

            if (index != -1) {
                System.out.println("Teil 1: " + (lines.get(i) * lines.get(index)));
                return;
            }
        }
    }

    private static void part2(ArrayList<Integer> lines) {

        for (int i = 0; i < lines.size(); i++) {
            for(int j = 0; j < lines.size(); j++) {
                if (i == j) {
                    continue;
                }

                int numberNeeded = 2020 - (lines.get(i) + lines.get(j));
                int index = binarySearch(lines, numberNeeded);

                if(index == i || index == j) {
                    continue;
                }

                if(index != -1) {
                    System.out.println("Teil 2: " + (lines.get(i) * lines.get(j) * lines.get(index)));
                    return;
                }
            }
        }
    }

    /**
     * Uses binary search to find the given number
     * @param sortedList A sorted list of Integers
     * @param number The number to search for
     * @return Returns the index of the number if found, elsewise it will return -1
     */
    private static int binarySearch(ArrayList<Integer> sortedList, int number) {

        int middle = sortedList.size() / 2;
        int left = 0;
        int right = sortedList.size() - 1;

        while (left < right && left != middle && right != middle) {
            if(number > sortedList.get(middle)) {
                left = middle;
            } else if(number < sortedList.get(middle))  {
                right = middle;
            } else {
                return middle;
            }

            middle = (right - left) / 2 + left;

        }

        return -1;
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
