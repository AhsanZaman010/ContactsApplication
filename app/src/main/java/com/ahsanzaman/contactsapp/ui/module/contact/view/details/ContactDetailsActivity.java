package com.ahsanzaman.contactsapp.ui.module.contact.view.details;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.ahsanzaman.contactsapp.R;
import com.ahsanzaman.contactsapp.di.module.ContactDetailsModule;
import com.ahsanzaman.contactsapp.model.response.ContactDetail;
import com.ahsanzaman.contactsapp.ui.module.base.BaseActivity;
import com.ahsanzaman.contactsapp.ui.module.base.BasePresenter;
import com.ahsanzaman.contactsapp.ui.module.base.ContactsApplication;
import com.ahsanzaman.contactsapp.ui.module.contact.adapter.ContactDetailsAdapter;
import com.ahsanzaman.contactsapp.ui.module.contact.presenter.details.ContactDetailsPresenter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ContactDetailsActivity extends BaseActivity implements ContactDetailsView {

    public static final String CONTACT_ID = "Contact id";
    public static final int CALL_PERMISSION_REQUEST = 101;
    private static final String TAG = ContactDetailsActivity.class.getSimpleName();


    @Inject
    ContactDetailsPresenter mContactDetailsPresenter;

    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.contact_details_rv)
    RecyclerView mDetailsRV;
    private ContactDetailsAdapter mContactDetailsAdapter;
    private ContactDetail mContactDetail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_details);
        ButterKnife.bind(this);
        Long contactID = -1L;
        if (getIntent() != null) {
            contactID = getIntent().getLongExtra(CONTACT_ID, -1);
        }
        if (contactID <= 0) {
            Toast.makeText(this, "Contact not found", Toast.LENGTH_SHORT);
            finish();
        }
        ((ContactsApplication) getApplicationContext()).getDeps().plus(new ContactDetailsModule(this, contactID)).inject(this);
        setSupportActionBar(mToolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setHomeButtonEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        }
        setTitle(mContactDetailsPresenter.getName());
        mContactDetailsAdapter = new ContactDetailsAdapter(this, new ArrayList<com.ahsanzaman.contactsapp.model.ContactDetail>());
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        mDetailsRV.setLayoutManager(mLayoutManager);
        mDetailsRV.setAdapter(mContactDetailsAdapter);
        mContactDetailsPresenter.getContactDetails();
    }

    @Override
    protected BasePresenter getPresenter() {
        return null;
    }

    @Override
    protected void retry() {
        mContactDetailsPresenter.getContactDetails();
    }

    @Override
    protected void onCancelErrorDialog(DialogInterface dialog) {
        dialog.dismiss();
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.contact_details_menu, menu);
        if (mContactDetailsPresenter != null) {
            boolean isFavourite = mContactDetailsPresenter.isFavourite();
            MenuItem item = menu.findItem(R.id.action_favorite);
            if (isFavourite) {
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
                if (isFavourite) {
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
    public void bind(final ContactDetail contactDetail, List<com.ahsanzaman.contactsapp.model.ContactDetail> contactDetails) {
        mContactDetailsAdapter.updateItems(contactDetails)
                .notifyDataSetChanged();
        mContactDetail = contactDetail;
    }

    @Override
    public void callNumber(String phoneNumber) {
        try {
            Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + phoneNumber));
            startActivity(intent);
        } catch (SecurityException e){
            Log.e(TAG, "Call permission not checked");
        }
    }

    @Override
    public void dialContactPhone(final String phoneNumber) {
        startActivity(new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", phoneNumber, null)));
    }

    @Override
    public void sendMail(String email){
        Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                "mailto",email, null));
        startActivity(emailIntent);
    }

    @Override
    public void sendMessage(String phoneNumber) {
        startActivity(new Intent(Intent.ACTION_VIEW, Uri.fromParts("sms", phoneNumber, null)));
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode){
            case CALL_PERMISSION_REQUEST:
                if(grantResults != null){
                    if(grantResults[0] ==  PackageManager.PERMISSION_GRANTED && mContactDetail !=null){
                        callNumber(mContactDetail.getPhoneNumber());
                    } else if(mContactDetail !=null){
                        dialContactPhone(mContactDetail.getPhoneNumber());
                    }
                }
        }
    }
}
