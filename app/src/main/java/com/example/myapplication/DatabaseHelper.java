package com.example.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "student.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "students";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_SCORE = "score";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + TABLE_NAME + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY, " +
                COLUMN_NAME + " TEXT, " +
                COLUMN_SCORE + " REAL)";
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    // 插入学生记录（覆盖添加）
    public boolean insertStudent(Student student) {
        SQLiteDatabase db = this.getWritableDatabase();

        // 删除旧记录（如果存在）
        db.delete(TABLE_NAME, COLUMN_ID + " = ?", new String[]{String.valueOf(student.getId())});

        // 插入新记录
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, student.getId());
        values.put(COLUMN_NAME, student.getName());
        values.put(COLUMN_SCORE, student.getScore());

        long result = db.insert(TABLE_NAME, null, values);
        return result != -1; // 插入成功返回 true
    }

    // 获取所有学生记录
    public Cursor getAllStudents() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
    }

    // 删除学生记录（通过学号）
    public boolean deleteStudentById(int studentId) {
        SQLiteDatabase db = this.getWritableDatabase();
        int rowsDeleted = db.delete(TABLE_NAME, COLUMN_ID + " = ?", new String[]{String.valueOf(studentId)});
        return rowsDeleted > 0; // 如果删除行数大于0，说明删除成功
    }

    // 获取学生信息通过学号
    public Student getStudentById(int studentId) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME, null, COLUMN_ID + " = ?", new String[]{String.valueOf(studentId)}, null, null, null);

        if (cursor != null && cursor.moveToFirst()) {
            int idIndex = cursor.getColumnIndex(COLUMN_ID);
            int nameIndex = cursor.getColumnIndex(COLUMN_NAME);
            int scoreIndex = cursor.getColumnIndex(COLUMN_SCORE);

            if (idIndex != -1 && nameIndex != -1 && scoreIndex != -1) {
                int id = cursor.getInt(idIndex);
                String name = cursor.getString(nameIndex);
                float score = cursor.getFloat(scoreIndex);
                cursor.close();
                return new Student(id, name, score);
            }
            cursor.close();
        }
        return null; // 如果未找到对应学号的学生时返回 null
    }

    // 获取按成绩排序的学生记录
    public Cursor getStudentsSortedByScoreDescending() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.query(TABLE_NAME, null, null, null, null, null, COLUMN_SCORE + " DESC");
    }

}
