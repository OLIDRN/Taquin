package com.example.taquin;

import javafx.beans.InvalidationListener;
import javafx.beans.value.ObservableValue;

@FunctionalInterface
public interface ChangeListener<T>{
    void changed(ObservableValue<? extends T> observable, T oldValue, T newValue);
}
