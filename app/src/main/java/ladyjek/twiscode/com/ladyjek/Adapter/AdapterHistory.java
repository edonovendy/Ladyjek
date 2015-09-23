package ladyjek.twiscode.com.ladyjek.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


import org.droidparts.contract.HTTP;

import java.util.Collections;
import java.util.List;

import ladyjek.twiscode.com.ladyjek.Model.ModelHistory;
import ladyjek.twiscode.com.ladyjek.Model.ModelOrder;
import ladyjek.twiscode.com.ladyjek.R;

/**
 * Created by Unity on 30/07/2015.
 */
public class AdapterHistory extends RecyclerView.Adapter<AdapterHistory.MyViewHolder>   {
    List<ModelHistory> data = Collections.emptyList();
    private LayoutInflater inflater;
    private Context context;
    private Activity act;

    public AdapterHistory(Context context, List<ModelHistory> data) {
        this.context = context;
        inflater = LayoutInflater.from(context);
        this.data = data;
        this.act = (Activity) context;
    }

    public void delete(int position) {
        data.remove(position);
        notifyItemRemoved(position);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.row_passenger_item, parent, false);
        MyViewHolder holder = new MyViewHolder(view);


        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {

        final int pos = position;
        String asal = "",tgl="",biaya="",tujuan="";

        try {
            asal = data.get(position).getFrom();
        }
        catch (Exception ex){
            ex.printStackTrace();
        }

        try {
            tujuan = data.get(position).getDestination();
        }
        catch (Exception ex){
            ex.printStackTrace();
        }

        try {
            biaya = data.get(position).getPrice();
        }
        catch (Exception ex){
            ex.printStackTrace();
        }

        try {
            tgl = data.get(position).getDate();
        }
        catch (Exception ex){
            ex.printStackTrace();
        }

        String[] from = asal.split(", ");
        String[] dest = tujuan.split(", ");
        holder.asal.setText(Html.fromHtml("<b>"+from[0]+"</b><br/>"+from[1]+", "+from[2]+", "+from[3]+", "+from[4]));
        holder.tujuan.setText(Html.fromHtml("<b>"+dest[0]+"</b><br/>"+dest[1]+", "+dest[2]+", "+dest[3]+", "+dest[4]));
        holder.biaya.setText("Rp. "+biaya);
        holder.tgl.setText(tgl);

        holder.row.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("pos row",""+position);
            }
        });



    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView asal,tujuan,biaya,tgl;
        LinearLayout row;
        public MyViewHolder(View itemView) {
            super(itemView);
            asal = (TextView) itemView.findViewById(R.id.passengerFrom);
            tujuan = (TextView) itemView.findViewById(R.id.passengerDestination);
            biaya = (TextView) itemView.findViewById(R.id.passengerPrice);
            tgl = (TextView) itemView.findViewById(R.id.passengerDate);
            row = (LinearLayout)itemView.findViewById(R.id.layout_row);
        }
    }

    private void SendBroadcast(String typeBroadcast,String type){
        Intent intent = new Intent(typeBroadcast);
        // add data
        intent.putExtra("message", type);
        LocalBroadcastManager.getInstance(act).sendBroadcast(intent);
    }
}
