package com.ojeksimangpred.IDServices;

import java.io.*;
import java.lang.ClassNotFoundException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

import javax.servlet.*;
import javax.servlet.http.*;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.google.gson.Gson;

import java.security.SecureRandom;
import java.util.Date;
import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.*;
import com.nimbusds.jwt.*;
import com.ojeksimangpred.bean.*;

public class Logout extends HttpServlet {

	public void doGet(HttpServletRequest request,HttpServletResponse response)
	throws ServletException,IOException {
		
		String json = request.getParameter("user");
		User user = new Gson().fromJson(json,User.class);
	}
}