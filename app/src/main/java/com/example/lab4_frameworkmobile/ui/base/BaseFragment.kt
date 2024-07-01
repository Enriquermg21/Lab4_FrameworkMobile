package com.example.lab4_frameworkmobile.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.Group
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.example.lab4_frameworkmobile.ui.extensions.gone
import com.example.lab4_frameworkmobile.ui.extensions.visible

abstract class BaseFragment<B : ViewBinding> : Fragment() {

    var binding: B? = null

    private lateinit var baseActivity: BaseActivity<*>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        baseActivity = activity as BaseActivity<*>
    }

    override fun onResume() {
        super.onResume()
        configureToolbarAndConfigScreenSections()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        callViewModelSaveData()
        super.onSaveInstanceState(outState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        inflateBinding()
        createViewAfterInflateBinding(inflater, container, savedInstanceState)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViewModel()
        observeViewModel()
        viewCreatedAfterSetupObserverViewModel(view, savedInstanceState)
    }

    fun hideToolbar() {
        baseActivity.hideToolbar()
    }

    fun hideKeyboard() {
        baseActivity.hideKeyboard()
    }

    fun showToolbar(
        showBack: Boolean = false,
        title: String = "",
    ) {
        baseActivity.showToolbar(
            showBack = showBack,
            title = title,
        )
    }

    fun updateShowToolbarBack(showBack: Boolean) {
        baseActivity.updateShowToolbarBack(showBack)
    }
    fun updateShowToolbarTitle(title: String) {
        baseActivity.updateShowToolbarTitle(title)
    }

    fun fragmentFullScreenLayoutWithoutToolbar() {
        baseActivity.fragmentFullScreenLayoutWithoutToolbar()
    }

    fun fragmentLayoutWithToolbar() {
        baseActivity.fragmentLayoutWithToolbar()
    }

    fun showNoResults(gResults: Group?, tvNoResult: TextView?) {
        gResults?.gone()
        tvNoResult?.visible()
    }

    fun hideNoResults(gResults: Group?, tvNoResult: TextView?) {
        gResults?.visible()
        tvNoResult?.gone()
    }

    override fun onDestroyView() {
        binding = null
        super.onDestroyView()
    }

    protected open fun setupViewModel() = Unit

    abstract fun inflateBinding()
    abstract fun createViewAfterInflateBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    )

    open fun callViewModelSaveData() = Unit
    abstract fun observeViewModel()
    abstract fun viewCreatedAfterSetupObserverViewModel(view: View, savedInstanceState: Bundle?)
    abstract fun configureToolbarAndConfigScreenSections()
}