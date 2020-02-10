package com.alhuda.duasapp.sonayjagnaykayazkaar.fragments;



import java.util.ArrayList;

import com.alhuda.duasapp.sonayjagnaykayazkaar.R;
import com.alhuda.duasapp.sonayjagnaykayazkaar.adapters.DuasListAdapter;
import com.alhuda.duasapp.sonayjagnaykayazkaar.general.FavoriteDuas;
import com.alhuda.duasapp.sonayjagnaykayazkaar.general.FontSize;
import com.alhuda.duasapp.sonayjagnaykayazkaar.utility.SharedPreferencesSupplication;
import com.alhuda.duasapp.sonayjagnaykayazkaar.utility.SingletonClass;
import com.alhuda.duasapp.sonayjagnaykayazkaar.activities.MainActivity;




import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.res.ResourcesCompat;
import android.util.SparseBooleanArray;
import android.util.TypedValue;
import android.view.ActionMode;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.AbsListView.MultiChoiceModeListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.CompoundButton.OnCheckedChangeListener;

public class DuasListFragment extends Fragment{
	
Context context;
	
	LinearLayout ll_back_duas_list_header;
	TextView ll_back_duas_list_header_title;
	ListView lvDuaas;
public	DuasListAdapter duasAdapter;
int intListViewItemPosition = -1;
 ArrayList<String> duas;

 Button btnRemoveallDuas;
 Button btnPlayAll;

 
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		context = inflater.getContext();
		View view = inflater.inflate(R.layout.fragment_duas_list, container, false);
	
		setHasOptionsMenu(true);
		
	// TODO Auto-generated method stub
		
		
		return view;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		
	
		btnRemoveallDuas = (Button) getActivity().findViewById(R.id.btn_remove_all_duas);
		btnPlayAll = (Button) getActivity().findViewById(R.id.btn_play_all_duas);
			
		ll_back_duas_list_header = (LinearLayout) getActivity().findViewById(R.id.ll_back_duas_list_header);
		ll_back_duas_list_header_title = (TextView) getActivity().findViewById(R.id.ll_back_duas_list_header_title);
		lvDuaas = (ListView) getActivity().findViewById(R.id.lv_duas);
		
		

		String verses = new SharedPreferencesSupplication().read(SingletonClass.keyListOfVerses, "s1");
		String[] versesList = verses.split(",");
		duas = new ArrayList<String>();
		for (int i = 0; i < versesList.length; i++) {
			if (versesList[i].length() > 0)
				duas.add(versesList[i]);
		}

		duasAdapter = new DuasListAdapter(context, duas);
		lvDuaas.setAdapter(duasAdapter);

		
		
  btnPlayAll.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				new SharedPreferencesSupplication().save(SingletonClass.keyPlayAll, true);
				new SharedPreferencesSupplication().save(SingletonClass.keySelVerseFromList, 0);
				((MainActivity) context).loadSingleDuaFragment();
			}
		});
		
		
		
	lvDuaas.setOnItemClickListener(new OnItemClickListener() {

		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			// TODO Auto-generated method stub
			new SharedPreferencesSupplication().save(SingletonClass.keySelVerseFromList, position);
			((MainActivity) context).loadSingleDuaFragment();
			
		}
	});
		
		boolean keyIsFavSel = new SharedPreferencesSupplication().read(SingletonClass.keyIsFavSelected, false);	
		
if (keyIsFavSel) {
			ll_back_duas_list_header.setBackgroundResource(R.color.label_bg);
			ll_back_duas_list_header_title.setText(getActivity().getResources().getString(R.string.favorite_duas));


			btnRemoveallDuas.setVisibility(View.VISIBLE);
			btnRemoveallDuas.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View arg0) {
					DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
						
						@Override
						public void onClick(DialogInterface dialog, int which) {
							switch (which) {
								case DialogInterface.BUTTON_POSITIVE:
									for (int i = duasAdapter.getCount() - 1; i >= 0; i--) {
										removeItemFromList(i);
									}
									break;
								
								case DialogInterface.BUTTON_NEGATIVE:
									// No button clicked
									break;
							}
						}
					};
					
					AlertDialog.Builder builder = new AlertDialog.Builder(context);
					builder.setMessage("Delete All Duas from Favorites?").setPositiveButton("Yes", dialogClickListener).setNegativeButton("No", dialogClickListener).show();
					
				}
			});
			
			
			lvDuaas.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE_MODAL);
			
			lvDuaas.setMultiChoiceModeListener(new MultiChoiceModeListener() {
				
				@Override
				public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
					// TODO Auto-generated method stub
					return false;
				}
				
				@Override
				public void onDestroyActionMode(ActionMode mode) {
					// TODO Auto-generated method stub
					duasAdapter.removeSelection();

					
				}
				
				@Override
				public boolean onCreateActionMode(ActionMode mode, Menu menu) {
					// TODO Auto-generated method stub
					mode.getMenuInflater().inflate(R.menu.activity_main, menu);
					return true;
				}
				
				@Override
				public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
					// TODO Auto-generated method stub
					switch (item.getItemId()) {
		              case R.id.delete:
		                    // Calls getSelectedIds method from ListViewAdapter Class
		                    SparseBooleanArray selected = duasAdapter.getSelectedIds();
		                    // Captures all selected ids with a loop
		                    for (int i = (selected.size() - 1); i >= 0; i--) {
		                        if (selected.valueAt(i)) {
		                           String selecteditem = duasAdapter
		                                    .getItem(selected.keyAt(i));
		                            // Remove selected items following the ids
		                          //  duasAdapter.remove(selecteditem);
		                           //removeItemFromList(i);
		                           new FavoriteDuas().removeDua(selecteditem);
		                           
		                           
		                        }
		                  //();
		                      
		                    }
		                    // Close CAB
		            updatedData();
		                mode.finish();
		         
		                    return true;
		                default:
		                    return false;
					} 

				}
				
				@Override
				public void onItemCheckedStateChanged(ActionMode mode, int position,
						long id, boolean checked) {
					// TODO Auto-generated method stub
					 final int checkedCount = lvDuaas.getCheckedItemCount();
		                // Set the CAB title according to total checked items
		                mode.setTitle(checkedCount + " Selected");
		                // Calls toggleSelection method from ListViewAdapter Class
		                duasAdapter.toggleSelection(position);

				}
			});
        
		}
		else {
			btnRemoveallDuas.setVisibility(View.INVISIBLE);
			
			boolean engTrans = new SharedPreferencesSupplication().read(SingletonClass.keyEngTrans, true);
			boolean urduTrans = new SharedPreferencesSupplication().read(SingletonClass.keyUrdTrans, true);
			
			if (versesList[0].charAt(0) == 's') {
				ll_back_duas_list_header.setBackgroundResource(R.color.sleep_color);
				
				if(urduTrans &&!engTrans)
				
					{
					ll_back_duas_list_header_title.setText(getActivity().getResources().getString(R.string.sduasurd));
                        try{
                            Typeface urdu_font = ResourcesCompat.getFont(context, R.font.jameel);
                            ll_back_duas_list_header_title.setTypeface(urdu_font);
                        }
                        catch (Exception e)
                        {
                            e.printStackTrace();
                            // Prints what exception has been thrown
                            System.out.println(e);
                        }
					}
					else
					ll_back_duas_list_header_title.setText(getActivity().getResources().getString(R.string.sduas));	
				
			}
			else{
				
				ll_back_duas_list_header.setBackgroundResource(R.color.wake_up_color);
				if(urduTrans &&!engTrans)
					
				{	ll_back_duas_list_header_title.setText(getActivity().getResources().getString(R.string.wduasurd));
				    try{
                        Typeface urdu_font = ResourcesCompat.getFont(context, R.font.jameel);
                        ll_back_duas_list_header_title.setTypeface(urdu_font);
                    }
                    catch (Exception e)
                    {
                        e.printStackTrace();
                        // Prints what exception has been thrown
                        System.out.println(e);
                    }
                }
				else
					ll_back_duas_list_header_title.setText(getActivity().getResources().getString(R.string.wduas));
			}
			
			int fontsize = FontSize.getFontSize();
			ll_back_duas_list_header_title.setTextSize(TypedValue.COMPLEX_UNIT_SP, fontsize);
				
		}


		
		super.onActivityCreated(savedInstanceState);
	}
	
	@Override
	public void onResume() {
	updatedData();
		super.onResume();
		
	}
	
	
	public void removeItemFromList(int index) {
		String verses = new SharedPreferencesSupplication().read(SingletonClass.keyListOfVerses, "s1");
		String[] versesList = verses.split(",");
		String identifier = versesList[index];
		new FavoriteDuas().removeDua(identifier);
		updatedData();
	
	}

   
	
	public void updatedData() {
		boolean keyIsFavSel = new SharedPreferencesSupplication().read(SingletonClass.keyIsFavSelected, false);
		if (keyIsFavSel)
			new SharedPreferencesSupplication().save(SingletonClass.keyListOfVerses, new SharedPreferencesSupplication().read(SingletonClass.keyFavVerses, "s1"));
		String verses = new SharedPreferencesSupplication().read(SingletonClass.keyListOfVerses, "s1");
		String[] versesList = verses.split(",");
		 duas = new ArrayList<String>();
		for (int i = 0; i < versesList.length; i++) {
			if (versesList[i].length() > 0)
				duas.add(versesList[i]);
		}
		 duasAdapter.clear();
		
if (duas.size() > 0) {
			
			for (String object : duas) {
				
				duasAdapter.insert(object, duasAdapter.getCount());
				
			}
		 new FavoriteDuas().refreshLists(context, verses);
		}

			duasAdapter.notifyDataSetChanged();

	
}
}
