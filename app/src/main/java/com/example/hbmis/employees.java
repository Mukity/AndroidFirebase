package com.example.hbmis;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.firebase.client.Firebase;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class employees extends AppCompatActivity {
    EditText first_Name, last_Name, employee_no, ID_number, E_mail;
    Button clear, Submit;


    // Declaring String variable ( In which we are storing firebase server URL )
    public static final String Firebase_Server_URL ="https://nurture-baby.firebaseio.com/";

    //declaring string variables to store data
    String firstNameHolder,lastNameHolder,EmployeeNoHolder,IDNumberHolder,EmailHolder;

    Firebase firebase;
    DatabaseReference databaseReference;

    // Root Database Name for Firebase Database.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employees);

        Firebase.setAndroidContext(employees.this);
        firebase = new Firebase(Firebase_Server_URL);
        databaseReference = FirebaseDatabase.getInstance().getReference();

        first_Name = (EditText) findViewById(R.id.first_name);
        last_Name = (EditText) findViewById(R.id.last_name);
        employee_no = (EditText) findViewById(R.id.empno);
        ID_number = (EditText) findViewById(R.id.idno);
        E_mail = (EditText) findViewById(R.id.e_mail);

        clear = (Button) findViewById(R.id.clear);
        Submit = (Button) findViewById(R.id.submit);
///
        Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userDetails uDetails = new userDetails();
                GetDataFromEditText();

                //Adding the details to the class function object
                uDetails.setfname(firstNameHolder);
                uDetails.setlName(lastNameHolder);
                uDetails.setEmpNo(EmployeeNoHolder);
                uDetails.setIDNo(IDNumberHolder);
                uDetails.setEmail(EmailHolder);

                // Getting the ID from firebase database.
                String userRecordIdFromServer = databaseReference.push().getKey();

                //adding the detail values using userDetails class object using ID
                databaseReference.child(userRecordIdFromServer).setValue(uDetails);

                Toast.makeText(employees.this,"User data added successfully!", Toast.LENGTH_LONG).show();
            }
        });
        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                first_Name.setText("");
                last_Name.setText("");
                employee_no.setText("");
                ID_number.setText("");
                E_mail.setText("");
            }
        });

    }
    public void GetDataFromEditText(){
        firstNameHolder = first_Name.getText().toString().trim();
        lastNameHolder = last_Name.getText().toString().trim();
        EmployeeNoHolder = employee_no.getText().toString().trim();
        IDNumberHolder = ID_number.getText().toString().trim();
        EmailHolder = E_mail.getText().toString().trim();
    }
}
