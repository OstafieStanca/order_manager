package presentation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * @Author: Ostafie Stanca
 * @Since: May 05, 2021
 */

public class View extends JFrame {

    private final JButton productButton = new JButton("Product");
    private final JButton clientButton = new JButton("Client");
    private final JButton orderButton = new JButton("Order");

    /**
     * Constructorul clasei View
     */

    public View() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        clientButton.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        productButton.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        orderButton.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.CENTER;
        constraints.insets = new Insets(20, 0, 0, 0);
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.ipady = 30;
        constraints.ipadx = 150;
        constraints.anchor = GridBagConstraints.CENTER;
        panel.add(clientButton, constraints);
        constraints.gridx = 0;
        constraints.gridy = 1;
        panel.add(productButton, constraints);
        constraints.gridx = 0;
        constraints.gridy = 2;
        panel.add(orderButton, constraints);
        panel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createEtchedBorder(), "Main View"));
        Color cl = new Color(246, 204, 255);
        panel.setBackground(cl);
        this.setContentPane(panel);
        this.pack();
        this.setTitle("ORDER MANAGEMENT");
        this.setSize(500, 400);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    /**
     * Functia viewClientPage adauga un ActionListener la butonul clientButton
     * @param e
     */

    void viewClientPage(ActionListener e) {
        clientButton.addActionListener(e);
    }

    /**
     * Functia viewProductPage adauga un ActionListener la butonul productButton
     * @param e
     */

    void viewProductPage(ActionListener e) {
        productButton.addActionListener(e);
    }

    /**
     * Functia viewOrderPage adauga un ActionListener la butonul orderButton
     * @param e
     */

    void viewOrderPage(ActionListener e) {
        orderButton.addActionListener(e);
    }

}
