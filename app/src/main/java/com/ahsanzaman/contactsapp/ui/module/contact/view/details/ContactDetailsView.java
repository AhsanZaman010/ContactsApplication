package com.ahsanzaman.contactsapp.ui.module.contact.view.details;

import com.ahsanzaman.contactsapp.model.Contact;
import com.ahsanzaman.contactsapp.model.ContactDetail;
import com.ahsanzaman.contactsapp.model.response.ContactDetailResponse;
import com.ahsanzaman.contactsapp.model.response.ContactResponse;
import com.ahsanzaman.contactsapp.ui.module.base.BaseView;

import java.util.List;

/**
 * Created by Accolite- on 7/23/2017.
 */

public interface ContactDetailsView extends BaseView {

    void bind(ContactDetailResponse contactDetailResponse, List<ContactDetail> contactDetails);

    void callNumber(String phoneNumber);

    void dialContactPhone(String phoneNumber);

    void sendMail(String email);

    void sendMessage(String phoneNumber);
}
