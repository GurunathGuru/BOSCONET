package com.integro.bosconet.apis;

import com.integro.bosconet.model.CoverPhotoList;
import com.integro.bosconet.model.GalleryVideosList;
import com.integro.bosconet.model.NewsImagesList;
import com.integro.bosconet.model.NewsList;
import com.integro.bosconet.model.NewsletterList;
import com.integro.bosconet.model.NotificationList;
import com.integro.bosconet.model.OurTeamList;
import com.integro.bosconet.model.PhotosGallery1List;
import com.integro.bosconet.model.PhotosGallery2List;
import com.integro.bosconet.model.SuccessStoryList;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ApiServices {

    @FormUrlEncoded
    @POST("/bosconet_news.php")
    Call<NewsList> getNewsList(@Field("updated_at") String updated_at);

    @FormUrlEncoded
    @POST("/bosconet_notification.php")
    Call<NotificationList> getNotificationList(@Field("updated_at") String updated_at);

    @FormUrlEncoded
    @POST("/bosconet_cphoto.php")
    Call<CoverPhotoList> getCoverPhotoList(@Field("updated_at") String updated_at);

    @FormUrlEncoded
    @POST("/bosconet_newsimage.php")
    Call<NewsImagesList> getNewsImagesList(@Field("news_id") String news_id);

    @FormUrlEncoded
    @POST("/bosconet_photos.php")
    Call<PhotosGallery1List> getPhotosGalleryList(@Field("updated_at") String updated_at);

    @FormUrlEncoded
    @POST("/bosconet_photo1.php")
    Call<PhotosGallery2List> getGalleryImagesList(@Field("p_id") String p_id);

    @FormUrlEncoded
    @POST("/bosconet_videos.php")
    Call<GalleryVideosList> getGalleryVideosList(@Field("updated_at") String updated_at);

    @FormUrlEncoded
    @POST("/bosconet_newsletter.php")
    Call<NewsletterList> getNewsletterList(@Field("updated_at") String updated_at);

    @FormUrlEncoded
    @POST("/bosconet_team.php")
    Call<OurTeamList> getOurTeamList(@Field("updated_at") String updated_at);

    @FormUrlEncoded
    @POST("/bosconet_sstory.php")
    Call<SuccessStoryList> getSuccessStoryList(@Field("updated_at") String updated_at);


}

