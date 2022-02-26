package presentation;

import bll.ClientBLL;
import bll.OrderBLL;
import bll.ProductBLL;
import model.Client;
import model.Comanda;
import model.Product;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

/**
 * @Author: Ostafie Stanca
 * @Since: May 05, 2021
 */

public class Controller {

    private final View view;
    private final ViewProduct viewProduct;
    private final ViewClient viewClient;
    private final ViewOrder viewOrder;

    /**
     * Constructorul clasei Controller
     * @param view este interfata deschisa in main
     */

    public Controller(View view) {
        this.view = view;
        this.viewClient = new ViewClient();
        this.viewProduct = new ViewProduct();
        this.viewOrder = new ViewOrder();
        view.viewClientPage(new viewClientPage());
        view.viewOrderPage(new viewOrderPage());
        view.viewProductPage(new viewProductPage());
        viewClient.deleteButtonClient(new deleteButtonClient());
        viewClient.insertButtonClient(new insertButtonClient());
        viewClient.updateButtonClient(new updateButtonClient());
        viewClient.viewButtonClients(new viewButtonClient());
        viewProduct.deleteButtonProduct(new deleteButtonProduct());
        viewProduct.updateButtonProduct(new updateButtonProduct());
        viewProduct.insertButtonProduct(new insertButtonProduct());
        viewProduct.viewButtonProducts(new viewButtonProduct());
        viewOrder.deleteButtonOrder(new deleteButtonOrder());
        viewOrder.updateButtonOrder(new updateButtonOrder());
        viewOrder.insertButtonOrder(new insertButtonOrder());
        viewOrder.viewButtonOrders(new viewButtonOrder());
    }


    private class viewClientPage implements ActionListener {

        /**
         * Seteaza vizibilitatea interfetei Client la true
         * @param e
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            viewClient.setNameClient("");
            viewClient.setEmail("");
            viewClient.setAgeClient("");
            viewClient.setIdClient("");
            viewClient.setVisible(true);
        }
    }

    private class viewOrderPage implements ActionListener {

        /**
         * seteaza vizibilitatea interfetei Order la true
         * @param e evenimentul care are loc
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            viewOrder.setId("");
            viewOrder.setQuantityNeeded("");
            viewOrder.setVisible(true);
        }
    }

    private class viewProductPage implements ActionListener {

        /**
         * Seteaza vizibilitatea interfetei Product la true
         * @param e evenimentul care are loc
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            viewProduct.setNameProduct("");
            viewProduct.setIdProduct("");
            viewProduct.setPriceProduct("");
            viewProduct.setQuantityProduct("");
            viewProduct.setVisible(true);
        }
    }

    private class deleteButtonClient implements ActionListener {

        /**
         * Metoda care efectueaza operatia de stergere a unui Client
         * Se extrag informatiile necesare din JTextField-uri si se apeleaza functia de delete daca exista o valoare in campul id
         * @param e evenimentul care are loc
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            if (viewClient.getIdClient().isEmpty() == false) {
                int id = Integer.parseInt(viewClient.getIdClient());
                String name = viewClient.getNameClient();
                int age = Integer.parseInt("0");
                String email = viewClient.getEmail();
                Client client = new Client(id, name, age, email);
                ClientBLL clientBll = new ClientBLL();
                clientBll.deleteClient(client, viewClient);
                viewOrder.removeIdC(client.getId());
            } else {
                JOptionPane.showMessageDialog(viewClient,
                        "Id field can not be empty!!",
                        "Inane error",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private class insertButtonClient implements ActionListener {

        /**
         * Metoda de inserare a unui nou Client
         * Se extrag valorile din JTextField-uri si se insereaza clientul in tabela Client daca functia errorMessage returneaza 0
         * In caz contrar se va deschide un mesaj de eroare si inserarea nu se va face
         * @param e evenimentul care are loc
         */
        @Override
        public void actionPerformed(ActionEvent e) {

            if (viewClient.errorMessage(viewClient) == 0) {
                int id = Integer.parseInt(viewClient.getIdClient());
                String name = viewClient.getNameClient();
                int age = Integer.parseInt(viewClient.getAgeClient());
                String email = viewClient.getEmail();
                Client client = new Client(id, name, age, email);
                ClientBLL clientBll = new ClientBLL();
                clientBll.insertClient(client, viewClient);
                viewOrder.setIdClient(String.valueOf(id));
            } else if (viewClient.errorMessage(viewClient) == 1) {
                JOptionPane.showMessageDialog(viewClient,
                        "All fields need to contain data.",
                        "Inane error",
                        JOptionPane.ERROR_MESSAGE);
            }

        }
    }

    private class updateButtonClient implements ActionListener {

        /**
         * Metoda modifica un client din tabela Client
         * Se extrag valorile din JTextField-uri si face update doar daca functia errorMessage returneaza valoarea 0
         * In caz contrat se va deschide un mesaj de eroare si nu se va face update
         * @param e evenimentul care are loc
         */
        @Override
        public void actionPerformed(ActionEvent e) {

            if (viewClient.errorMessage(viewClient) == 0) {
                int id = Integer.parseInt(viewClient.getIdClient());
                String name = viewClient.getNameClient();
                int age = Integer.parseInt(viewClient.getAgeClient());
                String email = viewClient.getEmail();
                Client client = new Client(id, name, age, email);
                ClientBLL clientBll = new ClientBLL();
                clientBll.updateClient(client, viewClient);
            } else {
                JOptionPane.showMessageDialog(viewClient,
                        "All fields need to contain data",
                        "Inane error",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private class viewButtonClient implements ActionListener {

        /**
         * Metoda va deschide un nou JFrame in care va face display la datele din tabela Client
         * Se apeleaza functia de createTableClient si se afiseaza valorile in interfata
         * @param e evenimentul care are loc
         */
        @Override
        public void actionPerformed(ActionEvent e) {

            ClientBLL clientBLL = new ClientBLL();
            JTable table = new JTable();
            try {
                table = clientBLL.createTableClient();
            } catch (IllegalAccessException illegalAccessException) {
                illegalAccessException.printStackTrace();
            }
            JFrame frame = new JFrame();
            JScrollPane scrollPane = new JScrollPane(table);
            frame.add(scrollPane, BorderLayout.CENTER);
            frame.setSize(600, 350);
            frame.setVisible(true);
        }
    }

    private class deleteButtonProduct implements ActionListener {

        /**
         * Metoda care efectueaza operatia de stergere a unui Produs
         * Se extrag informatiile necesare din JTextField-uri si se apeleaza functia de delete daca exista o valoare in campul id
         * @param e evenimentul care are loc
         */
        @Override
        public void actionPerformed(ActionEvent e) {

            if (viewProduct.getIdProduct().isEmpty() == false) {
                int id = Integer.parseInt(viewProduct.getIdProduct());
                String name = viewProduct.getNameProduct();
                int price = Integer.parseInt("0");
                int quantity = Integer.parseInt("0");
                Product product = new Product(id, name, quantity, price);
                ProductBLL productBll = new ProductBLL();
                productBll.deleteProduct(product, viewProduct);
                viewOrder.removeIdP(product.getId());
            } else {

                JOptionPane.showMessageDialog(viewClient,
                        "Id field can not be empty!!",
                        "Inane error",
                        JOptionPane.ERROR_MESSAGE);

            }

        }
    }

    private class updateButtonProduct implements ActionListener {

        /**
         * Metoda modifica un produs din tabela Product
         * Se extrag valorile din JTextField-uri si face update doar daca functia errorMessage returneaza valoarea 0
         * In caz contrat se va deschide un mesaj de eroare si nu se va face update
         * @param e evenimentul care are loc
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            if (viewProduct.errorMessage(viewProduct) == 0) {
                int id = Integer.parseInt(viewProduct.getIdProduct());
                String name = viewProduct.getNameProduct();
                int price = Integer.parseInt(viewProduct.getPriceProduct());
                int quantity = Integer.parseInt(viewProduct.getQuantityProduct());
                Product product = new Product(id, name, quantity, price);
                ProductBLL productBll = new ProductBLL();
                productBll.updateProduct(product, viewProduct);
            } else {

                JOptionPane.showMessageDialog(viewClient,
                        "All fields need to contain data",
                        "Inane error",
                        JOptionPane.ERROR_MESSAGE);

            }
        }
    }

    private class insertButtonProduct implements ActionListener {

        /**
         * Metoda de inserare a unui nou Produs
         * Se extrag valorile din JTextField-uri si se insereaza produsul in tabela Product daca functia errorMessage returneaza 0
         * In caz contrar se va deschide un mesaj de eroare si inserarea nu se va face
         * @param e evenimentul care are loc
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            if (viewProduct.errorMessage(viewProduct) == 0) {
                int id = Integer.parseInt(viewProduct.getIdProduct());
                String name = viewProduct.getNameProduct();
                int price = Integer.parseInt(viewProduct.getPriceProduct());
                int quantity = Integer.parseInt(viewProduct.getQuantityProduct());
                Product product = new Product(id, name, quantity, price);
                ProductBLL productBll = new ProductBLL();
                productBll.insertProduct(product, viewProduct);
                viewOrder.setIdProduct(String.valueOf(product.getId()));
            } else {

                JOptionPane.showMessageDialog(viewClient,
                        "All fields need to contain data",
                        "Inane error",
                        JOptionPane.ERROR_MESSAGE);

            }

        }
    }

    private class viewButtonProduct implements ActionListener {

        /**
         * Metoda va deschide un nou JFrame in care va face display la datele din tabela Product
         * Se apeleaza functia de createTableProduct si se afiseaza valorile in interfata
         * @param e evenimentul care are loc
         */
        @Override
        public void actionPerformed(ActionEvent e) {

            ProductBLL productBLL = new ProductBLL();
            JTable table = new JTable();
            try {
                table = productBLL.createTableProduct();
            } catch (IllegalAccessException illegalAccessException) {
                illegalAccessException.printStackTrace();
            }
            JFrame frame = new JFrame();
            JScrollPane scrollPane = new JScrollPane(table);
            frame.add(scrollPane, BorderLayout.CENTER);
            frame.setSize(600, 350);
            frame.setVisible(true);

        }
    }

    private class deleteButtonOrder implements ActionListener {

        /**
         * Metoda care efectueaza operatia de stergere a unei comenzi
         * Se extrag informatiile necesare din JTextField-uri si se apeleaza functia de delete daca exista o valoare in campul id
         * @param e evenimentul care are loc
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            if (viewOrder.getId().isEmpty() == false) {
                int id = Integer.parseInt(viewOrder.getId());
                int idClient = Integer.parseInt(viewOrder.getIdClient());
                int idProduct = Integer.parseInt(viewOrder.getIdProduct());
                int quantityN = Integer.parseInt("0");
                Comanda comanda = new Comanda(id, idProduct, idClient, quantityN);
                OrderBLL orderBLL = new OrderBLL();
                orderBLL.deleteOrder(comanda, viewOrder);
            } else {
                JOptionPane.showMessageDialog(viewClient,
                        "Id field can not be empty!!",
                        "Inane error",
                        JOptionPane.ERROR_MESSAGE);
            }

        }
    }

    private class updateButtonOrder implements ActionListener {

        /**
         * Metoda modifica o comanda din tabela Comanda
         * Se extrag valorile din JTextField-uri si face update doar daca functia errorMessage returneaza valoarea 0
         * In caz contrat se va deschide un mesaj de eroare si nu se va face update
         * @param e evenimentul care are loc
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            if (viewOrder.errorMessage(viewOrder) == 0) {
                int id = Integer.parseInt(viewOrder.getId());
                int idClient = Integer.parseInt(viewOrder.getIdClient());
                int idProduct = Integer.parseInt(viewOrder.getIdProduct());
                int quantityN = Integer.parseInt(viewOrder.getQuantityNeeded());
                Comanda comanda = new Comanda(id, idProduct, idClient, quantityN);
                OrderBLL orderBLL = new OrderBLL();
                orderBLL.updateOrder(comanda, viewOrder);
            } else {
                JOptionPane.showMessageDialog(viewClient,
                        "All fields need to contain data",
                        "Inane error",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private class insertButtonOrder implements ActionListener {

        /**
         * Metoda de inserare a unei noi Comenzi
         * Se extrag valorile din JTextField-uri si se insereaza comanda in tabela Comanda daca functia errorMessage returneaza 0
         * In caz contrar se va deschide un mesaj de eroare si inserarea nu se va face
         * @param e evenimentul care are loc
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            if (viewOrder.errorMessage(viewOrder) == 0) {
                int id = Integer.parseInt(viewOrder.getId());
                int idClient = Integer.parseInt(viewOrder.getIdClient());
                int idProduct = Integer.parseInt(viewOrder.getIdProduct());
                int quantityN = Integer.parseInt(viewOrder.getQuantityNeeded());
                Comanda comanda = new Comanda(id, idProduct, idClient, quantityN);
                OrderBLL orderBLL = new OrderBLL();
                try {
                    orderBLL.insertOrder(comanda, viewOrder);
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            } else {
                JOptionPane.showMessageDialog(viewClient,
                        "All fields need to contain data",
                        "Inane error",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private class viewButtonOrder implements ActionListener {
        /**
         * Metoda va deschide un nou JFrame in care va face display la datele din tabela Comanda
         * Se apeleaza functia de createTableComanda si se afiseaza valorile in interfata
         * @param e evenimentul care are loc
         */
        @Override
        public void actionPerformed(ActionEvent e) {

            OrderBLL orderBLL = new OrderBLL();
            JTable table = new JTable();
            try {
                table = orderBLL.createTableOrder();
            } catch (IllegalAccessException illegalAccessException) {
                illegalAccessException.printStackTrace();
            }
            JFrame frame = new JFrame();
            JScrollPane scrollPane = new JScrollPane(table);
            frame.add(scrollPane, BorderLayout.CENTER);
            frame.setSize(600, 350);
            frame.setVisible(true);

        }
    }
}
