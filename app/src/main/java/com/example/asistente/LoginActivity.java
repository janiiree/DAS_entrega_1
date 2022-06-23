package com.example.asistente;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    Button btnLogin, btnSignup;
    EditText etEmail, etPassword;
    DBHelper dbHelper = new DBHelper(this, "DBHelper", null, 1);
    boolean keepLogged;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etEmail = findViewById(R.id.et_email);
        etPassword = findViewById(R.id.et_password);

        btnLogin = findViewById(R.id.btn_login);
        btnSignup = findViewById(R.id.btn_signup);
    }

    public void onClickLogin(View view) {
        String email = etEmail.getText().toString();
        String password = etPassword.getText().toString();

        if (email == "" || password == "") {
            Toast.makeText(this, "Hay que llenar todo", Toast.LENGTH_LONG).show();
        } else {
            String login = dbHelper.selectLogin(email, password);
            if (!login.equals("")) {
                String username = login;

                Intent i = new Intent(this, MainActivity.class);
                i.putExtra("username", username)
                        .putExtra("email", email)
                        .putExtra("password", password);
                finish();
                startActivity(i);
            } else {
                Toast.makeText(this, R.string.login_wrong, Toast.LENGTH_LONG).show();
            }
        }
    }

    public void onClickSignUp(View view) {
        Intent i = new Intent(this, SignupActivity.class);
        finish();
        startActivity(i);
    }
}