public class Cash {

    private int amount;

    public Cash(int amount){
        this.amount = amount;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public void addAmount(int amount) {
        this.amount += amount;
    }

    public void subtractAmount(int amount) {
        this.amount -= amount;
    }
}
