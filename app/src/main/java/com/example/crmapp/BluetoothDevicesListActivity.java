package com.example.crmapp;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;

import java.util.Set;

public class BluetoothDevicesListActivity extends AppCompatActivity {
    //Declaration of Object classes to be used and other variables
    private MaterialButton scanDevices;
    private BluetoothAdapter mBluetoothAdapter;
    public static final int REQUEST_ACCESS_FINE_LOCATION = 1;
    public static final int REQUEST_ENABLE_BLUETOOTH = 11;
    private ArrayAdapter<String> listAdapter;
    private ListView listOfDevices;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bluetooth_devices_list);
        //instantiate and assigning variables respective values
        scanDevices = findViewById(R.id.scanDevices);
        listOfDevices = findViewById(R.id.listOfDevices);
        mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        //Arraylist to to show found devices
        listAdapter = new ArrayAdapter<String>(this, R.layout.deviec_list_item);
        listOfDevices.setAdapter(listAdapter);

        checkStateOfBluetoothOnDevice();
        scanDevices.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkStateOfBluetoothOnDevice();
            }
        });


    }

    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver(devicesFound, new IntentFilter(BluetoothDevice.ACTION_FOUND));
        registerReceiver(devicesFound, new IntentFilter(BluetoothAdapter.ACTION_DISCOVERY_STARTED));
        registerReceiver(devicesFound, new IntentFilter(BluetoothAdapter.ACTION_DISCOVERY_FINISHED));
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(devicesFound);
    }

    private boolean checkLocationPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_ACCESS_FINE_LOCATION);
            return false;
        }
        return true;
    }

    private void checkStateOfBluetoothOnDevice() {
        if (mBluetoothAdapter != null && mBluetoothAdapter.isEnabled()) {
            if (checkLocationPermission()) {
                listAdapter.clear();
                mBluetoothAdapter.startDiscovery();
            }
        } else {
            //Checking if bluetooth is enabled on device
            if (mBluetoothAdapter == null) {
                Toast.makeText(BluetoothDevicesListActivity.this, "Bluetooth not supported on this device", Toast.LENGTH_LONG).show();
            } else {
                //If bluetooth is enabled, checking if its enabled
                if (mBluetoothAdapter.isEnabled()) {
                    if (mBluetoothAdapter.isDiscovering()) {
                        Toast.makeText(BluetoothDevicesListActivity.this, "Discovering devices", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(BluetoothDevicesListActivity.this, "Bluetooth is disabled", Toast.LENGTH_SHORT).show();
                        scanDevices.setEnabled(true);
                    }
                } else {
                    //Bluetooth not enabled, therefore start intent for enabling bluetooth
                    Toast.makeText(BluetoothDevicesListActivity.this, "You need to enable bluetooth", Toast.LENGTH_SHORT).show();
                    Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                    startActivityForResult(enableBtIntent, REQUEST_ENABLE_BLUETOOTH);
                }
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_ENABLE_BLUETOOTH) {
            checkStateOfBluetoothOnDevice();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case REQUEST_ACCESS_FINE_LOCATION:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(BluetoothDevicesListActivity.this, "Access of location allowed, you can scan for devices", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(BluetoothDevicesListActivity.this, "Access of location denied, please allow then scan for devices", Toast.LENGTH_LONG).show();

                }
                break;
        }
    }

    //Received to get devices detected
    private final BroadcastReceiver devicesFound = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (BluetoothDevice.ACTION_FOUND.equals(action)) {
                BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                if (ActivityCompat.checkSelfPermission(BluetoothDevicesListActivity.this, Manifest.permission.BLUETOOTH_CONNECT) != PackageManager.PERMISSION_GRANTED) {
                    {
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S)
                        {
                            ActivityCompat.requestPermissions(BluetoothDevicesListActivity.this, new String[]{Manifest.permission.BLUETOOTH_CONNECT}, 2);
                            return;
                        }
                    }
                }
                listAdapter.add(device.getName() + "\n" + device.getAddress());
                listAdapter.notifyDataSetChanged();
            } else if (BluetoothAdapter.ACTION_DISCOVERY_FINISHED.equals(action)) {
                scanDevices.setText(R.string.scanning);
            } else if (BluetoothAdapter.ACTION_DISCOVERY_STARTED.equals(action)) {
                scanDevices.setText(R.string.scanning_in_progress);
            }
        }
    };

    private void ListPairedDevices() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.BLUETOOTH_CONNECT) != PackageManager.PERMISSION_GRANTED) {
            {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S)
                {
                    ActivityCompat.requestPermissions(BluetoothDevicesListActivity.this, new String[]{Manifest.permission.BLUETOOTH_CONNECT}, 2);
                    return;
                }
            }
        }
        Set<BluetoothDevice> mPairedDevices = mBluetoothAdapter.getBondedDevices();
        if (mPairedDevices.size() > 0) {
            for (BluetoothDevice mDevice : mPairedDevices) {
                Log.v(TAG, "PairedDevices: " + mDevice.getName() + "  "
                        + mDevice.getAddress());
            }
        }
    }
}