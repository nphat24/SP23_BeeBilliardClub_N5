package mob104.fpoly.myapplication.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

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
        itemView.findViewById(R.id.btn_del).setOnClickListener(v->{
            showDialogDelete(nv.getId());
        });



        itemView.setOnClickListener(v -> {
            Toast.makeText(context, "Bạn vừa click vào item", Toast.LENGTH_SHORT).show();
        });
        return itemView;
    }

    private void showDialogDelete(String id) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Xóa người dùng"); // Tiêu đề của dialog
        builder.setMessage("Bạn có chắc chắn muốn xóa người dùng này?"); // Nội dung thông báo

// Xử lý sự kiện khi người dùng nhấn nút "Xóa"
        builder.setPositiveButton("Xóa", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Xử lý xóa người dùng tại đây
                // Gọi hàm xóa người dùng hoặc thực hiện các tác vụ liên quan
                DatabaseReference userRef = FirebaseDatabase.getInstance().getReference().child("User").child(id);
                userRef.removeValue()
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                // Xóa người dùng thành công
                                // Thực hiện các tác vụ liên quan sau khi xóa
                                notifyDataSetChanged();
                                Toast.makeText(context, "Xóa thành công", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                // Xóa người dùng thất bại
                                // Xử lý lỗi và thông báo cho người dùng
                                Toast.makeText(context, "Xóa không thành công", Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });

// Xử lý sự kiện khi người dùng nhấn nút "Hủy"
        builder.setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss(); // Đóng dialog
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }
}
