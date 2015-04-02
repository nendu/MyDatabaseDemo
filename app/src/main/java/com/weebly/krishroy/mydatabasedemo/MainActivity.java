package com.weebly.krishroy.mydatabasedemo;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;


public class MainActivity extends ActionBarActivity {

    EditText inputEditText;
    TextView productsTextView;
    myDBHandler mDBHandler;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inputEditText = (EditText) findViewById(R.id.inputField);
        productsTextView = (TextView) findViewById(R.id.displayTextView);

        mDBHandler = new myDBHandler(this, null, null, 1);

        printDatabase();



    }

    public void printDatabase(){
        String dbString = mDBHandler.databaseToString();
        productsTextView.setText(dbString);
        inputEditText.setText("");
    }

    public void addButtonClicked(View view){
        Products p = new Products(inputEditText.getText().toString());
        mDBHandler.addProduct(p);
        printDatabase();
    }

    public void deleteButtonClicked(View view){
        String toBeDeleted = inputEditText.getText().toString();
        mDBHandler.deleteProduct(toBeDeleted);
        printDatabase();
    }

}
