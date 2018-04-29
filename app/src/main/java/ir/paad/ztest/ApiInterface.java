package ir.paad.ztest;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiInterface {

    @GET("hi/{name}")
    Call<Model2> getUsers(@Path("name") String name);
}
