package com.example.crud_operations;

import static java.security.AccessController.getContext;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    Spinner spinner;
    DatabaseHelper db;
    Button btn;
    TextView tx;
    String[] data = {"" , "Student_Data" , "Teacher_data" , "Admin_Data"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        db = new DatabaseHelper(MainActivity.this);
        spinner = findViewById(R.id.spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(MainActivity.this , android.R.layout.simple_spinner_item , data);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        btn = findViewById(R.id.showRecords);
        tx = findViewById(R.id.displayRecords);
        Cursor cursor = db.ViewData();
        Cursor cursor1 = db.teacherData();
        Cursor cursor2 = db.adminData();
        StringBuilder stringBuilder = new StringBuilder();
        StringBuilder stringBuilder1 = new StringBuilder();
        StringBuilder stringBuilder2 = new StringBuilder();
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    String shwData = spinner.getSelectedItem().toString();
                    if(shwData.equals("Student_Data"))
                    {
                        while(cursor.moveToNext())
                        {
                            stringBuilder.append("Student_id : " + cursor.getInt(0)
                                    + "\nStudent Name " + cursor.getString(1)
                                    + "\nDuration of Admission" + cursor.getString(2)
                                    + "\nStudent udergrad description : " + cursor.getString(3)
                                    + "\n" +
                                    "Job before admission : " + cursor.getString(4)
                            );

                        }
                        tx.setText(stringBuilder);
                    }
                    else if (shwData.equals("Teacher_data")) {
                        while(cursor1.moveToNext())
                        {
                            stringBuilder1.append("Teacher_id : " + cursor1.getInt(0)
                                    + "\nTeacher Name " + cursor1.getString(1)
                                    + "\nAddrress : " + cursor1.getString(2)

                            );

                        }
                        tx.setText(stringBuilder1);
                    } else if (shwData.equals("Admin_Data")) {
                       while(cursor2.moveToNext())
                       {
                           stringBuilder2.append("Admin_id : " + cursor2.getInt(0)
                                   + "\nDepartment of Admin " + cursor2.getString(1)
                                   + "\nAdmin Addrress : " + cursor2.getString(2)

                           );
                       }
                        tx.setText(stringBuilder2);

                    }
                    else {
                        String msg = "Please select valid option from dropdown you have selected none";
                        tx.setText(msg);
                    }
            }
        });

    }
}