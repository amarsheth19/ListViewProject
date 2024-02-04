package com.example.customadapterproject;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class CustomAdapter extends ArrayAdapter<Athletes> {

    Context mainActivityContext;
    int xml;
    List<Athletes> AthleteList;


    public CustomAdapter(@NonNull Context context, int resource, @NonNull List<Athletes> objects) {
        super(context, resource, objects);
        mainActivityContext = context;
        xml = resource;
        AthleteList = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View myCustomView;



        LayoutInflater layoutInflater = (LayoutInflater) mainActivityContext.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        myCustomView = layoutInflater.inflate(xml,null);
        ImageView team = myCustomView.findViewById(R.id.id_TeamImage);
        TextView name = myCustomView.findViewById(R.id.id_personName);
        Button delete = myCustomView.findViewById(R.id.id_deleteButton);
        team.setImageResource((AthleteList.get(position).getImage()));
        name.setText(AthleteList.get(position).getName());



        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AthleteList.remove(position);
                MainActivity.listView.invalidateViews();
                if(AthleteList.size()==0){
                    MainActivity.DraftYearTextView.setText("");
                    MainActivity.ageTextView.setText("");
                    MainActivity.everythingRemoved.setText("All Items Have Been Removed");
                    if(MainActivity.descriptionTextView != null)
                        MainActivity.descriptionTextView.setText("");
                }

            }
        });

        MainActivity.listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //if()
                MainActivity.DraftYearTextView.setText("Year Drafted: " + AthleteList.get(position).getDraftYear());
                MainActivity.ageTextView.setText("Age: " + AthleteList.get(position).getAge());
                MainActivity.desc = AthleteList.get(position).getDescription();
                if(MainActivity.descriptionTextView != null)
                    MainActivity.descriptionTextView.setText(AthleteList.get(position).getDescription());
            }
        });


        return myCustomView;
    }



}

