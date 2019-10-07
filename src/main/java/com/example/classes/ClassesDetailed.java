package com.example.classes;

public class ClassesDetailed {
    private String className;
    private String teacherName;
    private String groupName;

    public ClassesDetailed(String className, String teacherName, String groupName) {
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
}
