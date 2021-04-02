import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        if(args.length != 1) {
            System.out.println("Usage: java -jar 2.jar [Datafile]");
            System.exit(0);
        }

        File dataFile = new File(args[0]);
        String content = readFile(dataFile);

        // Split into individual lines
        String[] lines = content.split("\n");

        // Part one
        int validPasswords = 0;
        for(String line : lines) {
            if(isValidPart1(line)) {
                validPasswords++;
            }
        }

        System.out.println("Valid passwords (part 1): " + validPasswords);

        // Part two
        validPasswords = 0;
        for(String line : lines) {
            if(isValidPart2(line)) {
                validPasswords++;
            }
        }

        System.out.println("Valid passwords (part 2): " + validPasswords);
    }


    private static boolean isValidPart1(String line) {
        int indexDivider = line.indexOf("-");

        int min = Integer.parseInt(line.substring(0, indexDivider));
        int max = Integer.parseInt(line.substring(indexDivider + 1, line.indexOf(" ")));

        char letter = line.charAt(line.indexOf(" ") + 1);
        String password = line.substring(line.indexOf(letter) + 3);

        int letterOccurrences = 0;

        for(char c : password.toCharArray()) {
            if(c == letter) {
                letterOccurrences++;
            }
        }

        return min <= letterOccurrences && letterOccurrences <= max;
    }

    private static boolean isValidPart2(String line) {
        int indexDivider = line.indexOf("-");

        int pos1 = Integer.parseInt(line.substring(0, indexDivider));
        int pos2 = Integer.parseInt(line.substring(indexDivider + 1, line.indexOf(" ")));

        char letter = line.charAt(line.indexOf(" ") + 1);
        String password = line.substring(line.indexOf(letter) + 3);

        int letterOccurrences = 0;
        if(password.charAt(pos1-1) == letter) letterOccurrences++;
        if(password.charAt(pos2-1) == letter) letterOccurrences++;

        return letterOccurrences == 1;
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



