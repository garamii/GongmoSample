
package com.example.gongmosample.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * TODO JSON 파싱을 위한 준비
 */
@JsonIgnoreProperties(ignoreUnknown = true)     // 매칭 되는 필드가 없을 때 무시
public class Festival implements Parcelable {
    // 행사명
    public String CULTURE_NM;

    // 시작일자
    public String START_DT;

    // 종료일자
    public String END_DT;

    // 시작시간
    public String START_TIME;

    // 종료시간
    public String END_TIME;

    // 행사장 주소
    public String CTR_LOCATION;

    // 위도
    public String CTR_LOCATION_X;

    // 경도
    public String CTR_LOCATION_Y;

    // 전화번호
    public String TEL_NO;

    // 홈페이지
    public String HOMEPAGE_URL;

    // 티켓 가격
    public String TICKET_PRICE;

    // 관람 연령
    public String VIEW_AGE;

    //썸네일 이미지
    public String THUMB_IMAGE;


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.CULTURE_NM);
        dest.writeString(this.START_DT);
        dest.writeString(this.END_DT);
        dest.writeString(this.START_TIME);
        dest.writeString(this.END_TIME);
        dest.writeString(this.CTR_LOCATION);
        dest.writeString(this.CTR_LOCATION_X);
        dest.writeString(this.CTR_LOCATION_Y);
        dest.writeString(this.TEL_NO);
        dest.writeString(this.HOMEPAGE_URL);
        dest.writeString(this.TICKET_PRICE);
        dest.writeString(this.VIEW_AGE);
        dest.writeString(this.THUMB_IMAGE);
    }

    public Festival() {
    }

    protected Festival(Parcel in) {
        this.CULTURE_NM = in.readString();
        this.START_DT = in.readString();
        this.END_DT = in.readString();
        this.START_TIME = in.readString();
        this.END_TIME = in.readString();
        this.CTR_LOCATION = in.readString();
        this.CTR_LOCATION_X = in.readString();
        this.CTR_LOCATION_Y = in.readString();
        this.TEL_NO = in.readString();
        this.HOMEPAGE_URL = in.readString();
        this.TICKET_PRICE = in.readString();
        this.VIEW_AGE = in.readString();
        this.THUMB_IMAGE = in.readString();
    }

    public static final Parcelable.Creator<Festival> CREATOR = new Parcelable.Creator<Festival>() {
        public Festival createFromParcel(Parcel source) {
            return new Festival(source);
        }

        public Festival[] newArray(int size) {
            return new Festival[size];
        }
    };
}
