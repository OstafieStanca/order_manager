package model;

/**
 * @Author: Ostafie Stanca
 * @Since: May 05, 2021
 */

public class Product {
    private int id;
    private String name;
    private int quantity;
    private int price;

    /**
     * Constructorul clasei Product
     * @param id
     * @param name
     * @param quantity
     * @param price
     */

    public Product(int id, String name, int quantity, int price) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.price = price;
    }

    /**
     * Constrcutor gol pentru clasa Product
     */

    public Product() {
    }

    /**
     * getter Id Product
     * @return id product
     */

    public int getId() {
        return id;
    }

    /**
     * setter Id product
     * @param id noua valoare
     */

    public void setId(int id) {
        this.id = id;
    }

    /**
     * getter Name Product
     * @return name product
     */

    public String getName() {
        return name;
    }

    /**
     * setter Name product
     * @param name noua valoare
     */

    public void setName(String name) {
        this.name = name;
    }

    /**
     * getter Quantity Product
     * @return quantity product
     */

    public int getQuantity() {
        return quantity;
    }

    /**
     * setter Quantity Product
     * @param quantity noua valoare
     */

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /**
     * getter Price Product
     * @return price product
     */

    public int getPrice() {
        return price;
    }

    /**
     * setter Price Product
     * @param price noua valoare
     */

    public void setPrice(int price) {
        this.price = price;
    }

    /**
     * Metoda toString pentru afisarea unui produs
     * @return string
     */

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", quantity=" + quantity +
                ", price=" + price +
                '}';
    }
}
