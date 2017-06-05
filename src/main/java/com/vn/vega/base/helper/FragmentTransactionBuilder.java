package com.vn.vega.base.helper;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;

/**
 * Created by binhbt on 8/2/2016.
 */
public class FragmentTransactionBuilder {
    private FragmentTransaction transaction;
    private Fragment fragment;
    private int viewId;
    private FragmentManager fragmentManager;
    private TransactionType transactionType = TransactionType.REPLACE;
    private Bundle aguments;
    private boolean needClearStack = false;
    private boolean needAddToBackStack = false;
    private String backstackTag = null;
    private int animEnter, animExit, animPopEnter, animPopExit;
    private int transitStyle = FragmentTransaction.TRANSIT_NONE;


    public enum TransactionType {
        ADD, REPLACE, REMOVE
    }

    public FragmentTransactionBuilder(int viewId, FragmentManager fragmentManager, Fragment fragment) {
        this.fragment = fragment;
        this.viewId = viewId;
        this.fragmentManager = fragmentManager;
    }

    public FragmentTransactionBuilder setTransaction(FragmentTransaction transaction) {
        this.transaction = transaction;
        return this;
    }

    public FragmentTransactionBuilder setType(TransactionType type) {
        this.transactionType = type;
        return this;
    }

    public FragmentTransactionBuilder setAgument(Bundle bundle) {
        this.aguments = bundle;
        return this;
    }

    public FragmentTransactionBuilder clearBackStack() {
        needClearStack = true;
        return this;
    }

    public FragmentTransactionBuilder addToBackStack() {
        needAddToBackStack = true;
        return this;
    }

    public FragmentTransactionBuilder setBacktackTag(String tag) {
        this.backstackTag = tag;
        return this;
    }

    public FragmentTransactionBuilder setAnim(int enter, int exit) {
        this.animEnter = enter;
        this.animExit = exit;
        return this;
    }

    public FragmentTransactionBuilder setAnimPop(int popEnter, int popExit) {
        this.animPopEnter = popEnter;
        this.animPopExit = popExit;
        return this;
    }

    public FragmentTransactionBuilder setTransitFragmentStyle(int transitStyle) {
        this.transitStyle = transitStyle;
        return this;
    }

    public void commit() {
        if (transaction == null) {
            transaction = fragmentManager
                    .beginTransaction();
        }
        transaction.setTransitionStyle(transitStyle);
        if (animEnter > 0 || animExit > 0) {
            transaction.setCustomAnimations(animEnter, animExit);
            if (animPopEnter > 0 || animPopExit > 0) {
                transaction.setCustomAnimations(animEnter, animExit, animPopEnter, animPopExit);
            }
        }

        if (aguments != null) {
            fragment.setArguments(aguments);
        }
        if (needClearStack) {
            fragmentManager.popBackStack(null,
                    FragmentManager.POP_BACK_STACK_INCLUSIVE);
        }
        switch (transactionType) {
            case ADD:
                transaction.add(viewId, fragment);
                break;
            case REMOVE:
                transaction.remove(fragment);
                break;

            default:
                transaction.replace(viewId, fragment);
                break;
        }

        if (needAddToBackStack) {
            transaction.addToBackStack(TextUtils.isEmpty(backstackTag) ? fragment.getClass().getName() : backstackTag);
        }
        transaction.commit();
    }
    public void commitAllowingStateLoss(){
        if (transaction == null) {
            transaction = fragmentManager
                    .beginTransaction();
        }
        transaction.setTransitionStyle(transitStyle);
        if (animEnter > 0 || animExit > 0) {
            transaction.setCustomAnimations(animEnter, animExit);
            if (animPopEnter > 0 || animPopExit > 0) {
                transaction.setCustomAnimations(animEnter, animExit, animPopEnter, animPopExit);
            }
        }

        if (aguments != null) {
            fragment.setArguments(aguments);
        }
        if (needClearStack) {
            fragmentManager.popBackStack(null,
                    FragmentManager.POP_BACK_STACK_INCLUSIVE);
        }
        switch (transactionType) {
            case ADD:
                transaction.add(viewId, fragment);
                break;
            case REMOVE:
                transaction.remove(fragment);
                break;

            default:
                transaction.replace(viewId, fragment);
                break;
        }

        if (needAddToBackStack) {
            transaction.addToBackStack(TextUtils.isEmpty(backstackTag) ? fragment.getClass().getName() : backstackTag);
        }
        transaction.commitAllowingStateLoss();
    }
}
