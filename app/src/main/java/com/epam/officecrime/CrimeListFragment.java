package com.epam.officecrime;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Date;
import java.util.List;

/**
 * Created by Dmytro_Torianik on 1/31/2019.
 */

public class CrimeListFragment extends Fragment {

    private static final int CRIME_REQUIRES_POLICE = 1;
    private static final int CRIME_NOT_REQUIRES_POLICE = 0;

    private RecyclerView crimeRecyclerView;
    private CrimeAdapter crimeAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_crimes_list, container, false);
        crimeRecyclerView = view.findViewById(R.id.crime_recycler_view);
        crimeRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        updateUI();

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        updateUI();
    }

    private void updateUI() {
        CrimeStorage crimeStorage = CrimeStorage.get(getActivity());
        List<Crime> crimes = crimeStorage.findAll();

        if (crimeAdapter == null) {
            crimeAdapter = new CrimeAdapter(crimes);
            crimeRecyclerView.setAdapter(crimeAdapter);
        } else {
            crimeAdapter.notifyDataSetChanged();
        }
    }

    private class CrimeHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView crimeTitleTextView;
        private TextView crimeDateTextView;
        private Crime crime;

        public CrimeHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.list_item_crime, parent, false));
            initViews();
        }

        public CrimeHolder(View view) {
            super(view);
        }

        protected void initViews() {
            crimeTitleTextView = itemView.findViewById(R.id.crime_title);
            crimeDateTextView = itemView.findViewById(R.id.crime_date);
            itemView.setOnClickListener(this);
        }

        public void bind(Crime aCrime) {
            crime = aCrime;
            crimeTitleTextView.setText(crime.getTitle());
            crimeDateTextView.setText(crime.getFormattedDate());
        }

        @Override
        public void onClick(View view) {
            Intent intent = CrimeActivity.newIntent(getActivity(), crime.getId());
            startActivity(intent);
        }
    }

    private class PoliceCrimeHolder extends CrimeHolder {

        public PoliceCrimeHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.list_item_crime_police, parent, false));
            initViews();
        }
    }

    private class CrimeAdapter extends RecyclerView.Adapter<CrimeHolder> {
        private List<Crime> crimes;

        public CrimeAdapter(List<Crime> crimes) {
            this.crimes = crimes;
        }

        @Override
        public CrimeHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(getActivity());
            return viewType == CRIME_REQUIRES_POLICE ? new PoliceCrimeHolder(inflater, parent) : new CrimeHolder(inflater, parent);
        }

        @Override
        public void onBindViewHolder(CrimeHolder holder, int position) {
            Crime crime = crimes.get(position);
            holder.bind(crime);
        }

        @Override
        public int getItemCount() {
            return crimes.size();
        }

        @Override
        public int getItemViewType(int position) {
            Crime crime = crimes.get(position);
            return crime.isRequiresPolice() ? CRIME_REQUIRES_POLICE : CRIME_NOT_REQUIRES_POLICE;
        }
    }

}
