package com.realtimecep.scribe.client;

import com.realtimecep.utils.DateUtils;
import com.realtimecep.utils.FileUtils;
import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TSocket;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import scribe.thrift.LogEntry;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;


/**
 * Scribe Client in Java Class.
 * <p/>
 * <p/>This class read a line from a local file and
 * <p/>send the line message to the Scribe central server.
 *
 * @author <a href=mailto:tedd824@gmail.com">Ted Won</a>
 * @version 1.0
 */
public class ScribeClient {

    private static Logger logger = LoggerFactory.getLogger(ScribeClient.class);

    private static String scribeCategory = "default";
    private static String scribeHost = "localhost";
    private static int scribePort = 1463;


    public static void main(String[] args) {

        try {
            // Read a file from local file system

            String filePath = "/tmp/dummy.dat";
            File inputFile = FileUtils.getFile(filePath);
            InputStream input = FileUtils.openInputStream(inputFile);
            InputStreamReader inputStreamReader = new InputStreamReader(input, "UTF-8");
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String line = reader.readLine();


            // Open connection to Scribe Server

            TSocket sock = new TSocket(new Socket(ScribeClient.scribeHost, ScribeClient.scribePort));
            TFramedTransport transport = new TFramedTransport(sock);
            TBinaryProtocol protocol = new TBinaryProtocol(transport, false, false);

            scribe.thrift.scribe.Client client = new scribe.thrift.scribe.Client(protocol);

            double start = System.currentTimeMillis();
            logger.debug("Start     Time: " + DateUtils.getDateTime((long) start));

            while (line != null) {

                perform(client, line);

                line = reader.readLine();

            }

            double curr = System.currentTimeMillis();
            double executiontime = (curr - start) / 1000;
            logger.debug("End       Time: " + DateUtils.getDateTime((long) curr));
            logger.debug("Execution Time: " + executiontime + " sec");

            transport.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private static void perform(scribe.thrift.scribe.Client client, String message) throws TException {

        List<LogEntry> logEntries = new ArrayList<LogEntry>(1);

        LogEntry entry = new LogEntry(ScribeClient.scribeCategory, message);
        logEntries.add(entry);

        client.Log(logEntries);

    }
}
