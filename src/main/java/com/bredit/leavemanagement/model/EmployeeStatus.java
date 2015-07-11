/**
 * 
 */
package com.bredit.leavemanagement.model;

public enum EmployeeStatus
{
    DUMMY(0, ""), ACTIVE(1, "Active"), RESIGN(2, "Resign");

    private int id;
    private String name;

    private EmployeeStatus(int id, String name)
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

    public static EmployeeStatus getById(int id)
    {
        for (EmployeeStatus e : values())
        {
            if (e.getId() == id)
                return e;
        }
        return null;
    }

}
