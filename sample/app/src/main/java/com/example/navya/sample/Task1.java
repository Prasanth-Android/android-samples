package com.example.navya.sample;




/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Task1.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Task1#newInstance} factory method to
 * create an instance of this fragment.
 */
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Task1 extends Fragment {

    SectionsPagerAdapter mSectionsPagerAdapter;
    ViewPager mViewPager;
    View rootView;
    LinearLayout buttonslider;
    Button btn;
    private RelativeLayout rel_color;
    private Button red_color,blue_color,green_color;

    View page1,page2,page3,page4;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_task1, container, false);

        mSectionsPagerAdapter = new SectionsPagerAdapter(getChildFragmentManager());

        mViewPager = (ViewPager) rootView.findViewById(R.id.viewpager);
        mSectionsPagerAdapter.addfragments(PlaceholderFragment.newInstance(0));
        mSectionsPagerAdapter.addfragments(PlaceholderFragment.newInstance(1));
        mSectionsPagerAdapter.addfragments(PlaceholderFragment.newInstance(2));
        mSectionsPagerAdapter.addfragments(PlaceholderFragment.newInstance(3));
        mViewPager.setAdapter(mSectionsPagerAdapter);

        page1 = (View)rootView.findViewById(R.id.page1);
        page2 = (View)rootView.findViewById(R.id.page2);
        page3 = (View)rootView.findViewById(R.id.page3);
        page4 = (View)rootView.findViewById(R.id.page4);


        page1.setBackgroundColor(Color.BLACK);
        page2.setBackgroundColor(Color.WHITE);
        page3.setBackgroundColor(Color.WHITE);
        page4.setBackgroundColor(Color.WHITE);
        Toast toast = Toast.makeText(Task1.this.getActivity(), "Page -> 1", Toast.LENGTH_SHORT);
        toast.show();



        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int pos, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int pos) {
                Toast toast = Toast.makeText(Task1.this.getActivity(), "Page -> 1", Toast.LENGTH_LONG);
                if(pos == 0) {
                    page1.setBackgroundColor(Color.BLACK);
                    page2.setBackgroundColor(Color.WHITE);
                    page3.setBackgroundColor(Color.WHITE);
                    page4.setBackgroundColor(Color.WHITE);
                    toast.cancel();
                    toast = null;
                    toast = Toast.makeText(Task1.this.getActivity(), "Page -> 1", Toast.LENGTH_SHORT);
                    toast.setText("Page  -> 1");
                    toast.show();
                }
                if(pos==1){
                    page2.setBackgroundColor(Color.BLACK);
                    page1.setBackgroundColor(Color.WHITE);
                    page3.setBackgroundColor(Color.WHITE);
                    page4.setBackgroundColor(Color.WHITE);
                    toast.cancel();
                    toast = null;
                    toast = Toast.makeText(Task1.this.getActivity(), "Page -> 2", Toast.LENGTH_SHORT);
                    toast.setText("Page  -> 2");
                    toast.show();
                }
                if(pos==2){
                    page3.setBackgroundColor(Color.BLACK);
                    page1.setBackgroundColor(Color.WHITE);
                    page2.setBackgroundColor(Color.WHITE);
                    page4.setBackgroundColor(Color.WHITE);
                    toast.cancel();
                    toast = null;
                    toast = Toast.makeText(Task1.this.getActivity(), "Page -> 3", Toast.LENGTH_SHORT);
                    toast.setText("Page  -> 3");
                    toast.show();
                }
                if(pos==3) {
                    page4.setBackgroundColor(Color.BLACK);
                    page2.setBackgroundColor(Color.WHITE);
                    page3.setBackgroundColor(Color.WHITE);
                    page1.setBackgroundColor(Color.WHITE);
                    toast.cancel();
                    toast = null;
                    toast = Toast.makeText(Task1.this.getActivity(), "Page -> 4", Toast.LENGTH_SHORT);
                    toast.setText("Page  -> 4");
                    toast.show();
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        red_color = (Button)rootView.findViewById(R.id.color1);
        blue_color = (Button)rootView.findViewById(R.id.color2);
        green_color = (Button)rootView.findViewById(R.id.color3);
        rel_color = (RelativeLayout)rootView.findViewById(R.id.rel_color);
        buttonslider = (LinearLayout)rootView.findViewById(R.id.buttonslider);
        final TextView button_display = (TextView)rootView.findViewById(R.id.button_display);
        for(int i=0;i<5;i++) {
            btn  = new Button(Task1.this.getActivity());
            btn.setText("Item"+i);
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Button b = (Button)v;
                    button_display.setText(b.getText());
                }
            });
            buttonslider.addView(btn);
        }


        red_color.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rel_color.setBackgroundColor(Color.parseColor("#FF0000"));
            }
        });
        blue_color.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rel_color.setBackgroundColor(Color.parseColor("#0000FF"));
            }
        });
        green_color.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rel_color.setBackgroundColor(Color.parseColor("#00FF00"));
            }
        });
        return rootView;


    }

    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        List<Fragment> fragmentos;
        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
            fragmentos = new ArrayList<Fragment>();
        }

        public void addfragments(Fragment xfragment){
            fragmentos.add(xfragment);
        }


        @Override
        public Fragment getItem(int position) {
            return fragmentos.get(position);
        }

        @Override
        public int getCount() {
            return fragmentos.size();
        }
    }

    public static class PlaceholderFragment extends Fragment {
        private static final String ARG_POS = "pos";
        private int pos;

        public static PlaceholderFragment newInstance(int position) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_POS, position);
            fragment.setArguments(args);
            fragment.setRetainInstance(true);
            return fragment;
        }

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            if(getArguments() != null) {
                pos = getArguments().getInt(ARG_POS);
            }
        }

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.viewpager_fragment, container, false);

            LinearLayout page = (LinearLayout)rootView.findViewById(R.id.page);
            if(pos == 0) {
                page.setBackgroundColor(Color.BLUE);
            }
            if(pos==1){
                page.setBackgroundColor(Color.RED);}
            if(pos==2){
                page.setBackgroundColor(Color.GREEN);}
            if(pos==3) {
                page.setBackgroundColor(Color.YELLOW);
            }

            //Toast.makeText(this.getActivity(),pos,Toast.LENGTH_LONG).show();
            return rootView;
        }
    }

}
