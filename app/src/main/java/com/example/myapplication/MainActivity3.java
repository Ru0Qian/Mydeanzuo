package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity3 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main3);
        Button tianjia = findViewById(R.id.button3);
        Button chaxun = findViewById(R.id.button4);
        View.OnClickListener buttonListener = new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if(v.getId() == R.id.button3){
                    Intent intent = new Intent(MainActivity3.this, MainActivity4.class);
                    startActivity(intent);
                }
                else if (v.getId()==R.id.button4){
                    Intent intent = new Intent(MainActivity3.this, MainActivity5.class);
                    startActivity(intent);
                }
            }
        };
        tianjia.setOnClickListener(buttonListener);
        chaxun.setOnClickListener(buttonListener);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}