package com.example.taskerapp.adapters

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.andrefrsousa.superbottomsheet.SuperBottomSheetFragment
import com.example.taskerapp.R
import com.example.taskerapp.databinding.FragmentBottomBinding
import com.example.taskerapp.db.AppDatabase
import com.example.taskerapp.db.model.ListModel
import com.example.taskerapp.db.model.TaskModel
import kotlin.math.roundToInt


private const val ARG_PARAM1 = "box"
private const val ARG_PARAM2 = "param2"

class BottomFragment : SuperBottomSheetFragment() {
    private var _binding: FragmentBottomBinding? = null
    private val binding get() = _binding!!
    private var param1: ListModel? = null
    private var param2: String? = null
    private lateinit var appDatabase: AppDatabase
    private lateinit var firstTaskAdapter: FirstTaskAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getSerializable(ARG_PARAM1) as ListModel?
            param2 = it.getString(ARG_PARAM2)
        }
    }

    @SuppressLint("MissingSuperCall")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        _binding = FragmentBottomBinding.inflate(inflater, container, false)
        appDatabase = AppDatabase.getInstance(requireContext())

        firstTaskAdapter = FirstTaskAdapter(
            appDatabase.serviceDao().getAllListName(param1!!.name),
            requireContext()
        )
        binding.apply {
            taskCountTv.text = "${appDatabase.serviceDao().getAllListName(param1!!.name).size} Task"
            titleTv.text = param1?.name
            layout.setBackgroundColor(param1!!.color)
            taskRv.adapter = firstTaskAdapter
            addBtn.setOnClickListener {
                val bundle = Bundle()
                bundle.putSerializable("listModel", param1)
                findNavController().navigate(R.id.addTaskFragment, bundle)
            }

        }

        return binding.root
    }

    override fun getCornerRadius() =
        requireContext().resources.getDimension(R.dimen.demo_sheet_rounded_corner)

    override fun getStatusBarColor() = Color.RED

    override fun getPeekHeight(): Int {
        return requireContext().resources.getDimension(R.dimen.super_bottom_sheet_peek_height)
            .roundToInt()
    }


    companion object {
        @JvmStatic
        fun newInstance(param1: ListModel) =
            BottomFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(ARG_PARAM1, param1)
                }
            }
    }
}