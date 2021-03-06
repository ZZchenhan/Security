package com.hz.junxinbaoan.main.view;

import android.content.Intent;
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

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import com.hz.junxinbaoan.MyApplication;
import com.hz.junxinbaoan.R;
import com.hz.junxinbaoan.bean.Result;
import com.hz.junxinbaoan.utils.DateUtils;
import com.hz.junxinbaoan.work.api.WorkDutyApi;
import com.hz.junxinbaoan.work.bean.PersonSchedulingResult;
import com.hz.junxinbaoan.work.view.MyDutyActivity;
import com.hz.junxinbaoan.work.vo.GetSchedulingByTimeVo;

/**
 * 描述 工作台
 *
 * @author ch
 * @微信 xrbswo
 * @qq 869360026
 * @email 869360026@qq.com
 * @创建时间 2018/6/24 22:22
 */
public class WorkFragment extends Fragment implements View.OnClickListener{
    View root;
    private TextView tvDate;
    private TextView tvDay1;
    private TextView tvDay2;
    private TextView tvDay3;
    private TextView tvDay4;
    private TextView tvDay5;
    private TextView tvDay6;
    private TextView tvDay7;
    private TextView title;

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


        tvDay1.setOnClickListener(this);
        tvDay2.setOnClickListener(this);
        tvDay3.setOnClickListener(this);
        tvDay4.setOnClickListener(this);
        tvDay5.setOnClickListener(this);
        tvDay6.setOnClickListener(this);
        tvDay7.setOnClickListener(this);

        title = root.findViewById(R.id.tv_title);
        title.setText(MyApplication.userDetailInfo.getOrgVo()==null||MyApplication.userDetailInfo.getOrgVo().getName() == null ?"工作台":MyApplication.userDetailInfo.getOrgVo().getName());
        root.findViewById(R.id.left_menu).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity)getActivity()).openOrClose();
            }
        });
        blue = getContext().getResources().getDrawable(R.drawable.work_head_blue);
        blue.setBounds(0,0,blue.getMinimumWidth(),blue.getMinimumHeight());
        red = getContext().getResources().getDrawable(R.drawable.work_head_read);
        red.setBounds(0,0,red.getMinimumWidth(),red.getMinimumHeight());
        setDate();
    }

    Map<String,TextView> datesViews = new HashMap<>();
    private SimpleDateFormat simpleDateFormat  = new SimpleDateFormat("yyyy-MM-dd");
    private void setDate(){
       Calendar calendar =  DateUtils.getWekFisrstDay();
        tvDate.setText(simpleDateFormat.format(calendar.getTime()).substring(0,7));
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
                                sz.tianhe.baselib.utils.ToastUtils.makeText(getContext(),personSchedulingResultResult.getMessage(), sz.tianhe.baselib.utils.ToastUtils.LENGTH_LONG).show();
                                return;
                            }
                            if(personSchedulingResultResult.getResult().getPersonSchedulingMap() == null){
                                sz.tianhe.baselib.utils.ToastUtils.makeText(getContext(),"没有更多数据", sz.tianhe.baselib.utils.ToastUtils.LENGTH_LONG).show();
                                return;
                            }
                        for (Iterator<Map.Entry<String, TextView>> it = datesViews.entrySet().iterator(); it.hasNext(); ) {
                            Map.Entry<String, TextView> entity = it.next();
                            if(personSchedulingResultResult.getResult().getPersonSchedulingMap().containsKey(entity.getKey())){
                                    PersonSchedulingResult.Recode recode = personSchedulingResultResult.getResult().getPersonSchedulingMap().get(entity.getKey());
                                    if(recode != null) {
                                        if (recode.getStatus() == 0) {
                                            //999 的话 不显示点
                                        } else if (recode.getStatus() ==2  || recode.getStatus() == 4) {
                                            entity.getValue().setCompoundDrawables(null, null, null, red);
                                        } else {
                                            entity.getValue().setCompoundDrawables(null, null, null, blue);
                                        }
                                    }
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

    @Override
    public void onResume() {
        super.onResume();
        getData();
    }

    @Override
    public void onClick(View view) {
        startActivity(new Intent(getContext(), MyDutyActivity.class));
    }
}
