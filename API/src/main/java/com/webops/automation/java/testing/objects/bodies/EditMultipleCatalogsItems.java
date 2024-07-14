package com.webops.automation.java.testing.objects.bodies;
import com.webops.automation.java.testing.objects.reponses.CatalogItem;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
public class EditMultipleCatalogsItems {
    private String messageId;
    private String timestamp;
    private int manufacturerId;
    private CatalogItem[] items;

    public EditMultipleCatalogsItems(int inventoryId1, int inventoryId2, String catalogNumber1, String catalogNumber2) {
        this.messageId = "2d648c64-3eee-4fc7-9e19-df6f14c3fefd";
        this.timestamp = "2023-10-11T13:15:30Z";
        this.manufacturerId = 1015;
        this.items = new CatalogItem[2];
        this.items[0] = new CatalogItem(inventoryId1, catalogNumber1);
        this.items[1] = new CatalogItem(inventoryId2, catalogNumber2);
    }
}