package ir.paad.ztest.utils;

import java.util.List;

import ir.paad.ztest.models.GroupModel;
import ir.paad.ztest.models.Model2;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiInterface {

    @GET("hi/{name}")
    Call<Model2> getUsers(@Path("name") String name);

    @GET("getMainGroupList/")
    Call<List<GroupModel>> getGroupCount();


}
