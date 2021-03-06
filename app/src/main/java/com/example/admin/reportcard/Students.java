package com.example.admin.reportcard;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import static android.R.id.message;

public class Students extends Activity implements AdapterView.OnItemClickListener {
    DatabaseHelper myDb;
    Button btnViewStudents;
    ListView allStudents;
    ArrayAdapter<String> listAdapter;
    Cursor myCursor;
    ArrayList<String> nameList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_students);

        myDb = new DatabaseHelper(this);

        btnViewStudents = (Button) findViewById(R.id.view_students);
        allStudents = (ListView) findViewById(R.id.allStudentsView);
        myCursor = myDb.getAllData();
        System.out.println(myCursor.getCount());
//        for (int i = 0; i < myCursor.getCount(); i++) {
//            System.out.println((myCursor.getString(myCursor.getColumnIndex("NAME"))));
//            String name = myCursor.getString(myCursor.getColumnIndex("NAME"));
//            nameList.add(myCursor.getString(myCursor.getColumnIndex("NAME")));
//
//        }


//
        if(myCursor.moveToFirst())
        {
            do{
                nameList.add(myCursor.getString(myCursor.getColumnIndex("NAME")));
            }while(myCursor.moveToNext());
        }
        listAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, nameList);
        allStudents.setAdapter(listAdapter);
        allStudents.setOnItemClickListener(this);

       // AllStudents();
    }


//    @Override
//    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//
//    }

//    public void AllStudents(){
//
//
//        btnViewStudents.setOnClickListener(
//                new View.OnClickListener(){
//                    @Override
//                    public void onClick(View v){
//                       Cursor results =  myDb.getAllData();
//                        if(results.getCount() == 0){
//                            //show messages on screen
//                            showMessage("Error", "There is nothing to display");
//                            return;
//                        }
//
//                        StringBuffer buffer = new StringBuffer();
//                        while(results.moveToNext()){
//                            buffer.append("Id : " + results.getString(0) + "\n");
//                            buffer.append("Name : " + results.getString(1) + "\n");
//                            buffer.append("Email : " + results.getString(2) + "\n");
//                            buffer.append("Marks : " + results.getString(3) + "\n\n");
//                        }
//                        //Methods that displays all  data
//                        showMessage("Data",buffer.toString());
//
//                    }
//                }
//        );
//    }

    public void showMessage(String title, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent detailsIntent = new Intent(Students.this, Details.class);
        System.out.println(allStudents.getItemAtPosition(position).toString());

        long l = id+1;
        System.out.println("the id is : "+ l);
        String[] data = myDb.oneStudentData(l);
        System.out.println("the text is: "+ data[0]+" "+data[1]+" "+data[3]+" "+data[2]+" "+data[3]+" "+data[4]+" "+data[5]);
        detailsIntent.putExtra("Details",data);
        startActivity(detailsIntent);
    }
}