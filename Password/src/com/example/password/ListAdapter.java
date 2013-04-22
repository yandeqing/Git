package com.example.password;

import android.content.Context;
import com.password.R;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ListAdapter extends ArrayAdapter<String> {

    private String[] files;
    private Context context;

    /**
     * @param context
     * @param textViewResourceId
     * @param objects
     */
    public ListAdapter(Context context, int textViewResourceId,String[] files) {
        super(context, textViewResourceId, files);
        this.files = files;
        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        if (v == null) {
            LayoutInflater vi = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = vi.inflate(R.layout.list_row_menu, null);
        }
        String file = files[position];
        if (file != null) {
        	TextView name = (TextView) v.findViewById(R.id.file_list_name);
        	if (name != null) {
            	name.setText(file);
            }
        }

        return v;
    }
}