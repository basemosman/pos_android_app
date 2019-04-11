package uk.co.basemosman.myapplication1;

import android.content.Context;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TableRow;

import java.util.ArrayList;
import java.util.List;

public class Buttons extends AppCompatActivity implements View.OnClickListener {


    private Button button;
    private TableRow row1,row2,row3,row4,row5;

    private List<Button> btn;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);




    }

    public Buttons(Button button, TableRow row1, TableRow row2, TableRow row3, TableRow row4, TableRow row5) {
        this.button = button;
        this.row1 = row1;
        this.row2 = row2;
        this.row3 = row3;
        this.row4 = row4;
        this.row5 = row5;
    }

    public Buttons(Button button, TableRow row1, TableRow row2, TableRow row3, TableRow row4) {
        this.button = button;
        this.row1 = row1;
        this.row2 = row2;
        this.row3 = row3;
        this.row4 = row4;
    }

    public Buttons(Button button, TableRow row1, TableRow row2, TableRow row3) {
        this.button = button;
        this.row1 = row1;
        this.row2 = row2;
        this.row3 = row3;
    }

    public Buttons() {

    }

    public List<Button> setButtonitems(Button button1 ,int btnnum, String[] catList){


        btn = new ArrayList<>();

        for (int i = 1; i <=btnnum ; i++)
        {
            button1 = new Button(Buttons.this);
            button1.setId(200+i);
            button1.setText(catList[i-1]);
            button1.setTag(catList[i-1]);
            button1.setPadding(10,10,10,10);
            button1.setTextSize(12);
            button1.setHeight(120);
            button1.setWidth(250);

            btn.add(button1);
            Log.d("size", String.valueOf(btn.size()));

            button1.setOnClickListener(this);

        }

        return btn;
    }

    @Override
    public void onClick(View v) {

    }
}
