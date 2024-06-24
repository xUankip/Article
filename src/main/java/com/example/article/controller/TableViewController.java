package com.example.article.controller;

import com.example.article.entity.Article;
import com.example.article.repository.ArticleRepository;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class TableViewController implements Initializable {
    public TableView<Article> tableView;
    private ArticleRepository articleRepository = new ArticleRepository();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<Article> data = FXCollections.observableArrayList(articleRepository.findAll());
        tableView.setItems(data);
    }

    public void doClick(MouseEvent mouseEvent) {
        System.out.println("Hi");
        ObservableList<Article> list = tableView.getItems();
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i).isChecked());
        }
    }
}
