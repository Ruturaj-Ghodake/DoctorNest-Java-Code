package co.in.doctornest.services.cron;

import co.in.doctornest.email.payloads.MailRequest;
import co.in.doctornest.email.service.MailService;
import co.in.doctornest.entity.Patient;
import co.in.doctornest.repository.DoctorRepository;
import co.in.doctornest.repository.PatientRepository;
import co.in.doctornest.sms.SendSMS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AppointmentScheduler {

    private static String defaultAppointId = "97548";
    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    MailService mailService;

    @Scheduled(cron = "*/10 * * * * *")
    public void scheduler() {
        List<Patient> patientList = patientRepository.findByAppointmentIdIsNull();
        if (!patientList.isEmpty()) {
            patientList.forEach(patient ->
                    scheduleAppoint(patient)
            );
        }
    }

    private void scheduleAppoint(Patient patient) {
        try {
            String appointmentId = createAppointmentId(patient);

            patientRepository.save(patient);

           // createSmsBodyAndSendSms(patient.getFirstName(),appointmentId);

            sendMail(patient.getEmail(), appointmentId, patient.getFirstName());

            System.out.println(appointmentId + " " + patient.getFirstName());
        }
        catch(Exception e){
            System.out.println(e.getStackTrace());
        }
    }

    private void sendMail(String emailId, String appointmentId, String pName){
        Map<String, Object> model = new HashMap<>();
        model.put("aId", appointmentId);
        model.put("name", pName);

        MailRequest mailRequest= new MailRequest();
        mailRequest.setSubject("DoctorNest - Appointment Id Details");
        mailRequest.setTo(emailId);

        mailService.sendEmail(mailRequest, model);
    }

    private String createAppointmentId(Patient patient) {
        StringBuilder appointmentId = new StringBuilder(defaultAppointId);
        String mobileNo = patient.getMobileNo();
        appointmentId.append(mobileNo.substring(5) + patient.getId());
        patient.setAppointmentId(appointmentId.toString());
        return appointmentId.toString();
    }

    private void createSmsBodyAndSendSms(String patientName, String appointmentId){
        StringBuilder smsBody = new StringBuilder();
        smsBody.append("Hi "+ patientName +", Thank for using DoctorNest, Your appointment id is " + appointmentId.toString() +
                ". Get well soon ..! #DoctorNest - The best for you !");
        SendSMS.sendSms(smsBody.toString());
    }
}
