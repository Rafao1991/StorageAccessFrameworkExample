package com.example.rafaelsousa.storageaccessframeworkexample;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.Log;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;

public class MainActivity extends AppCompatActivity {

    private final int READ_REQUEST_CODE = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        intent.setType("*/*");
        startActivityForResult(intent, READ_REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == READ_REQUEST_CODE && resultCode == RESULT_OK && data != null) {
            final Uri uri = data.getData();
            Log.d("MainActivity", "Uri: " + uri.toString());
            byte[] fileByteArray = new byte[(int) uri.toString().length()];

            try {
                FileInputStream fileInputStream =
                        (FileInputStream) getContentResolver().openInputStream(data.getData());
                fileInputStream.read(fileByteArray);
            } catch (Exception e) {
                e.printStackTrace();
            }

            String encodedFile = Base64.encodeToString(fileByteArray, Base64.NO_WRAP);
            Toast.makeText(this, encodedFile, Toast.LENGTH_LONG).show();
        }
    }
}
