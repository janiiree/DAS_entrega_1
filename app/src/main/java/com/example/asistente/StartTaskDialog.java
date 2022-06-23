package com.example.asistente;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class StartTaskDialog extends DialogFragment {

    OnInputListener mOnInputListener = (OnInputListener)getActivity();

    public interface OnInputListener {
        void getInput(boolean input);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        super.onCreateDialog(savedInstanceState);
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(R.string.start_task);
        builder.setMessage(R.string.start_task_question);
        builder.setPositiveButton(R.string.start, (dialogInterface, i) -> {
            if(mOnInputListener != null) {
                mOnInputListener.getInput(true);
            }
        });
        builder.setNegativeButton(R.string.not_now, (dialogInterface, i) -> {
            if(mOnInputListener != null) {
                mOnInputListener.getInput(false);
            }
        });
        return builder.create();
    }
}
