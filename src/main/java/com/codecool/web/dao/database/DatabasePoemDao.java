package com.codecool.web.dao.database;

import com.codecool.web.dao.PoemDao;
import com.codecool.web.model.Poem;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabasePoemDao extends AbstractDao implements PoemDao {

    public DatabasePoemDao(Connection connection) {
        super(connection);
    }

    @Override
    public List<Poem> findAll() throws SQLException {
        List<Poem> poems = new ArrayList<>();
        String sql = "SELECT id, title, context, user_id FROM coupons";
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                poems.add(fetchPoems(resultSet));
            }
        }
        return poems;
    }

    @Override
    public Poem findById(int id) throws SQLException {
        String sql = "SELECT id, title FROM poems WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return fetchPoems(resultSet);
                }
            }
        }
        return null;
    }

    private Poem fetchPoems(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("id");
        String title = resultSet.getString("title");
        String context = resultSet.getString("context");
        int user_id = resultSet.getInt("user_id");
        return new Poem(id, title, context, user_id);
    }
}
