package com.ahsanzaman.contactsapp.ui.module.contact.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ahsanzaman.contactsapp.R;
import com.ahsanzaman.contactsapp.model.ContactDetail;
import com.ahsanzaman.contactsapp.utils.ContactUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Ahsan Zaman on 24-07-2017.
 */

public class ContactDetailsAdapter extends RecyclerView.Adapter<ContactDetailsAdapter.ViewHolder> {
    private List<ContactDetail> mItems;
    private Context context;

    public ContactDetailsAdapter(Context context, List<ContactDetail> data) {
        this.mItems = data;
        this.context = context;
    }


    @Override
    public ContactDetailsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.contact_details_item_layout, null);
        view.setLayoutParams(new RecyclerView.LayoutParams(RecyclerView.LayoutParams.MATCH_PARENT, RecyclerView.LayoutParams.WRAP_CONTENT));
        return new ContactDetailsAdapter.ViewHolder(view);
    }

    public ContactDetailsAdapter updateItems(List<ContactDetail> data){
        mItems.clear();
        mItems.addAll(data);
        return this;
    }

    public ContactDetailsAdapter addItems(List<ContactDetail> data){
        mItems.addAll(data);
        return this;
    }


    @Override
    public void onBindViewHolder(ContactDetailsAdapter.ViewHolder holder, int position) {
        holder.bind(mItems.get(position), position);

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
        void onClick(ContactDetail item);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.title_tv)
        TextView mTitleTV;
        @BindView(R.id.description_tv)
        TextView mDescriptionTV;
        @BindView(R.id.icon_start_iv)
        ImageView mIconStart;
        @BindView(R.id.icon_end_iv)
        ImageView mIconEnd;
        @BindView(R.id.item_view)
        View mItemView;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }


        public void bind(final ContactDetail contactDetail, int position) {
            if(contactDetail!=null){
                mIconStart.setImageResource(contactDetail.getStartImageResource());
                mIconEnd.setImageResource(contactDetail.getEndImageResource());
                mTitleTV.setText(contactDetail.getTitle());
                mDescriptionTV.setText(contactDetail.getDescription());
                mItemView.setOnClickListener(contactDetail.getItemOnClickListener());
                mIconEnd.setOnClickListener(contactDetail.getEndIconOnClickListener());
            }
        }
    }


}
