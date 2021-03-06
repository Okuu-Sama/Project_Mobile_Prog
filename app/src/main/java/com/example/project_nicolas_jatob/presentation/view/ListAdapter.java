package com.example.project_nicolas_jatob.presentation.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.project_nicolas_jatob.R;
import com.example.project_nicolas_jatob.presentation.model.Granblue_Character;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> {
    private List<Granblue_Character> values;
    private OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(Granblue_Character item);
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        public TextView txtHeader;
        public TextView txtFooter;
        public View layout;
        public ImageView characterIcon;

        public ViewHolder(View v) {
            super(v);
            layout = v;
            txtHeader = (TextView) v.findViewById(R.id.firstLine);
            txtFooter = (TextView) v.findViewById(R.id.secondLine);
            characterIcon = (ImageView) v.findViewById(R.id.icon);
        }
    }

    public void add(int position, Granblue_Character item) {
        values.add(position, item);
        notifyItemInserted(position);
    }

    public void remove(int position) {
        values.remove(position);
        notifyItemRemoved(position);
    }

    public ListAdapter(List<Granblue_Character> myDataset, OnItemClickListener listener) {
        values = myDataset;
        this.listener = listener;
    }

    @Override
    public ListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                   int viewType) {
        LayoutInflater inflater = LayoutInflater.from(
                parent.getContext());
        View v =
                inflater.inflate(R.layout.row_layout, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        final Granblue_Character currentCharacter = values.get(position);
        String iconPath = currentCharacter.getIconPath();
        String path = "https://gbf.wiki/images/"+iconPath;
        Picasso.get().load(path).resize(260,180).into(holder.characterIcon);
        holder.txtHeader.setText(currentCharacter.getName());
        holder.txtFooter.setText(currentCharacter.getElement());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClick(currentCharacter);
            }
        });

    }

    @Override
    public int getItemCount() {
        return values.size();
    }

}
