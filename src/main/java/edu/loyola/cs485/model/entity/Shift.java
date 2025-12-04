package edu.loyola.cs485.model.entity;
import java.sql.Timestamp;

public class Shift extends AbstractEntity {
    private Integer ID = null;
    private Timestamp startShift = null;
    private Timestamp endShift = null;

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public Timestamp getStartShift() {
    return startShift;
    }

    public void setStartShift(Timestamp startShift) {
        this.startShift = startShift;
    }

    public Timestamp getEndShift() {
        return endShift;
    }

    public void setEndShift(Timestamp endShift) {
        this.endShift = endShift;
    }


    @Override
    public String toString(){
        return getID().toString()+": Start of Shift:"+getStartShift()+" < End of Shift:"+getEndShift()+">";
    }
}
