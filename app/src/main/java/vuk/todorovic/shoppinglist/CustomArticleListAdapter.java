package vuk.todorovic.shoppinglist;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomArticleListAdapter extends BaseAdapter {
    private ArrayList<Article> articles;
    private Context context;

    public CustomArticleListAdapter(ArrayList<Article> articles, Context context) {
        this.articles = articles;
        this.context = context;
    }

    @Override
    public int getCount() {
        return articles.size();
    }

    @Override
    public Object getItem(int position) {
        return articles.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.article_item, parent, false);
        }

        Article article = articles.get(position);

        TextView tvArticleName = convertView.findViewById(R.id.tvArticleName);
        tvArticleName.setText(article.getName());

        TextView cbArticleDone = convertView.findViewById(R.id.cbArticleDone);
        cbArticleDone.setSelected(article.getDone());
        // Postaviti onclick listener da precrta tekst

        return convertView;
    }
}
