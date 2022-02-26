package bll;

import dao.ClientDAO;
import dao.OrderDAO;
import dao.ProductDAO;
import model.Client;
import model.Comanda;
import model.Product;
import presentation.ViewOrder;

import javax.swing.*;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * @Author: Ostafie Stanca
 * @Since: May 05, 2021
 */

public class OrderBLL {

    private final OrderDAO orderDAO;

    /**
     * Constructorul clasei OrderBLL
     */

    public OrderBLL() {
        orderDAO = new OrderDAO();
    }

    /**
     * Functia findById gaseste o comanda cu id-ul dat
     *
     * @param id este id-ul comenzii pe care dorim sa o gasim
     * @return comanda gasita sau exceptia NoSuchElementException daca id-ul comenzii nu exista
     */
    public Comanda findOrderById(int id) {
        Comanda comanda = orderDAO.findById(id);
        if (comanda == null) {
            throw new NoSuchElementException("The order with id = " + id + " was not found!");
        }
        return comanda;
    }

    /**
     * Functia findAllOrders gaseste toate elementele existente in tabela Order
     *
     * @return lista cu valorile gasite sau exceptia NoSuchElementException daca nu exista valori in tabela
     */

    public List<Comanda> findAllOrders() {
        List<Comanda> existingOrders = orderDAO.findAll();
        if (existingOrders.isEmpty()) {
            throw new NoSuchElementException("There are no orders to show");
        }
        return existingOrders;
    }

    /**
     * Functia createBill genereaza chitanta unei comenzi plasate cu succes
     *
     * @param f       fisierul in care se scrie
     * @param comanda pentru care dorim sa cream chitanta
     * @throws IOException
     */

    public void createBill(FileWriter f, Comanda comanda) throws IOException {
        ProductDAO productDAO = new ProductDAO();
        ClientDAO clientDAO = new ClientDAO();
        f.write("Bill:\n");
        Client client = clientDAO.findById(comanda.getIdClient());
        Product product = productDAO.findById(comanda.getIdProduct());
        f.write("Client " + client.getName() + " has placed successfully the order with id " + comanda.getId() + "\n");
        f.write("Product " + product.getName() + " has the price = " + product.getPrice() + "\n");
        f.write("Order information : " + product.getName() + "  *  " + comanda.getQuantityNeeded() + "\n");
        f.write("Total price = " + product.getPrice() * comanda.getQuantityNeeded());

    }

    /**
     * Functia insertOrder adauga o noua comanda la tabela Comanda si genereaza chitanta
     *
     * @param comanda comanda pe care dorim sa o plasam
     * @throws IOException
     */

    public void insertOrder(Comanda comanda, ViewOrder viewOrder) throws IOException {

        FileWriter f = new FileWriter("D:\\An II sem 2\\TP\\PT2021_30227_Ostafie_Stanca_Assignment_3\\bill.txt");
        List<Comanda> orders = orderDAO.findAll();
        for (Comanda c : orders) {
            if (c.getId() == comanda.getId()) {
                JOptionPane.showMessageDialog(viewOrder,
                        "insert Duplicate entry " + comanda.getId() + " for key 'comanda.PRIMARY'",
                        "Inane error",
                        JOptionPane.ERROR_MESSAGE);
                viewOrder.setId("");
                throw new NoSuchElementException("insert Duplicate entry " + comanda.getId() + " for key 'comanda.PRIMARY'");
            }
        }

        Product product, newProduct;
        ProductDAO productDAO = new ProductDAO();
        if (productDAO.findById(comanda.getIdProduct()) == null) {
            throw new NoSuchElementException("There is no product with the given id");
        } else product = productDAO.findById(comanda.getIdProduct());
        if (comanda.getQuantityNeeded() > product.getQuantity()) {
            f.close();
            JOptionPane.showMessageDialog(viewOrder,
                    "Insufficient quantity!!Quantity available  " + product.getQuantity() + "!",
                    "Inane error",
                    JOptionPane.ERROR_MESSAGE);
            viewOrder.setQuantityNeeded("");
            throw new NoSuchElementException("Insufficient quantity");
        } else {
            newProduct = new Product(product.getId(), product.getName(), (product.getQuantity() - comanda.getQuantityNeeded()), product.getPrice());
            createBill(f, comanda);
            int c = orderDAO.insert(comanda);
            if (c == 0) {
                throw new NoSuchElementException("The order" + comanda.toString() + " was not inserted");
            } else {
                productDAO.update(newProduct);
                JOptionPane.showMessageDialog(viewOrder,
                        "The order with id " + comanda.getId() + " was placed successfully",
                        "Operation succeeded",
                        JOptionPane.INFORMATION_MESSAGE);
                clearView(viewOrder);
            }
        }
        f.close();

    }

    /**
     * Functia updateOrder modifica o comanda
     *
     * @param comanda obictul pe care dorim sa il obtinem in tabela
     */

    public void updateOrder(Comanda comanda, ViewOrder viewOrder) {
        List<Comanda> orders = orderDAO.findAll();
        int ok = 0;
        for (Comanda c : orders) {
            if (c.getId() == comanda.getId()) {
                ok = 1;
                break;
            }
        }
        if (ok == 0) {
            JOptionPane.showMessageDialog(viewOrder,
                    "The order with id " + comanda.getId() + " does not exist!",
                    "Inane error",
                    JOptionPane.ERROR_MESSAGE);
            clearView(viewOrder);
            throw new NoSuchElementException("The orders dose not exist");
        } else {
            int c = orderDAO.update(comanda);
            if (c == 0) {
                throw new NoSuchElementException("The order" + comanda.toString() + " was not updated");
            } else {
                JOptionPane.showMessageDialog(viewOrder,
                        "The order with id " + comanda.getId() + " was edited successfully",
                        "Operation succeeded",
                        JOptionPane.INFORMATION_MESSAGE);
                clearView(viewOrder);
                System.out.println("The order = " + comanda.toString() + " was updated");
            }
        }
    }

    /**
     * Functia deleteOrder va sterge o comanda din tabela Comanda
     *
     * @param comanda obiectul pe care dorim sa il stergem
     */

    public void deleteOrder(Comanda comanda, ViewOrder viewOrder) {
        List<Comanda> orders = orderDAO.findAll();
        int ok = 0;
        for (Comanda c : orders) {
            if (c.getId() == comanda.getId())
                ok = 1;
        }
        if (ok == 0 || orders.isEmpty()) {
            JOptionPane.showMessageDialog(viewOrder,
                    "The order with id " + comanda.getId() + " does not exist!",
                    "Inane error",
                    JOptionPane.ERROR_MESSAGE);
            clearView(viewOrder);
            throw new NoSuchElementException("There is no order to delete");
        } else {
            int c = orderDAO.delete(comanda);
            if (c == 0) {
                throw new NoSuchElementException("The order" + comanda.toString() + " was not deleted");
            } else {
                JOptionPane.showMessageDialog(viewOrder,
                        "The order with id " + comanda.getId() + " was deleted successfully",
                        "Operation succeeded",
                        JOptionPane.INFORMATION_MESSAGE);
                clearView(viewOrder);
                System.out.println("The order = " + comanda.toString() + " was deleted");
            }
        }

    }

    /**
     * Functia createTableOrder genereaza un tabel cu toate datele din tabela Comanda
     *
     * @return tabelul cu rezultatele gasite
     * @throws IllegalAccessException
     */

    public void clearView(ViewOrder viewOrder){
        viewOrder.setId("");
        viewOrder.setQuantityNeeded("");
    }


    public JTable createTableOrder() throws IllegalAccessException {
        return orderDAO.createTable(orderDAO.findAll());
    }


}
