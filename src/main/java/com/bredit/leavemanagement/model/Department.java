/**
 * 
 */
package com.bredit.leavemanagement.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author KaweepattC
 * 
 */
@Entity
@Table(name = "department")
public class Department extends BaseModel
{
    private static final long serialVersionUID = 7291191154784023684L;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id; // int(11) NOT NULL AUTO_INCREMENT,
    
    @Column(name = "name", length = 300)
    private String name; // varchar(300) DEFAULT NULL,
    
    @Column(name = "code", length = 3)
    private String code; 

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

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

}
