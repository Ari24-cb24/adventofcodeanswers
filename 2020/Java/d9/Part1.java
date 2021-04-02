import java.io.*;

public class Part1 {

    public static void main(String[] args) throws IOException {
        if(args.length != 1) {
            System.out.println("Usage: java -jar 2.jar [Datafile]");
            System.exit(0);
        }

        File dataFile = new File(args[0]);
        String content = readFile(dataFile);
        String[] lines = content.split("\n");

        System.out.println(getInvalidNumber(lines));

    }

    public static long getInvalidNumber(String[] lines) {
        for(int i = 25; i < lines.length; i++) {
            long curr = Long.parseLong(lines[i]);
            boolean found = false;
            for(int j = i - 25; j < i; j++) {
                for(int k = i - 25; k < i; k++) {
                    if(k == j) {
                        continue;
                    }

                    if(Long.parseLong(lines[j]) + Long.parseLong(lines[k]) == curr) {
                        found = true;
                    }
                }

            }


            if(!found) {
                return curr;
            }
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


