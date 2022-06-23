package com.example.asistente;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class StartTaskDialog extends DialogFragment {

    OnStartInputListener mOnStartInputListener = (OnStartInputListener)getActivity();

    public interface OnStartInputListener {
        void getInputStart(boolean input);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        super.onCreateDialog(savedInstanceState);
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(R.string.start_task);
        builder.setMessage(R.string.start_task_question);
        builder.setPositiveButton(R.string.start, (dialogInterface, i) -> {
            Intent intent = new Intent(getContext(), StartTaskActivity.class);
            startActivity(intent);
            if(mOnStartInputListener != null) {
                Log.d("EMPEZAR", "si");
                mOnStartInputListener.getInputStart(true);
            }
        });
        builder.setNegativeButton(R.string.not_now, (dialogInterface, i) -> {
            if(mOnStartInputListener != null) {
                Log.d("EMPEZAR", "no");
                mOnStartInputListener.getInputStart(false);
            }
        });
        return builder.create();
    }
}
