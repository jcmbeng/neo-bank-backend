package fr.adservio.neobankbackend.exceptions;

public enum ErrorCodes {

    // Account Entity 11000

    ACCOUNT_NOT_FOUND(11000),
    ACCOUNT_NOT_VALID(11001),
    ACCOUNT_ALREADY_IN_USE(11002),

    // AccountType Entity 21000

    ACCOUNT_TYPE_NOT_FOUND(21000),
    ACCOUNT_TYPE_NOT_VALID(21001),
    ACCOUNT_TYPE_ALREADY_IN_USE(21002),
    ACCOUNT_TYPE_CODE_ALREADY_IN_USE(21003),


    // City Entity 31000

    CITY_NOT_FOUND(31000),
    CITY_NOT_VALID(31001),
    CITY_NAME_ALREADY_IN_USE(31002),
    SELECTED_CITY_NOT_VALID(31003),
    CITY_CODE_ALREADY_IN_USE(31004),

    // Country Entity 41000


    COUNTRY_NOT_FOUND(41000),
    COUNTRY_NOT_VALID(41001),
    COUNTRY_ALREADY_IN_USE(41002),
    COUNTRY_CODE_ALREADY_IN_USE(41003),
    COUNTRY_NAME_ALREADY_IN_USE(41004),
    SELECTED_COUNTRY_NOT_VALID(41005),
    // Person Entity 51000

    PERSON_NOT_FOUND(51000),
    PERSON_NOT_VALID(51001),
    PERSON_ALREADY_IN_USE(51002),
    SELECTED_PERSON_NOT_VALID(51003),
    // Transaction Entity 61000

    TRANSACTION_NOT_FOUND(61000),
    TRANSACTION_NOT_VALID(61001),
    TRANSACTION_ALREADY_IN_USE(61002),

    // User Entity 71000

    USER_NOT_FOUND(71000),
    USER_NOT_VALID(71001),
    USER_ALREADY_IN_USE(71002),
    USER_LOGIN_FAILED_INVALID_CREDENTIALS(72003),


    PRODUCT_NOT_FOUND(81000),
    PRODUCT_NOT_VALID(81001),
    PRODUCT_NAME_ALREADY_IN_USE(81002),
    CODE_ALREADY_IN_USE(81003),

    ADRESS_NOT_FOUND(91000),
    ADRESS_NOT_VALID(91001),
    ADRESS_ALREADY_IN_USE(91002),
    SELECTED_ADRESS_NOT_VALID(91003),

    // Liste des exception techniaues
    UPDATE_PHOTO_EXCEPTION(14000),
    UNKNOWN_CONTEXT(14001),
    OBJECT_ID_NULL_OR_NOT_VALID(14002),
    EMAIL_ALREADY_IN_USE(14003),


    ;

    private int code;

    ErrorCodes(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}