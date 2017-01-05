package assignment.android.acadgild.reteiveimage;

import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
private DBHelper dbHelper;//Creating an instance of DBHelper class created for Database operations
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dbHelper=new DBHelper(MainActivity.this);
        Employee employee_One = new Employee(BitmapFactory.decodeResource(
                getResources(), R.drawable.photo), "Venkat", 25);//Getting Image from Resource drawable folder
        dbHelper.openDB();//Opening DBConnection
        dbHelper.insertEmployee(employee_One);//Insering EmployeeOject of type Employee
        employee_One=null;
        employee_One=dbHelper.retriveEmpDetails();//After inserting data in Database retreived the details from Database
        dbHelper.closeDB();
        TextView empname = (TextView) findViewById(R.id.textViewPersonName);
        empname.setText(employee_One.getName());//Getting objects name
        ImageView empphoto = (ImageView) findViewById(R.id.imageViewPerson);
        empphoto.setImageBitmap(employee_One.getPhoto());

        TextView empage = (TextView) findViewById(R.id.textViewPersonAge);
        empage.setText("" + employee_One.getAge());

    }
}
