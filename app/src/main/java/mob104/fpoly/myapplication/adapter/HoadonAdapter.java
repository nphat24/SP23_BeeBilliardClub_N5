package mob104.fpoly.myapplication.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import mob104.fpoly.myapplication.R;
import mob104.fpoly.myapplication.activities.Activity_hoadon;
import mob104.fpoly.myapplication.activities.Activity_hoadonchitiet;
import mob104.fpoly.myapplication.models.BanchoiModel;
import mob104.fpoly.myapplication.models.HoadonModel;

public class HoadonAdapter extends BaseAdapter {
    private Context context;
    private List<HoadonModel> dataModels;

    public HoadonAdapter(Context context, List<HoadonModel> dataModels) {
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
        TextView tv_id, tv_ngay, tv_soban, tv_sogio, tv_tongtien;
        ImageView img_chitiet, img_xoa;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        ViewOfItem viewOfItem;

        if (convertView == null){
            convertView = inflater.inflate(R.layout.item_qlhoadon,parent,false);
            viewOfItem = new ViewOfItem();
            viewOfItem.tv_soban = convertView.findViewById(R.id.hoadon_soban);
            viewOfItem.tv_ngay = convertView.findViewById(R.id.hoadon_ngay);
            viewOfItem.tv_id = convertView.findViewById(R.id.hoadon_id);
            viewOfItem.tv_sogio = convertView.findViewById(R.id.hoadon_sogiochoi);
            viewOfItem.tv_tongtien = convertView.findViewById(R.id.hoadon_tongtien);
            viewOfItem.img_chitiet = convertView.findViewById(R.id.img_chitiet);

            convertView.setTag(viewOfItem);
        }else{
            viewOfItem = (ViewOfItem) convertView.getTag();
        }

        viewOfItem.tv_soban.setText(dataModels.get(position).getTable());
        viewOfItem.tv_ngay.setText(dataModels.get(position).getDay());
        viewOfItem.tv_id.setText(position+"");
        viewOfItem.tv_sogio.setText(dataModels.get(position).getTotalTime());
        viewOfItem.tv_tongtien.setText(dataModels.get(position).getTotalMoney());


        viewOfItem.img_chitiet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(context, Activity_hoadonchitiet.class);
                intent.putExtra("tonggio", dataModels.get(position).getTime());
                intent.putExtra("giatien", dataModels.get(position).getGiatien());
                intent.putExtra("giochoi", dataModels.get(position).getTotalTime());
                intent.putExtra("tongtien", dataModels.get(position).getTotalMoney());
                intent.putExtra("name",dataModels.get(position).getTable());
                intent.putExtra("check","none");
                context.startActivity(intent);
            }
        });

        return convertView;
    }
}
