package com.ahsanzaman.contactsapp.ui.module.contact.view.details;

import com.ahsanzaman.contactsapp.model.response.ContactDetail;
import com.ahsanzaman.contactsapp.ui.module.base.BaseView;

import java.util.List;

/**
 * Created by Accolite- on 7/23/2017.
 */

public interface ContactDetailsView extends BaseView {

    void bind(ContactDetail contactDetail, List<com.ahsanzaman.contactsapp.model.ContactDetail> contactDetails);

    void callNumber(String phoneNumber);

    void dialContactPhone(String phoneNumber);

    void sendMail(String email);

    void sendMessage(String phoneNumber);
}
