package hu.bme.aut.pizzaapp.settings;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.EditTextPreference;
import android.preference.PreferenceFragment;

import hu.bme.aut.pizzaapp.R;


public class SettingsFragment extends PreferenceFragment implements SharedPreferences.OnSharedPreferenceChangeListener{

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        addPreferencesFromResource(R.xml.settings);

        EditTextPreference name = (EditTextPreference) findPreference("name");
        name.setSummary(name.getText());

        EditTextPreference address = (EditTextPreference) findPreference("address");
        address.setSummary(address.getText());

        EditTextPreference phone = (EditTextPreference) findPreference("phone");
        phone.setSummary(phone.getText());

        EditTextPreference email = (EditTextPreference) findPreference("email");
        email.setSummary(email.getText());
    }

    @Override
    public void onResume() {
        super.onResume();
        getPreferenceScreen().getSharedPreferences()
                .registerOnSharedPreferenceChangeListener(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        getPreferenceScreen().getSharedPreferences()
                .unregisterOnSharedPreferenceChangeListener(this);
    }


    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String s) {
        EditTextPreference pref = (EditTextPreference) findPreference(s);
        pref.setSummary(pref.getText());
    }
}
