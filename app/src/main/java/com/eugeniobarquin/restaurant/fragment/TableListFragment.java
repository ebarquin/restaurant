package com.eugeniobarquin.restaurant.fragment;


import android.app.Fragment;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.eugeniobarquin.restaurant.R;
import com.eugeniobarquin.restaurant.model.Table;

import java.util.LinkedList;


public class TableListFragment extends Fragment {
    private static String ARG_TABLES = "tables";

    protected LinkedList<Table> mTables;

    public static TableListFragment newInstance(LinkedList<Table> tables) {
        TableListFragment fragment = new TableListFragment();

        Bundle arguments = new Bundle();
        arguments.putSerializable(ARG_TABLES, tables);
        fragment.setArguments(arguments);

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Get table list
        if (getArguments() != null) {
            mTables = (LinkedList<Table>) getArguments().getSerializable(ARG_TABLES);
        }


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_table_list, container, false);



        //Access to view
        ListView list = (ListView) root.findViewById(R.id.table_list);

        //Create adapter with table list
        ArrayAdapter<Table> adapter = new ArrayAdapter<Table>(getActivity(),android.R.layout.simple_list_item_1, mTables);

        //Give adapter to ListView to populate the list
        list.setAdapter(adapter);

        return root;
    }

}
