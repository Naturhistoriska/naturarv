package se.nrm.dina.web.portal.model;

import java.io.Serializable;

/**
 *
 * @author idali
 */
public class CollectionGroupData  implements Serializable {
    
    private final String group;
    private final String groupName;
    private final String swedishGroupName;
    private final int total;
    
    public CollectionGroupData(String group, String groupName, String swedishGrouupName, int total) {
        this.group = group;
        this.groupName = groupName;
        this.swedishGroupName = swedishGrouupName;
        this.total = total;
    }

    public String getGroup() {
        return group;
    }
    
    public String getGroupName() {
        return groupName;
    }

    public String getSwedishGroupName() {
        return swedishGroupName;
    }

    public int getTotal() {
        return total;
    }
    
    @Override
    public String toString() {
        return group + " -- " + groupName + " -- " + total;
    }
}
