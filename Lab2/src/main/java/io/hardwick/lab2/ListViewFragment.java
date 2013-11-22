package io.hardwick.lab2;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by matthewhardwick on 11/20/13.
 */
public class ListViewFragment extends Fragment implements AdapterView.OnItemClickListener {
    List<String> items;
    ListView listView;
    ArrayAdapter<String> adapter;
    Context context;
    OnDetailView mListener;

    public ListViewFragment(List<String> items) {
        this.items = items;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        context = activity;
        if (activity instanceof OnDetailView) {
            mListener = (OnDetailView) activity;
        } else {
            throw new ClassCastException(activity.toString() + " is lame!");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.list_view_fragment_layout, container, false);

        listView = (ListView) view.findViewById(R.id.listView);
        adapter = new ArrayAdapter<String>(context, android.R.layout.simple_list_item_1, items);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);
        return view;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        mListener.onDetailViewUpdate(position);
    }

    public interface OnDetailView {
        public void onDetailViewUpdate(int position);
    }
}