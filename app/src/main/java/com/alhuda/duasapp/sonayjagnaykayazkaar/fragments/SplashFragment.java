package com.alhuda.duasapp.sonayjagnaykayazkaar.fragments;

import com.alhuda.duasapp.sonayjagnaykayazkaar.R;
import com.alhuda.duasapp.sonayjagnaykayazkaar.activities.MainActivity;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class SplashFragment extends Fragment {

	Context context;
	
	boolean fragmentActive;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		context = inflater.getContext();
		View view = inflater.inflate(R.layout.fragment_splash, container, false);
		return view;
	}
	
	@Override
	public void onResume() {
		fragmentActive = true;
		super.onResume();
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				try {
					Thread.sleep(1500);
				}
				catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if (fragmentActive) {
					getActivity().runOnUiThread(new Runnable() {
						
						@Override
						public void run() {
							((MainActivity) context).loadMainFragment();
						}
					});
				}
			}
		}).start();
	}
	
	@Override
	public void onPause() {
		fragmentActive = false;
		super.onPause();
	}

}
