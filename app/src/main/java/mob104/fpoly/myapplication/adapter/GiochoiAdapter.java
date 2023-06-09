package mob104.fpoly.myapplication.adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import mob104.fpoly.myapplication.R;
import mob104.fpoly.myapplication.models.BanchoiModel;

public class GiochoiAdapter extends BaseAdapter {
    private Context context;
    private List<BanchoiModel> dataModels;

    public GiochoiAdapter(Context context, List<BanchoiModel> dataModels) {
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
        TextView tv_name, tv_trangthai, tv_btn_time;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        ViewOfItem viewOfItem;

        if (convertView == null){
            convertView = inflater.inflate(R.layout.item_lv_giochoi_nhanvien,parent,false);
            viewOfItem = new ViewOfItem();
            viewOfItem.tv_name = convertView.findViewById(R.id.tv_name);
            viewOfItem.tv_trangthai = convertView.findViewById(R.id.tv_trangthai);
            viewOfItem.tv_btn_time = convertView.findViewById(R.id.tv_btn_time);


            convertView.setTag(viewOfItem);
        }else{
            viewOfItem = (ViewOfItem) convertView.getTag();
        }

        if(dataModels.get(position).getStart().length() != 0 ){
            viewOfItem.tv_name.setText(dataModels.get(position).getName());
            viewOfItem.tv_trangthai.setText("Đang sử dụng");

            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

            try {
                Date startTime = dateFormat.parse(dataModels.get(position).getStart());
                Date endDate = new Date(); // Lấy thời điểm hiện tại
                long duration = endDate.getTime() - startTime.getTime();
                long hours = duration / (60 * 60 * 1000);

                viewOfItem.tv_btn_time.setText(hours + "h");
            } catch (ParseException e) {
                e.printStackTrace();
            }

        }else{
            viewOfItem.tv_name.setText(dataModels.get(position).getName());
            viewOfItem.tv_trangthai.setText("Đang trống");
            viewOfItem.tv_btn_time.setText("0h");
        }

        return convertView;
    }
}
