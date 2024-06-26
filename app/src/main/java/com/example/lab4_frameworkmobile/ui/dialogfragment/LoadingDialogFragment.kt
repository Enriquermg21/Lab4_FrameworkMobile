package com.example.lab4_frameworkmobile.ui.dialogfragment

import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.WindowManager
import androidx.annotation.RequiresApi
import androidx.fragment.app.DialogFragment
import com.example.lab4_frameworkmobile.databinding.FragmentDialogLoadingBinding


class LoadingDialogFragment : DialogFragment() {

    private lateinit var binding: FragmentDialogLoadingBinding

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState)
        binding =
            FragmentDialogLoadingBinding.inflate(context?.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater)
        dialog.setContentView(binding.root)
        dialog.setCancelable(false)
        dialog.setCanceledOnTouchOutside(false)
        dialog.setOnKeyListener(DialogInterface.OnKeyListener { _, keyCode, _ -> return@OnKeyListener (keyCode == KeyEvent.KEYCODE_BACK) })
        dialog.window?.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.argb(0.4F, 0F, 0F, 0F)))


        return dialog
    }

    override fun getTheme(): Int {
        return android.R.style.Theme_DeviceDefault_NoActionBar
    }
}