package com.example.juanmajr.flclase;

/**
 * Created by JuanmaJR on 10/12/2017.
 */

public class DataHolder {
    public String API_KEY="d54943f8f1b30c3fc2becfa076c9bb1f";
    public static DataHolder instance= new DataHolder();

    public FireBaseAdmin fireBaseAdmin;
    public DataHolder(){
        fireBaseAdmin = new FireBaseAdmin();
    }
}
