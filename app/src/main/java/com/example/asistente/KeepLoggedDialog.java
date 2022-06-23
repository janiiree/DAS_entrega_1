package com.example.asistente;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class KeepLoggedDialog extends DialogFragment {

    OnInputListener mOnInputListener = (OnInputListener)getActivity();

    public interface OnInputListener {
        void getInput(boolean input);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        super.onCreateDialog(savedInstanceState);
        Log.d("Dialog", "holi");
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(R.string.login_keep);
        builder.setMessage(R.string.login_keep_long);
        builder.setPositiveButton(R.string.login_yes, (dialogInterface, i) -> {
            if(mOnInputListener != null) {
                mOnInputListener.getInput(true);
            }
        });
        builder.setNegativeButton(R.string.no_thanks, (dialogInterface, i) -> {
            if(mOnInputListener != null) {
                mOnInputListener.getInput(false);
            }
        });
        return builder.create();
    }
}
