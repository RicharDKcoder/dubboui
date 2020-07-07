package org.docryze.dubboui.service.impl;

import com.google.common.collect.Lists;
import org.apache.commons.lang.StringUtils;
import org.apache.curator.framework.CuratorFramework;
import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.RegistryConfig;
import org.apache.zookeeper.data.Stat;
import org.docryze.dubboui.service.DubboUiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author docryze
 */
@Service("dubboUiService")
public class DubboUiServiceImpl implements DubboUiService {
    @Value("${dubbo.ui.root.path}")
    private String rootPath;
    @Autowired
    private CuratorFramework curatorFramework;
    @Autowired
    private RegistryConfig registryConfig;


    public List<String> listDubboClass() throws Exception {
        Stat stat = null;
        stat = curatorFramework.checkExists().forPath(rootPath);
        if(stat == null){
            return Lists.newArrayList();
        }
        return curatorFramework.getChildren().forPath(rootPath);
    }

    public List<String> listDubboMethod(String className) throws Exception {


        Map<String, String> parameters = registryConfig.getParameters();
        Map<String, String> metaData = registryConfig.getMetaData();

        if(StringUtils.isBlank(className)){
            return Lists.newArrayList();
        }
        Stat stat = null;
        String path = rootPath + "/" + className;
        stat = curatorFramework.checkExists().forPath(path);
        if(stat == null){
            return Lists.newArrayList();
        }
        return curatorFramework.getChildren().forPath(path);
    }
}
