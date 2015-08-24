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
import android.widget.TextView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Array;
import java.util.Random;


public class MainActivity extends ActionBarActivity {
    private Button start;
    char [] randChar;
    private TextView tv;
    private EditText editText;
    private int countTimes;
    private TextView tvHide;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.btnRanking).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Ranking.class));
            }
        });
        countTimes=5;
        start= (Button) findViewById(R.id.btnStart);
        tv=(TextView)findViewById(R.id.tvInf);
        editText=(EditText)findViewById(R.id.editText);
        tvHide= (TextView) findViewById(R.id.tvIntro);
        try {
            if(!fileExist()) {
                test();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        findViewById(R.id.btnStart).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startInitial();
            }
        });
    }
    public boolean fileExist(){
        try{
            File f=new File("/data/data/com.example.administrator.guess/files/"+FileUtil.FILE_NAME);
            if(!f.exists()){
                return false;
            }

        }catch (Exception e) {
            // TODO: handle exception
            return false;
        }
        return true;
    }

    private void test() throws IOException {
        FileOutputStream out=openFileOutput(FileUtil.FILE_NAME, Context.MODE_PRIVATE);
        StringBuilder base=new StringBuilder();
        OutputStreamWriter osw=new OutputStreamWriter(out ,"UTF-8");
        base.append("新手\n" + "25\n" + "中等\n" + "15\n" + "高手\n" + "8\n");
        osw.write(base.toString());
        osw.flush();
        out.flush();
        osw.close();
        out.close();
    }
    private void startInitial(){
        countTimes=0;
        tvHide.setVisibility(View.INVISIBLE);
        start.setText("提交");
        randChar=new char[4];
        char[] toChar=new char[10];
        int size=10;
        Random r=new Random();
        for(int i=0;i<10;i++)toChar[i]= (char) (i+'0');
        for(int i=0;i<4;i++) {
            int to= (int) (r.nextDouble() * size--);
            randChar[i] =toChar[to];
            char swap=toChar[size];
            toChar[size]=toChar[to];
            toChar[to]=swap;
        }
        findViewById(R.id.btnRanking).setVisibility(View.INVISIBLE);
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StringBuilder base=new StringBuilder(tv.getText().toString());
                String get=new String(editText.getText().toString());
                String answer=compare(get);
                if(answer.equals("4A0B"))
                    endGame();
                else if(answer.length()==4){
                    countTimes++;
                    base.append("\n");
                    base.append(get);
                    base.append(" ");
                    base.append(answer);
                    base.append("  已尝试：");
                    base.append(countTimes);
                    base.append("次");
                    editText.setText("");
                    tv.setText(base.toString());
                }
            }
        });
    }
    private String compare(String get){
        char[] com=get.toCharArray();
        if(get.length()<4||get.length()>4) {
            return new String("");
        }
        int A=0,B=0;
        for(int i=0;i<4;i++){
            for(int j=0;j<4;j++){
                if(com[i]==randChar[j])B++;
            }
            if(com[i]==randChar[i])A++;
        }
        B -= A;
        String back=new String(A+"A"+B+"B");
        return back;
    }
    private void endGame(){
        StringBuilder base1=new StringBuilder("");
        ((EditText)findViewById(R.id.editText)).setText("");
        ((TextView)findViewById(R.id.tvInf)).setText(base1.toString());
        start.setText("开始游戏");
        findViewById(R.id.btnRanking).setVisibility(View.VISIBLE);
        tvHide.setVisibility(View.VISIBLE);
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startInitial();
            }
        });
        Intent dengji=new Intent(MainActivity.this,DengJi.class);
        dengji.putExtra("times",countTimes);
        startActivity(dengji);
    }
}
