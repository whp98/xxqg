package xyz.intellij.xxqgDemo.ui.main;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;


import java.lang.reflect.Array;
import java.util.ArrayList;

import xyz.intellij.xxqgDemo.R;
import xyz.intellij.xxqgDemo.VideoItem;

public class ListFragment extends Fragment {


    public static ListFragment newInstance() {
        return new ListFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_item, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        // TODO: Use the ViewModel
        //添加数据到ListView
        final ArrayList<String> names = new ArrayList<String>();
        VideoItem aaa = new VideoItem();
        aaa.loadData(this.getContext());
        for(int i=0, count=VideoItem.ITEM_MAP.size(); i<count; i++){
            names.add(aaa.ITEMS.get(i).name);
        }

        //列表页面的ListView
        ListView listView = (ListView) getActivity().findViewById(R.id.item_name);
        listView.setAdapter(new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_1, names));

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                //实例化详情Fragment
                Fragment detailFragment = new PlayFragment();

                //从列表页面传递需要的参数到详情页面
                Bundle mBundle = new Bundle();
                mBundle.putString("arg", position+"");
                detailFragment.setArguments(mBundle);

                final FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                final FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                //判断手机的横竖屏
                Configuration configuration = getActivity().getResources().getConfiguration();
                int ori = configuration.orientation;

                fragmentTransaction.replace(R.id.paly_place, detailFragment);

                if(ori == configuration.ORIENTATION_PORTRAIT){
                    fragmentTransaction.addToBackStack(null);
                }

                fragmentTransaction.commit();


            }
        });
    }

}
