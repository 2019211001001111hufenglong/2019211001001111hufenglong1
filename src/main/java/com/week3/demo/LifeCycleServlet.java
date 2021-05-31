package com.week3.demo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "LifeCycleServlet")
public class LifeCycleServlet extends HttpServlet {
    public LifeCycleServlet(){
        System.out.println("i am in constructor --> LifeCycleServlet");
    }
    // init() -add code
    @Override
    public void init(){
        System.out.println("i am in init()");
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("i am in service () -->doGet()");
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("i am in doGet() --->doGet(）");
    }
    @Override
    public void destroy(){

    }
}
