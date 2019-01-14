package market.common.service;

import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by devinda on 1/14/19.
 */
@Service("helperService")
public class HelperService {

    public Date formatDate(String date) {
        Date formattedDate = new Date();
        try {
            formattedDate = new SimpleDateFormat("yyyy-MM-dd").parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return formattedDate;
    }

}
