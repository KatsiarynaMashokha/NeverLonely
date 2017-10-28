package com.epicodus.neverlonely.ui;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.epicodus.neverlonely.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class StartActivity extends AppCompatActivity {
    @Bind(R.id.title_text_view) TextView mTitleTextView;
    @OnClick(R.id.sign_up_button)
    public void signUp() {
        startActivity(new Intent(StartActivity.this, CreateAccountActivity.class));
    }
    @OnClick(R.id.email_login_button)
    public void logIn() {
        startActivity(new Intent(StartActivity.this, LoginActivity.class));
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start_activity);
        ButterKnife.bind(this);

        // Create a font
        Typeface grandHotelFont = Typeface.createFromAsset(getAssets(), "fonts/grandhotel.ttf");
        mTitleTextView.setTypeface(grandHotelFont);
    }
}
