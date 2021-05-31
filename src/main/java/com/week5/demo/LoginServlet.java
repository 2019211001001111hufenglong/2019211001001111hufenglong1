package com.week5.demo;


import com.dao.UserDao;
import com.model.User;



import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;


@WebServlet(name = "LoginServlet" , value="/login")
public class LoginServlet extends HttpServlet {
    Connection con=null;
    public void init() {

        con = (Connection) getServletContext().getAttribute("con");
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PreparedStatement ps=null;
        PrintWriter out = response.getWriter();
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String search = "select * from usertable where username = ? and password=?";
        System.out.println(search);
        try {
            ps=con.prepareStatement(search);
            ps.setString(1,username);
            ps.setString(2,password);
            ResultSet rs = ps.executeQuery();
            System.out.println(rs);
            User user=new User();
            if (rs.next()){
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
            }
            System.out.println(user.getUsername());
            System.out.println(user.getPassword());

            if (user.getUsername()!= null && user.getPassword().equals(password)){
                request.setAttribute("id",rs.getInt("id"));
                request.setAttribute("username",rs.getString("username"));
                request.setAttribute("password",rs.getString("password"));
                request.setAttribute("email",rs.getString("email"));
                request.setAttribute("gender",rs.getString("gender"));
                request.setAttribute("birthDate",rs.getString("birthDate"));
                request.getRequestDispatcher("userInfo.jsp").forward(request,response);
                }else {
                out.println("Username or Password Error!!!");
                request.setAttribute("message","Username or Password Error");
                request.getRequestDispatcher("login.jsp").forward(request,response);
                }


        } catch (SQLException e) {
            e.printStackTrace();
        }
        UserDao userDao = new UserDao();
        try {
            User user = userDao.findByUsernamePassword(con,username,password);
                if (user!=null){
                    String rememberMe =request.getParameter("rememberMe");
                    if (rememberMe!=null && rememberMe.equals("1")){
                        Cookie usernameCookie = new Cookie("cUsername",user.getUsername());
                        Cookie passwordCookie = new Cookie("cPassword",user.getPassword());
                        Cookie rememberMeCookie = new Cookie("cRememberMe",rememberMe);
                        usernameCookie.setMaxAge(5);
                        passwordCookie.setMaxAge(5);
                        rememberMeCookie.setMaxAge(5);
                        response.addCookie(usernameCookie);
                        response.addCookie(passwordCookie);
                        response.addCookie(rememberMeCookie);

                    }

                    HttpSession session  =request.getSession();
                    System.out.println("session id-->"+session.getId());
                    session.setMaxInactiveInterval(7*24*60*60);
                    session.setAttribute("user",user);
                    request.getRequestDispatcher("WEB-INF/views/userInfo.jsp").forward(request,response);
                }else {
                    request.setAttribute("message","Username or Password Error");
                   request.getRequestDispatcher("WEB-INF/views/login.jsp").forward(request,response);
                }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("WEB-INF/views/login.jsp").forward(request,response);
    }
}
