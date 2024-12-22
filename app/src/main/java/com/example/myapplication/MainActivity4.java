package com.example.myapplication;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity4 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main4);

        // 获取控件实例
        Button taianjai = findViewById(R.id.button8);
        EditText xuehao = findViewById(R.id.Text8);
        EditText name = findViewById(R.id.Text9);
        EditText zongchengji = findViewById(R.id.Text10);
        EditText xuehao2= findViewById(R.id.Text11);
        Button shanchu= findViewById(R.id.button9);
        // 数据库助手实例
        DatabaseHelper databaseHelper = new DatabaseHelper(this);

        // 按钮点击事件
        taianjai.setOnClickListener(v -> {
            try {
                // 获取用户输入的数据
                int xuehao1 = Integer.parseInt(xuehao.getText().toString().trim());
                String name1 = name.getText().toString().trim();
                float zongchengji1 = Float.parseFloat(zongchengji.getText().toString().trim());

                // 检查输入是否为空
                if (name1.isEmpty()) {
                    Toast.makeText(this, "姓名不能为空！", Toast.LENGTH_SHORT).show();
                    return;
                }

                // 创建学生对象
                Student student = new Student(xuehao1, name1, zongchengji1);

                // 插入数据并提示用户
                boolean isInserted = databaseHelper.insertStudent(student);
                if (isInserted) {
                    Toast.makeText(this, "数据插入成功！", Toast.LENGTH_SHORT).show();
                    // 清空输入框
                    xuehao.setText("");
                    name.setText("");
                    zongchengji.setText("");
                } else {
                    Toast.makeText(this, "数据插入失败！", Toast.LENGTH_SHORT).show();
                }
            } catch (NumberFormatException e) {
                // 捕获数据转换异常
                Toast.makeText(this, "请输入合法的学号和成绩！", Toast.LENGTH_SHORT).show();
            } catch (Exception e) {
                // 捕获其他异常
                Toast.makeText(this, "发生错误：" + e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        shanchu.setOnClickListener(v -> {
            int xuehao1 = Integer.parseInt(xuehao2.getText().toString());
            boolean isDeleted = databaseHelper.deleteStudentById(xuehao1);
            if (isDeleted) {
                Toast.makeText( this, "学生记录删除成功！", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "未找到对应学号的学生！", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
