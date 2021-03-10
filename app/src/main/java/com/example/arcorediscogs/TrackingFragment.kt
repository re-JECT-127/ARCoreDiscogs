package com.example.arcorediscogs

import android.graphics.BitmapFactory
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.ar.core.AugmentedImageDatabase
import com.google.ar.core.Config
import com.google.ar.core.Session
import com.google.ar.sceneform.ux.ArFragment

class TrackingFragment : ArFragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container:
        ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val view = super.onCreateView(inflater, container, savedInstanceState)
        // Disable plane renderer and turn off planeDiscoveryController
        planeDiscoveryController.hide()
        planeDiscoveryController.setInstructionView(null)
        arSceneView.planeRenderer.isEnabled = false
        return view
    }

    override fun getSessionConfiguration(session: Session?): Config {
        val config = super.getSessionConfiguration(session)
        // Create image database and set it as a part of session configuration
        setupAugmentedImageDatabase(config, session)
        config.focusMode = Config.FocusMode.AUTO
        return config
    }

    private fun setupAugmentedImageDatabase(
        config: Config, session:
        Session?
    ) {
        //Our current image database for the records we are supporting, this is the part of the code that needs the most work to get a well functioning app.
        val augmentedImageDb = AugmentedImageDatabase(session)
        val assetManager = context!!.assets
        listOf(
            "Vangelis - Blade Runner",
            "Bon Iver - Bon Iver",
            "Tame Impala - Lonerism",
            "Tame Impala - Slow Rush",
            "Mac Miller - Circles"
        ).forEach {
            val inputStream = assetManager.open("$it.jpg")
            val augmentedImageBitmap =
                BitmapFactory.decodeStream(inputStream)
            augmentedImageDb.addImage(it, augmentedImageBitmap)
        }
        config.augmentedImageDatabase = augmentedImageDb
    }

}