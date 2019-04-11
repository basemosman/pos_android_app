package uk.co.basemosman.myapplication1;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.security.PublicKey;
import java.util.ArrayList;

public class SubItemsMenu extends AppCompatDialogFragment implements AdapterView.OnItemSelectedListener {

    private SubitemMenuListener listener;
    View view=null;

    String [] listStarter,listMain,listSide,listRice,listSundires,listVegMain;
    String [] listClassics,listMossalla,listVariations,listBiryani,listSignature;

    //Spinner starter;
    Spinner spinnerSubMain,spinnerSubMain2;

    static String sitem;

    // Spinner spinnerSubMain;

    String item;
    String selectedSubItem,sp1SelectedItem,sp2SelectedItem,sp3SelectedItem,sp4SelectedItem,sp5SelectedItem;
    String sp11SelectedItem,sp21SelectedItem,sp41SelectedItem,selectedSubItem2;


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {



        Bundle mArgs = getArguments();
        item = mArgs.getString("item");

        sitem = item;





        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();


        listStarter = getResources().getStringArray(R.array.Starter);
        listMain = getResources().getStringArray(R.array.Set_Main);
        listSide = getResources().getStringArray(R.array.Vege_Side);
        listRice = getResources().getStringArray(R.array.Rice);
        listSundires = getResources().getStringArray(R.array.Sundries);


        listClassics = getResources().getStringArray(R.array.Classics);
        listMossalla = getResources().getStringArray(R.array.Mossalla);
        listVariations = getResources().getStringArray(R.array.Variation);
        listBiryani = getResources().getStringArray(R.array.Biryani);
        listSignature = getResources().getStringArray(R.array.Chefs_Signature);
        listVegMain = getResources().getStringArray(R.array.Vege_Main);


        if(item.equals("Veg_For One Person") || item.equals("Set_For One Person")){

             view = inflater.inflate(R.layout.activity_sub_items_menu2,null);

            setListforOnePerson();
        }else{
             view = inflater.inflate(R.layout.activity_sub_items_menu,null);
            setListforTwoPerson();
        }





        builder.setView(view)

                .setCancelable(false)
                .setTitle("Select Items For " + item)
                .setNegativeButton("Cancel", null)
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                if (item.equals("Veg_For One Person") || item.equals("Set_For One Person")){


                    listener.applyTexts(sp1SelectedItem,sp2SelectedItem,sp3SelectedItem,sp4SelectedItem,sp5SelectedItem,selectedSubItem,null,null,null,null,item);

                }else {
                    listener.applyTexts(sp1SelectedItem,sp2SelectedItem,sp3SelectedItem,sp4SelectedItem,sp5SelectedItem,selectedSubItem,sp11SelectedItem,sp21SelectedItem,sp41SelectedItem,selectedSubItem2,item);
                }
            }

        });

       final Dialog dialog =  builder.create();
       dialog.setOnShowListener(new DialogInterface.OnShowListener() {
           @Override
           public void onShow(DialogInterface dialog1) {
               ((AlertDialog) dialog).getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(getResources().getColor(R.color.colorb));
               ((AlertDialog) dialog).getButton(AlertDialog.BUTTON_POSITIVE).setTextSize(25);
               ((AlertDialog) dialog).getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(getResources().getColor(R.color.colorb));
           }
       });


       // builder.create().getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(R.color.colorAccent);

        return dialog;

    }



    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            listener = (SubitemMenuListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()+"must implement SubItemsMenuListener");
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        switch (parent.getId()){
            case R.id.spinner1:{

                 sp1SelectedItem = parent.getItemAtPosition(position).toString();

                break;
            }
            case R.id.spinner2:{

                sp2SelectedItem = parent.getItemAtPosition(position).toString();

                if (sp2SelectedItem.equals("Classics")){
                    setSubMainItems(listClassics);
                }else if (sp2SelectedItem.equals("Chefs_Signature")){
                    setSubMainItems(listSignature);
                }else if (sp2SelectedItem.equals("Biryani")){
                    setSubMainItems(listBiryani);
                }else if (sp2SelectedItem.equals("Variation")){
                    setSubMainItems(listVariations);
                }else if (sp2SelectedItem.equals("Mossalla")){
                    setSubMainItems(listMossalla);
                }else if (sp2SelectedItem.equals("Vege_Main")){
                    setSubMainItems(listVegMain);
                }
                break;
            } case R.id.spinner3:{

                sp3SelectedItem = parent.getItemAtPosition(position).toString();

                break;
            } case R.id.spinner4:{

                sp4SelectedItem = parent.getItemAtPosition(position).toString();

                break;
            }
            case R.id.spinner5:{

                sp5SelectedItem = parent.getItemAtPosition(position).toString();

                break;
            }
            case R.id.spinner11:{

                sp11SelectedItem = parent.getItemAtPosition(position).toString();

                break;
            }
            case R.id.spinner21:{

                sp21SelectedItem = parent.getItemAtPosition(position).toString();

                if (sp21SelectedItem.equals("Classics")){
                    setSubMainItems2(listClassics);
                }else if (sp21SelectedItem.equals("Chefs_Signature")){
                    setSubMainItems2(listSignature);
                }else if (sp21SelectedItem.equals("Biryani")){
                    setSubMainItems2(listBiryani);
                }else if (sp21SelectedItem.equals("Variation")){
                    setSubMainItems2(listVariations);
                }else if (sp21SelectedItem.equals("Mossalla")){
                    setSubMainItems2(listMossalla);
                }else if (sp21SelectedItem.equals("Vege_Main")){
                    setSubMainItems2(listVegMain);
                }

                break;
            }

            case R.id.spinner41:{

                sp41SelectedItem = parent.getItemAtPosition(position).toString();

                break;
            }
            case R.id.spinnerMain:{

                selectedSubItem = parent.getItemAtPosition(position).toString();

            }  case R.id.spinnerMain1:{

                selectedSubItem2 = parent.getItemAtPosition(position).toString();

            }
        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public interface SubitemMenuListener{

        void applyTexts(String sp1 , String sp2 ,String sp3,String sp4,String sp5,String spSubItem,String sp11,String sp21,String sp41,String spSubItem2,String categories);

    }

    public  void setListforOnePerson(){

        Spinner starter = view.findViewById(R.id.spinner1);
        Spinner main = view.findViewById(R.id.spinner2);
        Spinner side = view.findViewById(R.id.spinner3);
        Spinner rice = view.findViewById(R.id.spinner4);
        Spinner sundries = view.findViewById(R.id.spinner5);


        ArrayAdapter<String> arrayAdapterStarter = new ArrayAdapter<>(getActivity(),android.R.layout.simple_dropdown_item_1line, listStarter);

        if (item.equals("Veg_For One Person")){

            ArrayAdapter<String> arrayAdapterVegeMain = new ArrayAdapter<>(getActivity(),android.R.layout.simple_dropdown_item_1line, listVegMain);
            main.setAdapter(arrayAdapterVegeMain);
        }else {
            ArrayAdapter<String> arrayAdapterMain = new ArrayAdapter<>(getActivity(),android.R.layout.simple_dropdown_item_1line, listMain);
            main.setAdapter(arrayAdapterMain);
        }

        ArrayAdapter<String> arrayAdapterSide = new ArrayAdapter<>(getActivity(),android.R.layout.simple_dropdown_item_1line, listSide);
        ArrayAdapter<String> arrayAdapterRice = new ArrayAdapter<>(getActivity(),android.R.layout.simple_dropdown_item_1line, listRice);
        ArrayAdapter<String> arrayAdapterSundires = new ArrayAdapter<>(getActivity(),android.R.layout.simple_dropdown_item_1line, listSundires);


        starter.setAdapter(arrayAdapterStarter);
        side.setAdapter(arrayAdapterSide);
        rice.setAdapter(arrayAdapterRice);
        sundries.setAdapter(arrayAdapterSundires);


        starter.setOnItemSelectedListener(this);
        main.setOnItemSelectedListener(this);
        side.setOnItemSelectedListener(this);
        rice.setOnItemSelectedListener(this);
        sundries.setOnItemSelectedListener(this);


    }


    public  void setListforTwoPerson(){


        Spinner starter = view.findViewById(R.id.spinner1);
        Spinner main = view.findViewById(R.id.spinner2);
        Spinner side = view.findViewById(R.id.spinner3);
        Spinner rice = view.findViewById(R.id.spinner4);
        Spinner sundries = view.findViewById(R.id.spinner5);


        Spinner starter2 = view.findViewById(R.id.spinner11);
        Spinner main2 = view.findViewById(R.id.spinner21);
        Spinner rice2 = view.findViewById(R.id.spinner41);



        ArrayAdapter<String> arrayAdapterStarter2 = new ArrayAdapter<>(getActivity(),android.R.layout.simple_dropdown_item_1line, listStarter);
        ArrayAdapter<String> arrayAdapterMain2 = new ArrayAdapter<>(getActivity(),android.R.layout.simple_dropdown_item_1line, listMain);
        ArrayAdapter<String> arrayAdapterSide2 = new ArrayAdapter<>(getActivity(),android.R.layout.simple_dropdown_item_1line, listSide);
        ArrayAdapter<String> arrayAdapterRice2 = new ArrayAdapter<>(getActivity(),android.R.layout.simple_dropdown_item_1line, listRice);
        ArrayAdapter<String> arrayAdapterSundires2 = new ArrayAdapter<>(getActivity(),android.R.layout.simple_dropdown_item_1line, listSundires);


        if (item.equals("Veg_For Two Person")){

            arrayAdapterMain2 = new ArrayAdapter<>(getActivity(),android.R.layout.simple_dropdown_item_1line, listVegMain);

        }

        starter.setAdapter(arrayAdapterStarter2);
        main.setAdapter(arrayAdapterMain2);
        side.setAdapter(arrayAdapterSide2);
        rice.setAdapter(arrayAdapterRice2);
        sundries.setAdapter(arrayAdapterSundires2);

        starter2.setAdapter(arrayAdapterStarter2);
        main2.setAdapter(arrayAdapterMain2);
        rice2.setAdapter(arrayAdapterRice2);


        starter.setOnItemSelectedListener(this);
        main.setOnItemSelectedListener(this);
        side.setOnItemSelectedListener(this);
        rice.setOnItemSelectedListener(this);
        sundries.setOnItemSelectedListener(this);


        starter2.setOnItemSelectedListener(this);
        main2.setOnItemSelectedListener(this);
        rice2.setOnItemSelectedListener(this);

    }

    public void setSubMainItems(String[] list){

        spinnerSubMain = view.findViewById(R.id.spinnerMain);
        ArrayAdapter<String> arrayAdapterSubMain= new ArrayAdapter<>(getActivity(),android.R.layout.simple_dropdown_item_1line, list);
        spinnerSubMain.setAdapter(arrayAdapterSubMain);
        spinnerSubMain.setVisibility(View.VISIBLE);
        spinnerSubMain.setOnItemSelectedListener(this);

    }

    public void setSubMainItems2(String[] list){


            spinnerSubMain2 = view.findViewById(R.id.spinnerMain1);
            ArrayAdapter<String> arrayAdapterSubMain2= new ArrayAdapter<>(getActivity(),android.R.layout.simple_dropdown_item_1line, list);
            spinnerSubMain2.setAdapter(arrayAdapterSubMain2);
            spinnerSubMain2.setVisibility(View.VISIBLE);
            spinnerSubMain2.setOnItemSelectedListener(this);


    }

    public void toast(String st , int showlen){

        // Showing selected spinner item
        Toast.makeText(getActivity(), "Selected: " + st, showlen).show();
    }
}
