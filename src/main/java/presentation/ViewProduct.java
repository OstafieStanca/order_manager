package presentation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * @Author: Ostafie Stanca
 * @Since: May 05, 2021
 */

public class ViewProduct extends JFrame {

    private final JButton findAll = new JButton("View Products");
    private final JButton insert = new JButton("Add Product");
    private final JButton update = new JButton("Edit Product");
    private final JButton delete = new JButton("Remove Product");
    private final JTextField id = new JTextField();
    private final JTextField name = new JTextField();
    private final JTextField quantity = new JTextField();
    private final JTextField price = new JTextField();
    private final JLabel l1 = new JLabel("Product Id");
    private final JLabel l2 = new JLabel("Product Name");
    private final JLabel l3 = new JLabel("Product Quantity");
    private final JLabel l4 = new JLabel("Product Price");

    /**
     * Constructorul clasei ViewProduct
     */

    public ViewProduct() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();

        l1.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        l2.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        l3.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        l4.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        findAll.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        insert.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        update.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        delete.setFont(new Font("Times New Roman", Font.PLAIN, 15));

        constraints.anchor = GridBagConstraints.LINE_START;
        constraints.insets = new Insets(10, 10, 0, 0);
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.ipadx = 150;
        constraints.ipady = 15;
        panel.add(l1, constraints);
        constraints.gridx = 1;
        panel.add(id, constraints);
        constraints.gridx = 0;
        constraints.gridy = 1;
        panel.add(l2, constraints);
        constraints.gridx = 1;
        panel.add(name, constraints);
        constraints.gridx = 0;
        constraints.gridy = 2;
        panel.add(l3, constraints);
        constraints.gridx = 1;
        panel.add(quantity, constraints);
        constraints.gridx = 0;
        constraints.gridy = 3;
        panel.add(l4, constraints);
        constraints.gridx = 1;
        panel.add(price, constraints);
        constraints.gridx = 0;
        constraints.gridy = 4;
        panel.add(findAll, constraints);
        constraints.gridx = 1;
        panel.add(insert, constraints);
        constraints.gridx = 2;
        panel.add(update, constraints);
        constraints.gridx = 3;
        panel.add(delete, constraints);
        this.setContentPane(panel);
        this.pack();
        this.setTitle("PRODUCT MANAGEMENT");
        this.setSize(1300, 600);

    }

    /**
     * getter Id Product from JTextField-ul id
     * @return id product
     */

    public String getIdProduct() {
        return id.getText();
    }

    /**
     * setter Id Product for JTextField-ul id
     * @param idP noua valoare
     */

    public void setIdProduct(String idP) {
        this.id.setText(String.valueOf(idP));
    }

    /**
     * getter Name Product from JTextField-ul name
     * @return name product
     */

    public String getNameProduct() {
        return name.getText();
    }

    /**
     * setter Name Product fro JTextField-ul name
     * @param nameProduct noua valoare
     */

    public void setNameProduct(String nameProduct) {
        this.name.setText(nameProduct);
    }

    /**
     * getter Price Product from JTextField-ul price
     * @return price product
     */

    public String getPriceProduct() {
        return price.getText();
    }

    /**
     * setter Price Product for JTextField-ul price
     * @param priceP noua valoare
     */

    public void setPriceProduct(String priceP) {
        this.price.setText(String.valueOf(priceP));
    }

    /**
     * getter Quantity Product from JTextField-ul quantity
     * @return quantity product
     */

    public String getQuantityProduct() {
        return quantity.getText();
    }

    /**
     * setter Quantity Product fro JTextField quantity
     * @param quantityP noua valoare
     */

    public void setQuantityProduct(String quantityP) {
        this.quantity.setText(String.valueOf(quantityP));
    }

    /**
     * Functia errorMessage verifica daca JTextField-urile clasei sunt goale
     * @param v interfata pentru care vom verifica
     * @return 1 daca cel putin un JTextField is empty, 0 in caz contrar
     */

    public int errorMessage(ViewProduct v) {
        if (v.getIdProduct().isEmpty() || v.getPriceProduct().isEmpty() || v.getNameProduct().isEmpty() || v.getQuantityProduct().isEmpty()) {
            return 1;
        }
        return 0;
    }

    /**
     * Functia viewButtonOrders adauga un ActionListener pentru butonul findAll
     * @param e este actiunea pe care dorim sa o execute butonul
     */

    void viewButtonProducts(ActionListener e) {
        findAll.addActionListener(e);
    }

    /**
     * Functia updateButtonOrder adauga un ActionListener pentru butonul update
     * @param e este actiunea pe care dorim sa o execute butonul
     */

    void updateButtonProduct(ActionListener e) {
        update.addActionListener(e);
    }

    /**
     * Functia insertButtonOrder adauga un ActionListener pentru butonul insert
     * @param e este actiunea pe care dorim sa o execute butonul
     */

    void insertButtonProduct(ActionListener e) {
        insert.addActionListener(e);
    }

    /**
     *Functia deleteButtonOrder adauga un ActionListener pentru butonul delete
     * @param e este actiunea pe care dorim sa o execute butonul
     */

    void deleteButtonProduct(ActionListener e) {
        delete.addActionListener(e);
    }
}
