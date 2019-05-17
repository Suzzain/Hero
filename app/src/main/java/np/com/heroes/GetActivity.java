package np.com.heroes;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.List;

import api.HeroAPI;
import model.Heroes;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class GetActivity extends AppCompatActivity {
private TextView tvData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get);

        tvData = findViewById(R.id.tvData);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("10.0.2.2:3000/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        HeroAPI heroAPI = retrofit.create(HeroAPI.class);

        Call<List<Heroes>> listCall = heroAPI.getHero();

        listCall.enqueue(new Callback<List<Heroes>>() {
            @Override
            public void onResponse(Call<List<Heroes>> call, Response<List<Heroes>> response) {
                if (!response.isSuccessful()){
                    tvData.setText("Code : " + response.code());
                    return;
                }

                List<Heroes> heroesList = response.body();
                for(Heroes heroes : heroesList){
                    String content = "";
                    content += "Name : " + heroes.getName() + "\n";
                    content += "Description : " + heroes.getDesc() + "\n";

                    tvData.append(content);
                }
            }

            @Override
            public void onFailure(Call<List<Heroes>> call, Throwable t) {
                tvData.setText("Error : " +

                        t.getMessage());
            }
        });
    }
}
