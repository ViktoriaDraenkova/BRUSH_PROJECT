package com.ru.practicum.usmeshka_groovy.ui.profile

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.app.Dialog
import android.app.TimePickerDialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.practicum.usmeshka_groovy.R
import com.practicum.usmeshka_groovy.databinding.PersonalAccountFragmentBinding
import com.ru.practicum.usmeshka_groovy.domain.models.Notification
import com.ru.practicum.usmeshka_groovy.domain.models.RepeatInterval
import com.ru.practicum.usmeshka_groovy.presentation.viewmodel.PersonalAccountViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.Calendar

class PersonalAccountFragment : Fragment() {
    private val viewModel: PersonalAccountViewModel by viewModel()
    private var _binding: PersonalAccountFragmentBinding? = null
    private val binding get() = _binding!!
    private var notificationsViewAdapter: NotificationsViewAdapter? = null
    private lateinit var recyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        _binding = PersonalAccountFragmentBinding.inflate(layoutInflater)
        return binding.root
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = binding.notificationsRecyclerView
        notificationsViewAdapter = NotificationsViewAdapter {

        }
        binding.notificationsRecyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = notificationsViewAdapter
        }

        viewModel.getNotificationsLiveData().observe(viewLifecycleOwner) { notifications ->
            notificationsViewAdapter?.setList(notifications)
            recyclerView.adapter = notificationsViewAdapter
        }

        viewModel.getAchievementsLiveData().observe(viewLifecycleOwner) { achievements ->
            binding.days.text = achievements.countOfCleaning.toString() + " дней"
            binding.countPerDay.text = achievements.perDayYouClean.toString() + " раз в день"
            binding.achivement.text = achievements.countOfAchievements.toString() + " бупио"
        }
        viewModel.getCountAchievements()

        binding.name.text = viewModel.getNameSurname()

        binding.addNotification.setOnClickListener {
            val dialogBinding = layoutInflater.inflate(R.layout.dialog_adding_notification, null)
            val myDialog = Dialog(requireContext())
            myDialog.setContentView(dialogBinding)
            myDialog.setCancelable(true)
            myDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            myDialog.window?.setLayout(800, ViewGroup.LayoutParams.WRAP_CONTENT)
            val spinner: Spinner = dialogBinding.findViewById(R.id.notification_spinner)
            ArrayAdapter.createFromResource(
                requireContext(),
                R.array.array_for_spinner,
                android.R.layout.simple_spinner_item
            ).also { adapter ->
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                spinner.adapter = adapter
            }

            var repeatInterval = RepeatInterval.EVERY_DAY

            spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>,
                    view: View?,
                    pos: Int,
                    id: Long
                ) {
                    repeatInterval = RepeatInterval.values()[pos]
                    Log.d("repeat interval: ", repeatInterval.strValue)
                    Log.d("repeat interval values: ", enumValues<RepeatInterval>()[pos].toString())
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                    TODO("Not yet implemented")
                }

            }
            myDialog.show()

            val datetimeLiveData = MutableLiveData<Long?>(null)

            dialogBinding.findViewById<TextView>(
                R.id.new_notification_date
            ).setOnClickListener {
                showDateTimePicker(dialogBinding, datetimeLiveData)
            }

            dialogBinding.findViewById<Button>(R.id.add_notification_button)
                .setOnClickListener {
                    val name =
                        dialogBinding.findViewById<EditText>(R.id.new_notification_name).text.toString()

                    if (name.isEmpty() || datetimeLiveData.value == null) {
                        Snackbar.make(dialogBinding, "Заполните все поля!", Snackbar.LENGTH_SHORT)
                            .show()
                        return@setOnClickListener
                    }
                    myDialog.dismiss()
                    Log.d("repeat interval on save: ", repeatInterval.strValue)
                    viewModel.insertNotification(
                        Notification(
                            message = name,
                            date = datetimeLiveData.value!!,
                            repeating = repeatInterval
                        )
                    )
                    fetchNotifications()
                }
        }

        fetchNotifications()

        binding.settings.setOnClickListener {
            findNavController().navigate(R.id.action_personalAccountFragment_to_settingsFragment)
        }
    }

    private fun fetchNotifications() {
        viewModel.getNotificationList()

    }

    override fun onDestroyView() {
        super.onDestroyView()
    }

    private fun showDateTimePicker(dialogBinding: View, datetimeLiveData: MutableLiveData<Long?>) {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)
        val hour = calendar.get(Calendar.HOUR_OF_DAY)
        val minute = calendar.get(Calendar.MINUTE)

        val datePickerDialog =
            DatePickerDialog(requireContext(), { _, selectedYear, selectedMonth, selectedDay ->
                val timePickerDialog =
                    TimePickerDialog(requireContext(), { _, selectedHour, selectedMinute ->
                        dialogBinding.findViewById<TextView>(
                            R.id.new_notification_date
                        ).text = String.format(
                            "%02d/%02d/%04d %02d:%02d",
                            selectedDay,
                            selectedMonth + 1,
                            selectedYear,
                            selectedHour,
                            selectedMinute
                        )
                        calendar.set(Calendar.YEAR, selectedYear)
                        calendar.set(Calendar.MONTH, selectedMonth)
                        calendar.set(Calendar.DAY_OF_MONTH, selectedDay)
                        calendar.set(Calendar.HOUR_OF_DAY, selectedHour)
                        calendar.set(Calendar.MINUTE, selectedMinute)
                        datetimeLiveData.value = calendar.time.time
                        Log.d("selected datetime", datetimeLiveData.value.toString())
                    }, hour, minute, true)
                timePickerDialog.show()
            }, year, month, day)

        datePickerDialog.show()
    }
}