//package com.example.crmapp.adapters;
//
//import android.content.Context;
//import android.content.DialogInterface;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.BaseAdapter;
//import android.widget.ImageButton;
//import android.widget.TextView;
//
//import androidx.appcompat.app.AlertDialog;
//
//import com.example.crmapp.R;
//import com.example.crmapp.classes.Driver;
//
//import java.util.ArrayList;
//
//public class DriverListAdapter extends BaseAdapter {
//
//    Context context;
//    ArrayList<Driver> lstDriver;
//    LayoutInflater inflater;
//
//
//
//    public DriverListAdapter(Context _context, ArrayList<Driver> _list){
//        context = _context;
//        lstDriver = _list;
//        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//    }
//    @Override
//    public int getCount() {
//        return lstDriver.size();
//    }
//
//    @Override
//    public Object getItem(int position) {
//        return lstDriver.get(position);
//    }
//
//    @Override
//    public long getItemId(int position) {
//        return 0;
//    }
//
//    @Override
//    public View getView(int position, View convertView, ViewGroup parent) {
//        DriverDataHolder holder = new DriverDataHolder();
//
//        View view = inflater.inflate(R.layout.list_item,null);
//
//        holder.tvFName = view.findViewById(R.id.tvfName);
//        holder.tvLName = view.findViewById(R.id.tvlName);
//        holder.tvEmail = view.findViewById(R.id.tvEmail);
//        holder.tvPhone_no = view.findViewById(R.id.tvPhone_no);
//        holder.tvClose = view.findViewById(R.id.tvCLose);
//        holder.tvStatus = view.findViewById(R.id.tvStatus);
//
//
//        holder.tvFName.setText(lstDriver.get(position).getF_name());
//        holder.tvLName.setText(lstDriver.get(position).getL_name());
//        holder.tvEmail.setText(lstDriver.get(position).getEmail());
//        holder.tvPhone_no.setText(lstDriver.get(position).getPhone_no());
//        holder.tvClose.setText(lstDriver.get(position).getClose());
//        holder.tvStatus.setText(lstDriver.get(position).getStatus());
//
//
//        holder.ibEdit.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//            }
//        });
//
//        holder.ibDelete.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
//
//                alertDialogBuilder
//                        .setMessage("Are you sure you want to Delete this record?")
//                        .setCancelable(true)
//                        .setPositiveButton("YES", new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialogInterface, int i) {
////                                DeleteDriver();
//                            }
//                        })
//                        .setNegativeButton("NO", new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialogInterface, int i) {
////                                dialog.cancel();
//                            }
//                        });
//                AlertDialog alertDialog = alertDialogBuilder.create();
//                alertDialog.show();
//            }
//        });
//
//        return null;
//    }
//    public class DriverDataHolder{
//        public ImageButton ibEdit, ibDelete;
//        public TextView tvFName,tvLName,tvEmail,tvPhone_no, tvClose, tvStatus;
//    }
//}
