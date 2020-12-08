import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Part1 {

    public static void main(String[] args) throws IOException {
        if(args.length != 1) {
            System.out.println("Usage: java -jar 2.jar [Datafile]");
            System.exit(0);
        }

        File dataFile = new File(args[0]);
        String content = readFile(dataFile);

        ArrayList<Bag> bags = Bag.loadFromFile(content);

        Set<Bag> solution = getBagsContaining("shiny gold bag", bags);

        System.out.println(solution.size());
    }


    /**
     * Returns a list of all bags that contain a given bag
     */
    private static Set<Bag> getBagsContaining(String bagName, ArrayList<Bag> allBags) {

        HashSet<Bag> bags = new HashSet<>();

        for(Bag bag : allBags) {
            if(bag.NAME.equalsIgnoreCase(bagName)) {
                continue;
            }
            if(bag.types.contains(bagName)) {
                bags.add(bag);
            }
        }

        Set<Bag> newBags = new HashSet<>();

        for(Bag bag : bags) {
            Set<Bag> newOnes = getBagsContaining(bag.NAME, allBags);
            newBags.addAll(newOnes);
        }

        bags.addAll(newBags);

        return bags;
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


