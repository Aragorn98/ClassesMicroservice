package com.example.classes;

public class ClassesDetailed {
    private Long id;
    private String className;
    private String teacherName;
    private String groupName;

    public ClassesDetailed(Long id, String className, String teacherName, String groupName) {
        this.id = id;
        this.className = className;
        this.teacherName = teacherName;
        this.groupName = groupName;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
