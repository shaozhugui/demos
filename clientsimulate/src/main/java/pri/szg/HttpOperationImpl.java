package pri.szg;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import model.AlarmState;
import model.Frequency;
import model.Port;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HttpOperationImpl implements HttpOperation {
    private final static Logger LOG = LogManager.getLogger(HttpOperationImpl.class);
    private final static String HTTP = "http://";
    // http://127.0.0.1:8081/saveOrder
    private final static String CHARSET = "utf-8";
    private final static HttpClient HTTPCLIENT = HttpClients.createDefault();

    private String ip;
    private String socketPort;

    public HttpOperationImpl(String ip, String socketPort) {
        this.ip = ip;
        this.socketPort = socketPort;
    }

    private String getUri(String methodName) {
        return HTTP + ip + ":" + socketPort + "/" + methodName;
    }

    @Override
    public Frequency getFreq(int portId) {
        final String uri = getUri("getFreq");
        LOG.info("Query Frequency of portId is {}", portId);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("portId", portId);

        try {
            StringEntity entity = new StringEntity(jsonObject.toString(), CHARSET);

            String jsonStr = post(entity, uri);

            Frequency frequency = JSON.parseObject(jsonStr, Frequency.class);
            LOG.info("Result is {} for JsonStr is [{}]", frequency, jsonStr);
            return frequency;
        } catch (IOException e) {
            LOG.error("Get frequency fail. Exception: {}", e);
        }
        return null;
    }

    private String post(HttpEntity entity, String uri) throws IOException {
        HttpPost request = new HttpPost(uri);
        request.setEntity(entity);
        request.addHeader("Content-Type", "application/json;charset=utf-8");
        HttpResponse response = HTTPCLIENT.execute(request);
        int status = response.getStatusLine().getStatusCode();
        if (status < 200 ||  status >= 300) {
            LOG.error("response statu is {}", status);
            throw new ClientProtocolException("UnExpected statu: "+ status);
        }
        String result = EntityUtils.toString(response.getEntity());
        LOG.info("response is {}", result);
        return result;
    }

    @Override
    public boolean setFreq(int portId, Frequency frequency) {
        LOG.info("set freq to {} for portId {}", frequency, portId);
        final String uri = getUri("setFreq");

        //组合两个javaBean，可能有问题
        JSONObject obj = new JSONObject();
        obj.put("portId", portId);
        obj.put("freq", frequency);

        try {
            StringEntity entity = new StringEntity(obj.toString(), CHARSET);
            String jsonStr = post(entity, uri);
            return JSON.parseObject(jsonStr, Boolean.class);
        } catch (IOException e) {
            LOG.error("Set frequency fail. Exception: {}", e);
        }
        return false;
    }

    @Override
    public Port getPort(int portId) {
        final String uri = getUri("getPort");
        Port port = null;

        JSONObject obj = new JSONObject();
        obj.put("portId", portId);

        try {
            StringEntity entity = new StringEntity(obj.toString(), CHARSET);
            String jsonStr = post(entity, uri);
            port = JSON.parseObject(jsonStr, Port.class);
        } catch (IOException e) {
            LOG.error("Set frequency fail. Exception: {}", e);
        }
        return port;
    }

    @Override
    public List<Port> getPortsByState(AlarmState state) {
        final String uri = getUri("getPortsByState");
        List<Port> ports = new ArrayList<>();

        JSONObject obj = new JSONObject();
        obj.put("alarmstate", state.name());

        try {
            StringEntity entity = new StringEntity(obj.toString(), CHARSET);
            String jsonStr = post(entity, uri);
            ports = JSON.parseArray(jsonStr, Port.class);
        } catch (IOException e) {
            LOG.error("Set frequency fail. Exception: {}", e);
        }
        return ports;
    }

    @Override
    public boolean configNewPort(Port port) {
        final String uri = getUri("configNewPort");
        try {
            // 此处直接将javaBean转为string
            StringEntity entity = new StringEntity(JSONObject.toJSONString(port), CHARSET);
            String jsonStr = post(entity, uri);
            return JSON.parseObject(jsonStr, Boolean.class);
        } catch (IOException e) {
            LOG.error("Set frequency fail. Exception: {}", e);
        }
        return false;
    }
}
