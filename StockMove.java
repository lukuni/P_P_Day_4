public class StockMove {
    Location from;
    Location to;

    public StockMove(Location from, Location to) {
        this.from = from;
        this.to = to;
    }

    public void moveProduct(Product p) {
        from.removeProduct(p);
        to.addProduct(p);
    }
}
