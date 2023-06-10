package mob104.fpoly.myapplication.adapter;

import android.app.Activity;
import android.content.Context;
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

public class QLPhongbanAdapter extends BaseAdapter {
    private Context context;
    private List<BanchoiModel> dataModels;

    public QLPhongbanAdapter(Context context, List<BanchoiModel> dataModels) {
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
        TextView tv_soban, tv_idban, tv_trangthai, tv_giochoi, tv_tien, btn_edit_qlpb, btn_delete_qlpb;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        ViewOfItem viewOfItem;

        if (convertView == null){
            convertView = inflater.inflate(R.layout.item_qlphongban,parent,false);
            viewOfItem = new ViewOfItem();
            viewOfItem.tv_soban = convertView.findViewById(R.id.tv_soban);
            viewOfItem.tv_idban = convertView.findViewById(R.id.tv_idban);
            viewOfItem.tv_trangthai = convertView.findViewById(R.id.tv_trangthai);
            viewOfItem.tv_giochoi = convertView.findViewById(R.id.tv_giochoi);
            viewOfItem.tv_tien = convertView.findViewById(R.id.tv_tien);


            convertView.setTag(viewOfItem);
        }else{
            viewOfItem = (ViewOfItem) convertView.getTag();
        }

        viewOfItem.tv_tien.setText(dataModels.get(position).getPrice() + " đ");
        viewOfItem.tv_idban.setText("ID: " + position+"");

        if(dataModels.get(position).getStart().length() != 0 ){
            viewOfItem.tv_soban.setText(dataModels.get(position).getName() + " Billiard");
            viewOfItem.tv_trangthai.setText("Đang sử dụng");

            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

            try {
                Date startTime = dateFormat.parse(dataModels.get(position).getStart());
                Date endDate = new Date(); // Lấy thời điểm hiện tại
                long duration = endDate.getTime() - startTime.getTime();
                long hours = duration / (60 * 60 * 1000);
                viewOfItem.tv_giochoi.setText(hours + "h");
            } catch (ParseException e) {
                e.printStackTrace();
            }

        }else{
            viewOfItem.tv_soban.setText(dataModels.get(position).getName() + " Billiard");
            viewOfItem.tv_trangthai.setText("Đang trống");
            viewOfItem.tv_giochoi.setText("0h");
        }

        return convertView;
    }
}
