package com.example.padcc.retrofitlibraryapp;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ViewHolder>{
    private List<Contact> mData;
    private LayoutInflater mInflater;
    private ItemClickListener mClickListener;
    Context mContext;

    // data is passed into the constructor
    ContactAdapter(Context context, List<Contact> data) {
        this.mInflater = LayoutInflater.from(context);
        this.mData = data;
        mContext=context;
    }

    public ContactAdapter(MainActivity context, Contacts contacts) {
    }

    // inflates the row layout from xml when needed
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.recyclerview_row, parent, false);
        return new ViewHolder(view);
    }
    // binds the data to the TextView in each row
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        holder.txtid.setText(mData.get(position).getId());
        holder.txtname.setText((mData.get(position).getName()));
        holder.txtemail.setText((mData.get(position).getEmail()));
        holder.txtaddress.setText(mData.get(position).getAddress());
        holder.txtgender.setText(mData.get(position).getGender());
        holder.txtmobile.setText(mData.get(position).getPhone().getMobile());
        holder.txthome.setText(mData.get(position).getPhone().getHome());
        holder.txtoffice.setText(mData.get(position).getPhone().getOffice());

    }
    // total number of rows
    @Override
    public int getItemCount() {
        return mData.size();
    }
    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView txtid,txtname,txtemail,txtaddress,txtgender,txtmobile,txthome,txtoffice;

        ImageView imgProductImage;

        ViewHolder(View itemView) {
            super(itemView);
            txtid = itemView.findViewById(R.id.Id);
            txtname = itemView.findViewById(R.id.Name);
            txtemail = itemView.findViewById(R.id.Email);
            txtaddress = itemView.findViewById(R.id.Address);
           txtgender = itemView.findViewById(R.id.Gender);
            txtmobile = itemView.findViewById(R.id.Mobile);
            txthome = itemView.findViewById(R.id.Home);
            txtoffice = itemView.findViewById(R.id.Office);
            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition());
        }
    }

    // convenience method for getting data at click position
    String getItem(int id) {
        return String.valueOf(mData.get(id).getId());
    }

    // allows clicks events to be caught
    void setClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    // parent activity will implement this method to respond to click events
    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }
}
