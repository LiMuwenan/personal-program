package cn.ligen.server.bill.entity;

import java.util.Objects;

/**
 * @author ligen
 * @date 2023/9/2 23:00
 * @description 账单类型美剧
 */
public enum BillCategoryEnum {

    DAILY(1, "日常", "DAILY", true), // 一日三餐小零食，自己做饭
    FRUIT(2, "水果", "FRUIT", true),
    SUPER_MARKET(3, "商超", "SUPER_MARKET", true), // 大超市
    DINNER(4, "聚餐", "DINNER", true), // 大饭
    DISEASE(5, "医疗", "DISEASE", true), // 医疗
    WATER_BILL(20, "水费", "WATER_BILL", true),
    ELECTRICITY_BILL(21, "电费", "ELECTRICITY_BILL", true),
    GAS_BILL(22, "燃气费", "GAS_BILL", true),
    HEATING_BIL(23, "取暖费", "HEATING_BIL", true),
    PROPERTY_BILL(24, "物业费", "PROPERTY_BILL", true),
    CAR_BILL(25, "车品费", "CAR_BILL", true), // 车保险，燃油
    TRANSPORTATION(26, "交通费", "TRANSPORTATION", true),
    PHONE_BILL(27, "电话费", "PHONE_BILL", true),
    TRAVEL(40, "旅游", "TRAVEL", true),
    BEAUTY(41, "美妆","BEAUTY", true),
    ELECTRONIC(42, "电子产品", "ELECTRONIC", true), // 电子产品

    CLOTHES(43, "衣服鞋子", "CLOTHES", true), // 电子产品
    INVESTMENT(90, "投资", "INVESTMENT", true),
    SALARY(100, "薪水", "SALARY", false),
    INPUT(101, "收款", "SALARY", false), // 非二人收入
    OTHER(200, "其他", "OTHER", true);
    private Integer code;
    private String message;
    private String name;

    private Boolean isCost;

    private BillCategoryEnum(Integer code, String message, String name, Boolean isCost) {
        this.code = code;
        this.message = message;
        this.name = name;
        this.isCost = isCost;
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

    public Boolean getIsCost() {return isCost;}

    public static String getMessage(Integer code) {
        for (BillCategoryEnum value : BillCategoryEnum.values()) {
            if (Objects.equals(value.getCode(), code)) {
                return value.getMessage();
            }
        }
        return null;
    }

    public static Boolean getIsCost(Integer code) {
        for (BillCategoryEnum value : BillCategoryEnum.values()) {
            if (Objects.equals(value.getCode(), code)) {
                return value.getIsCost();
            }
        }
        return null;
    }
}
