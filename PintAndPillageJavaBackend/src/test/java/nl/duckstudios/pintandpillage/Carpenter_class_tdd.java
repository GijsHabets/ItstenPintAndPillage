package nl.duckstudios.pintandpillage;

import nl.duckstudios.pintandpillage.entity.Village;
import nl.duckstudios.pintandpillage.entity.buildings.Carpenter;
import nl.duckstudios.pintandpillage.entity.buildings.Mine;
import nl.duckstudios.pintandpillage.model.ResourceType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;

import static nl.duckstudios.pintandpillage.model.ResourceType.Wood;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class Carpenter_class_tdd {

    private Carpenter carpenter;
    private Village village;

    @BeforeEach
    void SetUp(){
        village = new Village();
            }



    @Test
    public void testCreateCarpenter(){
        carpenter = new Carpenter();
        Map<String, Integer> resourcesRequiredLevel1 = carpenter.getResourcesRequiredLevelUp();
        Long buildTime = carpenter.getConstructionTimeSeconds();
        int expectedWoodLevel1 = 45;
        int expectedStoneLevel1 = 25;
        Long expectedConstructionTimeLevel1 = 50L;
        assertEquals(expectedWoodLevel1, resourcesRequiredLevel1.get(Wood.name()));
        System.out.println("Expected wood zou 45 moeten zijn en is "+ resourcesRequiredLevel1.get(Wood.name()));
        assertEquals(expectedStoneLevel1, resourcesRequiredLevel1.get(ResourceType.Stone.name()));
        System.out.println("Expected stone zou 25 moeten zijn en is "+ resourcesRequiredLevel1.get(ResourceType.Stone.name()));
        assertEquals(expectedConstructionTimeLevel1,buildTime);
        System.out.println("Expected construction time zou 50 moeten zijn en is "+ buildTime);


    }

    @Test
    public void test_TurnWoodIntoPlanks() {
        Carpenter carpenter = new Carpenter();

        Map<ResourceType, Integer> resourcesInVillage = new HashMap<>();
        resourcesInVillage.put(ResourceType.Wood, 100);

        Map<ResourceType, Integer> result = carpenter.convertWoodToPlanks(resourcesInVillage);
        int planksInVillage = result.get(ResourceType.Planks);

        assertEquals(75, planksInVillage, "Expected planks should be 75");

        System.out.println("Expected planks should be 75 and is " + planksInVillage);
    }
}
