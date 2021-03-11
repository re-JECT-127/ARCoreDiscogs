package com.example.arcorediscogs

import android.app.Activity
import android.content.Intent
import android.net.Uri
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
import com.example.arcorediscogs.api.WebServiceRepository
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

    private val repository: WebServiceRepository =
        WebServiceRepository()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
        var gitButt = getView()?.findViewById<Button>(R.id.button2)
        gitButt?.setOnClickListener {
            val i = Intent(
                Intent.ACTION_VIEW,
                Uri.parse("https://github.com/re-JECT-127/ARCoreDiscogs")
            )
            startActivity(i)
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

    //BARCODE BUTTON
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var barButt = getView()?.findViewById<Button>(R.id.button3)
        barButt?.setOnClickListener {
            IntentIntegrator.forSupportFragment(this).initiateScan()
        }
    }

    //BARCODE SCANNING CODE RUNS HERE
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {


        if (resultCode == Activity.RESULT_OK) {
            val result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data)

            GlobalScope.launch {
                repository.getBarcode(result.contents)
                Log.d("FYI","${result.contents}")
            }
            Log.d("FYI", "toimiiko1 täääääää ${result.contents}")

            if (result != null) {
                if (result.contents == null) {
                    Toast.makeText(requireContext(), "Cancelled", Toast.LENGTH_LONG).show()
                } else {
                    // viewModel.hitcountquery(name = it.artist.toString())

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


}