package com.company;

import java.sql.*;

public class Main {

    public static void main(String[] args) {
        try {
            Class.forName("org.sqlite.JDBC");
            Connection conn= DriverManager.getConnection("jdbc:sqlite:reference5.db");
            Statement stm=conn.createStatement();
            ResultSet rs;


            //Creation of a Table in the database:
            System.out.println("Creation of students table:\n");
            stm.executeUpdate("create table students(sid int,name varchar(20),dept varchar(20))");



            //Insertion of Value in the Table:
            System.out.println("Insertion of values in students table:\n");
            stm.executeUpdate("insert into students values(101,'hari','cse')");
            stm.executeUpdate("insert into students values(102,'dhina','cse')");
            stm.executeUpdate("insert into students values(103,'vijay','cse')");
            rs = stm.executeQuery("select * from students");
            printer(rs);//An user Defined Method Defined Below the Main Function:
            System.out.println("-----------------------------------");



            //Updating Value in the Table:
            System.out.println("Updating the table:\n");
            stm.executeUpdate("update students set name='harish' where sid=101");
            rs = stm.executeQuery("select * from students");
            printer(rs);//An user Defined Method Defined Below the Main Function:
            System.out.println("-----------------------------------");



            //Altering the Schema of the Table:
            System.out.println("Altering the Schema of the table:\n");
            stm.executeUpdate("alter table students add email varchar(20)");
            stm.executeUpdate("update students set email='harish.jaybal@gmail.com' where sid in (101,102)");
            stm.executeUpdate("update students set email='john@gmail.com' where sid in (103)");
            stm.executeUpdate("update students set email='dhina@gmail.com' where sid in (102)");
            rs = stm.executeQuery("select * from students");
            printerusing(rs);//An user Defined Method Defined Below the Main Function:
            System.out.println("-----------------------------------");



            //Using the Arithematic operation like Addition:
            System.out.println("Using of Operations  addition:\n");
            rs=stm.executeQuery("select sid+100 as namer from students");
            while(rs.next())
            {
                System.out.println(rs.getInt("namer"));
            }
            System.out.println("-----------------------------------");




            //Using the Like operation to check for the Regular Expression:
            System.out.println("Using LIKE operator:\n");
            rs=stm.executeQuery("select * from students where email like '%@gmail%'");
            printerusing(rs);//An user Defined Method Defined Below the Main Function:
            System.out.println("-----------------------------------");
            rs=stm.executeQuery("select * from students where email like 'j___@gmail%'");
            printerusing(rs);//An user Defined Method Defined Below the Main Function:
            System.out.println("-----------------------------------");



            //Using Aggregation function:
            System.out.println("Using of Aggregation function:\n");
            rs = stm.executeQuery("select avg(sid) as avger from students");
            System.out.println(rs.getInt("avger"));
            System.out.println("-----------------------------------");



            //Adding aditional value to the Table for Grouping the table:
            stm.executeUpdate("insert into students values (201, 'kamal', 'ece', 'kamal@gmail.com')");
            stm.executeUpdate("insert into students values (202, 'naveen', 'ece', 'naveen@gmail.com')");



            //Using GroupBy method:
            System.out.println("Using of group by function\n");
            rs = stm.executeQuery("select dept, count(*) as count from students group by dept");
            while(rs.next())
            {
                System.out.println(rs.getString("dept") + " " + rs.getInt("count"));
            }
            System.out.println("-----------------------------------");



            //Using Having method :
            System.out.println("Using Having Method\n");
            rs = stm.executeQuery("select dept, count(*) as count from students group by dept having count > 2");
            while(rs.next())
            {
               System.out.println(rs.getString("dept") + " " + rs.getInt("count"));
             }
            System.out.println("-----------------------------------");



            //Using Limit Method:
            System.out.println("Using limit Method:\n");
            rs = stm.executeQuery("select * from students limit 1, 3");
            printerusing(rs);//An user Defined Method Defined Below the Main Function:
            System.out.println("-----------------------------------");



            //Creation of Staff table for Join Query:
            System.out.println("Creating of Staff Table\n");
            stm.executeUpdate("create table staff(stid int, name varchar, dept varchar)");
            stm.executeUpdate("insert into staff values(1, 'chardru', 'cse')");
            stm.executeUpdate("insert into staff values(2, 'Anu', 'cse')");
            stm.executeUpdate("insert into staff values(3, 'jean', 'cse')");
            stm.executeUpdate("insert into staff values(4,'sheryl', 'ece')");
            stm.executeUpdate("insert into staff values(5, 'jesline', 'ece')");
            rs= stm.executeQuery("select * from staff");
            printStaff(rs);//An user Defined Method Defined Below the Main Function:
            System.out.println("-----------------------------------");



            //Performing Inner Join:
            System.out.println("Performing Inner join:\n");
            rs = stm.executeQuery("select students.name as stname, staff.name as sname, students.dept as dept from students inner join staff on students.dept = staff.dept");
            while(rs.next()){
                System.out.println(rs.getString("stname") + " " + rs.getString("sname") +  " " + rs.getString("dept"));
            }
            System.out.println("-----------------------------------");



            //Performing Cross Join:
            System.out.println("Performing Cross join:\n");
            rs = stm.executeQuery("select students.name as stname, staff.name as sname, students.dept as dept from students cross join staff");
            while(rs.next()){
                System.out.println(rs.getString("stname") + " " + rs.getString("sname") +  " " + rs.getString("dept"));
            }
            System.out.println("-----------------------------------");



            //Performing Left Join:
            System.out.println("Performing Left Join:\n");
            rs = stm.executeQuery("select students.name as stname, staff.name as sname, students.dept as dept from students left join staff on students.dept = staff.dept");
            while(rs.next()){
                System.out.println(rs.getString("stname") + " " + rs.getString("sname") +  " " + rs.getString("dept"));
            }
            System.out.println("-----------------------------------");



            //Performing Union Operation:
            System.out.println("Union operation:\n");
            rs=stm.executeQuery("select sid as s,name as n, dept as d from students union select stid as s,name as n, dept as d  from staff");
            while(rs.next())
            {
                System.out.println(rs.getInt("s")+" "+rs.getString("n")+" "+rs.getString("d"));
            }
            System.out.println("-----------------------------------");



            //Performing Distinct Operation:
            System.out.println("Using distinct Method\n");
            rs=stm.executeQuery("select distinct dept as d from students");
            while(rs.next())
            {
                System.out.println(rs.getString("d"));
            }
            System.out.println("-----------------------------------");



            //Using AND Operation:
            System.out.println("Using AND operation:\n");
            rs=stm.executeQuery("select name as n from students where sid > 101 AND dept='cse'");
            while(rs.next())
            {
                System.out.println(rs.getString("n"));
            }
            System.out.println("-----------------------------------");


        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }
    private static void printer(ResultSet rs) throws SQLException {
        while(rs.next())
        {
            System.out.println(rs.getInt("sid")+" "+rs.getString("name")+" "+rs.getString("dept"));
        }
    }
    private static void printerusing(ResultSet rs) throws SQLException {
        while(rs.next())
        {
            System.out.println(rs.getInt("sid")+" "+rs.getString("name")+" "+rs.getString("dept")+" "+rs.getString("email"));
        }
    }
    private static void printStaff(ResultSet rs) throws SQLException {
        while(rs.next())
        {
            System.out.println(rs.getInt("stid")+" "+rs.getString("name")+" "+rs.getString("dept"));
        }
    }
}
