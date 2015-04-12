package com.weebly.krishroy.mydatabasedemo;


//**************************************
// http://www.mysamplecode.com/2012/07/android-listview-checkbox-example.html
//**************************************

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

//


public class MainActivity extends ActionBarActivity {

    EditText inputEditText;
    TextView productsTextView;
    myDBHandler mDBHandler;
    ListView myListView;
    CheckBox mCheckbox;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inputEditText = (EditText) findViewById(R.id.inputField);
        productsTextView = (TextView) findViewById(R.id.displayTextView);
        myListView = (ListView) findViewById(R.id.listView_items);
        mCheckbox = (CheckBox) findViewById(R.id.CustomListItemCheckBox);



        mDBHandler = new myDBHandler(this, null, null, 1);

        printDatabase();

        myListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
               String temp = (String) myListView.getItemAtPosition(position);
               Toast.makeText(getApplicationContext(), "Deleting " + temp, Toast.LENGTH_SHORT).show();
               mDBHandler.deleteProduct(temp);
               printDatabase();
            }
        });





    }

    public void printDatabase(){
        String dbString = mDBHandler.databaseToString();
        String[] arrayOfItems = dbString.split("\\n");

        ListAdapter myAdapter = new CustomListAdapter(this, arrayOfItems);
        //ListAdapter myAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, arrayOfItems);
        myListView.setAdapter(myAdapter);
        productsTextView.setText(dbString);
        inputEditText.setText("");
    }

    public void addButtonClicked(View view){
        Products p = new Products(inputEditText.getText().toString());
        if(!mDBHandler.addProduct(p)){
            Toast.makeText(getApplicationContext(), "Item already exists", Toast.LENGTH_SHORT).show();
        };
        printDatabase();
    }

    public void deleteButtonClicked(View view){
        String toBeDeleted = inputEditText.getText().toString();
        mDBHandler.deleteProduct(toBeDeleted);
        printDatabase();
    }


}
