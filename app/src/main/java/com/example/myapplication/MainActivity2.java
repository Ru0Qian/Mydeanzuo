package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity2 extends AppCompatActivity {
    private sqllite dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main2);
        dbHelper = new sqllite(this);
        EditText usernameEditText = findViewById(R.id.Text1);
        EditText passwordEditText = findViewById(R.id.Text2);
        Button reg = findViewById(R.id.button11);

        reg.setOnClickListener(v -> {
            String username = usernameEditText.getText().toString().trim(); // 获取用户名
            String password = passwordEditText.getText().toString().trim(); // 获取密码
            if (TextUtils.isEmpty(username) || TextUtils.isEmpty(password)) {
                Toast.makeText(MainActivity2.this, "请填写用户名和密码", Toast.LENGTH_SHORT).show();
            } else {
                boolean isRegistered = dbHelper.insertData(username, password); // 插入数据
                if (isRegistered) {
                    Toast.makeText(MainActivity2.this, "注册成功", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(MainActivity2.this, MainActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(MainActivity2.this, "用户名已存在", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}