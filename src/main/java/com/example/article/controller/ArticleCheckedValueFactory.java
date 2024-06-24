package com.example.article.controller;

import com.example.article.entity.Article;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableColumn;
import javafx.util.Callback;

public class ArticleCheckedValueFactory implements Callback<TableColumn.CellDataFeatures<Article, CheckBox>, ObservableValue<CheckBox>> {
    @Override
    public ObservableValue<CheckBox> call(TableColumn.CellDataFeatures<Article, CheckBox> param) {
        Article article = param.getValue();
        CheckBox checkBox = new CheckBox();
        checkBox.selectedProperty().setValue(article.isChecked());
        checkBox.selectedProperty().addListener((ov, old_val, new_val) -> {
            article.setChecked(new_val);
        });
        return new SimpleObjectProperty<>(checkBox);
    }
}
