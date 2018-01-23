package com.sourcey.materiallogindemo;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.sourcey.materiallogindemo.utility.TaskContract;

import java.io.IOException;
import java.lang.*;

/**
 * Created by thenu on 22-01-2018.
 */

public class Family extends Activity {
    private static int RESULT_LOAD_IMAGE = 1;
    private Uri filePath;

    private Bitmap bitmap;

    private  ImageView img;

    private EditText edRelationShip;

    private Button bAdded;
    Intent imageSelection ;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.familyperson);
        img = (ImageView) findViewById(R.id.iv_profile_img);
        edRelationShip = (EditText) findViewById(R.id.editTextTaskDescription);
        bAdded = (Button) findViewById(R.id.addButton);


        img.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View arg0) {
                imageSelection = new Intent(
                        Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(imageSelection, RESULT_LOAD_IMAGE);
            }

        });

        bAdded.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                // Insert new task data via a ContentResolver
                // Create new empty ContentValues object
                ContentValues contentValues = new ContentValues();
                // Put the task description and selected mPriority into the ContentValues
                contentValues.put(TaskContract.TaskEntry.COLUMN_PRIORITY, edRelationShip.getText().toString());
                contentValues.put(TaskContract.TaskEntry.COLUMN_DESCRIPTION, filePath.getEncodedPath());
                // Insert the content values via a ContentResolver
                Uri uri = getContentResolver().insert(TaskContract.TaskEntry.CONTENT_URI, contentValues);


//                Intent data = new Intent();
//                data.putExtra("relationShip", edRelationShip.getText().toString());
//                data.putExtra("filePath", filePath);
//                setResult(RESULT_OK, data);
//                finish();

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && data != null && data.getData() != null) {
            filePath = data.getData();
            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
                img.setImageBitmap(bitmap);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


}