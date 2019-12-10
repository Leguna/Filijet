package com.arksana.filijet;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import com.arksana.filijet.main.SectionsPagerAdapter;
import com.google.android.material.tabs.TabLayout;


public class MainActivity extends AppCompatActivity implements Toolbar.OnMenuItemClickListener {

    public MainFragment[] fragments = new MainFragment[3];
    public Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
        final ViewPager viewPager = findViewById(R.id.view_pager);

        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);

        viewPager.setOffscreenPageLimit(3);
        viewPager.setAdapter(sectionsPagerAdapter);

        toolbar = findViewById(R.id.toolbar);
        toolbar.setOnMenuItemClickListener(this);

    }


    @Override
    public boolean onMenuItemClick(MenuItem item) {

        if (item.getItemId() == R.id.action_tentangku) {
            Intent moveIntent = new Intent(getApplicationContext(), AboutMe.class);
            startActivity(moveIntent);
        } else if (item.getItemId() == R.id.action_refresh) {
            for (MainFragment fragment : fragments) fragment.refreshData();
        } else if (item.getItemId() == R.id.action_keluar) {
            onBackPressed();
        }
        return true;
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.AlertDialogStyle);

        builder.setMessage(getString(R.string.dialogexit))
                .setPositiveButton(getString(R.string.Yes), (dialog, id) -> finishAffinity())
                .setNegativeButton(getString(R.string.No), (dialog, id) -> dialog.cancel());
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

}
