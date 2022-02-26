package bll;

import dao.ProductDAO;
import model.Product;
import presentation.View;
import presentation.ViewProduct;

import javax.swing.*;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * @Author: Ostafie Stanca
 * @Since: May 05, 2021
 */

public class ProductBLL {

    private final ProductDAO productDAO;

    /**
     * Constructorul clasei ProductBLL
     */

    public ProductBLL() {
        this.productDAO = new ProductDAO();
    }

    /**
     * Functia findProductById gaseste un produs din tabela Product in functie de campul id
     * @param id este id-ul produsului pe care dorim sa il gasim
     * @return
     */

    public Product findProductById(int id) {
        Product product = productDAO.findById(id);
        if (product == null) {
            throw new NoSuchElementException("The product with id = " + id + " was not found!");
        }
        return product;
    }

    /**
     * Functia findAllProducts gaseste toate liniile din tabela Product
     * @return lista cu produsele gasite
     */

    public List<Product> findAllProducts() {
        List<Product> existingProducts = productDAO.findAll();
        if (existingProducts.isEmpty()) {
            throw new NoSuchElementException("There are no products to show");
        }
        return existingProducts;
    }

    /**
     * Functia insertProduct va adauga un nou produs la tabela Product
     * @param product este produsul pe care dorim sa il adaugam in tabel
     */

    public void insertProduct(Product product, ViewProduct viewProduct) {

        List<Product> products = productDAO.findAll();
        for (Product c : products) {
            if (c.getId() == product.getId()) {
                JOptionPane.showMessageDialog(viewProduct,
                        "insert Duplicate entry " + product.getId() + " for key 'product.PRIMARY'",
                        "Inane error",
                        JOptionPane.ERROR_MESSAGE);
                viewProduct.setIdProduct("");
                throw new NoSuchElementException("insert Duplicate entry " + product.getId() + " for key 'product.PRIMARY'");
            }
        }
        int c = productDAO.insert(product);
        if (c == 0) {
            throw new NoSuchElementException("The product" + product.toString() + " was not inserted");
        } else {
            JOptionPane.showMessageDialog(viewProduct,
                    "The product with id " + product.getId() + " was inserted successfully",
                    "Operation succeeded",
                    JOptionPane.INFORMATION_MESSAGE);
            clearView(viewProduct);
            System.out.println("The product = " + product.toString() + " was inserted");
        }
    }

    /**
     * Functia updateProduct va modifica campurile unui produs existent in tabela Product
     * @param product este obiectul pe care dorim sa il obtinem in tabel
     */

    public void updateProduct(Product product, ViewProduct viewProduct) {
        List<Product> products = productDAO.findAll();
        int ok = 0;
        for (Product c : products) {
            if (c.getId() == product.getId()) {
                ok = 1;
                break;
            }
        }
        if (ok == 0) {
            JOptionPane.showMessageDialog(viewProduct,
                    "The product with id " + product.getId() + " does not exist.",
                    "Inane error",
                    JOptionPane.ERROR_MESSAGE);
            clearView(viewProduct);
            throw new NoSuchElementException("The product dose not exist");
        } else {
            int c = productDAO.update(product);
            if (c == 0) {
                throw new NoSuchElementException("The product" + product.toString() + " was not updated");
            } else {
                JOptionPane.showMessageDialog(viewProduct,
                        "The product with id " + product.getId() + " was edited successfully",
                        "Operation succeeded",
                        JOptionPane.INFORMATION_MESSAGE);
                clearView(viewProduct);
                System.out.println("The product = " + product.toString() + " was updated");
            }
        }
    }

    /**
     * Functia deleteProduct va sterge un produs din tabela
     * @param product este produsul pe care dorim sa-l stergem
     */

    public void deleteProduct(Product product, ViewProduct viewProduct) {
        List<Product> products = productDAO.findAll();
        int ok = 0;
        for (Product c : products) {
            if (c.getId() == product.getId())
                ok = 1;
        }
        if (ok == 0 || products.isEmpty()) {
            JOptionPane.showMessageDialog(viewProduct,
                    "The product with id " + product.getId() + " does not exist",
                    "Inane error",
                    JOptionPane.ERROR_MESSAGE);
            clearView(viewProduct);
            throw new NoSuchElementException("There is no product to delete");
        } else {
            int c = productDAO.delete(product);
            if (c == 0) {
                throw new NoSuchElementException("The product" + product.toString() + " was not deleted");
            } else {
                JOptionPane.showMessageDialog(viewProduct,
                        "The product with id " + product.getId() + " was deleted successfully",
                        "Operation succeeded",
                        JOptionPane.INFORMATION_MESSAGE);
                clearView(viewProduct);
                System.out.println("The product = " + product.toString() + " was deleted");
            }
        }
    }

    /**
     * Functia createTableProduct creaza un tabel cu toate liniile din tabela Product
     * @return tabelul completat
     * @throws IllegalAccessException
     */

    public void clearView(ViewProduct viewProduct){
        viewProduct.setNameProduct("");
        viewProduct.setIdProduct("");
        viewProduct.setPriceProduct("");
        viewProduct.setQuantityProduct("");
    }

    public JTable createTableProduct() throws IllegalAccessException {
        return productDAO.createTable(productDAO.findAll());
    }


}
