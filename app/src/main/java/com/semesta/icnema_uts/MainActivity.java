package com.semesta.icnema_uts;


import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.appcompat.widget.SearchView;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private Spinner locationSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize TabLayout and ViewPager
        tabLayout = findViewById(R.id.tab_layout_id);
        viewPager = findViewById(R.id.viewpager_id);
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());

        // Add fragments to the ViewPager
        adapter.AddFragment(new FragmentBeranda(), "BERANDA");
        adapter.AddFragment(new FragmentBioskop(), "BIOSKOP");
        adapter.AddFragment(new FragmentTiket(), "TIKET");

        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);

        tabLayout.getTabAt(0).setIcon(R.drawable.ic_home);
        tabLayout.getTabAt(1).setIcon(R.drawable.ic_bioskop);
        tabLayout.getTabAt(2).setIcon(R.drawable.ic_tiket);

        // Set up SearchView listener
        SearchView searchView = findViewById(R.id.search_view);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                FragmentBioskop fragmentBioskop = (FragmentBioskop) adapter.getItem(1);
                fragmentBioskop.searchMovies(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                FragmentBioskop fragmentBioskop = (FragmentBioskop) adapter.getItem(1);
                if (newText.isEmpty()) {
                    fragmentBioskop.fetchTopMovies();
                }
                return false;
            }
        });
    }
}