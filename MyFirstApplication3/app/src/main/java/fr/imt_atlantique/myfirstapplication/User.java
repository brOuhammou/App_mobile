package fr.imt_atlantique.myfirstapplication;

import android.os.Parcel;
import android.os.Parcelable;
import android.widget.EditText;

import androidx.annotation.NonNull;

import java.util.Date;
import java.util.LinkedList;

public class User implements Parcelable {
    private  LinkedList<String> listphone = new LinkedList<>();
    //private String phone1;
    private String nom;
    private String prenom;
    private String depar;
    private String date;



    public User(String nom,String prenom,String depar,String date,LinkedList<String> listphone){
        this.nom=nom;
        this.prenom=prenom;
        this.depar=depar;
        this.date=date;
        this.listphone=listphone;


    }
    protected User(Parcel in) {
        nom=in.readString();
        prenom=in.readString();
        depar=in.readString();
        date=in.readString();
        in.readList(listphone, getClass().getClassLoader());
        //phone1=in.readString();
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        parcel.writeString(nom);
        parcel.writeString(prenom);
        parcel.writeString(depar);
        parcel.writeString(date);
        parcel.writeList(listphone);
        //parcel.writeString(phone1);
    }
    public String getnom(){return nom;}
    public String getprenom(){
        return prenom;
    }
    public String getdepar(){
        return depar;
    }
    public String getdate() {return date;}
    public LinkedList<String> getlistphone(){return listphone;}
    //public String getphone1(){return phone1;}
}
