package com.touge.learnsunflower.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.touge.learnsunflower.PlantDetailActivity;
import com.touge.learnsunflower.PlantDetailFragment;
import com.touge.learnsunflower.PlantListActivity;
import com.touge.learnsunflower.R;
import com.touge.learnsunflower.data.Plant;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * @Author Touge
 * @Date 2020/4/4 21:57
 * @Description
 */
public class PlantAdapter extends RecyclerView.Adapter<PlantAdapter.ViewHolder> {

    private final PlantListActivity mParentActivity;
    private final List<Plant> mValues;
    private final boolean isTwoPane;
    private final View.OnClickListener mOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Plant item = (Plant) view.getTag();
            if (isTwoPane) {
                Bundle arguments = new Bundle();
                arguments.putString(PlantDetailFragment.ARG_ITEM_ID, item.id);
                PlantDetailFragment fragment = new PlantDetailFragment();
                fragment.setArguments(arguments);
                mParentActivity.getSupportFragmentManager().beginTransaction()
                        .replace(R.id.plant_detail_container, fragment)
                        .commit();
            } else {
                Context context = view.getContext();
                Intent intent = new Intent(context, PlantDetailActivity.class);
                intent.putExtra(PlantDetailFragment.ARG_ITEM_ID, item.id);

                context.startActivity(intent);
            }

        }
    };


    public PlantAdapter(@NonNull PlantListActivity parent,
                        @NonNull List<Plant> items,
                        boolean isMasterDetail) {
        mParentActivity = parent;
        mValues = items;
        this.isTwoPane = isMasterDetail;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_plant, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.mIdView.setText(mValues.get(position).id);
        holder.mContentView.setText(mValues.get(position).name);
        holder.itemView.setTag(mValues.get(position));
        holder.itemView.setOnClickListener(mOnClickListener);
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        final TextView mIdView;
        final TextView mContentView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mIdView = itemView.findViewById(R.id.id_text);
            mContentView = itemView.findViewById(R.id.content);
        }
    }

}
