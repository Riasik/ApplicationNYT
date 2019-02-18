package com.ryasik.applicationnyt.Ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.ryasik.applicationnyt.Adapters.RecyclerArticlesAdapter;
import com.ryasik.applicationnyt.DataBase.BaseHelper;
import com.ryasik.applicationnyt.Model.Article;
import com.ryasik.applicationnytexample.R;

import java.util.List;

public class FavoritesActivity extends AppCompatActivity{

   private RecyclerArticlesAdapter adapter;
    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private List<Article> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view_favorite);
        linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        onLoadList();
        adapter = new RecyclerArticlesAdapter(this,list);
        recyclerView.setAdapter(adapter);
    }
    private void onLoadList(){
        BaseHelper baseHelper = new BaseHelper(this);
        list =  baseHelper.getFavoritesArticles();
    }
}
