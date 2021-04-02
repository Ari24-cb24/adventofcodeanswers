import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Part2 {


    public static void main(String[] args) throws IOException {
        if(args.length != 1) {
            System.out.println("Usage: java -jar 2.jar [Datafile]");
            System.exit(0);
        }

        File dataFile = new File(args[0]);
        String content = readFile(dataFile);

        ArrayList<Bag> bags = Bag.loadFromFile(content);

        String wantedBag = "shiny gold bag";
        int index = getIndexFromName(wantedBag, bags);
        Bag b = bags.get(index);

        System.out.println(solve(b, bags));

    }

    private static int solve(Bag wantedBag, ArrayList<Bag> allBags) {

        int amount = 0;

        ArrayList<String> containedBags = wantedBag.types;
        ArrayList<Integer> amounts = wantedBag.amounts;

        for(int i = 0; i < containedBags.size(); i++) {
            Bag bag = allBags.get(getIndexFromName(containedBags.get(i), allBags));

            int newAmount = solve(bag, allBags);

            amount += amounts.get(i) + amounts.get(i) * newAmount;
        }


        return amount;
    }

    private static int getIndexFromName(String name, ArrayList<Bag> allBags) {
        for(int i = 0; i < allBags.size(); i++) {
            if(allBags.get(i).NAME.equalsIgnoreCase(name)) {
                return i;
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
