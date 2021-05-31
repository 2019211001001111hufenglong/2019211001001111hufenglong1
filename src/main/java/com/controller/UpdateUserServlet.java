package com.controller;
import com.dao.UserDao;
import com.model.User;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;

@WebServlet(name = "UpdateUserServlet",value = "/updateUser")
public class UpdateUserServlet extends HttpServlet {
    Connection con = null;
    @Override
    public void init() throws ServletException{

        con = (Connection) getServletContext().getAttribute("con");
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer id = Integer.valueOf(request.getParameter("id"));
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        String gender = request.getParameter("gender");
        Date birthdate = Date.valueOf(request.getParameter("birthDate"));
        User user = new User(id,username,password,email,gender,birthdate);
        UserDao userDao = new UserDao();
        try {
            userDao.updateUser(con, user);
            User byId = userDao.findById(con, user.getId());
            request.getSession().setAttribute("user",byId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        request.getRequestDispatcher("WEB-INF/views/userInfo.jsp").forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("WEB-INF/views/updateUser.jsp").forward(request,response);
    }
}
