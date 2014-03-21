# 1. Introduction

This is a sample Android application that demonstrates using the [Smoothie library](https://github.com/lucasr/smoothie) created by Lucas Rocha.

This application iterates over all the device phonebook contacts and displays them in a `ListView`.


# 2. Smoothie Overview

Android applications that use `ListView`s or `GridView`s typically read the list or grid rows from a `Cursor`.  At times applications need to perform heavy operations for each item in the `Cursor`.  Heavy operations could include I/O intensive operations such as image rendering or remote calls such as web service invocations.

[Smoothie](https://github.com/lucasr/smoothie) was created to allow applications to offload all heavy operations on cursor items from the main UI thread in a simple and manageable way.  The key Smoothie component that enables this design is an `ItemLoader`.  An `ItemLoader` allows the application to perform one or more heavy operations away from the UI thread and load the UI incrementally.  This is facilitated by the following methods in this class.


#### 2.1. void displayItemPart(View view, T part, int position, boolean cached)

This method is invoked after some part of the UI has finished loading and is ready to be rendered.  `T` is a generic type and can vary from one application to another.  A sample implementation could be:

```
public void displayItemPart(View view, String part, int position, boolean cached)
{
  if(view == null || part === null)
  {
    return;
  }

  switch(position)
  {
    case 1:
      ((TextView) view.findViewById(R.id.name)).setText(part);
      break;
    case 2:
      ((TextView) view.findViewById(R.id.number)).setText(part);
      break;
  }
}
```


#### 2.2. T loadItemPart(P param, int position)

This method loads some part of the UI.  It is always invoked on a background thread so as not to block the main thread.  This is where the heavy operations should be performed.  A sample implementation could be:

```
public String loadItemPart(Long id, int position)
{
  switch(position)
  {
    case 1:
      // Return name.
    case 2:
      // Return number.
  }
}
```


#### 2.3. T loadItemPartFromMemory(P param, int position)

This method is called before `loadItemPart` and allows the application to load some part of the UI from an in-memory cache, if the application employs cacheable.  This is quite useful for avoiding heavy operations repeatedly.  A sample implementation could be:

```
public String loadItemPartFromMemory(Long id, int position)
{
  switch(position)
  {
    case 1:
      return NAME_CACHE.get(id);
    case 2:
      return NUMBER_CACHE.get(id);
  }
}
```

#### 2.4. P getItemParams(Adapter adapter, int position)

This method extracts the basic information from a `Cursor` using which the UI parts will be obtained.  The extracted information is returned as the generic type parameter `P`.  A sample implementation could be;

```
public Long getItemParams(Adapter adapter, int position)
{
  final Cursor = (Cursor) adapter.getItem(position);

  return cursor.getLong(cursor.getColumnIndex("id"));
}
```

Methods are called in the following order:

1. `getItemParams` - sets up the key based on which the item parts will be loaded;
1. `loadItemPartFromMemory` - attempts to load an item part from memory, if cached;
1. `loadItemPart` - loads an item part, typically through a complicated operation;
1. `displayItemPart` - displays an item part.
