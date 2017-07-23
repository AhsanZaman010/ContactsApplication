package com.ahsanzaman.contactsapp.ui.module.contact.view.details;

import android.os.Bundle;
import android.widget.Toast;

import com.ahsanzaman.contactsapp.R;
import com.ahsanzaman.contactsapp.di.module.ContactDetailsModule;
import com.ahsanzaman.contactsapp.ui.module.base.BaseActivity;
import com.ahsanzaman.contactsapp.ui.module.base.BasePresenter;
import com.ahsanzaman.contactsapp.ui.module.base.ContactsApplication;

public class ContactDetailsActivity extends BaseActivity {

    public static final String CONTACT_ID = "Contact id";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_details);
        Long contactID = -1L;
        if(getIntent()!=null && getIntent().getData() != null){
            contactID = getIntent().getLongExtra(CONTACT_ID, -1);
        }
        if(contactID <= 0){
            Toast.makeText(this, "Contact not found", Toast.LENGTH_SHORT);
            finish();
        }
        ((ContactsApplication) getApplicationContext()).getDeps().plus(new ContactDetailsModule(this, contactID)).inject(this);
    }

    @Override
    protected BasePresenter getPresenter() {
        return null;
    }
}
