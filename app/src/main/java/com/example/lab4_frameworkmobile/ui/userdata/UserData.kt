package com.example.lab4_frameworkmobile.ui.userdata

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.SeekBar
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.lab4_frameworkmobile.R
import com.example.lab4_frameworkmobile.data.database.entities.UserEntity
import com.example.lab4_frameworkmobile.databinding.FragmentUserDataBinding
import com.example.lab4_frameworkmobile.ui.base.BaseFragment
import com.example.lab4_frameworkmobile.ui.extensions.TAG
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UserData : BaseFragment<FragmentUserDataBinding>() {
    private val userDataViewModel: UserDataViewModel by viewModels()
    private var idUser: Int = 0
    private var redValue = 0
    private var greenValue = 0
    private var blueValue = 0

    override fun inflateBinding() {
        binding = FragmentUserDataBinding.inflate(layoutInflater)
    }

    override fun createViewAfterInflateBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) {
        userData()
        setColor()
        configEditText()
        configMapNavController()
        binding?.btnUpdate?.setOnClickListener {
            updateUser()
            configNavController()
        }
    }

    private fun configNavController() {
        findNavController().navigate(
            UserDataDirections.actionUserDataToContactsFragment()
        )
    }

    private fun configMapNavController() {
        binding?.btnMapFavoriteCity?.setOnClickListener {
            findNavController().navigate(
                UserDataDirections.actionUserDataToMapFragment()
            )
        }
    }


    private fun userData() {
        val args: UserDataArgs by navArgs()
        val userName = args.userName
        Log.d(TAG, "userData called with userName: $userName")
        userDataViewModel.loadUserByName(userName)
        userDataViewModel.userFull.observe(viewLifecycleOwner) { user ->
            if (user != null) {
                idUser = user.id
                Log.d(TAG, "User found: $user")
                binding?.etName?.setText(user.name)
                binding?.etBirthDate?.setText(user.dateOfBirth)
                binding?.colorImageView?.setBackgroundColor(Color.parseColor(user.color))
                binding?.etFavoriteCity?.setText(user.favoriteCity)
                binding?.etFavoriteNumber?.setText(user.favoriteNumber)
                binding?.etLocation?.setText(user.currentLocation)
                getColor(user.color)
            } else {
                Log.e(TAG, "User not found for name: $userName")
            }
        }

    }

    private fun updateUser() {
        Log.d(TAG, "updateUser called")
        val name = binding?.etName?.text.toString()
        val dateOfBirth = binding?.etBirthDate?.text.toString()
        val color = getColorFromImageView()
        val favoriteCity = binding?.etFavoriteCity?.text.toString()
        val favoriteNumber = binding?.etFavoriteNumber?.text.toString()
        val currentLocation = binding?.etLocation?.text.toString()
        val updatedUser = UserEntity(
            id = idUser,
            name = name,
            dateOfBirth = dateOfBirth,
            color = color,
            favoriteCity = favoriteCity,
            favoriteNumber = favoriteNumber,
            currentLocation = currentLocation
        )

        Log.d(TAG, "Updated user details: $updatedUser")


        userDataViewModel.updateUser(updatedUser)
    }

    private fun getColor(color: String) {
        try {
            val userColorInt = Color.parseColor(color)
            redValue = Color.red(userColorInt)
            greenValue = Color.green(userColorInt)
            blueValue = Color.blue(userColorInt)

            Log.d("getColor", "Red: $redValue, Green: $greenValue, Blue: $blueValue")

            binding?.seekBarR?.progress = redValue
            binding?.seekBarG?.progress = greenValue
            binding?.seekBarB?.progress = blueValue
        } catch (e: Exception) {
            Log.e("getColor", "Error getting color from ImageView background", e)
        }
    }

    private fun setColor() {
        binding?.seekBarR?.setOnSeekBarChangeListener(mChangeListener)
        binding?.seekBarG?.setOnSeekBarChangeListener(mChangeListener)
        binding?.seekBarB?.setOnSeekBarChangeListener(mChangeListener)
    }

    private val mChangeListener = object : SeekBar.OnSeekBarChangeListener {
        override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
            when (seekBar.id) {
                R.id.seekBarR -> redValue = progress
                R.id.seekBarG -> greenValue = progress
                R.id.seekBarB -> blueValue = progress
            }
            val color = Color.rgb(redValue, greenValue, blueValue)
            binding?.colorImageView?.setBackgroundColor(color)
        }

        override fun onStartTrackingTouch(seekBar: SeekBar) {
        }

        override fun onStopTrackingTouch(seekBar: SeekBar) {

        }
    }

    private fun getColorFromImageView(): String {
        val colorDrawable = binding?.colorImageView?.background as ColorDrawable
        val colorInt = colorDrawable.color
        return String.format("#%06X", 0xFFFFFF and colorInt)
    }

    private fun configEditText() {
        binding?.etName?.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                binding?.etName?.clearFocus()
                true
            } else {
                false
            }
        }
        binding?.etFavoriteCity?.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                binding?.etFavoriteCity?.clearFocus()
                true
            } else {
                false
            }
        }
    }


    override fun observeViewModel() = Unit

    override fun viewCreatedAfterSetupObserverViewModel(view: View, savedInstanceState: Bundle?) =
        Unit

    override fun configureToolbarAndConfigScreenSections() {
        fragmentLayoutWithToolbar()
        showToolbar(title = "Edit ", showBack = true)
    }
}