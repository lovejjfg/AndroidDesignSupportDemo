package com.example.zhangjun166.demo;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Toast;

import de.greenrobot.event.EventBus;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private String msg;
    private int arg1;
    private MyAdapter myAdapter;
    private WindowManager mWM;
    private WindowManager.LayoutParams mParams;
    private ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
        setContentView(R.layout.activity_second1);
       final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowTitleEnabled(false);
        ImageView imageView = new ImageView(this);
        imageView.setBackgroundResource(R.mipmap.ic_favorite_border_white_48dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("xxxxxxx", v.getId() + "");
            }
        });
//        getSupportActionBar().setCustomView(imageView);



//        CollapsingToolbarLayout collapsingToolbar =
//                (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
//        collapsingToolbar.setTitle("RecyclerViewActivity");
//        toolbar.setTitle("RecyclerViewActivity");

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        FloatingActionButton showToast = (FloatingActionButton) findViewById(R.id.show_toast);

        RecyclerView mRv = (RecyclerView) findViewById(R.id.rv);
        fab.setOnClickListener(this);
        showToast.setOnClickListener(this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRv.setLayoutManager(linearLayoutManager);
        myAdapter = new MyAdapter();
        mRv.setAdapter(myAdapter);
//        Toast.makeText().show();
        //EventBus.getDefault().post();


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

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

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fab:
//                Snackbar.make(v, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
                EventBus.getDefault().post(EventBusBean.getInstance(EventAction.SECOND_ACTIVITY));
                startActivity(new Intent(this, SecondActivity.class));
                break;
            case R.id.show_toast:
                showToast();
        }
    }

    private void showToast() {
//        TextView tv = new TextView(this);
//        tv.setText("ahahahahah");
//        tv.setTextColor(Color.RED);
        final View view = View.inflate(this, R.layout.item_test, null);
        mWM = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
        mParams = new WindowManager.LayoutParams();
        mParams.type = WindowManager.LayoutParams.TYPE_PHONE;  //type是关键，这里的2002表示系统级窗口，你也可以试试2003。
        mParams.format = 1;
        /**
         *这里的flags也很关键
         *代码实际是wmParams.flags |= FLAG_NOT_FOCUSABLE;
         *40的由来是wmParams的默认属性（32）+ FLAG_NOT_FOCUSABLE（8）
         */
        mParams.flags  = WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON ;
        mParams.width = WindowManager.LayoutParams.MATCH_PARENT;
        mParams.height = WindowManager.LayoutParams.WRAP_CONTENT;
        mParams.gravity = Gravity.TOP | Gravity.LEFT;
        mWM.addView(view, mParams);
        Handler mHandler = new Handler();
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                mWM.removeView(view);
            }
        }, 5000);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    public void onEventMainThread(EventBusBean event) {

        if (event.getAction().equals(EventAction.MAIN_ACTIVITY)) {

            msg = "onEventMainThread收到了消息：" + event.getAction();
            Log.d("harvic", "........");
             Toast.makeText(this, "hahahah", Toast.LENGTH_LONG).show();
            arg1 = event.arg1;
            myAdapter.setCount(arg1);
//            ScrollView;
//            TextView
//            Button
//            ViewGroup
//            WebView
        }
        // tv.setText(msg);
    }

}
