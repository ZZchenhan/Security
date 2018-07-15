package xxk.wuhai.seacurity.work.adapter;

import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.location.Location;
import android.view.View;

import com.amap.api.maps2d.AMap;
import com.amap.api.maps2d.CameraUpdateFactory;
import com.amap.api.maps2d.MapView;
import com.amap.api.maps2d.model.BitmapDescriptorFactory;
import com.amap.api.maps2d.model.LatLng;
import com.amap.api.maps2d.model.MarkerOptions;
import com.amap.api.maps2d.model.MyLocationStyle;
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import xxk.wuhai.seacurity.R;
import xxk.wuhai.seacurity.bean.RecoderBean;
import xxk.wuhai.seacurity.work.bean.DayDutyBean;

/**
 * Created by 86936 on 2018/6/29.
 *
 * @QQ 869360026
 */

public class RecordAdapter extends BaseMultiItemQuickAdapter<DayDutyBean.SchedulingInfoListBean,BaseViewHolder> {
    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param data A new list is created out of this one to avoid mutable list
     */
    public RecordAdapter(List<DayDutyBean.SchedulingInfoListBean> data) {
        super(data);
        addItemType(0, R.layout.item_record_no_locion_issu);
        addItemType(1, R.layout.item_record_locatopm);
    }

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

    private  String status(int status ){
        switch (status){
            case 0:
                return "未打卡";
            case 1:
                return "已打卡";
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
    protected void convert(BaseViewHolder helper, DayDutyBean.SchedulingInfoListBean item) {
        helper.addOnClickListener(R.id.btn_record);
        helper.addOnClickListener(R.id.tv_apply);
        try{
            helper.setText(R.id.record_type,revodeType(item.getAttendanceInfoVoList().get(0).getAttendanceType()));
        }catch (Exception e){
            helper.setText(R.id.record_type,"无类型");
        }
        helper.setText(R.id.cls_name,"班次："+item.getScheduleShortName());
        helper.setText(R.id.status,status(item.getStatus()));
        helper.setTextColor(R.id.status,item.getStatus() == 0?Color.RED:mContext.getResources().getColor(R.color.colorPrimary));
        helper.setText(R.id.time,"要求时间："+item.getBeginTime());
        helper.setText(R.id.btn_record,getTime(item.getBeginTime()));
        if(getTime(item.getBeginTime()).equals("未打卡")){
            helper.setGone(R.id.tv_apply, true);
        }else{
            helper.setGone(R.id.tv_apply, false);
        }
        if(item.getItemType() == 0){
            //无打卡位置
            helper.setText(R.id.adress,"要求时间："+item.getBeginTime());
        }else{
            //有打卡位置
            helper.setText(R.id.adress,"位置要求："+item.getBeginTime());
            MapView mapView = helper.getView(R.id.map_view);
            if(mapView.getTag() ==null) {
                initMap(mapView);
                mapView.setTag(item);
                mapView.onCreate(null);
            }
            if(RecoderBean.currentLatLng != null)
                mapView.getMap().clear();
                mapView.getMap().addMarker(new MarkerOptions().position(RecoderBean.currentLatLng).icon(BitmapDescriptorFactory.fromBitmap(BitmapFactory
                    .decodeResource(mContext.getResources(), R.mipmap.icon_poi_select))));
                mapView.getMap().moveCamera(CameraUpdateFactory.newLatLngZoom(RecoderBean.currentLatLng ,18));
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
}
