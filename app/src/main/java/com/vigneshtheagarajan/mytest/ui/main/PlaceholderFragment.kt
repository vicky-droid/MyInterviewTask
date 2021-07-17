package com.vigneshtheagarajan.mytest.ui.main

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.vigneshtheagarajan.mytest.adapter.MainAdapter
import com.vigneshtheagarajan.mytest.databinding.FragmentMainBinding
import com.vigneshtheagarajan.mytest.model.Food
import com.vigneshtheagarajan.mytest.model.MainActivityModel
import com.vigneshtheagarajan.mytest.viewModel.MainActivityViewModel
import com.vigneshtheagarajan.mytest.viewModel.PlaceholderFragmentViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * A placeholder fragment containing a simple view.
 */
class PlaceholderFragment : Fragment() {

    private lateinit var pageViewModel: PageViewModel
    private var _binding: FragmentMainBinding? = null
    private val viewModel: PlaceholderFragmentViewModel by viewModel()

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    lateinit var data : Food

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        pageViewModel = ViewModelProvider(this).get(PageViewModel::class.java).apply {
//            setIndex(arguments?.getInt(ARG_SECTION_NUMBER) ?: 1)
        }

        arguments?.apply {
            data= this.getParcelable("data")!!
        }

    }

    private fun setRV() {
        _binding?.rvFood?.apply {
            layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
            adapter = MainAdapter(data.fnblist)

    }
        viewModel.calculatePrice()


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentMainBinding.inflate(inflater, container, false)
        val root = binding.root

//        val textView: TextView = binding.sectionLabel
//        pageViewModel.text.observe(viewLifecycleOwner, Observer {
//            textView.text = it
//        })
        setRV()

        return root
    }

    companion object {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */


        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        @JvmStatic
        fun newInstance(data: Food): PlaceholderFragment {
            return PlaceholderFragment().apply {
                arguments = Bundle().apply {
//                    putInt(ARG_SECTION_NUMBER, sectionNumber)
                    putParcelable("data", data)
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}


interface RvOnClickListener {

    fun onItmClick(parentPosition: Int, nestedPosition: Int? = null)
}