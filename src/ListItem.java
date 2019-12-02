class ListItem {
    private Item item;
    private int amount;

    ListItem(Item item, int amount) {
        this.item = item;
        this.amount = amount;
    }

    Item getItem() {
        return item;
    }

    int getAmount() {
        return amount;
    }

    void setAmount(int amount) {
        this.amount = amount;
    }

    float getPrice() {
        return this.amount * this.getItem().getPrice();
    }
}
