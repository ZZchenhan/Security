package com.hz.junxinbaoan.work.view.itf;

import com.amap.api.services.core.PoiItem;

import java.util.List;

import sz.tianhe.baselib.view.IBaseView;

/**
 * Created by 86936 on 2018/6/28.
 *
 * @QQ 869360026
 */

public interface IUpdateLocationView extends IBaseView {

    void poiResult(List<PoiItem> poiItems);
}
