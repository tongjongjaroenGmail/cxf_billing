/**
 * 
 */
package com.metasoft.claim.model;

import java.io.Serializable;

/**
 * @author 
 * 
 */
public abstract class BaseModel implements Serializable
{
    private static final long serialVersionUID = 6003559843382889017L;

    public abstract Serializable getId();
}
