
package edu.jsu.mcis.cs310.tas_fa22;

import java.time.*;
import java.time.format.DateTimeFormatter;

public class Punch {
    
    //adjustmenttype
    // Punch(int terminalid, Badge badge, EventType punchtype); constructor for punch class
    // Punch(int id, int terminalid, Badge badge, LocalDateTime originaltimestamp, EventType punchtype);
    
    /*
    
In addition to the information about punches given in the database (in the "event" table), 
including the original timestamp, this class should include an extra instance field for the adjusted timestamp, as well as a PunchAdjustmentType field called "adjustmenttype"; 
these will become important in later features.  
(The PunchAdjustmentType data type has already been provided for you in the initial team repository, 
in the form of a simple enumeration.)
    
    // originalstimestamp.toLocalTime().isAfter(s.lunchstop)
    
    */
    
    private final Integer id;
    private final Integer terminalid;
    private final Badge badge;
    private final LocalDateTime originaltimestamp; //punch actually logged
    private final LocalDateTime adjustedtimestamp; // time after adjust method
    private final EventType punchtype;
    private PunchAdjustmentType adjustmenttype;
    
    
    
    
    
    public Punch(int terminalid, Badge badge, EventType punchtype) {
        this.id = null;
        this.terminalid = terminalid;
        this.badge = badge;
        this.punchtype = punchtype;
        this.adjustmenttype = null;
        this.originaltimestamp = LocalDateTime.now().withNano(0);
        this.adjustedtimestamp = null;
    }
            
            
    public Punch(int id, int terminalid, Badge badge, LocalDateTime originaltimestamp, EventType punchtype) {
        this.id = id;
        this.terminalid = terminalid;  
        this.originaltimestamp = originaltimestamp;
        this.badge = badge;
        this.punchtype = punchtype;
        this.adjustedtimestamp = null;
    }   
      

    
  
    
    public int getId() {
        return id;
    }

    public int getTerminalid() {
        return terminalid;
    }

    public Badge getBadge() {
        return badge;
    }

    public LocalDateTime getOriginaltimestamp() {
        return originaltimestamp;
    }
    
    private boolean isOnInterval(int minutes, int interval){
        return 0 == (minutes % interval);
    }

    public LocalDateTime getAdjustedTimeStamp() {
        return adjustedtimestamp;
    }
    
    public EventType getPunchtype(){

        return punchtype;
    }
    
    public void adjust(Shift s) {
        //adjustedtimestamp = null;
       
        if (originaltimestamp.getDayOfWeek() == DayOfWeek.SATURDAY|| originaltimestamp.getDayOfWeek() == DayOfWeek.SUNDAY ) {
            if (isOnInterval(originaltimestamp.getMinute(), s.getRoundinterval())) {
                adjustmenttype = PunchAdjustmentType.NONE;
            }
            
            else adjustmenttype = PunchAdjustmentType.INTERVAL_ROUND;  
        }
        
        //else if | grace period
        
        // else if (false) adjustmenttype = PunchAdjustmentType.grace
        // create new local date times for each line on the specific localdate of a punch
        // ell
        //}
    }
    
    public String printOriginal() {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("EEE MM/dd/yyyy HH:mm:ss");
        StringBuilder s = new StringBuilder();
        
        s.append('#');
        s.append(badge.getId());
        s.append(" ");
        s.append(punchtype);
        s.append(": ");
        s.append(originaltimestamp.format(format).toUpperCase());
       

        return s.toString();
        


    }
    
    public String printAdjusted() {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("EEE MM/dd/yyyy HH:mm:ss");
        StringBuilder s = new StringBuilder();
        
        s.append('#');
        s.append(badge.getId());
        s.append(" ");
        s.append(punchtype);
        s.append(": ");
        s.append(adjustedtimestamp.format(format).toUpperCase());
        
        return s.toString();
    }
    
}
