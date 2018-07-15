package xxk.wuhai.seacurity.msg.view;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.text.Html;
import android.view.View;
import android.widget.TextView;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import sz.tianhe.baselib.navagation.IBaseNavagation;
import sz.tianhe.baselib.view.activity.BaseActivity;
import xxk.wuhai.seacurity.MyApplication;
import xxk.wuhai.seacurity.R;
import xxk.wuhai.seacurity.bean.Result;
import xxk.wuhai.seacurity.common.navagation.LeftIconNavagation;
import xxk.wuhai.seacurity.msg.MsgApi;
import xxk.wuhai.seacurity.msg.bean.GetMessageDetailResult;
import xxk.wuhai.seacurity.msg.vo.GetMessageDetailVO;

public class CompanyMsgActivity extends BaseActivity {

    private TextView tvTile;

    private TextView tvTimes;

    private TextView tvContext;

    private TextView tvFrom;

    @Override
    public int layoutId() {
        return R.layout.activity_compnay_msg;
    }
    LeftIconNavagation leftIconNavagation;
    @Override
    public IBaseNavagation navagation() {
         leftIconNavagation = (LeftIconNavagation) new LeftIconNavagation(this) {
            @Override
            public String title() {
                return "公司信息";
            }
        }.setNavagationBackgroudColor(R.color.colorPrimary);
        leftIconNavagation.setIconClick(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        leftIconNavagation.setTitleColor(R.color.white);
        return leftIconNavagation;
    }


    @Override
    public void initView() {
        MyApplication.retrofitClient.getRetrofit().create(MsgApi.class)
                .getMsgDetails(
                        new GetMessageDetailVO(getIntent().
                                getIntExtra("msgId",0),
                                getIntent().getStringExtra("msgType")))

        .subscribeOn(Schedulers.newThread())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(new Observer<Result<GetMessageDetailResult>>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Result<GetMessageDetailResult> getMessageDetailResultResult) {
                if(isFinishing()){
                    return;
                }
                if(!getMessageDetailResultResult.getCode().equals("200")){
                    toast(getMessageDetailResultResult.getMessage());
                    return;
                }
                setData(getMessageDetailResultResult.getResult());
            }

            @Override
            public void onError(Throwable e) {
                if(!isFinishing()){
                    toast(e.getMessage());
                }
            }

            @Override
            public void onComplete() {

            }
        });
    }

    private void setData(GetMessageDetailResult result){
        if(result.getMessageDetail() != null){
            tvTile.setText("标题:"+result.getMessageDetail().getMessageTitle());
            tvTimes.setText("发送时间:"+result.getMessageDetail().getMessageCreateTime());
            tvContext.setText(Html.fromHtml(result.getMessageDetail().getMessageContent()));
            tvFrom.setText("来源:"+result.getMessageDetail().getCompanyName());
            return;
        }
        if(result.getPersonnelDetails() != null){
            tvTile.setText("标题:人事变动提醒");
            tvTimes.setText("发送时间:"+result.getPersonnelDetails().getGmtCreate());

            tvContext.setText(Html.fromHtml(result.getPersonnelDetails().getPost())
            );
            tvFrom.setText("来源:人事部");
            return;
        }
    }

    @Override
    public void findViews() {
        tvTile = findViewById(R.id.title);
        tvTimes = findViewById(R.id.time);
        tvContext = findViewById(R.id.content);
        tvFrom = findViewById(R.id.from);
    }

    public static void openActivity(Context context,String msgType,int msgId){
        Intent intent = new Intent(context,CompanyMsgActivity.class);
        intent.putExtra("msgType",msgType);
        intent.putExtra("msgId",msgId);
        context.startActivity(intent);
    }

}
