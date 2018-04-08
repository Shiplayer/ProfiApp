package rf.master.registration.profiapp.data.entity;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

import rf.master.registration.profiapp.R;

/**
 * Created by Shiplayer on 28.03.18.
 */

public class Store implements Parcelable{
    private static Drawable titleImage;
    private String nameStore;
    private String firstNameManager;
    private String lastNameManager;
    private float shopRating;
    private float costRating;
    private List<Employee> employeeList;

    public Store(){

    }

    public Store(Parcel in){

        nameStore = in.readString();
        firstNameManager = in.readString();
        lastNameManager = in.readString();
        shopRating = in.readFloat();
        costRating = in.readFloat();
        employeeList = in.createTypedArrayList(Employee.CREATOR);
    }

    public static final Creator<Store> CREATOR = new Creator<Store>() {
        @Override
        public Store createFromParcel(Parcel in) {
            return new Store(in);
        }

        @Override
        public Store[] newArray(int size) {
            return new Store[size];
        }
    };

    public Store(Resources resources, String nameStore, String firstNameManager, String lastNameManager, float shopRating, float costRating) {
        if(titleImage == null)
            this.titleImage = resources.getDrawable(R.mipmap.ic_launcher);
        this.nameStore = nameStore;
        this.firstNameManager = firstNameManager;
        this.lastNameManager = lastNameManager;
        this.shopRating = shopRating;
        this.costRating = costRating;
        employeeList = new ArrayList<>();
    }

    public Drawable getTitleImage() {
        return titleImage;
    }

    public void addNewEmployee(Employee employee){
        employeeList.add(employee);
    }

    public Employee getEmployee(int index){
        return employeeList.get(index);
    }

    public void setTitleImage(Drawable titleImage) {
        this.titleImage = titleImage;
    }

    public String getNameStore() {
        return nameStore;
    }

    public void setNameStore(String nameStore) {
        this.nameStore = nameStore;
    }

    public String getFirstNameManager() {
        return firstNameManager;
    }

    public void setFirstNameManager(String firstNameManager) {
        this.firstNameManager = firstNameManager;
    }

    public String getLastNameManager() {
        return lastNameManager;
    }

    public void setLastNameManager(String lastNameManager) {
        this.lastNameManager = lastNameManager;
    }

    public float getShopRating() {
        return shopRating;
    }

    public void setShopRating(float shopRating) {
        this.shopRating = shopRating;
    }

    public float getCostRating() {
        return costRating;
    }

    public void setCostRating(float costRating) {
        this.costRating = costRating;
    }

    @Override
    public String toString() {
        return "Store{" +
                "nameStore='" + nameStore + '\'' +
                ", firstNameManager='" + firstNameManager + '\'' +
                ", lastNameManager='" + lastNameManager + '\'' +
                ", shopRating=" + shopRating +
                ", costRating=" + costRating +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    /*
    private String nameStore;
    private String firstNameManager;
    private String lastNameManager;
    private float shopRating;
    private float costRating;
    private List<Employee> employeeList;
     */

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(nameStore);
        dest.writeString(firstNameManager);
        dest.writeString(lastNameManager);
        dest.writeFloat(shopRating);
        dest.writeFloat(costRating);
        dest.writeList(employeeList);
    }
}
