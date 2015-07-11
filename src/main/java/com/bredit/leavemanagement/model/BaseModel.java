/**
 * 
 */
package com.bredit.leavemanagement.model;

import java.io.Serializable;

/**
 * @author KaweepattC
 * 
 */
public abstract class BaseModel implements Serializable
{
    private static final long serialVersionUID = 6003559843382889017L;

    public abstract Serializable getId();
}
