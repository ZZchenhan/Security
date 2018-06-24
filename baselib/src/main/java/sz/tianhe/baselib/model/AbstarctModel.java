package sz.tianhe.baselib.model;

import android.content.Context;

import sz.tianhe.baselib.api.Api;
import sz.tianhe.baselib.http.RetrofitClient;

/**
 * 项目名称:etc_wallet
 * 类描述
 *
 * @author ch
 * @email 869360026@qq.com
 * 创建时间:2018/6/22 14:25
 */
public class AbstarctModel<A extends Api> implements IBaseModel {
    /**
     * 缺省的服务器地址
     */
    public String serverUrl = "http://192.168.10.108:8080/ectWallet";

    public String  getServerUrl(){
        return serverUrl;
    }

    private Context mContext;

    private RetrofitClient retrofitClient;

    private A api;

    public AbstarctModel(Context context, A api) {
        this.mContext = context;
        this.api = api;
        retrofitClient = new RetrofitClient(context,serverUrl);

        retrofitClient.getRetrofit().create(api.getClass());
    }


}
