package co.in.doctornest.sms;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

public class SendSMS {
    public static final String ACCOUNT_SID = "AC43b2a72ebf9695526a26db400733ec73";
    public static final String AUTH_TOKEN = "20592d4e5870e67d3c74e33d3d79df36";

    public static final String TRAIL_NUMBER = "+1 903 568 6626";


    public static void sendSms(String body) {
        try {
            Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

            Message message = Message.creator(new PhoneNumber("+918855880900"),//to
                    new PhoneNumber(TRAIL_NUMBER),//from
                    body).create();
        } catch (Exception e) {
            System.out.println("SMS not SENT PLEAE CHECK");
        }
    }
}
