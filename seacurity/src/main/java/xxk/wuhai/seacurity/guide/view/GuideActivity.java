package xxk.wuhai.seacurity.guide.view;

import android.widget.ImageView;
import android.widget.TextView;


import sz.tianhe.baselib.navagation.IBaseNavagation;
import sz.tianhe.baselib.presenter.IBasePresenter;
import sz.tianhe.baselib.view.activity.BaseActivity;
import xxk.wuhai.seacurity.login.view.LoginActivity;
import xxk.wuhai.seacurity.main.view.MainActivity;
import xxk.wuhai.seacurity.R;
import xxk.wuhai.seacurity.bean.CompanyBean;
import xxk.wuhai.seacurity.guide.presenter.GuidPrensenter;
import xxk.wuhai.seacurity.guide.view.itf.IGuideView;

/**
 * 描述
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
        presenter.init();
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
        MainActivity.openActivity(this, LoginActivity.class);
        finish();
    }

}
