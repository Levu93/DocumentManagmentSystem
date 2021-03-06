/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fon.documentmanagementsystem.dto;

/**
 *
 * @author Jelena
 */
public class TreeDto {

    public static final String ACTIVITY_ICON = "glyphicon glyphicon-ok";
    public static final String PROCESS_ICON = "glyphicon glyphicon-folder-open";
    private String id;
    private String parent;
    private String text;
    private String icon;
    private boolean primitive;
    private boolean activity = false;
    private String sign;
    private String description;
    private boolean hasChildren;

    public TreeDto(String id, String parent, String text, String icon, String sign, String description) {
        this.id = id;
        this.parent = parent;
        this.text = text;
        this.icon = icon;
        this.sign = sign;
        this.description = description;
        this.activity = true;
    }

    public TreeDto(String id, String parent, String text, String icon, boolean primitive, String sign, String description, boolean hasChildren) {
        this.id = id;
        this.parent = parent;
        this.text = text;
        this.icon = icon;
        this.primitive = primitive;
        this.sign = sign;
        this.description = description;
        this.hasChildren = hasChildren;
    }

    public TreeDto() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getParent() {
        return parent;
    }

    public void setParent(String parent) {
        this.parent = parent;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public boolean isPrimitive() {
        return primitive;
    }

    public void setPrimitive(boolean primitive) {
        this.primitive = primitive;
    }

    public boolean isActivity() {
        return activity;
    }

    public void setActivity(boolean activity) {
        this.activity = activity;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isHasChildren() {
        return hasChildren;
    }

    public void setHasChildren(boolean hasChildren) {
        this.hasChildren = hasChildren;
    }

    @Override
    public String toString() {
        return "TreeDto{" + "id=" + id + ", parent=" + parent + ", text=" + text + ", icon=" + icon + ", primitive=" + primitive + ", activity=" + activity + ", sign=" + sign + ", description=" + description + ", hasChildren=" + hasChildren + '}';
    }

}
