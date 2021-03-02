package com.example.arcorediscogs

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.NonNull
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.ar.core.AugmentedImage
import com.google.ar.core.TrackingState
import com.google.ar.sceneform.AnchorNode
import com.google.ar.sceneform.rendering.ViewRenderable
import com.google.ar.sceneform.ux.ArFragment
import com.google.ar.sceneform.ux.TransformableNode


class ImageTrackingFragment : Fragment(R.layout.fragment_image_tracking) {
    private lateinit var arFrag: ArFragment
    private var viewRenderable: ViewRenderable? = null
    private var master = ""

    lateinit var viewModel: MainViewModel
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("OVC", "onViewCreated: done")
        arFrag = childFragmentManager.findFragmentById(R.id.fragArImg) as ArFragment
        Log.d("OVC","Test $arFrag")
        val renderableFuture = ViewRenderable.builder()
            .setView(requireContext(), R.layout.view_renderable)
            .build()
        renderableFuture.thenAccept {
            viewRenderable = it
            Log.d("OVC", "viewRenderable")
        }
        renderableFuture.exceptionally {
            Log.d("OVC", "disaster")
            null
        }
        arFrag.arSceneView.scene.addOnUpdateListener { frameUpdate() }

        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        viewModel.totalHits.observe(this, Observer {
            Log.d("FYI", it.results[0].toString())
            Log.d("FYI", viewModel.totalHits.toString())
            master = viewModel.totalHits.toString()
            viewModel.masterRelease(id = master.toInt())
            // viewModel.hitcountquery(name = it.artist.toString())
        })
        fun String.toInt(): Int{
            return master.toInt()
        }
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        viewModel.tracklist.observe(this, Observer {

            Log.d("FYI", viewModel.tracklist.toString())
            // viewModel.hitcountquery(name = it.artist.toString())
        })

    }




    private fun frameUpdate() {
        val fitToScanImg = getView()?.findViewById<ImageView>(R.id.fitToScanImg)
        Log.d("OVC", "frameUpdate")
        val arFrame = arFrag.arSceneView.arFrame
        if (arFrame != null) {
            if (arFrame.camera.trackingState != TrackingState.TRACKING) return
        }


        val updatedAugmentedImages = arFrame?.getUpdatedTrackables(AugmentedImage::class.java)
        if (updatedAugmentedImages != null) {
            updatedAugmentedImages.forEach {
                Log.d("OVC", "frameUpdate images $it")
                when (it.trackingState) {
                    null -> {Log.d("FYI", "meneekö tähän")
                        return@forEach}



                    TrackingState.PAUSED -> {
                        Log.d("OVC", "paused")
                        // Image initially detected, but not enough data available to estimate its location in 3D space.
                        // Do not use the image's pose and size estimates until the image's tracking state is tracking
                        val text = "${R.string.detected_img_need_more_info} ${it.name}"
                        Toast.makeText(context, text, Toast.LENGTH_SHORT).show()
                    }
                    TrackingState.STOPPED -> {
                        Log.d("OVC", "stopped")
                        val text = "${R.string.track_stop} ${it.name}"
                        Toast.makeText(requireContext(), text, Toast.LENGTH_SHORT).show()
                    }
                    TrackingState.TRACKING -> {
                        Log.d("OVC", "halooooooooooooooooooo")
                        val anchors = it.anchors
                        if (anchors.isEmpty()) {
                            if (fitToScanImg != null) {
                                fitToScanImg.visibility = View.GONE
                            }
                            Log.d("OVC", "${it.name}")
                            // Create anchor and anchor node in the center of the image.
                            val pose = it.centerPose
                            val anchor = it.createAnchor(pose)
                            val anchorNode = AnchorNode(anchor)
                            //Attach anchor node in the scene
                            Log.d("OVC", "1")

                            anchorNode.setParent(arFrag.arSceneView.scene)
                            // Create a node as a child node of anchor node, and define node's renderable according to augmented image
                            val imgNode = TransformableNode(arFrag.transformationSystem)
                            imgNode.setParent(anchorNode)
                            viewRenderable?.view?.findViewById<TextView>(R.id.txtImgTrack)?.text =
                                it.name
                            Log.d("OVC", "2")


                            viewModel.hitcountquery("${it.name}")
                            Log.d("OVC", "toimiigö ${it.name}")
                            imgNode.renderable = viewRenderable

                        }

                    }
                    else -> {Log.d("OVC", "toimiiko")}
                }
            }
        }
    }
}
