package com.iontrading.cat.foureyes.mock.dataowner.modules;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

import javax.inject.Inject;

import com.iontrading.cat.foureyes.client_lib.enums.Fields;
import com.iontrading.xrs.api.DefaultXRSField;
import com.iontrading.xrs.api.IStructureModule;
import com.iontrading.xrs.api.IXRSField;
import com.iontrading.xrs.api.ModuleStatus;
import com.iontrading.xrs.api.XRSStatus;

public class FourEyesStructureModule extends BaseXrsModule implements IStructureModule {
    
    List<IXRSField> structure;

    @Inject
    public FourEyesStructureModule() {
        super("4EyesStructure");
        structure = new ArrayList<IXRSField>();
        EnumSet.allOf(Fields.class).forEach(field->structure.add(new DefaultXRSField(field.getServerString(), field.getFieldType())));
    }

    @Override
    public ModuleStatus getModuleStatus() {
        return new ModuleStatus(XRSStatus.RUNNING);
    }

    @Override
    public Iterable<IXRSField> getFieldsStructure() {
        return structure;
    }

}
