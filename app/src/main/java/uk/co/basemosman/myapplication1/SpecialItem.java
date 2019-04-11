package uk.co.basemosman.myapplication1;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class SpecialItem extends AppCompatDialogFragment {

    View view;
    EditText spItemNameText , spItemPriceText;
    private orderSpItemListener listener;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        LayoutInflater layoutInflater = getActivity().getLayoutInflater();
        view = layoutInflater.inflate(R.layout.special_item_layout,null);

        spItemNameText = view.findViewById(R.id.edSpItemName);
        spItemPriceText = view.findViewById(R.id.edSpItemPrice);


        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setView(view);
        builder.setTitle("Special Order");
        builder.setNegativeButton("Cancel" , null);
        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                if (!spItemNameText.getText().toString().isEmpty() || !spItemPriceText.getText().toString().isEmpty()){

                    double x = Double.valueOf(spItemPriceText.getText().toString());
                    listener.applySpecialItem(spItemNameText.getText().toString() ,x);
                }else {
                    Toast.makeText(getContext(), "Name or Price Field Can not be Empty", Toast.LENGTH_LONG).show();
                }
            }
        });

        Dialog dialog = builder.create();
        dialog.setCanceledOnTouchOutside(false);

        return dialog;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            listener = (orderSpItemListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()+"must implement EditOrderPriceListener");
        }

    }

    public interface orderSpItemListener{

        void applySpecialItem (String name , double price);

    }
}
