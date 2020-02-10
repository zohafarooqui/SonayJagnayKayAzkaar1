package com.alhuda.duasapp.sonayjagnaykayazkaar.general;

import com.alhuda.duasapp.sonayjagnaykayazkaar.utility.SharedPreferencesSupplication;
import com.alhuda.duasapp.sonayjagnaykayazkaar.utility.SingletonClass;

public class FontSize {
	
	public static int getFontSize() {
		int defaultFontSize = (SingletonClass.fontSizeMin + SingletonClass.fontSizeMax) / 2;
		int textSize = new SharedPreferencesSupplication().read(SingletonClass.keyFontSize, defaultFontSize);
		if (textSize < SingletonClass.fontSizeMin) {
			new SharedPreferencesSupplication().save(SingletonClass.keyFontSize, SingletonClass.fontSizeMin);
			return SingletonClass.fontSizeMin;
		}
		else if (textSize > SingletonClass.fontSizeMax) {
			new SharedPreferencesSupplication().save(SingletonClass.keyFontSize, SingletonClass.fontSizeMax);
			return SingletonClass.fontSizeMax;
		}
		return textSize;
	}
	
	public static int decreaseFontSize() {
		int fontSize = getFontSize();
		fontSize--;
		new SharedPreferencesSupplication().save(SingletonClass.keyFontSize, fontSize);
		return (getFontSize());
	}
	
	public static int increaseFontSize() {
		int fontSize = getFontSize();
		fontSize++;
		new SharedPreferencesSupplication().save(SingletonClass.keyFontSize, fontSize);
		return (getFontSize());
	}
}
