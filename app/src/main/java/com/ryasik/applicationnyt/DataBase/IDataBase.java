package com.ryasik.applicationnyt.DataBase;

import com.ryasik.applicationnyt.Model.Article;

import java.util.List;

public interface IDataBase {

    void createArticle(Article article);
    List getFavoritesArticles();
}
