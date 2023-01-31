package com.example.roadfinance.activity.config;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ConfiguraçaoFirebase {
    private  static FirebaseAuth autenticacao;
    private static DatabaseReference firebase;

    //retorna a instancia do firebaseDatabase
    public static DatabaseReference getFirebaseDatabase(){
        if(firebase == null){
            firebase = FirebaseDatabase.getInstance("https://road-finance-1381c-default-rtdb.firebaseio.com").getReference();
        }
        return firebase;
    }


    //retorna a instancia firebaseAuth
    public  static FirebaseAuth getFirebaseAutenticacao(){
        if(autenticacao==null) {
            autenticacao = FirebaseAuth.getInstance();
        }
        return  autenticacao;
    }
}
