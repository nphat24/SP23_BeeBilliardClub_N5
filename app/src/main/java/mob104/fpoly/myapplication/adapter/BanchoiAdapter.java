package mob104.fpoly.myapplication.adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import mob104.fpoly.myapplication.R;
import mob104.fpoly.myapplication.activities.Activity_banchoi;
import mob104.fpoly.myapplication.models.BanchoiModel;

public class BanchoiAdapter extends BaseAdapter {
    private Context context;
    private List<BanchoiModel> dataModels;
    FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();


    public BanchoiAdapter(Context context, List<BanchoiModel> dataModels) {
        this.context = context;
        this.dataModels = dataModels;
    }


    @Override
    public int getCount() {
        return dataModels.size();
    }

    @Override
    public Object getItem(int position) {
        return dataModels.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    public static class ViewOfItem{
        TextView tvBanchoi;
    }

    private void SaveStartTime(int position, String startTime ){
        String path ="Table/" + position;
        DatabaseReference ref = firebaseDatabase.getReference(path);

        ref.child("start").setValue(startTime);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        ViewOfItem viewOfItem;

        if (convertView == null){
            convertView = inflater.inflate(R.layout.item_banchoi,parent,false);
            viewOfItem = new ViewOfItem();
            viewOfItem.tvBanchoi = convertView.findViewById(R.id.tv_item_banchoi);

            convertView.setTag(viewOfItem);
        }else{
            viewOfItem = (ViewOfItem) convertView.getTag();
        }

        if(dataModels.get(position).getStart().length() != 0 ){
            viewOfItem.tvBanchoi.setText(dataModels.get(position).getName());
            viewOfItem.tvBanchoi.setTextColor(Color.WHITE);
            viewOfItem.tvBanchoi.setBackgroundResource(R.drawable.background_item_dangchoi);
        }else{
            viewOfItem.tvBanchoi.setText(dataModels.get(position).getName());
            viewOfItem.tvBanchoi.setTextColor(Color.BLACK);
            viewOfItem.tvBanchoi.setBackgroundResource(R.drawable.border_radius);
        }



        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(dataModels.get(position).getStart().length() != 0 ){
                    // đang chơi
                    Intent intent = new Intent(context, Activity_banchoi.class);
                    intent.putExtra("start",dataModels.get(position).getStart());
                    intent.putExtra("price", dataModels.get(position).getPrice());

                    context.startActivity(intent);

                }else{
                    // đang trống
                    AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                    LayoutInflater inflater = ((Activity)context).getLayoutInflater();
                    View view = inflater.inflate(R.layout.dialog_batdau,null);
                    builder.setView(view);
                    AlertDialog alertDialog = builder.create();

                    Button huy = view.findViewById(R.id.dialog_button_huy);
                    Button batdau = view.findViewById(R.id.dialog_button_batdau);
                    TextView textView = view.findViewById(R.id.dialog_start);

                    Date date = new Date();
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    String currentDateTime = dateFormat.format(date);

                    textView.setText("Bắt đầu sử dụng lúc :" + currentDateTime);


                    huy.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            alertDialog.dismiss();
                        }
                    });

                    batdau.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            SaveStartTime(position, currentDateTime);
                            Intent intent = new Intent(context, Activity_banchoi.class);
                            intent.putExtra("start",currentDateTime);
                            intent.putExtra("price", dataModels.get(position).getPrice());
                            context.startActivity(intent);
                            alertDialog.dismiss();
                        }
                    });
                    alertDialog.show();
                }





            }
        });




        return convertView;
    }
}
