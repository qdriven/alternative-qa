package io.fluent.qboxserver.demo.handler;

import io.fluent.qboxserver.demo.model.DemoComponent;
import org.apache.poi.ss.usermodel.Workbook;
import xyz.erupt.annotation.fun.DataProxy;
import xyz.erupt.annotation.query.Condition;

import java.util.Collection;
import java.util.List;
import java.util.Map;

@org.springframework.stereotype.Component
public class ComponentDataProxy implements DataProxy<DemoComponent> {

    @Override
    public void addBehavior(DemoComponent o) {
        o.setInput("China🇨🇳");
        o.setNumber1(3.1415926);
        o.setColor("#0099ff");
    }


    @Override
    public void beforeAdd(DemoComponent o) {
        System.err.println("beforeAdd");
    }

    @Override
    public void afterAdd(DemoComponent o) {
        System.err.println("afterAdd");
    }

    @Override
    public void beforeUpdate(DemoComponent o) {
        System.err.println("beforeUpdate");
    }

    @Override
    public void afterUpdate(DemoComponent o) {
        System.err.println("afterUpdate");
    }

    @Override
    public void beforeDelete(DemoComponent o) {
        System.err.println("beforeDelete");
    }

    @Override
    public void afterDelete(DemoComponent o) {
        System.err.println("afterDelete");
    }

    @Override
    public String beforeFetch(List<Condition> conditions) {
        System.err.println("beforeFetch");
        return null;
    }

    @Override
    public void afterFetch(Collection<Map<String, Object>> list) {
        System.err.println("afterFetch");
    }

    @Override
    public void editBehavior(DemoComponent o) {
        System.err.println("editBehavior");
    }

    @Override
    public void excelExport(Workbook wb) {
        System.err.println("excelExport");
    }

    @Override
    public void excelImport(DemoComponent o) {
        System.err.println("excelImport");
    }

}
