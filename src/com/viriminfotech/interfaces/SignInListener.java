package com.viriminfotech.interfaces;

public interface SignInListener {
    void onSignInSuccessful();
    void onSignInError(int responseCode);
}
