package edu.loyola.cs485.model.dao;

import edu.loyola.cs485.model.entity.AbstractEntity;

import java.sql.*;
import java.util.List;

public abstract class AbstractDAO<E extends AbstractEntity> {
    //By using Generics, we can make sure each DAO is bound to a specific Entity

    protected String ConUrl = "jdbc:mysql://localhost"; //protocol + url
    protected String Port = "3306"; //default MySQL port
    protected String Database = "shift_db"; // database/schema name
    protected String Username = "jdsanchez"; //read this from a local file
    protected String Password = "password2005"; //Also read this from a file

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(ConUrl+":"+Port+"/"+Database+ "?user="+Username+"&password="+Password);
    }

    public void setTestDatabase(){
        this.Database = "shift_db";
    }

    // Abstract Methods for each CRUD operation
    public abstract void create(E entity) throws SQLException;
    public abstract E read(int ID) throws SQLException;
    public abstract void update(E entity) throws SQLException;
    public abstract void delete(int ID) throws SQLException;
    public abstract List<E> list() throws SQLException;
}
