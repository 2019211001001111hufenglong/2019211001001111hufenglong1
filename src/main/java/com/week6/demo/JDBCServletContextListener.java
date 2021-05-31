package com.week6.demo;


import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@WebListener
public class JDBCServletContextListener implements ServletContextListener {
    public void contextInitialized(ServletContextEvent servletContextEvent) {

        ServletContext context =servletContextEvent.getServletContext();
        String driver = context.getInitParameter("driver");
        String url = context.getInitParameter("url");
        String username = context.getInitParameter("username");
        String password = context.getInitParameter("password");
        try {
            Class.forName(driver);
           Connection con= DriverManager.getConnection(url,username,password);
            System.out.println(con);
            context.setAttribute("con",con);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void contextDestroyed(ServletContextEvent servletContextEvent) {

        servletContextEvent.getServletContext().removeAttribute("con");
    }
}
