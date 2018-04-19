/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.ogiba.mavenwebproject.servlets;

import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import pl.ogiba.mavenwebproject.models.Post;

/**
 *
 * @author ogiba
 */
public class Posts extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Posts</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Posts at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    @SuppressWarnings("CallToPrintStackTrace")
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
//        processRequest(request, response);
        response.setContentType("application/json");

        boolean loadDataFromFile = false;

        String fromFileParam = request.getParameter("fromFile");
        
        if (fromFileParam != null) {
            loadDataFromFile = Boolean.parseBoolean(fromFileParam);
        }

        final String content;
        if (loadDataFromFile) {
            content = getFile("files/mocked_posts.json");
        } else {
            content = provideMockedItems(request.getParameter("limit"));
        }

        try (PrintWriter out = response.getWriter()) {
            out.print(content);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    private String provideMockedItems(String newLimit) {
        int limit = 10;

        if (newLimit != null) {
            try {
                limit = Integer.parseInt(newLimit);
            } catch (NumberFormatException ex) {
                ex.printStackTrace();
            }
        }

        ArrayList<Post> posts = new ArrayList<>();

        for (int i = 0; i < limit; i++) {
            Post post = new Post(i, "Test", "Test content");
            posts.add(post);
        }

        Gson gson = new Gson();
        return gson.toJson(posts);
    }

    private String getFile(String fileName) {

//        StringBuilder result = new StringBuilder("");
//
//        //Get file from resources folder
//        ClassLoader classLoader = getClass().getClassLoader();
//        File file = new File(classLoader.getResource(fileName).getFile());
//
//        try (Scanner scanner = new Scanner(file)) {
//
//            while (scanner.hasNextLine()) {
//                String line = scanner.nextLine();
//                result.append(line).append("\n");
//            }
//
//            scanner.close();
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        InputStream inputStream = getClass().getClassLoader()
                .getResourceAsStream(fileName);
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

        String expectedValue = "";
        try {
            String value;
            do {
                value = reader.readLine();

                if (value != null) {
                    expectedValue += value;
                }
            } while (value != null);
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        return expectedValue;

    }
}
