package com.bredit.leavemanagement.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "working_policy")
public class WorkingPolicy extends BaseModel
{
    private static final long serialVersionUID = -5253845141020484692L;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id; // int(11) NOT NULL,

    @Column(name = "name", length = 45, nullable = false)
    private String name; // varchar(45) NOT NULL,

    @Column(name = "weekend_description", length = 45, nullable = true)
    private String weekendDescription = "SAT,SUN"; // varchar(45) NOT NULL DEFAULT 'Saturnday,Sunday',

    @Column(name = "working_hour_per_day", nullable = false)
    private Integer workingHourPerDay = 8; // int(11) NOT NULL DEFAULT '8',

    @Column(name = "has_holiday", nullable = false)
    private Boolean hasHoliday = true; // has_holiday bit(1) NOT NULL DEFAULT b'1',

    public WorkingPolicy()
    {
        super();
    }

    public WorkingPolicy(int id)
    {
        super();
        this.id = id;
    }

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
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

    public String getWeekendDescription()
    {
        return weekendDescription;
    }

    public void setWeekendDescription(String weekendDescription)
    {
        this.weekendDescription = weekendDescription;
    }

    public Integer getWorkingHourPerDay()
    {
        return workingHourPerDay;
    }

    public void setWorkingHourPerDay(Integer workingHourPerDay)
    {
        this.workingHourPerDay = workingHourPerDay;
    }

    public Boolean getHasHoliday()
    {
        return hasHoliday;
    }

    public void setHasHoliday(Boolean hasHoliday)
    {
        this.hasHoliday = hasHoliday;
    }

}
