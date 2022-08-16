package io.fluent.qboxserver.demo.handler.complex;


import io.fluent.qboxserver.demo.model.complex.Complex;
import io.fluent.qboxserver.demo.model.complex.ComplexOperator;
import org.springframework.stereotype.Component;
import xyz.erupt.annotation.fun.OperationHandler;
import xyz.erupt.core.exception.EruptApiErrorTip;
import xyz.erupt.core.view.EruptApiModel;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;


@Component
public class OperationHandlerImpl implements OperationHandler<Complex, ComplexOperator> {

    @Resource
    private HttpServletRequest request; //展示自动注入功能

    @Override
    public String exec(List<Complex> data, ComplexOperator complexOperator, String[] param) {
      System.out.println(data);
      System.out.println(complexOperator);
      System.out.println(param);
        throw new EruptApiErrorTip(new EruptApiModel(EruptApiModel.Status.WARNING,
                "自定义报错提示：" + request.getServletPath(), EruptApiModel.PromptWay.NOTIFY));
    }

}
