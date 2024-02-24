package com.zulfadar.bisindgo.ui.dictionary

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dicoding.projecttapenerjemahbahasaisyaratindonesia.R
import com.dicoding.projecttapenerjemahbahasaisyaratindonesia.databinding.FragmentProfileBinding
import com.zulfadar.bisindgo.data.Vocabulary
import com.zulfadar.bisindgo.ui.adapter.ListVocabularyAdapter
import com.zulfadar.bisindgo.ui.detailvocabulary.DetailVocabularyActivity
import com.zulfadar.bisindgo.ui.profile.ProfileViewModel

class DictionaryFragment : Fragment() {
    companion object {
        val INTENT_PARCELABLE = "OBJECT_INTENT"
    }

    private lateinit var adapter: ListVocabularyAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var vocabArrayList: ArrayList<Vocabulary>

    lateinit var photo: Array<Int>
    lateinit var kosakata: Array<String>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_dictionary, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        dataInitialize()
        val layoutManager = LinearLayoutManager(context)
        recyclerView = view.findViewById(R.id.rv_dictionary)
        recyclerView.layoutManager = layoutManager
        recyclerView.setHasFixedSize(true)
        recyclerView.adapter = ListVocabularyAdapter(vocabArrayList) {
            val intent = Intent(context, DetailVocabularyActivity::class.java)
            intent.putExtra(INTENT_PARCELABLE, it)
            startActivity(intent)
        }
    }

    private fun dataInitialize() {
        vocabArrayList = arrayListOf<Vocabulary>()

        photo = arrayOf(
            R.drawable.a,
            R.drawable.b,
            R.drawable.c,
            R.drawable.d,
            R.drawable.e,
            R.drawable.f,
            R.drawable.g,
            R.drawable.h,
            R.drawable.i,
            R.drawable.j,
            R.drawable.k,
            R.drawable.l,
            R.drawable.m,
            R.drawable.n,
            R.drawable.o,
            R.drawable.p,
            R.drawable.q,
            R.drawable.r,
            R.drawable.s,
            R.drawable.t,
            R.drawable.u,
            R.drawable.v,
            R.drawable.w,
            R.drawable.x,
            R.drawable.y,
            R.drawable.z,
        )
        kosakata = arrayOf(
            getString(R.string.a),
            getString(R.string.b),
            getString(R.string.c),
            getString(R.string.d),
            getString(R.string.e),
            getString(R.string.f),
            getString(R.string.g),
            getString(R.string.h),
            getString(R.string.i),
            getString(R.string.j),
            getString(R.string.k),
            getString(R.string.l),
            getString(R.string.m),
            getString(R.string.n),
            getString(R.string.o),
            getString(R.string.p),
            getString(R.string.q),
            getString(R.string.r),
            getString(R.string.s),
            getString(R.string.t),
            getString(R.string.u),
            getString(R.string.v),
            getString(R.string.w),
            getString(R.string.x),
            getString(R.string.y),
            getString(R.string.z),
        )

        for (i in photo.indices) {

            val vocab = Vocabulary(kosakata[i], photo[i])
            vocabArrayList.add(vocab)
        }
    }
}