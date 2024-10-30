package nl.duckstudios.pintandpillage.entity.buildings;

import lombok.Getter;
import lombok.Setter;
import nl.duckstudios.pintandpillage.model.ResourceType;

import javax.persistence.Entity;
import java.util.HashMap;
import java.util.Map;

@Entity
public class Carpenter extends ResourceBuilding{

    @Getter
    @Setter
    public String name = "Carpenter";

    public Carpenter() {
        super.setDescription("A building to make planks from wood");
        this.updateBuilding();
        super.setGeneratesResource(ResourceType.Planks);
        super.setRequiresResources(ResourceType.Wood);
    }


    public Map<ResourceType, Integer> convertWoodToPlanks(Map<ResourceType, Integer> resource) {
        int woodAmount = resource.get(ResourceType.Wood);
        int planksProduced = (int) Math.floor(woodAmount * 0.75);
        resource.put(ResourceType.Planks, planksProduced);

        return resource;
    }


    @Override
    public void updateBuilding() {
        super.setConstructionTimeSeconds(20L *super.getLevel()+50L);
        super.setResourcesRequiredLevelUp(new HashMap<>() {
            {
                int level = Carpenter.super.getLevel();
                put(ResourceType.Stone.name(), level * 25 + 25);
                put(ResourceType.Wood.name(), level * 10 + 45);
            }
        });
    }
    private void setResourcesRequiredAtGivenLevel(int level){

    }
}
