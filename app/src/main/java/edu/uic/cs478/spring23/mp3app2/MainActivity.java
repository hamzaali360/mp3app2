package edu.uic.cs478.spring23.mp3app2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    String ACTION_CITY_SELECTED = "edu.uic.cs478.spring23.mp3";
    String city;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        IntentFilter filter = new IntentFilter(ACTION_CITY_SELECTED);
        registerReceiver(citySelectedReceiver, filter);

        // Get the list view
        ListView listView = findViewById(R.id.placesList);

        // Set click listener for the list view
//        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
//                showAttractionDetails(getAttractions().get(position));
//            }
//        });

    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(citySelectedReceiver);
    }

    // Get the list of tourist attractions for the selected city
    private List<String> getAttractions() {
        switch (city) {
            case "Orlando":
                return Arrays.asList("Universal Studios", "Disney World", "SeaWorld");
            case "New York City":
                return Arrays.asList("Statue of Liberty", "Central Park", "Empire State Building");
            default:
                return Collections.emptyList();
        }
    }

    // Show the detailed information about the selected attraction
//    private void showAttractionDetails(String attraction) {
//        // Start the details activity
//        Intent intent = new Intent(this, DetailsActivity.class);
//        intent.putExtra("attraction", attraction);
//        startActivity(intent);
//    }

    // Broadcast receiver for the selected city
    private final BroadcastReceiver citySelectedReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals(ACTION_CITY_SELECTED)) {
                city = intent.getStringExtra("city");
                List<String> attractions = getAttractions();
                ArrayAdapter<String> adapter = new ArrayAdapter<>(context, android.R.layout.simple_list_item_1, attractions);
                ListView listView = findViewById(R.id.placesList);
                listView.setAdapter(adapter);
            }
        }
    };
}