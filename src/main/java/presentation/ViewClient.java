package presentation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * @Author: Ostafie Stanca
 * @Since: May 05, 2021
 */

public class ViewClient extends JFrame {

    private final JButton findAllClients = new JButton("View Clients");
    private final JButton insertClient = new JButton("Add Client");
    private final JButton updateClient = new JButton("Edit Client");
    private final JButton deleteClient = new JButton("Remove Client");
    private final JTextField idClient = new JTextField();
    private final JTextField nameClient = new JTextField();
    private final JTextField ageClient = new JTextField();
    private final JTextField email = new JTextField();
    private final JLabel l1 = new JLabel("Client Id");
    private final JLabel l2 = new JLabel("Client Name");
    private final JLabel l3 = new JLabel("Client Age");
    private final JLabel l4 = new JLabel("Client Email");

    /**
     * Constructorul clasei ViewClient
     */

    public ViewClient() {

        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();

        l1.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        l2.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        l3.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        l4.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        findAllClients.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        insertClient.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        updateClient.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        deleteClient.setFont(new Font("Times New Roman", Font.PLAIN, 15));

        constraints.anchor = GridBagConstraints.LINE_START;
        constraints.insets = new Insets(10, 10, 0, 0);
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.ipadx = 150;
        constraints.ipady = 15;
        panel.add(l1, constraints);
        constraints.gridx = 1;
        panel.add(idClient, constraints);
        constraints.gridx = 0;
        constraints.gridy = 1;
        panel.add(l2, constraints);
        constraints.gridx = 1;
        panel.add(nameClient, constraints);
        constraints.gridx = 0;
        constraints.gridy = 2;
        panel.add(l3, constraints);
        constraints.gridx = 1;
        panel.add(ageClient, constraints);
        constraints.gridx = 0;
        constraints.gridy = 3;
        panel.add(l4, constraints);
        constraints.gridx = 1;
        panel.add(email, constraints);
        constraints.gridx = 0;
        constraints.gridy = 4;
        panel.add(findAllClients, constraints);
        constraints.gridx = 1;
        panel.add(insertClient, constraints);
        constraints.gridx = 2;
        panel.add(updateClient, constraints);
        constraints.gridx = 3;
        panel.add(deleteClient, constraints);
        this.setContentPane(panel);
        this.pack();
        this.setTitle("CLIENT MANAGEMENT");
        this.setSize(1300, 600);
    }

    /**
     * getter pentru a obtine valoarea din JTextField-ul idClient
     * @return un String cu valoarea gasita in campul idClient
     */

    public String getIdClient() {
        return idClient.getText();
    }

    /**
     * setter pentru a reseta valoarea din campul idClient
     * @param idClient noua valoare
     */

    public void setIdClient(String idClient) {
        this.idClient.setText(idClient);
    }

    /**
     * getter Nume Client din JTextField-ul nameClient
     * @return id client
     */

    public String getNameClient() {
        return nameClient.getText();
    }

    /**
     * setter Nume Client
     * @param nameClient noua valoare
     */

    public void setNameClient(String nameClient) {
        this.nameClient.setText(nameClient);
    }

    /**
     * getter Age Client from JTextField ageClient
     * @return age client
     */

    public String getAgeClient() {
        return ageClient.getText();
    }

    /**
     * setter AgeClient for JTextField ageClient
     * @param ageClient noua valoare
     */

    public void setAgeClient(String ageClient) {
        this.ageClient.setText(String.valueOf(ageClient));
    }

    /**
     * getter Email Client from JTextField email
     * @return email client
     */

    public String getEmail() {
        return email.getText();
    }

    /**
     * setter Email Client for JTextField email
     * @param email noua valoare
     */

    public void setEmail(String email) {
        this.email.setText(email);
    }

    /**
     * Functia errorMessage verifica daca JTextField-urile declarate nu sunt setate
     * @param v interfata pentru care se verifica
     * @return 1 daca cel putin un JTextField este gol, 0 in caz contrar
     */

    public int errorMessage(ViewClient v) {
        if (v.getIdClient().isEmpty() || v.getAgeClient().isEmpty() || v.getNameClient().isEmpty() || v.getEmail().isEmpty()) {
            return 1;
        }
        return 0;
    }

    /**
     * Functia viewButtonClients adauga un ActionListener pentru butonul findAllClients
     * @param e este actiunea pe care dorim sa o execute butonul
     */

    void viewButtonClients(ActionListener e) {
        findAllClients.addActionListener(e);
    }

    /**
     * Functia updateButtonClient adauga un ActionListener la butonul updateClient
     * @param e este actiunea pe care dorim sa o execute butonul
     */

    void updateButtonClient(ActionListener e) {
        updateClient.addActionListener(e);
    }

    /**
     * Functia insertButtonClients adauga un ActionListener pentru butonul insertClient
     * @param e este actiunea pe care dorim sa o execute butonul
     */

    void insertButtonClient(ActionListener e) {
        insertClient.addActionListener(e);
    }

    /**
     * Functia deleteButtonClients adauga un ActionListener pentru butonul deleteClient
     * @param e este actiunea pe care dorim sa o execute butonul
     */

    void deleteButtonClient(ActionListener e) {
        deleteClient.addActionListener(e);
    }
}
