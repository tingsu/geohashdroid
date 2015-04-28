/*
 * GHDBasicDialogBuilder.java
 * Copyright (C) 2015 Nicholas Killewald
 *
 * This file is distributed under the terms of the BSD license.
 * The source package should have a LICENSE file at the toplevel.
 */

package net.exclaimindustries.geohashdroid.util;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import net.exclaimindustries.geohashdroid.R;

/**
 * Y'know, you'd THINK I wouldn't have to resort to a specialized class just to
 * retheme the standard dialog popups, and yet, here we are.
 */
public class GHDBasicDialogBuilder {
    /** This CONTAINS a builder, it isn't a derivative of one. */
    private AlertDialog.Builder mBuilder;
    private Context mContext;
    private View mView;
    private AlertDialog mDialog;
    private ClickHandler mPositiveClicker;
    private ClickHandler mNegativeClicker;

    /**
     * This just converts the plain OnClickListener to a DialogInterface sort of
     * OnClickListener.
     */
    private class ClickHandler implements View.OnClickListener {
        private int mWhich;
        private DialogInterface.OnClickListener mOtherListener;

        public ClickHandler(int which) {
            mWhich = which;
        }

        public void setOnClickListener(DialogInterface.OnClickListener listener) {
            mOtherListener = listener;
        }

        @Override
        public void onClick(View v) {
            if(mOtherListener != null)
                mOtherListener.onClick(mDialog, mWhich);
        }
    }

    /**
     * Constructs a new builder object.  This'll make an AlertDialog.Builder and
     * set it up with the necessary standard layout stuff.
     *
     * @param context the Context
     */
    @SuppressLint("InflateParams")
    public GHDBasicDialogBuilder(Context context) {
        mBuilder = new AlertDialog.Builder(context, AlertDialog.THEME_DEVICE_DEFAULT_LIGHT);
        mView = ((LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.ghd_alert_dialog, null);
        mBuilder.setView(mView);

        // We want the click handlers to at least LOOK like AlertDialog's stuff,
        // so we'll wrap these not-Dialog buttons with something that converts
        // it out.
        mPositiveClicker = new ClickHandler(DialogInterface.BUTTON_POSITIVE);
        mNegativeClicker = new ClickHandler(DialogInterface.BUTTON_NEGATIVE);

        mView.findViewById(R.id.positive).setOnClickListener(mPositiveClicker);
        mView.findViewById(R.id.negative).setOnClickListener(mNegativeClicker);

        mContext = context;
    }

    /**
     * Sets the dialog's title by ID.
     *
     * @param id the ID
     * @return this, for chaining purposes
     * @see AlertDialog.Builder#setTitle(int)
     */
    public GHDBasicDialogBuilder setTitle(int id) {
        mBuilder.setTitle(id);
        return this;
    }

    /**
     * Sets the dialog's title by String.
     *
     * @param title the title text
     * @return this, for chaining purposes
     * @see AlertDialog.Builder#setTitle(CharSequence)
     */
    public GHDBasicDialogBuilder setTitle(String title) {
        mBuilder.setTitle(title);
        return this;
    }

    /**
     * Sets the text on the positive button and what'll happen when it's
     * clicked.  Also, makes said button visible.
     *
     * @param text text to put on the button
     * @param listener an OnClickListener for onclicklistening
     * @return this, for chaining purposes
     */
    public GHDBasicDialogBuilder setPositiveButton(String text, DialogInterface.OnClickListener listener) {
        // This also makes the positive button visible.
        Button positive = (Button)mView.findViewById(R.id.positive);
        positive.setText(text);
        mPositiveClicker.setOnClickListener(listener);
        positive.setVisibility(View.VISIBLE);

        return this;
    }

    /**
     * Sets the text on the negative button and what'll happen when it's
     * clicked.  Also, makes said button visible.
     *
     * @param text text to put on the button
     * @param listener an OnClickListener for onclicklistening
     * @return this, for chaining purposes
     */
    public GHDBasicDialogBuilder setNegativeButton(String text, DialogInterface.OnClickListener listener) {
        // This also makes the negative button visible.
        Button negative = (Button)mView.findViewById(R.id.negative);
        negative.setText(text);
        mNegativeClicker.setOnClickListener(listener);
        negative.setVisibility(View.VISIBLE);

        return this;
    }

    /**
     * Sets the text on the dialog by ID.
     *
     * @param id the ID
     * @return this, for chaining purposes
     */
    public GHDBasicDialogBuilder setMessage(int id) {
        return setMessage(mContext.getText(id).toString());
    }

    /**
     * Sets the text on the dialog by String.
     *
     * @param text the String
     * @return this, for chaining purposes
     */
    public GHDBasicDialogBuilder setMessage(String text) {
        ((TextView)mView.findViewById(R.id.message)).setText(text);

        return this;
    }

    /**
     * Shows the AlertDialog.  Which is to say, calls show() on the internal
     * builder.
     *
     * @return the newly-shown AlertDialog
     */
    public AlertDialog show() {
        mDialog = mBuilder.show();

        return mDialog;
    }

    /**
     * Returns a newly-created AlertDialog.  This DOESN'T call show() on the
     * internal builder.  Crucial difference.
     *
     * @return the newly-created AlertDialog
     */
    public AlertDialog create() {
        return mBuilder.create();
    }
}