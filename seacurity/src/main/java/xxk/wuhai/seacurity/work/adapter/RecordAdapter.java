package xxk.wuhai.seacurity.work.adapter;

import android.graphics.BitmapFactory;
import android.graphics.Color;

import com.amap.api.maps2d.AMapUtils;
import com.amap.api.maps2d.CameraUpdateFactory;
import com.amap.api.maps2d.MapView;
import com.amap.api.maps2d.model.BitmapDescriptorFactory;
import com.amap.api.maps2d.model.CircleOptions;
import com.amap.api.maps2d.model.LatLng;
import com.amap.api.maps2d.model.Marker;
import com.amap.api.maps2d.model.MarkerOptions;
import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import xxk.wuhai.seacurity.R;
import xxk.wuhai.seacurity.bean.RecoderBean;
import xxk.wuhai.seacurity.weight.mapwindows.MapWindows;
import xxk.wuhai.seacurity.work.bean.scheduling.AttendanceInfoVoListBean;
import xxk.wuhai.seacurity.work.bean.scheduling.GetPersonSchedulingByDateResponse;

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
                return "下班";
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
        helper.setText(R.id.cls_name,"班次："+item.getScheduleName());
        helper.setText(R.id.status,status(item.getStatus()));
        helper.setTextColor(R.id.status,item.getStatus()!=null && item.getStatus().equals("0")
                || item.getStatus().equals("5")
                ?Color.parseColor("#F43530"):mContext.getResources().getColor(R.color.colorPrimary));
        helper.setText(R.id.time,"要求时间："+item.getAttendanceTimeExpect()==null?"":item.getAttendanceTimeExpect());
        if(item.getAttendanceLocationExpect() == null
                ||
                item.getAttendanceLocationExpect().equals("")){
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
                    helper.setGone(R.id.tv_apply,true);
                } else if(item.getStatus().equals("5")){
                    //前去补卡
                    helper.setText(R.id.btn_record,"未打卡");
                    helper.setTextColor(R.id.btn_record,Color.parseColor("#F4F4F4"));
                    helper.setBackgroundRes(R.id.btn_record,R.drawable.bg_record_no_click);
                    helper.getView(R.id.btn_record).setEnabled(true);
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
                    if(currentTime<realTime-item.getPreLimit() *30*1000){
                        //太早
                        helper.setText(R.id.btn_record,"未开始");
                        helper.setTextColor(R.id.btn_record,Color.parseColor("#F4F4F4"));
                        helper.setBackgroundRes(R.id.btn_record,R.drawable.bg_record_no_click);
                        helper.getView(R.id.btn_record).setEnabled(false);
                        helper.setGone(R.id.tv_apply,false);
                    }else if(currentTime<realTime-item.getPreLimit()){
                        //显示倒计时
                        helper.setText(R.id.btn_record,new SimpleDateFormat("HH小时mm分ss秒").format(new Date(realTime-item.getPreLimit()-currentTime)));
                        helper.setTextColor(R.id.btn_record,Color.parseColor("#F4F4F4"));
                        helper.setBackgroundRes(R.id.btn_record,R.drawable.bg_record_no_click);
                        helper.getView(R.id.btn_record).setEnabled(false);
                        helper.setGone(R.id.tv_apply,false);
                    } else if(currentTime>realTime+item.getPostLimit()){
                        //太晚，让他补卡
                        helper.setText(R.id.btn_record,"未打卡");
                        helper.setTextColor(R.id.btn_record,Color.parseColor("#F4F4F4"));
                        helper.setBackgroundRes(R.id.btn_record,R.drawable.bg_record_no_click);
                        helper.getView(R.id.btn_record).setEnabled(false);
                        helper.setGone(R.id.tv_apply,false);
                    } else{
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
            //有打卡位置
            helper.setText(R.id.adress,item.getAttendanceLocationExpect());
            MapView mapView = helper.getView(R.id.map_view);
            if(mapView.getTag() ==null) {
                initMap(mapView);
                mapView.setTag(item);
                mapView.onCreate(null);
            }
            if(item.getRange() == null || item.getRange().equals("")){
                ToastUtils.showShort("后台传递范围为："+item.getRange());
                return;
            }
            if(item.getAttendanceLatExpect() == null || item.getAttendanceLatExpect().equals("")){
                ToastUtils.showShort("后台传递维度为："+item.getAttendanceLatExpect());
                return;
            }
            if(item.getAttendanceLonExpect() == null || item.getAttendanceLonExpect().equals("")){
                ToastUtils.showShort("后台传递精度为："+item.getAttendanceLatExpect());
                return;
            }
            helper.setText(R.id.range,item.getRange()==null?"0米":item.getRange()+"米范围");
            mapView.getMap().clear();
            Marker marker = null;
            if(RecoderBean.currentLatLng != null) {
                marker = mapView.getMap().addMarker(new MarkerOptions().position(RecoderBean.currentLatLng).icon(BitmapDescriptorFactory.fromBitmap(BitmapFactory
                        .decodeResource(mContext.getResources(), R.mipmap.icon_poi_select))));
                mapView.getMap().moveCamera(CameraUpdateFactory.newLatLngZoom(RecoderBean.currentLatLng, 14));
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
                    helper.setGone(R.id.tv_apply,true);
                } else if(item.getStatus().equals("5")){
                    //前去补卡
                    helper.setText(R.id.btn_record,"未打卡");
                    helper.setTextColor(R.id.btn_record,Color.parseColor("#F4F4F4"));
                    helper.setBackgroundRes(R.id.btn_record,R.drawable.bg_record_no_click);
                    helper.getView(R.id.btn_record).setEnabled(true);
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
                    if(currentTime<realTime-item.getPreLimit() *30*1000){
                        //太早
                        helper.setText(R.id.btn_record,"未开始");
                        helper.setTextColor(R.id.btn_record,Color.parseColor("#F4F4F4"));
                        helper.setBackgroundRes(R.id.btn_record,R.drawable.bg_record_no_click);
                        helper.getView(R.id.btn_record).setEnabled(false);
                        helper.setGone(R.id.tv_apply,false);
                    }else if(currentTime<realTime-item.getPreLimit()){
                        //显示倒计时
                        helper.setText(R.id.btn_record,new SimpleDateFormat("HH小时mm分ss秒").format(new Date(realTime-item.getPreLimit()-currentTime)));
                        helper.setTextColor(R.id.btn_record,Color.parseColor("#F4F4F4"));
                        helper.setBackgroundRes(R.id.btn_record,R.drawable.bg_record_no_click);
                        helper.getView(R.id.btn_record).setEnabled(false);
                        helper.setGone(R.id.tv_apply,false);
                    } else if(currentTime>realTime+item.getPostLimit()){
                        //太晚，让他补卡
                        helper.setText(R.id.btn_record,"未打卡");
                        helper.setTextColor(R.id.btn_record,Color.parseColor("#F4F4F4"));
                        helper.setBackgroundRes(R.id.btn_record,R.drawable.bg_record_no_click);
                        helper.getView(R.id.btn_record).setEnabled(false);
                        helper.setGone(R.id.tv_apply,false);
                    }else if(marker!=null && AMapUtils.calculateLineDistance(RecoderBean.currentLatLng,latLng)>Double.parseDouble(item.getRange())) {
                        //不在距离
                        helper.setText(R.id.btn_record, "不在打卡区域");
                        helper.setTextColor(R.id.btn_record, Color.parseColor("#F4F4F4"));
                        helper.setBackgroundRes(R.id.btn_record, R.drawable.bg_record_no_click);
                        helper.getView(R.id.btn_record).setEnabled(false);
                        helper.setGone(R.id.tv_apply, false);
                        mapView.getMap().setInfoWindowAdapter(new MapWindows(mContext));
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
            return new SimpleDateFormat("HH:mm:ss").parse(time).getTime() +this.time;
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

    public long time = 0;
    /**
     * 设置选中日期 以及当前班次是否跨天
     */
    public void setChooseDay(long time){
        this.time = time;
    }

    public long getTime() {
        return time;
    }
}
