package com.example.wifiscaner;

import static com.example.wifiscaner.ListAdapter.nets;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    /*private
    private WifiManager wifiManager;

    private List<ScanResult> results;
    private int size = 0;
    private ArrayList<String> arrayList = new ArrayList<>();
    private ArrayAdapter adapter;
    private */

    public WifiManager wifiManager;
    //private List<ScanResult> results;
    private Button btnScan;
    TextView text;
    TextView text1;
    BroadcastReceiver receiver;
    ListView listView;
    ListAdapter listAdapter;
    List list;
    @SuppressLint("WifiManagerLeak")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnScan = findViewById(R.id.button_start);
        this.wifiManager = (WifiManager) getSystemService(Context.WIFI_SERVICE);
        listView = (ListView) findViewById(R.id.listview);
        receiver = new WifiReceiver();
        registerReceiver(receiver, new IntentFilter(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION));

        btnScan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Snackbar.make(view, "Scanning...", Snackbar.LENGTH_SHORT).setAction("Action", null).show();
                checkPermission();

            }
        });



        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getApplicationContext(), InfoAboutWiFi.class);
                intent.putExtra("SSID",nets[i].getSsid().toString());
                intent.putExtra("BSSID",nets[i].getBssid().toString());
                intent.putExtra("Level",nets[i].getLevel().toString());
                intent.putExtra("Encryption",nets[i].getEncryption().toString());
                intent.putExtra("Frequency",nets[i].getFrequency().toString());
                intent.putExtra("Capabilities",nets[i].getCapabilities().toString());
                startActivity(intent);

            }
        });
    }

    private void scanWiFiList() {

        wifiManager.startScan();
        list = wifiManager.getScanResults();
        setAdapter();

    }

    private void checkPermission() {

        if(ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.ACCESS_FINE_LOCATION)!= PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION},0);

        }
        else
        {
            scanWiFiList();
        }

    }

    private void setAdapter() {
        listAdapter= new ListAdapter(getApplicationContext(),list);
        listView.setAdapter(listAdapter);
    }

    class WifiReceiver extends BroadcastReceiver{
        @Override
        public void onReceive(Context context, Intent intent) {

        }
    }


}