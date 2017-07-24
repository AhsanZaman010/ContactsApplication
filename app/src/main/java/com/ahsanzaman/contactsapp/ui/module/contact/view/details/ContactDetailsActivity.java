package com.ahsanzaman.contactsapp.ui.module.contact.view.details;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.ahsanzaman.contactsapp.R;
import com.ahsanzaman.contactsapp.di.module.ContactDetailsModule;
import com.ahsanzaman.contactsapp.model.Contact;
import com.ahsanzaman.contactsapp.model.ContactDetail;
import com.ahsanzaman.contactsapp.model.response.ContactResponse;
import com.ahsanzaman.contactsapp.model.response.ContactDetailResponse;
import com.ahsanzaman.contactsapp.ui.module.base.BaseActivity;
import com.ahsanzaman.contactsapp.ui.module.base.BasePresenter;
import com.ahsanzaman.contactsapp.ui.module.base.ContactsApplication;
import com.ahsanzaman.contactsapp.ui.module.contact.adapter.ContactDetailsAdapter;
import com.ahsanzaman.contactsapp.ui.module.contact.adapter.ContactsAdapter;
import com.ahsanzaman.contactsapp.ui.module.contact.presenter.details.ContactDetailsPresenter;
import com.ahsanzaman.contactsapp.ui.module.contact.view.ContactsActivity;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ContactDetailsActivity extends BaseActivity implements ContactDetailsView {

    public static final String CONTACT_ID = "Contact id";

    @Inject
    ContactDetailsPresenter mContactDetailsPresenter;

    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.contact_details_rv)
    RecyclerView mDetailsRV;
    private ContactDetailsAdapter mContactDetailsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_details);
        ButterKnife.bind(this);
        Long contactID = -1L;
        if(getIntent()!=null){
            contactID = getIntent().getLongExtra(CONTACT_ID, -1);
        }
        if(contactID <= 0){
            Toast.makeText(this, "Contact not found", Toast.LENGTH_SHORT);
            finish();
        }
        ((ContactsApplication) getApplicationContext()).getDeps().plus(new ContactDetailsModule(this, contactID)).inject(this);
        setSupportActionBar(mToolbar);
        if(getSupportActionBar()!= null) {
            getSupportActionBar().setHomeButtonEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        }
        setTitle(mContactDetailsPresenter.getName());
        mContactDetailsAdapter = new ContactDetailsAdapter(this, new ArrayList<ContactDetail>());
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        mDetailsRV.setLayoutManager(mLayoutManager);
        mDetailsRV.setAdapter(mContactDetailsAdapter);

    }

    @Override
    protected BasePresenter getPresenter() {
        return null;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.contact_details_menu, menu);
        if(mContactDetailsPresenter!=null){
            boolean isFavourite = mContactDetailsPresenter.isFavourite();
            MenuItem item = menu.findItem(R.id.action_favorite);
            if(isFavourite){
                item.setIcon(R.drawable.ic_favourite_filled);
            } else {
                item.setIcon(R.drawable.ic_favourite);
            }
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.action_favorite:
                boolean isFavourite = mContactDetailsPresenter.toggleFavourite();
                if(isFavourite){
                    item.setIcon(R.drawable.ic_favourite_filled);
                } else {
                    item.setIcon(R.drawable.ic_favourite);
                }
                return true;
            case R.id.action_edit:
                return true;
            case R.id.action_settings_share:
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
    
    @Override
    public void bind(ContactDetailResponse contactDetailResponse){
        if(contactDetailResponse == null){
            List<ContactDetail> contactDetails = new ArrayList<>();
            if(!TextUtils.isEmpty(contactDetailResponse.getPhoneNumber())){

            }
        }
    }
}
