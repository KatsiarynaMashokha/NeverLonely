package com.epicodus.neverlonely.adapters;

import android.content.Context;
import android.support.v4.view.MotionEventCompat;
import android.view.MotionEvent;
import android.view.View;

import com.epicodus.neverlonely.Constants;
import com.epicodus.neverlonely.models.Event;
import com.epicodus.neverlonely.util.ItemTouchHelperAdapter;
import com.epicodus.neverlonely.util.OnStartDragListener;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by katsiarynamashokha on 11/1/17.
 */

public class FirebaseEventListAdapter extends FirebaseRecyclerAdapter<Event, FirebaseEventsViewHolder>
        implements ItemTouchHelperAdapter {
    private DatabaseReference mRef;
    private OnStartDragListener mOnStartDragListener;
    private Context mContext;
    private ChildEventListener mChildEventListener;
    private ArrayList<Event> mEvents = new ArrayList<>();
    public static final String EXTRA_EVENT_ID = "com.epicodus.neverlonely.event_id";

    public FirebaseEventListAdapter(Class<Event> modelClass, int modelLayout,
                                    Class<FirebaseEventsViewHolder> viewHolderClass,
                                    Query ref, OnStartDragListener onStartDragListener, Context context) {
        super(modelClass, modelLayout, viewHolderClass, ref);
        mRef = ref.getRef();
        mOnStartDragListener = onStartDragListener;
        mContext = context;

        mChildEventListener = mRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                mEvents.add(dataSnapshot.getValue(Event.class));
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    @Override
    public boolean onItemMove(int fromPosition, int toPosition) {
        Collections.swap(mEvents, fromPosition, toPosition);
        notifyItemMoved(fromPosition, toPosition);
        return false;
    }

    @Override
    public void onItemDismiss(int position) {
        mEvents.remove(position);
        getRef(position).removeValue();
    }

    @Override
    protected void populateViewHolder(final FirebaseEventsViewHolder viewHolder, Event model, int position) {
        viewHolder.bindEvent(model);
        viewHolder.titleTextView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(MotionEventCompat.getActionMasked(event) == MotionEvent.ACTION_DOWN) {
                    mOnStartDragListener.onStartDrag(viewHolder);
                }
                return false;
            }
        });
    }

    private void setIndexInFirebase() {
           String userId = FirebaseAuth.getInstance()
                   .getCurrentUser()
                   .getUid();

            Map<String, Object> eventsMap = new HashMap<>();
            eventsMap.put(userId, mEvents);

            FirebaseDatabase.getInstance()
                    .getReference(Constants.FIREBASE_CHILD_MY_EVENTS)
                    .updateChildren(eventsMap);
    }

    @Override
    public void cleanup() {
        super.cleanup();
        setIndexInFirebase();
        mRef.removeEventListener(mChildEventListener);
    }
}
