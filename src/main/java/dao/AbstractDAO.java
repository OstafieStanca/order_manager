package dao;

import connection.ConnectionFactory;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @Author: Ostafie Stanca
 * @Since: May 05, 2021
 * @Source http://www.java-blog.com/mapping-javaobjects-database-reflection-generics
 */
public class AbstractDAO<T> {
    protected static final Logger LOGGER = Logger.getLogger(AbstractDAO.class.getName());

    /**
     * Variabila type este generica si corespunde clasei cu care se instantiaza
     */

    private final Class<T> type;

    @SuppressWarnings("unchecked")
    public AbstractDAO() {
        this.type = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];

    }

    /**
     * Functia createFindAllQuery() va crea instrictiunea necesara operatiei de Select * From table
     * @return string-ul necesar
     */

    private String createFindAllQuery() {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT ");
        sb.append(" * ");
        sb.append(" FROM ");
        sb.append(type.getSimpleName());
        return sb.toString();
    }

    /**
     * Functia findAll executa instructiunea de Select * From table
     * @return returneaza o lista cu toate informatiile din tabel
     */

    public List<T> findAll() {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = createFindAllQuery();
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            resultSet = statement.executeQuery();
            try {
                return createObjects(resultSet);
            } catch (NullPointerException e) {

            }
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:findAll " + e.getMessage());
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return null;
    }

    /**
     * Functia createSelectQuery creaza instructiunea necesara operatiei de Select * From table Where field = ?
     * @param field campul in functie de care se va face selectia
     * @return string-ul necesar
     */

    private String createSelectQuery(String field) {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT ");
        sb.append(" * ");
        sb.append(" FROM ");
        sb.append(type.getSimpleName());
        sb.append(" WHERE " + field + " =?");
        return sb.toString();
    }

    /**
     * Functia findById va returna rezultatul instructiunii Select * From table Where field = ?
     * @param id valoarea pe care o va lua ?
     * @return rezultatul instructiunii
     */

    public T findById(int id) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = createSelectQuery("id");
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();

            List<T> lista = createObjects(resultSet);
            if (lista.isEmpty()) {
                return null;
            } else {
                return lista.get(0);
            }
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:findById " + e.getMessage());
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return null;
    }

    /**
     * Functia createInsertQuery creaza instructiunea necesara operatiei de Insert
     * @return string-ul necesar
     */

    public String createInsertQuery() {

        StringBuilder sb = new StringBuilder();
        sb.append("INSERT ");
        sb.append(" INTO ");
        sb.append(type.getSimpleName());
        sb.append(" VALUES(?,?,?,?)");
        return sb.toString();

    }

    /**
     * Functia inset va insera un obiect in tabelul corespunzator clasei din care face parte obictul
     * @param t obiectul pe care dorim sa il inseram in baza de date
     * @return numarul de linii afectate de catre instructiune(1)
     */

    public int insert(T t) {
        Connection connection = null;
        PreparedStatement statement = null;
        String query = createInsertQuery();
        int position = 1;
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            for (Field field : t.getClass().getDeclaredFields()) {
                field.setAccessible(true);
                Object value;
                try {
                    value = field.get(t);
                    statement.setObject(position++, value);
                } catch (IllegalArgumentException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
            try {
                return statement.executeUpdate();
            } catch (NullPointerException e) {
            }
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:insert " + e.getMessage());
        } finally {
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return -1;
    }

    /**
     * Functia createUpdateQuery creaza instructiunea pentru operatia de Update
     * @param field1
     * @param field2
     * @param field3
     * @return string-ul necesar
     */

    public String createUpdateQuery(String field1, String field2, String field3) {
        StringBuilder sb = new StringBuilder();
        sb.append("UPDATE ");
        sb.append(type.getSimpleName());
        sb.append("  SET  ");
        sb.append(field1 + " =? , ");
        sb.append(field2 + " =? , ");
        sb.append(field3 + " =?  ");
        sb.append("  WHERE id =? ");
        return sb.toString();
    }

    /**
     * Functia update va modifica linii din tabel in functie de campul id
     * @param t obiectul din tabel pe care dorim sa il modificam
     * @return numarul de linii afectate din tabel ( 1 deoarece campul id este cheie primara)
     */

    public int update(T t) {
        Connection connection = null;
        PreparedStatement statement = null;
        List<String> fields = new ArrayList<>();
        for (Field field : t.getClass().getDeclaredFields()) {
            fields.add(field.getName());
        }
        String query = createUpdateQuery(fields.get(1), fields.get(2), fields.get(3));
        int position = 1, index = 1;
        Object idValue = 0;
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            for (Field field : t.getClass().getDeclaredFields()) {
                field.setAccessible(true);
                Object value;
                value = field.get(t);
                if (index == 1) {
                    idValue = value;
                    index++;
                } else
                    statement.setObject(position++, value);
            }
            statement.setObject(position, idValue);
            try {
                return statement.executeUpdate();
            } catch (NullPointerException e) {
            }
        } catch (SQLException | IllegalAccessException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:update " + e.getMessage());
        } finally {
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return -1;
    }

    /**
     * Functia createDeleteQuery creaza instructiunea necesara operatiei de Delete
     * @return string-ul necesar
     */

    public String createDeleteQuery() {
        StringBuilder sb = new StringBuilder();
        sb.append("DELETE  FROM  ");
        sb.append(type.getSimpleName());
        sb.append("  WHERE  id=? ");
        return sb.toString();
    }

    /**
     * Functia delete va sterge linii din tabel in functie de valoarea campului id
     * @param t obiectul din tabel pe care dorim sa il stergem
     * @return numarul de linii afectate de instructiune ( 1 )
     */

    public int delete(T t) {

        Connection connection = null;
        PreparedStatement statement = null;
        Object idT = 0;
        String query = createDeleteQuery();
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            for (Field field : t.getClass().getDeclaredFields()) {
                field.setAccessible(true);
                Object value = field.get(t);
                idT = value;
                break;
            }
            statement.setObject(1, idT);
            return statement.executeUpdate();

        } catch (SQLException | IllegalAccessException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:delete " + e.getMessage());
        } finally {
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return -1;
    }

    /**
     * Functia createObject creeaza o lista cu obiectele primite ca parametru
     * @param resultSet lista din care extragem informatiile
     * @return lista cu noiile informatii
     */

    private List<T> createObjects(ResultSet resultSet) {
        List<T> list = new ArrayList<T>();
        Constructor[] ctors = type.getDeclaredConstructors();
        Constructor ctor = null;
        for (int i = 0; i < ctors.length; i++) {
            ctor = ctors[i];
            if (ctor.getGenericParameterTypes().length == 0)
                break;
        }
        try {
            while (resultSet.next()) {
                ctor.setAccessible(true);
                T instance = (T) ctor.newInstance();
                for (Field field : type.getDeclaredFields()) {
                    String fieldName = field.getName();
                    Object value = resultSet.getObject(fieldName);
                    PropertyDescriptor propertyDescriptor = new PropertyDescriptor(fieldName, type);
                    Method method = propertyDescriptor.getWriteMethod();
                    method.invoke(instance, value);
                }
                list.add(instance);
            }
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IntrospectionException e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * Functia createTable va crea un tabel (JTable) cu liniile rezultate in urma instructiunii Select * From table
     * @param objects obiectele pe care dorim sa le vedem in tabel
     * @return tabelul completat cu obiectele dorite
     * @throws IllegalArgumentException
     * @throws IllegalAccessException
     */

    public JTable createTable(List<T> objects) throws IllegalArgumentException, IllegalAccessException {
        ArrayList<String> columnHead = new ArrayList<>();
        for (Field field : objects.get(0).getClass().getDeclaredFields()) {
            field.setAccessible(true);
            columnHead.add(field.getName());
        }
        String[] columnNames = new String[columnHead.size()];
        columnNames = columnHead.toArray(columnNames);
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
        Iterator<T> i = objects.iterator();
        while (i.hasNext()) {
            T object = i.next();
            ArrayList<Object> columnDataAsArrayList = new ArrayList<Object>();
            for (Field field : object.getClass().getDeclaredFields()) {
                field.setAccessible(true);
                columnDataAsArrayList.add(field.get(object));
            }
            Object[] rowContent = new Object[columnDataAsArrayList.size()];
            rowContent = columnDataAsArrayList.toArray(rowContent);
            tableModel.addRow(rowContent);
        }
        JTable table = new JTable(tableModel);
        return table;
    }

}
