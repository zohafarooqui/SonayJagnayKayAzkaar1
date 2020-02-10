package com.alhuda.duasapp.sonayjagnaykayazkaar.fragments;


import java.util.ArrayList;

import com.alhuda.duasapp.sonayjagnaykayazkaar.R;
import com.alhuda.duasapp.sonayjagnaykayazkaar.activities.MainActivity;
import com.alhuda.duasapp.sonayjagnaykayazkaar.general.FavoriteDuas;
import com.alhuda.duasapp.sonayjagnaykayazkaar.utility.SharedPreferencesSupplication;
import com.alhuda.duasapp.sonayjagnaykayazkaar.utility.SingletonClass;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class MainFragment  extends Fragment {

	Context context;
	Button SleepDuas;
	Button DayDuas;
	Button favoriteDuas;
	Button settingsPage;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		context = inflater.getContext();
		View view = inflater.inflate(R.layout.fragment_main, container, false);
		return view;
	}
	
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		
		
		SleepDuas = (Button) getActivity().findViewById(R.id.btn_sleep_duas);
		DayDuas= (Button) getActivity().findViewById(R.id.btn_day_duas);
		favoriteDuas = (Button) getActivity().findViewById(R.id.btn_favorite_duas);
		settingsPage = (Button) getActivity().findViewById(R.id.btn_settings_dua);
		
		
		
		SleepDuas.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				new SharedPreferencesSupplication().save(SingletonClass.keyIsFavSelected, false);
				SingletonClass.duasAra = new ArrayList<String>();
				SingletonClass.duasEng = new ArrayList<String>();
				SingletonClass.duasUrd = new ArrayList<String>();
				SingletonClass.duasRef = new ArrayList<String>();
				SingletonClass.duasEngTit =new ArrayList<String>();
				SingletonClass.duasUrdTit = new ArrayList<String>();
				String selectedOnes = "s0";
				String[] duasAra = getActivity().getResources().getStringArray(R.array.s_duas_ara);
				String[] duasEng = getActivity().getResources().getStringArray(R.array.s_duas_eng);
				String[] duasUrd = getActivity().getResources().getStringArray(R.array.s_duas_urd);
				String[] duasRef = getActivity().getResources().getStringArray(R.array.s_duas_ref);
				String[] duasEngTitles =getActivity().getResources().getStringArray(R.array.s_duas_eng_titles);
				String[] duasUrdTitles =getActivity().getResources().getStringArray(R.array.s_duas_urd_titles);
				
				SingletonClass.duasAra.add(duasAra[0]);
				SingletonClass.duasEng.add(duasEng[0]);
				SingletonClass.duasUrd.add(duasUrd[0]);
				SingletonClass.duasRef.add(duasRef[0]);
				SingletonClass.duasEngTit.add(duasEngTitles[0]);
				SingletonClass.duasUrdTit.add(duasUrdTitles[0]);
				for (int i = 1; i < duasRef.length; i++) {
					selectedOnes = selectedOnes + ",s" + (i);
					SingletonClass.duasAra.add(duasAra[i]);
					SingletonClass.duasEng.add(duasEng[i]);
					SingletonClass.duasUrd.add(duasUrd[i]);
					SingletonClass.duasRef.add(duasRef[i]);
					SingletonClass.duasEngTit.add(duasEngTitles[i]);
					SingletonClass.duasUrdTit.add(duasUrdTitles[i]);
				}
				new SharedPreferencesSupplication().save(SingletonClass.keyListOfVerses, selectedOnes);
				new SharedPreferencesSupplication().save(SingletonClass.keySelVerseFromList, 0);
				((MainActivity) context).loadSingleDuaFragment();
			}
		});
		
		DayDuas.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				new SharedPreferencesSupplication().save(SingletonClass.keyIsFavSelected, false);
				SingletonClass.duasAra = new ArrayList<String>();
				SingletonClass.duasEng = new ArrayList<String>();
				SingletonClass.duasUrd = new ArrayList<String>();
				SingletonClass.duasRef = new ArrayList<String>();
				SingletonClass.duasEngTit =new ArrayList<String>();
				SingletonClass.duasUrdTit = new ArrayList<String>();
				String selectedOnes = "d0";
				String[] duasAra = getActivity().getResources().getStringArray(R.array.d_duas_ara);
				String[] duasEng = getActivity().getResources().getStringArray(R.array.d_duas_eng);
				String[] duasUrd = getActivity().getResources().getStringArray(R.array.d_duas_urd);
				String[] duasRef = getActivity().getResources().getStringArray(R.array.d_duas_ref);
				String[] duasEngTitles =getActivity().getResources().getStringArray(R.array.d_duas_eng_titles);
				String[] duasUrdTitles =getActivity().getResources().getStringArray(R.array.d_duas_urd_titles);
				SingletonClass.duasAra.add(duasAra[0]);
				SingletonClass.duasEng.add(duasEng[0]);
				SingletonClass.duasUrd.add(duasUrd[0]);
				SingletonClass.duasRef.add(duasRef[0]);
				SingletonClass.duasEngTit.add(duasEngTitles[0]);
				SingletonClass.duasUrdTit.add(duasUrdTitles[0]);
				for (int i = 1; i < duasRef.length; i++) {
					selectedOnes = selectedOnes + ",d" + (i);
					SingletonClass.duasAra.add(duasAra[i]);
					SingletonClass.duasEng.add(duasEng[i]);
					SingletonClass.duasUrd.add(duasUrd[i]);
					SingletonClass.duasRef.add(duasRef[i]);
					SingletonClass.duasEngTit.add(duasEngTitles[i]);
					SingletonClass.duasUrdTit.add(duasUrdTitles[i]);
				}
				new SharedPreferencesSupplication().save(SingletonClass.keyListOfVerses, selectedOnes);
				new SharedPreferencesSupplication().save(SingletonClass.keySelVerseFromList, 0);
				((MainActivity) context).loadSingleDuaFragment();
			}
		});

		settingsPage.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				new SharedPreferencesSupplication().save(SingletonClass.keyIsFavSelected, false);
				((MainActivity) context).loadSettingsFragment();
			}
		});
		
favoriteDuas.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				
		
				new SharedPreferencesSupplication().save(SingletonClass.keyIsFavSelected, true);
				String fDuas = new SharedPreferencesSupplication().read(SingletonClass.keyFavVerses, "");
				
				if (!(fDuas.isEmpty())) {
					
					new FavoriteDuas().refreshLists(context, fDuas);
			
					//loadFavDuas();
				new SharedPreferencesSupplication().save(SingletonClass.keyListOfVerses, fDuas);
				((MainActivity) context).loadDuasListFragment();
				}
				else
					Toast.makeText(context, "No Supplication has been marked favorite", Toast.LENGTH_LONG).show();
			
			}
			
		});
		super.onActivityCreated(savedInstanceState);
	}
	
}
