package edu.jsu.mcis.cs310.tas_fa22;

import edu.jsu.mcis.cs310.tas_fa22.dao.*;
import java.time.*;
import java.util.*;
import java.time.temporal.ChronoUnit;
import java.time.format.DateTimeFormatter;
import org.json.simple.*;

public class Main {

    public static void main(String[] args) {
        
        // test database connectivity; get DAOs

        DAOFactory daoFactory = new DAOFactory("tas.jdbc");
        BadgeDAO badgeDAO = daoFactory.getBadgeDAO();
        ShiftDAO shiftDAO = daoFactory.getShiftDAO();
        PunchDAO punchDAO = daoFactory.getPunchDAO();
        DepartmentDAO departmentDAO = daoFactory.getDepartmentDAO();
        EmployeeDAO employeeDAO = daoFactory.getEmployeeDAO();
        
        
        
        // find badge

        Badge b = badgeDAO.find("31A25435");
        Shift s = shiftDAO.find(3);
        Department d = departmentDAO.find(1);
        Employee e = employeeDAO.find(1);
        Punch p = punchDAO.find(1963);
        p.adjust(s);
        
        
        
        // output should be "Test Badge: #31A25435 (Munday, Paul J)"
        
        

        
        
        //Shift s = shiftDAO.find("B6902696");
        
        System.err.println("Test Badge: " + b.toString());

        System.err.println("Test Shift: " + s.toString());
        
        System.err.println("Test Punch: " + p.printOriginal());
        
        System.err.println("Test Department: " + d.toString());
        
        System.err.println("Test Employee: " + e.toString());


    }

}
