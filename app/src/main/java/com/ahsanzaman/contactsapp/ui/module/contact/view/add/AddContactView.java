package com.ahsanzaman.contactsapp.ui.module.contact.view.add;

import com.ahsanzaman.contactsapp.model.response.ContactDetailResponse;
import com.ahsanzaman.contactsapp.ui.module.base.BaseView;

/**
 * Created by Accolite- on 7/23/2017.
 */

public interface AddContactView extends BaseView {

    ContactDetailResponse getContactDetail();

    void finishOnSuccess();
}
