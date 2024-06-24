package com.example.article.controller;

import com.example.article.entity.Article;
import com.example.article.repository.ArticleRepository;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

public class ArticleForm {
    public TextField txtTitle;
    public TextField txtDescription;
    public TextField txtContent;
    public TableColumn<Article, Boolean> checkBox;
    public TextField idInfo;
    public TextField titleInfo;
    public TextArea desInfo;
    public TextArea contentInfo;
    @FXML
    private TableView<Article> tableView;
    @FXML
    private TableColumn<Article, String> txtId;
    @FXML
    private TableColumn<Article, String > txtTitleView;
    @FXML
    private TableColumn<Article, String> txtDescriptionView;
    @FXML
    private TableColumn<Article, String> txtContentView;
    private ObservableList<Article> articlesList;
    ArticleRepository articleRepository = new ArticleRepository();
    private final String MYSQL_CONNECTION_STRING = "jdbc:mysql://localhost:3306/article_crawler";
    private final String MYSQL_USERNAME = "root";
    private final String MYSQL_PASSWORD = "";
    public void tableLoad(ActionEvent actionEvent) {
        try {
            ArrayList<Article> arrayList = articleRepository.findAll();
            articlesList = FXCollections.observableArrayList(arrayList);
            txtId.setCellValueFactory(new PropertyValueFactory<Article, String>("id"));
            txtTitleView.setCellValueFactory(new PropertyValueFactory<Article, String>("title"));
            txtDescriptionView.setCellValueFactory(new PropertyValueFactory<Article, String>("description"));
            txtContentView.setCellValueFactory(new PropertyValueFactory<Article, String>("content"));
            checkBox.setCellValueFactory(cellData -> cellData.getValue().selectedProperty());
            checkBox.setCellFactory(CheckBoxTableCell.forTableColumn(checkBox));
            tableView.setItems(articlesList);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public void processSave(ActionEvent actionEvent) {
        Article article = new Article();
        article.setTitle(txtTitle.getText());
        article.setDescription(txtDescription.getText());
        article.setContent(txtContent.getText());
        articleRepository.save(article);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText("Article Save Success");
        alert.show();
        reset();
    }

    public void processReset(ActionEvent actionEvent) {
        reset();
    }
    public void reset(){
        txtTitle.clear();
        txtDescription.clear();
        txtContent.clear();
    }

    public void changeScene(ActionEvent actionEvent) {

    }
    @FXML
    public void selected(MouseEvent mouseEvent) {
        int index = -1;
        index = tableView.getSelectionModel().getSelectedIndex();
        if (index <= -1){
            return;
        }
        idInfo.setText(txtId.getCellData(index));
        titleInfo.setText(txtTitleView.getCellData(index));
        desInfo.setText(txtDescriptionView.getCellData(index));
        contentInfo.setText(txtContentView.getCellData(index));
    }
    public void processEdit(ActionEvent actionEvent) throws SQLException {
        Article article = new Article();
        Connection connection = DriverManager.getConnection(MYSQL_CONNECTION_STRING, MYSQL_USERNAME, MYSQL_PASSWORD);
        PreparedStatement preparedStatement;
        String value2 = titleInfo.getText();
        String value3 = desInfo.getText();
        String value4 = contentInfo.getText();
        String sql = "update articles_fx set title='"+value2+"', description = '"+value3+"', content = '"+value4+"' ";
        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, article.getTitle());
        preparedStatement.executeUpdate();
        update();
    }
    public void update(){
        txtId.setCellValueFactory(new PropertyValueFactory<Article, String>("id"));
        txtTitleView.setCellValueFactory(new PropertyValueFactory<Article, String>("title"));
        txtDescriptionView.setCellValueFactory(new PropertyValueFactory<Article, String>("description"));
        txtContentView.setCellValueFactory(new PropertyValueFactory<Article, String>("content"));
        tableView.setItems(articlesList);
    }
}

