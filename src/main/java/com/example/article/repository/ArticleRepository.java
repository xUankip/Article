package com.example.article.repository;
import com.example.article.entity.Article;

import java.sql.*;
import java.util.ArrayList;

public class ArticleRepository {
    private final String MYSQL_CONNECTION_STRING = "jdbc:mysql://localhost:3306/article_crawler";
    private final String MYSQL_USERNAME = "root";
    private final String MYSQL_PASSWORD = "";
    public void save(Article article){
        try {
            Connection connection = DriverManager.getConnection(MYSQL_CONNECTION_STRING, MYSQL_USERNAME, MYSQL_PASSWORD);
            String strSql = "insert into articles_fx (title, description, content) values (?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(strSql);
            preparedStatement.setString(1, article.getTitle());
            preparedStatement.setString(2, article.getDescription());
            preparedStatement.setString(3, article.getContent());
            preparedStatement.executeUpdate();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public ArrayList<Article> findAll() {
        ArrayList<Article> articles = new ArrayList<>();
        try {
            Connection connection = DriverManager.getConnection(MYSQL_CONNECTION_STRING, MYSQL_USERNAME, MYSQL_PASSWORD);
            String prSql = "select * from articles_fx";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(prSql);
            while (resultSet.next()) {
                String id = resultSet.getString("id");
                String title = resultSet.getString("title");
                String description = resultSet.getString("description");
                String content = resultSet.getString("content");
                Article article = new Article(id, title, description, content);
                articles.add(article);
            }
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return articles;
    }
    public void edit(Article article) {
        try {
            Connection connection = DriverManager.getConnection(MYSQL_CONNECTION_STRING, MYSQL_USERNAME, MYSQL_PASSWORD);
            String strSql = "update articles_fx set title = ?, description = ?, content = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(strSql);
            preparedStatement.setString(1, article.getTitle());
            preparedStatement.setString(2, article.getDescription());
            preparedStatement.setString(3, article.getContent());
            preparedStatement.execute();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("Update success");
    }
}
