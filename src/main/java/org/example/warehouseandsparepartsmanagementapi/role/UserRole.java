package org.example.warehouseandsparepartsmanagementapi.role;

public enum UserRole {
    WAREHOUSE_OPERATOR("Warehouse Operator"),
    OPERATION_COORDINATOR("Operation Coordinator"),
    USER_ADMINISTRATOR("User Administrator");

    private final String displayName;

    UserRole(String displayName){
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
