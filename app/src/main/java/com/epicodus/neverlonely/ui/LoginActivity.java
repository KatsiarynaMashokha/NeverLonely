package com.epicodus.neverlonely.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.epicodus.neverlonely.Constants;
import com.epicodus.neverlonely.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mEditor;
    String email;
    private String recentlyUsedEmail;
    public static final String TAG = LoginActivity.class.getSimpleName();
    @Bind(R.id.app_name_text_view) TextView mNameTextView;
    @Bind(R.id.login_email_edit_text) EditText mLoginEmailEditText;
    @Bind(R.id.login_password_edit_text) EditText mLoginPasswordEditText;

    @OnClick(R.id.email_login_button)
    public void logInWithPassword() {
        email = mLoginEmailEditText.getText().toString().trim();
        String password = mLoginPasswordEditText.getText().toString().trim();
        if(email.equals("")) {
            mLoginEmailEditText.setError(getString(R.string.please_enter_email));
            return;
        }
        if(password.equals("")) {
            mLoginPasswordEditText.setError(getString(R.string.please_enter_password));
            return;
        }
        addToSharedPreferences(email);
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d(TAG, "signInWithEmail:onComplete:" + task.isSuccessful());
                        if(!task.isSuccessful()) {
                            Log.w(TAG, "signInWithEmail", task.getException());
                            Toast.makeText(LoginActivity.this, R.string.authentification_failed,
                                    Toast.LENGTH_SHORT).show();
                        } else {
                            addToSharedPreferences(email);
                        }
                    }
                });
    }
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

        mAuth = FirebaseAuth.getInstance();
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if(user != null) {
                    Intent intent = new Intent(LoginActivity.this, EventListActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                    finish();
                }
            }
        };

        mNameTextView.setTypeface(Typeface.createFromAsset(getAssets(), Constants.TITLE_FONT_NAME));
        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        mEditor = mSharedPreferences.edit();
        recentlyUsedEmail = mSharedPreferences.getString(Constants.PREFERENCE_EMAIL_KEY, null);
        if(recentlyUsedEmail != null) {
            mLoginEmailEditText.setText(recentlyUsedEmail);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if(mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }

    private void addToSharedPreferences(String email) {
        mEditor.putString(Constants.PREFERENCE_EMAIL_KEY, email).apply();
        mEditor.commit();
    }
}
