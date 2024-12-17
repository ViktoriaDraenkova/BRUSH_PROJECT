package com.ru.practicum.usmeshka_groovy.ui.goals

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.mikephil.charting.components.AxisBase
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.components.YAxis
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.formatter.ValueFormatter
import com.practicum.usmeshka_groovy.R
import com.practicum.usmeshka_groovy.databinding.AnalythicsFragmentBinding
import com.ru.practicum.usmeshka_groovy.presentation.viewmodel.AnalyticsViewModel
import com.ru.practicum.usmeshka_groovy.ui.profile.NotificationsViewAdapter
import com.ru.practicum.usmeshka_groovy.util.RoundBarChartRender
import com.ru.practicum.usmeshka_groovy.util.getCurrentDate
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.Calendar
import kotlin.math.log

class AnalythicsFragment : Fragment() {
    private var _binding: AnalythicsFragmentBinding? = null
    private val binding get() = _binding!!
    private val viewModel: AnalyticsViewModel by viewModel()
    private var achievementsViewAdapter: AchievementsViewAdapter? = null


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = AnalythicsFragmentBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        achievementsViewAdapter = AchievementsViewAdapter()
        binding.achievementRecyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = achievementsViewAdapter
        }

        viewModel.getAchievementsLiveData().observe(viewLifecycleOwner) {

            Log.d("AAAAAAA", it.toString())
            achievementsViewAdapter!!.setList(it)
        }

        viewModel.getAchievements()

        viewModel.getAnalyticsLiveData().observe(viewLifecycleOwner) {
            drawAnalytics(it)
        }

        viewModel.getAnalForGraph(getCurrentDate())
    }

    private fun drawAnalytics(data: List<Pair<Long, Int>>) {
        Log.d("Data: ", data.toString())
        val entries = mutableListOf<BarEntry>()
        for ((i, item) in data.withIndex()) {
            entries.add(BarEntry(i.toFloat(), item.second.toFloat()))
        }

        val dataSet = BarDataSet(entries, "")
        dataSet.color = resources.getColor(R.color.light_grey)
        val barData = BarData(dataSet)

        val roundedCornerRenderer = RoundBarChartRender(
            binding.analythicsGraph,
            binding.analythicsGraph.animator,
            binding.analythicsGraph.viewPortHandler
        )
        roundedCornerRenderer.setRadius(24)
        binding.analythicsGraph.renderer = roundedCornerRenderer;

        binding.analythicsGraph.data = barData
        binding.analythicsGraph.legend.isEnabled = false
        binding.analythicsGraph.setMaxVisibleValueCount(2)
        binding.analythicsGraph.description.isEnabled = false
        binding.analythicsGraph.axisLeft.apply {
            setDrawGridLines(false)
            granularity = 1F
            labelCount = 2
            setDrawAxisLine(false)
            axisMinimum = 0f
        }

        binding.analythicsGraph.axisRight.isEnabled = false

        binding.analythicsGraph.xAxis.apply {
            position = XAxis.XAxisPosition.BOTTOM
            setDrawGridLines(false)
            setDrawAxisLine(false)
        }

        val labels = arrayOf("ПН", "ВТ", "СР", "ЧТ", "ПТ", "СБ", "ВС")
        binding.analythicsGraph.xAxis.valueFormatter = object : ValueFormatter() {
            override fun getAxisLabel(value: Float, axis: AxisBase?): String {
                return labels[value.toInt()]
            }
        }
        binding.analythicsGraph.invalidate()
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }
}