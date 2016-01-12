package com.nan.shopping.bean;


import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by liu on 15/9/15.
 */
public class BaseResult implements Parcelable {
    private boolean status;
    private String message;

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeByte(status ? (byte) 1 : (byte) 0);
        dest.writeString(this.message);
    }

    public BaseResult() {
    }

    protected BaseResult(Parcel in) {
        this.status = in.readByte() != 0;
        this.message = in.readString();
    }

    public static final Parcelable.Creator<BaseResult> CREATOR = new Parcelable.Creator<BaseResult>() {
        public BaseResult createFromParcel(Parcel source) {
            return new BaseResult(source);
        }

        public BaseResult[] newArray(int size) {
            return new BaseResult[size];
        }
    };
}
