//package com.example.crmapp;
//
//import static android.media.CamcorderProfile.get;
//
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.appcompat.widget.AppCompatAutoCompleteTextView;
//
//import android.os.Bundle;
//import android.view.View;
//import android.widget.AdapterView;
//import android.widget.ArrayAdapter;
//import android.widget.ListView;
//import android.widget.Toast;
//
//import com.example.crmapp.classes.ApiClient;
//import com.example.crmapp.classes.Driver;
//import com.example.crmapp.interfaces.ApiInterface;
//import com.example.crmapp.responses.StatusResponse;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import retrofit2.Call;
//import retrofit2.Callback;
//import retrofit2.Response;
//import retrofit2.Retrofit;
//import retrofit2.converter.gson.GsonConverterFactory;
//
//public class DispatchActivity extends AppCompatActivity {
//    private ArrayList<StatusResponse> modelArrayList;
//    private ApiInterface apiInterface;
//    //    private ListView lv;
//    private String BaseUrl = "http://192.168.100.73/agritech/API/";
//
//
//    String[] vehicleList = {"Vehicle 1","Vehicle 2","Vehicle 3","Vehicle 4","Vehicle 5"};
//    String[] centersList = {"Center One","Center Two","Center Three","Center Four"};
//
//    AppCompatAutoCompleteTextView driverOptions;
//    AppCompatAutoCompleteTextView vehicleOptions;
//    AppCompatAutoCompleteTextView centerOptns;
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_dispatch);
//        driverOptions = findViewById(R.id.driverOptions);
//        vehicleOptions = findViewById(R.id.vehicleOptions);
//        centerOptns = findViewById(R.id.centerOptions);
//        modelArrayList = new ArrayList<>();
//        displayRetrofitData();
//
////        ArrayAdapter<String> driverArrAdptr = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, driversList);
////        driverOptions.setAdapter(driverArrAdptr);
//
//        ArrayAdapter<String> vehicleArrAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, vehicleList);
////        vehicleOptions.setAdapter(vehicleArrAdapter);
//
//        ArrayAdapter<String> centerArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,centersList );
//        centerOptns.setAdapter(centerArrayAdapter);
//
//        driverOptions.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                if(position == 1){
//                    vehicleOptions.setAdapter(new ArrayAdapter<String>(DispatchActivity.this, android.R.layout.simple_list_item_1, vehicleList));
//                }
//
//
//            }
//        });
//    }
//
//
//    private void displayRetrofitData() {
//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl(BaseUrl)
//                .addConverterFactory(GsonConverterFactory.create())
//                .build();
//        apiInterface = retrofit.create(ApiInterface.class);
//        Call<StatusResponse> arrayListCall = apiInterface.getAllDriver();
//        arrayListCall.enqueue(new Callback<ArrayList<StatusResponse>>() {
//            @Override
//            public void onResponse(Call<ArrayList<StatusResponse>> call, Response<ArrayList<StatusResponse>> response) {
//                modelArrayList = response.body();
//                String[] stringArr = new String[modelArrayList.size()];
//
//                for (int i = 0; i < modelArrayList.size(); i++) {
//                    stringArr[i] = modelArrayList.get(i).getF_name() + " " + modelArrayList.get(i).getL_name();
//                }
////                Custom custom = new Custom(modelArrayList, DispatchActivity.this, R.layout.singleview);
////                lv.setAdapter(custom);
//
//                driverOptions.setAdapter(new ArrayAdapter<String>(DispatchActivity.this, android.R.layout.simple_list_item_1, stringArr));
//            }
//
//
//
//            @Override
//            public void onFailure(Call<ArrayList<StatusResponse>> call, Throwable t) {
//                Toast.makeText(getApplicationContext(), "An error occured", Toast.LENGTH_LONG).show();
//            }
//        });
//
//
//    }
//}