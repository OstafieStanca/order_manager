package bll;


import dao.ClientDAO;
import model.Client;
import presentation.View;
import presentation.ViewClient;

import javax.swing.*;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * @Author: Ostafie Stanca
 * @Since: May 05, 2021
 */

public class ClientBLL {

    private final ClientDAO clientDAO;

    /**
     * Constructorul clasei ClientBLL
     */

    public ClientBLL() {

        clientDAO = new ClientDAO();
    }

    /**
     * Functia findClientById gaseste un obiect de tip Client cu id-ul transmis ca parametru
     * @param id
     * @return client-ul gasit sau o exceptie de tipul NoSuchElementException daca aceasta nu exista in tabel
     */
    public Client findClientById(int id) {
        Client client = clientDAO.findById(id);
        if (client == null) {
            throw new NoSuchElementException("The client with id = " + id + " was not found!");
        }
        return client;
    }

    /**
     * Functia findAllClients genereaza o lista cu toti clientii din tabela Client
     * @return lista cu clientii sau exceptia NoSuchElementException daca lista este goala.
     */

    public List<Client> findAllClients() {
        List<Client> existingClients = clientDAO.findAll();
        if (existingClients.isEmpty()) {
            throw new NoSuchElementException("There are no clients to show");
        }
        return existingClients;
    }

    /**
     * Functia inserClient insereaza un nou client in tabela Client
     * @param client noul obiectul ce va fi inserat in tabel
     */

    public void insertClient(Client client, ViewClient viewClient) {

        List<Client> clients = clientDAO.findAll();
        for (Client c : clients) {
            if (c.getId() == client.getId()) {
                JOptionPane.showMessageDialog(viewClient,
                        "insert Duplicate entry " + client.getId() + " for key 'client.PRIMARY'",
                        "Inane error",
                        JOptionPane.ERROR_MESSAGE);
                viewClient.setIdClient("");
                throw new NoSuchElementException("insert Duplicate entry " + client.getId() + " for key 'client.PRIMARY'");
            }
        }
        int c = clientDAO.insert(client);
        if (c == 0) {
            throw new NoSuchElementException("The client" + client.toString() + " was not inserted");
        } else {
            JOptionPane.showMessageDialog(viewClient,
                    "The client with id " + client.getId() + " was inserted successfully",
                    "Operation succeeded",
                    JOptionPane.INFORMATION_MESSAGE);
            clearView(viewClient);
            System.out.println("The client = " + client.toString() + " was inserted");
        }
    }

    /**
     * Functia updateClient va modifica clientul transmis ca parametru
     * @param client obiectul ce urmeaza sa fie modificat
     */

    public void updateClient(Client client, ViewClient viewClient) {
        List<Client> clients = clientDAO.findAll();
        int ok = 0;
        for (Client c : clients) {
            if (c.getId() == client.getId()) {
                ok = 1;
                break;
            }
        }
        if (ok == 0) {
            JOptionPane.showMessageDialog(viewClient,
                    "The client with id " + client.getId() + " does not exist!",
                    "Inane error",
                    JOptionPane.ERROR_MESSAGE);
            clearView(viewClient);
            throw new NoSuchElementException("The client dose not exist");
        } else {
            int c = clientDAO.update(client);
            if (c == 0) {
                throw new NoSuchElementException("The client" + client.toString() + " was not updated");
            } else {
                JOptionPane.showMessageDialog(viewClient,
                        "The client with id " + client.getId() + " was edited successfully",
                        "Operation succeeded",
                        JOptionPane.INFORMATION_MESSAGE);
                clearView(viewClient);
                System.out.println("The client = " + client.toString() + " was updated");
            }
        }
    }

    /**
     * Functia deleteClient va sterge clientul transmis ca parametru
     * @param client obiectul ce urmeaza sa fie sters
     */

    public void deleteClient(Client client, ViewClient viewClient) {
        List<Client> clients = clientDAO.findAll();
        int ok = 0;
        for (Client c : clients) {
            if (c.getId() == client.getId())
                ok = 1;
        }
        if (ok == 0 || clients.isEmpty()) {
            JOptionPane.showMessageDialog(viewClient,
                    "The client with id " + client.getId() + " does not exist",
                    "Inane error",
                    JOptionPane.ERROR_MESSAGE);
            clearView(viewClient);
            throw new NoSuchElementException("There is no client to delete");
        } else {
            int c = clientDAO.delete(client);
            if (c == 0) {
                throw new NoSuchElementException("The client" + client.toString() + " was not deleted");
            } else {
                JOptionPane.showMessageDialog(viewClient,
                        "The client with id " + client.getId() + " was deleted successfully",
                        "Operation succeeded",
                        JOptionPane.INFORMATION_MESSAGE);
                clearView(viewClient);
                System.out.println("The client = " + client.toString() + " was deleted");
            }
        }
    }

    /**
     * Functia createTableClient creaza un tabel cu valorile existente in tabela Client
     * @return tabelul cu valorile existente
     * @throws IllegalAccessException
     */

    public void clearView(ViewClient viewClient){
        viewClient.setNameClient("");
        viewClient.setEmail("");
        viewClient.setAgeClient("");
        viewClient.setIdClient("");
    }


    public JTable createTableClient() throws IllegalAccessException {
        JTable table = clientDAO.createTable(clientDAO.findAll());
        return table;
    }
}
