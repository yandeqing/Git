package com.example.password;

import com.password.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class CategoryListAdapter extends ArrayAdapter<String> {

    private String[] files;
    private Context context;

    /**
     * @param context
     * @param textViewResourceId
     * @param objects
     */
    public CategoryListAdapter(Context context, int textViewResourceId,String[] files) {
        super(context, textViewResourceId, files);
        this.files = files;
        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        if (v == null) {
            LayoutInflater vi = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = vi.inflate(R.layout.category_list_row, null);
        }
        String file = files[position];
        if (file != null) {
        	TextView name = (TextView) v.findViewById(R.id.category_list_name);
        	if (name != null) {
            	name.setText(file);
            }
        }
        return v;
    }
}
