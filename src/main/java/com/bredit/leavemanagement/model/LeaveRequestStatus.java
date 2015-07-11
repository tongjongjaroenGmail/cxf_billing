/**
 * 
 */
package com.bredit.leavemanagement.model;


/**
 * @author KaweepattC
 * 
 */
public enum LeaveRequestStatus
{
    NEW(0, "New"), VIEWED(1, "Viewed"), APPROVED(2, "Approved"), REJECTED(3, "Rejected"), CANCELLED(4, "Cancelled");

    private int id;
    private String name;

    private LeaveRequestStatus(int id, String name)
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

    public static LeaveRequestStatus getById(int id)
    {
        for (LeaveRequestStatus e : values())
        {
            if (e.getId() == id)
                return e;
        }
        return null;
    }

}
