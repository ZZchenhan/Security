package com.hz.junxinbaoan.guide.model;

import io.reactivex.Observable;
import sz.tianhe.baselib.model.bean.Result;
import com.hz.junxinbaoan.bean.CompanyBean;

/**
 * 描述
 *
 * @author ch
 * @微信 xrbswo
 * @qq 869360026
 * @email 869360026@qq.com
 * @创建时间 2018/6/24 19:34
 */
public interface GuideApi {

    Observable<Result<CompanyBean>> getCompnayInfo();
}
