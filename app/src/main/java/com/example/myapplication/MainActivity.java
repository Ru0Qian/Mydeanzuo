package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        // 查找控件
        sqllite dbHelper = new sqllite(this);
        EditText edit1 = findViewById(R.id.editText1);
        EditText edit2 = findViewById(R.id.editText2);
        Button login = findViewById(R.id.button);
        Button regis = findViewById(R.id.button2);

        // 设置按钮点击事件监听器
        View.OnClickListener buttonListener = new View.OnClickListener() {
            public void onClick(View v) {
                if (v.getId() == R.id.button) {
                    // 登录按钮点击事件
                    Toast.makeText(MainActivity.this, "登录按钮被点击", Toast.LENGTH_SHORT).show();
                    String username = edit1.getText().toString().trim(); // 获取输入的用户名
                    String password = edit2.getText().toString().trim(); // 获取输入的密码

                    if (TextUtils.isEmpty(username) || TextUtils.isEmpty(password)) {
                        Toast.makeText(MainActivity.this, "请填写用户名和密码", Toast.LENGTH_SHORT).show();
                    } else {
                        String storedPassword = dbHelper.getPasswordByUsername(username); // 获取数据库中的密码

                        if (password.equals(storedPassword)) {
                            Toast.makeText(MainActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
                            // 跳转到另一个界面
                            Intent intent = new Intent(MainActivity.this, MainActivity3.class);
                            startActivity(intent);
                            finish();
                        } else {
                            Toast.makeText(MainActivity.this, "用户名或密码错误", Toast.LENGTH_SHORT).show();
                        }
                    }
                } else if (v.getId() == R.id.button2) {
                    // 注册按钮点击事件
                    Toast.makeText(MainActivity.this, "注册按钮被点击", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                    startActivity(intent);
                }
            }
        };

        // 绑定监听器
        login.setOnClickListener(buttonListener);
        regis.setOnClickListener(buttonListener);
    }
}
