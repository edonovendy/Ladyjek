package ladyjek.twiscode.com.ladyjek.Model;


import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;

import org.json.JSONArray;
import org.json.JSONObject;

import ladyjek.twiscode.com.ladyjek.Utilities.SocketManager;

/**
 * Created by Unity on 19/05/2015.
 */
public class ApplicationData {


    private static String id="1";
    private static String nama ="Edo";
    private static String email = "edo@gmail.com";
    private static String password = "abcd";
    private static String hp = "83857565559";

    public static String phoneNumber = "089682587567";

    public static String registered_id = "";
    public static String login_id = "";
    public static String token = "";



    public static ModelUserOrder modelUserOrder = new ModelUserOrder(id,nama,email,password,hp,null,null);
    public static ModelUserOrder userLogin;
    //public static String phone = "2789";
    public static Boolean editPhone = false;
    public static Boolean editHome = false;

    public static LatLng posFrom;
    public static LatLng posDestination;
    public static LatLng posDriver = new LatLng(-7.2855448024207226,112.7096968626571);

    public static Class act = null;

    public static String addressFrom="";
    public static String addressDestination="";
    public static String detailFrom="";
    public static String detailDestination="";
    public static String distance="";
    public static String duration="";
    public static  String price = "";
    public static SocketManager socketManager = null;
    public static boolean isLogin = false;
    public static boolean isFindLocation = false;
    public static JSONArray getOrder = new JSONArray();

    public static final String PARSE_CHANNEL = "ladyjek";
    public static final String PARSE_APPLICATION_ID = "OqKceLUMcPo769myUmHlAlNLNGa0PxWgu6xNEYRD";
    public static final String PARSE_CLIENT_KEY = "WVJeHzaOwLUH5pQHTwnk9laqfQOrWIdRSPzcbGpC";

    //public static final String PARSE_APPLICATION_ID = "6v8hpUwe9Ba2BgUlgeuN8eQPulFFuGvVkiHPfEZ6";
    //public static final String PARSE_CLIENT_KEY = "iqG01ZjXfCODRvzejdb0qLX8ULoaP853jTEFi1xJ";
    public static final int NOTIFICATION_ID = 100;
    public static String PARSE_DEVICE_TOKEN = "";


    public static ModelOrder order = new ModelOrder();
    public static String driverID = "";
    public static ModelDriver driver = new ModelDriver();
    public static Marker markerFrom = null;
    public static Marker markerDriver = null;

}
