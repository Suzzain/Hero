package np.com.heroes;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import api.HeroAPI;
import model.Heroes;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PostActivity extends AppCompatActivity {
private final static String BaseUrl = "10.0.2.2:3000/";
private EditText etName, etdesc;
Button btnAdd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);

        etName = findViewById(R.id.etName);
        etdesc = findViewById(R.id.etDesc);
        btnAdd = findViewById(R.id.btnAdd);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Register();
            }
        });
    }

    private void Register(){
        String name = etName.getText().toString();
        String desc = etdesc.getText().toString();

        Heroes heroes = new Heroes(name, desc);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BaseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        HeroAPI heroAPI = retrofit.create(HeroAPI.class);

        Call<Void> voidCall = heroAPI.registerHero(heroes);

        voidCall.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                Toast.makeText(PostActivity.this, "Hero is registered!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(PostActivity.this, "Error " + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }
}
