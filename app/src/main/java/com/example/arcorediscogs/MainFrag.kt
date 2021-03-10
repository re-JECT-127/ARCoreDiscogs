package com.example.arcorediscogs

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils.replace
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.arcorediscogs.database.ResultDB
import com.example.arcorediscogs.database.entity.Result
import com.google.ar.sceneform.ux.ArFragment
import com.google.zxing.integration.android.IntentIntegrator
import com.google.zxing.integration.android.IntentResult
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class mainFrag : Fragment() {
    private var param1: String? = null
    private var param2: String? = null
    lateinit var viewModel: MainViewModel
    private val db by lazy { ResultDB.get(requireContext()) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }

    }

    override fun onCreateView(

        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {
        // Inflate the layout for this fragment
        container?.removeAllViews()
        return inflater.inflate(R.layout.mainfrag, container, false)
    }

    //BARCODE SCANNING CODE RUNS HERE
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode == Activity.RESULT_OK) {
            val result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data)
            if (result != null) {
                if (result.contents == null) {
                    Toast.makeText(requireContext(), "Cancelled", Toast.LENGTH_LONG).show()
                } else {
                    viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
                    viewModel.barcodeSearch.observe(this, Observer {

                        Log.d("FYI", "toimiiko ${result.contents}")
                        viewModel.barcodeScan(result.contents)
                        GlobalScope.launch {
                            val id = db.resultDao().insert(
                                Result(
                                    it.results[0].toString().split("=")[1].split(")")[0].split(",")[0].toInt(),
                                    it.results[0].title.split("-")[0],
                                    it.results[0].title.split("-")[1],
                                    it.results[0].genre[0],
                                    it.results[0].year,
                                    it.results[0].thumb
                                )
                            )
                        }

                        // viewModel.hitcountquery(name = it.artist.toString())
                    })
                    Log.d("FYI", "toimiiko1 ${result.contents}")
                    Toast.makeText(
                        requireContext(),
                        "Scanned: " + result.contents,
                        Toast.LENGTH_LONG
                    ).show()

                }
            } else {
                super.onActivityResult(requestCode, resultCode, data);
            }
        }
    }

    //BARCODE BUTTON
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var barButt = getView()?.findViewById<Button>(R.id.button3)
        barButt?.setOnClickListener {
            IntentIntegrator.forSupportFragment(this).initiateScan()
        }

    }

}
