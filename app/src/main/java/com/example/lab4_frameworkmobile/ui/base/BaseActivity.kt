package com.example.lab4_frameworkmobile.ui.base

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.updateLayoutParams
import androidx.navigation.NavController
import androidx.viewbinding.ViewBinding
import com.example.lab4_frameworkmobile.R
import com.example.lab4_frameworkmobile.ui.extensions.gone
import com.example.lab4_frameworkmobile.ui.extensions.visible

abstract class BaseActivity<B : ViewBinding> : AppCompatActivity(), View.OnClickListener {

    lateinit var binding: B

    private var isKeyboardVisible = false
    private var clToolbar: ConstraintLayout? = null
    private var tbToolbar: Toolbar? = null
    private var ibToolbarBack: ImageButton? = null
    private var tvToolbarTitle: TextView? = null
    private var ivClose: ImageView? = null


    lateinit var navController: NavController

    override fun onResume() {
        super.onResume()
        configureToolbarAndConfigScreenSections()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        callViewModelSaveData()
        super.onSaveInstanceState(outState)
    }

    @Deprecated("Deprecated in Java")
    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount == 1) {
            finish()
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        screenSplash()
        inflateBinding()
        setContentView(binding.root)
        findViewByIdToolbar()
        observeViewModel()
        createAfterInflateBindingSetupObserverViewModel(savedInstanceState)
        setListenersClickToolbarButtons()
    }

    private fun screenSplash() {
        val screenSplash = installSplashScreen()
        screenSplash.setKeepOnScreenCondition { false }
    }

    fun hideKeyboard() {
        val view = this.currentFocus
        if (view != null) {
            val imm = this.getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }

    private fun findViewByIdToolbar() {
        clToolbar = findViewById(R.id.clToolbar)
        tbToolbar = findViewById(R.id.tbToolbar)
        ibToolbarBack = findViewById(R.id.ibToolbarBack)
        tvToolbarTitle = findViewById(R.id.tvToolbarTitle)
    }

    private fun setListenersClickToolbarButtons() {
        ibToolbarBack?.setOnClickListener(this)
        ivClose?.setOnClickListener((this))
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.ibToolbarBack -> clickToolbarBack()
        }
    }

    protected open fun clickToolbarBack() {
        onBackPressed()
    }

    protected open fun clickToolbarMenu() = Unit
    protected open fun clickToolbarClose() = Unit
    fun hideToolbar() {
        tbToolbar?.gone()
    }

    private fun hideAllElementToolbar() {
        ibToolbarBack?.gone()
        tvToolbarTitle?.gone()
    }

    fun showToolbar(
        showBack: Boolean = false,
        title: String = "",
    ) {
        var maxIconLeft = 0
        var maxIconRight = 0

        hideAllElementToolbar()
        tbToolbar?.visible()
        if (showBack) {
            maxIconLeft++
            ibToolbarBack?.visible()
        }
        if (title.isNotBlank()) {
            tvToolbarTitle?.visible()
            tvToolbarTitle?.text = title
            if (maxIconLeft > maxIconRight) {
                configMarginTitle(maxIconLeft)
            } else {
                configMarginTitle(maxIconRight)
            }
        }
    }

    private fun configMarginTitle(numberIconsLeftRightTitle: Int) {
        if (tvToolbarTitle != null) {
            tvToolbarTitle!!.updateLayoutParams<ViewGroup.MarginLayoutParams> {
                when (numberIconsLeftRightTitle) {
                    0 -> {
                        marginStart =
                            resources.getDimension(R.dimen.toolbar_margin_title_zero_buttons)
                                .toInt()
                        marginEnd =
                            resources.getDimension(R.dimen.toolbar_margin_title_zero_buttons)
                                .toInt()
                    }

                    1 -> {
                        marginStart =
                            resources.getDimension(R.dimen.toolbar_margin_title_one_buttons).toInt()
                        marginEnd =
                            resources.getDimension(R.dimen.toolbar_margin_title_one_buttons).toInt()
                    }

                    2 -> {
                        marginStart =
                            resources.getDimension(R.dimen.toolbar_margin_title_two_buttons).toInt()
                        marginEnd =
                            resources.getDimension(R.dimen.toolbar_margin_title_two_buttons).toInt()
                    }

                    3 -> {
                        marginStart =
                            resources.getDimension(R.dimen.toolbar_margin_title_three_buttons)
                                .toInt()
                        marginEnd =
                            resources.getDimension(R.dimen.toolbar_margin_title_three_buttons)
                                .toInt()
                    }

                    else -> {
                        marginStart =
                            resources.getDimension(R.dimen.toolbar_margin_title_four_buttons)
                                .toInt()
                        marginEnd =
                            resources.getDimension(R.dimen.toolbar_margin_title_four_buttons)
                                .toInt()
                    }
                }
            }
        }
    }

    fun updateShowToolbarBack(showBack: Boolean) {
        if (showBack) {
            ibToolbarBack?.visible()
        } else {
            ibToolbarBack?.gone()
        }
    }

    private fun hideToolbarLayout() {
        clToolbar?.gone()
    }

    private fun showToolbarLayout() {
        clToolbar?.visible()
    }

    fun fragmentFullScreenLayoutWithoutToolbar() {
        hideToolbarLayout()
    }

    fun fragmentLayoutWithToolbar() {
        showToolbarLayout()
    }

    fun updateShowToolbarTitle(title: String) {
        if (title.isNotBlank()) {
            tvToolbarTitle?.visible()
            tvToolbarTitle?.text = title
        } else {
            tvToolbarTitle?.gone()
        }
    }

    protected open fun callViewModelSaveData() = Unit
    abstract fun inflateBinding()
    abstract fun observeViewModel()
    abstract fun createAfterInflateBindingSetupObserverViewModel(savedInstanceState: Bundle?)
    abstract fun configureToolbarAndConfigScreenSections()
}