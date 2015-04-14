package csusb.campussafety;

import android.app.Activity;
import android.os.Bundle;
import customadapters.ExpandableListAdapter;
import android.widget.ExpandableListView;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class Resources extends Activity {

    List<String> listDataHeader;
    HashMap<String, List<String>> listDataChild;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resources_page);

        // get the listview
        ExpandableListView expListView = (ExpandableListView) findViewById(R.id.lvExp);

        // preparing list data
        prepareListData();
        ExpandableListAdapter listAdapter = new ExpandableListAdapter(this, listDataHeader, listDataChild);

        // setting list adapter
        expListView.setAdapter(listAdapter);
    }

    /*
     * Preparing the list data
     */
    private void prepareListData() {
        listDataHeader = new ArrayList<>();
        listDataChild = new HashMap<>();

        // Adding child data
        listDataHeader.add("Purpose");
        listDataHeader.add("CSUSB Health Center");
        listDataHeader.add("Counseling & Psychological Services");
        listDataHeader.add("Women's Resource Center");
        listDataHeader.add("Rape, Abuse, Incest National Network");
        listDataHeader.add("Office on Violence Against Women");
        listDataHeader.add("National Criminal Justice Reference Service");
        listDataHeader.add("Ombuds Services");
        listDataHeader.add("Title IX & Gender Equity");
        listDataHeader.add("San Bernardino County 211");

        // Adding child data
        List<String> Purpose = new ArrayList<>();
        Purpose.add("The University Police Department Department strives to provide important information and helpful resources to the public including links for additional information, such as: Victim Assistance Information, Sex Offender Information, Forms & Documents, and Crime Preventable Tips.");

        List<String> CSUSB_Health_Center = new ArrayList<>();
        CSUSB_Health_Center.add("The Student Health Center provides first care and basic aid while attending CSUSB. The misssion of the Student Union Health Center is to provide compassionate, accessible opjects and cost effectiveness.  ");

        List<String> Counseling_and_Psychological_Services = new ArrayList<>();
        Counseling_and_Psychological_Services.add("Our hope is that this site will assist you in obtaining the services and community referrals necessary to enhance your college experience. The counseling staff at CAPS is available Monday through Friday with extended hours Monday through Thursday. CAPS is not open weekends or holidays, but phone counseling is available outside our hours of operation at (909)-537-5040.");
        Counseling_and_Psychological_Services.add("");

        List<String> Womens_Resource_Center = new ArrayList<>();
        Womens_Resource_Center.add("The Santos Manuel Student Union Women's Resource Center exists to provide a supportive place for all women on campus where diversity is respected and celebrated. ");
        Womens_Resource_Center.add("");

        List<String> Rape_Abuse_Incest_National_Network = new ArrayList<>();
        Rape_Abuse_Incest_National_Network.add("R.A.I.N.N. is the nation's largest anti-sexual violence organization.</a>");
        Rape_Abuse_Incest_National_Network.add(" ");

        List<String> Office_On_Violence_Against_Women = new ArrayList<>();
        Office_On_Violence_Against_Women.add("To enforce the law and defend the interests of the United States according to the law; to ensure public safety against threats foreign and domestic; to provide federal leadership in preventing and controlling crime; to seek just punishment for those guilty of unlawful behavior; and to ensure fair and impartial administration of justice for all Americans.");
        Office_On_Violence_Against_Women.add("");

        List<String> National_Criminal_Justice_Reference_Service = new ArrayList<>();
        National_Criminal_Justice_Reference_Service.add("Established in 1972, the National Criminal Justice Reference Service (NCJRS) is a federally funded resource offering justice and drug-related information to support research, policy, and program development worldwide.");
        National_Criminal_Justice_Reference_Service.add("");

        List<String> Ombuds_Services = new ArrayList<>();
        Ombuds_Services.add("The Mission of the Office of Ombuds Services ast CSUSB, is to provide a safe place where any member of the campus community may talk in confidence about a conflict, complaint, or issue with an impartial third party.");
        Ombuds_Services.add("");

        List<String> Title_IX_and_Gender_Equity = new ArrayList<>();
        Title_IX_and_Gender_Equity.add("Each CSU campus must have a Title IX Coordinator to oversee the Title IX responsibilities of addressing sexual discrimination, harassment and violence in an educational institution's academic, educational, extracurricular and athletic activities.");
        Title_IX_and_Gender_Equity.add("");

        List<String> San_Bernardino_County_211 = new ArrayList<>();
        San_Bernardino_County_211.add("Finding help used to mean calling dozens of phone numbers and navigating a sea of agencies and programs. An easier way to access information about community, social, health and government services in San Bernardino County.");
        San_Bernardino_County_211.add("");


        listDataChild.put(listDataHeader.get(0), Purpose); // Header, Child data
        listDataChild.put(listDataHeader.get(1), CSUSB_Health_Center);
        listDataChild.put(listDataHeader.get(2), Counseling_and_Psychological_Services);
        listDataChild.put(listDataHeader.get(3), Womens_Resource_Center);
        listDataChild.put(listDataHeader.get(4), Rape_Abuse_Incest_National_Network);
        listDataChild.put(listDataHeader.get(5), Office_On_Violence_Against_Women);
        listDataChild.put(listDataHeader.get(6), National_Criminal_Justice_Reference_Service);
        listDataChild.put(listDataHeader.get(7), Ombuds_Services);
        listDataChild.put(listDataHeader.get(8), Title_IX_and_Gender_Equity);
        listDataChild.put(listDataHeader.get(9), San_Bernardino_County_211);
    }
}
