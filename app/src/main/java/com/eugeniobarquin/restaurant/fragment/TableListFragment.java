package com.eugeniobarquin.restaurant.fragment;


import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.eugeniobarquin.restaurant.R;
import com.eugeniobarquin.restaurant.model.Table;

import java.util.LinkedList;


public class TableListFragment extends Fragment {
    private static String ARG_TABLE = "ARG_TABLE";

    protected LinkedList<Table> mTables;
    protected OnTableSelectedListener mOnTableSelectedListener;

    public static TableListFragment newInstance(LinkedList<Table> tables) {
        TableListFragment fragment = new TableListFragment();

        Bundle arguments = new Bundle();
        arguments.putSerializable(ARG_TABLE, tables);
        fragment.setArguments(arguments);

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Get table list
        if (getArguments() != null) {
            mTables = (LinkedList<Table>) getArguments().getSerializable(ARG_TABLE);
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

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (mOnTableSelectedListener != null) {
                    Table selectedTable = mTables.get(position);
                    mOnTableSelectedListener.onTableSelected(selectedTable, position);
                }
            }
        });


        return root;
    }

    @Override
    public void onAttachFragment(Fragment childFragment) {
        super.onAttachFragment(childFragment);
        if (getActivity()instanceof OnTableSelectedListener) {
            mOnTableSelectedListener = (OnTableSelectedListener) getActivity();
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (getActivity()instanceof OnTableSelectedListener) {
            mOnTableSelectedListener = (OnTableSelectedListener) getActivity();
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();

        mOnTableSelectedListener = null;
    }

    public interface OnTableSelectedListener {
        void onTableSelected(Table table, int position);
    }

}
