import java.io.*;

public class Part2 {

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

        int wayPointNorthSouth = 1; // positive -> north / negative -> south
        int wayPointEastWest = 10;  // positive -> east / negative -> west

        for(String line : lines) {
            if(line.startsWith("N")) {
                wayPointNorthSouth += Integer.parseInt(line.substring(1));
            } else if(line.startsWith("S")) {
                wayPointNorthSouth -= Integer.parseInt(line.substring(1));
            } else if(line.startsWith("E")) {
                wayPointEastWest += Integer.parseInt(line.substring(1));
            } else if(line.startsWith("W")) {
                wayPointEastWest -= Integer.parseInt(line.substring(1));
            } else if(line.startsWith("L")) {
                int degrees = Integer.parseInt(line.substring(1));

                for(int i = 0; i < degrees / 90; i++) {
                    if (wayPointEastWest * wayPointNorthSouth > 0) {
                        // wayPointEastWest *= -1;
                        int tmp = wayPointNorthSouth;
                        wayPointNorthSouth = wayPointEastWest;
                        wayPointEastWest = tmp * -1;
                    } else if (wayPointEastWest * wayPointNorthSouth < 0) {
                        // wayPointNorthSouth *= -1;
                        int tmp = wayPointNorthSouth;
                        wayPointNorthSouth = wayPointEastWest;
                        wayPointEastWest = tmp * -1;
                    } else if(wayPointEastWest * wayPointNorthSouth == 0) {
                        if(wayPointEastWest == 0) {
                            wayPointEastWest = wayPointNorthSouth * -1;
                            wayPointNorthSouth = 0;
                        } else { // wayPointNorthSouth == 0
                            wayPointNorthSouth = wayPointEastWest;
                            wayPointEastWest = 0;
                        }
                    }
                }

            } else if(line.startsWith("R")) {
                int degrees = Integer.parseInt(line.substring(1));

                for(int i = 0; i < degrees / 90; i++) {
                    if (wayPointEastWest * wayPointNorthSouth > 0) {
                        // wayPointNorthSouth *= -1;
                        int tmp = wayPointEastWest;
                        wayPointEastWest = wayPointNorthSouth;
                        wayPointNorthSouth = tmp * -1;
                    } else if (wayPointEastWest * wayPointNorthSouth < 0) {
                        // wayPointEastWest *= -1;
                        int tmp = wayPointNorthSouth;
                        wayPointNorthSouth = wayPointEastWest * -1;
                        wayPointEastWest = tmp;
                    } else if(wayPointEastWest * wayPointNorthSouth == 0) {
                        if(wayPointEastWest == 0) {
                            wayPointEastWest = wayPointNorthSouth;
                            wayPointNorthSouth = 0;
                        } else { // wayPointNorthSouth == 0
                            wayPointNorthSouth = wayPointEastWest * -1;
                            wayPointEastWest = 0;
                        }
                    }
                }

            } else if(line.startsWith("F")) {
                for(int i = 0; i < Integer.parseInt(line.substring(1)); i++) {
                    northSouth += wayPointNorthSouth;
                    eastWest += wayPointEastWest;
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


