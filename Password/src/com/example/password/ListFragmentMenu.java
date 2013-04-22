package com.example.password;

import android.app.ListFragment;
import com.password.R;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

public class ListFragmentMenu extends ListFragment {

	private String[] files={"Agregar una nueva cuenta","Consultar cuentas","Ajustes"};
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
			Intent intent = new Intent(getActivity(), AddPass.class);
			startActivity(intent);
			break;
		case 1:
			Intent intent2 = new Intent(getActivity(), ConsultPass.class);
			startActivity(intent2);
			break;
		case 2:
			Intent intent3 = new Intent(getActivity(), ShowSettings.class);
			startActivity(intent3);
			break;
		}
	}
}
