package com.example.webq.nilabhrajson;

class MyList {

    private String id;
    private String order_user_id;
    private String request_id;
    private String complaint_id;
    private String parent_category_id;
    private String parent_category_name;
    private String complaint_name;

    public MyList(String id, String order_user_id, String request_id, String complaint_id, String parent_category_id, String parent_category_name, String complaint_name) {
        this.id = id;
        this.order_user_id = order_user_id;
        this.request_id = request_id;
        this.complaint_id = complaint_id;
        this.parent_category_id = parent_category_id;
        this.parent_category_name = parent_category_name;
        this.complaint_name = complaint_name;
    }

    public String getId() {
        return id;
    }

    public String getOrder_user_id() {
        return order_user_id;
    }

    public String getRequest_id() {
        return request_id;
    }

    public String getComplaint_id() {
        return complaint_id;
    }

    public String getParent_category_id() {
        return parent_category_id;
    }

    public String getParent_category_name() {
        return parent_category_name;
    }

    public String getComplaint_name() {
        return complaint_name;
    }
}
