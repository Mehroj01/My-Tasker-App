package com.example.taskerapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.taskerapp.databinding.FragmentAddListBinding
import com.example.taskerapp.db.AppDatabase
import com.example.taskerapp.db.model.ListModel
import petrov.kristiyan.colorpicker.ColorPicker

class AddListFragment : Fragment() {
    private var _binding: FragmentAddListBinding? = null
    private val binding get() = _binding!!
    private lateinit var appDatabase: AppDatabase
    private var colorBack: Int = 0
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAddListBinding.inflate(inflater, container, false)
        appDatabase = AppDatabase.getInstance(requireContext())


        binding.apply {
            doneBtn.setOnClickListener {
                if (isValidation()) {
                    findNavController().popBackStack()
                }
            }
            button.setOnClickListener {
                val colorPicker = ColorPicker(requireActivity())
                val colors = ArrayList<String>()
                colors.add("#61DEA4")
                colors.add("#006CFF")
                colors.add("#C8C800")
                colors.add("#FFE761")
                colors.add("#4D00C8")
                colors.add("#006CFF")
                colors.add("#14C800")
                colors.add("#C500C8")
                colorPicker.setOnChooseColorListener(object : ColorPicker.OnChooseColorListener {
                    override fun onChooseColor(position: Int, color: Int) {
                        binding.imageBack.setBackgroundColor(color)
                        colorBack = color
                    }

                    override fun onCancel() {
                        colorPicker.dismissDialog()
                        Toast.makeText(requireContext(), "Cancel click", Toast.LENGTH_SHORT).show()
                    }

                }).apply {
                    setColors(colors)
                    disableDefaultButtons(false)
                    setColumns(5)
                    setRoundColorButton(true)
                    show()
                }
            }
        }
        return binding.root
    }

    private fun isValidation(): Boolean {
        val name = binding.edTv.text.toString().trim()
        if (name == "") {
            Toast.makeText(requireContext(), "Please Enter Name", Toast.LENGTH_SHORT).show()
            return false
        } else if (colorBack == 0) {
            Toast.makeText(requireContext(), "Please Enter Choose Color", Toast.LENGTH_SHORT).show()
            return false
        }
        appDatabase.listDao().addList(
            ListModel(
                name = name,
                color = colorBack
            )
        )
        return true
    }

    private fun colorList(): List<Int> {
        val list = ArrayList<Int>()
        list.add(R.color.black)
        list.add(R.color.white)
        list.add(R.color.green)
        list.add(R.color.red)
        list.add(R.color.darkBlue)
        list.add(R.color.lightBlue)
        list.add(R.color.yellow)
        list.add(R.color.bl)
        return list
    }
}