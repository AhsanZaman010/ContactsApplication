package com.ahsanzaman.contactsapp;

import com.ahsanzaman.contactsapp.data.repository.ContactsRepository;
import com.ahsanzaman.contactsapp.data.repository.IContactsRepository;
import com.ahsanzaman.contactsapp.model.Contact;
import com.ahsanzaman.contactsapp.network.callback.RemoteServiceCallback;
import com.ahsanzaman.contactsapp.ui.module.contact.presenter.ContactsPresenter;
import com.ahsanzaman.contactsapp.ui.module.contact.view.ContactsView;
import com.google.common.collect.Lists;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.verify;

/**
 * Created by Ahsan Zaman on 27-07-2017.
 */

public class ContactsPresenterTest{

    private static List<Contact> CONTACTS = Lists.newArrayList(new Contact(100001, "Test", "Name", true),
            new Contact(100002, "Test1", "Name", false));

    private static List<Contact> EMPTY_CONTACTS = new ArrayList<>(0);

    @Mock
    private IContactsRepository mContactsRepository;

    @Mock
    private ContactsView mContactsView;

    @Captor
    private ArgumentCaptor<RemoteServiceCallback> mLoadContactsCallbackCaptor;

    private ContactsPresenter mContactsPresenter;

    @Before
    public void setupContactsPresenter() {
        // Mockito has a very convenient way to inject mocks by using the @Mock annotation. To
        // inject the mocks in the test the initMocks method needs to be called.
        MockitoAnnotations.initMocks(this);

        // Get a reference to the class under test
        mContactsPresenter = new ContactsPresenter(mContactsView, mContactsRepository);
    }

    @Test
    public void loadContactsFromRepositoryAndLoadIntoView() {
        // Given an initialized ContactsPresenter with initialized notes
        // When loading of Contacts is requested
        mContactsPresenter.loadContacts(true);

    }
}