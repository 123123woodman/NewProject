package wangjie.com.newproject.data.githttps;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Administrator on 2019/5/20.
 */

public class GitHttpsData {
    @SerializedName("current_user_url")
    String current_user_url;
    @SerializedName("current_user_authorizations_html_url")
    String current_user_authorizations_html_url;
    @SerializedName("authorizations_url")
    String authorizations_url;
    @SerializedName("code_search_url")
    String code_search_url;
    @SerializedName("commit_search_url")
    String commit_search_url;
    @SerializedName("emails_url")
    String emails_url;
    @SerializedName("emojis_url")
    String emojis_url;
    @SerializedName("events_url")
    String events_url;
    @SerializedName("feeds_url")
    String feeds_url;
    @SerializedName("followers_url")
    String followers_url;
    @SerializedName("following_url")
    String following_url;

    public String getCurrent_user_url() {
        return current_user_url;
    }

    public void setCurrent_user_url(String current_user_url) {
        this.current_user_url = current_user_url;
    }

    public String getCurrent_user_authorizations_html_url() {
        return current_user_authorizations_html_url;
    }

    public void setCurrent_user_authorizations_html_url(String current_user_authorizations_html_url) {
        this.current_user_authorizations_html_url = current_user_authorizations_html_url;
    }

    public String getAuthorizations_url() {
        return authorizations_url;
    }

    public void setAuthorizations_url(String authorizations_url) {
        this.authorizations_url = authorizations_url;
    }

    public String getCode_search_url() {
        return code_search_url;
    }

    public void setCode_search_url(String code_search_url) {
        this.code_search_url = code_search_url;
    }

    public String getCommit_search_url() {
        return commit_search_url;
    }

    public void setCommit_search_url(String commit_search_url) {
        this.commit_search_url = commit_search_url;
    }

    public String getEmails_url() {
        return emails_url;
    }

    public void setEmails_url(String emails_url) {
        this.emails_url = emails_url;
    }

    public String getEmojis_url() {
        return emojis_url;
    }

    public void setEmojis_url(String emojis_url) {
        this.emojis_url = emojis_url;
    }

    public String getEvents_url() {
        return events_url;
    }

    public void setEvents_url(String events_url) {
        this.events_url = events_url;
    }

    public String getFeeds_url() {
        return feeds_url;
    }

    public void setFeeds_url(String feeds_url) {
        this.feeds_url = feeds_url;
    }

    public String getFollowers_url() {
        return followers_url;
    }

    public void setFollowers_url(String followers_url) {
        this.followers_url = followers_url;
    }

    public String getFollowing_url() {
        return following_url;
    }

    public void setFollowing_url(String following_url) {
        this.following_url = following_url;
    }

    @Override
    public String toString() {
        return "GitHttpsData{" +
                "current_user_url='" + current_user_url + '\'' +
                ", current_user_authorizations_html_url='" + current_user_authorizations_html_url + '\'' +
                ", authorizations_url='" + authorizations_url + '\'' +
                ", code_search_url='" + code_search_url + '\'' +
                ", commit_search_url='" + commit_search_url + '\'' +
                ", emails_url='" + emails_url + '\'' +
                ", emojis_url='" + emojis_url + '\'' +
                ", events_url='" + events_url + '\'' +
                ", feeds_url='" + feeds_url + '\'' +
                ", followers_url='" + followers_url + '\'' +
                ", following_url='" + following_url + '\'' +
                '}';
    }


}
