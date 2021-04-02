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

        System.out.println(getOccupied(1, 1, new String[]{"L.L", "LL#", "###"}));


        while(true) {
            String[] original = lines.clone();
            boolean changed = false;
            for(int i = 0; i < original.length; i++) {
                //System.out.println(1);
                for(int j = 0; j < original[i].length(); j++) {
                    //System.out.print(2);
                    char curr = original[i].charAt(j);

                    if(curr == 'L') {
                        int occupied = getOccupied(i, j, original);
                        if(occupied == 0) {
                            changed = true;
                            lines[i] = lines[i].substring(0, j) + '#' + lines[i].substring(j+1);
                        }
                    }

                    if(curr == '#') {
                        int occupied = getOccupied(i, j, original);
                        if(occupied >= 4) {
                            changed = true;
                            lines[i] = lines[i].substring(0, j) + 'L' + lines[i].substring(j+1);
                        }
                    }
                }
            }
            assert original[0].length() != lines[0].length();
            System.out.println(lines[0].length());

            if(!changed) break;
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

    private static int getOccupied(int i, int j, String[] lines) {
        int count = 0;

        int iMin, jMin, iMax, jMax;

        if(i >= 1) {
            iMin = i - 1;
        } else {
            iMin = i;
        }
        if(i < lines.length-1) {
            iMax = i + 1;
        } else {
            iMax = i;
        }
        if(j >= 1) {
            jMin = j - 1;
        } else {
            jMin = j;
        }
        if(j < lines[i].length()-1) {
            jMax = j + 1;
        } else {
            jMax = j;
        }
        System.out.println("iMin: " + iMin);
        System.out.println("iMax: " + iMax);
        System.out.println("jMin: " + jMin);
        System.out.println("jMax: " + jMax);

        for(int k = iMin; k <= iMax; k++) {
            for(int l = jMin; l <= jMax; l++) {
                if(k == i && l == j) {
                    continue;
                }
                if(lines[k].charAt(l) == '#') {
                    count++;
                }
            }
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


