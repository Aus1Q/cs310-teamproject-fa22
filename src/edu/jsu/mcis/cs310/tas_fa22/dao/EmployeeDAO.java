/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.jsu.mcis.cs310.tas_fa22.dao;

import edu.jsu.mcis.cs310.tas_fa22.Badge;
import edu.jsu.mcis.cs310.tas_fa22.Department;
import edu.jsu.mcis.cs310.tas_fa22.Employee;
import edu.jsu.mcis.cs310.tas_fa22.EmployeeType;
import edu.jsu.mcis.cs310.tas_fa22.EventType;
import edu.jsu.mcis.cs310.tas_fa22.Punch;
import edu.jsu.mcis.cs310.tas_fa22.Shift;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author Tony
 */
public class EmployeeDAO {
   private static final String QUERY_FIND_ID = "SELECT * FROM employee WHERE id = ?";
   private static final String QUERY_FIND_BADGE = "SELECT * FROM employee WHERE badgeid = ?";

    private final DAOFactory daoFactory;

        EmployeeDAO(DAOFactory daoFactory) {

        this.daoFactory = daoFactory;
        }
        
        
        public Employee find(int id) {

        Employee employee = null;

        PreparedStatement ps = null;
        ResultSet rs = null;

        try {

            Connection conn = daoFactory.getConnection();

            if (conn.isValid(0)) {

                ps = conn.prepareStatement(QUERY_FIND_ID);
                ps.setString(1, Integer.toString(id));

                boolean hasresults = ps.execute();

                if (hasresults) {

                    rs = ps.getResultSet();
                    
                    while (rs.next()) {
                        
                        String firstname = rs.getString("firstname");
                        String middlename = rs.getString("middlename");
                        String lastname = rs.getString("lastname");
                        
                        EmployeeType employeetype = EmployeeType.values()[rs.getInt("employeetypeid")];
                        
                      
                        DepartmentDAO DepartmentDAO = daoFactory.getDepartmentDAO();
                        Department department = DepartmentDAO.find(rs.getInt("departmentid"));
                        
                        BadgeDAO badgeDAO = daoFactory.getBadgeDAO();
                        Badge badge = badgeDAO.find(rs.getString("badgeid"));
                        
                    
                        ShiftDAO ShiftDAO = new ShiftDAO(daoFactory);
                        Shift shift = ShiftDAO.find(rs.getInt("shiftid"));
                        
                        DateTimeFormatter dateformat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                        LocalDateTime active = LocalDateTime.parse(rs.getString("active"), dateformat);
                        
                        
                        employee = new Employee(id, firstname, middlename, lastname, active, badge, department, shift, employeetype);
                    }

                }

            }

        } catch (SQLException e) {

            throw new DAOException(e.getMessage());

        } finally {

            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    throw new DAOException(e.getMessage());
                }
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    throw new DAOException(e.getMessage());
                }
            }

        }

        return employee;

    }
        public Employee find(Badge badge) {

        Employee employee = null;

        PreparedStatement ps = null;
        ResultSet rs = null;

        try {

            Connection conn = daoFactory.getConnection();

            if (conn.isValid(0)) {

                ps = conn.prepareStatement(QUERY_FIND_BADGE);
                ps.setString(1, (badge.getId()));

                boolean hasresults = ps.execute();

                if (hasresults) {

                    rs = ps.getResultSet();

                    while (rs.next()) {
                        
                        int id = rs.getInt("id");
                        String firstname = rs.getString("firstname");
                        String middlename = rs.getString("middlename");
                        String lastname = rs.getString("lastname");
                        
                        EmployeeType employeetype = EmployeeType.values()[rs.getInt("employeetypeid")];
                        
                      
                        DepartmentDAO DepartmentDAO = daoFactory.getDepartmentDAO();
                        Department department = DepartmentDAO.find(rs.getInt("departmentid"));
                        
                        
                        
                    
                        ShiftDAO ShiftDAO = new ShiftDAO(daoFactory);
                        Shift shift = ShiftDAO.find(badge);
                        
                        DateTimeFormatter dateformat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                        LocalDateTime active = LocalDateTime.parse(rs.getString("active"), dateformat);
                        
                        
                        employee = new Employee(id, firstname, middlename, lastname, active, badge, department, shift, employeetype);
                        
                        

                    }

                }

            }

        } catch (SQLException e) {

            throw new DAOException(e.getMessage());

        } finally {

            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    throw new DAOException(e.getMessage());
                }
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    throw new DAOException(e.getMessage());
                }
            }

        }

        return employee;

    }
}
