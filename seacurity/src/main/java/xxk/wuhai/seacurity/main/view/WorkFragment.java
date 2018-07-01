package xxk.wuhai.seacurity.main.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.haibin.calendarview.CalendarView;

import java.util.Calendar;

import xxk.wuhai.seacurity.R;

/**
 * 描述 工作台
 *
 * @author ch
 * @微信 xrbswo
 * @qq 869360026
 * @email 869360026@qq.com
 * @创建时间 2018/6/24 22:22
 */
public class WorkFragment extends Fragment {
    View root;
    CalendarView calendarView;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if(root ==null) {
            root = inflater.inflate(R.layout.fragment_work, null);
        }
        return root;
    }

    private void findViews(){
        calendarView =root.findViewById(R.id.calendarView);
    }
}
