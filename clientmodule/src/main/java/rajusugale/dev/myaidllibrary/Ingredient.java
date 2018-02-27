package rajusugale.dev.myaidllibrary;

import android.os.Parcel;
import android.os.Parcelable;


public class Ingredient implements Parcelable {


    private String name;
    private int quantity;
    private String ingreType;

    public Ingredient(String name, int quantity, String ingreType) {
        this.name = name;
        this.quantity = quantity;
        this.ingreType = ingreType;
    }

    protected Ingredient(Parcel in) {
        name = in.readString();
        quantity = in.readInt();
        ingreType = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeInt(quantity);
        dest.writeString(ingreType);
    }

    public static final Creator<Ingredient> CREATOR = new Creator<Ingredient>() {
        @Override
        public Ingredient createFromParcel(Parcel in) {
            return new Ingredient(in);
        }

        @Override
        public Ingredient[] newArray(int size) {
            return new Ingredient[size];
        }
    };
    public String getName() {
        return name;
    }
}