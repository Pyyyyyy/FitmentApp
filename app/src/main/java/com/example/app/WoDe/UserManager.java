package com.example.app.WoDe;

/**
 * Created by 1 on 2017/12/24.
 */
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.app.WoDe.User;

public class UserManager {
    private static final String TAG = "UserDataManager";
    private static final String DB_NAME = "User";
    private static final String TABLE_NAME = "Users";
    public static final String USER_NAME = "username";
    public static final String USER_PWD = "password";
    private static final int DB_VERSION = 1;
    private Context mContext = null;

    public UserManager(Context context){
        mContext = context;
    }
    //创建用户表
    private static final String CREATE_TABLE = "create table "+TABLE_NAME+" ("
            +"id integer primary key autoincrement,"
            +"username varchar,"
            +"password varchar,"
            +"name varchar,"
            +"phone integer)";

    private SQLiteDatabase mSQLiteDatabase = null;
    private DataBaseManagementHelper mDatabaseHelper = null;

    private static class DataBaseManagementHelper extends SQLiteOpenHelper{
        DataBaseManagementHelper(Context context){
            super(context,DB_NAME,null,DB_VERSION);

        }
        @Override
        public void onCreate(SQLiteDatabase db){
            db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
            db.execSQL(CREATE_TABLE);
        }
        @Override
        public  void onUpgrade(SQLiteDatabase db,int oldVersion,int newVersion){
            onCreate(db);
        }

    }
    //打开数据库
    public void openDataBase() throws SQLException{
        mDatabaseHelper = new DataBaseManagementHelper(mContext);
        mSQLiteDatabase = mDatabaseHelper.getWritableDatabase();
    }
    //关闭数据库
    public void closeDataBase() throws SQLException {
        mDatabaseHelper.close();
    }

    //添加新用户，即注册
    public long insertUserData(User userData) {
        String username = userData.getUsername();
        String name = userData.getName();
        int phone = userData.getPhone();
        String password = userData.getPassword();
        ContentValues values = new ContentValues();
        values.put("username", username);
        values.put("name", name);
        values.put("phone", phone);
        values.put("password", password);
        return mSQLiteDatabase.insert(TABLE_NAME, null, values);
    }
    //根据用户名找用户，可以判断注册时用户名是否已经存在
    public int findUserByName(String userName){
        int result = 0;
        Cursor Cursor = mSQLiteDatabase.query(TABLE_NAME, null, USER_NAME+"="+userName, null, null, null, null);
        if(Cursor != null){
            result = Cursor.getCount();
            Cursor.close();
        }
        return result;
    }

    //根据用户名和密码找用户，用于登录
    public int findUserByNameAndPwd(String userName,String pwd){
        int result=0;
        Cursor Cursor = mSQLiteDatabase.query(TABLE_NAME, null, USER_NAME+"="+userName+" and "+USER_PWD+"="+pwd,
                null, null, null, null);
        if(Cursor!=null){
            result = Cursor.getCount();
            Cursor.close();
        }
        return result;
    }


}
