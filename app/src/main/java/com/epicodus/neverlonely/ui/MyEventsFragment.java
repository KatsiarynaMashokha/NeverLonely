package com.epicodus.neverlonely.ui;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.epicodus.neverlonely.Constants;
import com.epicodus.neverlonely.R;
import com.epicodus.neverlonely.adapters.FirebaseEventListAdapter;
import com.epicodus.neverlonely.adapters.FirebaseEventsViewHolder;
import com.epicodus.neverlonely.models.Event;
import com.epicodus.neverlonely.util.OnStartDragListener;
import com.epicodus.neverlonely.util.SimpleItemTouchHelperCallback;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MyEventsFragment extends Fragment
        implements OnStartDragListener {

    public static MyEventsFragment newInstance() {
        return new MyEventsFragment();
    }

    private FirebaseEventListAdapter mFirebaseAdapter;
    private ItemTouchHelper mItemTouchHelper;
    @Bind(R.id.my_events_recycler_view) RecyclerView mRecyclerView;
    @Bind(R.id.my_events_text_view) TextView mMyEventsTextView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.my_events_fragment, container, false);
        ButterKnife.bind(this, v);
        Typeface grandHotelFont =  Typeface.createFromAsset(getActivity().getAssets(), "fonts/grandhotel.ttf");
        mMyEventsTextView.setTypeface(grandHotelFont);
        setUpFirebaseAdapter();
        return v;
    }

    private void setUpFirebaseAdapter() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String uid = user.getUid();

        Query query = FirebaseDatabase.getInstance()
                .getReference(Constants.FIREBASE_CHILD_MY_EVENTS)
                .child(uid)
                .orderByChild(Constants.FIREBASE_QUERY_INDEX);

        mFirebaseAdapter = new FirebaseEventListAdapter
                (Event.class,
                        R.layout.list_item_event,
                        FirebaseEventsViewHolder.class,
                        query,
                        this,
                        getActivity());

        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setAdapter(mFirebaseAdapter);

        mFirebaseAdapter.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
            @Override
            public void onItemRangeInserted(int positionStart, int itemCount) {
                super.onItemRangeInserted(positionStart, itemCount);
                mFirebaseAdapter.notifyDataSetChanged();
            }
        });

        ItemTouchHelper.Callback callback = new SimpleItemTouchHelperCallback(mFirebaseAdapter);
        mItemTouchHelper = new ItemTouchHelper(callback);
        mItemTouchHelper.attachToRecyclerView(mRecyclerView);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mFirebaseAdapter.cleanup();
    }

    @Override
    public void onStartDrag(RecyclerView.ViewHolder viewHolder) {
        mItemTouchHelper.startDrag(viewHolder);

    }
}
