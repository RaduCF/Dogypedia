package com.dogypedia.listUi;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dogypedia.R;
import com.dogypedia.model.BreedResponse;
import com.google.firebase.auth.FirebaseAuth;

import java.util.List;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> {
    final private OnListItemClickListener mOnListItemClickListener;
    private List<BreedResponse> breedList;

    public ListAdapter(List<BreedResponse> list, OnListItemClickListener listener) {
        breedList = list;
        mOnListItemClickListener = listener;
    }

    @NonNull
    @Override
    public ListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_breed, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListAdapter.ViewHolder holder, int position) {
        holder.breedName.setText(breedList.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return breedList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView breedName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            breedName = itemView.findViewById(R.id.breedName_textView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            mOnListItemClickListener.onListItemClick(getAdapterPosition());
        }
    }
}