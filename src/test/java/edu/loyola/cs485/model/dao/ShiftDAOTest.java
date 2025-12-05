package edu.loyola.cs485.model.dao;

import edu.loyola.cs485.controller.ShiftService;
import edu.loyola.cs485.model.entity.Shift;
import org.junit.jupiter.api.*;

import java.sql.Timestamp;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


public class ShiftDAOTest {

    @Test
    public void testFake(){
        assertAll(
            () -> assertEquals(1,1)
        );
    }

    @Test
    public void testCreateClient() throws Exception{
        ShiftDAO dao = new ShiftDAO();
        dao.setTestDatabase();

        Shift shift = new Shift();
        shift.setStartShift(Timestamp.valueOf("2025-12-03 21:58:00"));
        shift.setEndShift(Timestamp.valueOf("2025-12-04 21:58:00"));

        dao.create(shift); // Method Under Test

        //clean up
        dao.delete( shift.getID() );

        assertAll(
                () -> assertNotNull( shift.getID() )
        );
    }

    @Test
    public void testUpdateClient() throws Exception {
        ShiftDAO dao = new ShiftDAO();
        dao.setTestDatabase();
        Shift testShift = new Shift();
        testShift.setStartShift(Timestamp.valueOf("2025-12-03 21:58:00"));
        testShift.setEndShift(Timestamp.valueOf("2025-12-04 21:58:00"));
        dao.create(testShift);

        String test1 = "2000-01-01 07:00:00";
        String test2 = "2000-01-01 10:00:00";
        ShiftService config = new ShiftService();
        config.configure(test1,test2,testShift);

        dao.update(testShift);

        assertEquals(Timestamp.valueOf("2000-01-01 07:00:00"), testShift.getStartShift());
        assertEquals(Timestamp.valueOf("2000-01-01 10:00:00"), testShift.getEndShift());
    }

    @Test
    public void testReadClient() throws Exception{
        ShiftDAO dao = new ShiftDAO();
        dao.setTestDatabase();

        Shift shift = new Shift();
        shift.setStartShift(Timestamp.valueOf("2025-12-03 21:58:00"));
        shift.setEndShift(Timestamp.valueOf("2025-12-04 21:58:00"));

        dao.create(shift);
        Shift found = dao.read(shift.getID()); //Function under test

        //clean up
        dao.delete( shift.getID() );

        assertAll(
                () -> assertEquals(found.getID(), shift.getID()),
                () -> assertEquals(found.getStartShift(), shift.getStartShift()),
                () -> assertEquals(found.getEndShift(), shift.getEndShift())
        );

    }

    @Test
    public void testReadClientDoesNotExist() throws Exception{
        ShiftDAO dao = new ShiftDAO();
        dao.setTestDatabase();

        Shift found = dao.read(10);
        assertAll(
                () -> assertNull( found.getID() ),
                () -> assertNull( found.getStartShift() ),
                () -> assertNull( found.getEndShift() )
        );
    }

    @Test
    public void testListClient() throws Exception{
        ShiftDAO dao = new ShiftDAO();
        dao.setTestDatabase();

        List<Shift> lst = dao.list();
        assertAll(
                () -> assertEquals(0, lst.size())
        );

    }
}
