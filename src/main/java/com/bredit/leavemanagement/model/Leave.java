package com.bredit.leavemanagement.model;

import java.sql.Time;
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

@Entity
@Table(name = "`leave`")
public class Leave extends BaseModel
{
    private static final long serialVersionUID = -260998994122635242L;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id; // int(11) NOT NULL AUTO_INCREMENT,
    
    @Column(name = "date" ,nullable = false)
    private Date date; // date NOT NULL,
    
    @Column(name = "am",nullable = false)
    private Boolean am = true; // bit(1) NOT NULL DEFAULT b'1' COMMENT 'First half day',
    
    @Column(name = "pm",nullable = false)
    private Boolean pm = true; // bit(1) NOT NULL DEFAULT b'1' COMMENT 'Second half day',
    
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "leave_request_id", nullable = false)
    private LeaveRequest leaveRequest; // int(11) NOT NULL,
    
    @Column(name = "from_time")
    private Time fromTime; // time DEFAULT NULL,
    
    @Column(name = "to_time")
    private Time toTime; // time DEFAULT NULL,

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public Date getDate()
    {
        return date;
    }

    public void setDate(Date date)
    {
        this.date = date;
    }

    public Boolean getAm()
    {
        return am;
    }

    public void setAm(Boolean am)
    {
        this.am = am;
    }

    public Boolean getPm()
    {
        return pm;
    }

    public void setPm(Boolean pm)
    {
        this.pm = pm;
    }

    public LeaveRequest getLeaveRequest()
    {
        return leaveRequest;
    }

    public void setLeaveRequest(LeaveRequest leaveRequest)
    {
        this.leaveRequest = leaveRequest;
    }

	public Time getFromTime() {
		return fromTime;
	}

	public void setFromTime(Time fromTime) {
		this.fromTime = fromTime;
	}

	public Time getToTime() {
		return toTime;
	}

	public void setToTime(Time toTime) {
		this.toTime = toTime;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

  
}
