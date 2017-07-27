package com.ahsanzaman.contactsapp.ui.module.contact.presenter.edit;

import android.content.Context;

import com.ahsanzaman.contactsapp.data.repository.IContactsRepository;
import com.ahsanzaman.contactsapp.model.Contact;
import com.ahsanzaman.contactsapp.model.response.ContactDetail;
import com.ahsanzaman.contactsapp.network.service.IContactsService;
import com.ahsanzaman.contactsapp.ui.module.base.BasePresenter;
import com.ahsanzaman.contactsapp.ui.module.base.BaseView;
import com.ahsanzaman.contactsapp.ui.module.contact.view.edit.EditContactView;

import javax.inject.Inject;

/**
 * Created by Accolite- on 7/23/2017.
 */

public class EditContactPresenter extends BasePresenter{

    private static final int EDIT_CONTACT_REQUEST_CODE = 101;
    private final IContactsService mContactsService;
    private final EditContactView mEditContactView;
    private final IContactsRepository mContactsRepository;
    private final Contact mContact;
    private final ContactDetail mContactDetail;

    @Inject
    public EditContactPresenter(IContactsService contactsService, Context activity, IContactsRepository contactsRepository, ContactDetail contactDetail) {
        super((BaseView) activity);
        mContactsService = contactsService;
        mEditContactView = (EditContactView) activity;
        mContactsRepository = contactsRepository;
        mContact = contactsRepository.getLocalRepository().getContactById(contactDetail.getId());
        mContactDetail = contactDetail;
    }

    public void onSave() {
        mEditContactView.showLoading();
        mContactsService.editContactDetail(this, mContact.getId(), EDIT_CONTACT_REQUEST_CODE,mEditContactView.getContactDetail());
    }

    @Override
    public void onSuccess(Object responseObject, int requestCode) {
        super.onSuccess(responseObject, requestCode);
        if(responseObject instanceof ContactDetail){
            ContactDetail contactDetail = (ContactDetail) responseObject;
            mContact.setFirstName(contactDetail.getFirstName());
            mContact.setLastName(contactDetail.getLastName());
            mContact.setFavorite(contactDetail.isFavorite());
            mContact.setProfilePic(contactDetail.getProfilePic());
            mContactsRepository.getLocalRepository().updateContact(mContact);
            mEditContactView.finishOnSuccess(contactDetail);
        } else {
         mEditContactView.showError("");
        }
    }
}
