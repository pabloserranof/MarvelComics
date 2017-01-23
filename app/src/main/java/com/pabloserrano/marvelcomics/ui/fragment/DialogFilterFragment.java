package com.pabloserrano.marvelcomics.ui.fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import com.pabloserrano.marvelcomics.R;

import butterknife.ButterKnife;

public class DialogFilterFragment extends DialogFragment {

    public static final String TAG = "DialogFilterFragment";
    private static final String BUDGET = "budget";

    public interface OnFilterSelected {
        void onBudgetSelected(int budget);
    }

    private OnFilterSelected onFilterSelectedListener;

    private EditText editTextBudget;

    public static DialogFilterFragment newInstance(int budget) {
        DialogFilterFragment dialogFilterFragment = new DialogFilterFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(BUDGET, budget);
        dialogFilterFragment.setArguments(bundle);

        return dialogFilterFragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            onFilterSelectedListener = (OnFilterSelected) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " must implement OnFilterSelected");
        }
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.fragment_dialog_filter, null);
        builder.setView(view);
        editTextBudget = ButterKnife.findById(view, R.id.editTextBudget);
        int currentBudget = getArguments().getInt(BUDGET);
        if (currentBudget != -1) {
            editTextBudget.setText(String.valueOf(currentBudget));
        }
        builder.setTitle(R.string.searchfilter)
                .setPositiveButton(R.string.apply, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        if (!TextUtils.isEmpty(editTextBudget.getText())) {
                            onFilterSelectedListener.onBudgetSelected(Integer.valueOf(editTextBudget.getText().toString()));
                        }
                    }
                })
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                    }
                });

        return builder.create();
    }
}

