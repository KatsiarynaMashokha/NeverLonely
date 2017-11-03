package com.epicodus.neverlonely.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.epicodus.neverlonely.Constants;
import com.epicodus.neverlonely.R;
import com.epicodus.neverlonely.models.Event;
import com.epicodus.neverlonely.ui.EventPagerActivity;
import com.epicodus.neverlonely.util.ItemTouchHelperViewHolder;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

/**
 * Created by katsiarynamashokha on 10/29/17.
 */

public class FirebaseEventsViewHolder extends RecyclerView.ViewHolder
        implements View.OnClickListener, ItemTouchHelperViewHolder {
    View mView;
    Context mContext;
    public TextView titleTextView;

    public FirebaseEventsViewHolder(View itemView) {
        super(itemView);
        mView = itemView;
        mContext = itemView.getContext();
        itemView.setOnClickListener(this);
    }

    public void bindEvent(Event event) {
        titleTextView = mView.findViewById(R.id.title_text_view);
        TextView dateTextView = mView.findViewById(R.id.date_text_view);
        titleTextView.setText(event.getTitle());
        dateTextView.setText(event.getDate());
    }

    @Override
    public void onClick(View v) {
        final ArrayList<Event> events = new ArrayList<>();
        DatabaseReference ref = FirebaseDatabase
                .getInstance()
                .getReference(Constants.FIREBASE_CHILD_MY_EVENTS)
                .child(FirebaseAuth.getInstance().getCurrentUser().getUid());
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    events.add(snapshot.getValue(Event.class));
                }
                int itemPosition = getLayoutPosition();
                String id = events.get(itemPosition).getId();
                Intent intent2 = EventPagerActivity.newIntent(mContext, id);
                mContext.startActivity(intent2);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }

    @Override
    public void onItemSelected() {
        itemView.animate()
                .alpha(0.7f)
                .scaleX(0.9f)
                .scaleY(0.9f)
                .setDuration(500);
    }

    @Override
    public void onItemClear() {
        itemView.animate()
                .alpha(1f)
                .scaleX(1f)
                .scaleY(1f);
    }
}
