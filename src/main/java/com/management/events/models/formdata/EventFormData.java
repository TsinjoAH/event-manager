package com.management.events.models.formdata;

import com.management.events.models.City;
import com.management.events.models.Type;

import java.util.List;

public class EventFormData {
    private List<City> cityList;
    private List<Type> types;

    public EventFormData() {
    }

    public EventFormData(List<City> cityList, List<Type> types) {
        this.cityList = cityList;
        this.types = types;
    }

    public List<City> getCityList() {
        return cityList;
    }

    public void setCityList(List<City> cityList) {
        this.cityList = cityList;
    }

    public List<Type> getTypes() {
        return types;
    }

    public void setTypes(List<Type> types) {
        this.types = types;
    }
}
