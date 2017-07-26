package com.example.admin.tl;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import org.json.JSONArray;

import java.util.List;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.QueryListener;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UpdateListener;

public class MainActivity extends AppCompatActivity {

    public TextView text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        text = (TextView) findViewById(R.id.text);

        //第一：默认初始化
        Bmob.initialize(this, "b97d7b71082f4c983bc520bd4e0fe66d");
        // 注:自v3.5.2开始，数据sdk内部缝合了统计sdk，开发者无需额外集成，传渠道参数即可，不传默认没开启数据统计功能
        //Bmob.initialize(this, "Your Application ID","bmob");

        //第二：自v3.4.7版本开始,设置BmobConfig,允许设置请求超时时间、文件分片上传时每片的大小、文件的过期时间(单位为秒)，
        //BmobConfig config =new BmobConfig.Builder(this)
        ////设置appkey
        //.setApplicationId("Your Application ID")
        ////请求超时时间（单位为秒）：默认15s
        //.setConnectTimeout(30)
        ////文件分片上传时每片的大小（单位字节），默认512*1024
        //.setUploadBlockSize(1024*1024)
        ////文件的过期时间(单位为秒)：默认1800s
        //.setFileExpiration(2500)
        //.build();
        //Bmob.initialize(config);

        BmobQuery query = new BmobQuery("_User");
        query.addWhereEqualTo("username", "bb");
        query.findObjectsByTable(new QueryListener<JSONArray>() {
            @Override
            public void done(JSONArray jsonArray, BmobException e) {
                if (e == null) {
                    text.setText(jsonArray.toString());
                } else {
                    text.setText(e.getMessage() + "," + e.getErrorCode());
                }
            }
        });
        Gson gson = new Gson();
//        gson.fromJson()

        final Button add = (Button) findViewById(R.id.add);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                add();
            }
        });

        final Button update = (Button) findViewById(R.id.update);
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final _User user = new _User();
                user.setUsername("bb");
                user.update("KNWw444D", new UpdateListener() {
                    @Override
                    public void done(BmobException e) {
                        if (e == null) {
                            text.setText("更新成功");
                        } else {
                            text.setText(e.getMessage() + "," + e.getErrorCode());
                        }
                    }
                });
            }
        });

        final Button delete = (Button) findViewById(R.id.delete);
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final _User user = new _User();
                user.setObjectId("UI5r777H");
                user.delete(new UpdateListener() {
                    @Override
                    public void done(BmobException e) {
                        if (e == null) {
                            text.setText("删除成功");
                        } else {
                            text.setText(e.getMessage() + "," + e.getErrorCode());
                        }
                    }
                });
            }
        });
    }

    public void add() {
        Person user = new Person();
        user.setName("hfusianewfjw");
        user.save(new SaveListener<String>() {
            @Override
            public void done(String s, BmobException e) {
                if (e == null) {
                    text.setText("创建成功" + s);
                } else {
                    text.setText(e.getMessage() + "," + e.getErrorCode());
                }
            }
        });
    }
}
