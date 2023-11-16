package com.resulgenc.moviehub.ui.base_classes

import android.view.View
import androidx.annotation.LayoutRes
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.fragment.app.Fragment

abstract class BaseFragment(@LayoutRes contentLayoutId: Int) : Fragment(contentLayoutId) {

    /**
     * Hides the system UI (status bar, navigation bar) to create a more immersive user experience.
     * @param rootView The root view of the fragment layout.
     */
    protected fun hideSystemUI(rootView: View) {
        val window = activity?.window ?: return

        // Set the window to not fit system windows to allow hiding the system bars
        WindowCompat.setDecorFitsSystemWindows(window, false)

        // Use WindowInsetsControllerCompat to hide the system bars and define their behavior
        WindowInsetsControllerCompat(window, rootView).let { controller ->
            controller.hide(WindowInsetsCompat.Type.systemBars())
            controller.systemBarsBehavior = WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
        }
    }

    /**
     * Shows the system UI (status bar, navigation bar) that was previously hidden.
     * @param rootView The root view of the fragment layout.
     */
    protected fun showSystemUI(rootView: View) {
        val window = activity?.window ?: return

        // Set the window to fit system windows to display the system bars
        WindowCompat.setDecorFitsSystemWindows(window, true)

        // Use WindowInsetsControllerCompat to show the system bars
        WindowInsetsControllerCompat(window, rootView).show(WindowInsetsCompat.Type.systemBars())
    }
}