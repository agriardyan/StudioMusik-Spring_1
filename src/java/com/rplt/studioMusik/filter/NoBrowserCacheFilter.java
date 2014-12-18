///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//
//package com.rplt.studioMusik.filter;
//
//import java.io.IOException;
//import javax.servlet.Filter;
//import javax.servlet.FilterChain;
//import javax.servlet.FilterConfig;
//import javax.servlet.ServletException;
//import javax.servlet.ServletRequest;
//import javax.servlet.ServletResponse;
//import javax.servlet.annotation.WebFilter;
//import javax.servlet.http.HttpServletResponse;
//
///**
// *
// * @author root
// */
//@WebFilter
//public class NoBrowserCacheFilter implements Filter {
//
//    @Override
//    public void destroy() {
//
//    }
//
//    @Override
//    public void doFilter(ServletRequest req, ServletResponse res,
//            FilterChain chain) throws IOException, ServletException {
//        HttpServletResponse response=(HttpServletResponse)res;
//        response.setHeader("Pragma", "no-cache");
//        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
//        response.setDateHeader("Expires", -1);
//        chain.doFilter(req, res);
//    }
//
//    @Override
//    public void init(FilterConfig filterConfig) throws ServletException {
//        
//    }
//
//
//}
