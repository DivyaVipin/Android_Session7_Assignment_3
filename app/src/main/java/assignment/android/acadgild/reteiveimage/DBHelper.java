package assignment.android.acadgild.reteiveimage;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by DivyaVipin on 1/4/2017.
 */
public class DBHelper  extends SQLiteOpenHelper{
    private static final String DB_NAME = "Employee.db";
    private static final int DB_VERSION_NUMBER = 1;
    private static final String DB_TABLE_NAME = "EmployeeDetails";
    public static final String EMP_ID = "id";
    private static final String DB_COLUMN_EMPLOYEE_NAME = "employee_name";
    private static final String DB_COLUMN_EMPLOYEE_AGE = "employee_age";
    private static final String DB_COLUMN_EMPLOYEE_PHOTO= "employee_photo";

    private static final String CREATE_EMPLOYEES_TABLE = "create table "
            + DB_TABLE_NAME + " (" + EMP_ID
            + " integer primary key autoincrement, " + DB_COLUMN_EMPLOYEE_PHOTO
            + " blob not null, " + DB_COLUMN_EMPLOYEE_NAME + " text not null unique, "
            + DB_COLUMN_EMPLOYEE_AGE + " integer );";

    private SQLiteDatabase sqDBInstance = null;
    public DBHelper(Context context)

    {
        super(context, DB_NAME, null, DB_VERSION_NUMBER);
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        Log.i("onCreate", "Creating the database...");
        sqLiteDatabase.execSQL(CREATE_EMPLOYEES_TABLE );
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + DB_TABLE_NAME);
        onCreate(db);
    }


    public void openDB() throws SQLException {
        Log.i("openDB", "Checking sqliteDBInstance...");
        if(this.sqDBInstance == null) {
            Log.i("openDB", "Creating sqliteDBInstance...");
            this.sqDBInstance = this.getWritableDatabase();
        }
    }
    public void closeDB() {
        if(this.sqDBInstance != null) {
            if(this.sqDBInstance.isOpen())
                this.sqDBInstance.close();
        }
    }
    public void insertEmployee(Employee employee)
    {
        ContentValues cv=new ContentValues() ;

        cv.put(DB_COLUMN_EMPLOYEE_PHOTO, Utility.getBytes(employee.getPhoto()));//Calling static function for converting photo to bytes
        cv.put(DB_COLUMN_EMPLOYEE_NAME, employee.getName());
        cv.put(DB_COLUMN_EMPLOYEE_AGE, employee.getAge());
        sqDBInstance.insert(DB_TABLE_NAME, null, cv);
    }
    public Employee retriveEmpDetails() throws SQLException {
        Cursor cur = sqDBInstance.query(true,DB_TABLE_NAME, new String[] { DB_COLUMN_EMPLOYEE_PHOTO,DB_COLUMN_EMPLOYEE_NAME,DB_COLUMN_EMPLOYEE_AGE}, null, null, null, null, null, null);
        if (cur.moveToFirst()) {
            byte[] blob = cur.getBlob(cur.getColumnIndex(DB_COLUMN_EMPLOYEE_PHOTO));
            String name = cur.getString(cur.getColumnIndex(DB_COLUMN_EMPLOYEE_NAME));
            int age = cur.getInt(cur.getColumnIndex(DB_COLUMN_EMPLOYEE_AGE));
            cur.close();
            return new Employee(Utility.getPhoto(blob), name, age);
        }
        cur.close();
        return null;
    }


}
