package sz.tianhe.baselib.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.Toast;

import sz.tianhe.baselib.R;
import sz.tianhe.baselib.navagation.IBaseNavagation;
import sz.tianhe.baselib.presenter.IBasePresenter;
import sz.tianhe.baselib.view.IBaseView;

/**
 * 项目名称:etc_wallet
 * 类描述 基础的Activity
 *
 * @author ch
 * @email 869360026@qq.com
 * 创建时间:2018/6/22 14:30
 */
public abstract class BaseActivity extends AppCompatActivity implements IBaseView {
    /**
     * 缺省的代理
     */
    protected IBasePresenter presenter;

    /**
     * baseActivity 根目录
     */
    private LinearLayout root;

    /**
     * 获得布局文件的layoutId
     *
     * @return
     */
    public abstract int layoutId();

    /**
     * 获取导航栏
     *
     * @return
     */
    public abstract IBaseNavagation navagation();


    public abstract void initView();

    public abstract  void findViews();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.base_activity);

        this.root = (LinearLayout) findViewById(R.id.root);
        if (null != navagation()) {
            this.root.addView(navagation().getNavagation());
        }
        View rootView = LayoutInflater.from(this).inflate(layoutId(), null);
        rootView.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        this.root.addView(rootView);


        presenter = createPrensenter();
        if (null != presenter) {
            this.presenter.attachView(this);
        }
        findViews();
        initView();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (null != presenter)
            this.presenter.dettacheView();
    }

    @Override
    public void showLoadingView() {

    }

    @Override
    public void hideLoadingView() {

    }

    @Override
    public void toast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
    }

    public  IBasePresenter createPrensenter(){
        return null;
    }

    public static void openActivity(Context context,Class cls){
        context.startActivity(new Intent(context,cls));
    }
}
