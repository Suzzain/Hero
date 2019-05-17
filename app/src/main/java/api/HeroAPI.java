package api;

import java.util.List;

import model.Heroes;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface HeroAPI {

    @POST("create")
    Call<Void> registerHero(@Body Heroes heroes);

    @GET("heroes")
    Call<List<Heroes>> getHero();


}
