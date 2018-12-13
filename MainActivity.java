package com.example.computer_ji.todolist;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener, AdapterView.OnItemLongClickListener {

    private EditText task;
    private Button btn;
    private ListView list_item;

    private ArrayList<String> items;
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

     //   list_item.setLongClickable(true);
        task= findViewById(R.id.e_task);
        btn= findViewById(R.id.add_button);
        list_item= findViewById(R.id.list);

        items = HelperDB.readData(this);
        adapter= new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, items);
        list_item.setAdapter(adapter);

        btn.setOnClickListener(this);
        list_item.setOnItemClickListener(this);


        list_item.setOnItemLongClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.add_button:
                String item= task.getText().toString();
                adapter.add(item);
                task.setText("");

                HelperDB.writeData(items, this);
                Toast.makeText(this, "Items Added to list", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        items.remove(position);
        adapter.notifyDataSetChanged();
        Toast.makeText(this, "Delete", Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {


        ClipData myClip;
        final ClipboardManager clipboard= (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
       // String text= String.valueOf(items.toArray());
        String s= (String) list_item.getItemAtPosition(position);
        Toast.makeText(this, "Text Copied", Toast.LENGTH_SHORT).show();
//        for(int i=0; i<text.length() ;i++)
//        {
        //copy data to clipboard
            myClip = ClipData.newPlainText("text", s);
            clipboard.setPrimaryClip(myClip);

        return true;
    }
}
