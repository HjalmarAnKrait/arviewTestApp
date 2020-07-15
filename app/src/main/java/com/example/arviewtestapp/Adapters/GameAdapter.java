package com.example.arviewtestapp.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.arviewtestapp.POJO.GamePOJO;
import com.example.arviewtestapp.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class GameAdapter extends RecyclerView.Adapter<GameAdapter.ViewHolder>
{
    private LayoutInflater inflater;
    private List<GamePOJO> gameList;
    private Context context;

    public GameAdapter(Context context, List<GamePOJO> gameList) {
        this.gameList = gameList;
        this.inflater = LayoutInflater.from(context);
        this.context = context;
    }
    @Override
    public GameAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View view = inflater.inflate(R.layout.list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(GameAdapter.ViewHolder holder, int position)
    {
        GamePOJO game = gameList.get(position);
        holder.channels.setText("Channels: " + game.getChannels());
        holder.gameName.setText(game.getGameName());
        holder.viewers.setText("Viewers: " + game.getViewers());
        Picasso.with(context).load(game.getImageUrl()).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return gameList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        ImageView imageView;
        TextView gameName, viewers, channels;
        ViewHolder(View view)
        {
            super(view);
            imageView = view.findViewById(R.id.gameIcon);
            gameName = view.findViewById(R.id.gameName);
            viewers = view.findViewById(R.id.viewers);
            channels = view.findViewById(R.id.channels);
        }
    }
}
