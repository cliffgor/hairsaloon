import org.junit.*;

import static org.junit.Assert.*;

import org.sql2o.*;

import java.util.Arrays;



public class StylistTest {

  @Rule
  public DatabaseRule database = new DatabaseRule();



  @Before

  public void setUp() {

    DB.sql2o = new Sql2o("jdbc:postgresql://localhost:5432/hair_salon_test", "postgres", "cliero");

  }



  @After

  public void tearDown() {

    try(Connection con = DB.sql2o.open()) {

      String deleteStylistQuery = "DELETE FROM stylist *;";

      String deleteClientsQuery = "DELETE FROM clients *;";

      con.createQuery(deleteStylistQuery).executeUpdate();

      con.createQuery(deleteClientsQuery).executeUpdate();

    }

  }



  @Test

  public void stylist_instantiatesCorrectly_true() {

    Stylist teststylist = new stylist("Home");

    assertEquals(true, teststylist instanceof Stylist);

  }



  @Test

  public void getName_StylistInstantiatesWithName_Home() {

    Stylist testStylist = new Stylist("Home");

    assertEquals("Home", testStylist.getName());

  }



 @Test

 public void all_returnsAllInstancesOfStylist_true() {

   Stylist firstStylist = new Stylist("Home");

   firstStylist.save();

   Stylist secondStylist = new Stylist("Work");

   secondStylist.save();

   assertEquals(true, Stylist.all().get(0).equals(firstStylist));

   assertEquals(true, Stylist.all().get(1).equals(secondStylist));

 }



 @Test

 public void getId_categoriesInstantiateWithAnId_1() {

   Stylist testStylist = new Stylist("Home");

   testStylist.save();

   assertTrue(testStylist.getId() > 0);

 }



 @Test

 public void find_returnsStylistWithSameId_secondStylist() {

   Stylist firstStylist = new Stylist("Home");

   firstStylist.save();

   Stylist secondStylist = new Stylist("Work");

   secondStylist.save();

   assertEquals(Stylist.find(secondStylist.getId()), secondStylist);

 }



  @Test

  public void equals_returnsTrueIfNamesAretheSame() {

    Stylist firstStylist = new Stylist("Household chores");

    Stylist secondStylist = new Stylist("Household chores");

    assertTrue(firstStylist.equals(secondStylist));

  }



  @Test

  public void save_savesIntoDatabase_true() {

    Stylist myStylist = new Stylist("Household chores");

    myStylist.save();

    assertTrue(Stylist.all().get(0).equals(myStylist));

  }



  @Test

  public void save_assignsIdToObject() {

    Stylist myStylist = new Stylist("Household chores");

    myStylist.save();

    Stylist savedStylist = Stylist.all().get(0);

    assertEquals(myStylist.getId(), savedStylist.getId());

  }



  @Test

  public void getStylists_retrievesALlStylistsFromDatabase_StylistsList() {

    Stylist myStylist = new Stylist("Household chores");

    myStylist.save();

    Stylist firstStylist = new Stylist("Mow the lawn", myStylist.getId());

    firstStylist.save();

    Stylist secondStylist = new Stylist("Do the dishes", myStylist.getId());

    secondStylist.save();

    Stylist[] Stylists = new Stylist[] { firstStylist, secondStylist };

    assertTrue(myStylist.getStylists().containsAll(Arrays.asList(Stylists)));

  }



}