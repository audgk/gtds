package com.withcoding.myapplication;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import net.alhazmy13.gota.Gota;
import net.alhazmy13.gota.GotaResponse;

public class MainActivity extends AppCompatActivity implements Gota.OnRequestPermissionsBack {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new Gota.Builder(this)
                .withPermissions(Manifest.permission.SEND_SMS)
                .requestId(1)
                .setListener(this)
                .check();
        Button button = findViewById(R.id.button);
        final EditText phone_number = findViewById(R.id.editText);
        final EditText text = findViewById(R.id.editText2);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.fromParts(text.getText().toString(), phone_number.getText().toString(), null));
                if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.SEND_SMS) == PackageManager.PERMISSION_GRANTED){
                    // startActivity(intent);
                    SmsManager.getDefault().sendTextMessage(phone_number.getText().toString(), null, text.getText().toString(), null, null);
                }
            }
        });
    }
    @Override
    public void onRequestBack(int requestId, @NonNull GotaResponse gotaResponse) {
        if(gotaResponse.isGranted(Manifest.permission.SEND_SMS)) {
            // Your Code
        }
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
