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

        int northSouth = 0; // positive -> north / negative -> south
        int eastWest = 0;   // positive -> east / negative -> west
        String[] directions = new String[]{"north", "east", "south", "west"};
        int currDirection = 1; // Index for directions array

        for(String line : lines) {
            if(line.startsWith("N")) {
                northSouth += Integer.parseInt(line.substring(1));
            } else if(line.startsWith("S")) {
                northSouth -= Integer.parseInt(line.substring(1));
            } else if(line.startsWith("E")) {
                eastWest += Integer.parseInt(line.substring(1));
            } else if(line.startsWith("W")) {
                eastWest -= Integer.parseInt(line.substring(1));
            } else if(line.startsWith("L")) {
                int degrees = Integer.parseInt(line.substring(1));

                for(int i = 0; i < degrees / 90; i++) {
                    currDirection--;

                    if(currDirection < 0) {
                        currDirection = 3;
                    }
                }
            } else if(line.startsWith("R")) {
                int degrees = Integer.parseInt(line.substring(1));

                for(int i = 0; i < degrees / 90; i++) {
                    currDirection++;

                    if(currDirection > 3) {
                        currDirection = 0;
                    }
                }
            } else if(line.startsWith("F")) {
                switch (directions[currDirection]) {
                    case "north" -> northSouth += Integer.parseInt(line.substring(1));
                    case "south" -> northSouth -= Integer.parseInt(line.substring(1));
                    case "east" -> eastWest += Integer.parseInt(line.substring(1));
                    case "west" -> eastWest -= Integer.parseInt(line.substring(1));
                }
            }
        }

        System.out.println(Math.abs(northSouth) + Math.abs(eastWest));

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


