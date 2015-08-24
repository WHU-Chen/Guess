package com.example.administrator.guess;

import android.content.Context;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Scanner;


public class Ranking extends ActionBarActivity {
    TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ranking);
        tv= (TextView) findViewById(R.id.tvRank);
        try {
            FileInputStream in = null;
            in = openFileInput(FileUtil.FILE_NAME);
            InputStreamReader is=new InputStreamReader(in,"UTF-8");
            ArrayList<RankData> array1 = new ArrayList<RankData>();
            String name;
            BufferedReader ini=new BufferedReader(is);
            name =ini.readLine();
            boolean is_have=false;
            while(name!=null){
                is_have=true;
                String times;
                times = ini.readLine();
                array1.add(new RankData(Integer.parseInt(times), name));
                name =ini.readLine();
            }
            ini.close();
            in.close();
            is.close();
            Collections.sort(array1);
            RankData[] array=array1.toArray(new RankData[array1.size()]);
            StringBuilder outPut=new StringBuilder();
            boolean is_have_data=false;
            for (RankData i : array) {
                is_have_data=true;
                outPut.append(i.getName());
                outPut.append(" " + "尝试次数:" + i.getTimes() + "\n");
            }
            tv.setText(outPut.toString());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



}
