package org.docryze.dubboui.service;

import java.util.List;

public interface DubboUiService {
    List<String> listDubboClass() throws Exception;

    List<String> listDubboMethod(String className) throws Exception;

}
