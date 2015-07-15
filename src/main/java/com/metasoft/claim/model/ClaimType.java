/**
 * 
 */
package com.metasoft.claim.model;


/**
 * @author 
 * 
 */
public enum ClaimType
{
    KFK(1, "KFK"), FAST_TRACK(2, "fast track"), REQUEST(3, "เรียกร้อง");

    private int id;
    private String name;

    private ClaimType(int id, String name)
    {
        this.id = id;
        this.name = name;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public static ClaimType getById(int id)
    {
        for (ClaimType e : values())
        {
            if (e.getId() == id)
                return e;
        }
        return null;
    }

}
