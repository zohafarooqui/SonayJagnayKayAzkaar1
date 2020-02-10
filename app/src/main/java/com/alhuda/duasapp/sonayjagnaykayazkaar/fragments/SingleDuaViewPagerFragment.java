package com.alhuda.duasapp.sonayjagnaykayazkaar.fragments;


import com.alhuda.duasapp.sonayjagnaykayazkaar.R;
import com.alhuda.duasapp.sonayjagnaykayazkaar.utility.SharedPreferencesSupplication;
import com.alhuda.duasapp.sonayjagnaykayazkaar.utility.SingletonClass;
import com.alhuda.duasapp.sonayjagnaykayazkaar.activities.MainActivity;
import com.alhuda.duasapp.sonayjagnaykayazkaar.general.FavoriteDuas;

import android.content.Context;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

public class SingleDuaViewPagerFragment extends Fragment {
	
	Context context;
	int mPageNumber;
	String identifier;
	
	ViewGroup rootView;
	private ViewPager mPager;
	private PagerAdapter mPagerAdapter;
	
	MediaPlayer mp;
	Button btn_list_duas;
	
	LinearLayout ll_back_single_dua_header;
	LinearLayout ll_back_single_dua_footer;
	TextView tv_back_single_dua_header_title;
	TextView tv_back_single_dua_footer_counter;
	
	Button btnPlayPauseDua;
	ToggleButton togbtnIsFavorite;
	ToggleButton togbtnRepeat;
	Button btnShareDua;
	boolean repeatDua;
	
	int currPos;
	
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		context = inflater.getContext();
		rootView = (ViewGroup) inflater.inflate(R.layout.fragment_single_dua_view_pager, container, false);
		return rootView;
	}
	
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		ll_back_single_dua_header = (LinearLayout) rootView.findViewById(R.id.ll_back_single_dua_header);
		ll_back_single_dua_footer = (LinearLayout) rootView.findViewById(R.id.ll_back_single_dua_footer);
		tv_back_single_dua_header_title = (TextView) rootView.findViewById(R.id.tv_back_single_dua_header_title);
		tv_back_single_dua_footer_counter = (TextView) rootView.findViewById(R.id.tv_back_single_dua_footer_counter);
		
		togbtnIsFavorite = (ToggleButton) rootView.findViewById(R.id.tog_btn_favorite_dua);
		togbtnRepeat = (ToggleButton) rootView.findViewById(R.id.tog_btn_repeat_dua);
		btnPlayPauseDua = (Button) rootView.findViewById(R.id.btn_play_pause_dua);
		btnShareDua = (Button) rootView.findViewById(R.id.btn_share_dua);
		
		btn_list_duas = (Button) rootView.findViewById(R.id.btn_list_duas);
		
	
	btn_list_duas.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				((MainActivity) context).loadDuasListFragment();
			}
		});
	
	btnPlayPauseDua.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View arg0) {
			if (mp instanceof MediaPlayer) {
				if (mp.isPlaying()) {
					mp.pause();
					btnPlayPauseDua.setBackgroundResource(R.drawable.play_btn_custom);
					currPos = mp.getCurrentPosition();
				}
				else {
					btnPlayPauseDua.setBackgroundResource(R.drawable.pause_btn_custom);
					mp.setOnCompletionListener(new OnCompletionListener() {
						
						@Override
						public void onCompletion(MediaPlayer _mp) {
							btnPlayPauseDua.setBackgroundResource(R.drawable.play_btn_custom);
							currPos = 0;
							if (repeatDua) {
								btnPlayPauseDua.performClick();
							}
							else {
								if (new SharedPreferencesSupplication().read(SingletonClass.keyPlayAll, false)) {
									if (mPager.getCurrentItem() > 0) {
										mPager.setCurrentItem(mPager.getCurrentItem() - 1);
										btnPlayPauseDua.performClick();
									}
								}
							}
						}
					});
					mp.seekTo(currPos);
					mp.start();
				}
			}
			else {
				Toast.makeText(context, "No audio file found", Toast.LENGTH_LONG).show();
			}
		}
	});
	
	togbtnIsFavorite.setOnCheckedChangeListener(new OnCheckedChangeListener() {
		
		@Override
		public void onCheckedChanged(CompoundButton arg0, boolean state) {
			
			String verses = new SharedPreferencesSupplication().read(SingletonClass.keyListOfVerses, "s1");
			String[] versesList = verses.split(",");
			int index = (versesList.length - 1) - mPageNumber;
			String identifier = versesList[index];
			if (state)
				new FavoriteDuas().addDua(identifier);
			else{
				new FavoriteDuas().removeDua(identifier);
			}
		}
	});
	
	togbtnRepeat.setOnCheckedChangeListener(new OnCheckedChangeListener() {
		
		@Override
		public void onCheckedChanged(CompoundButton arg0, boolean state) {
			repeatDua = state;
			Toast.makeText(context, repeatDua ? "Repeat On" : "Repeat Off", Toast.LENGTH_SHORT).show();
		}
	});

	btnShareDua.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View arg0) {
			int index = (SingletonClass.duasAra.size() - 1) - mPageNumber;
			String toBeShared = SingletonClass.duasAra.get(index);
			toBeShared = toBeShared + "\n\n" + SingletonClass.duasEng.get(index);
			toBeShared = toBeShared + "\n\n" + SingletonClass.duasUrd.get(index);
			toBeShared = toBeShared + "\n\n" + SingletonClass.duasRef.get(index);
			toBeShared = toBeShared + "\n\n" + "Dua shared by Alhuda International Sonay Jagnay Kay Azkaar Duas App for Android (c) 2014.";
			toBeShared = toBeShared + "\n" + "www.farhathashmi.com, www.alhudapk.com";
			((MainActivity) context).shareDua(toBeShared);
		}
	});
		
		mPager = (ViewPager) rootView.findViewById(R.id.pager);
		mPagerAdapter = new ScreenSlidePagerAdapter(getChildFragmentManager());
		mPager.setOnPageChangeListener(new OnPageChangeListener() {
			
			@Override
			public void onPageSelected(int position) {
				mPageNumber = position;
				stopMusicPlayback();
				updateView();
			}
			
			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {}
			
			@Override
			public void onPageScrollStateChanged(int arg0) {}
		});
		mPager.setAdapter(mPagerAdapter);
		String verses = new SharedPreferencesSupplication().read(SingletonClass.keyListOfVerses, "s1");
		String[] versesList = verses.split(",");
		int total = versesList.length;
		int position = new SharedPreferencesSupplication().read(SingletonClass.keySelVerseFromList, 0);
		mPager.setCurrentItem((total - 1) - position);
		super.onActivityCreated(savedInstanceState);
		
	
	}
	
	@Override
	public void onResume() {
		
		repeatDua = false;
		togbtnRepeat.setChecked(false);
		
		updateView();
	if (new SharedPreferencesSupplication().read(SingletonClass.keyPlayAll, false))
			btnPlayPauseDua.performClick();
		super.onResume();
	}
	
	void updateView() {
		
		
		mPageNumber = mPager.getCurrentItem();
		String verses = new SharedPreferencesSupplication().read(SingletonClass.keyListOfVerses, "s1");
		String[] versesList = verses.split(",");
		int index = (versesList.length - 1) - mPageNumber;
		identifier = versesList[index];
		
		int resID = getActivity().getResources().getIdentifier(identifier, "raw", getActivity().getPackageName());
		try {
			mp = MediaPlayer.create(context, resID);
		}
		catch (Exception e) {
			mp = null;
		}
		btnPlayPauseDua.setBackgroundResource(R.drawable.play_btn_custom);
		
		
	
		tv_back_single_dua_footer_counter.setText((index + 1) + " / " + versesList.length);
		
		if (new FavoriteDuas().isDuaFavorite(identifier))
			togbtnIsFavorite.setChecked(true);
		else
			togbtnIsFavorite.setChecked(false);
	
		boolean engTrans = new SharedPreferencesSupplication().read(SingletonClass.keyEngTrans, true);
		boolean urduTrans = new SharedPreferencesSupplication().read(SingletonClass.keyUrdTrans, true);
		
		if (identifier.charAt(0) == 's') {
			ll_back_single_dua_header.setBackgroundResource(R.color.sleep_color);
			ll_back_single_dua_footer.setBackgroundResource(R.color.sleep_color);
			if(urduTrans &&!engTrans)
			{
				tv_back_single_dua_header_title.setText(getActivity().getResources().getString(R.string.sduasurd));
				try{
					Typeface urdu_font = ResourcesCompat.getFont(context, R.font.jameel);
					tv_back_single_dua_header_title.setTypeface(urdu_font);
				}
				catch (Exception e)
				{
					e.printStackTrace();
					// Prints what exception has been thrown
					System.out.println(e);
				}
			}
				
			
			else
					tv_back_single_dua_header_title.setText(getActivity().getResources().getString(R.string.sduas));
		}
		else {
			ll_back_single_dua_header.setBackgroundResource(R.color.wake_up_color);
			ll_back_single_dua_footer.setBackgroundResource(R.color.wake_up_color);
			
			if(urduTrans &&!engTrans)
				{
				
				tv_back_single_dua_header_title.setText(getActivity().getResources().getString(R.string.wduasurd));

				try{
						Typeface urdu_font = ResourcesCompat.getFont(context, R.font.jameel);
						tv_back_single_dua_header_title.setTypeface(urdu_font);
					}
					catch (Exception e)
					{
						e.printStackTrace();
						// Prints what exception has been thrown
						System.out.println(e);
					}
				}
				else
					tv_back_single_dua_header_title.setText(getActivity().getResources().getString(R.string.wduas));
			
		}
		
		currPos = 0;
	}
	
	@Override
	public void onPause() {
		new SharedPreferencesSupplication().save(SingletonClass.keyPlayAll, false);
		stopMusicPlayback();
		super.onPause();
	}
	
	void stopMusicPlayback() {
		if (mp instanceof MediaPlayer) {
			mp.stop();
			mp.release();
		}
		btnPlayPauseDua.setBackgroundResource(R.drawable.play_btn_custom);
	}
	
	
	private class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter {
		
		public ScreenSlidePagerAdapter(FragmentManager fm) {
			super(fm);
		}
		
		@Override
		public Fragment getItem(int position) {
			return (SingleDuaFragment.create(position));
		}
		
		@Override
		public int getCount() {
			String verses = new SharedPreferencesSupplication().read(SingletonClass.keyListOfVerses, "s1");
			String[] versesList = verses.split(",");
			return versesList.length;
		}
		
	}

}

