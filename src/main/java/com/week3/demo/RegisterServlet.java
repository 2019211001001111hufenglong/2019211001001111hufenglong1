package com.week3.demo;




import com.model.User;

import javax.servlet.ServletException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import java.sql.*;



public class RegisterServlet extends HttpServlet {

    Connection con = null;
    @Override
    public void init() throws ServletException{

         con = (Connection) getServletContext().getAttribute("con");
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PreparedStatement ps=null;
//        Integer id = Integer.valueOf(request.getParameter("id"));
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        String gender = request.getParameter("gender");
        Date birthdate = Date.valueOf(request.getParameter("birthDate"));
        User user =new User(null,username,password,email,gender,birthdate);

        String sql = "insert into usertable(username,password,email,gender,birthdate) values(?,?,?,?,?)";
        String search = "select * from usertable";

        try {
            ps=con.prepareStatement(sql);

            ps.setString(1,user.getUsername());
            ps.setString(2,user.getPassword());
            ps.setString(3,user.getEmail());
            ps.setString(4,user.getGender());
            ps.setDate(5, (Date) user.getBirthdate());
            ps.executeUpdate();
            System.out.println("插入成功");

            response.sendRedirect("login");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("WEB-INF/views/register.jsp").forward(request,response);
    }
    @Override
    public void destroy(){
        super.destroy();
        try {
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
