package com.epicodus.neverlonely.ui;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.epicodus.neverlonely.R;
import com.epicodus.neverlonely.models.Event;
import com.epicodus.neverlonely.models.EventsCart;

import java.util.List;

/**
 * Created by katsiarynamashokha on 10/21/17.
 */

public class EventListFragment extends Fragment {
    private RecyclerView mEventRecyclerView;
    private EventAdapter mAdapter;

    public static EventListFragment newInstance() {
        return new EventListFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_event_list, container, false);
        mEventRecyclerView = view.findViewById(R.id.event_recycler_view);
        mEventRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        TextView mHeaderTextView = view.findViewById(R.id.events_around_text_view);
        Typeface grandHotelFont =  Typeface.createFromAsset(getActivity().getAssets(), "fonts/grandhotel.ttf");
        mHeaderTextView.setTypeface(grandHotelFont);

        final FloatingActionButton mAddFab = view.findViewById(R.id.add_fab);
        mAddFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), NewEventActivity.class);
                startActivity(intent);
            }
        });

        // Hide mAddFab when scrolling down
        mEventRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                if(dy > 0) {
                    mAddFab.hide();
                } else if(dy < 0) {
                    mAddFab.show();
                }
            }
        });

        updateUI();
        return view;
    }

    private void updateUI() {
        EventsCart eventsCart = EventsCart.get(getActivity());
        List<Event> events = eventsCart.getEvents();
        if(mAdapter == null) {
            mAdapter = new EventAdapter(events);
            mEventRecyclerView.setAdapter(mAdapter);
        } else {
            mAdapter.notifyDataSetChanged();
        }
    }

    private class EventHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private Event mEvent;
        private TextView mTitleTextView;
        private TextView mDateTextView;

        public EventHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.list_item_event, parent, false));

            mTitleTextView = itemView.findViewById(R.id.title_text_view);
            mDateTextView = itemView.findViewById(R.id.date_text_view);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (v == itemView) {
                Intent intent = EventPagerActivity.newIntent(getActivity(), mEvent.getId());
                startActivity(intent);
            }
        }

        public void bind(Event event) {
            mEvent = event;
            mTitleTextView.setText(event.getTitle());
            mDateTextView.setText(event.getDate());
        }
    }

    private class EventAdapter extends RecyclerView.Adapter<EventHolder> {
        private List<Event> mEvents;

        public EventAdapter(List<Event> events) {
            mEvents = events;
        }

        @Override
        public EventHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            return new EventHolder(layoutInflater, parent);
        }

        @Override
        public void onBindViewHolder(EventHolder holder, int position) {
            Event event = mEvents.get(position);
            holder.bind(event);
        }

        @Override
        public int getItemCount() {
            return mEvents.size();
        }
    }

    // update RecyclerView when a new item got added
    @Override
    public void onResume() {
        super.onResume();
        updateUI();
    }
}
