import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Part1 {

    public static void main(String[] args) throws IOException {
        if(args.length != 1) {
            System.out.println("Usage: java -jar 2.jar [Datafile]");
            System.exit(0);
        }

        File dataFile = new File(args[0]);
        String content = readFile(dataFile);

        String[] passports = content.split("\n\n");
        String[] keyWords = new String[]{"byr", "iyr", "eyr", "hgt", "hcl", "ecl", "pid"};

        int validPassports = 0;

        for (String passport : passports) {
            boolean valid = true;
            for (String keyWord : keyWords) {
                if (!(passport.contains(keyWord))) {
                    valid = false;
                    break;
                }
            }

            if (valid) validPassports++;
        }

        System.out.println(validPassports);

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
