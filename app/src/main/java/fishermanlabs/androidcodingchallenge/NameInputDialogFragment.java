package fishermanlabs.androidcodingchallenge;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

/**
 * Created by tonyk_000 on 2/22/2016.
 */
public class NameInputDialogFragment extends DialogFragment {

    private NameInputListener mNameInputListener;

    public interface NameInputListener {
        void onOkayClicked(Name name);
    }

    public void setListener(NameInputListener nameInputListener) {
        mNameInputListener = nameInputListener;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View dialogLayout = inflater.inflate(R.layout.dialog_input_name, null);
        final EditText firstNameInput = (EditText) dialogLayout.findViewById(R.id.first_name_input);
        final EditText lastNameInput = (EditText) dialogLayout.findViewById(R.id.last_name_input);

        builder.setView(dialogLayout)
                .setPositiveButton("ADD", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (mNameInputListener != null){
                            mNameInputListener.onOkayClicked(new Name(firstNameInput.getText().toString(),
                                    lastNameInput.getText().toString()));
                        }
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        NameInputDialogFragment.this.getDialog().cancel();
                    }
                });
        return builder.create();

    }
}
