package servlets;

import database.HibernateUtil;
import database.Sports;
import database.Student;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "studentprinting", urlPatterns = {"/student"})
public class studentprinting extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.addCookie(new Cookie("praveen", "sai"));
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        Session session = HibernateUtil.getDatabaseSession();
        Transaction transaction = session.beginTransaction();
        List<Student> students = session.createQuery("from Student ").list();

        out.println("<table border=1px>");
        for (Student student : students) {
            out.println("<tr>");
            out.println("<td> " + student.getId() + "</td>");
            out.println("<td> " + student.getName() + "</td>");
            out.println("<td> " + student.getAddress() + "</td>");
            out.println("</tr>");
        }
        List<Sports> sports = session.createQuery("from Sports ").list();

        out.println("<table border=1px>");
        for (Sports sport : sports) {
            out.println("<tr>");
            out.println("<td> " + sport.getId() + "</td>");
            out.println("<td> " + sport.getName() + "</td>");
            out.println("</tr>");
        }
    }
}
