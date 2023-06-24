package com.example.wifiscaner;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class InfoAboutWiFi extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_about_wi_fi);

        Intent intent = getIntent();
        String SSID = intent.getStringExtra("SSID");
        String BSSID = intent.getStringExtra("BSSID");
        String Level = intent.getStringExtra("Level");
        String Encryption = intent.getStringExtra("Encryption");
        String Frequency = intent.getStringExtra("Frequency");
        String Capabilities = intent.getStringExtra("Capabilities");
        TextView txtSSID = findViewById(R.id.textSSID);
        TextView txtInfo = findViewById(R.id.textInfo);
        txtSSID.setText(SSID);

        txtInfo.setText("BSSID: "+BSSID.toString()+"\nLevel: "+Level.toString()+"\nEncryption: "+Encryption.toString()+"\nFrequency: "+Frequency.toString()+"\nCapabilities: "+Capabilities.toString());
    }
}