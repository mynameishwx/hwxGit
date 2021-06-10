package com.demo.Service;

import javax.servlet.http.HttpServletRequest;

public interface wxSerivce {
    String data(HttpServletRequest request, String wxid, Integer wxint , String wxtext);
}
