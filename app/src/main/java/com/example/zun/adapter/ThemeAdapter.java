package com.example.zun.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.zun.R;
import com.example.zun.ThemesShowActivity;
import com.example.zun.domain.Theme;

import java.util.List;

public class ThemeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
 private LayoutInflater inflater;
 private List<Theme> themeList;
 private Context context_adapter;

    public ThemeAdapter(LayoutInflater inflater, List<Theme> themeList, Context context_adapter) {
        this.inflater = inflater;
        this.themeList = themeList;
        this.context_adapter = context_adapter;
    }
    public  class  MyViewHolder extends RecyclerView.ViewHolder{
        private  final TextView tvName,tvUser,tvComment;
        private final LinearLayout ll_item;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            ll_item = itemView.findViewById(R.id.ll_item);
            tvName = itemView.findViewById(R.id.tv_name);
            tvUser = itemView.findViewById(R.id.tv_user);
            tvComment = itemView.findViewById(R.id.tv_comment);
        }
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.list_theme_item, parent, false);
        return  new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder,
                                 @SuppressLint("RecycleView") int position) {
            Theme theme = themeList.get(position);
        ((MyViewHolder) holder).tvName.setText(theme.getName());
        ((MyViewHolder) holder).tvUser.setText(theme.getUser().getName());
        ((MyViewHolder) holder).tvComment.setText(theme.getCommentList().toString());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context_adapter, ThemesShowActivity.class);
                intent.putExtra("name", theme.getName());
                intent.putExtra("content", theme.getContent());
                context_adapter.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {

        return themeList.size();
    }
}
