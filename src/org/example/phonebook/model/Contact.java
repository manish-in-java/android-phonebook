package org.example.phonebook.model;

import android.provider.ContactsContract.CommonDataKinds.Phone;

/**
 * Represents a contact.
 */
public final class Contact
{
    public static final String  ID     = Phone._ID;
    public static final String  NAME   = Phone.DISPLAY_NAME;
    public static final String  NUMBER = Phone.NUMBER;
    public static final String  TYPE   = Phone.TYPE;

    private static final String FAX    = "fax: ";
    private static final String HOME   = "home: ";
    private static final String MOBILE = "mobile: ";
    private static final String OTHER  = "other: ";
    private static final String WORK   = "work: ";

    private final String        name;
    private final String        number;
    private final String        type;

    /**
     * Sets the contact fields.
     * 
     * @param name
     *            The contact name.
     * @param number
     *            The contact phone number.
     * @param type
     *            The contact phone number type.
     */
    public Contact(final String name, final String number, final int type)
    {
        this.name = name;
        this.number = number;

        switch (type)
        {
            case Phone.TYPE_FAX_HOME:
            case Phone.TYPE_FAX_WORK:
            case Phone.TYPE_OTHER_FAX:
                this.type = FAX;
                break;

            case Phone.TYPE_HOME:
                this.type = HOME;
                break;

            case Phone.TYPE_MOBILE:
                this.type = MOBILE;
                break;

            case Phone.TYPE_COMPANY_MAIN:
            case Phone.TYPE_WORK:
            case Phone.TYPE_WORK_MOBILE:
            case Phone.TYPE_WORK_PAGER:
                this.type = WORK;
                break;

            default:
                this.type = OTHER;
                break;
        }
    }

    /**
     * Gets the contact name.
     * 
     * @return The contact name.
     */
    public final String getName()
    {
        return this.name;
    }

    /**
     * Gets the contact phone number.
     * 
     * @return The contact phone number.
     */
    public final String getNumber()
    {
        return this.number;
    }

    /**
     * Gets the contact phone number type.
     * 
     * @return The contact phone number type.
     */
    public final String getType()
    {
        return this.type;
    }
}
