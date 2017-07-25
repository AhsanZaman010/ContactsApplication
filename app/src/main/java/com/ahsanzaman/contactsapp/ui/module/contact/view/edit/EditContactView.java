package com.ahsanzaman.contactsapp.ui.module.contact.view.edit;

import com.ahsanzaman.contactsapp.model.response.ContactDetailResponse;
import com.ahsanzaman.contactsapp.ui.module.base.BaseView;

/**
 * Created by Accolite- on 7/23/2017.
 */

public interface EditContactView extends BaseView {
    void bindToContact(ContactDetailResponse contactDetailResponse);

    ContactDetailResponse getContactDetail();

    void finishOnSuccess(ContactDetailResponse contactDetailResponse);
}
