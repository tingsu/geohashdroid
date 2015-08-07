/*
 * CentralMapExtraFragment.java
 * Copyright (C) 2015 Nicholas Killewald
 *
 * This file is distributed under the terms of the BSD license.
 * The source package should have a LICENSE file at the toplevel.
 */

package net.exclaimindustries.geohashdroid.fragments;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

import net.exclaimindustries.geohashdroid.activities.DetailedInfoActivity;
import net.exclaimindustries.geohashdroid.activities.WikiActivity;
import net.exclaimindustries.geohashdroid.util.Info;

/**
 * This is the base class from which the two extra Fragments {@link net.exclaimindustries.geohashdroid.activities.CentralMap}
 * can use derive.  It simply allows better communication between them and
 * {@link net.exclaimindustries.geohashdroid.util.ExpeditionMode}.
 */
public abstract class CentralMapExtraFragment extends Fragment {
    /**
     * The various types of CentralMapExtraFragment that can exist.  It's either
     * this or a mess of instanceof checks to see what's currently showing.
     */
    public enum FragmentType {
        /** The Detailed Info fragment. */
        DETAILS,
        /** The Wiki fragment. */
        WIKI
    }

    /** The bundle key for the Info. */
    public final static String INFO = "info";

    /**
     * Now, what you've got here is your garden-variety interface that something
     * ought to implement to handle the close button on this here fragment.
     */
    public interface CloseListener {
        /**
         * Called when the user clicks the close button.  Use this opportunity
         * to either dismiss the fragment or close the activity that contains
         * it.
         *
         * @param fragment the CentralMapExtraFragment that is about to be closed
         */
        void extraFragmentClosing(CentralMapExtraFragment fragment);

        /**
         * Called during onDestroy().  ExpeditionMode needs this so it knows
         * when the user backed out of the fragment, as opposed to just the
         * close button.
         *
         * @param fragment the CentralMapExtraFragment that is being destroyed
         */
        void extraFragmentDestroying(CentralMapExtraFragment fragment);
    }

    protected CloseListener mCloseListener;
    protected Info mInfo;

    /**
     * Sets what'll be listening for the close button and/or an onDestroy event.
     *
     * @param listener some CloseListener somewhere
     */
    public void setCloseListener(CloseListener listener) {
        mCloseListener = listener;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // First, see if there's an instance state.
        if(savedInstanceState != null) {
            // If so, use the info in there.  Assuming it exists.
            mInfo = savedInstanceState.getParcelable(INFO);
        }

        // If mInfo is still null here (there was no instance state or there was
        // null data there), continue on to the arguments.
        if(mInfo == null) {
            Bundle args = getArguments();
            if(args != null) {
                mInfo = args.getParcelable(INFO);
            }
        }
    }

    @Override
    public void onDestroy() {
        // The parent needs to know when this fragment is destroyed so it can
        // make the FrameLayout go away.
        if(mCloseListener != null)
            mCloseListener.extraFragmentDestroying(this);

        super.onDestroy();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        // Also, remember that last info.  Owing to how ExpeditionMode works, it
        // might have changed since arguments time.  If it DIDN'T change, well,
        // it'll be the same as the arguments anyway.
        outState.putParcelable(INFO, mInfo);
    }

    /**
     * Registers a button (or, well, a {@link View} in general) to act as the
     * close button, calling the close method on the CloseListener.
     *
     * @param v the View that will act as the close button
     */
    protected void registerCloseButton(@NonNull View v) {
        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mCloseListener != null)
                    mCloseListener.extraFragmentClosing(CentralMapExtraFragment.this);
            }
        });
    }

    /**
     * Sets the Info for this fragment, to whatever degree that's useful for it.
     * Whatever gets set here will override any arguments originally passed in
     * if and when onSaveInstanceState is needed.
     *
     * @param info that new Info
     */
    public void setInfo(@Nullable final Info info) {
        mInfo = info;
    }

    /**
     * Gets what type of CentralMapExtraFragment this is so you don't have to
     * keep using instanceof.  Stop that.
     *
     * @return a FragmentType enum
     */
    @NonNull
    public abstract FragmentType getType();

    /**
     * Static factory that makes an Intent for a given FragmentType's Activity
     * container.  This happens if the user's on a phone.
     *
     * @param type the type
     * @return one factory-direct Intent
     */
    @NonNull
    public static Intent makeIntentForType(Context c, FragmentType type) {
        switch(type) {
            case DETAILS:
                return new Intent(c, DetailedInfoActivity.class);
            case WIKI:
                return new Intent(c, WikiActivity.class);
        }

        throw new RuntimeException("I don't know what sort of FragmentType " + type + " is supposed to be!");
    }

    /**
     * Static factory that makes a Fragment for a given FragmentType.  This
     * happens if the user's on a tablet.
     *
     * @param type the type
     * @return a fragment
     */
    public static CentralMapExtraFragment makeFragmentForType(FragmentType type) {
        switch(type) {
            case DETAILS:
                return new DetailedInfoFragment();
            case WIKI:
                return new WikiFragment();
        }

        throw new RuntimeException("I don't know what sort of FragmentType " + type + " is supposed to be!");
    }
}