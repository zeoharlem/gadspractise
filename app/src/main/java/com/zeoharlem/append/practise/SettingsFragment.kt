package com.zeoharlem.append.practise

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.NavHostFragment
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import androidx.preference.PreferenceManager


class SettingsFragment : PreferenceFragmentCompat() {

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.settings, rootKey)
        val accSettingPref  = findPreference<Preference>(getString(R.string.key_account_settings))
        accSettingPref?.setOnPreferenceClickListener {
            val navHostFragment = requireActivity().supportFragmentManager.findFragmentById(R.id.nav_host_frag) as NavHostFragment
            val navController   = navHostFragment.navController
            val action          = SettingsFragmentDirections.actionSettingsToAccSettings()
            navController.navigate(action)
            true
        }

        val sharedByPref    = PreferenceManager.getDefaultSharedPreferences(requireContext())
        val autoReplyTime   = sharedByPref.getString(getString(R.string.key_auto_reply_time), "")
        val statusPref      = findPreference<Preference>(getString(R.string.key_status))
        statusPref?.setOnPreferenceChangeListener { preference, newValue ->
            val newStatus   = newValue as String
            Toast.makeText(requireContext(), "this is my $newStatus", Toast.LENGTH_SHORT).show()
            true
        }
    }

}