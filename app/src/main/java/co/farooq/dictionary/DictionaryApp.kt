package co.farooq.dictionary

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class DictionaryApp :  Application(){


    override fun onCreate() {
        super.onCreate()

    }
}