package com.example.demo.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;

import java.io.IOException;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;

@Component
public class SimpleStatusServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setStatus(200);
        resp.setContentType("text/plain");
        resp.getWriter().write("OK");
    }

    @Bean
    public ServletRegistrationBean<HttpServlet> statusServlet() {
        ServletRegistrationBean<HttpServlet> srb = new ServletRegistrationBean<>(this, "/status");
        srb.setName("statusServlet");
        return srb;
    }
}