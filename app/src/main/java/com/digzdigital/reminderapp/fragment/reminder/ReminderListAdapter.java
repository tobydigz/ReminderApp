package com.digzdigital.reminderapp.fragment.reminder;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.amulyakhare.textdrawable.TextDrawable;
import com.amulyakhare.textdrawable.util.ColorGenerator;
import com.digzdigital.reminderapp.R;
import com.digzdigital.reminderapp.data.db.model.ReminderItem;

import java.util.ArrayList;
import java.util.Calendar;


public class ReminderListAdapter extends RecyclerView.Adapter<ReminderListAdapter.ViewHolder> {
    private static MyClickListener myClickListener;
    ArrayList<ReminderItem> reminderItems;

    public ReminderListAdapter(ArrayList<ReminderItem> reminderItems) {
        this.reminderItems = reminderItems;
    }

    public ReminderItem getItem(int position) {
        return reminderItems.get(position);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_reminder, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        ReminderItem reminderItem = getItem(position);

        holder.reminderTitle.setText(reminderItem.getTitle());
        holder.reminderVenue.setText(reminderItem.getVenue());
        holder.reminderTime.setText(reminderItem.getDateString());
        if (reminderItem.getDate().after(Calendar.getInstance().getTime())) {
            holder.reminderImage.setImageDrawable(createDrawable("U"));
        } else {
            holder.reminderImage.setImageDrawable(createDrawable("D"));

        }

    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public int getItemCount() {
        return reminderItems.size();
    }

    public void setOnItemClickListener(MyClickListener myClickListener) {
        this.myClickListener = myClickListener;
    }

    private TextDrawable createDrawable(String name) {
        ColorGenerator generator = ColorGenerator.MATERIAL; // or use DEFAULT
        int color1 = generator.getRandomColor();
        TextDrawable.IBuilder builder = TextDrawable.builder()
                .beginConfig()
                .withBorder(4)
                .endConfig()
                .roundRect(10);
        return builder.build(name, Color.BLUE);
    }

    public interface MyClickListener {
        public void onItemClick(int position, View v);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView reminderImage;
        TextView reminderTitle, reminderVenue, reminderTime;

        ViewHolder(View itemView) {
            super(itemView);
            reminderImage = (ImageView) itemView.findViewById(R.id.reminderImage);
            reminderTitle = (TextView) itemView.findViewById(R.id.reminderTitle);
            reminderTime = (TextView) itemView.findViewById(R.id.reminderTime);
            reminderVenue = (TextView) itemView.findViewById(R.id.reminderVenue);
            itemView.setOnClickListener(this);
        }


        @Override
        public void onClick(View v) {
            myClickListener.onItemClick(getAdapterPosition(), v);

        }


    }
}

