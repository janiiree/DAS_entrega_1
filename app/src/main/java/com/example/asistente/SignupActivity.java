package com.example.asistente;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SignupActivity extends AppCompatActivity {

    Button btnSignup;
    EditText etEmail, etUsername, etPassword, etRepPassword;
    DBHelper dbHelper = new DBHelper(this, "DBHelper", null, 1);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        etEmail = findViewById(R.id.et_email);
        etUsername = findViewById(R.id.et_username);
        etPassword = findViewById(R.id.et_password);
        etRepPassword = findViewById(R.id.et_rep_password);

        btnSignup = findViewById(R.id.btn_signup);
    }

    public void onClickSignUp(View view) {
        String email = etEmail.getText().toString();
        String username = etUsername.getText().toString();
        String password = etPassword.getText().toString();
        String repPassword = etRepPassword.getText().toString();

        if (email.equals("") || username.equals("") || password.equals("") || repPassword.equals("")) {
            Toast.makeText(this, "Hay que llenar todo", Toast.LENGTH_LONG).show();
        } else {
            if (password.equals(repPassword)) {
                boolean exists = dbHelper.checkUserExist(email);
                if (!exists) {
                    dbHelper.insertUser(email, username, password);
                    String login = dbHelper.selectLogin(email, password);
                    if (!login.equals("")) {
                        DialogFragment keepLoggedDialog = new KeepLoggedDialog();
                        keepLoggedDialog.show(getSupportFragmentManager(), "etiqueta");

                        Intent i = new Intent(this, MainActivity.class);
                        i.putExtra("username", username)
                                    .putExtra("email", email)
                                    .putExtra("password", password);
                        finish();
                        startActivity(i);
                    } else {
                        Toast.makeText(this, R.string.login_failed, Toast.LENGTH_LONG).show();
                    }

                } else {
                    Toast.makeText(this, R.string.email_registered, Toast.LENGTH_LONG).show();
                }
            } else {
                Toast.makeText(this, R.string.pass_different, Toast.LENGTH_LONG).show();
            }
        }
    }
}