package com.example.arcorediscogs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment



class scanSelectFrag : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val arSearchButton = getView()?.findViewById<Button>(R.id.arSearchButt)

        if (arSearchButton != null) {
            arSearchButton.setOnClickListener{
                childFragmentManager.beginTransaction().apply {
                    replace(R.id.fragment2, ImageTrackingFragment())
                    commit()
                }
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.scan_select_fragment, container, false)
    }



}

