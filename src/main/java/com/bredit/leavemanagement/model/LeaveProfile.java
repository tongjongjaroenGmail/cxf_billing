package com.bredit.leavemanagement.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "leave_profile", uniqueConstraints = { @UniqueConstraint(columnNames = { "employee_id", "year" }) })
public class LeaveProfile extends BaseModel
{
    private static final long serialVersionUID = -7440910975354523464L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id; // int(11) NOT NULL AUTO_INCREMENT,

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "employee_id", nullable = false)
    private Employee employee;

    @Column(name = "leave_available", nullable = false)
    private Float leaveAvailable = 0f; // int(11) NOT NULL DEFAULT '0',

    @Column(name = "carry_forward", nullable = false)
    private Float carryForward = 0f; // int(11) NOT NULL DEFAULT '0',

    @Column(name = "year", nullable = false)
    private Integer year; // int(11) NOT NULL,

    @Column(name = "note", nullable = true)
    private String note;

    @Column(name = "create_date", nullable = false)
    private Date createDate;

    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "create_by", nullable = false)
    private Employee createBy;

    @Column(name = "update_date")
    private Date updateDate;

    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "update_by", nullable = true)
    private Employee updateBy;

    public Date getCreateDate()
    {
        return createDate;
    }

    public void setCreateDate(Date createDate)
    {
        this.createDate = createDate;
    }

    public Employee getCreateBy()
    {
        return createBy;
    }

    public void setCreateBy(Employee createBy)
    {
        this.createBy = createBy;
    }

    public Date getUpdateDate()
    {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate)
    {
        this.updateDate = updateDate;
    }

    public Employee getUpdateBy()
    {
        return updateBy;
    }

    public void setUpdateBy(Employee updateBy)
    {
        this.updateBy = updateBy;
    }

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public Float getLeaveAvailable()
    {
        return leaveAvailable;
    }

    public void setLeaveAvailable(Float leaveAvailable)
    {
        this.leaveAvailable = leaveAvailable;
    }

    public Float getCarryForward()
    {
        return carryForward;
    }

    public void setCarryForward(Float carryForward)
    {
        this.carryForward = carryForward;
    }

    public Integer getYear()
    {
        return year;
    }

    public void setYear(Integer year)
    {
        this.year = year;
    }

    public Employee getEmployee()
    {
        return employee;
    }

    public void setEmployee(Employee employee)
    {
        this.employee = employee;
    }

    public String getNote()
    {
        return note;
    }

    public void setNote(String note)
    {
        this.note = note;
    }

    public static long getSerialversionuid()
    {
        return serialVersionUID;
    }

}
