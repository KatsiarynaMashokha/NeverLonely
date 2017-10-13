package com.epicodus.neverlonely;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    @Bind(R.id.title_text_view) TextView mTitleTextView;
    @Bind(R.id.facebook_login_button) Button mFacebookLoginButton;
    @Bind(R.id.email_login_button) Button mEmailLoginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        // Create a font
        Typeface grandHotelFont = Typeface.createFromAsset(getAssets(), "fonts/grandhotel.ttf");
        mTitleTextView.setTypeface(grandHotelFont);

        mFacebookLoginButton.setOnClickListener(this);
        mEmailLoginButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent mainIntent = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(mainIntent);
    }
}
