/*
 * Copyright (C) 2017 The XPerience Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package mx.xperience.rainbowunicorn.fragments;

import java.util.ArrayList;

import android.app.AlertDialog;
import android.content.Context;
import android.content.ContentResolver;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.os.Bundle;
import android.os.UserHandle;
import android.os.Handler;
import androidx.preference.ListPreference;
import androidx.preference.SwitchPreference;
import androidx.preference.Preference;
import androidx.preference.PreferenceCategory;
import androidx.preference.PreferenceScreen;
import androidx.preference.Preference.OnPreferenceChangeListener;
import android.provider.Settings;

import com.android.settings.SettingsPreferenceFragment;
import com.android.internal.logging.MetricsLogger;
import com.android.internal.logging.nano.MetricsProto;
import com.android.settings.R;

import com.android.internal.util.xperience.XPerienceUtils;

import mx.xperience.rainbowunicorn.colorpicker.ColorPickerPreference;

public class NotificationSettings extends SettingsPreferenceFragment implements OnPreferenceChangeListener {

    private static final String FLASHLIGHT_ON_CALL = "flashlight_on_call";
    private static final String PULSE_AMBIENT_LIGHT_COLOR = "pulse_ambient_light_color";

    private ListPreference mFlashlightOnCall;
    private ColorPickerPreference mEdgeLightColorPreference;

    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        addPreferencesFromResource(R.xml.xperience_notifications);
        PreferenceScreen prefScreen = getPreferenceScreen();

/*       mFlashlightOnCall = (ListPreference) findPreference(FLASHLIGHT_ON_CALL);
        Preference FlashOnCall = findPreference("flashlight_on_call");
        int flashlightValue = Settings.System.getInt(getContentResolver(),
                Settings.System.FLASHLIGHT_ON_CALL, 1);
        mFlashlightOnCall.setValue(String.valueOf(flashlightValue));
        mFlashlightOnCall.setSummary(mFlashlightOnCall.getEntry());
        mFlashlightOnCall.setOnPreferenceChangeListener(this);

         if (!XPerienceUtils.deviceHasFlashlight(getActivity())) {
            prefScreen.removePreference(FlashOnCall);
        }*/

        mEdgeLightColorPreference = (ColorPickerPreference) findPreference(PULSE_AMBIENT_LIGHT_COLOR);
        int edgeLightColor = Settings.System.getInt(getContentResolver(),
                Settings.System.PULSE_AMBIENT_LIGHT_COLOR, 0xFF3980FF);
        mEdgeLightColorPreference.setNewPreviewColor(edgeLightColor);
        mEdgeLightColorPreference.setAlphaSliderEnabled(false);
        String edgeLightColorHex = String.format("#%08x", (0xFF3980FF & edgeLightColor));
        if (edgeLightColorHex.equals("#ff3980ff")) {
            mEdgeLightColorPreference.setSummary(R.string.default_string);
        } else {
            mEdgeLightColorPreference.setSummary(edgeLightColorHex);
        }
        mEdgeLightColorPreference.setOnPreferenceChangeListener(this);
    }

     public boolean onPreferenceChange(Preference preference, Object newValue) {
         ContentResolver resolver = getActivity().getContentResolver();

           /*  if (preference == mFlashlightOnCall) {
               int flashlightValue = Integer.parseInt(((String) newValue).toString());
               Settings.System.putInt(resolver,
                     Settings.System.FLASHLIGHT_ON_CALL, flashlightValue);
               mFlashlightOnCall.setValue(String.valueOf(flashlightValue));
               mFlashlightOnCall.setSummary(mFlashlightOnCall.getEntry());
               return true;
            }*/
        if (preference == mEdgeLightColorPreference) {
            String hex = ColorPickerPreference.convertToARGB(
                    Integer.valueOf(String.valueOf(newValue)));
            if (hex.equals("#ff3980ff")) {
                preference.setSummary(R.string.default_string);
            } else {
                preference.setSummary(hex);
            }
            int intHex = ColorPickerPreference.convertToColorInt(hex);
            Settings.System.putInt(getContentResolver(),
                    Settings.System.PULSE_AMBIENT_LIGHT_COLOR, intHex);
            return true;
		}
        return false;
    }

    @Override
    public int getMetricsCategory() {
        return MetricsProto.MetricsEvent.RAINBOW_UNICORN;
    }
}
