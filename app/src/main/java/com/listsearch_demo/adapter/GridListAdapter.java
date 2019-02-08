package com.listsearch_demo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.listsearch_demo.R;
import com.listsearch_demo.helper.FilterType;
import com.listsearch_demo.helper.UserModel;

import java.util.ArrayList;
import java.util.Locale;

/**
 * Created by sonu on 08/02/17.
 */

public class GridListAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<UserModel> arrayList;
    private LayoutInflater inflater;
    private boolean isListView;
    private ArrayList<UserModel> filterArrayList;//duplicate list for filtering

    public GridListAdapter(Context context, ArrayList<UserModel> arrayList, boolean isListView) {
        this.context = context;
        this.arrayList = arrayList;
        this.isListView = isListView;
        inflater = LayoutInflater.from(context);

        this.filterArrayList = new ArrayList<>();//initiate filter list
        this.filterArrayList.addAll(arrayList);//add all items of array list to filter list
    }

    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object getItem(int i) {
        return arrayList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if (view == null) {
            viewHolder = new ViewHolder();

            //inflate the layout on basis of boolean
            if (isListView)
                view = inflater.inflate(R.layout.list_custom_row_layout, viewGroup, false);
            else
                view = inflater.inflate(R.layout.grid_custom_row_layout, viewGroup, false);

            viewHolder.name = (TextView) view.findViewById(R.id.name_label);
            viewHolder.number = (TextView) view.findViewById(R.id.number_label);
            viewHolder.emailID = (TextView) view.findViewById(R.id.email_label);

            view.setTag(viewHolder);
        } else
            viewHolder = (ViewHolder) view.getTag();

        UserModel model = arrayList.get(i);
        viewHolder.name.setText(model.getName());
        viewHolder.number.setText(model.getNumber());
        viewHolder.emailID.setText(model.getEmailID());

        return view;
    }


    private class ViewHolder {
        private TextView name, number, emailID;
    }

    // Filter Class to filter data
    public void filter(FilterType filterType, String charText, boolean isSearchWithPrefix) {

        //If Filter type is NAME and EMAIL then only do lowercase, else in case of NUMBER no need to do lowercase because of number format
        if (filterType == FilterType.NAME || filterType == FilterType.EMAIL)
            charText = charText.toLowerCase(Locale.getDefault());

        arrayList.clear();//Clear the main ArrayList

        //If search query is null or length is 0 then add all filterList items back to arrayList
        if (charText.length() == 0) {
            arrayList.addAll(filterArrayList);
        } else {

            //Else if search query is not null do a loop to all filterList items
            for (UserModel model : filterArrayList) {

                //Now check the type of search filter
                switch (filterType) {
                    case NAME:
                        if (isSearchWithPrefix) {
                            //if STARTS WITH radio button is selected then it will match the exact NAME which match with search query
                            if (model.getName().toLowerCase(Locale.getDefault()).startsWith(charText))
                                arrayList.add(model);
                        } else {
                            //if CONTAINS radio button is selected then it will match the NAME wherever it contains search query
                            if (model.getName().toLowerCase(Locale.getDefault()).contains(charText))
                                arrayList.add(model);
                        }

                        break;
                    case EMAIL:
                        if (isSearchWithPrefix) {
                            //if STARTS WITH radio button is selected then it will match the exact EMAIL which match with search query
                            if (model.getEmailID().toLowerCase(Locale.getDefault()).startsWith(charText))
                                arrayList.add(model);
                        } else {
                            //if CONTAINS radio button is selected then it will match the EMAIL wherever it contains search query
                            if (model.getEmailID().toLowerCase(Locale.getDefault()).contains(charText))
                                arrayList.add(model);
                        }

                        break;
                    case NUMBER:
                        if (isSearchWithPrefix) {
                            //if STARTS WITH radio button is selected then it will match the exact NUMBER which match with search query
                            if (model.getNumber().startsWith(charText))
                                arrayList.add(model);
                        } else {
                            //if CONTAINS radio button is selected then it will match the NUMBER wherever it contains search query
                            if (model.getNumber().contains(charText))
                                arrayList.add(model);
                        }

                        break;
                }

            }
        }
        notifyDataSetChanged();
    }


}
