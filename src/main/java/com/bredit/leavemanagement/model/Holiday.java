package com.bredit.leavemanagement.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "holiday")
public class Holiday extends BaseModel
{
    private static final long serialVersionUID = -5856371358828581909L;

    @Id
    @Column(name = "date")
    private Date date; // date NOT NULL,
    
    @Column(name = "name", length = 300)
    private String name; // varchar(300) DEFAULT NULL,

    public Date getId()
    {
        return date;
    }

    public Date getDate()
    {
        return date;
    }

    public void setDate(Date date)
    {
        this.date = date;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

}
