package com.ryasik.applicationnyt.Ui;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.ryasik.applicationnyt.Adapters.RecyclerArticlesAdapter;
import com.ryasik.applicationnyt.Model.ApiResponse;
import com.ryasik.applicationnyt.Model.Article;
import com.ryasik.applicationnyt.Rest.ApiClient;
import com.ryasik.applicationnyt.Rest.IApi;
import com.ryasik.applicationnytexample.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MostFragment extends Fragment{

    private IApi apiService;
    private List<Article> listArticle = new ArrayList<Article>();
    private ApiResponse apiResponse;
    private RecyclerArticlesAdapter adapter;


    private static final String TAG = MostFragment.class.getSimpleName();
    private View view;

    private String title;

    private  RecyclerView recyclerView;

    public MostFragment() {
    }

    public MostFragment(String title) {
        this.title = title;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.list_fragment, container, false);
        apiResponse = new ApiResponse();
        recyclerView = (RecyclerView) view
                .findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        adapter = new RecyclerArticlesAdapter(getContext(),listArticle );
        recyclerView.setAdapter(adapter);
        Log.d(TAG, " on Create Fragment");
        return view;


    }

    public void loadDataSources(String most){
        apiService = ApiClient.getClient().create(IApi.class);
        final ProgressDialog dialog = new ProgressDialog(getContext());
        dialog.setMessage("Loading...");
        dialog.show();

        Call<ApiResponse> api = apiService.getData("emailed", ApiClient.API_KEY);
        api.enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                if (response.isSuccessful()){
                     apiResponse = response.body();

                    if (apiResponse.getStatus().equals("OK")){
                        listArticle = apiResponse.getArticles();
                        adapter.setListArticles(listArticle);
                    }
                } else {
                    Toast.makeText(getContext(), "Failed", Toast.LENGTH_SHORT).show();
                }
                dialog.dismiss();
            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {
                Toast.makeText(getContext(), "Connection Problem", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });
    }}



