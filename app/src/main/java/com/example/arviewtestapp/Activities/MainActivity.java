package com.example.arviewtestapp.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.arviewtestapp.Adapters.GameAdapter;
import com.example.arviewtestapp.Networking.Api;
import com.example.arviewtestapp.POJO.GamePOJO;
import com.example.arviewtestapp.R;
import com.google.gson.JsonElement;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity
{
    private JsonElement jsonElement;
    private RecyclerView recyclerView;
    private int maxGameAmount = 10;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recycle_view);
        addDivider();
        recyclerView.addOnScrollListener(listener);
        fillList();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        startActivity(new Intent(getApplicationContext(), FeedbackActivity.class));
        return true;
    }

    private void fillList()
    {
        String accept, key;
        accept = getResources().getString(R.string.accept);
        key = getResources().getString(R.string.api_key);
        Api.getInstance().getApi().getGameList(key, accept, maxGameAmount).enqueue(new Callback<JsonElement>()
        {
            @Override
            public void onResponse(Call<JsonElement> call, Response<JsonElement> response)
            {
                setJsonElement(response.body());
                GameAdapter adapter = new GameAdapter(getApplicationContext(),getGameList(jsonElement));
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<JsonElement> call, Throwable t)
            {
                Log.e("Fail", t.getMessage() + "unsuccess");
            }
        });

    }
    private void setJsonElement(JsonElement jsonElement)
    {
        this.jsonElement = jsonElement;
    }

    private ArrayList<GamePOJO> getGameList(JsonElement element)
    {
        ArrayList<GamePOJO> gameList = new ArrayList();
        String gameName, url;
        int viewers, channels;
        int counter = 0;

        try
        {
            JSONObject jsonObject = new JSONObject(jsonElement.toString());
            JSONArray jsonArray = jsonObject.getJSONArray("top");
            while (!jsonArray.getJSONObject(counter).toString().isEmpty())
            {
                jsonObject = jsonArray.getJSONObject(counter);
                viewers = jsonObject.getInt("viewers");
                channels = jsonObject.getInt("channels");
                jsonObject = jsonObject.getJSONObject("game");
                gameName = jsonObject.getString("name");
                jsonObject = jsonObject.getJSONObject("box");
                url = jsonObject.getString("large");

                gameList.add(new GamePOJO(viewers,channels, gameName, url));

                counter++;
            }

        }
        catch (JSONException e) {
        }
        return gameList;
    }

    private void addDivider()
    {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),
                linearLayoutManager.getOrientation());
        dividerItemDecoration.setDrawable(getDrawable(R.drawable.divider));
        recyclerView.addItemDecoration(dividerItemDecoration);
    }

    RecyclerView.OnScrollListener listener = new RecyclerView.OnScrollListener()
    {
        @Override
        public void onScrollStateChanged(final RecyclerView recyclerView, int newState) {
            super.onScrollStateChanged(recyclerView, newState);

            if (!recyclerView.canScrollVertically(1)) {
                maxGameAmount++;
                fillList();
            }
        }
    };

}
