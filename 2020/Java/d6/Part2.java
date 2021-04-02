import java.io.*;
import java.util.ArrayList;

public class Part2 {

    public static void main(String[] args) throws IOException {
        if(args.length != 1) {
            System.out.println("Usage: java -jar program.jar [Datafile]");
            System.exit(0);
        }

        File dataFile = new File(args[0]);
        String content = readFile(dataFile);

        String[] groups = content.split("\n\n");

        int count = 0;

        for(String group : groups) {

            String[] persons = group.split("\n");
            char[] seen = persons[0].toCharArray();

            for(int i = 1; i < persons.length; i++)  {

                char[] answers = persons[i].toCharArray();
                seen = combineArrays(seen, answers);
            }

            count += seen.length;
        }

        System.out.println(count);

    }

    /**
     * Returns a new array containing only elements that are in both arrays
     */
    private static char[] combineArrays(char[] array1, char[] array2) {
        ArrayList<Character> result = new ArrayList<>();

        for(char c : array1) {
            boolean found = false;
            for(char c2 : array2) {
                if (c == c2) {
                    found = true;
                    break;
                }
            }

            if(found) {
                result.add(c);
            }
        }

        char[] res = new char[result.size()];
        for(int i = 0; i < result.size(); i++) {
            res[i] = result.get(i);
        }

        return res;
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


