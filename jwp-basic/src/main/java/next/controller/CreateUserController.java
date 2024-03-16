package next.controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.WebServiceRef;

import next.dao.UserDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import core.db.DataBase;
import core.mvc.Controller;
import next.model.User;

import java.io.IOException;
import java.sql.SQLException;


@WebServlet("/users/create")
public class CreateUserController extends HttpServlet {
    private static final Logger log = LoggerFactory.getLogger(CreateUserController.class);



    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = new User(
                req.getParameter("userId"),
                req.getParameter("password"),
                req.getParameter("name"),
                req.getParameter("email"));

        UserDao userDao = new UserDao();
        try{
            userDao.insert(user);
        } catch (SQLException e){
            log.error(e.getMessage());
        }
        resp.sendRedirect("/");
    }
}
