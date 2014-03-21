package org.example.phonebook.loader;

import org.example.phonebook.model.Contact;
import org.example.phonebook.view.ContactView;
import org.lucasr.smoothie.SimpleItemLoader;

import android.database.Cursor;
import android.view.View;
import android.widget.Adapter;

/**
 * Loads contact information asynchronously so that the loading operation does
 * not impact the
 */
public final class ContactLoader extends SimpleItemLoader<Contact, Contact>
{
    /**
     * {@inheritDoc}
     */
    public Contact getItemParams(final Adapter adapter, final int position)
    {
        final Cursor cursor = (Cursor) adapter.getItem(position);

        return new Contact(cursor.getString(cursor.getColumnIndex(Contact.NAME)), cursor.getString(cursor.getColumnIndex(Contact.NUMBER)), cursor.getInt(cursor
                .getColumnIndex(Contact.TYPE)));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void displayItem(final View view, final Contact model, final boolean cached)
    {
        if (model == null || view == null)
        {
            return;
        }

        final ContactView contact = (ContactView) view.getTag();

        if (contact != null)
        {
            contact.setName(model.getName());
            contact.setNumber(model.getNumber());
            contact.setType(model.getType());
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Contact loadItem(final Contact model)
    {
        return model;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Contact loadItemFromMemory(final Contact model)
    {
        return model;
    }
}
