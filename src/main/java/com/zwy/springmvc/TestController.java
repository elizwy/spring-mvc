package com.zwy.springmvc;

import com.alibaba.nacos.client.naming.utils.RandomUtils;
import io.prometheus.client.Counter;
import io.prometheus.client.Gauge;
import io.prometheus.client.hotspot.DefaultExports;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.concurrent.ConcurrentHashMap;

@Controller
@RequestMapping("/config")
public class TestController {
    private Logger logger = LoggerFactory.getLogger("prometheus");

    /**
     * 一级name 不可重复
     */
    Gauge gauge = Gauge.build().name("ali_projcet_guage").labelNames("type").help("guage").register();
    Counter counter = Counter.build().name("ali_projcet_counter").labelNames("type").help("log desc").register();

    ConcurrentHashMap<String,Counter.Child> counterMap=new ConcurrentHashMap<>();
    ConcurrentHashMap<String,Gauge.Child> gauageMap=new ConcurrentHashMap<>();


    //    @Value("${mysql.name}")
    private String name = "zwy";
    //    @Value("${mysql.password}")
    private String password = "123";
    //    @Value("${mysql.url}")
    private String url = "www.zwy.com";

    @Autowired
    NestNameClass nestNameClass;


    public TestController() {
        logger.info("prometheus initial start");
        DefaultExports.initialize();
        new Thread(() -> {
            while (true) {
                counter.labels("errorLog").inc();
                gauge.labels("zwy_test").set(RandomUtils.nextInt(100));

                counterMap.getOrDefault("ali_project",buildCounter("ali_project")).inc(RandomUtils.nextInt(100));
                gauageMap.getOrDefault("ali_project",builGuage("ali_project")).set(RandomUtils.nextInt(100));

//                io.micrometer.core.instrument.Counter counter2 = Metrics.counter("ali_project_count", "type", "zwy");
//                counter2.increment(RandomUtils.nextInt(100));
//
//                DistributionSummary summary = Metrics.summary("ali_project_summary", "type", "zwy");
//                summary.record(RandomUtils.nextInt(100));
            }
        }).start();
    }

    private Counter.Child buildCounter(String type) {
        return counter.labels(type);
    }
    private Gauge.Child builGuage(String type) {
        return gauge.labels(type);
    }


    @RequestMapping(value = "/get")
    public String get(Model model) {
        System.out.println(System.getProperty("namespace"));
        System.out.println(url);
        System.out.println(name);
        System.out.println(password);
        model.addAttribute("url", url);
        model.addAttribute("name", name);
        model.addAttribute("password", password);
        model.addAttribute("nestName", nestNameClass.getNestConfigName());
        model.addAttribute("nestPassword", nestNameClass.getNestPassword());
        return "say";
    }


}
