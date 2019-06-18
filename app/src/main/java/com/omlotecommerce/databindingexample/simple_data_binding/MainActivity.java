package com.omlotecommerce.databindingexample.simple_data_binding;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.omlotecommerce.databindingexample.R;
import com.omlotecommerce.databindingexample.databinding.ActivityMainBinding;
import com.omlotecommerce.databindingexample.two_way_binding.ContactsActivity;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding activityMainBinding;
    private MainActivityClickHandlers mainActivityClickHandlers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        activityMainBinding.setStudent(getCurrentStudent());

        mainActivityClickHandlers = new MainActivityClickHandlers(this);
        activityMainBinding.setClickHandler(mainActivityClickHandlers);

    }

    private Student getCurrentStudent() {
        Student student = new Student();
        student.setStudentName("Alex");
        student.setStudentEmail("alex@gmail.com");
        return student;
    }

    public class MainActivityClickHandlers {

        private Context context;

        public MainActivityClickHandlers(Context context) {
            this.context = context;
        }

        public void onEnrollButtonClicked(View view) {
            Toast.makeText(context, "Enroll Clicked!", Toast.LENGTH_SHORT).show();
        }

        public void onCancelButtonClicked(View view) {
            Toast.makeText(context, "Cancelled Clicked!", Toast.LENGTH_SHORT).show();
        }

        public void onFABClicked(View view) {
            startActivity(new Intent(MainActivity.this, ContactsActivity.class));
        }

    }

}
