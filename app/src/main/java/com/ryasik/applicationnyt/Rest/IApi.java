package com.ryasik.applicationnyt.Rest;

import com.ryasik.applicationnyt.Model.ApiResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

// https://api.nytimes.com/svc/mostpopular/v2/
public interface IApi {
    @GET("{most}/30.json")
    Call<ApiResponse> getData(@Path("most") String most,
                              @Query("api-key") String api_key);
}
