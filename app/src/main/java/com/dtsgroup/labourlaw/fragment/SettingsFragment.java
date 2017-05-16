package com.dtsgroup.labourlaw.fragment;

import android.os.Bundle;
import android.support.v7.preference.PreferenceFragmentCompat;
import com.dtsgroup.labourlaw.R;

public class SettingsFragment extends PreferenceFragmentCompat {

    @Override
    public void onCreatePreferences(Bundle bundle, String s) {
        addPreferencesFromResource(R.xml.setting_preference);
    }
}
