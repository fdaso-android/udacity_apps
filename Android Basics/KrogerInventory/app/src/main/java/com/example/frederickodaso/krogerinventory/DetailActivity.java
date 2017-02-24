package com.example.frederickodaso.krogerinventory;

import android.app.AlertDialog;
import android.app.LoaderManager;
import android.content.ContentValues;
import android.content.CursorLoader;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import com.example.frederickodaso.krogerinventory.data.StockContract;

import static com.example.frederickodaso.krogerinventory.R.string.baby;

public class DetailActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {

    private EditText mSupplierFieldText;
    private EditText mNameFieldText;
    private EditText mPriceFieldText;
    private EditText mQuantityFieldText;
    private ImageView mCategoryImage;
    private Spinner mCategoryType;
    private Button mIncrement;
    private Button mDecrement;
    private Button mOrderMore;
    private Button mUpdateItem;
    private Button mDeleteItem;
    private Button mAddItem;
    private int mItemType = StockContract.StockEntry.TYPE_MISC;

    private boolean mItemHasChanged = false;

    private Uri mItemContentUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        // Bind views
        mSupplierFieldText = (EditText) findViewById(R.id.detailSupplier_editText);
        mNameFieldText = (EditText) findViewById(R.id.detailName_editText);
        mPriceFieldText = (EditText) findViewById(R.id.detailPrice_editText);
        mQuantityFieldText = (EditText) findViewById(R.id.detailQuantity_editText);
        mSupplierFieldText.setOnTouchListener(mTouchListener);
        mNameFieldText.setOnTouchListener(mTouchListener);
        mPriceFieldText.setOnTouchListener(mTouchListener);
        mQuantityFieldText.setOnTouchListener(mTouchListener);
        mCategoryImage = (ImageView) findViewById(R.id.category_imageView);
        mCategoryImage.setImageResource(R.drawable.images);
        mCategoryType = (Spinner) findViewById(R.id.spinner_item);
        mIncrement = (Button) findViewById(R.id.increase_Button);

        mIncrement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mQuantityFieldText.getText().toString().trim().equals("")) {
                    mQuantityFieldText.setText("0");
                }
                int currentQuantity = Integer.parseInt(mQuantityFieldText.getText().toString().trim());
                currentQuantity += 1;
                mQuantityFieldText.setText("" + currentQuantity);
            }
        });

        mDecrement = (Button) findViewById(R.id.decrease_Button);

        mDecrement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mQuantityFieldText.getText().toString().trim().equals("")) {
                    mQuantityFieldText.setText("0");
                }
                int currentQuantity = Integer.parseInt(mQuantityFieldText.getText().toString().trim());
                if (currentQuantity == 0) {
                    currentQuantity = 0;
                } else {
                    currentQuantity -= 1;
                }
                mQuantityFieldText.setText("" + currentQuantity);
            }
        });
        mOrderMore = (Button) findViewById(R.id.orderMore_Button);
        mUpdateItem = (Button) findViewById(R.id.updateItem_Button);

        mUpdateItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateItem();
                finish();
            }
        });

        mAddItem = (Button) findViewById(R.id.addItem_Button);

        mAddItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addItem();
                finish();
            }
        });

        mDeleteItem = (Button) findViewById(R.id.deleteItem_Button);

        mDeleteItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDeleteConfirmationDialog();
            }
        });

        mItemContentUri = getIntent().getData();
        if (mItemContentUri != null) {
            setTitle("Edit An Item");
            mAddItem.setVisibility(View.GONE);
            mDeleteItem.setVisibility(View.VISIBLE);
            mOrderMore.setVisibility(View.VISIBLE);
            mUpdateItem.setVisibility(View.VISIBLE);
        } else {
            setTitle("Add An Item");
            mDeleteItem.setVisibility(View.GONE);
            mOrderMore.setVisibility(View.GONE);
            mUpdateItem.setVisibility(View.GONE);
            mAddItem.setVisibility(View.VISIBLE);
        }

        getLoaderManager().initLoader(0,null, this);

        setupSpinner();
    }

    public void sendOrder(View view) {
        Intent sendOrderIntent = new Intent(Intent.ACTION_SENDTO);
        sendOrderIntent.setData(Uri.parse("mailto:"));
        sendOrderIntent.putExtra(Intent.EXTRA_EMAIL, "fakeemail123@gmail.com");
        sendOrderIntent.putExtra(Intent.EXTRA_SUBJECT, "Requesting Another Order of Goods");
        sendOrderIntent.putExtra(Intent.EXTRA_TEXT, "I would like to order another shipment of the goods.");
        startActivity(sendOrderIntent);
    }

    private void setupSpinner() {
        // Create adapter for spinner. The list options are from the String array it will use
        // the spinner will use the default layout
        ArrayAdapter itemSpinnerAdapter = ArrayAdapter.createFromResource(this,
                R.array.item_category_options, android.R.layout.simple_spinner_item);

        // Specify dropdown layout style - simple list view with 1 item per line
        itemSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);

        // Apply the adapter to the spinner
        mCategoryType.setAdapter(itemSpinnerAdapter);

        // Set the integer mSelected to the constant values
        mCategoryType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selection = (String) parent.getItemAtPosition(position);
                if (!TextUtils.isEmpty(selection)) {
                    if (selection.equals(getString(baby))) {
                        mItemType = StockContract.StockEntry.TYPE_BABY_ITEMS; // Baby Items
                        mCategoryImage.setImageResource(R.drawable.baby_items);
                        Log.v("DetailActivity", "Code Entered 1");
                    } else if (selection.equals(getString(R.string.baking))) {
                        mItemType = StockContract.StockEntry.TYPE_BAKING; // Baking
                        mCategoryImage.setImageResource(R.drawable.baking);
                        Log.v("DetailActivity", "Code Entered 2");
                    } else if (selection.equals(getString(R.string.beverages))) {
                        mItemType = StockContract.StockEntry.TYPE_BEVERAGES; // Beverages
                        mCategoryImage.setImageResource(R.drawable.beverages);
                        Log.v("DetailActivity", "Code Entered 3");
                    } else if (selection.equals(getString(R.string.bread))) {
                        mItemType = StockContract.StockEntry.TYPE_BREAD_BAKERY; // Bread
                        mCategoryImage.setImageResource(R.drawable.bread);
                        Log.v("DetailActivity", "Code Entered 4");
                    } else if (selection.equals(getString(R.string.canned))) {
                        mItemType = StockContract.StockEntry.TYPE_CANNED_GOODS; // Canned Goods
                        mCategoryImage.setImageResource(R.drawable.canned_food);
                        Log.v("DetailActivity", "Code Entered 5");
                    } else if (selection.equals(getString(R.string.cereal))) {
                        mItemType = StockContract.StockEntry.TYPE_CEREAL; // Cereal
                        mCategoryImage.setImageResource(R.drawable.cereal);
                        Log.v("DetailActivity", "Code Entered 6");
                    } else if (selection.equals(getString(R.string.dairy))) {
                        mItemType = StockContract.StockEntry.TYPE_DAIRY; // Dairy
                        mCategoryImage.setImageResource(R.drawable.dairy);
                        Log.v("DetailActivity", "Code Entered 7");
                    } else if (selection.equals(getString(R.string.deli))) {
                        mItemType = StockContract.StockEntry.TYPE_DELI; // Deli
                        mCategoryImage.setImageResource(R.drawable.deli);
                        Log.v("DetailActivity", "Code Entered 8");
                    } else if (selection.equals(getString(R.string.frozen))) {
                        mItemType = StockContract.StockEntry.TYPE_FROZEN_FOODS; // Frozen Foods
                        mCategoryImage.setImageResource(R.drawable.frozen_dinner);
                        Log.v("DetailActivity", "Code Entered 9");
                    } else if (selection.equals(getString(R.string.meats))) {
                        mItemType = StockContract.StockEntry.TYPE_MEATS; // Eating
                        mCategoryImage.setImageResource(R.drawable.meat);
                        Log.v("DetailActivity", "Code Entered 10");
                    } else {
                       mItemType = StockContract.StockEntry.TYPE_MISC; // Misc.
                       mCategoryImage.setImageResource(R.drawable.images);
                    }
                }
            }

            // Because AdapterView is an abstract class, onNothingSelected must be defined
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                mItemType = StockContract.StockEntry.TYPE_MISC; // Misc.
            }
        });
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        String[] projection = {
                StockContract.StockEntry._ID,
                StockContract.StockEntry.COLUMN_STOCK_SUPPLIER,
                StockContract.StockEntry.COLUMN_STOCK_NAME,
                StockContract.StockEntry.COLUMN_STOCK_PRICE,
                StockContract.StockEntry.COLUMN_STOCK_QUANTITY,
                StockContract.StockEntry.COLUMN_STOCK_CATEGORY,
                StockContract.StockEntry.COLUMN_STOCK_SOLD
        };
        if (mItemContentUri != null) {
            return new CursorLoader(this,
                    mItemContentUri,
                    projection,
                    null,
                    null,
                    null);
        }
        return null;
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        if (data.moveToFirst()) {
            mSupplierFieldText.setText(data.getString(data.getColumnIndexOrThrow("supplier")));
            mNameFieldText.setText(data.getString(data.getColumnIndexOrThrow("name")));
            mQuantityFieldText.setText(Integer.toString(data.getInt(data.getColumnIndexOrThrow("quantity"))));
            mPriceFieldText.setText(Integer.toString(data.getInt(data.getColumnIndexOrThrow("price"))));
            int type = data.getInt(data.getColumnIndexOrThrow("category"));

            switch (type) {
                case StockContract.StockEntry.TYPE_BABY_ITEMS:
                    mCategoryType.setSelection(0);
                    mCategoryImage.setImageResource(R.drawable.baby_items);
                    break;
                case StockContract.StockEntry.TYPE_BAKING:
                    mCategoryType.setSelection(1);
                    mCategoryImage.setImageResource(R.drawable.baking);
                    break;
                case StockContract.StockEntry.TYPE_BEVERAGES:
                    mCategoryType.setSelection(2);
                    mCategoryImage.setImageResource(R.drawable.beverages);
                    break;
                case StockContract.StockEntry.TYPE_BREAD_BAKERY:
                    mCategoryType.setSelection(3);
                    mCategoryImage.setImageResource(R.drawable.bread);
                    break;
                case StockContract.StockEntry.TYPE_CANNED_GOODS:
                    mCategoryType.setSelection(4);
                    mCategoryImage.setImageResource(R.drawable.canned_food);
                    break;
                case StockContract.StockEntry.TYPE_CEREAL:
                    mCategoryType.setSelection(5);
                    mCategoryImage.setImageResource(R.drawable.cereal);
                    break;
                case StockContract.StockEntry.TYPE_DAIRY:
                    mCategoryType.setSelection(6);
                    mCategoryImage.setImageResource(R.drawable.dairy);
                    break;
                case StockContract.StockEntry.TYPE_DELI:
                    mCategoryType.setSelection(7);
                    mCategoryImage.setImageResource(R.drawable.deli);
                    break;
                case StockContract.StockEntry.TYPE_FROZEN_FOODS:
                    mCategoryType.setSelection(8);
                    mCategoryImage.setImageResource(R.drawable.frozen_dinner);
                    break;
                case StockContract.StockEntry.TYPE_MEATS:
                    mCategoryType.setSelection(9);
                    mCategoryImage.setImageResource(R.drawable.meat);
                    break;
                default:
                    mCategoryType.setSelection(10);
                    mCategoryImage.setImageResource(R.drawable.images);
                    break;
            }
        }
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        mSupplierFieldText.setText("");
        mNameFieldText.setText("");
        mPriceFieldText.setText("");
        mQuantityFieldText.setText("");
        mCategoryType.setSelection(10);
    }

    private View.OnTouchListener mTouchListener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {
            mItemHasChanged = true;
            return false;
        }
    };

    private void showUnsavedChangesDialog(
            DialogInterface.OnClickListener discardButtonClickListener) {
        // Create an AlertDialog.Builder and set the message, and click listeners
        // for the positive and negative buttons on the dialog.
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(R.string.unsaved_changes_dialog_msg);
        builder.setPositiveButton(R.string.discard, discardButtonClickListener);
        builder.setNegativeButton(R.string.keep_editing, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // User clicked the "Keep editing" button, so dismiss the dialog
                // and continue editing the item.
                if (dialog != null) {
                    dialog.dismiss();
                }
            }
        });

        // Create and show the AlertDialog
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    @Override
    public void onBackPressed() {
        // If the item hasn't changed, continue with handling back button press
        if (!mItemHasChanged) {
            super.onBackPressed();
            return;
        }

        // Otherwise if there are unsaved changes, setup a dialog to warn the user.
        // Create a click listener to handle the user confirming that changes should be discarded.
        DialogInterface.OnClickListener discardButtonClickListener =
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // User clicked "Discard" button, close the current activity.
                        finish();
                    }
                };

        // Show dialog that there are unsaved changes
        showUnsavedChangesDialog(discardButtonClickListener);
    }

    private void showDeleteConfirmationDialog() {
        // Create an AlertDialog.Builder and set the message, and click listeners
        // for the positive and negative buttons on the dialog.
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(R.string.delete_dialog_msg);
        builder.setPositiveButton(R.string.delete, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // User clicked the "Delete" button, so delete the pet.
                deleteItem();
            }
        });
        builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // User clicked the "Cancel" button, so dismiss the dialog
                // and continue editing the pet.
                if (dialog != null) {
                    dialog.dismiss();
                }
            }
        });

        // Create and show the AlertDialog
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    private void deleteItem() {
        if (mItemContentUri != null) {
            // Call the ContentResolver to delete the pet at the given content URI.
            // Pass in null for the selection and selection args because the mCurrentPetUri
            // content URI already identifies the pet that we want.
            int rowsDeleted = getContentResolver().delete(mItemContentUri, null, null);

//            // Show a toast message depending on whether or not the delete was successful.
//            if (rowsDeleted == 0) {
//                // If no rows were deleted, then there was an error with the delete.
//                Toast.makeText(this, getString(R.string.editor_delete_pet_failed),
//                        Toast.LENGTH_SHORT).show();
//            } else {
//                // Otherwise, the delete was successful and we can display a toast.
//                Toast.makeText(this, getString(R.string.editor_delete_pet_successful),
//                        Toast.LENGTH_SHORT).show();
//            }
            finish();
        }
    }

    private void addItem() {

        String supplierString = mSupplierFieldText.getText().toString().trim();
        String nameString = mNameFieldText.getText().toString().trim();
        String priceString = mPriceFieldText.getText().toString().trim();
        String quantityString = mQuantityFieldText.getText().toString().trim();
        int itemType = mItemType;
        Log.v("DetailActivity", "1:" + supplierString);
        Log.v("DetailActivity", "2:" + nameString);
        Log.v("DetailActivity", "3:" + priceString);
        Log.v("DetailActivity", "4:" + quantityString);

        if (mItemContentUri == null &&
                TextUtils.isEmpty(nameString) && TextUtils.isEmpty(supplierString) &&
                TextUtils.isEmpty(priceString) && TextUtils.isEmpty(quantityString)) {
            Log.v("DetailActivity", "CODE ENTERED");
            return;
        }

        ContentValues values = new ContentValues();
        values.put(StockContract.StockEntry.COLUMN_STOCK_SUPPLIER, supplierString);
        values.put(StockContract.StockEntry.COLUMN_STOCK_NAME, nameString);
        values.put(StockContract.StockEntry.COLUMN_STOCK_CATEGORY, itemType);

        int price = 0;
        if (!TextUtils.isEmpty(priceString)) {
            price = Integer.parseInt(priceString);
        }
        values.put(StockContract.StockEntry.COLUMN_STOCK_PRICE, price);

        int quantity = 0;
        if (!TextUtils.isEmpty(quantityString)) {
            quantity = Integer.parseInt(quantityString);
        }
        values.put(StockContract.StockEntry.COLUMN_STOCK_QUANTITY, quantity);

        Uri newUri = getContentResolver().insert(StockContract.StockEntry.CONTENT_URI, values);
    }

    private void updateItem() {
        String supplierString = mSupplierFieldText.getText().toString().trim();
        String nameString = mNameFieldText.getText().toString().trim();
        String priceString = mPriceFieldText.getText().toString().trim();
        String quantityString = mQuantityFieldText.getText().toString().trim();
        int itemType = mItemType;

        if (mItemContentUri == null &&
                TextUtils.isEmpty(nameString) && TextUtils.isEmpty(supplierString) &&
                TextUtils.isEmpty(priceString) && TextUtils.isEmpty(quantityString)) {
            return;
        }

        ContentValues values = new ContentValues();
        values.put(StockContract.StockEntry.COLUMN_STOCK_SUPPLIER, supplierString);
        values.put(StockContract.StockEntry.COLUMN_STOCK_NAME, nameString);
        values.put(StockContract.StockEntry.COLUMN_STOCK_CATEGORY, itemType);

        int price = 0;
        if (!TextUtils.isEmpty(priceString)) {
            price = Integer.parseInt(priceString);
        }
        values.put(StockContract.StockEntry.COLUMN_STOCK_PRICE, price);

        int quantity = 0;
        if (!TextUtils.isEmpty(quantityString)) {
            quantity = Integer.parseInt(quantityString);
        }
        values.put(StockContract.StockEntry.COLUMN_STOCK_QUANTITY, quantity);

        int nowUpdated = getContentResolver().update(mItemContentUri, values, null, null);
    }
}
