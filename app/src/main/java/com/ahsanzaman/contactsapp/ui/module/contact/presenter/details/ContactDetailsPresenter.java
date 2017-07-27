package com.ahsanzaman.contactsapp.ui.module.contact.presenter.details;

import android.accounts.NetworkErrorException;
import android.content.Context;

import com.ahsanzaman.contactsapp.data.repository.IContactsRepository;
import com.ahsanzaman.contactsapp.model.Contact;
import com.ahsanzaman.contactsapp.model.ContactDetailUIItem;
import com.ahsanzaman.contactsapp.model.response.ContactDetail;
import com.ahsanzaman.contactsapp.network.callback.RemoteServiceCallback;
import com.ahsanzaman.contactsapp.ui.module.base.BasePresenter;
import com.ahsanzaman.contactsapp.ui.module.base.BaseView;
import com.ahsanzaman.contactsapp.ui.module.contact.view.details.ContactDetailsView;
import com.ahsanzaman.contactsapp.utils.ContactUtils;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by Accolite- on 7/23/2017.
 */

public class ContactDetailsPresenter extends BasePresenter implements RemoteServiceCallback{

    private static final int CONTACT_DETAIL_REQUEST_CODE = 101;
    private final ContactDetailsView mContactDetailsView;
    private final Contact mContact;
    private final IContactsRepository mContactRepository;
    private ContactDetail mContactDetail;

    @Inject
    public ContactDetailsPresenter(Context context, Long contactID, IContactsRepository contactsRepository) {
        super((BaseView) context);
        mContactDetailsView = (ContactDetailsView) context;
        mContact = contactsRepository.getLocalRepository().getContactById(contactID);
        mContactRepository = contactsRepository;
    }

    public void getContactDetails(){
        mCompositeDisposable.add(mContactRepository.getContactDetail(this, mContact.getId(), CONTACT_DETAIL_REQUEST_CODE));
    }

    public boolean toggleFavourite() {
        mContact.setFavorite(!mContact.isFavorite());
        mContactRepository.getLocalRepository().updateContact(mContact);
        return mContact.isFavorite();
    }

    public boolean isFavourite() {
        if(mContact!=null)
        return mContact.isFavorite();
        return false;
    }

    public String getName() {
        if(mContact!=null){
            return mContact.getFirstName()+" "+ mContact.getLastName();
        }
        return "";
    }

    private void setResponse(final ContactDetail contactDetail){
        mContactDetail = contactDetail;
        List<ContactDetailUIItem> contactDetailsUIItems = ContactUtils.getContactDetailsUIItems(contactDetail, mContactDetailsView);
        mContactDetailsView.bind(contactDetail, contactDetailsUIItems);
    }



    @Override
    public void onSuccess(Object responseObject, int requestCode) {
        ContactDetail contactDetail = (ContactDetail) responseObject;
        mContactDetailsView.hideLoading();
        setResponse(contactDetail);
    }

    @Override
    public void onError(NetworkErrorException networkError, int requestCode) {
        mContactDetailsView.hideLoading();
        mContactDetailsView.showError(networkError.getMessage());
    }

    public void sendSMS() {
        if(null != mContactDetail) {
            mContactDetailsView.sendSMS(mContactDetail.getPhoneNumber());
        }
    }

    public void sendEmail() {
        if(null != mContactDetail) {
            mContactDetailsView.sendEmail(mContactDetail.getEmail());
        }
    }

    public void call() {
        if(null != mContactDetail) {
            mContactDetailsView.callNumber(mContactDetail.getPhoneNumber());
        }
    }

    public void dial() {
        if(null != mContactDetail) {
            mContactDetailsView.dial(mContactDetail.getPhoneNumber());
        }
    }
}
