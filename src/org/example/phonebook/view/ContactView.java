package org.example.phonebook.view;

import android.widget.TextView;

/**
 * Represents the displayable information about a contact.
 */
public class ContactView
{
    private final TextView name;
    private final TextView number;
    private final TextView type;

    /**
     * Sets the display fields for a contact.
     * 
     * @param name
     *            The contact name field.
     * @param number
     *            The contact phone number field.
     * @param type
     *            The contact phone number type field.
     */
    public ContactView(final TextView name, final TextView number, final TextView type)
    {
        this.name = name;
        this.number = number;
        this.type = type;
    }

    /**
     * Sets the contact name.
     * 
     * @param name
     *            The contact name.
     */
    public final void setName(final String name)
    {
        if (this.name != null)
        {
            this.name.setText(name);
        }
    }

    /**
     * Sets the contact phone number.
     * 
     * @param number
     *            The contact phone number.
     */
    public final void setNumber(final String number)
    {
        if (this.number != null)
        {
            this.number.setText(number);
        }
    }

    /**
     * Sets the contact phone number type.
     * 
     * @param type
     *            The contact phone number type.
     */
    public final void setType(final String type)
    {
        if (this.type != null)
        {
            this.type.setText(type);
        }
    }
}
