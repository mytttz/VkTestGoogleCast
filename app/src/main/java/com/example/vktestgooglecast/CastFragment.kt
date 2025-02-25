package com.example.vktestgooglecast

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.mediarouter.app.MediaRouteButton
import com.google.android.gms.cast.framework.CastButtonFactory
import com.google.android.gms.cast.framework.CastContext
import org.koin.androidx.viewmodel.ext.android.viewModel

class CastFragment : Fragment() {
    private val viewModel: CastViewModel by viewModel()
    private val videoUrl =
        "https://videolink-test.mycdn.me/?pct=1&sig=6QNOvp0y3BE&ct=0&clientType=45&mid=193241622673&type=5"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        val mediaRouter = androidx.mediarouter.media.MediaRouter.getInstance(requireContext())
        mediaRouter.setRouterParams(
            androidx.mediarouter.media.MediaRouterParams.Builder()
                .setOutputSwitcherEnabled(true)
                .build()
        )

        val view = inflater.inflate(R.layout.fragment_cast, container, false)

        CastContext.getSharedInstance(requireContext())
        val mediaRouteButton = view.findViewById<MediaRouteButton>(R.id.media_route_button)
        CastButtonFactory.setUpMediaRouteButton(requireContext(), mediaRouteButton)


        val button = view.findViewById<Button>(R.id.cast_button)
        viewModel.initializeCast(requireContext())
        button.setOnClickListener {
            viewModel.playVideo(videoUrl)
        }

        return view
    }
}