package com.digzdigital.reminderapp.fragment.addCourse;

import android.app.Fragment;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import com.digzdigital.reminderapp.R;
import com.digzdigital.reminderapp.activity.MainActivity;
import com.digzdigital.reminderapp.data.db.model.Course;
import com.digzdigital.reminderapp.databinding.FragmentAddCourseBinding;
import com.wdullaer.materialdatetimepicker.time.TimePickerDialog;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class AddCourseFragment extends Fragment implements View.OnClickListener, AdapterView.OnItemSelectedListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private FragmentAddCourseBinding binding;
    private Date startTime;
    private MainActivity mainActivity;

    private Course course = new Course();

    public AddCourseFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AddCourseFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AddCourseFragment newInstance(String param1, String param2) {
        AddCourseFragment fragment = new AddCourseFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        mainActivity = (MainActivity) getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_add_course, container, false);
        binding.save.setOnClickListener(this);
        binding.cancel.setOnClickListener(this);
        // binding.courseStartTime.setOnClickListener(this);
        initDaySpinner();
        initTimeSpinner();
        initDurationSpinner();
        return binding.getRoot();
    }

    private void initDaySpinner() {
        String[] ITEMS = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, ITEMS);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.daySelect.setAdapter(adapter);
        binding.daySelect.setOnItemSelectedListener(this);
    }

    private void initTimeSpinner() {
        String[] ITEMS = {"8:00 am", "9:00 am", "10:00 am", "11:00 am", "12:00 pm", "1:00 pm", "2:00 pm", "3:00 pm", "4:00 pm", "5:00 pm", "6:00 pm", "7:00 pm"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, ITEMS);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.courseStartTime.setAdapter(adapter);
        binding.courseStartTime.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                course.setTime(position + 1);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void initDurationSpinner() {
        String[] ITEMS = {"1", "2"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, ITEMS);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.duration.setAdapter(adapter);
        binding.duration.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                course.setDuration(position + 1);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.save:
                mainActivity.onSaveClicked(updateCourse());
                break;
            case R.id.cancel:
                mainActivity.onCancelClicked();
                break;
            case R.id.courseStartTime:
                setTime("Set Start Time");
                break;

        }
    }

    private void setTime(String title) {
        Calendar now = Calendar.getInstance();
        /*TimePickerDialog dialog = TimePickerDialog.newInstance(this,
                now.get(Calendar.HOUR_OF_DAY),
                now.get(Calendar.MINUTE),
                true);
        dialog.setTitle(title);*/
        // dialog.show(getFragmentManager(), "Da");
    }

    private Course updateCourse() {

        course.setCourseCode(binding.courseCode.getText().toString());
        course.setCourseTitle(binding.courseTitle.getText().toString());
        course.setVenue(binding.courseVenue.getText().toString());
        // course.setDuration(Integer.parseInt(binding.duration.getText().toString()));
        return course;
    }


    private Date createTime(int hourOfDay, int minute) {
        Calendar calendar = new GregorianCalendar();
        calendar.clear();
        calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
        calendar.set(Calendar.MINUTE, minute);
        calendar.set(Calendar.SECOND, 0);
        return calendar.getTime();
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        course.setDay(position);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */


}
