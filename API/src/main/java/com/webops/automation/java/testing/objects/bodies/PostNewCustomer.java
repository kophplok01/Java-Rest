package com.webops.automation.java.testing.objects.bodies;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class PostNewCustomer {
    private int manufacturerId;
    private String messageId;
    private String timestamp;
    private int accountNumber;
    private String accountType;
    private int accountSubType;
    private int relatedAccountNumber;
    private String relatedAccountName;
    private String accountNameOrganization;
    private String accountNamePerson;
    private String customerType;
    private int enterpriseId;
    private String customerSubType;
    private int npiNumber;
    private String accountName;
    private boolean active;
    private String divisionName;
    private int salesOrganizationId;
    private String distributionChannelCode;
    private String divisionCode;
    private String partnerFunction;
    private String street1;
    private String street2;
    private String street3;
    private String street4;
    private String poBox;
    private int houseId;
    private int additionalHouseId;
    private int buildingId;
    private int floorId;
    private int roomId;
    private String streetName;
    private String city;
    private String postalCode;
    private String stateCode;
    private String countryCode;

    public PostNewCustomer() {
        this.manufacturerId = 1015;
        this.messageId = "2d648c64-3eee-4fc7-9e19-df6f14c3fefd";
        this.timestamp = "2018-11-05T13:15:30Z";
        this.accountNumber = 123456;
        this.accountType = "Z001";
        this.accountSubType = 9;
        this.relatedAccountNumber = 20068125;
        this.relatedAccountName = "GASTROENTEROLOGY ASSOCS OF ST AUGUST";
        this.accountNameOrganization = "DIGESTIVE DISEASE ENDO";
        this.accountNamePerson = "ABSHER,BRAD";
        this.customerType = "CustomerTypeExample";
        this.enterpriseId = 123456;
        this.customerSubType = "CustomerSubType";
        this.npiNumber = 123456;
        this.accountName = "accountNameExample";
        this.active = true;
        this.divisionName = "DivisionNameExample";
        this.salesOrganizationId = 123456;
        this.distributionChannelCode = "channelCodeExample";
        this.divisionCode = "divisionCodeExample";
        this.partnerFunction = "partnerFunctionExample";
        this.street1 = "12 Cherry Street";
        this.street2 = "12 Cherry Street";
        this.street3 = "12 Cherry Street";
        this.street4 = "12 Cherry Street";
        this.poBox = "poBoxExample";
        this.houseId = 123456;
        this.additionalHouseId = 123456;
        this.buildingId = 123456;
        this.floorId = 123456;
        this.roomId = 123456;
        this.streetName = "streetNameExample";
        this.city = "cityExample";
        this.postalCode = "postalCodeExample";
        this.stateCode = "stateCodeExample";
        this.countryCode = "countryCodeExample";
    }
}
