package uk.co.basemosman.myapplication1;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class EditPriceOrder extends AppCompatDialogFragment {

    Button btnPriceDown, btnPriceUp;
    TextView currentPrice, edtPrice;
    double ePrice =0.00;
    double cPrice = 0.00;


    View view;
    private orderPriceListener listener;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        LayoutInflater layoutInflater = getActivity().getLayoutInflater();
        view = layoutInflater.inflate(R.layout.activity_edit_price_order,null);


        Bundle mArgs = getArguments();
        String getcurrentPrice = mArgs.getString("currentPrice");
        double x = Double.valueOf(getcurrentPrice);

        currentPrice = view.findViewById(R.id.TextVCPrice);
        currentPrice.setText(String.format("%.2f",x));
        edtPrice = view.findViewById(R.id.TextVEditPrcie);


        btnPriceDown = view.findViewById(R.id.btnPriceDown);
        btnPriceDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                cPrice = Double.valueOf(currentPrice.getText().toString());

                ePrice -=0.50;
                cPrice = cPrice -0.50;

                edtPrice.setText(String.format("%.2f",ePrice));
                currentPrice.setText(String.format("%.2f",cPrice));

            }
        });

        btnPriceUp = view.findViewById(R.id.btnPriceUp);
        btnPriceUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                cPrice = Double.valueOf(currentPrice.getText().toString());

                ePrice +=0.50;
                cPrice = cPrice +0.50;

                edtPrice.setText(String.format("%.2f",ePrice));
                currentPrice.setText(String.format("%.2f",cPrice));

            }
        });

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setView(view);
        builder.setTitle("Edit Order Price");
        builder.setNegativeButton("Cancel" , null);
        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                listener.applyPrice(ePrice);
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
            listener = (orderPriceListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()+"must implement EditOrderPriceListener");
        }

    }

    public interface orderPriceListener{

        void applyPrice(double EditOrderPrice);

    }
}
