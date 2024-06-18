package com.example.lab4_frameworkmobile.ui.base

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.updateLayoutParams
import androidx.navigation.NavController
import androidx.viewbinding.ViewBinding
import com.example.lab4_frameworkmobile.R
import com.example.lab4_frameworkmobile.ui.extensions.gone
import com.example.lab4_frameworkmobile.ui.extensions.visible
import com.vasscompany.vassuniversitybaseproject.ui.dialogfragment.loading.LoadingDialogFragment
import javax.inject.Inject

abstract class BaseActivity<B : ViewBinding> : AppCompatActivity(), View.OnClickListener {
    companion object {
        const val LOADING_DIALOG_FRAGMENT_TAG: String = "LOADING_DIALOG_FRAGMENT_TAG"
    }

    lateinit var binding: B

    private var isKeyboardVisible = false
    private var clToolbar: ConstraintLayout? = null
    private var tbToolbar: Toolbar? = null
    private var ibToolbarBack: ImageButton? = null
    private var ibToolbarMenu: ImageButton? = null
    private var tvToolbarTitle: TextView? = null
    private var ibToolbarClose: ImageButton? = null
    private var ivClose: ImageView? = null
    private var loadingDialogFragment: LoadingDialogFragment = LoadingDialogFragment()

    @Inject
    lateinit var baseActivityControlShowLoading: BaseActivityControlShowLoading

    lateinit var navController: NavController

    override fun onResume() {
        super.onResume()
        configureToolbarAndConfigScreenSections()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        callViewModelSaveData()
        super.onSaveInstanceState(outState)
    }

    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount == 1) {
            finish()
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        inflateBinding()
        setContentView(binding.root)
        findViewByIdToolbar()
        observeViewModel()
        createAfterInflateBindingSetupObserverViewModel(savedInstanceState)
        setListenersClickToolbarButtons()
    }

    private fun findViewByIdToolbar() {
        clToolbar = findViewById(R.id.clToolbar)
        tbToolbar = findViewById(R.id.tbToolbar)
        ibToolbarBack = findViewById(R.id.ibToolbarBack)
        ibToolbarMenu = findViewById(R.id.ibToolbarMenu)
        tvToolbarTitle = findViewById(R.id.tvToolbarTitle)
        ibToolbarClose = findViewById(R.id.ibToolbarClose)
    }

    private fun setListenersClickToolbarButtons() {
        ibToolbarBack?.setOnClickListener(this)
        ibToolbarMenu?.setOnClickListener(this)
        ibToolbarClose?.setOnClickListener(this)
        ivClose?.setOnClickListener((this))
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.ibToolbarBack -> clickToolbarBack()
            R.id.ibToolbarMenu -> clickToolbarMenu()
            R.id.ibToolbarClose -> clickToolbarClose()
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
        ibToolbarMenu?.gone()
        tvToolbarTitle?.gone()
        ibToolbarClose?.gone()
    }

    fun showToolbar(
        showBack: Boolean = false,
        showMenu: Boolean = false,
        title: String = "",
        showClose: Boolean = false,
    ) {
        var maxIconLeft = 0
        var maxIconRight = 0

        hideAllElementToolbar()
        tbToolbar?.visible()
        if (showBack) {
            maxIconLeft++
            ibToolbarBack?.visible()
        }
        if (showMenu) {
            maxIconLeft++
            ibToolbarMenu?.visible()
        }
        if (showClose) {
            maxIconRight++
            ibToolbarClose?.visible()
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

    fun updateShowToolbarMenu(showMenu: Boolean) {
        if (showMenu) {
            ibToolbarMenu?.visible()
        } else {
            ibToolbarMenu?.gone()
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

    fun showLoading(show: Boolean) {
        if (show) {
            if (baseActivityControlShowLoading.canShowLoading(
                    supportFragmentManager,
                    LOADING_DIALOG_FRAGMENT_TAG
                )
            ) {
                loadingDialogFragment.show(supportFragmentManager, LOADING_DIALOG_FRAGMENT_TAG)
            }
        } else {
            if (baseActivityControlShowLoading.canHideLoading(
                    supportFragmentManager,
                    LOADING_DIALOG_FRAGMENT_TAG
                )
            ) {
                loadingDialogFragment.dismiss()
            }
        }
    }

    protected open fun callViewModelSaveData() = Unit
    abstract fun inflateBinding()
    abstract fun observeViewModel()
    abstract fun createAfterInflateBindingSetupObserverViewModel(savedInstanceState: Bundle?)
    abstract fun configureToolbarAndConfigScreenSections()
}