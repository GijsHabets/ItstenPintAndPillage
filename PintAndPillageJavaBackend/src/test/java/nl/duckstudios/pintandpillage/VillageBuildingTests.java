package nl.duckstudios.pintandpillage;

import nl.duckstudios.pintandpillage.entity.Coord;
import nl.duckstudios.pintandpillage.entity.User;
import nl.duckstudios.pintandpillage.entity.Village;
import nl.duckstudios.pintandpillage.entity.buildings.Building;
import nl.duckstudios.pintandpillage.entity.buildings.Headquarters;
import nl.duckstudios.pintandpillage.entity.buildings.House;
import nl.duckstudios.pintandpillage.entity.buildings.Storage;
import nl.duckstudios.pintandpillage.helper.VillageFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class VillageBuildingTests {

    private VillageFactory villageFactory;
    private Village village;
    private Building storage;
    private Building house;

    @BeforeEach
    void setup(){
        village = new Village();

    }

    @Test
    void testCreateBasicVillage(){
        villageFactory = new VillageFactory();
        User testUser = new User();
        Coord testCoord = new Coord(10, 10);


        village = villageFactory.createBasicVillage(testUser, testCoord);

        assertEquals(10, village.getPositionX());
        assertEquals(10, village.getPositionY());

        Headquarters headquarters = (Headquarters) village.getBuildingsForTest().stream()
                .filter(building -> building instanceof Headquarters)
                .findFirst()
                .orElse(null);

        assertNotNull(headquarters);
        assertEquals(1, headquarters.getLevel());
        assertEquals(6, headquarters.getPosition().getX());
        assertEquals(6, headquarters.getPosition().getY());
        assertFalse(headquarters.isUnderConstruction());
    }

    @Test
    void testBuildStorageIncreasesResourceLimit() {
        storage = new Storage();
        int initialResourceLimit = village.getResourceLimit();



        village.createBuilding(storage);
        village.updateVillageState();

        int updatedResourceLimit = village.getResourceLimit();

        System.out.println("Initial Resource Limit: " + initialResourceLimit);
        System.out.println("Updated Resource Limit after building Storage: " + updatedResourceLimit);

        assertTrue(updatedResourceLimit > initialResourceLimit, "Expected resource limit to increase after building Storage.");
    }

    @Test
    void testBuildHouseIncreasesPopulationCapacity() {
        house = new House();
        int initialPopulationCapacity = village.getPopulation();

        village.createBuilding(house);
        house.setLevel(1);
        village.updateVillageState();

        int updatedPopulationCapacity = village.getPopulation();

        System.out.println("Initial Population Capacity: " + initialPopulationCapacity);
        System.out.println("Updated Population Capacity after building House: " + updatedPopulationCapacity);

        assertTrue(updatedPopulationCapacity > initialPopulationCapacity, "Expected population capacity to increase after building a House.");
    }

}
