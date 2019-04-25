package com.codecool.web.servlet;

import com.codecool.web.dao.PoemDao;
import com.codecool.web.dao.database.DatabasePoemDao;
import com.codecool.web.dto.PoemDto;
import com.codecool.web.model.Poem;
import com.codecool.web.service.PoemService;
import com.codecool.web.service.exception.ServiceException;
import com.codecool.web.service.simple.SimplePoemService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

@WebServlet("/protected/poem")
public class PoemServlet extends AbstractServlet {

    // https://www.postgresql.org/docs/current/static/errcodes-appendix.html
    private static final String SQL_ERROR_CODE_UNIQUE_VIOLATION = "23505";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try (Connection connection = getConnection(req.getServletContext())) {
            PoemDao poemDao = new DatabasePoemDao(connection);

            PoemService poemService = new SimplePoemService(poemDao);

            String id = req.getParameter("id");

            Poem poem = poemService.getPoem(id);

            sendMessage(resp, HttpServletResponse.SC_OK, new PoemDto(poem));
        } catch (ServiceException ex) {
            sendMessage(resp, HttpServletResponse.SC_OK, ex.getMessage());
        } catch (SQLException ex) {
            handleSqlError(resp, ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try (Connection connection = getConnection(req.getServletContext())) {
            PoemDao poemDao = new DatabasePoemDao(connection);
            PoemService poemService = new SimplePoemService(poemDao);

            String poemId = req.getParameter("id");
            String[] shopIds = req.getParameterValues("shopIds");

            doGet(req, resp);
        } catch (SQLException ex) {
            if (SQL_ERROR_CODE_UNIQUE_VIOLATION.equals(ex.getSQLState())) {
                sendMessage(resp, HttpServletResponse.SC_BAD_REQUEST, "Poem has been already added to one of the selected shops");
            } else {
                handleSqlError(resp, ex);
            }
        }
    }
}
