package com.digzdigital.reminderapp.fragment.manageCourse;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.amulyakhare.textdrawable.TextDrawable;
import com.amulyakhare.textdrawable.util.ColorGenerator;
import com.digzdigital.reminderapp.R;
import com.digzdigital.reminderapp.data.db.model.Course;

import org.zakariya.stickyheaders.SectioningAdapter;

import java.util.ArrayList;



public class ManageCourseListAdapter extends SectioningAdapter {

    private ArrayList<Course> courses;
    private ArrayList<Section> sections = new ArrayList<>();

    private static MyClickListener myClickListener;
    public ManageCourseListAdapter() {

    }

    public void setCourses(ArrayList<Course> courses){
        this.courses = courses;
        sections.clear();

        int alpha = 0;
        String dayText;
        Section currentSection = null;
        for(Course course : courses){
          if (course.getDayInt() != alpha){
              if (currentSection !=null){
                  sections.add(currentSection);
              }

              currentSection = new Section();
              alpha = course.getDayInt();
              currentSection.day = course.getDayText();

          }

            if (currentSection !=null){
                currentSection.courses.add(course);
            }
        }

        sections.add(currentSection);
        notifyAllSectionsDataSetChanged();
    }

    @Override
    public int getNumberOfSections() {
        return sections.size();
    }

    @Override
    public int getNumberOfItemsInSection(int sectionIndex) {
        return sections.get(sectionIndex).courses.size();
    }

    @Override
    public boolean doesSectionHaveHeader(int sectionIndex) {
        return true;
    }

    @Override
    public boolean doesSectionHaveFooter(int sectionIndex) {
        return false;
    }

    @Override
    public ItemViewHolder onCreateItemViewHolder(ViewGroup parent, int itemType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.item_manage_courses, parent, false);
        return new ItemViewHolder(v);
    }

    @Override
    public HeaderViewHolder onCreateHeaderViewHolder(ViewGroup parent, int headerType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.header_manage_courses, parent, false);
        return new HeaderViewHolder(v);
    }

    @Override
    public void onBindHeaderViewHolder(SectioningAdapter.HeaderViewHolder viewHolder, int sectionIndex, int headerUserType) {
        Section section = sections.get(sectionIndex);
        HeaderViewHolder headerViewHolder = (HeaderViewHolder) viewHolder;

        headerViewHolder.dayText.setText(section.day);
    }

    @Override
    public void onBindItemViewHolder(SectioningAdapter.ItemViewHolder viewHolder, int sectionIndex, int itemIndex, int itemUserType) {
        Section section = sections.get(sectionIndex);
        ItemViewHolder itemViewHolder = (ItemViewHolder) viewHolder;
        Course course = section.courses.get(itemIndex);

        itemViewHolder.courseTitle.setText(course.getCourseTitle());
        itemViewHolder.courseVenue.setText(course.getVenue());
        itemViewHolder.courseTime.setText(course.getStartTime());
        itemViewHolder.courseImage.setImageDrawable(createDrawable(course.getCourseCode()));
    }

    private TextDrawable createDrawable(String name) {
        ColorGenerator generator = ColorGenerator.MATERIAL; // or use DEFAULT
        int color1 = generator.getColor(name);
        TextDrawable.IBuilder builder = TextDrawable.builder()
                .beginConfig()
                .toUpperCase()
                .withBorder(4)
                .endConfig()
                .roundRect(10);
        return builder.build(name, color1);
    }

    private class Section {
        String day;
        ArrayList<Course> courses = new ArrayList<>();
    }

    public class ItemViewHolder extends SectioningAdapter.ItemViewHolder implements  View.OnClickListener{

        ImageView courseImage;
        TextView courseTitle, courseVenue, courseTime;

        public ItemViewHolder(View itemView) {
            super(itemView);
            courseImage = (ImageView) itemView.findViewById(R.id.courseImage);
            courseTitle = (TextView) itemView.findViewById(R.id.courseTitle);
            courseTime = (TextView) itemView.findViewById(R.id.courseTime);
            courseVenue = (TextView) itemView.findViewById(R.id.courseVenue);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int adapterPosition = getAdapterPosition();
            final int sectionInt = ManageCourseListAdapter.this.getSectionForAdapterPosition(adapterPosition);
            final int itemInt = ManageCourseListAdapter.this.getPositionOfItemInSection(sectionInt, adapterPosition);
            Section section = sections.get(sectionInt);
            Course course = section.courses.get(itemInt);
            // Course course = new Course();
            myClickListener.onItemClick(course, v);
        }
    }

    public class HeaderViewHolder extends SectioningAdapter.HeaderViewHolder {

        TextView dayText;

        public HeaderViewHolder(View itemView) {
            super(itemView);
            dayText = (TextView) itemView.findViewById(R.id.titleTextView);
        }
    }


    public void setOnItemClickListener(MyClickListener myClickListener) {
        this.myClickListener = myClickListener;
    }

    public interface MyClickListener {
        public void onItemClick(Course course, View v);
    }
}

