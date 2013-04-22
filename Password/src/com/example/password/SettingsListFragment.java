package com.example.password;

import android.os.Bundle;

import com.password.Preferences;
import com.password.R;
import android.app.Activity;
import android.app.ListFragment;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

public class SettingsListFragment extends ListFragment {

	private String[] files={"Agregar nueva categor’a","Elegir contrase–a principal"};
	View mContentView = null;
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		this.setListAdapter(new ListAdapter(getActivity(), R.layout.list_row_menu, files));
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		mContentView = inflater.inflate(R.layout.activity_list_fragment_menu, null);
		return mContentView;
	}
	
	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		switch (position) {
		case 0:
			Intent intent = new Intent(getActivity(), ActivitySettings.class);
			startActivity(intent);
			break;
		case 1:
			Intent intent2 = new Intent(getActivity(), Preferences.class);
			startActivity(intent2);
			break;
		}
	}
}
