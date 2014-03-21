package org.example.phonebook.adapter;

import org.example.phonebook.R;
import org.example.phonebook.view.ContactView;

import android.content.Context;
import android.support.v4.widget.SimpleCursorAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;

/**
 * Allows reading {@link ContactView} data into a {@link ListView} or a
 * {@link GridView}.
 */
public class ContactAdapter extends SimpleCursorAdapter
{
    /**
     * Sets the context for this adapter.
     * 
     * @param context
     *            A {@link Context}.
     */
    public ContactAdapter(final Context context)
    {
        super(context, -1, null, new String[] {}, new int[] {}, 0);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public View getView(final int position, View view, final ViewGroup parent)
    {
        if (view == null)
        {
            // Create a view from the layout.
            view = LayoutInflater.from(mContext).inflate(R.layout.contact, parent, false);

            // Add a view holder that can be used to populate the view when the
            // content has loaded.
            view.setTag(new ContactView((TextView) view.findViewById(R.id.name), (TextView) view.findViewById(R.id.number), (TextView) view
                    .findViewById(R.id.type)));
        }

        return view;
    }
}
