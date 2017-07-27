package com.ahsanzaman.contactsapp.ui.module.contact.presenter.add;

import android.content.Context;

import com.ahsanzaman.contactsapp.data.repository.IContactsRepository;
import com.ahsanzaman.contactsapp.model.response.ContactDetail;
import com.ahsanzaman.contactsapp.network.service.IContactsService;
import com.ahsanzaman.contactsapp.ui.module.base.BasePresenter;
import com.ahsanzaman.contactsapp.ui.module.base.BaseView;
import com.ahsanzaman.contactsapp.ui.module.contact.view.add.AddContactView;

import javax.inject.Inject;

/**
 * Created by Accolite- on 7/23/2017.
 */

public class AddContactPresenter extends BasePresenter {

    private static final int ADD_CONTACT_REQUEST_CODE = 101;
    private final IContactsService mContactsService;
    private final AddContactView mContactsView;
    private final IContactsRepository mContactsRepository;

    @Inject
    public AddContactPresenter(IContactsService contactsService, Context context, IContactsRepository contactsRepository) {
        super((BaseView) context);
        mContactsService = contactsService;
        mContactsView = (AddContactView) context;
        mContactsRepository = contactsRepository;
    }

    public void onSave() {
        mContactsView.showLoading();
        mContactsService.addContactDetail(this, ADD_CONTACT_REQUEST_CODE, mContactsView.getContactDetail());
    }

    @Override
    public void onSuccess(Object responseObject, int requestCode) {
        super.onSuccess(responseObject, requestCode);
        if(responseObject instanceof ContactDetail){
            mContactsRepository.getLocalRepository().clearContacts();
            mContactsView.finishOnSuccess();
        } else {
            mContactsView.showError("");
        }
    }
}
