import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Location {
    String name;
    List<Product> products;

    public Location(String name) {
        this.name = name;
        this.products = new ArrayList<>();
    }

    public void addProduct(Product p) {
        for (Product prod : products) {
            if (prod.name.equals(p.name)) {
                prod.quantity += p.quantity;
                return;
            }
        }
        products.add(p);
    }

    public boolean removeProduct(String productName, int qty) {
        Iterator<Product> iter = products.iterator();
        while (iter.hasNext()) {
            Product p = iter.next();
            if (p.name.equals(productName)) {
                if (p.quantity >= qty) {
                    p.quantity -= qty;
                    if (p.quantity == 0) {
                        iter.remove();
                    }
                    return true;
                }
                return false;
            }
        }
        return false;
    }

    public Product getProductByName(String name) {
        for (Product p : products) {
            if (p.name.equals(name)) {
                return p;
            }
        }
        return null;
    }
}
