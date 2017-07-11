package com.dianwoba.zapus.model;

import java.util.Date;
import org.apache.commons.lang.StringUtils;

public class ZapusTag {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column zapus_tag.id
     *
     * @mbg.generated Mon Jul 10 17:22:31 CST 2017
     */
    private Long id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column zapus_tag.created_date
     *
     * @mbg.generated Mon Jul 10 17:22:31 CST 2017
     */
    private Date createdDate;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column zapus_tag.last_modified_date
     *
     * @mbg.generated Mon Jul 10 17:22:31 CST 2017
     */
    private Date lastModifiedDate;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column zapus_tag.tagValue
     *
     * @mbg.generated Mon Jul 10 17:22:31 CST 2017
     */
    private String tagvalue;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column zapus_tag.created_user
     *
     * @mbg.generated Mon Jul 10 17:22:31 CST 2017
     */
    private Long createdUser;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column zapus_tag.last_modified_user
     *
     * @mbg.generated Mon Jul 10 17:22:31 CST 2017
     */
    private Long lastModifiedUser;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column zapus_tag.id
     *
     * @return the value of zapus_tag.id
     *
     * @mbg.generated Mon Jul 10 17:22:31 CST 2017
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column zapus_tag.id
     *
     * @param id the value for zapus_tag.id
     *
     * @mbg.generated Mon Jul 10 17:22:31 CST 2017
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column zapus_tag.created_date
     *
     * @return the value of zapus_tag.created_date
     *
     * @mbg.generated Mon Jul 10 17:22:31 CST 2017
     */
    public Date getCreatedDate() {
        return createdDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column zapus_tag.created_date
     *
     * @param createdDate the value for zapus_tag.created_date
     *
     * @mbg.generated Mon Jul 10 17:22:31 CST 2017
     */
    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column zapus_tag.last_modified_date
     *
     * @return the value of zapus_tag.last_modified_date
     *
     * @mbg.generated Mon Jul 10 17:22:31 CST 2017
     */
    public Date getLastModifiedDate() {
        return lastModifiedDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column zapus_tag.last_modified_date
     *
     * @param lastModifiedDate the value for zapus_tag.last_modified_date
     *
     * @mbg.generated Mon Jul 10 17:22:31 CST 2017
     */
    public void setLastModifiedDate(Date lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column zapus_tag.tagValue
     *
     * @return the value of zapus_tag.tagValue
     *
     * @mbg.generated Mon Jul 10 17:22:31 CST 2017
     */
    public String getTagvalue() {
        return tagvalue;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column zapus_tag.tagValue
     *
     * @param tagvalue the value for zapus_tag.tagValue
     *
     * @mbg.generated Mon Jul 10 17:22:31 CST 2017
     */
    public void setTagvalue(String tagvalue) {
        this.tagvalue = tagvalue == null ? null : tagvalue.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column zapus_tag.created_user
     *
     * @return the value of zapus_tag.created_user
     *
     * @mbg.generated Mon Jul 10 17:22:31 CST 2017
     */
    public Long getCreatedUser() {
        return createdUser;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column zapus_tag.created_user
     *
     * @param createdUser the value for zapus_tag.created_user
     *
     * @mbg.generated Mon Jul 10 17:22:31 CST 2017
     */
    public void setCreatedUser(Long createdUser) {
        this.createdUser = createdUser;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column zapus_tag.last_modified_user
     *
     * @return the value of zapus_tag.last_modified_user
     *
     * @mbg.generated Mon Jul 10 17:22:31 CST 2017
     */
    public Long getLastModifiedUser() {
        return lastModifiedUser;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column zapus_tag.last_modified_user
     *
     * @param lastModifiedUser the value for zapus_tag.last_modified_user
     *
     * @mbg.generated Mon Jul 10 17:22:31 CST 2017
     */
    public void setLastModifiedUser(Long lastModifiedUser) {
        this.lastModifiedUser = lastModifiedUser;
    }

    public int compare(ZapusTag o1, ZapusTag o2) {
        return o1.getTagvalue().compareTo(o2.getTagvalue());
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof ZapusTag && StringUtils.equalsIgnoreCase(this.getTagvalue(), ((ZapusTag) obj).getTagvalue());
    }

    @Override
    public int hashCode() {
        return StringUtils.trimToEmpty(this.getTagvalue()).hashCode();
    }

    public int compareTo(ZapusTag o) {
        return compare(this, o);
    }
}