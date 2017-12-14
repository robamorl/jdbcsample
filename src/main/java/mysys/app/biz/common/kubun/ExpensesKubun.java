package mysys.app.biz.common.kubun;

import java.util.Arrays;
import java.util.List;

import mysys.app.biz.common.kubun.stationery.Kubun;

/**
 *
 * 費目区分を管理するクラス
 *
 * @author Ryuji
 *
 */
public class ExpensesKubun {

    /** 費目区分：食費 */
    public static final String EXPENSES_KUBUN_FOOD = "01";
    /** 費目区分：水道光熱費 */
    public static final String EXPENSES_KUBUN_WATER_UTILITY_COST = "02";
    /** 費目区分：通信費 */
    public static final String EXPENSES_KUBUN_COMMUNICATION_COST = "03";
    /** 費目区分：遊興費 */
    public static final String EXPENSES_KUBUN_AMUSEMENT = "04";
    /** 費目区分：交通費 */
    public static final String EXPENSES_KUBUN_TRANSPORTATION = "05";
    /** 費目区分：美容費 */
    public static final String EXPENSES_KUBUN_BEAUTY = "06";
    /** 費目区分：医療費 */
    public static final String EXPENSES_KUBUN_MEDICAL_BILLS = "07";
    /** 費目区分：被服費 */
    public static final String EXPENSES_KUBUN_CLOTHING_COST = "08";
    /** 費目区分：生活雑貨・日用品 */
    public static final String EXPENSES_KUBUN_DAILY_NECESSITIES = "09";
    /** 費目区分：教育費 */
    public static final String EXPENSES_KUBUN_EDU = "10";
    /** 費目区分：交際費 */
    public static final String EXPENSES_KUBUN_ENTERTAINMENT = "11";
    /** 費目区分：住宅費 */
    public static final String EXPENSES_KUBUN_HOUSING = "12";
    /** 費目区分：ペット */
    public static final String EXPENSES_KUBUN_PET = "13";
    /** 費目区分：保険 */
    public static final String EXPENSES_KUBUN_INSURANCE = "14";
    /** 費目区分：社会保険 */
    public static final String EXPENSES_KUBUN_SOCIAL_INSURANCE = "15";
    /** 費目区分：税金 */
    public static final String EXPENSES_KUBUN_TAX = "16";
    /** 費目区分：その他費用 */
    public static final String EXPENSES_KUBUN_OTHER = "17";


    /** 口座区分のリスト */
    public static final List<Kubun> EXPENSES_KUBUN_LIST = Arrays.asList(new Kubun[]{
            new Kubun(EXPENSES_KUBUN_FOOD, "食費"),
            new Kubun(EXPENSES_KUBUN_WATER_UTILITY_COST, "水道光熱費"),
            new Kubun(EXPENSES_KUBUN_COMMUNICATION_COST, "通信費"),
            new Kubun(EXPENSES_KUBUN_AMUSEMENT, "遊興費"),
            new Kubun(EXPENSES_KUBUN_TRANSPORTATION, "交通費"),
            new Kubun(EXPENSES_KUBUN_BEAUTY, "美容費"),
            new Kubun(EXPENSES_KUBUN_MEDICAL_BILLS, "医療費"),
            new Kubun(EXPENSES_KUBUN_CLOTHING_COST, "被服費"),
            new Kubun(EXPENSES_KUBUN_DAILY_NECESSITIES, "生活雑貨・日用品"),
            new Kubun(EXPENSES_KUBUN_EDU, "教育費"),
            new Kubun(EXPENSES_KUBUN_ENTERTAINMENT, "交際費"),
            new Kubun(EXPENSES_KUBUN_HOUSING, "住宅費"),
            new Kubun(EXPENSES_KUBUN_PET, "ペット"),
            new Kubun(EXPENSES_KUBUN_INSURANCE, "保険"),
            new Kubun(EXPENSES_KUBUN_SOCIAL_INSURANCE, "社会保険"),
            new Kubun(EXPENSES_KUBUN_TAX, "税金"),
            new Kubun(EXPENSES_KUBUN_OTHER, "その他費用"),
    });

}
