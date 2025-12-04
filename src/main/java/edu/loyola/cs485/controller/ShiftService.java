package edu.loyola.cs485.controller;

import edu.loyola.cs485.model.dao.ShiftDAO;
import edu.loyola.cs485.model.entity.Shift;
import java.text.SimpleDateFormat;
import java.sql.Timestamp;
import java.util.List;

/**
 * For an MVC architecture, we need another class to separate the Interface (View) from the DAOs (Model)
 * Even if it seems redudant to you, it is a good practice (make a habit of it).
 */
public class ShiftService {

    public Shift createShift(String strStartShift, String strEndShift) throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
        java.sql.Timestamp startShift = new java.sql.Timestamp(sdf.parse(strStartShift).getTime());
        java.sql.Timestamp endShift = new java.sql.Timestamp(sdf.parse(strEndShift).getTime());

        Shift shift = new Shift();
        shift.setStartShift(startShift);
        shift.setEndShift(endShift);

        ShiftDAO dao = new ShiftDAO();
        dao.create(shift);

        return shift;
    }

    public List<Shift> getAllShifts() throws Exception {
        ShiftDAO dao = new ShiftDAO();
        return dao.list();
    }

    public void deleteShift(int id) throws Exception {
        ShiftDAO dao = new ShiftDAO();
        dao.delete(id);
    }
}
