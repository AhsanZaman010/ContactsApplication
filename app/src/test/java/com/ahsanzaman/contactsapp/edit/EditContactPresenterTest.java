package com.ahsanzaman.contactsapp.edit;

import android.util.Log;

import com.ahsanzaman.contactsapp.data.repository.IContactsRepository;
import com.ahsanzaman.contactsapp.data.repository.ILocalRepository;
import com.ahsanzaman.contactsapp.model.Contact;
import com.ahsanzaman.contactsapp.model.ContactDetailUIItem;
import com.ahsanzaman.contactsapp.model.response.ContactDetail;
import com.ahsanzaman.contactsapp.network.callback.RemoteServiceCallback;
import com.ahsanzaman.contactsapp.ui.module.contact.presenter.edit.EditContactPresenter;
import com.ahsanzaman.contactsapp.ui.module.contact.view.edit.EditContactView;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.powermock.api.mockito.PowerMockito;

import java.util.List;

import static com.ahsanzaman.contactsapp.ui.module.contact.presenter.edit.EditContactPresenter.EDIT_CONTACT_REQUEST_CODE;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by Accolite- on 8/6/2017.
 */

public class EditContactPresenterTest {
    private static ContactDetail CONTACT_DETAIL = new ContactDetail(100001, "Test", "Name", "", "", "", true);

    private static long ID = 100001;

    private static Contact CONTACT = new Contact(100001, "Test", "Name", true);

    @Mock
    private IContactsRepository mContactsRepository;

    @Mock
    private ILocalRepository mLocalRepository;

    @Mock
    private EditContactView mEditContactView;

    @Captor
    private ArgumentCaptor<RemoteServiceCallback> mLoadContactsCallbackCaptor;

    private EditContactPresenter mEditContactPresenter;

    @Before
    public void setupContactsPresenter() {
        MockitoAnnotations.initMocks(this);

        when(mContactsRepository.getLocalRepository()).thenReturn(mLocalRepository);
        when(mLocalRepository.getContactById(ID)).thenReturn(CONTACT);
        mEditContactPresenter = new EditContactPresenter(mEditContactView,  mContactsRepository, CONTACT_DETAIL);
    }

    @Test
    public void makeEditContactDetailAndFinishOnSuccessTest() {
        PowerMockito.mockStatic(Log.class);
        when(mContactsRepository.getLocalRepository()).thenReturn(mLocalRepository);
        when(mLocalRepository.getContactById(ID)).thenReturn(CONTACT);
        when(mLocalRepository.getContactById(CONTACT.getId())).thenReturn(CONTACT);
        when(mEditContactView.getContactDetail()).thenReturn(CONTACT_DETAIL);


        mEditContactPresenter.onSave();

        verify(mContactsRepository).editContactDetail(mLoadContactsCallbackCaptor.capture(), eq(ID), eq(EDIT_CONTACT_REQUEST_CODE), eq(CONTACT_DETAIL));
        mLoadContactsCallbackCaptor.getValue().onSuccess(CONTACT_DETAIL, EDIT_CONTACT_REQUEST_CODE);

        verify(mEditContactView).finishOnSuccess(CONTACT_DETAIL);
        verify(mLocalRepository).updateContact(CONTACT);
    }
}
