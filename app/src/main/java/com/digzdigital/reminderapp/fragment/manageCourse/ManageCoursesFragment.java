package com.digzdigital.reminderapp.fragment.manageCourse;

import android.app.Fragment;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.digzdigital.reminderapp.R;
import com.digzdigital.reminderapp.data.db.DbHelper;
import com.digzdigital.reminderapp.data.db.model.Course;
import com.digzdigital.reminderapp.eventbus.EventType;
import com.digzdigital.reminderapp.eventbus.FirebaseEvent;
import com.digzdigital.reminderapp.fragment.reminder.ReminderListAdapter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.zakariya.stickyheaders.StickyHeaderLayoutManager;

import java.util.ArrayList;

import javax.inject.Inject;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ManageCoursesFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ManageCoursesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ManageCoursesFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private ArrayList<Course> courses;
    @Inject
    public DbHelper dbHelper;
    private RecyclerView courseRV;

    private OnFragmentInteractionListener mListener;

    public ManageCoursesFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ManageCoursesFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static Fragment newInstance(String param1, String param2) {
        ManageCoursesFragment fragment = new ManageCoursesFragment();
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
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_manage_courses, container, false);
        courseRV = (RecyclerView)view.findViewById(R.id.courseRv);
        courses = dbHelper.getAllCourses();
        dbHelper.queryForCourses();
        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
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
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onCourseClicked(Course course);
    }

    protected void doRest() {
        courseRV.setLayoutManager(new StickyHeaderLayoutManager());
        if (courses != null) {
            if (courses.size() > 0) {
                ManageCourseListAdapter adapter = new ManageCourseListAdapter();
                adapter.setCourses(courses);
                courseRV.setAdapter(adapter);
            }
        }
    }

    @Override
    public void onStart(){
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop(){
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onFirebaseEvent(FirebaseEvent event){
        if (event.type == EventType.COURSES){
            courses = dbHelper.getAllCourses();
            doRest();
        }
    }
}
