package com.ahsanzaman.contactsapp.ui.module.contact.view;

import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;

import com.ahsanzaman.contactsapp.R;
import com.ahsanzaman.contactsapp.model.response.ContactDetailResponse;
import com.ahsanzaman.contactsapp.ui.module.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public abstract class EditableContactActivity extends BaseActivity {

    @BindView(R.id.name_et)
    EditText mNameET;
    @BindView(R.id.phone_et)
    EditText mPhoneET;
    @BindView(R.id.email_et)
    EditText mEmailET;
    @BindView(R.id.name_til)
    TextInputLayout mNameTIL;
    @BindView(R.id.phone_et)
    TextInputLayout mPhoneTIL;
    @BindView(R.id.email_et)
    TextInputLayout mEmailTIL;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    protected abstract void onValidatedSave();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editable_contact);
        ButterKnife.bind(this);
        setSupportActionBar(mToolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setHomeButtonEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    protected boolean validate(){
        return isNamevalid() && isPhonevalid() && isEmailValid();
    }

    private boolean isNamevalid(){
        String name = mNameET.getText().toString();
        if(TextUtils.isEmpty(name)){
            mNameTIL.setErrorEnabled(true);
            mNameTIL.setError("Please enter a name");
            return false;
        }
        String[] names = name.split(" ");
        if(names.length > 2){
            mNameTIL.setErrorEnabled(true);
            mNameTIL.setError("Please enter only first and last names");
            return false;
        }
        if(names.length < 2){
            mNameTIL.setErrorEnabled(true);
            mNameTIL.setError("Please enter first and last names");
            return false;
        }
        if(TextUtils.isEmpty(names[0]) || names[0].length()<3){
            mNameTIL.setErrorEnabled(true);
            mNameTIL.setError("First name should be of at least 3 characters");
            return false;
        }
        if(TextUtils.isEmpty(names[1]) || names[1].length()<3){
            mNameTIL.setErrorEnabled(true);
            mNameTIL.setError("Last name should be of at least 3 characters");
            return false;
        }
        return true;
    }
    private boolean isPhonevalid(){
        String phone = mPhoneET.getText().toString();
        if(TextUtils.isEmpty(phone) || !isValidMobile(phone) || phone.length()!=10){
            mPhoneTIL.setErrorEnabled(true);
            mPhoneTIL.setError("Please enter a 10 digit phone number");
            return false;
        }
        return true;
    }
    
    private boolean isEmailValid(){
        String email = mEmailET.getText().toString();
        if(TextUtils.isEmpty(email) || !isValidMail(email)){
            mNameTIL.setErrorEnabled(true);
            mNameTIL.setError("Please enter a valid email");
            return false;
        }
        return true;
    }

    private boolean isValidMail(String email) {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    private boolean isValidMobile(String phone) {
        return android.util.Patterns.PHONE.matcher(phone).matches();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.editable_contact_details_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.action_save:
                if(validate()){
                    onValidatedSave();
                }
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    protected void bind(ContactDetailResponse contactDetailResponse){
        if(contactDetailResponse!=null) {
            mNameET.setText(contactDetailResponse.getFirstName() + " " + contactDetailResponse.getLastName());
            mPhoneET.setText(contactDetailResponse.getPhoneNumber());
            mEmailET.setText(contactDetailResponse.getEmail());
        }
    }

    protected ContactDetailResponse getContactDetailResponse(){
        ContactDetailResponse contactDetailResponse = new ContactDetailResponse();
        String [] names = mNameET.getText().toString().split(" ");
        contactDetailResponse.setFirstName(names[0]);
        contactDetailResponse.setLastName(names[1]);
        contactDetailResponse.setPhoneNumber(mPhoneET.getText().toString());
        contactDetailResponse.setEmail(mEmailET.getText().toString());
        return contactDetailResponse;
    }
}
