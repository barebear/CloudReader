package com.example.jingbin.cloudreader.ui.wan;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import com.example.jingbin.cloudreader.R;
import com.example.jingbin.cloudreader.base.BaseFragment;
import com.example.jingbin.cloudreader.databinding.FragmentContentBinding;
import com.example.jingbin.cloudreader.ui.wan.child.HomeFragment;
import com.example.jingbin.cloudreader.ui.wan.child.NavigationFragment;
import com.example.jingbin.cloudreader.ui.wan.child.TreeFragment;
import com.example.jingbin.cloudreader.ui.wan.child.WxArticleFragment;
import com.example.jingbin.cloudreader.view.MyFragmentPagerAdapter;
import com.example.jingbin.cloudreader.viewmodel.menu.NoViewModel;

import java.util.ArrayList;

/**
 * Created by jingbin on 16/12/14.
 * 展示玩安卓的页面
 */
public class WanFragment extends BaseFragment<NoViewModel, FragmentContentBinding> {

    private ArrayList<String> mTitleList = new ArrayList<>(4);
    private ArrayList<Fragment> mFragments = new ArrayList<>(4);

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        showLoading();
        initFragmentList();
        /**
         * 注意使用的是：getChildFragmentManager，
         * 这样setOffscreenPageLimit()就可以添加上，保留相邻2个实例，切换时不会卡
         */
        MyFragmentPagerAdapter myAdapter = new MyFragmentPagerAdapter(getChildFragmentManager(), mFragments, mTitleList);
        bindingView.vpGank.setAdapter(myAdapter);
        // 左右预加载页面的个数
        bindingView.vpGank.setOffscreenPageLimit(3);
        myAdapter.notifyDataSetChanged();
        bindingView.tabGank.setupWithViewPager(bindingView.vpGank);
        showContentView();
    }

    @Override
    public int setContent() {
        return R.layout.fragment_content;
    }

    private void initFragmentList() {
        mTitleList.clear();
        mTitleList.add("玩安卓");
        mTitleList.add("公众号");
        mTitleList.add("知识体系");
        mTitleList.add("导航");
        mFragments.add(HomeFragment.newInstance());
        mFragments.add(WxArticleFragment.newInstance());
//        mFragments.add(KnowledgeTreeFragment.newInstance());
        mFragments.add(TreeFragment.newInstance());
        mFragments.add(NavigationFragment.newInstance());
    }
}
