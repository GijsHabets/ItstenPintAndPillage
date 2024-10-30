package nl.duckstudios.pintandpillage;

import nl.duckstudios.pintandpillage.entity.buildings.Mine;
import nl.duckstudios.pintandpillage.model.ResourceType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BuildingLevelTest {
    private Mine testBuilding;

    @BeforeEach
    void SetUp(){
        testBuilding = new Mine(){};
    }
    @Test
    void testWoodRequiredForUpgradeAtEachLevel(){
        testBuilding.setLevel(1);
        testBuilding.updateBuilding();
        Map<String, Integer> resourcesRequiredLevel1 = testBuilding.getResourcesRequiredLevelUp();
        int expectedWoodLevel1 = 1 * 25 + 25;
        assertEquals(expectedWoodLevel1, resourcesRequiredLevel1.get(ResourceType.Wood.name()),
                "Expected wood zou 50 moeten zijn");


        testBuilding.setLevel(2);
        testBuilding.updateBuilding();
        Map<String, Integer> resourcesRequiredLevel2 = testBuilding.getResourcesRequiredLevelUp();
        int expectedWoodLevel2 = 2 * 25 + 25;
        assertEquals(expectedWoodLevel2, resourcesRequiredLevel2.get(ResourceType.Wood.name()),
                "Expected wood zou 75 moeten zijn");

        testBuilding.setLevel(3);
        testBuilding.updateBuilding();
        Map<String, Integer> resourcesRequiredLevel3 = testBuilding.getResourcesRequiredLevelUp();
        int expectedWoodLevel3 = 3 * 25 + 25;
        assertEquals(expectedWoodLevel3, resourcesRequiredLevel3.get(ResourceType.Wood.name()),
                "Expected wood zou 100 moeten zijn");

    }

}
