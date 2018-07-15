package xxk.wuhai.seacurity.guide.view;

import android.support.annotation.NonNull;
import android.widget.ImageView;
import android.widget.TextView;


import sz.tianhe.baselib.http.interceptor.BaseInterceptor;
import sz.tianhe.baselib.navagation.IBaseNavagation;
import sz.tianhe.baselib.presenter.IBasePresenter;
import sz.tianhe.baselib.view.activity.BaseActivity;
import xxk.wuhai.seacurity.MyApplication;
import xxk.wuhai.seacurity.login.view.LoginActivity;
import xxk.wuhai.seacurity.main.view.MainActivity;
import xxk.wuhai.seacurity.R;
import xxk.wuhai.seacurity.bean.CompanyBean;
import xxk.wuhai.seacurity.guide.presenter.GuidPrensenter;
import xxk.wuhai.seacurity.guide.view.itf.IGuideView;

/**
 * 引导页面
 *
 * @author ch
 * @微信 xrbswo
 * @qq 869360026
 * @email 869360026@qq.com
 * @创建时间 2018/6/24 17:27
 */
public class GuideActivity extends BaseActivity implements IGuideView {


    ImageView companyIcon;
    TextView companyTitle;
    TextView tvVersion;

    @Override
    public IBasePresenter createPrensenter() {
        return new GuidPrensenter(this, this);
    }

    @Override
    public int layoutId() {
        return R.layout.activity_guide;
    }

    @Override
    public IBaseNavagation navagation() {
        return null;
    }

    @Override
    public void initView() {
        presenter.init();
    }

    @Override
    public void findViews() {
        companyIcon = findViewById(R.id.company_icon);
        companyTitle = findViewById(R.id.company_title);
        tvVersion = findViewById(R.id.tv_version);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void loadCompanyView(CompanyBean companyBean) {

    }

    @Override
    public void versionName(String apkName, String versionName) {
        tvVersion.setText(apkName + versionName);
    }

    @Override
    public void handover() {
        if(MyApplication.userDetailInfo == null || BaseInterceptor.token == null || BaseInterceptor.token.equals("")) {
            LoginActivity.openActivity(this, LoginActivity.class);
            finish();
        }else{
            MainActivity.openActivity(this,MainActivity.class);
            finish();
        }
    }

    @Override
    public void showPermisionUnAccept() {
        toast("请前往设置页面打开当前应用权限");
        finish();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        ((GuidPrensenter) presenter).onRequestPermissionsResult(requestCode, permissions, grantResults);
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
}
