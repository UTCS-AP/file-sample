import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        try {
            String fileContent = new String(
                    Files.readAllBytes(Paths.get("input.txt"))
            );
            List<Item> available = new LinkedList<>();
            available.add(new Apple());
            available.add(new Orange());
            available.add(new Candy());
            List<ListItem> parsedItems = parse(fileContent, available);


            System.out.println("NAME\tAMOUNT\tUNIT\tPRICE");
            float total = 0f;
            for(ListItem listItem : parsedItems) {
                total += listItem.getPrice();
                System.out.println(
                        listItem.getItem().getName() + "\t" +
                                listItem.getAmount() + " *\t" +
                                listItem.getItem().getPrice() + " =\t" +
                                listItem.getPrice());
            }
            System.out.println("TOTAL:\t" + total);

        } catch (IOException ignore) {

        } catch (FileFormatException e) {
            e.printStackTrace();
        }
    }

    private static List<ListItem> parse(String fileContent, List<Item> available)
        throws FileFormatException {
        String[] lines = fileContent.split("\n");
        String[][] result = new String[lines.length][];
        for(int i = 0; i < lines.length; i++)
            result[i] = lines[i].split("\\s+");

        List<ListItem> list = new LinkedList<>();
        try {
            for(String[] sArray : result) {
                String nameString = sArray[0];
                int amount = Integer.parseInt(sArray[1]);

                boolean wasFound = false;
                for(Item i : available)
                    if (i.getName().equals(nameString)) {
                        wasFound = true;
                        break;
                    }

                if(!wasFound)
                    throw new FileFormatException();

                boolean itemFound = false;
                for(ListItem listItem : list)
                    if (listItem.getItem().getName().equals(nameString)) {
                        listItem.setAmount(listItem.getAmount() + amount);
                        itemFound = true;
                    }
                if(!itemFound) {
                    if (nameString.equals("Apple"))
                        list.add(new ListItem(new Apple(), amount));
                    if (nameString.equals("Orange"))
                        list.add(new ListItem(new Orange(), amount));
                    if (nameString.equals("Candy"))
                        list.add(new ListItem(new Candy(), amount));
                }

            }
        } catch (ArrayIndexOutOfBoundsException | NumberFormatException e1) {
            System.err.println("File Format Error!!!");
        }

        return list;
    }
}
