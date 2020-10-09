package com.integro.bosconet.model;

import java.io.Serializable;

public class PhotosGallery2 implements Serializable {

    private String image;

    private String updated_at;

    private String id;

    private String p_id;

    public String getImage ()
    {
        return image;
    }

    public void setImage (String image)
    {
        this.image = image;
    }

    public String getUpdated_at ()
    {
        return updated_at;
    }

    public void setUpdated_at (String updated_at)
    {
        this.updated_at = updated_at;
    }

    public String getId ()
    {
        return id;
    }

    public void setId (String id)
    {
        this.id = id;
    }

    public String getP_id ()
    {
        return p_id;
    }

    public void setP_id (String p_id)
    {
        this.p_id = p_id;
    }
}
