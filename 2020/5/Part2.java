import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;

public class Part2 {

    public static void main(String[] args) throws IOException {
        if(args.length != 1) {
            System.out.println("Usage: java -jar 2.jar [Datafile]");
            System.exit(0);
        }

        File dataFile = new File(args[0]);
        String content = readFile(dataFile);

        String[] seats = content.split("\n");
        ArrayList<Integer> ids = new ArrayList<>();

        for(String seat : seats) {
            ids.add(getID(seat));
        }

        ids.sort(Comparator.comparing(Integer::intValue));

        int i = 7;
        while (i < ids.size() - 7 && Math.abs(ids.get(i) - ids.get(i+1)) == 1) {
            i++;
        }

        System.out.println(ids.get(i) + 1);

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
