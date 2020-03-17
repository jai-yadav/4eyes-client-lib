package com.iontrading.cat.foureyes.mock.server.modules;

import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

import javax.inject.Inject;

import org.apache.commons.lang.StringUtils;

import com.iontrading.isf.commons.util.ValueHolder;
import com.iontrading.xrs.api.IModifiableQueryRequest;
import com.iontrading.xrs.api.IQueryModule;
import com.iontrading.xrs.api.ModuleStatus;
import com.iontrading.xrs.api.XRSGenericResult;
import com.iontrading.xrs.api.XRSStatus;
import com.iontrading.xrs.api.request.FieldValueParameter;
import com.iontrading.xrs.api.request.IFilterParameter;
import com.iontrading.xrs.api.request.IFilterParameterVisitor;
import com.iontrading.xrs.api.request.expression.IExprParserFormulaFilterParameter;

public class FourEyesQueryModule extends BaseXrsModule implements IQueryModule {

    @Inject
    public FourEyesQueryModule() {
        super("4EyesQuery");
    }

    @Override
    public ModuleStatus getModuleStatus() {
        return new ModuleStatus(XRSStatus.RUNNING);
    }

    @Override
    public XRSGenericResult validate(IModifiableQueryRequest request) {
        final ValueHolder<String> message = new ValueHolder<>("");
        final Set<String> missingFields = new TreeSet<String>();
        missingFields.add("ProtocolVersion");
        Iterator<IFilterParameter> it = request.getFilterParameterCollection().iterator();
        while (it.hasNext()) {
            IFilterParameter filterParam = it.next();
            filterParam.acceptVisitor(new IFilterParameterVisitor() {
                @Override
                public void visit(FieldValueParameter param) {
                    String field = param.getFieldName();
                    if (field.equals("ProtocolVersion")) {
                        missingFields.remove(field);
                        Object value = param.getValues().get(0);
                        if (value.getClass().isAssignableFrom(Integer.class) && ((Integer) value) == 1) {
                            it.remove();
                        } else {
                            message.setValue("Unsupported protocol version. Currently supported version: " + 1);
                        }
                    }
                }

                @Override
                public void visit(IExprParserFormulaFilterParameter obj) {
                    message.setValue("WHERE conditions are not supported");
                }
            });
        }

        XRSGenericResult result = !missingFields.isEmpty() ?
                XRSGenericResult.error("Missing mandatory parameter(s): " + StringUtils.join(missingFields, ", ")) :
                message.getValue().isEmpty() ? XRSGenericResult.ok() : XRSGenericResult.error(message.getValue());

        return result;
    }

}
