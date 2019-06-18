package com.omlotecommerce.databindingexample.two_way_binding;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.omlotecommerce.databindingexample.BR;

@Entity(tableName ="contacts" )
public class Contact extends BaseObservable {

    @PrimaryKey(autoGenerate =true)
    private int id;
    private String name;
    private String email;
    private String country;

    @Ignore
    public Contact() {
    }

    public Contact(String name, String email, String country) {
        this.name = name;
        this.email = email;
        this.country = country;
    }

    @Bindable
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
        notifyPropertyChanged(BR.id);
    }

    @Bindable
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        notifyPropertyChanged(BR.name);
    }

    @Bindable
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
        notifyPropertyChanged(BR.email);
    }

    @Bindable
    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
        notifyPropertyChanged(BR.country);
    }
}
