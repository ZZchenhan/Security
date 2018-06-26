package xxk.wuhai.seacurity.main.view;

import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import sz.tianhe.baselib.navagation.IBaseNavagation;
import sz.tianhe.baselib.view.activity.BaseActivity;
import sz.tianhe.baselib.weight.CircleImageView;
import xxk.wuhai.seacurity.R;
import xxk.wuhai.seacurity.bean.CompanyBean;
import xxk.wuhai.seacurity.bean.DutyInfoBean;
import xxk.wuhai.seacurity.bean.UserInfoBean;
import xxk.wuhai.seacurity.main.view.itf.IMainView;
import xxk.wuhai.seacurity.work.view.SignActivity;

public class MainActivity extends BaseActivity
        implements NavigationView.OnNavigationItemSelectedListener,IMainView{

    private String[] tabTitle = new String[]{"工作台","消息","联系人"};

    /**
     * 导航栏
     */
    Toolbar toolbar;

    DrawerLayout drawer;

    NavigationView navigationView;

    CircleImageView personHeadPic;

    ImageView personBg;

    TextView tvName;

    TabLayout tabLayout;

    ViewPager viewPager;

    List<Fragment> fragmentList = new ArrayList<>();

    @Override
    public int layoutId() {
        return R.layout.activity_main;
    }

    @Override
    public IBaseNavagation navagation() {
        return null;
    }

    @Override
    public void initView() {
        setSupportActionBar(toolbar);
        toolbar.setTitle("嘉兴南湖保安服务有限公司");
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);


        fragmentList.add(new WorkFragment());
        fragmentList.add(new MsgFragment());
        fragmentList.add(new ContactFragment());

        tabLayout.setupWithViewPager(viewPager);
        viewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int i) {
                return fragmentList.get(i);
            }

            @Override
            public int getCount() {
                return fragmentList.size();
            }

            @Nullable
            @Override
            public CharSequence getPageTitle(int position) {
                return tabTitle[position];
            }
        });

    }

    @Override
    public void findViews() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        navigationView = (NavigationView) findViewById(R.id.nav_view);
        View headView = navigationView.getHeaderView(0);

        personBg = headView.findViewById(R.id.head_bg);
        personHeadPic = headView.findViewById(R.id.person_icon);
        tvName = headView.findViewById(R.id.person_name);
        tabLayout = findViewById(R.id.tablayout);
        viewPager = findViewById(R.id.viewpage);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    //
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        switch (id){
            case R.id.nav_settin:
                SettingActivity.openActivity(this,SettingActivity.class);
                break;
            case R.id.nav_update_user_info:
                UpdateUserInfoActivity.openActivity(this,UpdateUserInfoActivity.class);
                break;
        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void setUserInfo(UserInfoBean userInfo) {
        tvName.setText(userInfo.getName()+"    "+userInfo.getMobile());
        Glide.with(this).load(userInfo.getHeadIcon()).into(personHeadPic);
        Glide.with(this).load(userInfo.getHeadIcon()).into(personBg);
    }

    @Override
    public void setCompanyInfo(CompanyBean companyInfo) {
        toolbar.setTitle(companyInfo.getCompanyName()+"");
    }




    public void onClick(View view) {
        switch (view.getId()){
            case R.id.tv_sign:
                SignActivity.openActivity(this,SignActivity.class);
                break;
        }
    }
}
