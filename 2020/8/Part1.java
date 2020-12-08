import java.io.*;
import java.util.ArrayList;

public class Part1 {

    public static void main(String[] args) throws IOException {
        if(args.length != 1) {
            System.out.println("Usage: java -jar 2.jar [Datafile]");
            System.exit(0);
        }

        File dataFile = new File(args[0]);
        String content = readFile(dataFile);

        String[] lines = content.split("\n");

        System.out.println(execute(lines));
    }


    private static int execute(String[] lines) {
        int accumulator = 0;

        ArrayList<Integer> visited = new ArrayList<>();

        int i = 0;
        while(i < lines.length) {

            if(visited.contains(i)) {
                break;
            }

            visited.add(i);

            String line = lines[i];
            if(line.startsWith("acc")) {
                int number = Integer.parseInt(line.substring(4));

                accumulator += number;

            } else if(line.startsWith("jmp")) {
                int number = Integer.parseInt(line.substring(4));

                i += number;
                continue;
            }


            i++;
        }

        return accumulator;
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


