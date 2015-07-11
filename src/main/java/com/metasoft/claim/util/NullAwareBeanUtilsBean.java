/**
 * 
 */
package com.metasoft.claim.util;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;

import org.apache.commons.beanutils.BeanUtilsBean;
import org.apache.commons.beanutils.converters.DateConverter;

/**
 * @author 
 * 
 */
public class NullAwareBeanUtilsBean extends BeanUtilsBean
{
    public NullAwareBeanUtilsBean()
    {
        super();
        this.getConvertUtils().register(new DateConverter(null), Date.class);
    }

    @Override
    public void copyProperty(Object dest, String name, Object value) throws IllegalAccessException, InvocationTargetException
    {
        if (value == null)
            return;
        super.copyProperty(dest, name, value);
    }
}
