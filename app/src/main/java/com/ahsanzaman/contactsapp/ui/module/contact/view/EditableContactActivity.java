package com.ahsanzaman.contactsapp.ui.module.contact.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.ahsanzaman.contactsapp.R;
import com.ahsanzaman.contactsapp.ui.module.base.BaseActivity;

public abstract class EditableContactActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editable_contact);
    }
}
