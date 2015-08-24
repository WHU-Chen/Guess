package com.example.administrator.guess;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.nio.CharBuffer;


public class DengJi extends ActionBarActivity {
    int times;
    Button btnUp;
    EditText etName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deng_ji);
        Intent get=getIntent();
        times=get.getIntExtra("times",0);
        btnUp=(Button)findViewById(R.id.btnUp);
        etName= (EditText) findViewById(R.id.etName);
        btnUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    FileInputStream in =openFileInput(FileUtil.FILE_NAME);
                    InputStreamReader is=new InputStreamReader(in,"UTF-8");
                    char[] baseC=new char[in.available()];
                    is.read(baseC);
                    StringBuilder base = new StringBuilder(new String(baseC));
                    in.close();
                    is.close();
                    FileOutputStream out=openFileOutput(FileUtil.FILE_NAME,Context.MODE_PRIVATE);
                    OutputStreamWriter osw=new OutputStreamWriter(out ,"UTF-8");
                    base.append(etName.getText());
                    base.append("\n");
                    base.append(times);
                    base.append("\n");
                    osw.write(base.toString());
                    osw.flush();
                    out.flush();
                    osw.close();
                    out.close();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                finish();
            }
        });
    }


}
