package com.ryasik.applicationnyt.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.ryasik.applicationnyt.DataBase.BaseHelper;
import com.ryasik.applicationnyt.Model.Article;
import com.ryasik.applicationnyt.Ui.DetailsActivity;
import com.ryasik.applicationnytexample.R;

import java.util.List;

public class RecyclerArticlesAdapter extends RecyclerView.Adapter<RecyclerArticlesAdapter.ViewHolder>
{

    private Context context;
    private List<Article> listArticles;

    public RecyclerArticlesAdapter(Context context, List<Article> listArticles) {
        this.context = context;
        this.listArticles = listArticles;
    }

    public Context getContext() {
        return context;
    }



    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.item_row, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {

        final Article article = listArticles.get(position);
        holder.title.setText(article.getTitle());
        holder.author.setText(article.getAuthor());
//        holder.articleImage.setImageURI(article.);


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(getContext(), DetailsActivity.class);
                myIntent.putExtra("url", article.getUrl());
                context.startActivity(myIntent);
            }
        });
        holder.favoriteIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.favoriteIcon.setImageResource(R.drawable.ic_favorite);
                BaseHelper baseHelper = new BaseHelper(context);
                baseHelper.createArticle(article);

            }
        });
        holder.shareIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_TEXT, article.getUrl());
                try
                {
                    context.startActivity(Intent.createChooser(intent, "Article URL"));
                }
                catch (android.content.ActivityNotFoundException ex)
                {
                    Toast.makeText(context, "Some error", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return listArticles.size();
    }

    public void setListArticles(List<Article> listArticles){
        this.listArticles = listArticles;
        notifyDataSetChanged();
    }
    public static class ViewHolder extends RecyclerView.ViewHolder{

        private TextView title, author;
        private ImageView articleImage, favoriteIcon, shareIcon;

        private CardView cardView;
        private ProgressBar mProgress;

        public ViewHolder(View itemView) {
            super(itemView);

            title = (TextView) itemView.findViewById(R.id.title_tv_tag);
            author = (TextView) itemView.findViewById(R.id.author_tv);
            articleImage = (ImageView) itemView.findViewById(R.id.article_image);
            favoriteIcon = (ImageView) itemView.findViewById(R.id.article_favorite);
            shareIcon = (ImageView) itemView.findViewById(R.id.article_share);
            cardView = (CardView) itemView.findViewById(R.id.card_view);
        }
    }

}
