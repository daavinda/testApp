package market.common.utils;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.util.Date;
import java.text.SimpleDateFormat;

@Controller
@RequestMapping("/dump")
public class MysqlBackup {

    private static String ip = "localhost";
    private static String port = "3306";
    private static String database = "customercare";
    private static String user = "user";
    private static String pass = "password";
    private static String path = "/home/devinda/Desktop/";

    @RequestMapping(value = "/export", method = RequestMethod.GET)
    public void export() throws Exception{
        Date dateNow = new Date();
        SimpleDateFormat dateformatyyyyMMdd = new SimpleDateFormat("yyyyMMdd");
        String date_to_string = dateformatyyyyMMdd.format(dateNow);
        String ss = "dump.sql";
        String fullName = path + date_to_string + ss;
        String dumpCommand = "mysqldump " + database + " -h " + ip + " -u " + user + " -p" + pass;
        Runtime rt = Runtime.getRuntime();
        File test = new File(fullName);
        PrintStream ps;
        try {
            Process child = rt.exec(dumpCommand);
            ps = new PrintStream(test);
            InputStream in = child.getInputStream();
            int ch;
            while ((ch = in.read()) != -1) {
                ps.write(ch);
            }

            InputStream err = child.getErrorStream();
            while ((ch = err.read()) != -1) {
                System.out.write(ch);
            }
        } catch (Exception exc) {
            exc.printStackTrace();
        }

        downloadFile(fullName);
    }

    private void downloadFile(String path) throws Exception {

        System.out.println("http://localhost:8080"+path);

        String fileName = "fileName.sql";
        URL link = new URL(path);

        InputStream in = new BufferedInputStream(link.openStream());
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        byte[] buf = new byte[1024];
        int n = 0;
        while (-1!=(n=in.read(buf)))
        {
            out.write(buf, 0, n);
        }
        out.close();
        in.close();
        byte[] response = out.toByteArray();

        FileOutputStream fos = new FileOutputStream(fileName);
        fos.write(response);
        fos.close();
    }
}