package com.example.retrofit_ljs20518;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.retrofit_ljs20518.Data.Article;

import java.util.List;

public class ArticleAdapter extends RecyclerView.Adapter<ArticleAdapter.ViewHolder> {

    List<Article> list;
    Context context;

    public ArticleAdapter(List<Article> list, Context context) {
        super();
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.row_article, parent, false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ArticleAdapter.ViewHolder holder, int position) {
        Article article = list.get(position);

        Glide
                .with(context)
                .load(article.author.image)
                .into(holder.articleImage);

        holder.articleNameText.setText(article.author.username);
        holder.articleTitleText.setText(article.title);
        holder.articleBodyText.setText(article.body);
        holder.articleLikeText.setText(String.valueOf(article.favoritesCount));
        holder.articleCreateDateText.setText(article.createdAt);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        ImageView articleImage;
        TextView articleNameText;
        TextView articleBodyText;
        TextView articleLikeText;
        TextView articleTitleText;
        TextView articleCreateDateText;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            articleImage = itemView.findViewById(R.id.articleImage);
            articleNameText = itemView.findViewById(R.id.articleNameText);
            articleBodyText = itemView.findViewById(R.id.articleBody);
            articleLikeText = itemView.findViewById(R.id.articleLikeText);
            articleTitleText = itemView.findViewById(R.id.articleTitle);
            articleCreateDateText = itemView.findViewById(R.id.articleCreateDateTv);
        }
    }
}
