package com.example.myapplication;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity5 extends AppCompatActivity {
    DatabaseHelper databaseHelper = new DatabaseHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main5);

        Button chauxun = findViewById(R.id.button3);
        Button paixu = findViewById(R.id.button4);
        EditText xuehao = findViewById(R.id.Text100);
        TextView jeiguo = findViewById(R.id.textView);

        // 按学号查询学生
        chauxun.setOnClickListener(v -> {
            int x = Integer.parseInt(xuehao.getText().toString());
            Student student = databaseHelper.getStudentById(x);
            if (student != null) {
                String studentInfo = "学号: " + student.getId() + "\n" +
                        "姓名: " + student.getName() + "\n" +
                        "总成绩: " + student.getScore();
                jeiguo.setText(studentInfo);
            } else {
                jeiguo.setText("未找到对应的学生");
            }
        });

        // 按成绩排序展示所有学生
        paixu.setOnClickListener(v -> {
            Cursor cursor = databaseHelper.getStudentsSortedByScoreDescending(); // 通过降序获取学生
            if (cursor != null && cursor.getCount() > 0) {
                StringBuilder sortedStudentsInfo = new StringBuilder();
                while (cursor.moveToNext()) {
                    int idIndex = cursor.getColumnIndex("id");
                    int nameIndex = cursor.getColumnIndex("name");
                    int scoreIndex = cursor.getColumnIndex("score");

                    // 确保索引不为-1，并且检查每个字段
                    if (idIndex != -1 && nameIndex != -1 && scoreIndex != -1) {
                        int id = cursor.getInt(idIndex);
                        String name = cursor.getString(nameIndex);
                        float score = cursor.getFloat(scoreIndex);
                        sortedStudentsInfo.append("学号: ").append(id).append("\n")
                                .append("姓名: ").append(name).append("\n")
                                .append("总成绩: ").append(score).append("\n\n");
                    }
                }
                cursor.close(); // 关闭Cursor
                jeiguo.setText(sortedStudentsInfo.toString());
            } else {
                jeiguo.setText("无学生数据或排序失败");
            }
        });



    }
}
