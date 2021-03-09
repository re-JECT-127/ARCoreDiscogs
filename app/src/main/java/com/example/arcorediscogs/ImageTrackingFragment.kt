package com.example.arcorediscogs

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.arcorediscogs.database.entity.Result
import com.example.arcorediscogs.database.ResultDB
import com.example.arcorediscogs.database.entity.TracklistInfo
import com.google.ar.core.AugmentedImage
import com.google.ar.core.TrackingState
import com.google.ar.sceneform.AnchorNode
import com.google.ar.sceneform.rendering.ViewRenderable
import com.google.ar.sceneform.ux.ArFragment
import com.google.ar.sceneform.ux.TransformableNode
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class ImageTrackingFragment : Fragment(R.layout.fragment_image_tracking) {
    private lateinit var arFrag: ArFragment
    private var viewRenderable: ViewRenderable? = null
    private var master = ""
    private var stopTracking = false
    private val db by lazy { ResultDB.get(requireContext()) }
    lateinit var viewModel: MainViewModel
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("OVC", "onViewCreated: done")
        arFrag = childFragmentManager.findFragmentById(R.id.fragArImg) as ArFragment
        Log.d("OVC", "Test $arFrag")
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
        val btn = getView()?.findViewById<Button>(R.id.button2)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        viewModel.totalHits.observe(viewLifecycleOwner, Observer {
            Log.d("FYI", it.results.toString())
            Log.d("FYI", viewModel.totalHits.toString())
            master = it.results[0].toString().split("=")[1].split(")")[0].split(",")[0]
            Log.d("FYI", master)

            viewModel.masterRelease(id = master.toInt())
            GlobalScope.launch {
                val id = db.resultDao().insert(
                    Result(
                        master.toInt(),
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

        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        viewModel.tracklist.observe(viewLifecycleOwner, Observer {
            Log.d("FYI", it.toString())
            var mArray = arrayOf(it.tracklist)
            var a = it.tracklist.size
            var b = 0
            Log.d("FYI", "$a")
            GlobalScope.launch {
            for (i in it.tracklist) {

                    val master_id = db.tracklistInfoDao().insert(
                        TracklistInfo(
                            master.toInt(),
                            it.tracklist[b].title,
                            it.tracklist[b].position,
                            it.tracklist[b].title,
                            it.tracklist[b].duration,
                            master + "_" + it.tracklist[b].position
                        )
                    )
                    b++
                }
                Log.d("FYI", "adding to db done" )
               /* val activity = view.context as AppCompatActivity
                activity.supportFragmentManager.beginTransaction()
                    .replace(R.id.fragment2, CardFragment(master.toLong())).addToBackStack(null)
                    .commit()*/
            }
            Log.d("FYI", viewModel.tracklist.toString())

            // viewModel.hitcountquery(name = it.artist.toString())
            stopTracking = true
        })
        fun String.toInt(): Int {
            return master.toInt()
        }

    }
    fun startCardFrag() {
        childFragmentManager.beginTransaction().apply {
            replace(R.id.fragment2, CardFragment(master.toLong()))
            commit()
        }
    }


    private fun frameUpdate() {
        val fitToScanImg = getView()?.findViewById<ImageView>(R.id.fitToScanImg)
        Log.d("OVC", "frameUpdate")
        val arFrame = arFrag.arSceneView.arFrame
        if (arFrame != null && stopTracking == false) {
            if (arFrame.camera.trackingState != TrackingState.TRACKING) return
        } else {
            startCardFrag()
        }


        val updatedAugmentedImages = arFrame?.getUpdatedTrackables(AugmentedImage::class.java)
        if (updatedAugmentedImages != null) {
            updatedAugmentedImages.forEach {
                Log.d("OVC", "frameUpdate images $it")
                when (it.trackingState) {
                    null -> {
                        Log.d("FYI", "meneekö tähän")
                        return@forEach
                    }


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
                    else -> {
                        Log.d("OVC", "toimiiko")
                    }
                }
            }
        }
    }
}
