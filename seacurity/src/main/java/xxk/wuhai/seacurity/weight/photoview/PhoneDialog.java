package xxk.wuhai.seacurity.weight.photoview;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import xxk.wuhai.seacurity.R;
import xxk.wuhai.seacurity.weight.date.DatePickerDialogFragment;

/**
 * Created by 86936 on 2018/7/26.
 *
 * @QQ 869360026
 */

public class PhoneDialog  extends DialogFragment {
    private List<String> url = new ArrayList<>();
    private List<PhotoFragment> photoFragments = new ArrayList<>();
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_photo, container);
        ViewPager viewPager = view.findViewById(R.id.viewpager);
        view.findViewById(R.id.cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
        TextView tvCount = view.findViewById(R.id.tv_num);
        url.addAll(getArguments().getStringArrayList("data"));
        for(int i=0;i<url.size();i++){
            photoFragments.add(PhotoFragment.newInstance(url.get(i)));
        }

        viewPager.setAdapter(new FragmentPagerAdapter(getChildFragmentManager()) {
            @Override
            public Fragment getItem(int i) {
                return photoFragments.get(i);
            }

            @Override
            public int getCount() {
                return photoFragments.size();
            }
        });
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                tvCount.setText((i+1)+"/"+url.size());
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
        viewPager.setCurrentItem(getArguments().getInt("index"));
        tvCount.setText((getArguments().getInt("index")+1)+"/"+url.size());
        initChild();
        return view;
    }

    protected void initChild() {

    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = new Dialog(getActivity(), R.style.DatePickerBottomDialog);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE); // 设置Content前设定

        dialog.setContentView(R.layout.layout_photo);
        dialog.setCanceledOnTouchOutside(true); // 外部点击取消

        Window window = dialog.getWindow();
        if (window != null) {
            WindowManager.LayoutParams lp = window.getAttributes();
            lp.gravity = Gravity.BOTTOM; // 紧贴底部
            lp.width = WindowManager.LayoutParams.MATCH_PARENT; // 宽度持平
            lp.height = WindowManager.LayoutParams.MATCH_PARENT; // 宽度持平
            lp.dimAmount = 0.35f;
            window.setAttributes(lp);
            window.addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        }

        return dialog;
    }


    public static void seePic(AppCompatActivity activity, List<String> url, int postion){
        PhoneDialog datePickerDialogFragment = new PhoneDialog();
        Bundle bundle = new Bundle();
        bundle.putStringArrayList("data", (ArrayList<String>) url);
        bundle.putInt("index",postion);
        datePickerDialogFragment.setArguments(bundle);
        datePickerDialogFragment.show(activity.getSupportFragmentManager(), "DatePickerDialogFragment");
    }
}
