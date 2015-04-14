package csusb.campussafety;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ExpandableListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import customadapters.ExpandableListAdapter;


public class Services extends Activity {

    private List<String> listDataHeader;
    private HashMap<String, List<String>> listDataChild;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_services_page);

        // get the listview
        ExpandableListView expListView = (ExpandableListView) findViewById(R.id.lvExp);

        // preparing list data
        prepareListData();

        ExpandableListAdapter listAdapter = new ExpandableListAdapter(this, listDataHeader, listDataChild);

        // setting list adapter
        expListView.setAdapter(listAdapter);
    }

    private void prepareListData() {
        listDataHeader = new ArrayList<>();
        listDataChild = new HashMap<>();

        // Adding child data
        listDataHeader.add("Live Scan Fingerprinting:");
        listDataHeader.add("Motorcycle Officer Program:");
        listDataHeader.add("Campus Escort Service:");
        listDataHeader.add("The University Police Explorers:");
        listDataHeader.add("The Police Chaplain Program:");
        listDataHeader.add("The Citizen Services Volunteer Program:");
        listDataHeader.add("The Holiday Helpers Program:");
        listDataHeader.add("Nixle:");

        // Adding child data
        List<String> live = new ArrayList<>();
        live.add("Live Scan Fingerprinting is done by appointment only. Please call (909) 537-3552 to schedule an appointment.");

        List<String> motorcycle = new ArrayList<>();
        motorcycle.add("The Motorcycle Officer Program provides traffic enforcement on campus and public safety outreach at events such as new student orientations.");

        List<String> campus = new ArrayList<>();
        campus.add("The escort program provides transportation to CSUSB students, staff, faculty, or visitors who are concerned about their safety. Escorts are provided by foot, department cart, or state vehicle.");

        List<String> explorers = new ArrayList<>();
        explorers.add("The University Police Explorers program mentors local youth who have an interest in law enforcement through a partnership with the San Bernardino Public Safety Academy.");

        List<String> chaplain = new ArrayList<>();
        chaplain.add("The Chaplain Program offers spiritual support to University Police officers as well as campus community during a crisis.");

        List<String> citizen = new ArrayList<>();
        citizen.add("Invites community volunteers to work with the department by assisting with campus patrol, escort service and crime prevention efforts.");

        List<String> holiday = new ArrayList<>();
        holiday.add("The annual event in which police officers, university staff and volunteers team up to assist needy families during the holidays.");

        List<String> nixle = new ArrayList<>();
        nixle.add("Provides free electronic alerts for accurate, important, and time sensitive information via text message, e-mail, and web. Get neighborhood-level public safety and health alerts and other relevant community information.");

        listDataChild.put(listDataHeader.get(0), live); // Header, Child data
        listDataChild.put(listDataHeader.get(1), motorcycle);
        listDataChild.put(listDataHeader.get(2), campus);
        listDataChild.put(listDataHeader.get(3), explorers);
        listDataChild.put(listDataHeader.get(4), chaplain);
        listDataChild.put(listDataHeader.get(5), citizen);
        listDataChild.put(listDataHeader.get(6), holiday);
        listDataChild.put(listDataHeader.get(7), nixle);

    }

}
