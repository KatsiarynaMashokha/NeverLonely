package com.epicodus.neverlonely;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity {
    @Bind(R.id.app_name_text_view) TextView mNameTextView;
    @OnClick(R.id.sign_up_text_view)
    public void signUp() {
        startActivity(new Intent(LoginActivity.this, CreateAccountActivity.class));
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        mNameTextView.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/grandhotel.ttf"));
    }
}
