package com.alhuda.duasapp.sonayjagnaykayazkaar.adapters;

import java.util.List;

import com.alhuda.duasapp.sonayjagnaykayazkaar.R;
import com.alhuda.duasapp.sonayjagnaykayazkaar.general.FontSize;
import com.alhuda.duasapp.sonayjagnaykayazkaar.utility.SharedPreferencesSupplication;
import com.alhuda.duasapp.sonayjagnaykayazkaar.utility.SingletonClass;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v4.content.res.ResourcesCompat;
import android.util.SparseBooleanArray;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


public class DuasListAdapter extends ArrayAdapter<String> {
	
	// class variables
	private final Context context;// to save context
	private final List<String> duas;// to save list of stores
	LayoutInflater inflater;// so save layout inflater for the view
	private SparseBooleanArray mSelectedItemsIds;

	public DuasListAdapter(Context ctx, List<String> duasList) {
		
		super(ctx, R.layout.adapter_list_duas, duasList);
		mSelectedItemsIds = new SparseBooleanArray();
		context = ctx;// save context
		duas = duasList;// save list of stores
		inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);// save inflater layout
	}
	
	/**
	 * @description to show view of every item in the stores list
	 * @parent ArrayAdapter
	 * @param position int - index of store list item
	 * @param convertView View - view to be displayed in store list individual item
	 * @param parent ViewGroup - view of parent layout just for reference
	 * @return View
	 */
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		
		
	
		
		ViewHolder holder =null;
		// if view is not loaded
				if (!(convertView instanceof View)) {
					holder = new ViewHolder();
					
					convertView = inflater.inflate(R.layout.adapter_list_duas, parent, false);
					// load the view from holder class
					holder.background = (LinearLayout) convertView.findViewById(R.id.ll_duas_list);
					holder.iv_duatype = (ImageView) convertView.findViewById(R.id.iv_duatype);
					holder.tv_dua_arabic = (TextView) convertView.findViewById(R.id.tv_dua_arabic);
					holder.tv_dua_ref= (TextView) convertView.findViewById(R.id.tv_dua_ref);
					holder.tv_dua_no = (TextView) convertView.findViewById(R.id.dua_no);
					//holder = new ViewHolder(convertView);
					// set the tag for future use
					convertView.setTag(holder);

		}
		// if view is loaded
		else{
			// get view from tag
			holder = (ViewHolder) convertView.getTag();
		}
		holder.duaIndex = position;
		
	//	boolean keyIsFavSel = new SharedPreferencesSupplication().read(SingletonClass.keyIsFavSelected, false);

		boolean engTrans = new SharedPreferencesSupplication().read(SingletonClass.keyEngTrans, true);
		boolean urdTrans = new SharedPreferencesSupplication().read(SingletonClass.keyUrdTrans, true);
		
		if (duas.get(holder.duaIndex).charAt(0) == 's'){
			holder.iv_duatype.setBackgroundResource(R.drawable.night_list_icon);
		}
		else{
			holder.iv_duatype.setBackgroundResource(R.drawable.day_list_icon);
		}
		
				if((SingletonClass.duasEngTit.get(holder.duaIndex).equals("NONE")))
				{
					holder.tv_dua_arabic.setText(SingletonClass.duasAra.get(holder.duaIndex));

				}
				
				else{
							if(urdTrans && !engTrans)
							{	holder.tv_dua_arabic.setText(SingletonClass.duasUrdTit.get(holder.duaIndex));

							
							}
							
							else
							{
								holder.tv_dua_arabic.setText(SingletonClass.duasEngTit.get(holder.duaIndex));

							}
				}
				
				
		
		holder.tv_dua_ref.setText(SingletonClass.duasRef.get(holder.duaIndex));
		try {

			Typeface arabic_font = ResourcesCompat.getFont(context, R.font.arabic);
			holder.tv_dua_arabic.setTypeface(arabic_font);
            Typeface urdu_font = ResourcesCompat.getFont(context, R.font.jameel);
            holder.tv_dua_ref.setTypeface(urdu_font);
		}

		catch (Exception e)
		{
			e.printStackTrace();

			// Prints what exception has been thrown
			System.out.println(e);
		}
		
		int fontsize = FontSize.getFontSize();
		holder.tv_dua_arabic.setTextSize(TypedValue.COMPLEX_UNIT_SP, fontsize + 4);
		holder.tv_dua_ref.setTextSize(TypedValue.COMPLEX_UNIT_SP, (float) (fontsize * 0.75));

		

			return convertView;
		}
		

		
	
	/**
	 * @author OMS-20130502-0900
	 * @description class to hold the view of each individual store list item
	 * @modified
	 */
	static class ViewHolder {
		
		 int duaIndex;
		LinearLayout background;// background to display color for read and unread messages
		ImageView iv_duatype;
		TextView tv_dua_arabic;// title of message
		TextView tv_dua_ref;// message by and message created on
		TextView tv_dua_no;
		
		/**
		 * @description constructor to save context and list of stores views
		 * @param v to refer the view
		 * @return
		 */
		
	}


	public void remove(List<String> object){
		
		duas.remove(object);
		notifyDataSetChanged();
	}
	

	public List<String> getDuas(){
		
		return duas;
		}

	public void toggleSelection(int position) {
		selectView(position, !mSelectedItemsIds.get(position));
	}

	public void removeSelection() {
		mSelectedItemsIds = new SparseBooleanArray();
		notifyDataSetChanged();
	}

	public void selectView(int position, boolean value) {
		if (value)
			mSelectedItemsIds.put(position, value);
		else
			mSelectedItemsIds.delete(position);
		notifyDataSetChanged();
	}

	public int getSelectedCount() {
		return mSelectedItemsIds.size();
	}

	public SparseBooleanArray getSelectedIds() {
		return mSelectedItemsIds;
	}

	
}
