package com.ybllcodes.studentapplication;

import static com.ybllcodes.studentapplication.utils.Constant.*;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.HraManager;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.ybllcodes.studentapplication.pojo.HraControl;
import com.ybllcodes.studentapplication.pojo.ResultBean;
import com.ybllcodes.studentapplication.utils.HraManagerUtils;
import com.ybllcodes.studentapplication.utils.HttpCallback;
import com.ybllcodes.studentapplication.utils.OkHttpUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.OkHttpClient;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, HttpCallback {
    private EditText et;
    private Button btnUpdate;
    private TextView tvControl;
    private HraManager hraManager;

    {
        OkHttpUtils.setHttpCallback(this);
    }
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        et = findViewById(R.id.et_mac);
        findViewById(R.id.btn_register).setOnClickListener(this);

        btnUpdate = findViewById(R.id.btn_update_control);
        btnUpdate.setOnClickListener(this);
        tvControl = findViewById(R.id.tv_control_stu);
        hraManager = (HraManager)getSystemService(HraManager.class);


    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id){
            case R.id.btn_register:
                String registerUrl = USER_URL + REGISTER_MAC;
                Editable text = et.getText();
                String s = text.toString();
                Map<String,String> map = new HashMap<>();
                map.put("mac",s);
                map.put("type","1");
                //注册SN号
                OkHttpUtils.sendMapHttp(registerUrl,map);
                break;
            case R.id.btn_update_control:
                String upCUrl = CONTROL_URL + GETMSG_FROM_S;
                String upText = et.getText().toString();
                Map<String,String> mapUp = new HashMap<>();
                mapUp.put("stuMac",upText);
                mapUp.put("parMac","parent01");
                //注册SN号
                OkHttpUtils.sendMapHttp(upCUrl,mapUp);
                break;
        }
    }

    @Override
    public void setMain(ResultBean resultBean) {
        boolean flag = resultBean.isFlag();
        String msg = resultBean.getMsg();
        if(flag){
            Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void setMainForPControl(ResultBean resultBean) {

    }

    @Override
    public void setMainForSControl(ResultBean resultBean) {
        boolean flag = resultBean.isFlag();
        String msg = resultBean.getMsg();
        HraControl hc = JSON.parseObject(resultBean.getResult(), HraControl.class);
        System.out.println("=====" + hc + "=====");
        tvControl.setText(hc.toString());
        HraManagerUtils.setControl(hraManager,hc);
    }
}