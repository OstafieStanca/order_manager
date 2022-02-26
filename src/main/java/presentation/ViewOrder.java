package presentation;

import bll.ClientBLL;
import bll.ProductBLL;
import model.Client;
import model.Product;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: Ostafie Stanca
 * @Since: May 05, 2021
 */

public class ViewOrder extends JFrame {

    private final JButton findAll = new JButton("View Orders");
    private final JButton insert = new JButton("Add Order");
    private final JButton update = new JButton("Edit Order");
    private final JButton delete = new JButton("Remove Order");
    private final JTextField id = new JTextField();
    private final JComboBox<Integer> idC = new JComboBox<>();
    private final JComboBox<Integer> idP = new JComboBox<>();
    private final JTextField quantityNeeded = new JTextField();
    private final JLabel l1 = new JLabel("Order Id");
    private final JLabel l2 = new JLabel("Client Id");
    private final JLabel l3 = new JLabel("Product Id");
    private final JLabel l4 = new JLabel("Quantity");

    /**
     * Constructorul calsei ViewOrder
     */

    public ViewOrder() {

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

        ClientBLL clientBLL = new ClientBLL();
        ProductBLL productBLL = new ProductBLL();
        List<Client> clients = new ArrayList<>();
        clients = clientBLL.findAllClients();
        List<Product> products = new ArrayList<>();
        products = productBLL.findAllProducts();
        for (Client c : clients) {
            idC.addItem(c.getId());
        }
        for (Product p : products) {
            idP.addItem(p.getId());
        }

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
        panel.add(idC, constraints);
        constraints.gridx = 0;
        constraints.gridy = 2;
        panel.add(l3, constraints);
        constraints.gridx = 1;
        panel.add(idP, constraints);
        constraints.gridx = 0;
        constraints.gridy = 3;
        panel.add(l4, constraints);
        constraints.gridx = 1;
        panel.add(quantityNeeded, constraints);
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
        this.setTitle("ORDER MANAGEMENT");
        this.setSize(1300, 600);

    }

    /**
     * getter Id Order form JTextField id
     * @return id order
     */

    public String getId() {
        return id.getText();
    }

    /**
     * setter Id Order for JTextField id
     * @param idO noua valoare
     */

    public void setId(String idO) {
        this.id.setText(String.valueOf(idO));
    }

    /**
     * getter Id Client from JComboBox-ul idC
     * @return id Client
     */

    public String getIdClient() {
        return idC.getSelectedItem().toString();
    }

    /**
     * Adauga un nou idC la JComboBox-ul idC
     * @param idC noua valoare
     */

    public void setIdClient(String idC) {
        this.idC.addItem(Integer.parseInt(idC));
    }

    /**
     * getter Id Product from JComboBox-ul idP
     * @return id product
     */

    public String getIdProduct() {
        return idP.getSelectedItem().toString();
    }

    /**
     * Adauga un nou idP la JComboBox-ul idP
     * @param idP noua valoare
     */

    public void setIdProduct(String idP) {
        this.idP.addItem(Integer.parseInt(idP));
    }

    /**
     * getter Quantity Needed for a Order from JTextField quantityNeeded
     * @return quantity needed
     */

    public String getQuantityNeeded() {
        return quantityNeeded.getText();
    }

    /**
     * setter Quantity Needed for JTextField quantityNeeded
     * @param quantityN noua valoare
     */

    public void setQuantityNeeded(String quantityN) {
        this.quantityNeeded.setText(String.valueOf(quantityN));
    }

    /**
     * Elimina un idC din JComboBox-ul idC
     * @param idC valoarea pe care o vom elimina
     */

    public void removeIdC(int idC) {
        this.idC.removeItem(idC);
    }

    /**
     * Elimina un idP din JComboBox-ul idP
     * @param idP valoarea pe care o vom elimina
     */

    public void removeIdP(int idP) {
        this.idP.removeItem(idP);
    }

    /**
     * Functia errorMessage verifica daca JTextField-urile clasei sunt goale
     * @param v interfata pentru care vom verifica
     * @return 1 daca cel putin un JTextField is empty, 0 in caz contrar
     */

    public int errorMessage(ViewOrder v) {
        if (v.getIdClient().isEmpty() || v.getId().isEmpty() || v.getIdProduct().isEmpty() || v.getQuantityNeeded().isEmpty()) {

            return 1;
        }
        return 0;
    }

    /**
     * Functia viewButtonOrders adauga un ActionListener pentru butonul findAll
     * @param e este actiunea pe care dorim sa o execute butonul
     */

    void viewButtonOrders(ActionListener e) {
        findAll.addActionListener(e);
    }

    /**
     * Functia updateButtonOrder adauga un ActionListener pentru butonul update
     * @param e este actiunea pe care dorim sa o execute butonul
     */

    void updateButtonOrder(ActionListener e) {
        update.addActionListener(e);
    }

    /**
     * Functia insertButtonOrder adauga un ActionListener pentru butonul insert
     * @param e este actiunea pe care dorim sa o execute butonul
     */

    void insertButtonOrder(ActionListener e) {
        insert.addActionListener(e);
    }

    /**
     *Functia deleteButtonOrder adauga un ActionListener pentru butonul delete
     * @param e este actiunea pe care dorim sa o execute butonul
     */

    void deleteButtonOrder(ActionListener e) {
        delete.addActionListener(e);
    }

}