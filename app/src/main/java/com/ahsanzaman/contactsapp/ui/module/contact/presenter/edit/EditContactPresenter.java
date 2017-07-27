package com.ahsanzaman.contactsapp.ui.module.contact.presenter.edit;

import android.content.Context;

import com.ahsanzaman.contactsapp.data.repository.IContactsRepository;
import com.ahsanzaman.contactsapp.model.Contact;
import com.ahsanzaman.contactsapp.model.response.ContactDetailResponse;
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
    private final ContactDetailResponse mContactDetailResponse;

    @Inject
    public EditContactPresenter(IContactsService contactsService, Context activity, IContactsRepository contactsRepository, ContactDetailResponse contactDetailResponse) {
        super((BaseView) activity);
        mContactsService = contactsService;
        mEditContactView = (EditContactView) activity;
        mContactsRepository = contactsRepository;
        mContact = contactsRepository.getLocalRepository().getContactById(contactDetailResponse.getId());
        mContactDetailResponse = contactDetailResponse;
    }

    public void onSave() {
        mEditContactView.showLoading();
        mContactsService.editContactDetail(this, mContact.getId(), EDIT_CONTACT_REQUEST_CODE,mEditContactView.getContactDetail());
    }

    @Override
    public void onSuccess(Object responseObject, int requestCode) {
        super.onSuccess(responseObject, requestCode);
        if(responseObject instanceof ContactDetailResponse){
            ContactDetailResponse contactDetailResponse = (ContactDetailResponse) responseObject;
            mContact.setFirstName(contactDetailResponse.getFirstName());
            mContact.setLastName(contactDetailResponse.getLastName());
            mContact.setFavorite(contactDetailResponse.isFavorite());
            mContact.setProfilePic(contactDetailResponse.getProfilePic());
            mContactsRepository.getLocalRepository().updateContact(mContact);
            mEditContactView.finishOnSuccess(contactDetailResponse);
        } else {
         mEditContactView.showError("");
        }
    }
}
