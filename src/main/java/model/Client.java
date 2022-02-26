package model;

/**
 * @Author: Ostafie Stanca
 * @Since: May 05, 2021
 */

public class Client {
    private int id;
    private String name;
    private int age;
    private String email;

    /**
     * constructorul clasei Client
     * @param id
     * @param name
     * @param age
     * @param email
     */

    public Client(int id, String name, int age, String email) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.email = email;
    }

    /**
     * Constructor gol, pentru a instantia un obiect de aceasta clasa
     */
    public Client() {
    }

    /**
     * getter pentru id-ul unui client
     * @return id-ul clientului
     */

    public int getId() {
        return id;
    }

    /**
     * setter pentru id-ul unui client
     * @param id noua valoare
     */

    public void setId(int id) {
        this.id = id;
    }

    /**
     * getter Nume client
     * @return nume client
     */

    public String getName() {
        return name;
    }

    /**
     * setter Nume client
     * @param name noua valoare
     */

    public void setName(String name) {
        this.name = name;
    }

    /**
     * getter Age client
     * @return age client
     */

    public int getAge() {
        return age;
    }

    /**
     * setter Age client
     * @param age noua valoare
     */

    public void setAge(int age) {
        this.age = age;
    }

    /**
     * getter Email client
     * @return eamil client
     */

    public String getEmail() {
        return email;
    }

    /**
     * setter Email client
     * @param email noua valoare
     */

    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Metoda toString pentru afisarea unui client
     * @return string
     */

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", email='" + email + '\'' +
                '}';
    }


}
