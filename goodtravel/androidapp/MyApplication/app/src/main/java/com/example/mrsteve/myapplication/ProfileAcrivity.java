package com.example.mrsteve.myapplication;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.hitomi.cmlibrary.CircleMenu;
import com.hitomi.cmlibrary.OnMenuSelectedListener;
import com.hitomi.cmlibrary.OnMenuStatusChangeListener;

public class ProfileAcrivity extends AppCompatActivity{

    private FirebaseAuth firebaseAuth;

    private TextView textViewUserEmail;

    private CircleMenu circleMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_acrivity);

        circleMenu = (CircleMenu) findViewById(R.id.circle_menu);

        circleMenu.setMainMenu(Color.parseColor("#ECA842"), R.drawable.icmenu, R.drawable.icon_cancel);
        circleMenu.addSubMenu(Color.parseColor("#258CFF"), R.drawable.icprof)
                .addSubMenu(Color.parseColor("#30A400"), R.drawable.icon_mess)
                .addSubMenu(Color.parseColor("#FF4B32"), R.drawable.icset)
                .addSubMenu(Color.parseColor("#8A39FF"), R.drawable.icexit)
                .addSubMenu(Color.parseColor("#8A39FF"), R.drawable.ictravel);

        circleMenu.setOnMenuSelectedListener(new OnMenuSelectedListener() {

             @Override
             public void onMenuSelected(int index) {
                 switch (index) {
                     case 0:                          Toast.makeText(ProfileAcrivity.this, "Profile in developing", Toast.LENGTH_SHORT).show();
                         break;
                     case 1:
                         Toast.makeText(ProfileAcrivity.this, "Message in developing", Toast.LENGTH_SHORT).show();
                         break;
                     case 2:
                         Toast.makeText(ProfileAcrivity.this, "settings in developing", Toast.LENGTH_SHORT).show();
                         break;
                     case 3:
                         firebaseAuth.signOut();
                         finish();
                         startActivity(new Intent(ProfileAcrivity.this, MainActivity.class));
                         break;
                     case 4:
                         finish();
                         startActivity(new Intent(ProfileAcrivity.this, CountryActivity.class));
                         break;
                 }
             }
         }

        );

        circleMenu.setOnMenuStatusChangeListener(new OnMenuStatusChangeListener() {

                 @Override
                 public void onMenuOpened() {
                     Toast.makeText(ProfileAcrivity.this, "Menu Opend", Toast.LENGTH_SHORT).show();
                 }

                 @Override
                 public void onMenuClosed() {
                     Toast.makeText(ProfileAcrivity.this, "Menu Closed", Toast.LENGTH_SHORT).show();
                 }
             }
        );

        firebaseAuth = FirebaseAuth.getInstance();

        if (firebaseAuth.getCurrentUser() == null) {

            finish();
            startActivity(new Intent(this, MainActivity.class));

        }

        FirebaseUser user = firebaseAuth.getCurrentUser();

        textViewUserEmail = (TextView) findViewById(R.id.textViewUserEmail);
        textViewUserEmail.setText(user.getEmail());


    }

    @Override
    public void onBackPressed() {
        if (circleMenu.isOpened())
            circleMenu.closeMenu();
        else
            finish();
    }

}
