package com.ahsanzaman.contactsapp.utils;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.text.TextUtils;
import android.view.View;

import com.ahsanzaman.contactsapp.R;
import com.ahsanzaman.contactsapp.model.Contact;
import com.ahsanzaman.contactsapp.model.ContactDetailUIItem;
import com.ahsanzaman.contactsapp.model.response.ContactDetail;
import com.ahsanzaman.contactsapp.ui.module.contact.presenter.ContactableInteractiveView;
import com.ahsanzaman.contactsapp.ui.module.contact.view.details.ContactDetailsActivity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static com.ahsanzaman.contactsapp.ui.module.contact.view.details.ContactDetailsActivity.CALL_PERMISSION_REQUEST;

/**
 * Created by Accolite- on 7/22/2017.
 */

public class ContactUtils {

    public static void sortContacts(List<Contact> contacts){
        Collections.sort(contacts, new Comparator<Contact>() {
            @Override
            public int compare(Contact o1, Contact o2) {
                String name1 = o1.getFirstName() + " " + o1.getLastName();
                String name2 = o2.getFirstName() + " " + o2.getLastName();
                if(o1.isFavorite() && o2.isFavorite()){
                    return name1.compareToIgnoreCase(name2);
                } else if(o1.isFavorite()){
                    return -1;
                } else if(o2.isFavorite()){
                    return 1;
                } else {
                    return name1.compareToIgnoreCase(name2);
                }
            }
        });
    }

    public static synchronized boolean isHeader(List<Contact> contacts, int position){
        if(contacts == null || contacts.size()==0){
            return false;
        }
        if(position < 0 || position >= contacts.size()){
            return false;
        }
        if(position == 0){
            return true;
        }
        Contact contact = contacts.get(position);
        if(contact.isFavorite()){
            return false;
        }
        String label = getLabel(contact);
        String labelPrevious = getLabel(contacts.get(position-1));
        if(label.equals(labelPrevious)){
            return false;
        } else {
            return true;
        }
    }

    public static synchronized String getLabel(Contact contact){
        if(!TextUtils.isEmpty(contact.getFirstName())){
            return (contact.getFirstName().charAt(0) + "").toUpperCase();
        } else if(!TextUtils.isEmpty(contact.getLastName())){
            return (contact.getLastName().charAt(0) + "").toUpperCase();
        } else return "";
    }

    public static synchronized List<ContactDetailUIItem> getContactDetailsUIItems(final ContactDetail contactDetail, final ContactableInteractiveView contactableInteractiveView){
        List<ContactDetailUIItem> contactDetailUIItems = new ArrayList<>();
        if (contactDetail == null) {
            if (!TextUtils.isEmpty(contactDetail.getPhoneNumber())) {
                final ContactDetailUIItem contactDetailUIItem = new ContactDetailUIItem();
                contactDetailUIItem.setTitle(contactDetail.getPhoneNumber());
                contactDetailUIItem.setDescription("Mobile");
                contactDetailUIItem.setEndImageResource(R.drawable.ic_message);
                contactDetailUIItem.setStartImageResource(R.drawable.ic_call_blue);
                contactDetailUIItem.setEndIconOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        contactableInteractiveView.onSMSClicked();
                    }
                });
                contactDetailUIItem.setItemOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        contactableInteractiveView.onCallClicked();
                        }
                });
                contactDetailUIItems.add(contactDetailUIItem);
            }
            if (!TextUtils.isEmpty(contactDetail.getPhoneNumber())) {
                final ContactDetailUIItem contactDetailUIItem = new ContactDetailUIItem();
                contactDetailUIItem.setTitle(contactDetail.getPhoneNumber());
                contactDetailUIItem.setDescription("Other");
                contactDetailUIItem.setStartImageResource(R.drawable.ic_email_blue);
                contactDetailUIItem.setItemOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        contactableInteractiveView.onEmailClicked();}
                });
                contactDetailUIItems.add(contactDetailUIItem);
            }
        }
        return contactDetailUIItems;
    }

}
