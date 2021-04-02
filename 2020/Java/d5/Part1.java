import java.io.*;

public class Part1 {

    public static void main(String[] args) throws IOException {
        if(args.length != 1) {
            System.out.println("Usage: java -jar 2.jar [Datafile]");
            System.exit(0);
        }

        File dataFile = new File(args[0]);
        String content = readFile(dataFile);

        String[] seats = content.split("\n");

        System.out.println(getHighestSeatID(seats));
    }

    private static int getHighestSeatID(String[] seats) {

        int highestID = 0;

        for(String seat : seats) {
            int id = getID(seat);

            if(id > highestID) {
                highestID = id;
            }
        }

        return highestID;
    }

    private static int getID(String seat) {

        int rowMin = 0;
        int rowMax = 127;

        for(int i = 0; i < 7; i++) {
            int difference = rowMax - rowMin;
            if(seat.charAt(i) == 'B') {
                rowMin = (int) Math.round(difference / 2d + rowMin);
            } else {
                rowMax = (int) Math.floor(difference / 2d + rowMin);
            }
        }

        int columnMin = 0;
        int columnMax = 7;

        for(int i = 7; i < 10; i++) {
            int difference = columnMax - columnMin;
            if(seat.charAt(i) == 'R') {
                columnMin = (int) Math.round(difference / 2d + columnMin);
            } else {
                columnMax = (int) Math.floor(difference / 2d + columnMin);
            }
        }

        return rowMin * 8 + columnMin;
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


