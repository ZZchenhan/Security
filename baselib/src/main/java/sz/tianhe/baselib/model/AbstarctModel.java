package sz.tianhe.baselib.model;

import android.content.Context;

import com.google.gson.Gson;

import java.util.Map;

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
public class AbstarctModel implements IBaseModel {
    /**
     * 缺省的服务器地址
     */
    public String serverUrl = "http://192.168.10.108:8080/ectWallet";

    public String getServerUrl() {
        return serverUrl;
    }

    private Context mContext;

    protected RetrofitClient retrofitClient;

    private static AbstarctModel abstarctModel;

    private Api api;

    private AbstarctModel(Context context) {
        this.mContext = context;
        retrofitClient = new RetrofitClient(context, serverUrl);

        api = retrofitClient.getRetrofit().create(Api.class);
    }

    public static AbstarctModel getInstance(Context context) {
        if (abstarctModel == null) {
            abstarctModel = new AbstarctModel(context);
        }
        return abstarctModel;
    }

}
