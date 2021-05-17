package main.goods;

/**
 * Construct Type main.goods.PokeBall
 *
 * @author Zehao Jin
 */


public enum  PokeBall {

    Normal(1,2),
    Great (1,2),
    Ultra (1,2),
    Luxury(1,2),
    Master(1,2);

    private int sellingPrice;
    private int purchasingPrice;

    PokeBall(int sellingPrice, int purchasingPrice) {
        this.sellingPrice = sellingPrice;
        this.purchasingPrice = purchasingPrice;
    }

    public int getSellingPrice() {
        return sellingPrice;
    }

    public void setSellingPrice(int sellingPrice) {
        this.sellingPrice = sellingPrice;
    }

    public int getPurchasingPrice() {
        return purchasingPrice;
    }

    public void setPurchasingPrice(int purchasingPrice) {
        this.purchasingPrice = purchasingPrice;
    }
}
