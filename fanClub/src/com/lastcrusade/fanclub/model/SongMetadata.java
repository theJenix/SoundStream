package com.lastcrusade.fanclub.model;

import android.os.Parcel;
import android.os.Parcelable;

public class SongMetadata implements Parcelable {

    private long id;

    private String title;

    private String artist;

    private String album;

    // macAddress of person who added song to playlist
    private String macAddress; 
    
    public SongMetadata() {}

    public SongMetadata(long aId, String aTitle, String aArtist, String aAlbum, String aMacAddress) {
    	//be careful setting the song ID
        this.id = aId;
    	this.title = aTitle;
    	this.artist = aArtist;
    	this.album = aAlbum;
    	this.macAddress = aMacAddress;
    }

    public SongMetadata(Parcel in) {
        this.id         = in.readLong();
        this.title      = in.readString();
        this.artist     = in.readString();
        this.album      = in.readString();
        this.macAddress = in.readString();
    }

    @Override
    public int describeContents() {
        return 0; //default, no special kind of objects
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(this.id);
        dest.writeString(this.title);
        dest.writeString(this.artist);
        dest.writeString(this.album);
        dest.writeString(this.macAddress);
    }
    
    public static final Parcelable.Creator<SongMetadata> CREATOR = new Parcelable.Creator<SongMetadata>() {
        public SongMetadata createFromParcel(Parcel in) {
            SongMetadata sm = null;
            try {
                if (in != null) {
                    sm = new SongMetadata(in);
                }
            }
            catch (Exception e) {
            }

            return sm;
        }

        public SongMetadata[] newArray(int size) {
            return new SongMetadata[size];
        }
    };

    @Override
    public String toString() {
        return String.format("%s by %s on %s from %s", this.title, this.artist, this.album, this.macAddress);
    }
    
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public String getMacAddress() {
        return macAddress;
    }

    public void setMacAddress(String macAddress) {
        this.macAddress = macAddress;
    }

}
