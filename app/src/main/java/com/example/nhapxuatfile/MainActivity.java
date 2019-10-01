package com.example.nhapxuatfile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final EditText etnhaptenfile = (EditText)findViewById(R.id.etnhaptenfile);
        final EditText etnhapthongtin = (EditText)findViewById(R.id.etnhapthongtin);
        Button btnnhapmoi = (Button)findViewById(R.id.btnnhapmoi);
        btnnhapmoi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                etnhaptenfile.setText("");
                etnhapthongtin.setText("");
            }
        });
        final ArrayList<String> listtenfile = new ArrayList<>();
        listtenfile.add("Hello");
        final Spinner sptenfile =  (Spinner)findViewById(R.id.sptenfile);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_single_choice,listtenfile);
        sptenfile.setAdapter(adapter);
        sptenfile.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                etnhaptenfile.setText(listtenfile.get(i).toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        Button btnluu = (Button)findViewById(R.id.btnluu);
        btnluu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tenfile = etnhaptenfile.getText().toString();
                listtenfile.add(tenfile);
                /*try{
                    FileOutputStream fout = openFileOutput(tenfile, Context.MODE_PRIVATE);
                    fout.write(etnhapthongtin.getText().toString().getBytes());
                    fout.close();
                }catch (Exception e){
                    Toast.makeText(MainActivity.this,"Lỗi lưu file",Toast.LENGTH_LONG).show();
                }*/
                SharedPreferences pref = getApplicationContext().getSharedPreferences(tenfile,Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = pref.edit();
                editor.putString("content",etnhapthongtin.getText().toString());
                editor.commit();

            }
        });
        Button btnmo = (Button)findViewById(R.id.bntmo);
        btnmo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tenfile = etnhaptenfile.getText().toString();
                    /*StringBuffer buffer = new StringBuffer();
                    String line = null;
                    try{
                       FileInputStream fin = openFileInput(tenfile);
                    BufferedReader br = new BufferedReader(new InputStreamReader(fin));
                    while((line = br.readLine())!=null){
                        buffer.append(line).append("\n");
                        etnhapthongtin.setText(buffer.toString());
                    }
                }catch (Exception e){

                }*/
                SharedPreferences pref = getApplicationContext().getSharedPreferences(tenfile,Context.MODE_PRIVATE);
                etnhapthongtin.setText(pref.getString("content",null));
            }
        });
    }
}
