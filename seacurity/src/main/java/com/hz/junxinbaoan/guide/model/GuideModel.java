package com.hz.junxinbaoan.guide.model;

import android.content.Context;

import io.reactivex.Observable;
import sz.tianhe.baselib.http.RetrofitClient;
import sz.tianhe.baselib.model.IBaseModel;
import sz.tianhe.baselib.model.bean.Result;
import com.hz.junxinbaoan.bean.CompanyBean;

/**
 * 描述
 *
 * @author ch
 * @微信 xrbswo
 * @qq 869360026
 * @email 869360026@qq.com
 * @创建时间 2018/6/24 19:33
 */
public class GuideModel implements IBaseModel {
    RetrofitClient retrofitClient;
    GuideApi guideApi;

    public GuideModel(Context context) {
        guideApi = retrofitClient.getRetrofit().create(GuideApi.class);
    }

    public Observable<Result<CompanyBean>> getCompnay(){
        return guideApi.getCompnayInfo();
    }


}
