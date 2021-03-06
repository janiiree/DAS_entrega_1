package com.example.asistente;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
        implements KeepLoggedDialog.OnInputListener, StartTaskDialog.OnStartInputListener {

    private boolean keepLogged;
    DBHelper dbHelper = new DBHelper(this, "DBHelper", null, 1);
    FloatingActionButton fbtnAdd;
    String username, email, password;

    private List<Task> taskList;

    ListView tasksListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        checkLogin();
    }

    @Override
    public void onResume() {
        super.onResume();

        taskList = dbHelper.selectTasks(email);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.logout, menu);
        return true;
    }

    public void onOptionItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.logout:
                SharedPreferences preferences = getSharedPreferences("log", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putBoolean("keeplogged", false);

                editor.putString("username", "");
                editor.putString("email", "");
                editor.putString("password", "");
                editor.apply();
                break;
        }
    }

    private void checkLogin() {
        SharedPreferences preferences = getSharedPreferences("log", Context.MODE_PRIVATE);
        username = preferences.getString("user", "");
        email = preferences.getString("email", "");
        password = preferences.getString("password", "");

        if (email.equals("") || password.equals("")) {
            Bundle extras = getIntent().getExtras();
            if (extras != null) {
                username = (String) extras.get("username");
                email = (String) extras.get("email");
                password = (String) extras.get("password");

                DialogFragment keepLoggedDialog = new KeepLoggedDialog();
                keepLoggedDialog.show(getSupportFragmentManager(), "etiqueta");

                SharedPreferences.Editor editor = preferences.edit();
                editor.putBoolean("keeplogged", false);

                editor.putString("username", username);
                editor.putString("email", email);
                editor.putString("password", password);
                editor.apply();

                Toast.makeText(this, "Contrase??a y usuario guardados correctamente", Toast.LENGTH_LONG).show();
            } else {
                username = "";
                email = "";
                password = "";
            }
        }

        if (email.equals("") || password.equals("")) {
            Intent i = new Intent(this, LoginActivity.class);
            finish();
            startActivity(i);
        } else {
            setContentView(R.layout.activity_main);

            tasksListView = findViewById(R.id.listView);
            taskList = new ArrayList<Task>();
            taskList = dbHelper.selectTasks(email);
            TaskAdapter adapter = new TaskAdapter(getApplicationContext(), taskList);
            tasksListView.setAdapter(adapter);

            tasksListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                    Task task = taskList.get(position);

                    DialogFragment startTaskDialog = new StartTaskDialog();
                    startTaskDialog.show(getSupportFragmentManager(), "etiqueta");
                }
            });
        }
    }

    public void onClickAddTask(View view) {
        Intent i = new Intent(this, NewTaskActivity.class);
        i.putExtra("email", email);
        finish();
        startActivity(i);
    }

    @Override
    public void getInput(boolean input) {
        keepLogged = input;
    }

    @Override
    public void getInputStart(boolean input) {
        Log.d("EMPEZAR", String.valueOf(input));
        if (input) {
            Intent i = new Intent(this, StartTaskActivity.class);
            startActivity(i);
        }
    }
}