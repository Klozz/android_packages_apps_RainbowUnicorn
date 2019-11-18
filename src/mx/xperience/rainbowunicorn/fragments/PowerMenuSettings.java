/*
 * Copyright (C) 2011-2018 The XPerience Project
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

import android.app.AlertDialog;
import android.os.Bundle;
import android.content.ContentResolver;
import android.content.DialogInterface;
import android.content.Intent;
import androidx.preference.Preference;
import androidx.preference.PreferenceCategory;
import androidx.preference.PreferenceScreen;
import androidx.preference.Preference.OnPreferenceChangeListener;
import android.provider.Settings;
import android.text.Spannable;
import android.text.TextUtils;
import android.widget.EditText;

import com.android.internal.logging.nano.MetricsProto;
import com.android.settings.R;
import com.android.settings.SettingsPreferenceFragment;

public class PowerMenuSettings extends SettingsPreferenceFragment implements
        Preference.OnPreferenceChangeListener {

      @Override
      public void onCreate(Bundle icicle) {
            super.onCreate(icicle);
            addPreferencesFromResource(R.xml.xperience_settings_power);
            PreferenceScreen prefSet = getPreferenceScreen();
        }

        @Override
        public boolean onPreferenceChange(Preference preference, Object objValue) {
              return false;
          }

            @Override
            public int getMetricsCategory() {
                return MetricsProto.MetricsEvent.RAINBOW_UNICORN;
            }

        }
