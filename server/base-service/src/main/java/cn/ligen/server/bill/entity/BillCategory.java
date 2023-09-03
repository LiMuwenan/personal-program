package cn.ligen.server.bill.entity;

import lombok.Data;

/**
 * @author ligen
 * @date 2023/9/2 23:00
 * @description 账单类型美剧
 */
public enum BillCategory {

    FOOD(1, "食物", "FOOD"), // 一日三餐小零食
    FRUIT(2, "水果", "FRUIT"),
    SUPER_MARKET(3, "商超", "SUPER_MARKET"), // 超市
    DINNER(4, "聚餐", "DINNER"),
    WATER_BILL(20, "水费", "WATER_BILL"),
    ELECTRICITY_BILL(21, "电费", "ELECTRICITY_BILL"),
    GAS_BILL(22, "燃气费", "GAS_BILL"),
    HEATING_BIL(23, "取暖费", "HEATING_BIL"),
    PROPERTY_BILL(24, "物业费", "PROPERTY_BILL"),
    CAR_BILL(25, "车品费", "CAR_BILL"), // 车保险，燃油
    TRANSPORTATION(26, "交通费", "TRANSPORTATION"),
    PHONE_BILL(27, "电话费", "PHONE_BILL"),
    TRAVEL(40, "旅游", "TRAVEL"),
    BEAUTY(41, "美妆","BEAUTY"),
    ELECTRONIC(42, "电子产品", "ELECTRONIC"), // 电子产品
    INVESTMENT(90, "投资", "INVESTMENT"),
    SALARY(100, "薪水", "SALARY"),
    OTHER(200, "其他", "OTHER");
    private Integer code;
    private String message;
    private String name;

    private BillCategory(Integer code, String message, String name) {
        this.code = code;
        this.message = message;
        this.name = name;
    }

    public Integer getCode() {
        return code;
    }
    public String getMessage() {
        return message;
    }
    public String getName() {
        return name;
    }
}
