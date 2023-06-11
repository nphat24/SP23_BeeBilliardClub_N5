package mob104.fpoly.myapplication.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.List;

import mob104.fpoly.myapplication.R;
import mob104.fpoly.myapplication.activities.UpdateNvActivity;
import mob104.fpoly.myapplication.models.NhanvienModel;

public class UserAdapter extends BaseAdapter {
    List<NhanvienModel> list ;
    Context context;

    public UserAdapter(List<NhanvienModel> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View itemView;
        if (view == null) {
            itemView = View.inflate(viewGroup.getContext(), R.layout.item_user_nv, null);

        }else {
            itemView = view;
        }

        final  NhanvienModel nv = list.get(i);
        TextView tvName = itemView.findViewById(R.id.tv_name);
        TextView tvUsername = itemView.findViewById(R.id.tv_username);
        TextView tvCccd = itemView.findViewById(R.id.tv_cccd);
        TextView tvLuong = itemView.findViewById(R.id.tv_luong);
        //set text
        tvName.setText(nv.getName());
        tvUsername.setText(nv.getUsername());
        tvCccd.setText(nv.getCccd());
        tvLuong.setText(nv.getLuong()+"");

        itemView.findViewById(R.id.btn_edit).setOnClickListener(v->{
            Toast.makeText(context, nv.getId()+"", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(context, UpdateNvActivity.class);
            intent.putExtra("key", nv.getId());
            context.startActivity(intent);
        });



        itemView.setOnClickListener(v -> {
            Toast.makeText(context, "Bạn vừa click vào item", Toast.LENGTH_SHORT).show();
        });
        return itemView;
    }
}
