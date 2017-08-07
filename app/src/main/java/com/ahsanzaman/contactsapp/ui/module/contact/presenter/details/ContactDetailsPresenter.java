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

import io.reactivex.disposables.Disposable;

/**
 * Created by Accolite- on 7/23/2017.
 */

public class ContactDetailsPresenter extends BasePresenter implements RemoteServiceCallback{

    private static final int CONTACT_DETAIL_REQUEST_CODE = 101;
    public static final int TOGGLE_FAVOURITE_API = 102;
    private final ContactDetailsView mContactDetailsView;
    private final long mContactID;
    private Contact mContact;
    private final IContactsRepository mContactRepository;
    private ContactDetail mContactDetail;

    @Inject
    public ContactDetailsPresenter(ContactDetailsView contactDetailsView, Long contactID, IContactsRepository contactsRepository) {
        super(contactDetailsView);
        mContactDetailsView = contactDetailsView;
        mContactRepository = contactsRepository;
        mContactID = contactID;
        getContact();
    }

    public void getContact(){
        if(mContactID < 1){
            mContactDetailsView.showInvalidContactAndExit();
        }
        mContact = mContactRepository.getLocalRepository().getContactById(mContactID);
    }

    public void reloadAPI(int requestCode){
        switch (requestCode){
            case CONTACT_DETAIL_REQUEST_CODE:
                getContactDetails();
                break;
            case TOGGLE_FAVOURITE_API:
                toggleFavourite();
                break;
        }
    }

    public void getContactDetails(){
        Disposable disposable = mContactRepository.getContactDetail(this, mContact.getId(), CONTACT_DETAIL_REQUEST_CODE);
        if(mCompositeDisposable!=null) {
            mCompositeDisposable.add(disposable);
        }
    }

    public void toggleFavourite() {
        boolean isFavourite = mContact.isFavorite();
        ContactDetail contactDetail = new ContactDetail();
        contactDetail.setId(mContact.getId());
        contactDetail.setFavorite(!isFavourite);
        mContactRepository.editContactDetail(this,mContact.getId(), TOGGLE_FAVOURITE_API, contactDetail );
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
        if(responseObject!=null && responseObject instanceof ContactDetail) {
            ContactDetail contactDetail = (ContactDetail) responseObject;
            setContactDetail(contactDetail);
            switch (requestCode) {
                case CONTACT_DETAIL_REQUEST_CODE:
                    mContactDetailsView.hideLoading();
                    setResponse(contactDetail);
                    break;

                case TOGGLE_FAVOURITE_API:
                    mContactDetailsView.hideLoading();
                    mContact.setFavorite(contactDetail.isFavorite());
                    mContactRepository.getLocalRepository().updateContact(mContact);
                    mContactDetailsView.showFavourite(contactDetail.isFavorite());
                break;
            }
        }
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

    public void setContactDetail(ContactDetail contactDetail){
        mContactDetail = contactDetail;
    }
}
