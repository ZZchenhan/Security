package com.hz.junxinbaoan.work.adapter;

import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;

import com.amap.api.maps2d.AMapUtils;
import com.amap.api.maps2d.CameraUpdateFactory;
import com.amap.api.maps2d.MapView;
import com.amap.api.maps2d.model.BitmapDescriptorFactory;
import com.amap.api.maps2d.model.CircleOptions;
import com.amap.api.maps2d.model.LatLng;
import com.amap.api.maps2d.model.Marker;
import com.amap.api.maps2d.model.MarkerOptions;
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import sz.tianhe.baselib.utils.ToastUtils;
import com.hz.junxinbaoan.R;
import com.hz.junxinbaoan.bean.RecoderBean;
import com.hz.junxinbaoan.weight.mapwindows.MapWindows;
import com.hz.junxinbaoan.weight.record.CountTimeText;
import com.hz.junxinbaoan.work.bean.scheduling.AttendanceInfoVoListBean;

/**
 * Created by 86936 on 2018/6/29.
 *
 * @QQ 869360026
 */

public class RecordAdapter extends BaseMultiItemQuickAdapter<AttendanceInfoVoListBean,BaseViewHolder> {
    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param data A new list is created out of this one to avoid mutable list
     */
    public RecordAdapter(List<AttendanceInfoVoListBean> data) {
        super(data);
        addItemType(0, R.layout.item_record_no_locion_issu);
        addItemType(1, R.layout.item_record_locatopm);
    }

    // 打卡类型 0：上班 1：中途 2：下班 ,
    private String revodeType(String type){
        if(null == type) return "错误";
        switch (type){
            case "0":
                return "上班";
            case "1":
                return "中途";
            case "2":
              return "下班";
        }
        return "错误";
    }


    public String getType(String type){
        if(null == type) return "错误";
        switch (type){
            case "0":
                return "日常";
            case "1":
                return "加班";
            case "2":
                return "临时";
        }
        return "错误";
    }

    //，0 初始 1.补卡 2.迟到 3.早退 4.正常 5.缺卡 6请假
    private  String status(String status ){
        if(status == null){
            return "未打卡";
        }
        switch (status){
            case "0":
                return "未打卡";
            case "1":
                return "已补卡";
            case "2":
                return "迟到";
            case "3":
                return "早退";
            case "4":
                return "已打卡";
            case "5":
                return "未打卡";
            case "6":
                return "请假";
        }
        return "未打卡";
    }
    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("HH:mm:ss");
    public   static long timeLong = System.currentTimeMillis();
    private String getTime(String time){
        try {
            Date date = simpleDateFormat.parse(time);
            if(date.getTime() - timeLong>1000*60*60*2){
                return "未打卡";
            }else if(date.getTime() - timeLong>0){
                return "打卡";
            }
            else if (date.getTime() - timeLong>-1000*60*60*2){
                return simpleDateFormat2.format(new Date(date.getTime() - timeLong));
            }else{
                return "未开始";
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return "异常";
    }


    @Override
    protected void convert(BaseViewHolder helper, AttendanceInfoVoListBean item) {
        helper.addOnClickListener(R.id.btn_record);
        helper.addOnClickListener(R.id.tv_apply);
        try{
            helper.setText(R.id.record_type,revodeType(item.getAttendanceType()));
        }catch (Exception e){
            helper.setText(R.id.record_type,"错误");
        }
        helper.setText(R.id.type,"类型:"+getType(item.getType()));
        helper.setText(R.id.cls_name,"班次:"+item.getScheduleShortName());
        helper.setText(R.id.status,status(item.getStatus()));
        helper.setTextColor(R.id.status,item.getStatus()!=null && item.getStatus().equals("0")
                || item.getStatus().equals("5")
                ||
                item.getStatus().equals("2")
                ||
                item.getStatus().equals("3")
                ?Color.parseColor("#F43530"):
                mContext.getResources().getColor(R.color.colorPrimary));
        helper.setText(R.id.time,"要求时间："+item.getAttendanceTimeExpect()==null?"":item.getAttendanceTimeExpect());
        ((CountTimeText)helper.getView(R.id.btn_record)).changeNomarl();
        if((item.getAttendanceLatExpect() == null
                || item.getAttendanceLatExpect().equals("")) ||
                (item.getAttendanceLonExpect() == null || item.getAttendanceLonExpect().equals("")) ||
                item.getAttendanceLocationExpect().equals("任意位置")){
            ////这里0 做倒计时 5做未打卡 6请假
            if(item.getStatus().equals("0") || item.getStatus().equals("5") || item.getStatus().equals("6")){
                helper.setGone(R.id.ll_can_apply,true);
                helper.setGone(R.id.ll_over,false);
                long currentTime = System.currentTimeMillis();
                if(item.getStatus().equals("6")){
                    helper.setText(R.id.btn_record,"已请假");
                    helper.setTextColor(R.id.btn_record,Color.parseColor("#F4F4F4"));
                    helper.setBackgroundRes(R.id.btn_record,R.drawable.bg_record_no_click);
                    helper.getView(R.id.btn_record).setEnabled(false);
                    helper.setGone(R.id.tv_apply,false);
                } else if(item.getStatus().equals("5")){
                    //前去补卡
                    helper.setText(R.id.btn_record,"未打卡");
                    helper.setTextColor(R.id.btn_record,Color.parseColor("#F4F4F4"));
                    helper.setBackgroundRes(R.id.btn_record,R.drawable.bg_record_no_click);
                    helper.getView(R.id.btn_record).setEnabled(false);
                    helper.setGone(R.id.tv_apply,true);
                }else {
                    //开始做倒计时
                    helper.setGone(R.id.tv_apply,false);
                    helper.getView(R.id.btn_record).setEnabled(true);
                    //1、判断不能打卡逻辑。 太早，不在范围之类。台完
                    long realTime = getRecodeTime(item.getAttendanceTimeExpect());

                    if(item.isLastDay()){
                        //这个跨天，选择日期+1
                        realTime = realTime+24 *60 *60 *1000;
                    }
                    if(currentTime<realTime-(item.getPreLimit()*1000*60) -(60*1000*60)){
                        //太早realTIme 1532194955924 currnet 1532162608449
                        helper.setText(R.id.btn_record,"未开始");
                        helper.setTextColor(R.id.btn_record,Color.parseColor("#F4F4F4"));
                        helper.setBackgroundRes(R.id.btn_record,R.drawable.bg_record_no_click);
                        helper.getView(R.id.btn_record).setEnabled(false);
                        helper.setGone(R.id.tv_apply,false);
                    }else if(currentTime<realTime-item.getPreLimit()*60*1000){
                        //显示倒计时
                        ((CountTimeText)helper.getView(R.id.btn_record)).changesTimer();
                        final long distance = realTime-item.getPreLimit()*60*1000;
                        ((CountTimeText)helper.getView(R.id.btn_record)).setTimeChangeListener(new CountTimeText.OnTimeChangeListener() {
                            @Override
                            public void timeChanges() {
                                if(distance-System.currentTimeMillis()<=0){
                                    notifyDataSetChanged();
                                    return;
                                }
                                helper.setText(R.id.btn_record,getOverTimes(distance-System.currentTimeMillis()));
                            }
                        });

                        helper.setTextColor(R.id.btn_record,Color.parseColor("#F4F4F4"));
                        helper.setBackgroundRes(R.id.btn_record,R.drawable.bg_record_no_click);
                        helper.getView(R.id.btn_record).setEnabled(false);
                        helper.setGone(R.id.tv_apply,false);
                    } else if(currentTime>realTime+item.getPostLimit()*1000*60+1000*60){
                        //太晚，让他补卡
                        helper.setText(R.id.btn_record,"未打卡");
                        helper.setTextColor(R.id.btn_record,Color.parseColor("#F4F4F4"));
                        helper.setBackgroundRes(R.id.btn_record,R.drawable.bg_record_no_click);
                        helper.getView(R.id.btn_record).setEnabled(false);
                        helper.setGone(R.id.tv_apply,true);
                    }else{
                        helper.setText(R.id.btn_record, "打卡");
                        helper.setTextColor(R.id.btn_record, Color.parseColor("#FFFFFF"));
                        helper.setBackgroundRes(R.id.btn_record, R.drawable.bg_record_click);
                        helper.getView(R.id.btn_record).setEnabled(true);
                        helper.setGone(R.id.tv_apply, false);
                    }

                }
            }else{
                //已经打过卡，不管是补卡还是什么
                helper.setGone(R.id.ll_can_apply,false);
                helper.setGone(R.id.ll_over,true);
                helper.setText(R.id.attention_addreess,item.getAttendanceLocation());
                helper.setText(R.id.attention_times,item.getAttendanceTime());
            }
        }else{
            helper.addOnClickListener(R.id.refresh);
            //有打卡位置
            helper.setText(R.id.adress,item.getAttendanceLocationExpect());
            MapView mapView = helper.getView(R.id.map_view);
            if(mapView.getTag() ==null) {
                initMap(mapView);
                mapView.getViewTreeObserver().addOnGlobalLayoutListener(() -> {
                    ViewGroup child = (ViewGroup)  mapView.getChildAt(0);//地图框架
                    child.getChildAt(2).setVisibility(View.GONE);//logo
                });
                mapView.setTag(item);
                mapView.onCreate(null);
            }
            if(item.getRange() == null || item.getRange().equals("")){
               toast("后台传递范围为："+item.getRange());
                return;
            }
            if(item.getAttendanceLatExpect() != null && item.getAttendanceLonExpect() == null){
               toast("后台传递维度为："+item.getAttendanceLatExpect());
                return;
            }
            if(item.getAttendanceLonExpect() == null || item.getAttendanceLonExpect().equals("")){
               toast("后台传递精度为："+item.getAttendanceLatExpect());
                return;
            }
            helper.setText(R.id.range,item.getRange()==null?"0米":item.getRange()+"米内");
            mapView.getMap().clear();
            Marker marker = null;
            if(RecoderBean.currentLatLng != null) {
                marker = mapView.getMap().addMarker(new MarkerOptions().position(RecoderBean.currentLatLng).title("打卡地址").snippet("打卡地址").
                        icon(BitmapDescriptorFactory.fromBitmap(BitmapFactory
                        .decodeResource(mContext.getResources(), R.mipmap.icon_poi_select))));
                float range = Float.parseFloat(item.getRange());
                if(range<50){
                    mapView.getMap().moveCamera(CameraUpdateFactory.newLatLngZoom(RecoderBean.currentLatLng, 17));
                }else if(range <200){
                    mapView.getMap().moveCamera(CameraUpdateFactory.newLatLngZoom(RecoderBean.currentLatLng, 16));
                }else if(range <500){
                    mapView.getMap().moveCamera(CameraUpdateFactory.newLatLngZoom(RecoderBean.currentLatLng, 15));
                }else if(range<1000){
                    mapView.getMap().moveCamera(CameraUpdateFactory.newLatLngZoom(RecoderBean.currentLatLng, 14));
                }else if(range<5000){
                    mapView.getMap().moveCamera(CameraUpdateFactory.newLatLngZoom(RecoderBean.currentLatLng, 13));
                }
            }
            LatLng latLng = new LatLng(Float.parseFloat(item.getAttendanceLatExpect()),Float.parseFloat(item.getAttendanceLonExpect()));
            mapView.getMap().addCircle(new CircleOptions().
                    center(latLng).
                    radius(Double.parseDouble(item.getRange())).
                    fillColor(Color.parseColor("#20197ABD")).
                    strokeColor(Color.parseColor("#197ABD")).
                    strokeWidth(1));

            ////这里0 做倒计时 5做未打卡 6请假
            if(item.getStatus().equals("0") || item.getStatus().equals("5") || item.getStatus().equals("6")){
                helper.setGone(R.id.ll_can_apply,true);
                helper.setGone(R.id.ll_over,false);
                long currentTime = System.currentTimeMillis();
                if(item.getStatus().equals("6")){
                    helper.setText(R.id.btn_record,"已请假");
                    helper.setTextColor(R.id.btn_record,Color.parseColor("#F4F4F4"));
                    helper.setBackgroundRes(R.id.btn_record,R.drawable.bg_record_no_click);
                    helper.getView(R.id.btn_record).setEnabled(false);
                    helper.setGone(R.id.tv_apply,false);
                } else if(item.getStatus().equals("5")){
                    //前去补卡
                    helper.setText(R.id.btn_record,"未打卡");
                    helper.setTextColor(R.id.btn_record,Color.parseColor("#F4F4F4"));
                    helper.setBackgroundRes(R.id.btn_record,R.drawable.bg_record_no_click);
                    helper.getView(R.id.btn_record).setEnabled(false);
                    helper.setGone(R.id.tv_apply,true);
                }else {
                    //开始做倒计时
                    helper.setGone(R.id.tv_apply,false);
                    helper.getView(R.id.btn_record).setEnabled(true);
                    //1、判断不能打卡逻辑。 太早，不在范围之类。台完
                    long realTime = getRecodeTime(item.getAttendanceTimeExpect());
                    if(item.isLastDay()){
                        //这个跨天，选择日期+1
                        realTime = realTime+24 *60 *60 *1000;
                    }
                    if(currentTime<realTime-(item.getPreLimit()*1000*60) -(60*1000*60)){
                        //太早realTIme 1532194955924 currnet 1532162608449
                        helper.setText(R.id.btn_record,"未开始");
                        helper.setTextColor(R.id.btn_record,Color.parseColor("#F4F4F4"));
                        helper.setBackgroundRes(R.id.btn_record,R.drawable.bg_record_no_click);
                        helper.getView(R.id.btn_record).setEnabled(false);
                        helper.setGone(R.id.tv_apply,false);
                    }else if(currentTime<realTime-item.getPreLimit()*60*1000){
                        //显示倒计时
                            ((CountTimeText)helper.getView(R.id.btn_record)).changesTimer();
                        final long distance = realTime-item.getPreLimit()*60*1000;
                        ((CountTimeText)helper.getView(R.id.btn_record)).setTimeChangeListener(new CountTimeText.OnTimeChangeListener() {
                            @Override
                            public void timeChanges() {
                                if(distance-System.currentTimeMillis()<=0){
                                    notifyDataSetChanged();
                                    return;
                                }
                                helper.setText(R.id.btn_record,getOverTimes(distance-System.currentTimeMillis()));
                            }
                        });
                        helper.setText(R.id.btn_record,getOverTimes(realTime-currentTime-item.getPreLimit()*60*1000));
                        helper.setTextColor(R.id.btn_record,Color.parseColor("#F4F4F4"));
                        helper.setBackgroundRes(R.id.btn_record,R.drawable.bg_record_no_click);
                        helper.getView(R.id.btn_record).setEnabled(false);
                        helper.setGone(R.id.tv_apply,false);
                    } else if(currentTime>realTime+item.getPostLimit()*1000*60 +1000*60){
                        //太晚，让他补卡
                        helper.setText(R.id.btn_record,"未打卡");
                        helper.setTextColor(R.id.btn_record,Color.parseColor("#F4F4F4"));
                        helper.setBackgroundRes(R.id.btn_record,R.drawable.bg_record_no_click);
                        helper.getView(R.id.btn_record).setEnabled(false);
                        helper.setGone(R.id.tv_apply,true);
                    }else if(marker!=null && AMapUtils.calculateLineDistance(RecoderBean.currentLatLng,latLng)>Double.parseDouble(item.getRange())) {
                        //不在距离
                        helper.setText(R.id.btn_record, "不在打卡区域");
                        helper.setTextColor(R.id.btn_record, Color.parseColor("#F4F4F4"));
                        helper.setBackgroundRes(R.id.btn_record, R.drawable.bg_record_no_click);
                        helper.getView(R.id.btn_record).setEnabled(false);
                        helper.setGone(R.id.tv_apply, false);
                        MapWindows mapWindows =    new MapWindows(mContext);
                        mapWindows.initData( AMapUtils.calculateLineDistance(RecoderBean.currentLatLng,latLng)+"",item.getAttendanceLocationExpect()+"",RecoderBean.poi);
                        mapView.getMap().setInfoWindowAdapter(mapWindows);
                        marker.showInfoWindow();
                    }else{
                        helper.setText(R.id.btn_record, "打卡");
                        helper.setTextColor(R.id.btn_record, Color.parseColor("#FFFFFF"));
                        helper.setBackgroundRes(R.id.btn_record, R.drawable.bg_record_click);
                        helper.getView(R.id.btn_record).setEnabled(true);
                        helper.setGone(R.id.tv_apply, false);
                    }

                }
            }else{
                //已经打过卡，不管是补卡还是什么
                helper.setGone(R.id.ll_can_apply,false);
                helper.setGone(R.id.ll_over,true);
                helper.setText(R.id.attention_addreess,item.getAttendanceLocation());
                helper.setText(R.id.attention_times,item.getAttendanceTime());
            }
        }
    }




    private long getRecodeTime(String time){
        try {
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(this.date+" "+time).getTime();
        } catch (ParseException e) {
            return 0;
        }
    }

    /**
     * 初始化道德地图
     *
     * @param mapView
     */
    public void initMap(MapView mapView) {
        mapView.getMap().getUiSettings().setZoomControlsEnabled(false);//缩放按钮
        mapView.getMap().getUiSettings().setCompassEnabled(false);//指南针
        mapView.getMap().getUiSettings().setMyLocationButtonEnabled(false); //显示默认的定位按钮
        mapView.getMap().getUiSettings().setScaleControlsEnabled(false); //控制比例尺控件是否显示
    }

    public String date = "";
    /**
     * 设置选中日期 以及当前班次是否跨天
     */
    public void setChooseDay(String time){
        this.date = time;
    }

    public String getTime() {
        return date;
    }


    private String getOverTimes(long times){
        int hour = (int) (times/60/60/1000);
        int minute = (int) (times%(60*60*1000))/60/1000;
        int second = (int) ((times%(60*60*1000))%(60*1000)/1000);
        return String.format("%d小时%d分%d秒",hour,minute,second);
    }
    public void toast(String msg) {
        ToastUtils.makeText(mContext, msg, ToastUtils.LENGTH_LONG).show();
    }


}
