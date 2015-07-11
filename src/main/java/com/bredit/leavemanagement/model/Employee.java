package com.bredit.leavemanagement.model;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

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

import org.hibernate.annotations.Formula;

@Entity
@Table(name = "employee")
public class Employee extends BaseModel
{
    private static final long serialVersionUID = -5218588168084900707L;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id; // int(11) NOT NULL AUTO_INCREMENT,

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "employee")
    private List<LeaveProfile> leaveProfiles;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "requestEmployee")
    @OrderBy("requestDate")
    private List<LeaveRequest> leaveRequests;

    @Column(name = "code", length = 100)
    private String code; // varchar(100) NOT NULL,

    @Column(name = "firstname", length = 300)
    private String firstname; // varchar(300) DEFAULT NULL,

    @Column(name = "lastname", length = 300)
    private String lastname; // varchar(300) DEFAULT NULL,

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "department_id", nullable = false)
    private Department department; // int(11) NOT NULL,

    @ManyToOne(fetch = FetchType.EAGER, optional = true)
    @JoinColumn(name = "role_id", nullable = true)
    private Role role; // int(11) DEFAULT NULL,

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "working_policy_id", nullable = false)
    private WorkingPolicy workingPolicy; // int(11) NOT NULL,

    @Enumerated(EnumType.ORDINAL)
    private EmployeeStatus status = EmployeeStatus.ACTIVE;

    @Formula(value = "CONCAT(IFNULL(firstname,''),' ', IFNULL(lastname,''))")
    private String fullName;
    
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
    
    @Column(name = "employee_id")
    private String employeeId; 
    
    @Column(name = "join_date")
    private Date joinDate; 

    public String getFullName()
    {
        return fullName;
    }

    public void setFullName(String fullName)
    {
        this.fullName = fullName;
    }

    /**
     * 
     * @return
     */
    public LeaveProfile getLeaveProfileInCurrentYear()
    {
        for (LeaveProfile leaveProfile : getLeaveProfiles())
        {
            SimpleDateFormat fmt = new SimpleDateFormat("yyyy");
            if (fmt.format(new Date()).equals(leaveProfile.getYear().toString()))
            {
                return leaveProfile;
            }
        }
        return null;
    }
    
    public LeaveProfile getLeaveProfileByYear(int year)
    {
        for (LeaveProfile leaveProfile : getLeaveProfiles())
        {
            if (year == leaveProfile.getYear().intValue())
            {
                return leaveProfile;
            }
        }
        return null;
    }

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public List<LeaveProfile> getLeaveProfiles()
    {
        return leaveProfiles;
    }

    public void setLeaveProfiles(List<LeaveProfile> leaveProfiles)
    {
        this.leaveProfiles = leaveProfiles;
    }

    public List<LeaveRequest> getLeaveRequests()
    {
        return leaveRequests;
    }

    public void setLeaveRequests(List<LeaveRequest> leaveRequests)
    {
        this.leaveRequests = leaveRequests;
    }

    public String getCode()
    {
        return code;
    }

    public void setCode(String code)
    {
        this.code = code;
    }

    public String getFirstname()
    {
        return firstname;
    }

    public void setFirstname(String firstname)
    {
        this.firstname = firstname;
    }

    public String getLastname()
    {
        return lastname;
    }

    public void setLastname(String lastname)
    {
        this.lastname = lastname;
    }

    public Department getDepartment()
    {
        return department;
    }

    public void setDepartment(Department department)
    {
        this.department = department;
    }

    public Role getRole()
    {
        return role;
    }

    public void setRole(Role role)
    {
        this.role = role;
    }

    public WorkingPolicy getWorkingPolicy()
    {
        return workingPolicy;
    }

    public void setWorkingPolicy(WorkingPolicy workingPolicy)
    {
        this.workingPolicy = workingPolicy;
    }

    public EmployeeStatus getStatus()
    {
        return status;
    }

    public void setStatus(EmployeeStatus status)
    {
        this.status = status;
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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	public Date getJoinDate() {
		return joinDate;
	}

	public void setJoinDate(Date joinDate) {
		this.joinDate = joinDate;
	}
}
