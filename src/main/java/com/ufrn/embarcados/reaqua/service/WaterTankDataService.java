package com.ufrn.embarcados.reaqua.service;

import com.ufrn.embarcados.reaqua.model.ApplicationUser;
import com.ufrn.embarcados.reaqua.model.Tower;
import com.ufrn.embarcados.reaqua.model.WaterTank;
import com.ufrn.embarcados.reaqua.model.WaterTankData;
import com.ufrn.embarcados.reaqua.repository.WaterTankDataRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.time.LocalDate;
import java.util.List;
import java.util.Properties;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class WaterTankDataService {

    //@Autowired
    private final WaterTankDataRepository waterTankDataRepository;

    //@Autowired
    private TowerService towerService;

    //@Autowired
    private ApplicationUserService applicationUserService;

    public void save(WaterTankData waterTankData)
    {
        if (waterTankData.getLevel() != null){
            if (waterTankData.getLevel() <= 25)
                sendUsersEmail(waterTankData); // @TODO
        }
        try {
            waterTankDataRepository.save(waterTankData);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    private void sendUsersEmail(WaterTankData waterTankData) {
        WaterTank waterTank = waterTankData.getWaterTank();
        Tower tower = towerService.getByWaterTank(waterTank);
        // @TODO Percorrer usuários para mandar email
        List<ApplicationUser> users = applicationUserService.getAllByTower(tower);
        for (ApplicationUser user: users) {
            sendUserEmail(user);
        }
    }

    private void sendUserEmail(ApplicationUser user) {
        // @TODO Substituir usuario e senha por usuario e senhas reais para
        // Que a notificação funcione
        String to = user.getEmail();
        String from = "reaquanoreplies@gmail.com";
        String host = "smtp.gmail.com";

        String username = "reaquanoreplies@gmail.com";
        String password = "***";
        Properties prop = new Properties();
        // mail.smtp.host=smtp.gmail.com, mail.smtp.port=25, mail.smtp.auth=true mail.smtp.starttls.enable=true
        prop.put("mail.smtp.auth", true);
        prop.put("mail.smtp.starttls.enable", true);
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "25");
        prop.put("mail.smtp.ssl.trust", "smtp.gmail.com");
        Session session = Session.getInstance(prop, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("reaquanoreplies@gmail.com"));
            message.setRecipients(
                    Message.RecipientType.TO, InternetAddress.parse(user.getEmail()));
            message.setSubject("Notificação ReAqua");

            String msg = "O reservatório da sua torre está abaixo de 25% e " +
                    "pode ser que em breve falte água no seu prédio.";

            MimeBodyPart mimeBodyPart = new MimeBodyPart();
            mimeBodyPart.setContent(msg, "text/html");

            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(mimeBodyPart);

            message.setContent(multipart);

            Transport.send(message);
            System.out.println("Email enviado para " + user.getEmail());
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public List<WaterTankData> getLevelsByDays(LocalDate startDate,
                                         LocalDate finalData)
    {
        return waterTankDataRepository.findByDateRange(startDate, finalData);
    }

    public List<WaterTankData> getAllData() {
        return waterTankDataRepository.findAll();
    }

	public List<WaterTankData> getDataByTankId(Long tankId) {
        return waterTankDataRepository.findByWaterTank(tankId);
    }

	public List<WaterTankData> listAll(){
		return waterTankDataRepository.findAll(Sort.by(Sort.Direction.DESC, "timestamp"));
	}
	
	public List<WaterTankData> findByWaterTankAndDate(WaterTank waterTank, Date startDate, Date endDate){
		return waterTankDataRepository.findByWaterTankAndTimestampBeforeAndTimestampAfterOrderByTimestamp(waterTank, startDate, endDate);
	}

}
