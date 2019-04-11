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

public class OrderComment extends AppCompatDialogFragment {

    View view;
    EditText commentText;
    private orderCommentListener listener;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        LayoutInflater layoutInflater = getActivity().getLayoutInflater();
        view = layoutInflater.inflate(R.layout.order_comment,null);

        commentText = view.findViewById(R.id.ordercomment);

        Bundle mArgs = getArguments();
        String comment = mArgs.getString("comment");
        commentText.setText(comment);

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setView(view);
        builder.setTitle("Order Comment");
        builder.setNegativeButton("Cancel" , null);
        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                listener.applyComment(commentText.getText().toString());
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
            listener = (orderCommentListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()+"must implement EditOrderPriceListener");
        }

    }

    public interface orderCommentListener{

        void applyComment (String comment);

    }
}
