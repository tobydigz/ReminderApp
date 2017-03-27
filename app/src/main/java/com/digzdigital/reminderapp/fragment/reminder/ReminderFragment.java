package com.digzdigital.reminderapp.fragment.reminder;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.digzdigital.reminderapp.R;
import com.digzdigital.reminderapp.activity.MainActivity;
import com.digzdigital.reminderapp.data.db.DbHelper;
import com.digzdigital.reminderapp.data.db.model.ReminderItem;
import com.digzdigital.reminderapp.eventbus.EventType;
import com.digzdigital.reminderapp.eventbus.FirebaseEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 * Use the {@link ReminderFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ReminderFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private DbHelper dbHelper;


    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private RecyclerView reminderRv;
    private ArrayList<ReminderItem> reminderItems;

    public ReminderFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ReminderFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ReminderFragment newInstance(String param1, String param2) {
        ReminderFragment fragment = new ReminderFragment();
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
        MainActivity activity = (MainActivity) getActivity();
        dbHelper = activity.getDbHelper();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_reminders, container, false);
        reminderRv = (RecyclerView) view.findViewById(R.id.reminderRv);
        dbHelper.queryForReminders();
        return view;
    }

    private void loadReminders() {
        reminderItems = dbHelper.getOnlineReminders();
        doRest();
    }


    protected void doRest() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        reminderRv.setLayoutManager(linearLayoutManager);
        if (reminderItems != null) {
            if (reminderItems.size() > 0) {
                ReminderListAdapter reminderListAdapter = new ReminderListAdapter(reminderItems);
                reminderRv.setAdapter(reminderListAdapter);

                reminderListAdapter.setOnItemClickListener(new ReminderListAdapter.MyClickListener() {
                    @Override
                    public void onItemClick(int position, View v) {
//                        Handle click whatever
                    }
                });
            }
        }

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onFirebaseEvent(FirebaseEvent event) {
        if (event.type == EventType.REMINDER) loadReminders();
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
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
