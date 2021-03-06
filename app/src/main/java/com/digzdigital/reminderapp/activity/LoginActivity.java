package com.digzdigital.reminderapp.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;

import com.digzdigital.reminderapp.R;
import com.digzdigital.reminderapp.databinding.ActivityLoginBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener, OnCompleteListener<AuthResult> {

    private ActivityLoginBinding binding;
    private FirebaseAuth auth;
private ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        auth = FirebaseAuth.getInstance();
        if (auth.getCurrentUser() != null) {
            switchActivity(MainActivity.class);
        }

        progressDialog = new ProgressDialog(this);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login);

        binding.btnLogin.setOnClickListener(this);
        binding.btnResetPassword.setOnClickListener(this);
        binding.btnSignup.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_login:
                login();
                break;
            case R.id.btn_reset_password:
                switchActivity(ResetPasswordActivity.class);
                break;
            case R.id.btn_signup:
                switchActivity(RegisterActivity.class);
                break;
        }
    }

    private void login() {
        String emailText = binding.email.getText().toString().trim();
        String passwordText = binding.password.getText().toString().trim();
        if (!validate(emailText, passwordText)) return;
        progressDialog.setTitle("Please wait");
        progressDialog.setMessage("Please wait, signing you in");
        progressDialog.show();
        // binding.btnLogin.setEnabled(false);
        auth.signInWithEmailAndPassword(emailText, passwordText).addOnCompleteListener(this, this);
    }

    private boolean validate(String email, String password) {
        boolean state = true;

        if (TextUtils.isEmpty(email)) {
            Snackbar.make(binding.activityLogin, "Enter email address!", Snackbar.LENGTH_SHORT).show();
            binding.email.setError("Enter email address");
            state = false;
        } else binding.email.setError(null);


        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            Snackbar.make(binding.activityLogin, "Enter a valid email address!", Snackbar.LENGTH_SHORT).show();
            binding.email.setError("Enter a valid email address!");
            state = false;
        } else binding.email.setError(null);


        if (TextUtils.isEmpty(password)) {
            Snackbar.make(binding.activityLogin, "Enter password", Snackbar.LENGTH_SHORT).show();
            binding.password.setError("Enter password");
            state = false;
        } else binding.password.setError(null);


        if (password.length() < 6) {
            Snackbar.make(binding.activityLogin,"Password should be at least six characters", Snackbar.LENGTH_SHORT).show();
            binding.password.setError("Password too short");
            state = false;
        } else binding.password.setError(null);

        return state;
    }

    @Override
    public void onComplete(@NonNull Task<AuthResult> task) {
        // binding.progressBar.setVisibility(View.GONE);
        progressDialog.dismiss();
        if (!task.isSuccessful()) {
            Snackbar.make(binding.activityLogin, "Login failed", Snackbar.LENGTH_SHORT).show();
            // binding.btnLogin.setEnabled(true);
        } else {
            switchActivity(MainActivity.class);
        }
    }

    private void switchActivity(Class classFile) {
        Intent intent = new Intent(this, classFile);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
}