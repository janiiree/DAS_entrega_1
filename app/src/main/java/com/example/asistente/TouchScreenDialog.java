package com.example.asistente;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import java.util.Random;

public class TouchScreenDialog extends DialogFragment {

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        super.onCreateDialog(savedInstanceState);
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        Random rand = new Random(); //instance of random class
        int upperbound = 5;
        int int_random = rand.nextInt(upperbound);
        String message = "";
        String button_message = "";
        switch (int_random) {
            case 0:
                message = getResources().getString(R.string.message_1);
                button_message = getResources().getString(R.string.btn_message_1);
                break;
            case 1:
                message = getResources().getString(R.string.message_2);
                button_message = getResources().getString(R.string.btn_message_2);
                break;
            case 2:
                message = getResources().getString(R.string.message_3);
                button_message = getResources().getString(R.string.btn_message_3);
                break;
            case 3:
                message = getResources().getString(R.string.message_4);
                button_message = getResources().getString(R.string.btn_message_4);
                break;
            case 4:
                message = getResources().getString(R.string.message_5);
                button_message = getResources().getString(R.string.btn_message_5);
                break;
        }
        builder.setMessage(message);
        builder.setPositiveButton(button_message, (dialogInterface, i) -> {

        });
        return builder.create();
    }
}
