package com.alhuda.duasapp.sonayjagnaykayazkaar.activities;

import com.alhuda.duasapp.sonayjagnaykayazkaar.R;
import com.alhuda.duasapp.sonayjagnaykayazkaar.fragments.DuasListFragment;
import com.alhuda.duasapp.sonayjagnaykayazkaar.fragments.MainFragment;
import com.alhuda.duasapp.sonayjagnaykayazkaar.fragments.SettingsFragment;
import com.alhuda.duasapp.sonayjagnaykayazkaar.fragments.SingleDuaViewPagerFragment;
import com.alhuda.duasapp.sonayjagnaykayazkaar.fragments.SplashFragment;
import com.alhuda.duasapp.sonayjagnaykayazkaar.utility.SharedPreferencesSupplication;
import com.alhuda.duasapp.sonayjagnaykayazkaar.utility.SingletonClass;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.app.ActionBar;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;

public class MainActivity   extends FragmentActivity {

	Context context;
	boolean activityActive;
	
	Fragment frag;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		getWindow().setFlags(android.view.WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON,
                android.view.WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
		ActionBar actionbar = getActionBar();
		actionbar.hide();
		context = MainActivity.this;
			SingletonClass.applicationContext = getApplicationContext();

		FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
		frag = new SplashFragment();
		ft.replace(R.id.fl_view, frag);
		ft.commit();
	}
	
	@Override
	protected void onResume() {
		activityActive = true;
		super.onResume();
	}
	
	@Override
	protected void onPause() {
		activityActive = false;
		super.onPause();
	}
	
	public void loadMainFragment() {
		if (activityActive) {
			FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
			frag = new MainFragment();
			ft.replace(R.id.fl_view, frag);
			ft.commit();
		}
	}
	
	public void loadSingleDuaFragment() {
		if (activityActive) {
			FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
			frag = new SingleDuaViewPagerFragment();
			ft.replace(R.id.fl_view, frag);
			ft.commit();
		}
	}

	public void loadSettingsFragment() {
		if (activityActive) {
			FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
			frag = new SettingsFragment();
			ft.replace(R.id.fl_view, frag);
			ft.commit();
		}
	}
	
	public void loadDuasListFragment() {
		if (activityActive) {
			FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
			frag = new DuasListFragment();
			ft.replace(R.id.fl_view, frag);
			ft.commit();
		}
	}
	
	public void shareDua(CharSequence charSequence) {
		if (activityActive) {
			Intent sendIntent = new Intent();
			sendIntent.setAction(Intent.ACTION_SEND);
			sendIntent.putExtra(Intent.EXTRA_TEXT, charSequence);
			sendIntent.setType("text/plain");
			startActivityForResult(Intent.createChooser(sendIntent, "Share On:"), 123);
		}
	}
	
	@Override
	public void onBackPressed() {
		if (frag instanceof SettingsFragment)
			loadMainFragment();
		else if (frag instanceof SingleDuaViewPagerFragment) {
			boolean keyIsFavSel = new SharedPreferencesSupplication().read(SingletonClass.keyIsFavSelected, false);
			if (keyIsFavSel)
				loadDuasListFragment();
			else
				loadMainFragment();
		}
		else if (frag instanceof DuasListFragment) {
			boolean keyIsFavSel = new SharedPreferencesSupplication().read(SingletonClass.keyIsFavSelected, false);
			if (keyIsFavSel)
				loadMainFragment();
			else
				loadSingleDuaFragment();
		}
		else
			super.onBackPressed();
	}
}
