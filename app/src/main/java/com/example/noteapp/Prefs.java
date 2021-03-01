package com.example.noteapp;

import android.content.Context;
import android.content.SharedPreferences;

    public class Prefs {

        private SharedPreferences preferences;

        public Prefs(Context context) {
            preferences = context.getSharedPreferences("settings", Context.MODE_PRIVATE);
        }

        public void saveIsShown() {
            preferences.edit().putBoolean("isShown", false);
        }

        public boolean isShown() {
            return preferences.getBoolean("isShown", false);
        }

    }


