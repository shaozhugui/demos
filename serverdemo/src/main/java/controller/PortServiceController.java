package controller;

import api.PortService;
import api.SetFreqRequest;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import model.AlarmState;
import model.Frequency;
import model.Port;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@SpringBootConfiguration
@Controller
public class PortServiceController {

    private final static Logger LOG = LogManager.getLogger(PortServiceController.class);

    @Autowired
    private PortService portService;

    @PostMapping("/getFreq")
    @ResponseBody
    private Frequency getFreq(@RequestBody String input) {
        int portId = Integer.parseInt(JSON.parseObject(input).get("portId").toString());
        // int portId = JSONObject.parseObject(input, Integer.class);
        return portService.getFreq(portId);
    }

    @PostMapping("/setFreq")
    @ResponseBody
    private boolean setFreq(@RequestBody String input) {
        SetFreqRequest request = JSONObject.parseObject(input, SetFreqRequest.class);
        return portService.setFreq(request);
    }

    @PostMapping("/getPort")
    @ResponseBody
    private Port getPort(@RequestBody String input) {
        int portId = Integer.parseInt(JSON.parseObject(input).get("portId").toString());
        return portService.getPort(portId);
    }

    @PostMapping("/getPortsByState")
    @ResponseBody
    private List<Port> getPortsByState(@RequestBody String input) {
        AlarmState alarmState
                = AlarmState.valueOf(JSON.parseObject(input).get("alarmstate").toString().toUpperCase());
        return portService.getPortsByState(alarmState);
    }

    @PostMapping("/configNewPort")
    @ResponseBody
    private boolean configNewPort(@RequestBody String input) {
        LOG.info("input is {}", input);
        //该方法无法获取到正确对象，原因是Port必须具有对应的构造方法，之前仅有id在构造方法内。所以其他值为null，与反射有关
        Port port = JSONObject.parseObject(input, Port.class);
        /*
        JSONObject obj = JSON.parseObject(input);
        int id = Integer.parseInt(obj.get("id").toString());
        int power = Integer.parseInt(obj.get("power").toString());
        int osnr = Integer.parseInt(obj.get("osnr").toString());
        String location = obj.get("location").toString();
        AlarmState alarmState
                = AlarmState.valueOf(obj.get("state").toString().toUpperCase());
        Frequency freq = JSONObject.parseObject(obj.get("freq").toString(), Frequency.class);
        Port port = new Port(location, power, osnr, id, freq, alarmState);
        */
        LOG.info("new port is {}", port);
        return portService.configNewPort(port);
    }

}
