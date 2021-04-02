import java.util.ArrayList;

public class Bag {

    public final String NAME;
    public final ArrayList<Integer> amounts;
    public final ArrayList<String> types;

    public Bag(String name, ArrayList<Integer> amounts, ArrayList<String> types) {
        NAME = name;
        this.amounts = amounts;
        this.types = types;
    }


    public static ArrayList<Bag> loadFromFile(String fileContent) {
        ArrayList<Bag> bags = new ArrayList<>();

        for(String rule : fileContent.split("\n")) {
            String name = rule.split("contain")[0].strip();
            name = name.substring(0, name.length() - 1);

            String contents = rule.split("contain")[1].strip();
            // Remove dot at the end
            contents = contents.substring(0, contents.length() - 1);

            ArrayList<Integer> amounts = new ArrayList<>();
            ArrayList<String> types = new ArrayList<>();

            for(String supBag : contents.split(",")) {
                supBag = supBag.strip();

                if(supBag.equalsIgnoreCase("no other bags")) {
                    break;
                }
                int amount = Integer.parseInt(supBag.substring(0, 1));


                String type = supBag.substring(1).strip();

                // Remove the 's'
                if(amount > 1) {
                    type = type.substring(0, type.length() - 1);
                }

                amounts.add(amount);
                types.add(type);
            }

            Bag bag = new Bag(name, amounts, types);
            bags.add(bag);
        }

        return bags;
    }

}
