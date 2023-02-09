
package com.example.crmapp

import android.annotation.SuppressLint
import android.app.Activity
import android.app.AlertDialog
import android.bluetooth.BluetoothDevice
import android.bluetooth.BluetoothGattCharacteristic
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SimpleItemAnimator
import com.example.crmapp.ble.*
import kotlinx.android.synthetic.main.activity_ble_operations.*
import java.text.SimpleDateFormat
import java.util.*
import kotlin.experimental.and


//@RequiresApi(Build.VERSION_CODES.O)
@SuppressLint("NewApi")

class BleOperationsActivity : AppCompatActivity() {
    var valueDecoded = ""
    private lateinit var device: BluetoothDevice
    private val dateFormatter = SimpleDateFormat("MMM d, HH:mm:ss", Locale.US)
    private val characteristics by lazy {
        ConnectionManager.servicesOnDevice(device)?.flatMap { service ->
            service.characteristics ?: listOf()
        } ?: listOf()
    }
    private val characteristicProperties by lazy {
        characteristics.map { characteristic ->
            characteristic to mutableListOf<CharacteristicProperty>().apply {
                if (characteristic.isNotifiable()) add(CharacteristicProperty.Notifiable)
                if (characteristic.isIndicatable()) add(CharacteristicProperty.Indicatable)
                if (characteristic.isReadable()) add(CharacteristicProperty.Readable)
                if (characteristic.isWritable()) add(CharacteristicProperty.Writable)
                if (characteristic.isWritableWithoutResponse()) {
                    add(CharacteristicProperty.WritableWithoutResponse)
                }
            }.toList()
        }.toMap()
    }
    private val characteristicAdapter: CharacteristicAdapter by lazy {
        CharacteristicAdapter(characteristics) { characteristic ->
            showCharacteristicOptions(characteristic)
//            Toast.makeText(this@BleOperationsActivity, "I am here", Toast.LENGTH_LONG).show()/
        }
    }
    private var notifyingCharacteristics = mutableListOf<UUID>()

    override fun onCreate(savedInstanceState: Bundle?) {
        ConnectionManager.registerListener(connectionEventListener)
        super.onCreate(savedInstanceState)
        device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE)
            ?: error("Missing BluetoothDevice from MainActivity!")
        setContentView(R.layout.activity_ble_operations)
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowTitleEnabled(true)
            title = getString(R.string.ble_playground)
        }
        setupRecyclerView()
        request_mtu_button.setOnClickListener {
            if (mtu_field.text.isNotEmpty() && mtu_field.text.isNotBlank()) {
                mtu_field.text.toString().toIntOrNull()?.let { mtu ->
                    log("Requesting for MTU value of $mtu")
                    ConnectionManager.requestMtu(device, mtu)
                } ?: log("Invalid MTU value: ${mtu_field.text}")
            } else {
                log("Please specify a numeric value for desired ATT MTU (23-517)")
            }
            hideKeyboard()
        }


        getWeightBtn.setOnClickListener{
            if(characteristics.isNotEmpty()){

                Intent(this@BleOperationsActivity, PurchaseActivity::class.java).also {
                    it.putExtra("weightValue", valueDecoded)
                    startActivity(it)
                }
//                ConnectionManager.unregisterListener(this)
            }
        }
    }

    override fun onDestroy() {
        ConnectionManager.unregisterListener(connectionEventListener)
        ConnectionManager.teardownConnection(device)
        super.onDestroy()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun setupRecyclerView() {
        characteristics_recycler_view.apply {
            adapter = characteristicAdapter
            layoutManager = LinearLayoutManager(
                this@BleOperationsActivity,
                RecyclerView.VERTICAL,
                false
            )
            isNestedScrollingEnabled = false
        }

        val animator = characteristics_recycler_view.itemAnimator
        if (animator is SimpleItemAnimator) {
            animator.supportsChangeAnimations = false
        }
    }

    @SuppressLint("SetTextI18n")
    private fun log(message: String) {
        val formattedMessage = String.format("%s: %s", dateFormatter.format(Date()), message)
        runOnUiThread {
            val currentLogText = if (log_text_view.text.isEmpty()) {
                "Beginning of log."
            } else {
                log_text_view.text
            }
            log_text_view.text = "$currentLogText\n$formattedMessage"
            log_scroll_view.post { log_scroll_view.fullScroll(View.FOCUS_DOWN) }
        }
    }


    private fun showCharacteristicOptions(characteristic: BluetoothGattCharacteristic) {
        characteristicProperties[characteristic]?.let { properties ->
//            val prop = "Read"
//            log("Reading from ${properties.map { it.action }}")
//
//            if(properties.map { it.action }.contains(prop)){
//                ConnectionManager.readCharacteristic(device, characteristic)
//            }
            val notProp = "Toggle Notifications"
            if(properties.map { it.action }.contains(notProp)){
                ConnectionManager.enqueueOperation(EnableNotifications(device, characteristic.uuid))


            }
            //properties return
//            val builder = AlertDialog.Builder(this)
//            builder.setTitle("Select an action to perform")
//            builder.setMessage(properties.map { it.action }::class.java.typeName)

//            builder.setSingleChoiceItems(properties.map { it.action }, -1) {_,i ->
//                when (properties[i]) {
//                    CharacteristicProperty.Readable -> {
//                        log("Reading from ${characteristic.uuid}")
//                        ConnectionManager.readCharacteristic(device, characteristic)
//                    }
//                    CharacteristicProperty.Writable, CharacteristicProperty.WritableWithoutResponse -> {
//                        showWritePayloadDialog(characteristic)
//                    }
//                    CharacteristicProperty.Notifiable, CharacteristicProperty.Indicatable -> {
//                        if (notifyingCharacteristics.contains(characteristic.uuid)) {
//                            log("Disabling notifications on ${characteristic.uuid}")
//                            ConnectionManager.disableNotifications(device, characteristic)
//                        } else {
//                            log("Enabling notifications on ${characteristic.uuid}")
//                            ConnectionManager.enableNotifications(device, characteristic)
//                        }
//                    }
//                }
//            }
//            val dialog :AlertDialog = builder.create()
//            dialog.show()
//            selector("Select an action to perform", properties.map { it.action }) { _, i ->
//                when (properties[i]) {
//                    CharacteristicProperty.Readable -> {
//                        log("Reading from ${characteristic.uuid}")
//                        ConnectionManager.readCharacteristic(device, characteristic)
//                    }
//                    CharacteristicProperty.Writable, CharacteristicProperty.WritableWithoutResponse -> {
//                        showWritePayloadDialog(characteristic)
//                    }
//                    CharacteristicProperty.Notifiable, CharacteristicProperty.Indicatable -> {
//                        if (notifyingCharacteristics.contains(characteristic.uuid)) {
//                            log("Disabling notifications on ${characteristic.uuid}")
//                            ConnectionManager.disableNotifications(device, characteristic)
//                        } else {
//                            log("Enabling notifications on ${characteristic.uuid}")
//                            ConnectionManager.enableNotifications(device, characteristic)
//                        }
//                    }
//                }
//            }
        }
    }

    @SuppressLint("InflateParams")
    private fun showWritePayloadDialog(characteristic: BluetoothGattCharacteristic) {
        val hexField = layoutInflater.inflate(R.layout.edittext_hex_payload, null) as EditText
        val builder = AlertDialog.Builder(this@BleOperationsActivity)
        with(builder){
            setTitle("Disconnected")
            setMessage("Disconnected from device.");
            setPositiveButton("OK" ) {
                    _, _ -> with(hexField.text.toString()) {
                if (isNotBlank() && isNotEmpty()) {
                    val bytes = hexToBytes()
                    log("Writing to ${characteristic.uuid}: ${bytes.toHexString()}")
                    ConnectionManager.writeCharacteristic(device, characteristic, bytes)
                } else {
                    log("Please enter a hex payload to write to ${characteristic.uuid}")
                }
            }
            }
            setNegativeButton("No"){
                _,_->{}
            }
            val alertDialog: AlertDialog = builder.create()
            alertDialog.setCancelable(false)
            alertDialog.show()
            hexField.showKeyboard()
        }

    }

    private val connectionEventListener by lazy {
        ConnectionEventListener().apply {
            onDisconnect = {
                runOnUiThread {
                    val builder = AlertDialog.Builder(this@BleOperationsActivity)
                    with(builder){
                        setTitle("Disconnected")
                        setMessage("Disconnected from device.");
                        setPositiveButton("OK" ) {
                                _, _ -> {}
                        }
                        val alertDialog: AlertDialog = builder.create()
                        alertDialog.show()
                    }

                }
            }


             onCharacteristicRead = { _, characteristic ->
                 val decodeValue = characteristic.value.decodeToString()
                log("Reads from ${characteristic.uuid}: $decodeValue")

                log("Readers are from here : $decodeValue and our thingy follows ");

            }

            onCharacteristicWrite = { _, characteristic ->
                log("Wrote to ${characteristic.uuid}")
            }

            onMtuChanged = { _, mtu ->
                log("MTU updated to $mtu")
            }

            onCharacteristicChanged = { _, characteristic ->
                val decodedHexString = characteristic.value.decodeToString();
                valueDecoded = decodedHexString
                log("Value changed to $decodedHexString")
            }

            onNotificationsEnabled = { _, characteristic ->
                log("Enabled notifications on ${characteristic.uuid}")
                notifyingCharacteristics.add(characteristic.uuid)
            }

            onNotificationsDisabled = { _, characteristic ->
                log("Disabled notifications on ${characteristic.uuid}")
                notifyingCharacteristics.remove(characteristic.uuid)
            }
        }
    }

    private enum class CharacteristicProperty {
        Readable,
        Writable,
        WritableWithoutResponse,
        Notifiable,
        Indicatable;

        val action
            get() = when (this) {
                Readable -> "Read"
                Writable -> "Write"
                WritableWithoutResponse -> "Write Without Response"
                Notifiable -> "Toggle Notifications"
                Indicatable -> "Toggle Indications"
            }
    }

    private fun Activity.hideKeyboard() {
        hideKeyboard(currentFocus ?: View(this))
    }

    private fun Context.hideKeyboard(view: View) {
        val inputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
    }

    private fun EditText.showKeyboard() {
        val inputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        requestFocus()
        inputMethodManager.showSoftInput(this, InputMethodManager.SHOW_IMPLICIT)
    }

    private fun String.hexToBytes() =
        this.chunked(2).map { it.toUpperCase(Locale.US).toInt(16).toByte() }.toByteArray()
}
