package com.example.lubanyasuodemoonly;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.io.File;
import java.util.List;
import java.util.function.Function;

import top.zibin.luban.Luban;
import top.zibin.luban.OnCompressListener;
/*
*
* Luban 压缩 高仿微信
* 配合Glide进行图片展示
*
* */
public class MainActivity extends AppCompatActivity {
    ImageView ivBackGround;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ivBackGround = findViewById(R.id.iv_background);

        LubanYaSuo();

    }
    /*
* 异步调用*/
    private void LubanYaSuo() {
        String path = Environment.getExternalStorageDirectory() + "/" + "ll.png";
        File file = new File(path);
        Luban.with(this)
                .load(file)                     //传人要压缩的图片
                .ignoreBy(100)          // 忽略不压缩图片的大小 100k 以下
                .setTargetDir(Environment.getExternalStorageDirectory() + "/" )                        // 设置压缩后文件存储位置
                .setCompressListener(new OnCompressListener() { //设置回调
                    @Override
                    public void onStart() {
                        // TODO 压缩开始前调用，可以在方法内启动 loading UI
                    }

                    @Override
                    public void onSuccess(File file) {
                        //TODO 压缩成功后调用，返回压缩后的图片文件
                        Glide.with(MainActivity.this).load(file).into(ivBackGround);

                    }

                    @Override
                    public void onError(Throwable e) {
                        // TODO 当压缩过程出现问题时调用
                    }

                }).launch();    //启动压缩
    }


}
