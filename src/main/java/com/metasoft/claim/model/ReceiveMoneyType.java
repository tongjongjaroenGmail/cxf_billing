/**
 * 
 */
package com.metasoft.claim.model;


/**
 * @author 
 * 
 */
public enum ReceiveMoneyType
{
    KFK(1, "KFK"), BILLING(2, "วางบิล"), DEBIT(3, "ตัดบัญชี");

    private int id;
    private String name;

    private ReceiveMoneyType(int id, String name)
    {
        this.id = id;
        this.name = name;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
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

    public static ReceiveMoneyType getById(int id)
    {
        for (ReceiveMoneyType e : values())
        {
            if (e.getId() == id)
                return e;
        }
        return null;
    }

}
