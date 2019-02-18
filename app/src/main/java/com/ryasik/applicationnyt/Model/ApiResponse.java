package com.ryasik.applicationnyt.Model;

import java.util.List;

public class ApiResponse {
    private List<Article> results;
    private String status;

    public List<Article> getArticles() {
        return results;
    }

    public String getStatus() {
        return status;
    }
}
