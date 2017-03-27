package com.digzdigital.reminderapp.fragment.timetable;

import android.app.Fragment;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.digzdigital.reminderapp.R;
import com.digzdigital.reminderapp.activity.MainActivity;
import com.digzdigital.reminderapp.data.db.DbHelper;
import com.digzdigital.reminderapp.data.db.model.RowObject;
import com.digzdigital.reminderapp.databinding.FragmentTimetableBinding;

import java.util.ArrayList;

import de.codecrafters.tableview.model.TableColumnDpWidthModel;
import de.codecrafters.tableview.toolkit.SimpleTableHeaderAdapter;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 * Use the {@link TimetableFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TimetableFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final String[] HEADER_DATA = {"", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private FragmentTimetableBinding binding;
    private ArrayList<RowObject> rowObjects;
    private DbHelper dbHelper;

    public TimetableFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment TimetableFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static TimetableFragment newInstance(String param1, String param2) {
        TimetableFragment fragment = new TimetableFragment();
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
        MainActivity activity = (MainActivity)getActivity();
        dbHelper = activity.getDbHelper();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_timetable, container, false);
        setupTable();
        return binding.getRoot();
    }

    private void setupTable() {
        TableColumnDpWidthModel columnModel = new TableColumnDpWidthModel(getActivity(), 8);
        binding.tableView.setColumnModel(columnModel);
        binding.tableView.setHeaderAdapter(new SimpleTableHeaderAdapter(getActivity(), HEADER_DATA));
    }

    private void loadTableRowItems() {
        rowObjects = dbHelper.getRowObjects();
        if (rowObjects != null && rowObjects.size() > 0) {
            binding.tableView.setDataAdapter(new TimetableAdapter(getActivity(), rowObjects));
        }
    }
}
