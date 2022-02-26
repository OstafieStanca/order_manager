package model;

/**
 * @Author: Ostafie Stanca
 * @Since: May 05, 2021
 */

public class Comanda {

    private int id;
    private int idProduct;
    private int idClient;
    private int quantityNeeded;

    /**
     * Constructorul clasei Comanda
     * @param id
     * @param idProduct
     * @param idClient
     * @param quantityNeeded
     */

    public Comanda(int id, int idProduct, int idClient, int quantityNeeded) {
        this.id = id;
        this.idProduct = idProduct;
        this.idClient = idClient;
        this.quantityNeeded = quantityNeeded;
    }

    public Comanda(){}

    /**
     * getter Id Comanda
     * @return
     */

    public int getId() {
        return id;
    }

    /**
     * setter Id Comanda
     * @param id
     */

    public void setId(int id) {
        this.id = id;
    }

    /**
     * getter Id Product
     * @return
     */

    public int getIdProduct() {
        return idProduct;
    }

    /**
     * setter Id Product
     * @param idProduct
     */

    public void setIdProduct(int idProduct) {
        this.idProduct = idProduct;
    }

    /**
     * getter Id Client
     * @return
     */

    public int getIdClient() {
        return idClient;
    }

    /**
     * setter Id Client
     * @param idClient
     */

    public void setIdClient(int idClient) {
        this.idClient = idClient;
    }

    /**
     * getter QuantityNeeded
     * @return
     */

    public int getQuantityNeeded() {
        return quantityNeeded;
    }

    /**
     * setter Quantity Needed
     * @param quantityNeeded
     */

    public void setQuantityNeeded(int quantityNeeded) {
        this.quantityNeeded = quantityNeeded;
    }

    /**
     * Metoda toString pentru afisarea unei Comenzi
     * @return
     */

    @Override
    public String toString() {
        return "Comanda{" +
                "id=" + id +
                ", idProduct=" + idProduct +
                ", idClient=" + idClient +
                ", quantityNeedes=" + quantityNeeded +
                '}';
    }
}
