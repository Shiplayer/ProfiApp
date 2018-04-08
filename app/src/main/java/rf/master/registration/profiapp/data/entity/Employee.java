package rf.master.registration.profiapp.data.entity;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Shiplayer on 28.03.18.
 */

public class Employee implements Parcelable{

    private String firstName;
    private String lastName;

    protected Employee(Parcel in) {
        firstName = in.readString();
        lastName = in.readString();
    }

    public static final Creator<Employee> CREATOR = new Creator<Employee>() {
        @Override
        public Employee createFromParcel(Parcel in) {
            return new Employee(in);
        }

        @Override
        public Employee[] newArray(int size) {
            return new Employee[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(firstName);
        dest.writeString(lastName);
    }
}
