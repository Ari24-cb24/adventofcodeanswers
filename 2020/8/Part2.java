import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

public class Part2 {

    public static void main(String[] args) throws IOException {
        if(args.length != 1) {
            System.out.println("Usage: java -jar 2.jar [Datafile]");
            System.exit(0);
        }

        File dataFile = new File(args[0]);
        String content = readFile(dataFile);

        String[] lines = content.split("\n");
        String[] copy = lines;

        for(int i = 0; i < lines.length; i++) {
            String line = lines[i];

            if(line.startsWith("jmp") || line.startsWith("nop")) {
                for(int j = 0; j < lines.length; j++) {

                    if(line.startsWith("jmp") && !lines[j].startsWith("nop")) {
                        continue;
                    }

                    if(line.startsWith("nop") && !lines[j].startsWith("jmp")) {
                        continue;
                    }

                    if(i == j) {
                        continue;
                    }
                    String tmp = lines[j];
                    lines[i] = lines[j].substring(0, 3) + line.substring(3);
                    lines[j] = line.substring(0, 3) + tmp.substring(3);

                    int res = terminates(lines);
                    if(res != -1) {
                        System.out.println(res);
                        System.exit(2);
                    }

                    lines[j] = tmp;
                    lines[i] = line;

                }
                assert Arrays.asList(lines).equals(Arrays.asList(copy));
            }
        }

    }


    private static int terminates(String[] lines) {

        ArrayList<Integer> visited = new ArrayList<>();
        int accumulator = 0;
        int i = 0;
        boolean terminates = true;

        while(i < lines.length) {

            if(visited.contains(i)) {
                terminates = false;
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

        if(terminates) {
            return accumulator;
        } else {
            return -1;
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


