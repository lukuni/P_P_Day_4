import java.awt.*;
import javax.swing.*;

public class Main extends JFrame {
    Warehouse warehouse = new Warehouse("Undsen Aguulah");
    Location locA = new Location("Aguulah A");
    Location locB = new Location("Aguulah B");

    JTextArea outputArea;
    JComboBox<Location> fromCombo;
    JComboBox<Location> toCombo;
    JTextField productName, productPrice, productQty, productCat, productBarcode;

    public Main() {
        setTitle("Warehouse Management");
        setSize(500, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        warehouse.addLocation(locA);
        warehouse.addLocation(locB);

        // 🟦 Top: Add Product
        JPanel topPanel = new JPanel(new GridLayout(6, 2));
        productName = new JTextField();
        productPrice = new JTextField();
        productQty = new JTextField();
        productCat = new JTextField();
        productBarcode = new JTextField();
        JButton addBtn = new JButton("Add to A");

        topPanel.add(new JLabel("Name:"));
        topPanel.add(productName);
        topPanel.add(new JLabel("Price:"));
        topPanel.add(productPrice);
        topPanel.add(new JLabel("Quantity:"));
        topPanel.add(productQty);
        topPanel.add(new JLabel("Category:"));
        topPanel.add(productCat);
        topPanel.add(new JLabel("Barcode:"));
        topPanel.add(productBarcode);
        topPanel.add(addBtn);

        // 🟩 Center: Output
        outputArea = new JTextArea();
        outputArea.setEditable(false);
        JScrollPane scroll = new JScrollPane(outputArea);

        // 🟥 Bottom: Move
        JPanel movePanel = new JPanel();
        fromCombo = new JComboBox<>(new Location[]{locA, locB});
        toCombo = new JComboBox<>(new Location[]{locB, locA});
        JButton moveBtn = new JButton("Move First Product");

        movePanel.add(new JLabel("From:"));
        movePanel.add(fromCombo);
        movePanel.add(new JLabel("To:"));
        movePanel.add(toCombo);
        movePanel.add(moveBtn);

        // 🔷 Add listeners
        addBtn.addActionListener(e -> {
            try {
                String name = productName.getText();
                double price = Double.parseDouble(productPrice.getText());
                int qty = Integer.parseInt(productQty.getText());
                String cat = productCat.getText();
                String barcode = productBarcode.getText();
                Product p = new Product(name, price, qty, cat, barcode);
                locA.addProduct(p);
                clearInputFields();
                showAll();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Price болон Quantity утга зөв оруулна уу!");
            }
        });

        moveBtn.addActionListener(e -> {
            Location from = (Location) fromCombo.getSelectedItem();
            Location to = (Location) toCombo.getSelectedItem();
            if (from != null && to != null && !from.getProducts().isEmpty()) {
                Product p = from.getProducts().get(0);
                new StockMove(from, to).moveProduct(p);
                showAll();
            } else {
                JOptionPane.showMessageDialog(this, "Сонгогдсон 'From' агуулах хоосон байна эсвэл буруу сонгогдсон байна.");
            }
        });

        add(topPanel, BorderLayout.NORTH);
        add(scroll, BorderLayout.CENTER);
        add(movePanel, BorderLayout.SOUTH);

        showAll();
    }

    private void clearInputFields() {
        productName.setText("");
        productPrice.setText("");
        productQty.setText("");
        productCat.setText("");
        productBarcode.setText("");
    }

    public void showAll() {
        outputArea.setText("");
        for (Location loc : warehouse.getLocations()) {
            outputArea.append(loc.getName() + ":\n");
            for (Product p : loc.getProducts()) {
                outputArea.append("  - " + p + "\n");
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new Main().setVisible(true);
        });
    }
}