package com.flairinfosystems.b2bmarket.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.flairinfosystems.b2bmarket.R;
import com.flairinfosystems.b2bmarket.tasks.DatabaseSql;
import com.flairinfosystems.b2bmarket.tasks.SaveSharedPreference;


public class LoginActivity extends AppCompatActivity {
    DatabaseSql helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme_NoActionBar);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        helper= DatabaseSql.getInstance(this);

    }

    public void onSignupTextClick(View v) {
        Intent reg = new Intent(getApplicationContext(), RegisterActivity.class);
        reg.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(reg);

    }

    public void onSubmitClick(View v) {
        if (v.getId() == R.id.submit) {
            TextInputLayout e = (TextInputLayout) findViewById(R.id.email);
            TextInputLayout p = (TextInputLayout) findViewById(R.id.password);
            String emailstr = e.getEditText().getText().toString();
            String passwordstr = p.getEditText().getText().toString();
            String passer = helper.searchPass(emailstr);
            if (passer.equals("not found password")) {
                toaster("Invalid credentials!!");
            } else if (passwordstr.equals(passer)) {
                toaster("Login Success");
                SaveSharedPreference.setPrefEmail(getApplicationContext(),emailstr);
                Intent reg = new Intent(getApplicationContext(), Main2Activity.class);
                reg.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                reg.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(reg);
                finish();
            }
            else {
                toaster("Invalid credentials");
            }
        }

    }


    private void toaster(String s) {
        Toast passer = Toast.makeText(LoginActivity.this, s, Toast.LENGTH_SHORT);
        passer.show();
    }
}