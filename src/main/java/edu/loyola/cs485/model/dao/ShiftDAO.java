package edu.loyola.cs485.model.dao;

import edu.loyola.cs485.model.entity.Shift;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ShiftDAO extends AbstractDAO<Shift> {
    @Override
    public void create(Shift entity) throws SQLException {
        Connection con = getConnection(); // Always open a connection

        String sql = "INSERT INTO shift (start_shift, end_shift) VALUES (?, ?)";
        PreparedStatement pst = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
        pst.setTimestamp(1, entity.getStartShift());
        pst.setTimestamp(2, entity.getEndShift());
        pst.executeUpdate();

        ResultSet rs = pst.getGeneratedKeys();
        if (rs.next()) {
            entity.setID(rs.getInt(1));
        }

        con.close(); // Dont forget to close it
    }

    @Override
    public Shift read(int ID) throws SQLException {
        Shift readShift = new Shift();
        Connection con = getConnection();

        String sql = "SELECT * FROM shift WHERE id_shift = ?";
        PreparedStatement pst = con.prepareStatement(sql);
        pst.setInt(1, ID);
        ResultSet Rs = pst.executeQuery();
        if (Rs.next()) { //IF because there should be 1 or 0 results
            readShift.setID( Rs.getInt("id_shift") );
            readShift.setStartShift( Rs.getTimestamp("start_shift") );
            readShift.setEndShift( Rs.getTimestamp("end_shift") );
        }

        con.close();
        return readShift;
    }

    @Override
    public void update(Shift entity) throws SQLException {
        Connection con = getConnection();
        String sql = "UPDATE shift set start_shift = ?, end_shift = ? WHERE id_shift = ?";
        PreparedStatement pst = con.prepareStatement(sql);
        pst.setTimestamp(1,entity.getStartShift());
        pst.setTimestamp(2,entity.getEndShift());
        pst.setInt(3,entity.getID());
        pst.executeUpdate();
        con.close();

    }

    @Override
    public void delete(int ID) throws SQLException {
        Connection con = getConnection();
        String sql = "DELETE FROM shift WHERE id_shift = ?";

        PreparedStatement pst = con.prepareStatement(sql);
        pst.setInt(1, ID);
        pst.executeUpdate();

        con.close();
    }

    @Override
    public List<Shift> list() throws SQLException {
        ArrayList<Shift> lstShift = new ArrayList<>();

        Connection con = getConnection();
        String sql = "SELECT * FROM shift ORDER BY start_shift, end_shift ";
        PreparedStatement pst = con.prepareStatement(sql);
        ResultSet rs = pst.executeQuery();
        while (rs.next()) { // While there is a next row of results
            Shift c = new Shift();
            c.setID( rs.getInt("id_shift") );
            c.setStartShift( rs.getTimestamp("start_shift") );
            c.setEndShift( rs.getTimestamp("end_shift") );
            lstShift.add( c ); // add client to our Collection
        }

        return lstShift;
    }
}
