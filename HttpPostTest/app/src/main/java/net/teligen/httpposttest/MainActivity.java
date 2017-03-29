package net.teligen.httpposttest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private  ApiService apiService;
    private EditText et;
    private Button bt;
    private  TextView tv;
    private String userId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        et = (EditText) findViewById(R.id.eT);
        bt = (Button) findViewById(R.id.bT);
        tv = (TextView) findViewById(R.id.tV);

        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userId = et.getText().toString();
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("http://www.teligen.net/meip/imController/")           //Base路径，与第二步的@GET路径相拼接
                        .addConverterFactory(GsonConverterFactory.create()) //设置返回数据转换器，第一步中配置的Gradle与之对应
                        .build();
                apiService = retrofit.create(ApiService.class);
                // map为第二步中的复杂参数，注意map必须符合接口请求规范，此处读者照搬可能读取不到需要的数据
                Map<String,String> map = new HashMap<>();
                map.put("userId", userId);
                apiService.readTrackList(map).enqueue(new Callback<TestEntity>() {
                    @Override
                    public void onResponse(Call<TestEntity> call, Response<TestEntity> response) {
                        TestEntity body = response.body();
                        Log.i("TAG","--Call:" + body.secretyKey);
                        tv.setText(body.secretyKey);
                    }

                    @Override
                    public void onFailure(Call<TestEntity> call, Throwable t) {

                    }
                });
            }

        });

    }
}
