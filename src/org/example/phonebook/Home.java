package org.example.phonebook;

import org.example.phonebook.adapter.ContactAdapter;
import org.example.phonebook.loader.ContactLoader;
import org.example.phonebook.model.Contact;
import org.lucasr.smoothie.AsyncListView;
import org.lucasr.smoothie.ItemManager.Builder;

import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract.CommonDataKinds.Phone;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.LoaderManager.LoaderCallbacks;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.view.Menu;

/**
 * Home activity.
 */
public class Home extends FragmentActivity implements LoaderCallbacks<Cursor>
{
    private static final int CONTACT_LOADER = -1;

    private ContactAdapter   adapter;

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.home);

        this.adapter = new ContactAdapter(this);

        final ContactLoader loader = new ContactLoader();

        final Builder builder = new Builder(loader);
        builder.setPreloadItemsCount(20);
        builder.setPreloadItemsEnabled(true);

        final AsyncListView contacts = (AsyncListView) this.findViewById(R.id.contacts);
        contacts.setAdapter(this.adapter);
        contacts.setItemManager(builder.build());

        this.getSupportLoaderManager().initLoader(CONTACT_LOADER, null, this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        this.getMenuInflater().inflate(R.menu.home, menu);

        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Loader<Cursor> onCreateLoader(final int loader, final Bundle bundle)
    {
        if (loader == CONTACT_LOADER)
        {
            return new CursorLoader(Home.this, Phone.CONTENT_URI, new String[] { Contact.ID, Contact.NAME, Contact.NUMBER, Contact.TYPE }, null, null, Contact.NAME);
        }

        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onLoadFinished(final Loader<Cursor> loader, final Cursor cursor)
    {
        this.adapter.changeCursor(cursor);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onLoaderReset(final Loader<Cursor> loader)
    {
        this.adapter.changeCursor(null);
    }
}
