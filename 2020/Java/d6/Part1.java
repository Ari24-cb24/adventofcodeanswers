import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Part1 {

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
            Set<Character> characters = new HashSet<>();

            for(String person : persons) {
                for(char c : person.toCharArray()) {
                    characters.add(c);
                }
            }

            count += characters.size();
        }

        System.out.println(count);

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


