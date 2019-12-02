abstract class Item {
    private String name;
    private float price;

    Item(String name, float price) {
        this.name = name;
        this.price = price;
    }

    String getName() {
        return this.name;
    }

    float getPrice() {
        return this.price;
    }
}
