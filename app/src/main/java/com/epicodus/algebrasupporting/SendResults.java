package com.epicodus.algebrasupporting;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;

/**
 * Created by rodnier.borrego on 3/27/18.
 */

//public class SendResults {
//    static final int PICK_CONTACT_REQUEST = 1;  // The request code
//
//    private void pickContact() {
//        Intent pickContactIntent = new Intent(Intent.ACTION_PICK, Uri.parse("content://contacts"));
//        pickContactIntent.setType(Phone.CONTENT_TYPE); // Show user only contacts w/ phone numbers
//        startActivityForResult(pickContactIntent, PICK_CONTACT_REQUEST);
//    }
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        // Check which request it is that we're responding to
//        if (requestCode == PICK_CONTACT_REQUEST) {
//            // Make sure the request was successful
//            if (resultCode == RESULT_OK) {
//                // Get the URI that points to the selected contact
//                Uri contactUri = data.getData();
//                // We only need the NUMBER column, because there will be only one row in the result
//                String[] projection = {Phone.NUMBER};
//
//                // Perform the query on the contact to get the NUMBER column
//                // We don't need a selection or sort order (there's only one result for the given URI)
//                // CAUTION: The query() method should be called from a separate thread to avoid blocking
//                // your app's UI thread. (For simplicity of the sample, this code doesn't do that.)
//                // Consider using CursorLoader to perform the query.
//                Cursor cursor = getContentResolver()
//                        .query(contactUri, projection, null, null, null);
//                cursor.moveToFirst();
//
//                // Retrieve the phone number from the NUMBER column
//                int column = cursor.getColumnIndex(Phone.NUMBER);
//                String number = cursor.getString(column);
//
//                // Do something with the phone number...
//            }
//        }
//    }
//
//}
