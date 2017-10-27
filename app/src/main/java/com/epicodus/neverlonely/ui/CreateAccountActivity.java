package com.epicodus.neverlonely.ui;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.epicodus.neverlonely.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CreateAccountActivity extends AppCompatActivity {
    public static final String TAG = CreateAccountActivity.class.getSimpleName();
    @Bind(R.id.app_name_text_view) TextView mNameTextView;
    @Bind(R.id.new_account_name) EditText mNewAccountName;
    @Bind(R.id.new_account_email) EditText mNewAccountEmail;
    @Bind(R.id.new_account_password) EditText mNewAccountPassword;
    @Bind(R.id.new_account_confirm_password) EditText mNewAccountConfirmPassword;

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListener; // informs application when user account is successfully authenticated

    @OnClick(R.id.login_text_view)
    public void login() {
        Intent intent = new Intent(CreateAccountActivity.this, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }

    @OnClick(R.id.create_new_account_button)
    public void createNewUser() {
        final String name = mNewAccountName.getText().toString().trim();
        final String email = mNewAccountEmail.getText().toString().trim();
        String password = mNewAccountPassword.getText().toString().trim();
        String confirmPassword = mNewAccountConfirmPassword.getText().toString().trim();

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()) {
                            Log.d(TAG, "Authentication successful");
                        } else {
                            Toast.makeText(CreateAccountActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);
        ButterKnife.bind(this);
        mNameTextView.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/grandhotel.ttf"));

        mAuth = FirebaseAuth.getInstance();
        createAuthStateListener();
    }

    @Override
    protected void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthStateListener);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if(mAuthStateListener != null) {
            mAuth.removeAuthStateListener(mAuthStateListener);
        }
    }

    private void createAuthStateListener() {
        mAuthStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                final FirebaseUser user = firebaseAuth.getCurrentUser();
                if(user != null) {
                    Intent intent = new Intent(CreateAccountActivity.this, EventListActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                    finish();
                }
            }
        };
    }
}
