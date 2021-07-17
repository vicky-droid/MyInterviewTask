package com.vigneshtheagarajan.mytest

import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.library.baseAdapters.BR
import androidx.viewpager.widget.ViewPager
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetBehavior.BottomSheetCallback
import com.google.android.material.tabs.TabLayout
import com.vigneshtheagarajan.mytest.databinding.ActivityMainBinding
import com.vigneshtheagarajan.mytest.model.Fnblist
import com.vigneshtheagarajan.mytest.model.MainActivityModel
import com.vigneshtheagarajan.mytest.ui.main.SectionsPagerAdapter
import com.vigneshtheagarajan.mytest.viewModel.MainActivityViewModel
import com.vigneshtheagarajan.utils.one.RecyclerviewUtil.DatabindingAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainActivityViewModel by viewModel()
    private val rvAdapter = DatabindingAdapter<Fnblist>()

    private var sheetBehavior: BottomSheetBehavior<*>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        first()
        setBottomSheet()
    }

    private fun setBottomSheet() {
        sheetBehavior = BottomSheetBehavior.from(binding.bottom.bottomSheetLayout)


        binding.bottom.bottomSheetArrow?.setOnClickListener {
            sheetBehavior?.apply {
                if (state != BottomSheetBehavior.STATE_EXPANDED) {
                    setState(BottomSheetBehavior.STATE_EXPANDED)
                } else {
                    setState(BottomSheetBehavior.STATE_COLLAPSED)
                }
                addBottomSheetCallback(object : BottomSheetCallback() {
                    override fun onStateChanged(bottomSheet: View, newState: Int) {}
                    override fun onSlide(bottomSheet: View, slideOffset: Float) {
                        it.rotation = slideOffset * 180
                    }
                })

            }

        }


    }

    private fun setBottomSheetRv(arrayList: ArrayList<Fnblist>) {

        rvAdapter.apply {
            setLayout(R.layout.cell_bottom_sheet)
            setViewModelId(BR.BottomListViewModel)
            binding.bottom.rvBottomSheet.adapter = this
            setItems(arrayList)
        }
    }

    fun first() {
        viewModel.loadData().observe(this, { data ->
            data.apply {
                val tabList = foodList.map { it.tabName }
                createTabs(tabList.toTypedArray(), data)
                viewModel.currency=currency
                binding.bottom.price.text = "$currency 0.0"

            }
        })

        viewModel.foodLiveDataBottomSheet.observe(this, {
            setBottomSheetRv(it)
        })

        viewModel.totalPrice.observe(this, {
            binding.bottom.price.text = it
        })


    }

    private fun createTabs(tabList: Array<String>, data: MainActivityModel) {
        val sectionsPagerAdapter = SectionsPagerAdapter(this, supportFragmentManager, tabList, data)
        val viewPager: ViewPager = binding.viewPager
        viewPager.adapter = sectionsPagerAdapter
        val tabs: TabLayout = binding.tabs
        tabs.setupWithViewPager(viewPager)
        addTabDivider()
    }
    fun addTabDivider() {
        val linearLayout = binding.tabs.getChildAt(0) as LinearLayout
        linearLayout.showDividers = LinearLayout.SHOW_DIVIDER_MIDDLE
        val drawable = GradientDrawable()
        drawable.setColor(Color.GRAY)
        drawable.setSize(1, 1)
        linearLayout.dividerPadding = 20
        linearLayout.dividerDrawable = drawable
    }


}