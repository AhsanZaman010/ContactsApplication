package com.ahsanzaman.contactsapp.ui.module.contact.view.details;

import com.ahsanzaman.contactsapp.model.Contact;
import com.ahsanzaman.contactsapp.model.response.ContactDetailResponse;
import com.ahsanzaman.contactsapp.model.response.ContactResponse;
import com.ahsanzaman.contactsapp.ui.module.base.BaseView;

/**
 * Created by Accolite- on 7/23/2017.
 */

public interface ContactDetailsView extends BaseView {

    void bind(ContactDetailResponse contactDetailResponse);
}
