package com.logotet.androlottowheeler.threads;

import android.util.Log;

import com.logotet.androlottowheeler.model.AllStatic;
import com.logotet.androlottowheeler.model.FullWheel;
import com.logotet.androlottowheeler.model.Wheel;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * kreirano:
 * Date: Feb 3, 2006
 * Time: 2:45:44 PM
 */
public class WheelsFactory extends Thread{
    private static final String TAG = "WheelFactory";
    private static ArrayList wheels6 = null;
    private static ArrayList wheels7 = null;
    private static ArrayList wheels5 = null;

    private static String[] wheelNames = {
        "11550034.LS7", "11560010.LS7", "11570003.LS7", "11660086.LS7", "11670021.LS7",
        "12440024.LS7", "12450008.LS7", "12550060.LS7", "12560018.LS7", "12570006.LS7",
        "12660187.LS7", "12660408.LS7", "12670040.LS7", "13440034.LS7", "13450010.LS7",
        "13550098.LS7", "13560026.LS7", "13570011.LS7", "13660276.LS7", "13660888.LS7",
        "13670076.LS7", "13670224.LS7", "14440044.LS7", "14450014.LS7", "14460007.LS7",
        "14550154.LS7", "14560043.LS7", "14570016.LS7", "14660521.LS7", "14670100.LS7",
        "15440063.LS7", "15450021.LS7", "15460009.LS7", "15550223.LS7", "15560072.LS7",
        "15570027.LS7", "15660926.LS7", "15670217.LS7", "16440090.LS7", "16450028.LS7",
        "16460015.LS7", "16550337.LS7", "16560106.LS7", "16570039.LS7", "16661574.LS7",
        "16670385.LS7", "17440122.LS7", "17450045.LS7", "17460019.LS7", "17470011.LS7",
        "17550496.LS7", "17560148.LS7", "17570061.LS7", "17662352.LS7", "17670603.LS7",
        "18440159.LS7", "18450054.LS7", "18460024.LS7", "18470012.LS7", "18550694.LS7",
        "18560206.LS7", "18570084.LS7", "18663612.LS7", "18670912.LS7", "19440190.LS7",
        "19450075.LS7", "19460034.LS7", "19470016.LS7", "19550929.LS7", "19560297.LS7",
        "19570103.LS7", "19665336.LS7", "19671459.LS7", "20440226.LS7", "20450086.LS7",
        "20460040.LS7", "20470020.LS7", "20551239.LS7", "20560427.LS7", "20570155.LS7",
        "20672259.LS7", "21450112.LS7", "21460054.LS7", "21470028.LS7", "21551616.LS7",
        "21560590.LS7", "21570222.LS7", "21673258.LS7", "22450161.LS7", "22460067.LS7",
        "22470034.LS7", "22560800.LS7", "22570325.LS7", "22674682.LS7", "23440253.LS7",
        "23450195.LS7", "23460088.LS7", "23470042.LS7", "23561022.LS7", "23570440.LS7",
        "24460108.LS7", "24470048.LS7", "24561320.LS7", "24561664.LS7", "24570560.LS7",
        "25460133.LS7", "25470058.LS7", "25570694.LS7", "26460164.LS7", "26470068.LS7",
        "27460185.LS7", "27470078.LS7", "28470088.LS7", "29470107.LS7", "30470126.LS7",
        "31470153.LS7", "32470180.LS7", "33470212.LS7", "34470244.LS7", "35470277.LS7",
        "39470343.LS7", "08550006.LS7", "09440006.LS7", "09550009.LS7", "09660016.LS7",
        "10440010.LS7", "10550020.LS7", "10560006.LS7", "10660045.LS7", "10670008.LS7",
        "11440018.LS7", "11450005.LS7",

        "22330077.LS6", "23330104.LS6", "24330119.LS6", "26330130.LS6", "30330237.LS6",
        "08330004.LS6", "09330007.LS6", "10330010.LS6", "11330011.LS6", "12330015.LS6",
        "13330021.LS6", "14330025.LS6", "15330031.LS6", "16330043.LS6", "18330048.LS6",
        "19330069.LS6", "21340054.LS6", "22340065.LS6", "23340071.LS6", "24340078.LS6",
        "28340148.LS6", "30340194.LS6", "11340005.LS6", "12340008.LS6", "13340009.LS6",
        "14340012.LS6", "15340015.LS6", "16340019.LS6", "17340025.LS6", "18340027.LS6",
        "19340034.LS6", "20340043.LS6", "26350040.LS6", "27350046.LS6", "28350050.LS6",
        "30350062.LS6", "32350073.LS6", "33350079.LS6", "16350008.LS6", "17350011.LS6",
        "18350012.LS6", "19350015.LS6", "20350018.LS6", "21350021.LS6", "22350022.LS6",
        "23350026.LS6", "24350030.LS6", "25350036.LS6", "28360034.LS6", "30360042.LS6",
        "32360052.LS6", "40360104.LS6", "41360111.LS6", "42360120.LS6", "45360142.LS6",
        "19360009.LS6", "20360010.LS6", "21360013.LS6", "22360016.LS6", "23360019.LS6",
        "24360020.LS6", "25360023.LS6", "26360026.LS6", "27360030.LS6", "14440087.LS6",
        "15440121.LS6", "16440175.LS6", "17440242.LS6", "18440258.LS6", "19440352.LS6",
        "20440440.LS6", "21440585.LS6", "08440007.LS6", "09440012.LS6", "10440020.LS6",
        "11440035.LS6", "12440041.LS6", "13440072.LS6", "16450060.LS6", "17450079.LS6",
        "18450096.LS6", "19450140.LS6", "20450182.LS6", "21450229.LS6", "22450307.LS6",
        "09450003.LS6", "10450007.LS6", "11450012.LS6", "12450015.LS6", "13450024.LS6",
        "14450037.LS6", "15450046.LS6", "18460042.LS6", "19460054.LS6", "20460082.LS6",
        "21460111.LS6", "22460134.LS6", "23460141.LS6", "24460169.LS6", "11460005.LS6",
        "12460006.LS6", "13460010.LS6", "14460015.LS6", "15460021.LS6", "16460031.LS6",
        "17460038.LS6", "16550840.LS6", "17551272.LS6", "18551802.LS6", "19552485.LS6",
        "20553307.LS6", "21554378.LS6", "08550012.LS6", "09550030.LS6", "10550050.LS6",
        "11550101.LS6", "12550132.LS6", "13550245.LS6", "14550407.LS6", "15550659.LS6",
        "17560401.LS6", "18560569.LS6", "19560863.LS6", "20561071.LS6", "21561642.LS6",
        "22562152.LS6", "09560007.LS6", "10560015.LS6", "11560022.LS6", "12560042.LS6",
        "13560078.LS6", "14560125.LS6", "15560190.LS6", "16560280.LS6",


        "07330005.LS5", "07440009.LS5", "07450003.LS5", "08330008.LS5", "08340003.LS5",
        "08440020.LS5", "08450005.LS5", "09330012.LS5", "09340005.LS5", "09440030.LS5",
        "09450009.LS5", "10330017.LS5", "10340007.LS5", "10440051.LS5", "10450014.LS5",
        "11330020.LS5", "11340009.LS5", "11350005.LS5", "11440066.LS5", "11450022.LS5",
        "12330029.LS5", "12340012.LS5", "12350006.LS5", "12440113.LS5", "12450035.LS5",
        "13330034.LS5", "13340016.LS5", "13350009.LS5", "13440157.LS5", "13450050.LS5",
        "14330043.LS5", "14340020.LS5", "14350010.LS5", "14440231.LS5", "14450069.LS5",
        "15330057.LS5", "15340025.LS5", "15350013.LS5", "15440295.LS5", "15450095.LS5",
        "16330067.LS5", "16340032.LS5", "16350016.LS5", "16440405.LS5", "16450134.LS5",
        "17330068.LS5", "17340040.LS5", "17350020.LS5", "17440492.LS5", "17450182.LS5",
        "18330094.LS5", "18340049.LS5", "18350024.LS5", "18440664.LS5", "18450236.LS5",
        "19330113.LS5", "19340059.LS5", "19350028.LS5", "19440846.LS5", "19450317.LS5",
        "20330141.LS5", "20340070.LS5", "20350032.LS5", "20441083.LS5", "20450399.LS5",
        "21330151.LS5", "21340082.LS5", "21350037.LS5", "21441251.LS5", "21450487.LS5",
        "22330183.LS5", "22340093.LS5", "22350040.LS5", "22441573.LS5", "22450620.LS5",
        "23330196.LS5", "23340103.LS5", "23350049.LS5", "23441771.LS5", "23450768.LS5",
        "24330236.LS5", "24340116.LS5", "24350054.LS5", "24442237.LS5", "24450960.LS5",
        "25340134.LS5", "25350063.LS5", "25442706.LS5", "25451065.LS5", "26330260.LS5",
        "26340154.LS5", "26350068.LS5", "26443306.LS5", "26451228.LS5", "27350077.LS5",
        "27443848.LS5", "27451386.LS5", "28330373.LS5", "28350086.LS5", "28444550.LS5",
        "28451710.LS5", "29350097.LS5", "29445369.LS5", "29452061.LS5", "06440005.LS5",
        "30330504.LS5", "30350102.LS5", "30446232.LS5", "30452436.LS5", "31446780.LS5",
        "31452711.LS5", "32453010.LS5", "34350136.LS5", "35330802.LS5", "36330878.LS5",
        "36350181.LS5", "36455091.LS5"};


    @Override
    public void run() {
        Wheel wheel;
        Log.w(TAG, "Thread started");
        AllStatic.wheelSystems.clear();
        AllStatic.wheelSystems = getWheels(AllStatic.numbersDrawn, AllStatic.lottoSize);

    }

    public ArrayList getWheels(int vrstaLotoa, int maxSistem) {
        Log.w(TAG, "get wheels(" + vrstaLotoa + "," + maxSistem + ")");
        if ((wheels6 == null) || (wheels7 == null) || wheels5 == null)
            createCollections();
        switch (vrstaLotoa) {
            case 5:
                return getArrayListSubset(wheels5, maxSistem);
            case 7:
                return getArrayListSubset(wheels7, maxSistem);
            default:
                return getArrayListSubset(wheels6, maxSistem);
        }
    }

    private ArrayList getArrayListSubset(ArrayList sisArrayList, int maxSistem) {
        ArrayList tmp = new ArrayList();
        Iterator enm = sisArrayList.iterator();
        while (enm.hasNext()) {
            Wheel wheel = (Wheel) enm.next();
            if (wheel.getOdigranihBrojeva() <= maxSistem)
                tmp.add(wheel);
        }
        return tmp;
    }

    private void createCollections() {
        Log.w(TAG, "Create collesction");
        wheels5 = new ArrayList();
        wheels6 = new ArrayList();
        wheels7 = new ArrayList();
        for (int i = 0; i < wheelNames.length; i++) {
            String wheelName = wheelNames[i];
            String tip = wheelName.substring(9);
            try {
                int a = Integer.parseInt(wheelName.substring(0, 2));
                int b = Integer.parseInt(wheelName.substring(2, 3));
                int c = Integer.parseInt(wheelName.substring(3, 4));
                int d = Integer.parseInt(wheelName.substring(4, 8));
                if (tip.equals("LS6")) {
                    wheels6.add(new Wheel(a, b, c, d, 6));
                } else if (tip.equals("LS7")) {
                    wheels7.add(new Wheel(a, b, c, d, 7));
                } else
                    wheels5.add(new Wheel(a, b, c, d, 5));
            } catch (NumberFormatException nfe) {
            }
        }
    }
}
