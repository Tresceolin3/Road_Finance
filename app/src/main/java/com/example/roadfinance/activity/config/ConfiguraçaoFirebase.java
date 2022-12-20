package com.example.roadfinance.activity.config;

import com.google.firebase.auth.FirebaseAuth;

public class ConfiguraçaoFirebase {
    private  static FirebaseAuth autenticacao;

    //
    public  static FirebaseAuth getFirebaseAutenticacao(){
        if(autenticacao==null) {
            autenticacao = FirebaseAuth.getInstance();
        }
        return  autenticacao;
    }
}
