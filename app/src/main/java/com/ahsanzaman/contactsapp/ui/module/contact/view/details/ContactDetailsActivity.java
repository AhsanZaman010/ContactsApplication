package com.ahsanzaman.contactsapp.ui.module.contact.view.details;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.ahsanzaman.contactsapp.R;
import com.ahsanzaman.contactsapp.ui.module.base.BaseActivity;
import com.ahsanzaman.contactsapp.ui.module.base.BasePresenter;

public class ContactDetailsActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_details);
    }

    @Override
    protected BasePresenter getPresenter() {
        return null;
    }
}
