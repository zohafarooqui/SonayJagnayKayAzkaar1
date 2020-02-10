package com.alhuda.duasapp.sonayjagnaykayazkaar.fragments;
import com.alhuda.duasapp.sonayjagnaykayazkaar.R;
import com.alhuda.duasapp.sonayjagnaykayazkaar.general.FontSize;
import com.alhuda.duasapp.sonayjagnaykayazkaar.utility.SharedPreferencesSupplication;
import com.alhuda.duasapp.sonayjagnaykayazkaar.utility.SingletonClass;

import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.res.ResourcesCompat;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;
import android.widget.TextView;

public class SingleDuaFragment  extends Fragment{
	
Context context;
	
	public static final String ARG_PAGE = "page";
	int mPageNumber;
	ViewGroup rootView;
	
	TextView tvDuaArabic;
	TextView tvDuaEnglish;
	TextView tvDuaUrdu;
	TextView tvDuaRef;
	TextView dividerEng;
	TextView dividerUrdu;
	TextView dividerRef;
	TextView tvTitle;

	
	ScrollView svContent;
	
	public static SingleDuaFragment create(int pageNumber) {
		SingleDuaFragment fragment = new SingleDuaFragment();
		Bundle args = new Bundle();
		args.putInt(ARG_PAGE, pageNumber);
		fragment.setArguments(args);
		return fragment;
	}

public SingleDuaFragment() {}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mPageNumber = getArguments().getInt(ARG_PAGE);

	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		context = inflater.getContext();
		rootView = (ViewGroup) inflater.inflate(R.layout.fragment_single_dua, container, false);
		return rootView;
	}
	
	@Override
	public void onResume() {
		String verses = new SharedPreferencesSupplication().read(SingletonClass.keyListOfVerses, "s1");
		String[] versesList = verses.split(",");
		int index = (versesList.length - 1) - mPageNumber;
		String identifier = versesList[index];
		
		boolean engTrans = new SharedPreferencesSupplication().read(SingletonClass.keyEngTrans, true);
		boolean urduTrans = new SharedPreferencesSupplication().read(SingletonClass.keyUrdTrans, true);
		boolean refVisible = new SharedPreferencesSupplication().read(SingletonClass.keyRef, true);
		
		
		tvDuaArabic = (TextView) rootView.findViewById(R.id.tv_arabic);
		tvDuaEnglish = (TextView) rootView.findViewById(R.id.tv_english);
		tvDuaUrdu = (TextView) rootView.findViewById(R.id.tv_urdu);
		tvDuaRef = (TextView) rootView.findViewById(R.id.tv_ref);
		tvTitle = (TextView) rootView.findViewById(R.id.tv_title);
		dividerEng = (TextView) rootView.findViewById(R.id.divider_english);
		dividerUrdu = (TextView) rootView.findViewById(R.id.divider_urdu);
		dividerRef = (TextView) rootView.findViewById(R.id.divider_ref);
		
		tvDuaArabic.setText(SingletonClass.duasAra.get(index));
		tvDuaEnglish.setText(SingletonClass.duasEng.get(index));
		tvDuaUrdu.setText(SingletonClass.duasUrd.get(index));

		tvDuaRef.setText(SingletonClass.duasRef.get(index));

		try{

            Typeface arabic_font = ResourcesCompat.getFont(context, R.font.arabic);
            tvDuaArabic.setTypeface(arabic_font);
            Typeface urdu_font = ResourcesCompat.getFont(context, R.font.jameel);
            tvDuaUrdu.setTypeface(urdu_font);
            tvDuaRef.setTypeface(urdu_font);
            tvTitle.setTypeface(urdu_font);
		}
		catch (Exception e)
		{
			 e.printStackTrace(); 
             
	            // Prints what exception has been thrown 
	            System.out.println(e);
		}
		


	
		if((SingletonClass.duasEngTit.get(index).equals("NONE"))){
			tvTitle.setVisibility(View.GONE);
		}
		else
		{
			if(urduTrans && !engTrans)
			tvTitle.setText((getActivity().getResources().getString(R.string.title))+" "+SingletonClass.duasUrdTit.get(index)+" "+(getActivity().getResources().getString(R.string.title)));
			else
			tvTitle.setText((getActivity().getResources().getString(R.string.title))+" "+SingletonClass.duasEngTit.get(index)+" "+(getActivity().getResources().getString(R.string.title)));
			
			
			if (identifier.charAt(0) == 's')
			tvTitle.setTextColor(getActivity().getResources().getColor(R.color.sleep_color));
			else
			tvTitle.setTextColor(getActivity().getResources().getColor(R.color.wake_up_color));
		}
		
		
		if (identifier.charAt(0) == 's') {
			dividerEng.setTextColor(getActivity().getResources().getColor(R.color.sleep_color));
			dividerUrdu.setTextColor(getActivity().getResources().getColor(R.color.sleep_color));
			dividerRef.setTextColor(getActivity().getResources().getColor(R.color.sleep_color));
		}
		else {
			dividerEng.setTextColor(getActivity().getResources().getColor(R.color.wake_up_color));
			dividerUrdu.setTextColor(getActivity().getResources().getColor(R.color.wake_up_color));
			dividerRef.setTextColor(getActivity().getResources().getColor(R.color.wake_up_color));
		}
		
		int fontsize = FontSize.getFontSize();
		tvDuaArabic.setTextSize(TypedValue.COMPLEX_UNIT_SP, fontsize * 2);
		tvDuaEnglish.setTextSize(TypedValue.COMPLEX_UNIT_SP, fontsize);
		tvDuaUrdu.setTextSize(TypedValue.COMPLEX_UNIT_SP, fontsize);
		tvDuaRef.setTextSize(TypedValue.COMPLEX_UNIT_SP, (float) (fontsize * 0.75));
		tvTitle.setTextSize(TypedValue.COMPLEX_UNIT_SP, fontsize + 2);
	//	dividerEng.setTextSize(TypedValue.COMPLEX_UNIT_SP, (float) (fontsize * 2));
		//dividerUrdu.setTextSize(TypedValue.COMPLEX_UNIT_SP, (float) (fontsize * 2));
		//dividerRef.setTextSize(TypedValue.COMPLEX_UNIT_SP, (float) (fontsize * 2));	
		
		
		
		if (engTrans) {
			tvDuaEnglish.setVisibility(View.VISIBLE);
			dividerEng.setVisibility(View.VISIBLE);
		}
		else {
			tvDuaEnglish.setVisibility(View.GONE);
			dividerEng.setVisibility(View.GONE);
		}
		
		if (urduTrans) {
			tvDuaUrdu.setVisibility(View.VISIBLE);
			dividerUrdu.setVisibility(View.VISIBLE);
		}
		else {
			tvDuaUrdu.setVisibility(View.GONE);
			dividerUrdu.setVisibility(View.GONE);
		}
		
		if (refVisible) {
			tvDuaRef.setVisibility(View.VISIBLE);
			dividerRef.setVisibility(View.VISIBLE);
		}
		else {
			tvDuaRef.setVisibility(View.GONE);
			dividerRef.setVisibility(View.GONE);
		}
		
		svContent = (ScrollView) rootView.findViewById(R.id.sv_content);
		svContent.scrollTo(0, 0);
		
		super.onResume();
}
	
	public int getPageNumber() {
		return mPageNumber;
	}
}
