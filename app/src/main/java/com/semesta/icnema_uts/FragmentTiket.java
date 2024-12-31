package com.semesta.icnema_uts;

import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class FragmentTiket extends Fragment {
    private View view;
    private RecyclerView recyclerView;
    private TicketAdapter ticketAdapter;
    private List<Ticket> ticketList;

    public FragmentTiket() {}

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.tiket, container, false);

        // Initialize RecyclerView
        recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        // Load ticket data from database
        loadTicketData();

        // Set adapter
        ticketAdapter = new TicketAdapter(ticketList, getActivity());
        recyclerView.setAdapter(ticketAdapter);

        return view;
    }

    private void loadTicketData() {
        ticketList = new ArrayList<>();
        DatabaseHelper dbHelper = new DatabaseHelper(getContext());
        Cursor cursor = dbHelper.getReadableDatabase().query(
                DatabaseHelper.TABLE_NAME,
                null, null, null, null, null, null);

        if (cursor != null) {
            while (cursor.moveToNext()) {
                int titleIndex = cursor.getColumnIndex(DatabaseHelper.COLUMN_TITLE);
                int overviewIndex = cursor.getColumnIndex(DatabaseHelper.COLUMN_OVERVIEW);
                int releaseDateIndex = cursor.getColumnIndex(DatabaseHelper.COLUMN_RELEASE_DATE);
                int timeIndex = cursor.getColumnIndex(DatabaseHelper.COLUMN_TIME); // Add time index
                int seatsIndex = cursor.getColumnIndex("seats");
                int priceIndex = cursor.getColumnIndex("price");
                int imageUrlIndex = cursor.getColumnIndex("image_url");

                if (titleIndex >= 0 && overviewIndex >= 0 && releaseDateIndex >= 0 && timeIndex >= 0 && seatsIndex >= 0 && priceIndex >= 0 && imageUrlIndex >= 0) {
                    String title = cursor.getString(titleIndex);
                    String overview = cursor.getString(overviewIndex);
                    String releaseDate = cursor.getString(releaseDateIndex);
                    String time = cursor.getString(timeIndex); // Get time value
                    String seats = cursor.getString(seatsIndex);
                    int price = cursor.getInt(priceIndex);
                    String imageUrl = cursor.getString(imageUrlIndex);

                    Log.d("FragmentTiket", "Loaded image URL: " + imageUrl);

                    Ticket ticket = new Ticket(title, overview, releaseDate, time, seats, price, imageUrl); // Add time to ticket
                    ticketList.add(ticket);
                }
            }
            cursor.close();
        }
    }
}