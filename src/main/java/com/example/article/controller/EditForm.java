package com.example.article.controller;

import com.example.article.entity.Article;
import com.example.article.repository.ArticleRepository;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class EditForm {
    public TextField txtTitle;
    public TextField txtDescription;
    public TextField txtContent;
    ArticleRepository articleRepository = new ArticleRepository();

}
