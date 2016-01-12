package com.nan.shopping.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liu on 15/11/21.
 */
public class TestResult extends BaseResult implements Parcelable {


    /**
     * status : true
     * message :
     * data : [{"thumb":"http://www.tingshijie.com/Uploads/201404/534faef69d832.png","name":"听中国工作室","history":"","id":475,"isFollowed":false,"focusFans":22,"worksCount":16,"groupname":"入门主播"},{"thumb":"http://www.tingshijie.com/Uploads/201511/20151107164330.png","name":"听世界有声剧团","history":"","id":8075,"isFollowed":false,"focusFans":23,"worksCount":2,"groupname":"签约主播"},{"thumb":"http://www.tingshijie.com/Uploads/201404/535343622275d.jpg","name":"书虫","history":"","id":380,"isFollowed":false,"focusFans":26,"worksCount":6,"groupname":"签约主播"},{"thumb":"http://www.tingshijie.com/Uploads/20140412/1397286512.","name":"动之以情","history":"","id":371,"isFollowed":false,"focusFans":19,"worksCount":2,"groupname":"入门主播"},{"thumb":"","name":"章鱼","history":"","id":4873,"isFollowed":false,"focusFans":14,"worksCount":11,"groupname":"声音爱好者"},{"thumb":"http://fdfs.xmcdn.com/group3/M0A/54/37/wKgDslNQpkCCr7a8AC7oOfr4Fos171_web_x_large.jpg","name":"子厚","history":"","id":337,"isFollowed":false,"focusFans":12,"worksCount":3,"groupname":"签约主播"},{"thumb":"","name":"大灰狼","history":"","id":5244,"isFollowed":false,"focusFans":15,"worksCount":21,"groupname":"入门主播"},{"thumb":"http://www.tingshijie.com/Uploads/201404/533ffbb169aea.jpg","name":"DJ猪红","history":"","id":301,"isFollowed":false,"focusFans":12,"worksCount":3,"groupname":"入门主播"}]
     */

    /**
     * thumb : http://www.tingshijie.com/Uploads/201404/534faef69d832.png
     * name : 听中国工作室
     * history :
     * id : 475
     * isFollowed : false
     * focusFans : 22
     * worksCount : 16
     * groupname : 入门主播
     */

    private List<DataEntity> data;



    public void setData(List<DataEntity> data) {
        this.data = data;
    }



    public List<DataEntity> getData() {
        return data;
    }

    public static class DataEntity {
        private String thumb;
        private String name;
        private String history;
        private int id;
        private boolean isFollowed;
        private int focusFans;
        private int worksCount;
        private String groupname;

        public void setThumb(String thumb) {
            this.thumb = thumb;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setHistory(String history) {
            this.history = history;
        }

        public void setId(int id) {
            this.id = id;
        }

        public void setIsFollowed(boolean isFollowed) {
            this.isFollowed = isFollowed;
        }

        public void setFocusFans(int focusFans) {
            this.focusFans = focusFans;
        }

        public void setWorksCount(int worksCount) {
            this.worksCount = worksCount;
        }

        public void setGroupname(String groupname) {
            this.groupname = groupname;
        }

        public String getThumb() {
            return thumb;
        }

        public String getName() {
            return name;
        }

        public String getHistory() {
            return history;
        }

        public int getId() {
            return id;
        }

        public boolean isIsFollowed() {
            return isFollowed;
        }

        public int getFocusFans() {
            return focusFans;
        }

        public int getWorksCount() {
            return worksCount;
        }

        public String getGroupname() {
            return groupname;
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(this.data);
    }

    public TestResult() {
    }

    protected TestResult(Parcel in) {
        this.data = new ArrayList<DataEntity>();
        in.readList(this.data, List.class.getClassLoader());
    }

    public static final Parcelable.Creator<TestResult> CREATOR = new Parcelable.Creator<TestResult>() {
        public TestResult createFromParcel(Parcel source) {
            return new TestResult(source);
        }

        public TestResult[] newArray(int size) {
            return new TestResult[size];
        }
    };
}
