package indi.aljet.myuserguideview_master;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.zhl.cbpullrefresh.CBPullRefreshListView;
//import com.zhl.userguideview.UserGuideView;


import java.util.LinkedHashMap;

import indi.aljet.myuserguideview_master.userguideview.UserGuideView;


public class MainActivity extends AppCompatActivity {

    private String[] datas = new String[] {"收藏","字体大小",
    "软件设置","换肤"};
    GridView mGridView;
    private UserGuideView guideView;
    private ImageView icon,back,top;
    private CBPullRefreshListView listview;
    View tipTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        guideView  = (UserGuideView)findViewById(R.id
        .guidView);
        guideView.setTouchOutsideDismiss(false);
        tipTextView = LayoutInflater.from(this)
                .inflate(R.layout.custom_tipview,null);
        mGridView = (GridView)findViewById(R.id.gridview);
        mGridView.setAdapter(new MyAdapter());
        icon = (ImageView) findViewById(R.id.icon);
        back = (ImageView) findViewById(R.id.back);
        top = (ImageView) findViewById(R.id.top);

        icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                guideView.setTipView(tipTextView,400,200);
                guideView.setHighLightView(icon);
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                guideView.setTipView(tipTextView,400,200);
                guideView.setHighLightView(back);
            }
        });
        top.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                guideView.setArrowUpCenter(R.mipmap.up_arrow);
                guideView.setTipView(R.mipmap.tip_view);
                guideView.setHighLightView(top);
            }
        });
        LinkedHashMap<View,Integer> targets = new
                LinkedHashMap<>();
        //添加View对应的图标 到自定义View 让他呈现出来
        targets.put(top,R.mipmap.panda);
        targets.put(icon,R.mipmap.tip2);
        targets.put(back,R.mipmap.tip3);
        guideView.setArrowUpLeftMoveX(-30);
        guideView.setHighLightView(targets);

    }



    private class MyAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return datas.length;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public Object getItem(int i) {
            return datas[i];
        }


        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            ViewHolder viewHolder = null ;
            if(view == null){
                viewHolder = new ViewHolder();
                view = LayoutInflater.from(MainActivity.this)
                        .inflate(R.layout.grid_item,viewGroup,false);
                viewHolder.textView = (TextView)view.findViewById(R.id.tx);
                viewHolder.textView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        guideView.setTipView(tipTextView,400,200);
                        guideView.setHighLightView(view.findViewById(R.id.tx));
                    }
                });
                view.setTag(viewHolder);
            }else{
                viewHolder = (ViewHolder) view.getTag();
            }
            viewHolder.textView.setText(datas[i]);
            return view;
        }

        private class ViewHolder{
            public TextView textView;
        }
    }
}
