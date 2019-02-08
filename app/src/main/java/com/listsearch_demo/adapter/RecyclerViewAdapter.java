package com.listsearch_demo.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.listsearch_demo.R;
import com.listsearch_demo.helper.FilterType;
import com.listsearch_demo.helper.UserModel;

import java.util.ArrayList;
import java.util.Locale;

/**
 * Created by sonu on 19/09/16.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.RecyclerViewHolder> {

    static class RecyclerViewHolder extends RecyclerView.ViewHolder {

        private TextView name, number, emailID;

        RecyclerViewHolder(View view) {
            super(view);

            name = (TextView) view.findViewById(R.id.name_label);
            number = (TextView) view.findViewById(R.id.number_label);
            emailID = (TextView) view.findViewById(R.id.email_label);
        }

    }

    private ArrayList<UserModel> arrayList;
    private ArrayList<UserModel> filterArrayList;//duplicate list for filtering
    private Context context;


    public RecyclerViewAdapter(Context context, ArrayList<UserModel> arrayList) {
        this.arrayList = arrayList;
        this.context = context;

        this.filterArrayList = new ArrayList<>();//initiate filter list
        this.filterArrayList.addAll(arrayList);//add all items of array list to filter list
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_custom_row_layout, viewGroup, false);
        return new RecyclerViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final RecyclerViewHolder holder, final int i) {

        UserModel model = arrayList.get(i);
        holder.name.setText(model.getName());
        holder.number.setText(model.getNumber());
        holder.emailID.setText(model.getEmailID());
    }


    @Override
    public int getItemCount() {
        return (null != arrayList ? arrayList.size() : 0);
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