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
        val augmentedImageDb = AugmentedImageDatabase(session)
        val assetManager = context!!.assets
        listOf(
            "Vangelis - Blade Runner",
            "Bon Iver - Bon Iver",
            "Animal Collective - Merriweather Post Pavillion",
            "Chris Christodoulou (2) \u200E– Risk Of Rain 2",
            "Tame Impala - Lonerism",
            "Tame Impala - Slow Rush",
            "Mac Miller - Circles",
            "Godspeed You Black Emperor! - F♯ A♯ ∞",
            "Valve Studio Orchestra \u200E– Fight Songs The Music Of Team Fortress 2"
        ).forEach {
            val inputStream = assetManager.open("$it.jpg")
            val augmentedImageBitmap =
                BitmapFactory.decodeStream(inputStream)
            augmentedImageDb.addImage(it, augmentedImageBitmap)
        }
        config.augmentedImageDatabase = augmentedImageDb
    }

}