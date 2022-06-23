package com.example.asistente;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class NewTaskActivity extends AppCompatActivity {

    DBHelper taskDB;

    Button btnCreate, btnCancel;
    EditText etTaskName, etStartDate, etFinishDate, etDescription;
    RatingBar rbPriority;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_task);

        btnCreate = findViewById(R.id.btn_create);
        btnCancel = findViewById(R.id.btn_cancel);

        etTaskName = findViewById(R.id.et_task_name);
        etDescription = findViewById(R.id.et_desc);

        rbPriority = findViewById(R.id.rb_priority);
    }

    public void onClickCancel(View view) {
        Intent i = new Intent(this, MainActivity.class);
        finish();
        startActivity(i);
    }

    public void onClickCreate(View view) {
        String email = "";

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            email = (String) extras.get("email");
        }

        Log.d("NUEVA", "email " + email);

        taskDB = new DBHelper(NewTaskActivity.this, "DBHelper", null, 1);
        long id = taskDB.inserTask(email,
                etTaskName.getText().toString(),
                etDescription.getText().toString(),
                (int) rbPriority.getRating(),
                0);

        if (id > 0) {
            Toast.makeText(this, R.string.task_created, Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, R.string.error, Toast.LENGTH_LONG).show();
        }

        Intent i = new Intent(this, MainActivity.class);
        finish();
        startActivity(i);
    }
}
