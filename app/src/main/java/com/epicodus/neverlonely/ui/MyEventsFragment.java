package com.epicodus.neverlonely.ui;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.epicodus.neverlonely.Constants;
import com.epicodus.neverlonely.R;
import com.epicodus.neverlonely.adapters.FirebaseEventsViewHolder;
import com.epicodus.neverlonely.models.Event;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MyEventsFragment extends Fragment {

    public static MyEventsFragment newInstance() {
        return new MyEventsFragment();
    }

    private DatabaseReference mEventsReference;
    private FirebaseRecyclerAdapter mFirebaseAdapter;
    @Bind(R.id.my_events_recycler_view) RecyclerView mRecyclerView;
    @Bind(R.id.my_events_text_view) TextView mMyEventsTextView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.my_events_fragment, container, false);
        ButterKnife.bind(this, v);
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String uid = user.getUid();
        Typeface grandHotelFont =  Typeface.createFromAsset(getActivity().getAssets(), "fonts/grandhotel.ttf");
        mMyEventsTextView.setTypeface(grandHotelFont);
        mEventsReference = FirebaseDatabase
                .getInstance()
                .getReference(Constants.FIREBASE_CHILD_MY_EVENTS)
                .child(uid);
        setUpFirebaseAdapter();
        return v;
    }

    private void setUpFirebaseAdapter() {
        mFirebaseAdapter = new FirebaseRecyclerAdapter<Event, FirebaseEventsViewHolder>
                (Event.class, R.layout.list_item_event, FirebaseEventsViewHolder.class, mEventsReference) {
            @Override
            protected void populateViewHolder(FirebaseEventsViewHolder viewHolder, Event model, int position) {
                viewHolder.bindEvent(model);
            }
        };
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setAdapter(mFirebaseAdapter);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mFirebaseAdapter.cleanup();
    }
}
