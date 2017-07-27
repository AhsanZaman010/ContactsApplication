package com.ahsanzaman.contactsapp.ui.module.contact.view.details;

import com.ahsanzaman.contactsapp.model.ContactDetailUIItem;
import com.ahsanzaman.contactsapp.model.response.ContactDetail;
import com.ahsanzaman.contactsapp.ui.module.contact.presenter.ContactableInteractiveView;

import java.util.List;

/**
 * Created by Accolite- on 7/23/2017.
 */

public interface ContactDetailsView extends ContactableInteractiveView {

    void bind(ContactDetail contactDetail, List<ContactDetailUIItem> contactDetailUIItems);

    void callNumber(String phoneNumber);
}
