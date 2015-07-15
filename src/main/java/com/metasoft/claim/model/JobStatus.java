/**
 * 
 */
package com.metasoft.claim.model;


/**
 * @author 
 * 
 */
public enum JobStatus
{
    RECEIVED(0, "รับงาน"), FOLLOWED(1, "ออกหนังสือติดตาม"), CLOSED(2, "ปิดงาน"), CANCELLED(3, "ยกเลิก");

    private int id;
    private String name;

    private JobStatus(int id, String name)
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

    public static JobStatus getById(int id)
    {
        for (JobStatus e : values())
        {
            if (e.getId() == id)
                return e;
        }
        return null;
    }

}
