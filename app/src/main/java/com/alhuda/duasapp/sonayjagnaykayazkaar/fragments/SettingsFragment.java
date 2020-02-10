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
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.CompoundButton.OnCheckedChangeListener;

public class SettingsFragment extends Fragment{
	
	Context context;
	Switch switchEngTrans;
	Switch switchUrdTrans;
	Switch switchRef;
	
	Button btnTxtSizeMinus;
	Button btnTxtSizePlus;
	TextView tvFontSize;
	TextView tvFontSample;

	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		context = inflater.getContext();
		View view = inflater.inflate(R.layout.fragment_settings, container, false);
		return view;
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		switchEngTrans = (Switch) getActivity().findViewById(R.id.switch_end_trans);
	//	switchEngTrans.setClickable(false);
		
		switchUrdTrans = (Switch) getActivity().findViewById(R.id.switch_urd_trans);
		switchRef = (Switch) getActivity().findViewById(R.id.switch_ref);
		
		btnTxtSizeMinus = (Button) getActivity().findViewById(R.id.btn_size_minus);
		btnTxtSizePlus = (Button) getActivity().findViewById(R.id.btn_size_plus);
		tvFontSize = (TextView) getActivity().findViewById(R.id.tv_font_size);
		tvFontSample = (TextView) getActivity().findViewById(R.id.tv_font_size_sample);

		try {

			Typeface arabic_font = ResourcesCompat.getFont(context, R.font.arabic);
			tvFontSample.setTypeface(arabic_font);
		}

		catch (Exception e)
		{
			e.printStackTrace();

			// Prints what exception has been thrown
			System.out.println(e);
		}
		
	switchEngTrans.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				new SharedPreferencesSupplication().save(SingletonClass.keyEngTrans, isChecked);
			}
		});
		
		
		switchUrdTrans.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				new SharedPreferencesSupplication().save(SingletonClass.keyUrdTrans, isChecked);
			}
		});
		
		switchRef.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				new SharedPreferencesSupplication().save(SingletonClass.keyRef, isChecked);
			}
		});
		
		btnTxtSizeMinus.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				int textSize = FontSize.decreaseFontSize();
				tvFontSample.setTextSize(TypedValue.COMPLEX_UNIT_SP, textSize * 2);
				tvFontSize.setText("" + textSize * 2);
			}
		});
		
		btnTxtSizePlus.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				int textSize = FontSize.increaseFontSize();
				tvFontSample.setTextSize(TypedValue.COMPLEX_UNIT_SP, textSize * 2);
				tvFontSize.setText("" + textSize * 2);
			}
		});
		
		super.onActivityCreated(savedInstanceState);
	}
	
	@Override
	public void onResume() {
		
		boolean engTrans = new SharedPreferencesSupplication().read(SingletonClass.keyEngTrans, true);
		boolean urdTrans = new SharedPreferencesSupplication().read(SingletonClass.keyUrdTrans, true);
		boolean refVisible = new SharedPreferencesSupplication().read(SingletonClass.keyRef, true);
		
		if (engTrans)
			switchEngTrans.setChecked(true);
		else
			switchEngTrans.setChecked(false);
		
		if (urdTrans)
			switchUrdTrans.setChecked(true);
		else
			switchUrdTrans.setChecked(false);
		
		if (refVisible)
			switchRef.setChecked(true);
		else
			switchRef.setChecked(false);
		
		int textSize = FontSize.getFontSize();
		tvFontSample.setTextSize(TypedValue.COMPLEX_UNIT_SP, textSize * 2);
		tvFontSize.setText("" + textSize * 2);
		
		super.onResume();
	}

}
