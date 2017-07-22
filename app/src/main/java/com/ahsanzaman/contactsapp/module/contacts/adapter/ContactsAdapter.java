package com.ahsanzaman.contactsapp.module.contacts.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ahsanzaman.contactsapp.R;
import com.ahsanzaman.contactsapp.model.Contact;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Accolite- on 7/22/2017.
 */

public class ContactsAdapter extends RecyclerView.Adapter<ContactsAdapter.ViewHolder> {
    private final OnItemClickListener listener;
    private List<Contact> mItems;
    private Context context;

    public ContactsAdapter(Context context, List<Contact> data, OnItemClickListener listener) {
        this.mItems = data;
        this.listener = listener;
        this.context = context;
    }


    @Override
    public ContactsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.contact_item_layout, null);
        view.setLayoutParams(new RecyclerView.LayoutParams(RecyclerView.LayoutParams.MATCH_PARENT, RecyclerView.LayoutParams.WRAP_CONTENT));
        return new ViewHolder(view);
    }

    public ContactsAdapter updateItems(List<Contact> data){
        mItems.clear();
        mItems.addAll(data);
        return this;
    }

    public ContactsAdapter addItems(List<Contact> data){
        mItems.addAll(data);
        return this;
    }


    @Override
    public void onBindViewHolder(ContactsAdapter.ViewHolder holder, int position) {
        holder.bind(mItems.get(position), listener);

        //String images = mItems.get(position).getBackground();

        /*Glide.with(context)
                .load(images)
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .skipMemoryCache(true)
                .into(holder.background);*/

    }


    @Override
    public int getItemCount() {
        return mItems == null? 0 : mItems.size();
    }


    public interface OnItemClickListener {
        void onClick(Contact item);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.header_text)
        TextView mHeaderTV;
        @BindView(R.id.label_text)
        TextView mLabelTV;
        @BindView(R.id.name_text)
        TextView mNameTV;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }


        public void bind(final Contact contact, final OnItemClickListener listener) {
            mHeaderTV.setText(contact.getFirstName() + " " + contact.getLastName());
            if(!TextUtils.isEmpty(contact.getFirstName())){
                mLabelTV.setText(contact.getFirstName().charAt(0) + "");
            } else if(!TextUtils.isEmpty(contact.getLastName())){
                mLabelTV.setText(contact.getLastName().charAt(0) + "");
            }
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onClick(contact);
                }
            });
        }
    }


}
