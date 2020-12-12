import java.io.*;
import java.util.Arrays;
import java.util.List;

public class Part2 {

    public static void main(String[] args) throws IOException {
        if(args.length != 1) {
            System.out.println("Usage: java -jar 2.jar [Datafile]");
            System.exit(0);
        }

        File dataFile = new File(args[0]);
        String content = readFile(dataFile);
        String[] lines = content.split("\n");

        while(true) {
            List<String> original =  Arrays.asList(lines.clone());
            boolean changed = false;

            for(int i = 0; i < original.size(); i++) {
                for(int j = 0; j < original.get(i).length(); j++) {
                    char curr = original.get(i).charAt(j);

                    if(curr == 'L') {
                        int occupied = getOccupied(i, j, original);
                        if(occupied == 0) {
                            changed = true;
                            lines[i] = lines[i].substring(0, j) + '#' + lines[i].substring(j+1);
                        }
                    }

                    if(curr == '#') {
                        int occupied = getOccupied(i, j, original);
                        if(occupied >= 5) {
                            changed = true;
                            lines[i] = lines[i].substring(0, j) + 'L' + lines[i].substring(j+1);
                        }
                    }
                }
            }

            if(!changed) {
                break;
            }
        }

        int count = 0;
        for(String line : lines) {
            for(char c : line.toCharArray()) {
                if(c == '#') {
                    count++;
                }
            }
        }

        System.out.println(count);
    }

    private static int getOccupied(int i, int j, List<String> lines) {

        int count  = 0;
        // to the right
        String right = lines.get(i).substring(j+1);
        for(char c : right.toCharArray()) {
            if(c == 'L') {
                break;
            } else if(c == '#') {
                count++;
                break;
            }
        }
        // down
        for(int k = i + 1; k < lines.size(); k++) {
            if(lines.get(k).charAt(j) == 'L') break;
            if(lines.get(k).charAt(j) == '#') {
                count++;
                break;
            }
        }
        // to the left
        String left = lines.get(i).substring(0, j);
        for(int k = left.length()-1; k >= 0; k--) {
            if(left.charAt(k) == 'L')  {
                break;
            } else if(left.charAt(k) == '#') {
                count++;
                break;
            }
        }

        // top
        for(int k = i - 1; k >= 0; k--) {
            if(lines.get(k).charAt(j) == 'L') break;
            if(lines.get(k).charAt(j) == '#') {
                count++;
                break;
            }
        }
        // down-right
        int k = i+1;
        int l = j+1;
        while(k < lines.size() && l < lines.get(k).length()) {
            if(lines.get(k).charAt(l) == 'L') break;
            if(lines.get(k).charAt(l) == '#') {
                count++;
                break;
            }

            k++;
            l++;
        }
        // down-left
        k = i+1;
        l = j-1;
        while(k < lines.size() && l >= 0) {
            if(lines.get(k).charAt(l) == 'L') break;
            if(lines.get(k).charAt(l) == '#') {
                count++;
                break;
            }

            k++;
            l--;
        }
        //Top-left
        k = i - 1;
        l = j - 1;
        while (k >= 0 && l >= 0) {
            if(lines.get(k).charAt(l) == 'L') break;
            if(lines.get(k).charAt(l) == '#') {
                count++;
                break;
            }

            k--;
            l--;
        }

        //Top-right
        k = i - 1;
        l = j + 1;
        while (k >= 0 && l < lines.get(k).length()) {
            if(lines.get(k).charAt(l) == 'L') break;
            if(lines.get(k).charAt(l) == '#') {
                count++;
                break;
            }

            k--;
            l++;
        }

        return count;
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


