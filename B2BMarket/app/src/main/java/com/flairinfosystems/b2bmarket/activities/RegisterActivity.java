package com.flairinfosystems.b2bmarket.activities;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.flairinfosystems.b2bmarket.R;
import com.flairinfosystems.b2bmarket.models.Contact;
import com.flairinfosystems.b2bmarket.tasks.BitmapToArray;
import com.flairinfosystems.b2bmarket.tasks.DatabaseSql;
import com.flairinfosystems.b2bmarket.tasks.RoundCropBitmap;

import java.io.IOException;

public class RegisterActivity extends AppCompatActivity {
    ProgressBar progressBar;
    DatabaseSql helper;
    private static final int PICK_IMAGE = 1;
    private TextView textView;
    private ImageView imageView;
    private Button button;
    private byte[] imageByte;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        helper=DatabaseSql.getInstance(this);
        imageView = (ImageView) findViewById(R.id.profileimg);
       textView= (TextView) findViewById(R.id.uploadtext);
        button=(Button)findViewById(R.id.signup) ;
        final TextInputLayout name=(TextInputLayout) findViewById(R.id.name);
        final TextInputLayout company=(TextInputLayout) findViewById(R.id.company);
        final TextInputLayout email=(TextInputLayout) findViewById(R.id.email);
        final TextInputLayout pass=(TextInputLayout) findViewById(R.id.password);
        final TextInputLayout confirmpass=(TextInputLayout) findViewById(R.id.confirmpass);
        // on click select an image
        imageView.setClickable(true);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectImageFromGallery();

            }
        });
       imageView.setOnLongClickListener(new View.OnLongClickListener(){
            public boolean onLongClick(View v){
                imageView.setImageResource(R.drawable.accountimg1);
                textView.setText("Tap to Upload Image");
                return true;
            }
        });
       button.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {

               progressBar = (ProgressBar) findViewById(R.id.progressBar);
               progressBar.setVisibility(View.VISIBLE);
               if(view.getId() == R.id.signup)
               {
                   String namestr =name.getEditText().getText().toString();
                   String companystr =company.getEditText().getText().toString();
                   String emailstr =email.getEditText().getText().toString();
                   String passwordstr =pass.getEditText().getText().toString();
                   String confirmpassstr =confirmpass.getEditText().getText().toString();
                   String em = helper.searchEmail(emailstr);
                   
                   BitmapDrawable drawable = (BitmapDrawable) imageView.getDrawable();
                   Bitmap bitmap = drawable.getBitmap();
                   imageByte= BitmapToArray.getBytes(bitmap);
     /*  if(namestr.isEmpty() ||companystr.isEmpty() ||emailstr.isEmpty() ||passwordstr.isEmpty() ||confirmpassstr.isEmpty()) {
                toaster("Fields are empty!!");
            }
            else if(!isValidEmail(emailstr)){
                toaster("Not a valid email!");
            }
            else  if(em.equals(emailstr)){
            toaster("Email already exists!");
            }
            else if(passwordstr.length()< 6){
             toaster("Password should have minimum length of 6");
            }
            else if (!passwordstr.equals(confirmpassstr)) {
                    toaster("Password doesn't match!!");
                }
            else {*/

                   Contact c = new Contact();
                   c.setName(namestr);
                   c.setCompany(companystr);
                   c.setEmail(emailstr);
                   c.setPass(passwordstr);
                   c.setConfirmpass(confirmpassstr);
                   c.setImageByte(imageByte);
                   helper.insertContact(c);
                   toaster("Registration successfull!");

                   Intent reg = new Intent(getApplicationContext(), LoginActivity.class);
                   reg.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                   startActivity(reg);

               }

           }
       });
    }

//**********************select from gallery********************************
    public void selectImageFromGallery() {
        Intent intent = new Intent();
// Show only images, no videos or anything else
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
// Always show the chooser (if there are multiple options available)
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
            super.onActivityResult(requestCode, resultCode, data);

            if (requestCode == PICK_IMAGE && resultCode == RESULT_OK && data != null && data.getData() != null) {

                Uri uri = data.getData();

                try {
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                    imageView.setImageBitmap(RoundCropBitmap.getCroppedBitmap(bitmap));
                    textView.setText("Hold to remove image");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
 //**********************select from gallery********************************


    private void toaster(String s)
    {
        Toast passer = Toast.makeText(RegisterActivity.this, s,Toast.LENGTH_SHORT);
        passer.show();
    }
    private static boolean isValidEmail(CharSequence target) {
        if (target == null) {
            return false;
        } else {
            return android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
        }
    }
}
