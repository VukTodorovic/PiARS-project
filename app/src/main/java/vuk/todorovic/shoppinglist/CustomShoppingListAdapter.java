package vuk.todorovic.shoppinglist;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class CustomShoppingListAdapter extends BaseAdapter {
    private ArrayList<ShoppingList> shoppingLists;
    private Context context;

    public CustomShoppingListAdapter(ArrayList<ShoppingList> shoppingLists, Context context) {
        this.shoppingLists = shoppingLists;
        this.context = context;
    }

    @Override
    public int getCount() {
        return shoppingLists.size();
    }

    @Override
    public Object getItem(int position) {
        return shoppingLists.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.shopping_list_item, parent, false);
        }

        ShoppingList shoppingList = shoppingLists.get(position);

        TextView tvListTitle = convertView.findViewById(R.id.tvListTitle);
        tvListTitle.setText(shoppingList.getTitle());

        TextView tvListShared = convertView.findViewById(R.id.tvListShared);
        String sharedText = shoppingList.getShared() ? "True" : "False";
        tvListShared.setText(sharedText);

        // Open ShowListActivity on click
        convertView.setOnClickListener(view -> {
            Intent intent = new Intent(context, ShowListActivity.class);
            intent.putExtra("shoppingList", shoppingList);
            context.startActivity(intent);
        });

        // Delete shopping list on long click event
        convertView.setOnLongClickListener(v -> {
            shoppingLists.remove(position);
            notifyDataSetChanged();
            return true;
        });

        return convertView;
    }
}
