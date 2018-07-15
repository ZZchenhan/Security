package xxk.wuhai.seacurity.main.view;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.blankj.utilcode.util.ToastUtils;
import com.haibin.calendarview.CalendarView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import xxk.wuhai.seacurity.MyApplication;
import xxk.wuhai.seacurity.R;
import xxk.wuhai.seacurity.bean.Result;
import xxk.wuhai.seacurity.utils.DateUtils;
import xxk.wuhai.seacurity.work.api.WorkDutyApi;
import xxk.wuhai.seacurity.work.bean.PersonSchedulingResult;
import xxk.wuhai.seacurity.work.vo.GetSchedulingByTimeVo;

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
    private TextView tvDate;
    private TextView tvDay1;
    private TextView tvDay2;
    private TextView tvDay3;
    private TextView tvDay4;
    private TextView tvDay5;
    private TextView tvDay6;
    private TextView tvDay7;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if(root ==null) {
            root = inflater.inflate(R.layout.fragment_work, null);
            findViews();
        }
        return root;
    }

    private void findViews(){
        tvDate = root.findViewById(R.id.date);
        tvDay1 = root.findViewById(R.id.day1);
        tvDay2 = root.findViewById(R.id.day2);
        tvDay3 = root.findViewById(R.id.day3);
        tvDay4 = root.findViewById(R.id.day4);
        tvDay5 = root.findViewById(R.id.day5);
        tvDay6 = root.findViewById(R.id.day6);
        tvDay7 = root.findViewById(R.id.day7);

        root.findViewById(R.id.left_menu).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity)getActivity()).openOrClose();
            }
        });
        blue = getContext().getDrawable(R.drawable.work_head_blue);
        blue.setBounds(0,0,blue.getMinimumWidth(),blue.getMinimumHeight());
        red = getContext().getDrawable(R.drawable.work_head_read);
        red.setBounds(0,0,red.getMinimumWidth(),red.getMinimumHeight());
        setDate();
    }

    Map<String,TextView> datesViews = new HashMap<>();
    private SimpleDateFormat simpleDateFormat  = new SimpleDateFormat("yyyy-MM-dd");
    private void setDate(){
       Calendar calendar =  DateUtils.getWekFisrstDay();
        tvDate.setText(simpleDateFormat.format(calendar.getTime()));
       tvDay1.setText(""+calendar.get(Calendar.DAY_OF_MONTH));
       datesViews.put( simpleDateFormat.format(calendar.getTime()),tvDay1);
       calendar.set(calendar.DAY_OF_MONTH,calendar.get(Calendar.DAY_OF_MONTH)+1);
       tvDay2.setText(""+calendar.get(Calendar.DAY_OF_MONTH));
        datesViews.put(  simpleDateFormat.format(calendar.getTime()),tvDay2);
        calendar.set(calendar.DAY_OF_MONTH,calendar.get(Calendar.DAY_OF_MONTH)+1);
       tvDay3.setText(""+calendar.get(Calendar.DAY_OF_MONTH));
        datesViews.put(  simpleDateFormat.format(calendar.getTime()),tvDay3);
        calendar.set(calendar.DAY_OF_MONTH,calendar.get(Calendar.DAY_OF_MONTH)+1);
       tvDay4.setText(""+calendar.get(Calendar.DAY_OF_MONTH));
        datesViews.put(  simpleDateFormat.format(calendar.getTime()),tvDay4);
        calendar.set(calendar.DAY_OF_MONTH,calendar.get(Calendar.DAY_OF_MONTH)+1);
       tvDay5.setText(""+calendar.get(Calendar.DAY_OF_MONTH));
        datesViews.put(  simpleDateFormat.format(calendar.getTime()),tvDay5);
        calendar.set(calendar.DAY_OF_MONTH,calendar.get(Calendar.DAY_OF_MONTH)+1);
       tvDay6.setText(""+calendar.get(Calendar.DAY_OF_MONTH));
        datesViews.put(  simpleDateFormat.format(calendar.getTime()),tvDay6);
        calendar.set(calendar.DAY_OF_MONTH,calendar.get(Calendar.DAY_OF_MONTH)+1);
       tvDay7.setText(""+calendar.get(Calendar.DAY_OF_MONTH));
        datesViews.put(  simpleDateFormat.format(calendar.getTime()),tvDay7);
        getData();
    }

    private void getData(){
        MyApplication.retrofitClient.getRetrofit()
                .create(WorkDutyApi.class)
                .getTimesScheduling(new GetSchedulingByTimeVo(
                        simpleDateFormat.format(DateUtils.getWekLastDay().getTime()),
                        simpleDateFormat.format(DateUtils.getWekFisrstDay().getTime()),
                        MyApplication.userDetailInfo.getUserInfo().getUserId()
                )).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Result<PersonSchedulingResult>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Result<PersonSchedulingResult> personSchedulingResultResult) {
                            if(!personSchedulingResultResult.getCode().equals("200")){
                                ToastUtils.showShort(personSchedulingResultResult.getMessage());
                                return;
                            }
                            if(personSchedulingResultResult.getResult().getPersonSchedulingMap() == null){
                                ToastUtils.showShort("没有更多数据");
                                return;
                            }
                        for (Iterator<Map.Entry<String, TextView>> it = datesViews.entrySet().iterator(); it.hasNext(); ) {
                            Map.Entry<String, TextView> entity = it.next();
                            if(personSchedulingResultResult.getResult().getPersonSchedulingMap().containsKey(entity.getKey())){
                                entity.getValue().setCompoundDrawables(null,null,null,blue);
                            }else{
                                entity.getValue().setCompoundDrawables(null,null,null,red);
                            }
                        }
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    Drawable blue;
    Drawable red;
}
