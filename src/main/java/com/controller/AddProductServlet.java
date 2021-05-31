package com.controller;

import com.dao.ProductDao;
import com.model.Category;
import com.model.Product;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "AddProductServlet",value = "/admin/addProduct")
@MultipartConfig(maxFileSize = 16177215)
public class AddProductServlet extends HttpServlet {
    Connection con  = null;
    public void init(){
        con= (Connection) getServletContext().getAttribute("con");
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String productName = request.getParameter("productName");
        Double price = request.getParameter("price")!=null?Double.parseDouble(request.getParameter("price")):0.0;
        int categoryId = request.getParameter("categoryId")!=null?Integer.parseInt(request.getParameter("categoryId")):0;
        String productDescription = request.getParameter("productDescription");
        InputStream inputStream = null;
        Part filePart =request.getPart("picture");
        if (filePart!=null){
            System.out.println("file name:"+filePart.getName()+"size"+filePart.getSize()+"file type"+filePart.getContentType());
            inputStream = filePart.getInputStream();

        }
        Product product = new Product();
        product.setProductName(productName);
        product.setPrice(price);
        product.setPicture(inputStream);
        product.setProductDescription(productDescription);
        product.setCategoryId(categoryId);


        ProductDao productDao = new ProductDao();

        try {
            int n = productDao.save(product,con);
            if (1>0){
                response.sendRedirect("productList");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Category> categoryList = null;
        try {
            categoryList = Category.findAllCategory(con);
            request.setAttribute("categoryList",categoryList);
            String path = "/WEB-INF/views/admin/addProduct.jsp";
            request.getRequestDispatcher(path).forward(request,response);
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }
}
