package com.huzzy.oauth_client.Scopces;


import android.support.annotation.NonNull;

public enum TwitchScope {
    channel_check_subscription,
    channel_feed_edit,
    channel_feed_read,
    channel_read,
    channel_stream,
    collections_edit,
    communities_edit,
    communities_moderate,
    openid,
    user_read,
    user_blocks_edit,
    user_blocks_read,
    user_follows_edit,
    user_subscriptions,
    viewing_activity_read,
    channel_editor,
    channel_subscriptions,
    channel_commercial,
    channelmoderate{
        @NonNull
        public String toString() {
            return "channel:moderate";
        }
    },chatread{
        @NonNull
        public String toString() {
            return "chat:read";
        }
    },chatedit{
        @NonNull
        public String toString() {
            return "chat:edit";
        }
    },whispersread{
        @NonNull
        public String toString() {
            return "whispers:read";
        }
    },whispersedit{
        @NonNull
        public String toString() {
            return "whispers:edit";
        }
    },analyticsreadextensions{
        @NonNull
        public String toString() {
            return "analytics:read:extensions";
        }
    }, analyticsreadgames{
        @NonNull
        public String toString() {
            return "analytics:read:games";
        }
    }, bitsread{
        @NonNull
        public String toString() {
            return "bits:read";
        }
    }, clipsedit{
        @NonNull
        public String toString() {
            return "clips:edit";
        }
    },useredit{
        @NonNull
        public String toString() {
            return "user:edit";
        }
    },usereditbroadcast{
        @NonNull
        public String toString() {
            return "user:edit:broadcast";
        }
    },userreadbroadcast{
        @NonNull
        public String toString() {
            return "user:read:broadcast";
        }
    },userreademail{
        @NonNull
        public String toString() {
            return "user:read:email";
        }
    }
}


