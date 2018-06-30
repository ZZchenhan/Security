package xxk.wuhai.seacurity.me.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import xxk.wuhai.seacurity.R;

/**
 * Created by 86936 on 2018/6/30.
 *
 * @QQ 869360026
 */

public class MeInfoFragment extends Fragment {
    View root;

    private Button btnEdit;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if(root == null){
            root = inflater.inflate(R.layout.fragment_me_info,null);
            findViews();
        }
        return root;
    }

    private void findViews(){
        btnEdit = root.findViewById(R.id.edit);


        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MeInfoActivity)getActivity()).change();
            }
        });
    }
}
