package xxk.wuhai.seacurity.main.view;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.view.View;
import android.widget.FrameLayout;

import me.majiajie.pagerbottomtabstrip.NavigationController;
import me.majiajie.pagerbottomtabstrip.PageNavigationView;
import me.majiajie.pagerbottomtabstrip.item.BaseTabItem;
import me.majiajie.pagerbottomtabstrip.item.NormalItemView;
import me.majiajie.pagerbottomtabstrip.listener.OnTabItemSelectedListener;
import sz.tianhe.baselib.navagation.IBaseNavagation;
import sz.tianhe.baselib.view.activity.BaseActivity;
import xxk.wuhai.seacurity.R;
import xxk.wuhai.seacurity.common.navagation.CommonNavagation;
import xxk.wuhai.seacurity.common.navagation.LeftIconNavagation;
import xxk.wuhai.seacurity.common.navagation.LeftTitleNavagation;
import xxk.wuhai.seacurity.main.view.custorm.MyNormalItem;
import xxk.wuhai.seacurity.work.view.RecordActivity;
import xxk.wuhai.seacurity.work.view.SignActivity;

public class MainActivity extends BaseActivity implements OnTabItemSelectedListener {

    private String[] tabTitle = new String[]{"工作台","消息","通讯录","我的"};


    FrameLayout fragmentContainer;
    PageNavigationView tab;
    private NavigationController controller;


    private void initFragment() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        Fragment fragment1 = new WorkFragment();
        transaction.add(R.id.fragment_container, fragment1, tabTitle[0]);
        transaction.commit();
    }


    private void initBottom() {
        controller = tab.custom()
                .addItem(newItem(R.mipmap.icon_work_nomarl, R.mipmap.icon_nomal_select, tabTitle[0]))
                .addItem(newItem(R.mipmap.icon_msg_normal, R.mipmap.icon_msg_select, tabTitle[1]))
                .addItem(newItem(R.mipmap.icon_contact_normal, R.mipmap.icon_contac_select, tabTitle[2]))
                .addItem(newItem(R.mipmap.icon_me_normal, R.mipmap.icon_me_select, tabTitle[3]))
                .build();
        controller.addTabItemSelectedListener(this);
    }

    //创建一个Item
    private BaseTabItem newItem(int drawable, int checkedDrawable, String text){
        MyNormalItem normalItemView = new MyNormalItem(this);
        normalItemView.initialize(drawable,checkedDrawable,text);
        normalItemView.setTextDefaultColor(0xff7F8389);
        normalItemView.setTextCheckedColor(0xff49B1FA);
        return normalItemView;
    }

    @Override
    public int layoutId() {
        return R.layout.activity_main;
    }
    LeftTitleNavagation leftIconNavagation;
    @Override
    public IBaseNavagation navagation() {
        return leftIconNavagation = new LeftTitleNavagation(this) {
            @Override
            public String title() {
                return "工作台";
            }
        };
    }

    @Override
    public void initView() {
        initBottom();
        initFragment();
    }

    @Override
    public void findViews() {
        fragmentContainer = findViewById(R.id.fragment_container);
        tab = findViewById(R.id.tab);
    }

    public void onClick(View view) {
        switch (view.getId()){
            case R.id.tv_sign:
                SignActivity.openActivity(this,SignActivity.class);
                break;
            case R.id.tv_work:
                RecordActivity.openActivity(this,RecordActivity.class);
                break;
        }
    }

    @Override
    public void onSelected(int index, int old) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();

        Fragment oldFragment= fragmentManager.findFragmentByTag(tabTitle[old]);
        Fragment newFragment = fragmentManager.findFragmentByTag(tabTitle[index]);

        if(newFragment == null){
            if(index==0){
                newFragment = new WorkFragment();
            }
            if(index==1){
                newFragment = new MsgFragment();
            }
            if(index==2){
                newFragment = new ContactFragment();
            }
            if(index==3){
                newFragment = new MeFragment();
            }

            transaction.add(R.id.fragment_container, newFragment, tabTitle[index]);
        }
        leftIconNavagation.setTvTitle(tabTitle[index]);
        transaction.hide(oldFragment).show(newFragment);
        transaction.commit();
    }

    @Override
    public void onRepeat(int index) {

    }
}
