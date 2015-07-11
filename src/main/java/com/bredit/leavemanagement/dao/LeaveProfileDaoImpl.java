/**
 * 
 */
package com.bredit.leavemanagement.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.bredit.leavemanagement.model.LeaveProfile;

/**
 * @author
 * 
 */
@Repository("LeaveProfileDao")
@Transactional
public class LeaveProfileDaoImpl extends AbstractDaoImpl<LeaveProfile, Integer> implements LeaveProfileDao
{
    public LeaveProfileDaoImpl()
    {
        super(LeaveProfile.class);
    }
}
