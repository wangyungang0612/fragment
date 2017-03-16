package com.example.administrator.myactivity;

import android.app.Activity;
import android.app.Fragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/12/14 0014.
 */
public class NewsTitleFragement extends Fragment{
    private ListView newsTitleListView ;
    private List<News> newsList;
    private NewsAdapter adapter;
    private boolean isTwoPane;

    public void onAttach(Activity activity){
        super.onAttach(activity);
        newsList = getNews();
        adapter = new NewsAdapter(activity,R.layout.news_item,newsList);
    }
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.news_title_frag,container,false);
        newsTitleListView = (ListView) view.findViewById(R.id.news_title_list_view);
        newsTitleListView.setAdapter(adapter);
       // newsTitleListView.setOnItemClickListener(this);

        newsTitleListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                News news = newsList.get(i);
                if(isTwoPane){
                    //如果是双页模式，则刷新NewsContentFragment中的内容
                    NewsContentFragement newsContentFragement = (NewsContentFragement) getFragmentManager().findFragmentById(R.id.news_content_fragment);
                    newsContentFragement.refresh(news.getTitle(),news.getContent());
                }else {
                    //如果是单页模式，则直接启动NewsContentActivity
                    NewsContentActivity.actionStart(getActivity(),news.getTitle(),news.getContent());
                }
            }
        });

        return view;
    }

    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);

        if(getActivity().findViewById(R.id.news_content_layout)!=null){
            isTwoPane = true;//可以找到news_content_layout布局时为双页模式
        }else {
            isTwoPane = false;//找不到news_content_layout布局时为单页模式
        }
    }

//    public void onItemClick(AdapterView<?> parent,View view,int position,long id){
//        News news = newsList.get(position);
//        if(isTwoPane){
//            //如果是双页模式，则刷新NewsContentFragment中的内容
//            NewsContentFragement newsContentFragement = (NewsContentFragement) getFragmentManager().findFragmentById(R.id.news_content_fragment);
//            newsContentFragement.refresh(news.getTitle(),news.getContent());
//        }else {
//            //如果是单页模式，则直接启动NewsContentActivity
//            NewsContentActivity.actionStart(getActivity(),news.getTitle(),news.getContent());
//        }
//    }

    private List<News> getNews(){
        List<News> newsList = new ArrayList<News>();
        News news1 = new News();
        news1.setTitle("春运抢票难");
        news1.setContent("        2017年春运今天开始抢票啦，由于明年春节提早，加上车票预售期缩短以及天气原因等，此次春运极有可能出现短时间内出行人流叠加的情况，买火车票的难度陡然增加。面对不容乐观的抢票形势，360手机浏览器特推出抢票提醒日历，方便大家查看提前购票日期， 避免错过抢票的黄金时段。");
        newsList.add(news1);
        News news2 = new News();
        news2.setTitle("吴敏霞确认将退役");
        news2.setContent("        昨日奥运冠军吴敏霞在微博中写下一段意味深长的话：“6岁学习跳水， 13岁进入国家队，25年弹指一挥间啊，谢谢大家一直以来的支持。”暗示自己将要退役， 而据《北京青年报》向中国跳水队领队周继红确认，吴敏霞将在今日CCTV体坛风云人物初评揭晓的 发布会上正式宣布退役的决定。");
        newsList.add(news2);
        return newsList;
    }



}
