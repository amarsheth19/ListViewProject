package com.example.customadapterproject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    static ListView listView;
    static TextView DraftYearTextView;
    static TextView ageTextView;
    static TextView descriptionTextView;
    static TextView everythingRemoved;
    static ArrayList<Athletes> athletes;
    static String desc;

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList("athletes", athletes);
        outState.putCharSequence("age", ageTextView.getText());
        outState.putCharSequence("draftYear", DraftYearTextView.getText());
        outState.putCharSequence("description", desc);
        outState.putCharSequence("everythingGone", everythingRemoved.getText());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            setContentView(R.layout.activity_main);
            listView = findViewById(R.id.id_listViewVert);
            DraftYearTextView = findViewById(R.id.id_athleteDraftYearVert);
            ageTextView = findViewById(R.id.id_athleteAgeVert);
            everythingRemoved = findViewById(R.id.id_everythingRemovedVert);
        }
        else{
            setContentView(R.layout.horizontal_layout);
            listView = findViewById(R.id.id_listViewHor);
            DraftYearTextView = findViewById(R.id.id_athleteDraftYearHor);
            ageTextView = findViewById(R.id.id_athleteAgeHor);
            descriptionTextView = findViewById(R.id.id_athleteDescriptionHor);
            everythingRemoved = findViewById(R.id.id_everythingRemovedHor);
            descriptionTextView.setText("");
        }

        DraftYearTextView.setText("");
        ageTextView.setText("");
        everythingRemoved.setText("");
        athletes = new ArrayList<Athletes>();

        if(savedInstanceState==null) {
            athletes.add(new Athletes("Nick Chubb", 26, R.drawable.brownslogo, 2018, "Nick Chubb is now in his 5th season in the NFL. Since his rookie season, he has proven to be a top running back and is now the face of the Browns. He has been selected to 3 pro bowls and 3 first team all-pro."));
            athletes.add(new Athletes("Saquon Barkley", 25, R.drawable.giants, 2018, "Saquon Barkley is now in his 5th season in the NFL. He was selected to the pro bowl, the first team all-pro, and won Offensive Rookie of the Year in 2018 and is making a Comeback Player of the Year campaign this year."));
            athletes.add(new Athletes("Patrick Mahomes", 27, R.drawable.chiefslogo, 2017, "Patrick Mahomes is now in his 6th season in the NFL. He has been selected to 4 pro bowls and 2 all-pro teams. He has also won a MVP, OPOY, and Superbowl MVP, and is making another MVP campaign this year."));
            athletes.add(new Athletes("Aaron Donald", 31, R.drawable.ramslogo, 2014, "Aaron Donald is now in his 9th season in the NFl. He has won DROY once and DPOY 3 times. He was selected to 7 first team all-pro teams and 8 pro bowls. He is widely considered one of the best defensive players of all time."));
            athletes.add(new Athletes("Josh Allen", 26, R.drawable.billslogo, 2018, "Josh Allen is now in his 5th season in the NFL. He has only been selected to one second team all-pro and one pro bowl. However, he is one of the best QBs in the league right now and is making an MVP campaign this year."));
            athletes.add(new Athletes("Tyreek Hill", 28, R.drawable.dolphinslogo, 2016, "Tyreek Hill changed teams over the offseason from the Chiefs to Dolphins and was doubted because many thought that his old QB made him good. However, he proved that to be wrong as he is on an OPOY campaign and is arguably the best WR right now"));
            athletes.add(new Athletes("Tom Brady", 45, R.drawable.bucslogo, 2000, "Tom Brady is now in his 23rd season in the NFl and is still playing at a high level. His detailed resume of awards and Superbowl rings give him a strong case for being the greatest football player of all time"));
        }
        else{
            CharSequence savedAge = savedInstanceState.getCharSequence("age");
            CharSequence savedDraftYear = savedInstanceState.getCharSequence("draftYear");
            CharSequence savedDescription = savedInstanceState.getCharSequence("description");
            CharSequence savedeverythingGone = savedInstanceState.getCharSequence("everythingGone");
            ageTextView.setText(savedAge);
            DraftYearTextView.setText(savedDraftYear);
            everythingRemoved.setText(savedeverythingGone);
            if(everythingRemoved.getText().equals("All Items Have Been Removed")){
                if(descriptionTextView!=null)
                    descriptionTextView.setText("");
            }
            else{
                if(descriptionTextView!=null)
                    descriptionTextView.setText(savedDescription);
            }


            athletes = savedInstanceState.getParcelableArrayList("athletes");
        }
        CustomAdapter customAdapter = new CustomAdapter(this,R.layout.vertical_layout,athletes);
        listView.setAdapter(customAdapter);

        listView.setClickable(true);






    }

/*
    public class CustomAdapter extends ArrayAdapter<Athletes>{

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
                    listView.invalidateViews();
                    if(AthleteList.size()==0){
                        DraftYearTextView.setText("");
                        ageTextView.setText("");
                        everythingRemoved.setText("All Items Have Been Removed");
                        if(descriptionTextView != null)
                            descriptionTextView.setText("");
                    }

                }
            });

            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    //if()
                    DraftYearTextView.setText("Year Drafted: " + AthleteList.get(position).getDraftYear());
                    ageTextView.setText("Age: " + AthleteList.get(position).getAge());
                    desc = AthleteList.get(position).getDescription();
                    if(descriptionTextView != null) {
                        descriptionTextView.setText(AthleteList.get(position).getDescription());
                    }
                }
            });

            return myCustomView;
        }



    }*/

}