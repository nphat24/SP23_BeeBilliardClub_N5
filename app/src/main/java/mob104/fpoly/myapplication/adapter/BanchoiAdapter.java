package mob104.fpoly.myapplication.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import mob104.fpoly.myapplication.R;
import mob104.fpoly.myapplication.models.BanchoiModel;

public class BanchoiAdapter extends BaseAdapter {
    private Context context;
    private List<BanchoiModel> dataModels;

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
        viewOfItem.tvBanchoi.setText("Bàn "+ position);
        return convertView;
    }
}
