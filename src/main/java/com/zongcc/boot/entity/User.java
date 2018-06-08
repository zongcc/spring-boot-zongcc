package com.zongcc.boot.entity;

import java.io.Serializable;

/**
 * Created by chunchengzong on 2017-01-06.
 */
//@Component
//,locations = "classpath:config/app.properties"
//@ConfigurationProperties(prefix = "user")
////1.5以前版本可以使用locations，以后的话只能使用下边的属性
//@PropertySource("classpath:config/app.properties")
public class User implements Serializable {
    private static final long serialVersionUID = -2412012366788773382L;
    private Integer id;
    private String userName;

    public User() {
    }

    public User(Integer id, String userName) {
        this.id = id;
        this.userName = userName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (id != null ? !id.equals(user.id) : user.id != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
