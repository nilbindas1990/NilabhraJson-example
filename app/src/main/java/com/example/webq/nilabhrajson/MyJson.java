package com.example.webq.nilabhrajson;

public class MyJson {
    String id, name, email;

    public MyJson(String id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }
    public String getId() {
        return id;
    }
    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }
}
