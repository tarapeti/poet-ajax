package com.codecool.web.service.simple;

import com.codecool.web.dao.PoemDao;
import com.codecool.web.model.Poem;
import com.codecool.web.service.PoemService;
import com.codecool.web.service.exception.ServiceException;

import java.sql.SQLException;
import java.util.List;

public class SimplePoemService implements PoemService {

    private final PoemDao poemDao;

    public SimplePoemService(PoemDao poemDao) {
        this.poemDao = poemDao;
    }

    @Override
    public List<Poem> getPoems() throws SQLException {
        return null;
    }

    @Override
    public Poem getPoem(String id) throws SQLException, ServiceException {
        return null;
    }

    @Override
    public Poem addShop(String name) throws SQLException, ServiceException {
        return null;
    }
}
