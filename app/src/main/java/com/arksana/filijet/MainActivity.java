package com.arksana.filijet;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import com.arksana.filijet.main.MainFragment;
import com.arksana.filijet.main.SectionsPagerAdapter;
import com.google.android.material.tabs.TabLayout;


public class MainActivity extends AppCompatActivity implements Toolbar.OnMenuItemClickListener {

    public static int mode = R.id.action_card;
    public MainFragment[] fragments = new MainFragment[2];
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


    public void setMode(int selectedMode, MainFragment fragment) {
        if (selectedMode == R.id.action_list) {
            hideMenu(selectedMode);
            fragment.showRecyclerList();
        } else if (selectedMode == R.id.action_card) {
            hideMenu(selectedMode);
            fragment.showRecyclerCardView();
        } else if (selectedMode == R.id.action_grid) {
            hideMenu(selectedMode);
            fragment.showRecyclerGrid();
        }
    }

    public void hideMenu(int selectedMode) {
        MainActivity.mode = selectedMode;
        int[] daftarmenu = {R.id.action_list, R.id.action_card, R.id.action_grid};
        toolbar.getMenu().findItem(mode).setVisible(false);
        for (int i : daftarmenu) {
            if (i != mode)
                toolbar.getMenu().findItem(i).setVisible(true);
        }
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {

        for (MainFragment fragment : fragments)setMode(item.getItemId(), fragment);
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
                .setPositiveButton(getString(R.string.Yes), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        finishAffinity();

                    }
                })
                .setNegativeButton(getString(R.string.No), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

}
