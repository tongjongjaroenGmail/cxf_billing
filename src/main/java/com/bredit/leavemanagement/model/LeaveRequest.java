package com.bredit.leavemanagement.model;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

@Entity
@Table(name = "leave_request")
public class LeaveRequest extends BaseModel
{
    private static final long serialVersionUID = -2864083032342769043L;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id; // int(11) NOT NULL AUTO_INCREMENT,

    /* One to Many relationships */
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "leaveRequest", cascade = { CascadeType.ALL })
    @OrderBy("leaveRequest.id ,date")
    private List<Leave> leaves;

    /* Many to One relationships */
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "leave_type_id", nullable = false)
    private LeaveType leaveType; // int(11) NOT NULL,

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "request_employee_id", nullable = false)
    private Employee requestEmployee; // int(11) NOT NULL,

    @ManyToOne(fetch = FetchType.EAGER, optional = true)
    @JoinColumn(name = "approve_employee_id", nullable = true)
    private Employee approveEmployee; // int(11) DEFAULT NULL,

    @ManyToOne(fetch = FetchType.EAGER, optional = true)
    @JoinColumn(name = "reject_employee_id", nullable = true)
    private Employee rejectEmployee; // int(11) DEFAULT NULL,

    @ManyToOne(fetch = FetchType.EAGER, optional = true)
    @JoinColumn(name = "cancel_employee_id", nullable = true)
    private Employee cancelEmployee; // int(11) DEFAULT NULL,

    /* Entity's attributes */
    @Enumerated(EnumType.ORDINAL)
    private LeaveRequestStatus status; // int(11) NOT NULL COMMENT '0 = new, 1 = viewed, 2 = approved, 3 = reject, 4 = cancel',

    @Column(name = "total_no_days")
    private Float totalNoDays; // int(10) DEFAULT NULL COMMENT 'This is the value stamp of summary of leaves',
    
    @Column(name = "request_date", nullable = false)
    @Type(type="date")
    private Date requestDate; 
    
    @Column(name = "request_note")
    private String requestNote; 
    
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
    
    @Column(name = "reject_note")
    private String rejectNote; 
    
    public String getRejectNote() {
		return rejectNote;
	}

	public void setRejectNote(String rejectNote) {
		this.rejectNote = rejectNote;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getRequestNote() {
		return requestNote;
	}

	public void setRequestNote(String requestNote) {
		this.requestNote = requestNote;
	}

	public Date getRequestDate() {
		return requestDate;
	}

	public void setRequestDate(Date requestDate) {
		this.requestDate = requestDate;
	}

	public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public List<Leave> getLeaves()
    {
        return leaves;
    }

    public void setLeaves(List<Leave> leaves)
    {
        this.leaves = leaves;
    }

    public LeaveType getLeaveType()
    {
        return leaveType;
    }

    public void setLeaveType(LeaveType leaveType)
    {
        this.leaveType = leaveType;
    }

    public Employee getRequestEmployee()
    {
        return requestEmployee;
    }

    public void setRequestEmployee(Employee requestEmployee)
    {
        this.requestEmployee = requestEmployee;
    }

    public Employee getApproveEmployee()
    {
        return approveEmployee;
    }

    public void setApproveEmployee(Employee approveEmployee)
    {
        this.approveEmployee = approveEmployee;
    }

    public Employee getRejectEmployee()
    {
        return rejectEmployee;
    }

    public void setRejectEmployee(Employee rejectEmployee)
    {
        this.rejectEmployee = rejectEmployee;
    }

    public Employee getCancelEmployee()
    {
        return cancelEmployee;
    }

    public void setCancelEmployee(Employee cancelEmployee)
    {
        this.cancelEmployee = cancelEmployee;
    }

    public LeaveRequestStatus getStatus()
    {
        return status;
    }

    public void setStatus(LeaveRequestStatus status)
    {
        this.status = status;
    }

    public Float getTotalNoDays()
    {
        return totalNoDays;
    }

    public void setTotalNoDays(Float totalNoDays)
    {
        this.totalNoDays = totalNoDays;
    }

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Employee getCreateBy() {
		return createBy;
	}

	public void setCreateBy(Employee createBy) {
		this.createBy = createBy;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public Employee getUpdateBy() {
		return updateBy;
	}

	public void setUpdateBy(Employee updateBy) {
		this.updateBy = updateBy;
	}

    
}
