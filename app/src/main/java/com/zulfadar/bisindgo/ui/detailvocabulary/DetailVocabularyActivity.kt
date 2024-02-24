package com.zulfadar.bisindgo.ui.detailvocabulary

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.dicoding.projecttapenerjemahbahasaisyaratindonesia.R
import com.dicoding.projecttapenerjemahbahasaisyaratindonesia.databinding.ActivityDetailVocabularyBinding
import com.zulfadar.bisindgo.data.Vocabulary
import com.zulfadar.bisindgo.ui.dictionary.DictionaryFragment

class DetailVocabularyActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_vocabulary)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        val Vocabulary = intent.getParcelableExtra<Vocabulary>(DictionaryFragment.INTENT_PARCELABLE)

        val imageVocab = findViewById<ImageView>(R.id.word_img_iv)
        val translatedWord = findViewById<TextView>(R.id.tv_translated)

        imageVocab.setImageResource(Vocabulary?.photo!!)
        translatedWord.text = Vocabulary?.kosakata!!
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}