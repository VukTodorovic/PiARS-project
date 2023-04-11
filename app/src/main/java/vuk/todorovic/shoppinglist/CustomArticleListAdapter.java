package vuk.todorovic.shoppinglist;

import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
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

        CheckBox cbArticleDone = convertView.findViewById(R.id.cbArticleDone);
        cbArticleDone.setChecked(article.getDone());

        if(article.getDone()) {
            tvArticleName.setPaintFlags(tvArticleName.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG); // Anything OR 1 will be 1
        }
        else {
            tvArticleName.setPaintFlags(tvArticleName.getPaintFlags() & ~Paint.STRIKE_THRU_TEXT_FLAG); // !1 = 0 and anything AND 0 will be 0
        }

        // Set onclick listener to do striketrough
        cbArticleDone.setOnCheckedChangeListener((view_param, isChecked) -> {
            if(isChecked) {
                tvArticleName.setPaintFlags(tvArticleName.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG); // Bilo sta ili 1 bice 1
            }
            else {
                tvArticleName.setPaintFlags(tvArticleName.getPaintFlags() & ~Paint.STRIKE_THRU_TEXT_FLAG); // !1 = 0 a bilo sta i 0 dace 0
            }
        });

        // Delete article on long click event
        convertView.setOnLongClickListener(v -> {
            articles.remove(position);
            notifyDataSetChanged();
            return true;
        });


        return convertView;


    }
}
