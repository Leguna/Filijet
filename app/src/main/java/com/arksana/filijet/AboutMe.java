package com.arksana.filijet;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Objects;
import java.util.Random;

public class AboutMe extends AppCompatActivity implements View.OnClickListener {

    int idSelected = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_me);
        setActionBarTitle();
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        Button btnCallMeWA = findViewById(R.id.btn_callmewa);
        btnCallMeWA.setOnClickListener(this);
        Button btnCallMe = findViewById(R.id.btn_callme);
        btnCallMe.setOnClickListener(this);
        Button btnFacebook = findViewById(R.id.btn_callmefacebook);
        btnFacebook.setOnClickListener(this);
        ImageView myPhoto = findViewById(R.id.photo);
        myPhoto.setOnClickListener(this);

    }

    private void setActionBarTitle() {
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(getString(R.string.aboutme));
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_callmewa:
                callMeWA();
                break;
            case R.id.btn_callme:
                callMe();
                break;
            case R.id.btn_callmefacebook:
                callMeFacebook();
                break;
            case R.id.photo:
                changePhotoAndName();
                break;
        }
    }

    private void changePhotoAndName() {
        int[] myImageIdList = new int[]{R.drawable.avatar, R.drawable.avatar2};
        ImageView myPhoto = findViewById(R.id.photo);
        idSelected = new Random().nextInt(myImageIdList.length);
        myPhoto.setImageResource(myImageIdList[idSelected]);

    }

    private void callMeFacebook() {
        String url = "https://www.facebook.com/AhmadTuflihunBlaBla";
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url));
        startActivity(i);
    }

    private void callMe() {
        String phoneNumber = "+6285246654277";
        Intent dialPhoneIntent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + phoneNumber));
        startActivity(dialPhoneIntent);
    }

    private void callMeWA() {
        boolean isWhatsappInstalled = whatsappInstalledOrNot();
        if (isWhatsappInstalled) {
            Uri uri = Uri.parse("smsto:" + "+6285246654277");
            Intent sendIntent = new Intent(Intent.ACTION_SENDTO, uri);
            sendIntent.setPackage("com.whatsapp");
            startActivity(sendIntent);
        } else {
            Toast.makeText(this, "WhatsApp tidak terinstall",
                    Toast.LENGTH_SHORT).show();
            Uri uri = Uri.parse("market://details?id=com.whatsapp");
            Intent goToMarket = new Intent(Intent.ACTION_VIEW, uri);
            startActivity(goToMarket);
        }
    }

    private boolean whatsappInstalledOrNot() {
        PackageManager pm = getPackageManager();
        boolean app_installed = false;
        try {
            pm.getPackageInfo("com.whatsapp", PackageManager.GET_ACTIVITIES);
            app_installed = true;
        } catch (PackageManager.NameNotFoundException ignored) {
        }
        return app_installed;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            super.onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }
}
