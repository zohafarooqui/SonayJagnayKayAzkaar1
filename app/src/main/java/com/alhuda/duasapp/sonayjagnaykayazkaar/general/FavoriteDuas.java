package com.alhuda.duasapp.sonayjagnaykayazkaar.general;

import java.util.ArrayList;

import com.alhuda.duasapp.sonayjagnaykayazkaar.R;
import com.alhuda.duasapp.sonayjagnaykayazkaar.utility.SharedPreferencesSupplication;
import com.alhuda.duasapp.sonayjagnaykayazkaar.utility.SingletonClass;

import android.content.Context;



public class FavoriteDuas {
	
	public boolean isDuaFavorite(String duaIdentifier) {
		
		String verses = new SharedPreferencesSupplication().read(SingletonClass.keyFavVerses, "");
		String[] versesList = verses.split(",");
		
		for (int i = 0; i < versesList.length; i++) {
			if (versesList[i].equals(duaIdentifier))
				return true;
		}
		return false;
	}
	
	public void addDua(String duaIdentifier) {
		String verses = new SharedPreferencesSupplication().read(SingletonClass.keyFavVerses, "");
		String[] versesList = verses.split(",");
		for (int i = 0; i < versesList.length; i++) {
			if (versesList[i].equals(duaIdentifier))
				return;
		}
		if (verses.isEmpty())
			verses = duaIdentifier;
		else
			verses = verses + "," + duaIdentifier;
		new SharedPreferencesSupplication().save(SingletonClass.keyFavVerses, verses);
	}
	
	public void removeDua(String duaIdentifier) {
		String verses = new SharedPreferencesSupplication().read(SingletonClass.keyFavVerses, "");
		String[] versesList = verses.split(",");
		int indexFound = -1;
		for (int i = 0; i < versesList.length; i++) {
			if (versesList[i].equals(duaIdentifier)) {
				indexFound = i;
				break;
			}
		}
		if (indexFound >= 0) {
			verses = "";
			for (int i = 0; i < versesList.length; i++) {
				if (indexFound != i) {
					if (verses.isEmpty())
						verses = versesList[i];
					else
						verses = verses + "," + versesList[i];
				}
			}
			new SharedPreferencesSupplication().save(SingletonClass.keyFavVerses, verses);
		}
	}
	
	public void refreshLists(Context context, String verses) {
		SingletonClass.duasAra = new ArrayList<String>();
		SingletonClass.duasEng = new ArrayList<String>();
		SingletonClass.duasUrd = new ArrayList<String>();
		SingletonClass.duasRef = new ArrayList<String>();
		SingletonClass.duasEngTit =new ArrayList<String>();
		SingletonClass.duasUrdTit = new ArrayList<String>();
		
		String[] fDuasList = verses.split(",");
		for (int i = 0; i < fDuasList.length; i++) {
			if (fDuasList[i].charAt(0) == 's') {
				String indexStr = fDuasList[i].substring(1);
				int index = Integer.parseInt(indexStr);
				
				String[] duasAra =context.getResources().getStringArray(R.array.s_duas_ara);
				String[] duasEng =context.getResources().getStringArray(R.array.s_duas_eng);
				String[] duasUrd =context.getResources().getStringArray(R.array.s_duas_urd);
				String[] duasRef =context.getResources().getStringArray(R.array.s_duas_ref);
				String[] duasEngTitles =context.getResources().getStringArray(R.array.s_duas_eng_titles);
				String[] duasUrdTitles =context.getResources().getStringArray(R.array.s_duas_urd_titles);
				
				SingletonClass.duasAra.add(duasAra[index]);
				SingletonClass.duasEng.add(duasEng[index]);
				SingletonClass.duasUrd.add(duasUrd[index]);
				SingletonClass.duasRef.add(duasRef[index]);
				SingletonClass.duasEngTit.add(duasEngTitles[index]);
				SingletonClass.duasUrdTit.add(duasUrdTitles[index]);
			}
			else if (fDuasList[i].charAt(0) == 'd') {
				String indexStr = fDuasList[i].substring(1);
				int index = Integer.parseInt(indexStr);
				
				String[] duasAra = context.getResources().getStringArray(R.array.d_duas_ara);
				String[] duasEng = context.getResources().getStringArray(R.array.d_duas_eng);
				String[] duasUrd = context.getResources().getStringArray(R.array.d_duas_urd);
				String[] duasRef = context.getResources().getStringArray(R.array.d_duas_ref);
				String[] duasEngTitles = context.getResources().getStringArray(R.array.d_duas_eng_titles);
				String[] duasUrdTitles = context.getResources().getStringArray(R.array.d_duas_urd_titles);
				
				SingletonClass.duasAra.add(duasAra[index]);
				SingletonClass.duasEng.add(duasEng[index]);
				SingletonClass.duasUrd.add(duasUrd[index]);
				SingletonClass.duasRef.add(duasRef[index]);
				SingletonClass.duasEngTit.add(duasEngTitles[index]);
				SingletonClass.duasUrdTit.add(duasUrdTitles[index]);
			}
		}
	}
	
}
