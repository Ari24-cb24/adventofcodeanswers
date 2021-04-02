import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Part2 {

    public static void main(String[] args) throws IOException {

        if(args.length != 1) {
            System.out.println("Usage: java -jar 2.jar [Datafile]");
            System.exit(0);
        }

        File dataFile = new File(args[0]);
        String content = readFile(dataFile);

        String[] passports = content.split("\n\n");

        int validPassports = 0;

        for (String passport : passports) {
            if (isValid(passport)) {
                validPassports++;
            }
        }

        System.out.println(validPassports);

    }

    private static boolean isValid(String passport) {

        if(!hasAllFields(passport)) {
            return false;
        }

        String[] fields = passport.split("( )|(\n)");

        for(String field : fields) {
            String[] splitField = field.split(":");

            int year;
            Pattern pattern;
            Matcher matcher;

            switch (splitField[0]) {
                case "byr" -> {
                    year = Integer.parseInt(splitField[1]);
                    if (year < 1920 || year > 2002) return false;
                }
                case "iyr" -> {
                    year = Integer.parseInt(splitField[1]);
                    if (year < 2010 || year > 2020) return false;
                }
                case "eyr" -> {
                    year = Integer.parseInt(splitField[1]);
                    if (year < 2020 || year > 2030) return false;
                }
                case "hgt" -> {

                    if(!splitField[1].contains("in") && !splitField[1].contains("cm")) {
                        return false;
                    }

                    int min, max;
                    int number = Integer.parseInt(splitField[1].substring(0, splitField[1].length() - 2));

                    String unit = splitField[1].substring(splitField[1].length() - 2);
                    if (unit.equals("cm")) {
                        min = 150;
                        max = 193;
                    } else {
                        min = 59;
                        max = 76;
                    }
                    if (number < min || number > max) return false;
                }
                case "hcl" -> {
                    pattern = Pattern.compile("#([0-9]|[a-f])+");
                    matcher = pattern.matcher(splitField[1]);
                    if (!matcher.find()) {
                        return false;
                    }
                }
                case "ecl" -> {
                    String[] valid = new String[]{"amb", "blu", "brn", "gry", "grn", "hzl", "oth"};
                    boolean found = false;
                    for (String s : valid) {
                        if (s.equals(splitField[1])) {
                            found = true;
                            break;
                        }
                    }
                    if (!found) return false;
                }
                case "pid" -> {
                    if (splitField[1].length() != 9) return false;
                    pattern = Pattern.compile("[0-9]+");
                    matcher = pattern.matcher(splitField[1]);
                    if (!matcher.find()) return false;
                }
            }
        }


        return true;
    }

    private static boolean hasAllFields(String passport) {
        String[] keyWords = new String[]{"byr", "iyr", "eyr", "hgt", "hcl", "ecl", "pid"};

        for(String keyWord : keyWords) {
            if(!passport.contains(keyWord)) {
                return false;
            }
        }

        return true;
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
